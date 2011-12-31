package com.mosso.deimos.book.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.mosso.deimos.book.dao.QuestionDao;
import com.mosso.deimos.book.dao.TextbookDao;
import com.mosso.deimos.book.dao.WordDao;
import com.mosso.deimos.book.model.Explain;
import com.mosso.deimos.book.model.Question;
import com.mosso.deimos.book.model.Sentence;
import com.mosso.deimos.book.model.Textbook;
import com.mosso.deimos.book.model.Word;
import com.mosso.deimos.common.exception.BusinessException;

/**
 * @author liubin
 *
 */
@Service
public class TextbookService {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WordService wordService;
	
	@Autowired
	private TextbookDao textbookDao;
	
	@Autowired
	private WordDao wordDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	private String grabWordUrl = "http://www.shanbay.com";
	private String wordDetailUrl = "http://dict.cn/ws.php";

	/**
	 * 从扇贝抓取单词
	 * @param textbook
	 * @param book 教材
	 * @param unitStart 开始单元
	 * @param unitEnd 最后的单元
	 * @param unitEndPageSize 最后一单元的页数
	 */
	public void grabWordsFromShanbay(Textbook textbook, String book, int unitStart, int unitEnd, int unitEndPageSize) {
		String bookUrl = grabWordUrl + "/book/" + book;
		long wordCount = 0L;
		StopWatch watch = null;
		long httpTimeTake = 0L;
		long httpTimeStart = 0L;
		try {
			if(logger.isDebugEnabled()) {
				watch = new StopWatch();
				watch.start("抓取" + textbook.getName() + "单词");
				logger.debug("开始抓取" + textbook.getName() + "单词");
			}
			
			List<Word> words = new ArrayList<Word>();
			for (int i = unitStart; i <= unitEnd; i++) {
				String unitUrl = bookUrl + "/unit/" + i;
				int maxPageSize = i == unitEnd ? unitEndPageSize : 10;
				for (int j = 1; j <= maxPageSize; j++) {
					String pageUrl = unitUrl + "/?page_id=" + j;
					
					if(logger.isDebugEnabled()) {
						httpTimeStart = System.currentTimeMillis();
					}
					
					Document doc = Jsoup.connect(pageUrl).get();
					
					if(logger.isDebugEnabled()) {
						httpTimeTake += System.currentTimeMillis() - httpTimeStart; 
					}
					
					Elements tds = doc.getElementsByClass("list-word");
					for(Iterator<Element> iter = tds.iterator(); iter.hasNext();) {
						Element a = iter.next();
						String word = a.text();
						
						Word wordBean = new Word();
						wordBean.setSpell(word);
						if(word.contains(" ") || word.contains(".")) {
							wordBean.setPhrase(true);
						}
						wordBean.setTextbook(textbook);
//						wordDao.save(wordBean);
						words.add(wordBean);
						wordCount++;
					}
				}
			}
			
			wordDao.insertBatch(words);
			textbook.setWordCount(wordCount);
			textbookDao.save(textbook);
			
			if(logger.isDebugEnabled() && watch != null) {
				watch.stop();
				logger.debug(watch.prettyPrint());
				logger.debug("http请求共耗时" + httpTimeTake + "ms");
				logger.debug("共抓取" + wordCount + "个单词");
			}
		} catch (IOException e) {
			throw new BusinessException("抓取单词时发生异常", e);
		}

	}

	/**
	 * 调用Web API得到单词详细信息
	 */
	@SuppressWarnings("unchecked")
	public int getWordDetailsFromDict(Textbook textbook) {
		
		List<Word> words = wordDao.findByTextbookId(textbook.getId());
		int successCount = 0;
		StopWatch watch = null;
		long httpTimeTake = 0L;
		long httpTimeStart = 0L;
		if(logger.isDebugEnabled()) {
			watch = new StopWatch();
			watch.start();
			logger.debug("开始调用WebApi得到{}单词详细信息", textbook.getName());
		}
		for(Word word : words) {
			try {
				if(logger.isDebugEnabled()) {
					httpTimeStart = System.currentTimeMillis();
					if(successCount != 0 && successCount % 1000 == 0) {
						logger.debug("已成功更新{}个单词", successCount);
					}
				}
				String xml = Jsoup.connect(wordDetailUrl).timeout(5000).data("utf8", "true")
						.data("q", word.getSpell()).execute().body();
				if(logger.isDebugEnabled()) {
					httpTimeTake += System.currentTimeMillis() - httpTimeStart; 
				}
				
				
				org.dom4j.Document document = DocumentHelper.parseText(xml);
				org.dom4j.Element root = document.getRootElement();
				String spell = root.elementText("key");
				if(spell == null) {
					logger.warn("查询不到该单词的信息,单词:{}", word.getSpell());
					continue;
				}
				if(!word.getSpell().equals(spell)) {
					logger.error("请求单词详细信息接口后返回的单词数据有错误,请求的单词[{}], 返回的单词[{}]", word.getSpell(), spell);
					continue;
				}
				
				if(!word.getPhrase()) {
					//例子:http://mp3.dict.cn/mp3.php?q=efVw7
					String audio = root.elementText("audio");
					if(!StringUtils.isBlank(audio)) {
						word.setAudio(audio);
						int paramIndex = audio.indexOf("q=");
						if(paramIndex != -1) {
							word.setAudioParam(audio.substring(paramIndex + 2));
						}
					}
					
					//例子:'reb&#601;l,ri'bel
					String pron = root.elementText("pron");
					if(!StringUtils.isBlank(pron)) {
						String[] prons = pron.split(",");
						if(prons != null && prons.length > 0) {
							word.setProns(Arrays.asList(prons));
						}
					}
				}
				
				//例子:n.单词,消息,话语,诺言\nv.用词语表达
				String def = root.elementText("def");
				if(!StringUtils.isBlank(def)) {
					String[] defs = def.split("\\n");
					List<Explain> explainList = new ArrayList<Explain>(defs.length);
					for (int i = 0; i < defs.length; i++) {
						def = defs[i];
						Explain explain = new Explain();
						int charactLastIndex = def.indexOf(".");
						if(charactLastIndex != -1) {
							explain.setCharact(def.substring(0, charactLastIndex));
							explain.setExplain(def.substring(charactLastIndex+1));
						} else {
							explain.setExplain(def);
						}
						explainList.add(explain);
					}
					word.setExplains(explainList);
				}
				
				
				List<org.dom4j.Element> sents = root.elements("sent");
				if(sents != null && !sents.isEmpty()) {
					List<Sentence> sentenceList = new ArrayList<Sentence>(sents.size());
					for(org.dom4j.Element sent : sents) {
						String orig = sent.elementText("orig");
						String trans = sent.elementText("trans");
						Sentence sentence = new Sentence();
						sentence.setOrig(orig);
						sentence.setTrans(trans);
						sentenceList.add(sentence);
					}
					word.setSentences(sentenceList);
				}
				word.setValid(true);
				wordDao.save(word);
				
				successCount++;
			} catch (IOException e) {
				logger.error("请求单词接口时发生错误, 单词:" + word.getSpell(), e);
			} catch (DocumentException e) {
				logger.error("解析xml文件时发生错误, 单词:" + word.getSpell(), e);
			}
			
		}
		
		if(logger.isDebugEnabled() && watch != null) {
			watch.stop();
			logger.debug(watch.prettyPrint());
			logger.debug("http请求共耗时" + httpTimeTake + "ms");
			logger.debug("{}现存{}个单词,成功得到{}个单词详细信息", new Object[]{textbook.getName(),words.size(), successCount});
		}
		
		return successCount;
	}

	/**
	 * 获得单词的相似词以及构建问题列表
	 * @param textbook
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getWordSuggAndBuildQuestion(Textbook textbook) {
		List<Word> words = wordDao.findByTextbookId(textbook.getId());
		List<Question> questions = new ArrayList<Question>();
		int successCount = 0;
		StopWatch watch = null;
		long httpTimeTake = 0L;
		long httpTimeStart = 0L;
		if(logger.isDebugEnabled()) {
			watch = new StopWatch();
			watch.start();
			logger.debug("开始调用WebApi构建{}单词问题列表", textbook.getName());
		}
		for(Word word : words) {
			try {
				if(!word.getValid()) continue;
				if(logger.isDebugEnabled()) {
					httpTimeStart = System.currentTimeMillis();
					if(successCount != 0 && successCount % 1000 == 0) {
						logger.debug("已成功构建{}个单词", successCount);
					}
				}
				if(!word.getPhrase()) {
					//单词
					String xml = Jsoup.connect(wordDetailUrl).timeout(5000)
							.data("q", "[" + word.getSpell() + "]").execute().body();
					if(logger.isDebugEnabled()) {
						httpTimeTake += System.currentTimeMillis() - httpTimeStart; 
					}
					org.dom4j.Document document = DocumentHelper.parseText(xml);
					org.dom4j.Element root = document.getRootElement();
					
					List<org.dom4j.Element> suggs = root.elements("sugg");
					if(suggs != null && !suggs.isEmpty()) {
						List<String> similarWords = new ArrayList<String>(suggs.size());
						for(org.dom4j.Element sugg : suggs) {
							similarWords.add(sugg.getText());
						}
						word.setSimilarWords(similarWords);
					}
					questions.addAll(wordService.buildQuestions(word, word.getSimilarWords()));
					
					wordDao.save(word);
				} else {
					//短语
					questions.addAll(wordService.buildQuestions(word, null));
				}
				
				successCount++;
			} catch (IOException e) {
				logger.error("请求单词接口时发生错误, 单词:" + word.getSpell(), e);
			} catch (DocumentException e) {
				logger.error("解析xml文件时发生错误, 单词:" + word.getSpell(), e);
			}
			
		}
		if(!questions.isEmpty()) {
			questionDao.insertBatch(questions);
		}
		
		if(logger.isDebugEnabled() && watch != null) {
			watch.stop();
			logger.debug(watch.prettyPrint());
			logger.debug("http请求共耗时" + httpTimeTake + "ms");
			logger.debug("{}有{}个单词需要构建问题列表,有{}个单词构建成功,保存问题{}条", new Object[]{textbook.getName(),words.size(), successCount, questions.size()});
		}
		
		return successCount;
	}
	
}

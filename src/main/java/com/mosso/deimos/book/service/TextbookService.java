package com.mosso.deimos.book.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.mosso.deimos.book.dao.TextbookDao;
import com.mosso.deimos.book.dao.WordDao;
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
	private TextbookDao textbookDao;
	
	@Autowired
	private WordDao wordDao;
	
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
	public int getWordDetailsFromDict() {
		
		List<Word> words = wordDao.findAll();
		int sucessCount = 0;
		for(Word word : words) {
			try {
				String xml = Jsoup.connect(wordDetailUrl).data("utf8", "true")
						.data("q", word.getSpell()).execute().body();
				org.dom4j.Document document = DocumentHelper.parseText(xml);
				org.dom4j.Element root = document.getRootElement();
				String spell = root.elementText("key");
				if(!word.getSpell().equals(spell)) {
					logger.error("请求单词详细信息接口后返回的单词数据有错误,请求的单词[{0}], 返回的单词[{1}]", word.getSpell(), spell);
					continue;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return sucessCount;
	}
	
}

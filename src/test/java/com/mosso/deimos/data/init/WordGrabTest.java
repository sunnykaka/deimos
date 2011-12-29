package com.mosso.deimos.data.init;

import java.util.Date;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.jsoup.Jsoup;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mosso.deimos.book.dao.CategoryDao;
import com.mosso.deimos.book.dao.TextbookDao;
import com.mosso.deimos.book.model.Category;
import com.mosso.deimos.book.model.Textbook;
import com.mosso.deimos.book.service.TextbookService;
import com.mosso.deimos.common.test.BaseTest;


/**
 * 抓取单词
 * @author liubin
 *
 */
public class WordGrabTest extends BaseTest {

	@Autowired
	TextbookService textbookService;
	
	@Autowired
	TextbookDao textbookDao;
	
	@Autowired
	CategoryDao categoryDao;
//	
//	@Autowired
//	BaseDao baseDao;
	
	@Test
	@Ignore
	/**
	 * 抓取单词
	 */
	public void grabWordsFromShanbay() {
		//抓取大学四六级教材
		String cet4Name = "大学英语四级";
		String cet6Name = "大学英语六级";
		
		Category category = categoryDao.getByName("大学英语");
		if(category == null) {
			throw new RuntimeException("大学英语教材类别不存在,请确认Category数据已经初始化");
		}
		
		Textbook cet4 = textbookDao.getByName(cet4Name);
		Textbook cet6 = textbookDao.getByName(cet6Name);
		if(cet4 != null || cet6 != null) {
			throw new RuntimeException(cet4Name + "或者" + cet6Name + "的教材已经存在,抓取单词失败");
		}
		
		cet4 = new Textbook();
		cet4.setCreateDate(new Date());
		cet4.setName(cet4Name);
		cet4.setCategory(category);
		
		cet6 = new Textbook();
		cet6.setCreateDate(new Date());
		cet6.setName(cet6Name);
		cet6.setCategory(category);
		
		textbookDao.save(cet4);
		textbookDao.save(cet6);
		
		textbookService.grabWordsFromShanbay(cet4, "1", 1, 63, 6);
		textbookService.grabWordsFromShanbay(cet6, "2", 64, 84, 8);
	}
	
	/**
	 * 调用Web API得到单词详细信息
	 */
	@Test
	public void getWordDetailsFromDict() throws Exception {
		System.out.println(textbookService.getWordDetailsFromDict());
//		String word = "word";
//		String wordDetailUrl = "http://dict.cn/ws.php";
//		String xml = Jsoup.connect(wordDetailUrl).data("utf8", "true")
//				.data("q", word).execute().body();
//		org.dom4j.Document document = DocumentHelper.parseText(xml);
//		org.dom4j.Element root = document.getRootElement();
//		String spell = root.elementText("key");
//		String audio = root.elementText("audio");
//		String pron = root.elementText("pron");
//		String def = root.elementText("def");
//		List<org.dom4j.Element> sents = root.elements("sent");
//		if(sents != null && !sents.isEmpty()) {
//			for(org.dom4j.Element sent : sents) {
//				String orig = sent.elementText("orig");
//				String trans = sent.elementText("trans");
//			}
//		}
//		System.out.println("finish");
	}
}

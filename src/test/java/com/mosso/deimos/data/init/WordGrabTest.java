package com.mosso.deimos.data.init;

import java.util.Date;

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
	
	String cet4Name = "大学英语四级";
	String cet6Name = "大学英语六级";
	
	@Test
	@Ignore
	/**
	 * 抓取单词
	 */
	public void grabWordsFromShanbay() {
		//抓取大学四六级教材
		
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
	@Ignore
	public void getWordDetailsFromDict() throws Exception {
		Textbook cet4 = textbookDao.getByName(cet4Name);
		Textbook cet6 = textbookDao.getByName(cet6Name);
		System.out.println(textbookService.getWordDetailsFromDict(cet4));
		System.out.println(textbookService.getWordDetailsFromDict(cet6));
	}
	
	/**
	 * 调用Web API得到单词详细信息
	 */
	@Test
//	@Ignore
	public void getWordSuggAndBuildQuestion() throws Exception {
		Textbook cet4 = textbookDao.getByName(cet4Name);
		Textbook cet6 = textbookDao.getByName(cet6Name);
		System.out.println(textbookService.getWordSuggAndBuildQuestion(cet4));
		System.out.println(textbookService.getWordSuggAndBuildQuestion(cet6));
	}
}

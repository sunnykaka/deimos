package com.mosso.deimos.book.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mosso.deimos.book.model.Textbook;
import com.mosso.deimos.common.test.BaseTest;


public class TextbookDaoTest extends BaseTest {

	@Autowired
	TextbookDao textbookDao;
//	
//	@Autowired
//	BaseDao baseDao;
	
	@Test
	public void testSave() {
		
		String name = "教材3";
		long wordCount = 1001L;
		Date date = new Date();
		
		Textbook textbook = new Textbook();
		
		textbook.setName(name);
		textbook.setWordCount(wordCount);
		textbook.setCreateDate(date);
		
		textbookDao.save(textbook);
		
		Textbook textbookAfterRead = (Textbook)textbookDao.findOne(textbook.getId());
		Textbook textbook2 = textbookDao.getByName(name);
		
//		assertEquals(textbook, textbookAfterRead);
//		assertThat(books.size(), is(1));
//		Textbook textbook2 = books.get(0);
		assertThat(textbook2, notNullValue());
		assertThat(textbook2.getName(), is(textbookAfterRead.getName()));
		assertThat(textbook2.getId(), is(textbookAfterRead.getId()));
		
		textbookDao.delete(textbook);
		
		
		textbookDao.doSomething("你们");
		
		System.out.println("ok");
	}
	
//	@Test
//	public void testBaseDaoSave() {
//		
//		String name = "教材3";
//		long wordCount = 1002L;
//		Date date = new Date();
//		
//		Textbook textbook = new Textbook();
//		
//		textbook.setName(name);
//		textbook.setWordCount(wordCount);
//		textbook.setCreateDate(date);
//		
//		baseDao.save(textbook);
//		
//		Textbook textbookAfterRead = (Textbook)baseDao.findOne(textbook.getId());
//		
//		assertEquals(textbook, textbookAfterRead);
//		
//		baseDao.delete(textbook);
//		
//		
//		System.out.println("ok");
//	}
	
	private void assertEquals(Textbook before, Textbook after) {
		assertThat(after, notNullValue());
		assertThat(after.getId(), is(before.getId()));
		assertThat(after.getName(), is(before.getName()));
		assertThat(after.getWordCount(), is(before.getWordCount()));
		assertThat(after.getCreateDate(), is(before.getCreateDate()));
	}

}

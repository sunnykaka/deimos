package com.mosso.deimos.book.dao.impl;

import com.mosso.deimos.book.dao.TextbookCustomDao;

public class TextbookDaoImpl implements TextbookCustomDao {

	@Override
	public void doSomething(String name) {
		System.out.println("hello ," + name);
	}

}

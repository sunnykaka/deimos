package com.mosso.deimos.book.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mosso.deimos.book.dao.WordCustomDao;
import com.mosso.deimos.book.model.Word;

public class WordDaoImpl implements WordCustomDao {
	
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void insertBatch(List<Word> words) {
		mongoOperations.insert(words, Word.class);
	}

}

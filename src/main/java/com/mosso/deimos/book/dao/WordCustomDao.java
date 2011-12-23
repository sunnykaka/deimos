package com.mosso.deimos.book.dao;

import java.util.List;

import com.mosso.deimos.book.model.Word;

public interface WordCustomDao {

	void insertBatch(List<Word> words);
	
}

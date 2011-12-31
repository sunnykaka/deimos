package com.mosso.deimos.book.dao;

import java.util.List;

import com.mosso.deimos.book.model.Question;

public interface QuestionCustomDao {

	/**
	 * 批量插入问题
	 * @param words
	 */
	void insertBatch(List<Question> questions);
	
	
}

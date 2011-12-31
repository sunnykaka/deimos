package com.mosso.deimos.book.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mosso.deimos.book.model.Question;

/**
 * @author liubin
 *
 */
public interface QuestionDao extends MongoRepository<Question, String>, QuestionCustomDao{
	
}

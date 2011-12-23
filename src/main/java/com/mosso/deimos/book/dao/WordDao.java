package com.mosso.deimos.book.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mosso.deimos.book.model.Word;

/**
 * @author liubin
 *
 */
public interface WordDao extends MongoRepository<Word, String>, WordCustomDao{
	
}

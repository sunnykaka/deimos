package com.mosso.deimos.book.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mosso.deimos.book.model.Textbook;

/**
 * @author liubin
 *
 */
public interface TextbookDao extends MongoRepository<Textbook, String>, TextbookCustomDao {
	
	@Query(value = "{ 'name' : ?0 }")
	Textbook getByName(String name);
	
}

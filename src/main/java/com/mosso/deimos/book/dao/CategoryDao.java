package com.mosso.deimos.book.dao;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mosso.deimos.book.model.Category;
import com.mosso.deimos.book.model.Textbook;

/**
 * @author liubin
 *
 */
public interface CategoryDao extends MongoRepository<Textbook, BigInteger>{
	
	@Query(value = "{ 'name' : ?0 }")
	Category getByName(String name);
	
}

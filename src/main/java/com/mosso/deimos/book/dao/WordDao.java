package com.mosso.deimos.book.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mosso.deimos.book.model.Word;

/**
 * @author liubin
 *
 */
public interface WordDao extends MongoRepository<Word, String>, WordCustomDao{
	
	@Query(value = "{ 'textbook.$id' : ?0 }")
	List<Word> findByTextbookId(String textbookId);
	
	@Query(value = "{ 'spell' : ?0 }")
	Word getBySpell(String word);
	
}

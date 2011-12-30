package com.mosso.deimos.book.dao.impl;

import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mosso.deimos.book.dao.WordCustomDao;
import com.mosso.deimos.book.model.Word;

public class WordDaoImpl implements WordCustomDao {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void insertBatch(List<Word> words) {
		mongoOperations.insert(words, Word.class);
	}

	@Override
	public Word findRandomOne() {
		Query query = new Query();
		query.addCriteria(Criteria.where("valid").is(Boolean.TRUE).and("phrase").is(Boolean.FALSE));
		long size = mongoOperations.count(query, Word.class);
		logger.debug(" words size in findRandomOne method of WordDaoImpl:{} ", size);
		if(size == 0L) return null;
		query.skip(RandomUtils.nextInt((int)size)).limit(1);
		List<Word> words = mongoOperations.find(query, Word.class);
		return words.isEmpty() ? null : words.get(0);
	}

}

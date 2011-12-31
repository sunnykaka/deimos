package com.mosso.deimos.book.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosso.deimos.book.dao.WordDao;
import com.mosso.deimos.book.model.EnumQuestionType;
import com.mosso.deimos.book.model.Question;
import com.mosso.deimos.book.model.Word;

/**
 * @author liubin
 *
 */
@Service
public class WordService {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WordDao wordDao;

	
	private final int optionMaxSize = 3;
	

	/**
	 * 为单词构建问题列表,但是不保存问题列表直接返回
	 * @param word
	 * @param similarWords
	 * @return
	 */
	public List<Question> buildQuestions(Word word, List<String> similarWords) {
		List<Question> questions = new ArrayList<Question>();
		
		int optionSize = 0;
		List<Word> optionWords = new ArrayList<Word>();
		//丢掉前4个类似的单词,因为有可能是该词的不同时态
		if(similarWords != null && similarWords.size() > 4) {
			for(int i=4; i<similarWords.size(); i++) {
				String similarWord = similarWords.get(i);
				Word similarWordBean = wordDao.getBySpell(similarWord);
				if(similarWordBean == null) continue;
				optionWords.add(similarWordBean);
				if(++optionSize == optionMaxSize) {
					//如果已经找到了足够的选项,退出循环
					break;
				}
			}
		}
		
		if(!word.getPhrase()) {
			//单词
			
			Question q1 = new Question();
			q1.setWord(word);
			q1.setType(EnumQuestionType.TYPE_1.value);
			q1.setOrdinal(0);
			setOptionsAndRightIndex(q1, EnumQuestionType.TYPE_1, new ArrayList<Word>(optionWords));
			questions.add(q1);
			
			Question q2 = new Question();
			q2.setWord(word);
			q2.setType(EnumQuestionType.TYPE_2.value);
			q2.setOrdinal(1);
			setOptionsAndRightIndex(q2, EnumQuestionType.TYPE_2, new ArrayList<Word>(optionWords));
			questions.add(q2);
			
			Question q3 = new Question();
			q3.setWord(word);
			q3.setType(EnumQuestionType.TYPE_3.value);
			q3.setOrdinal(2);
			setOptionsAndRightIndex(q3, EnumQuestionType.TYPE_3, new ArrayList<Word>(optionWords));
			questions.add(q3);
			
			Question q4 = new Question();
			q4.setWord(word);
			q4.setType(EnumQuestionType.TYPE_4.value);
			q4.setOrdinal(3);
			questions.add(q4);
		} else {
			//短语
			
			Question q1 = new Question();
			q1.setWord(word);
			q1.setType(EnumQuestionType.TYPE_1.value);
			q1.setOrdinal(0);
			setOptionsAndRightIndex(q1, EnumQuestionType.TYPE_1, new ArrayList<Word>(optionWords));
			questions.add(q1);
			
			Question q2 = new Question();
			q2.setWord(word);
			q2.setType(EnumQuestionType.TYPE_2.value);
			q2.setOrdinal(1);
			setOptionsAndRightIndex(q2, EnumQuestionType.TYPE_2, new ArrayList<Word>(optionWords));
			questions.add(q2);
			
		}
		return questions;
	}


	/**
	 * 设置问题的选项以及正确选项所在位置
	 * @param q
	 * @param type
	 * @param similarWords
	 */
	private void setOptionsAndRightIndex(Question q, EnumQuestionType type,
			List<Word> optionWords) {
		
		List<String> options = new ArrayList<String>();
		int optionSize = optionWords.size();

		while(optionSize < optionMaxSize) {
			Word word = wordDao.findRandomOne();
			if(word == null) continue;
			optionWords.add(word);
			optionSize++;
		}
		int rightIndex = RandomUtils.nextInt(4) + 1;
		optionWords.add(rightIndex - 1, q.getWord());
		
		for(Word word : optionWords) {
			switch (type) {
				case TYPE_1: {
					options.add(word.getPrettyExplain());
					break;
				} case TYPE_2: {
					options.add(word.getSpell());
					break;
				} case TYPE_3: {
					options.add(word.getPrettyExplain());
					break;
				}
			}
		}
		
		q.setRightIndex(rightIndex);
		q.setOptions(options);
	}

	
}

package com.mosso.deimos.book.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 单词
 * @author liubin
 *
 */
@Document
public class Word {
	
	@Id
	private String id;
	
	//单词
	@Indexed
	private String spell;
	
	//发音
	private List<String> prons;
	
	//单词解释
	private List<Explain> explains;
	
	//发音url
	private String audio;
	
	//发音参数
	private String audioParam;
	
	//例句
	private List<Sentence> sentences;
	
	//是否是短语
	private Boolean phrase = false;
	
	//单词是否有效
	private Boolean valid = false;
	
	//相近的词
	private List<String> similarWords;
	
	//教材
	@DBRef
	private Textbook textbook;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public List<Explain> getExplains() {
		return explains;
	}

	public void setExplains(List<Explain> explains) {
		this.explains = explains;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getAudioParam() {
		return audioParam;
	}

	public void setAudioParam(String audioParam) {
		this.audioParam = audioParam;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public Textbook getTextbook() {
		return textbook;
	}

	public void setTextbook(Textbook textbook) {
		this.textbook = textbook;
	}

	public List<String> getProns() {
		return prons;
	}

	public void setProns(List<String> prons) {
		this.prons = prons;
	}

	public Boolean getPhrase() {
		return phrase;
	}

	public void setPhrase(Boolean phrase) {
		this.phrase = phrase;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public List<String> getSimilarWords() {
		return similarWords;
	}

	public void setSimilarWords(List<String> similarWords) {
		this.similarWords = similarWords;
	}
	
	/**
	 * 得到单词解释
	 * @return
	 */
	public String getPrettyExplain() {
		if(explains == null || explains.isEmpty()) return null;
		StringBuilder sb = new StringBuilder();
		for(Explain e : explains) {
			sb.append(e.getCharact()).append(". ").append(e.getExplain()).append(";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}

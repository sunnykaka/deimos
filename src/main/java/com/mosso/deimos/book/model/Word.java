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
	private List<String> phonograms;
	
	//单词解释
	private List<Explain> explains;
	
	//发音url
	private String audio;
	
	//发音参数
	private String audioParam;
	
	//例句
	private List<String> sentences;
	
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

	public List<String> getPhonograms() {
		return phonograms;
	}

	public void setPhonograms(List<String> phonograms) {
		this.phonograms = phonograms;
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

	public List<String> getSentences() {
		return sentences;
	}

	public void setSentences(List<String> sentences) {
		this.sentences = sentences;
	}

	public Textbook getTextbook() {
		return textbook;
	}

	public void setTextbook(Textbook textbook) {
		this.textbook = textbook;
	}
}

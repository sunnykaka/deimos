package com.mosso.deimos.book.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 例句
 * @author liubin
 */
@Document
public class Sentence {

	//英语原句
	private String orig;
	
	//中文翻译
	private String trans;

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

}
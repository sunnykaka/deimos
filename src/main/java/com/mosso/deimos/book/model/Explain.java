package com.mosso.deimos.book.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 单词解释
 * @author liubin
 */
@Document
public class Explain {

	//单词解释
	private String explain;
	
	//单词词性
	private String charact;

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getCharact() {
		return charact;
	}

	public void setCharact(String charact) {
		this.charact = charact;
	}
}
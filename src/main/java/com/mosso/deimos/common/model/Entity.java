package com.mosso.deimos.common.model;

import java.io.Serializable;

/**
 * 实体类接口
 * @author liubin
 *
 * @param <ID>
 */
public interface Entity<ID extends Serializable> {

	/**
	 * @return
	 */
	ID getId();

	/**
	 * @param id
	 */
	void setId(ID id);
	
}

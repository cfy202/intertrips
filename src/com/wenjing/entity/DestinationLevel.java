/**
 * 
 */
package com.wenjing.entity;

import java.io.Serializable;

/**
 * 类说明
 * @author xiejin
 * @date 2015-9-25 
 * @date 2015-9-25 下午3:29:36
 */
public class DestinationLevel implements Serializable{


	private static final long serialVersionUID = -2920880765426813428L;
	
	private String id;
	
	private String description;
	
	private int level;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	


}

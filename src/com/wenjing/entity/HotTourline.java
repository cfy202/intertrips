/**
 * 
 */
package com.wenjing.entity;

import java.io.Serializable;

/**
 * 类说明
 * @author xiejin
 * @date 2015-8-21 
 * @date 2015-8-21 上午10:54:50
 */
public class HotTourline implements Serializable{

	private static final long serialVersionUID = -1610293328361741243L;

	
	private String id;
	
	private String costNumber;
	
	private String tourlineId;
	
	private Integer addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public String getTourlineId() {
		return tourlineId;
	}

	public void setTourlineId(String tourlineId) {
		this.tourlineId = tourlineId;
	}

	public Integer getAddTime() {
		return addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

	
}

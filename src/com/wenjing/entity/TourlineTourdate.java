
package com.wenjing.entity;

import java.io.Serializable;

/**
 * 类说明		线路所有出发日期实体类
 * @author xiejin
 * @date 2015-9-11 
 * @date 2015-9-11 下午1:49:55
 */
public class TourlineTourdate implements Serializable{

	private static final long serialVersionUID = -9104933813628224713L;
	
	private String id;
	
	private String tourlineId;
	
	private String tourCode;
	
	private String departureDates;
	
	private String costNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTourlineId() {
		return tourlineId;
	}

	public void setTourlineId(String tourlineId) {
		this.tourlineId = tourlineId;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public String getDepartureDates() {
		return departureDates;
	}

	public void setDepartureDates(String departureDates) {
		this.departureDates = departureDates;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}
	

}

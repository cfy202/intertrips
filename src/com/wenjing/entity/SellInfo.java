package com.wenjing.entity;

import java.io.Serializable;

/**
 * @ClassName SellInfo
 * @PackageName com.wenjing.entity
 * @Description 成团人数
 * @author Bowden
 * @Date 2016-2-25 下午4:43:36
 * @Version V1.0
 */
public class SellInfo implements Serializable {
	/**
	 * @Fields serialVersionUID :
	 **/
	private static final long serialVersionUID = 3849251555246849982L;

	private String id;

	private String date; // 会员id

	private String tourlineId; // 线路id

	private Integer totalNum; // 总成团人数
	
	private Integer sellNum; // 售出人数
	
	private String costNumber; // 销售中心
	
	private String tourdateId; // 日期id

	public SellInfo() {
		super();
	}

	public SellInfo(String date, String tourlineId, String costNumber,
			String tourdateId) {
		super();
		this.date = date;
		this.tourlineId = tourlineId;
		this.costNumber = costNumber;
		this.tourdateId = tourdateId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTourlineId() {
		return tourlineId;
	}

	public void setTourlineId(String tourlineId) {
		this.tourlineId = tourlineId;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getSellNum() {
		return sellNum;
	}

	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public String getTourdateId() {
		return tourdateId;
	}

	public void setTourdateId(String tourdateId) {
		this.tourdateId = tourdateId;
	}
}

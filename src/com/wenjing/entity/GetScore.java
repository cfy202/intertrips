package com.wenjing.entity;

import java.io.Serializable;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-7-3 上午11:53:35
 * 类说明 :积分获取表
 */
public class GetScore implements Serializable {
	
	private static final long serialVersionUID = 2102829865906477429L;
	
	private String id;
	
	private Integer gettime; //积分获取时间 
	
	private String projectName; //获取积分项目名称
	
	private String namepy;
	
	private String orderNo; //订单号
	
	private Integer score; //积分
	
	private Integer status; //积分状态(0：未启用；1：已启用；2：已取消)
	
	private String memberid; //会员id
	
	private String getTimeString; //获取时间的字符串

	public GetScore(String id, Integer gettime, String projectName, String namepy,
			String orderNo, Integer score, Integer status, String memberid) {
		super();
		this.id = id;
		this.gettime = gettime;
		this.projectName = projectName;
		this.namepy = namepy;
		this.orderNo = orderNo;
		this.score = score;
		this.status = status;
		this.memberid = memberid;
	}

	public GetScore() {
		super();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getGettime() {
		return gettime;
	}

	public void setGettime(Integer gettime) {
		this.gettime = gettime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getNamepy() {
		return namepy;
	}

	public void setNamepy(String namepy) {
		this.namepy = namepy;
	}
	
	public String getGetTimeString() {
		return getTimeString;
	}

	public void setGetTimeString(String getTimeString) {
		this.getTimeString = getTimeString;
	}
}


package com.wenjing.entity;

import java.io.Serializable;

/**
 * 类说明		订阅邮箱实体类
 * @author xiejin
 * @date 2015-6-29 
 * @date 2015-6-29 下午5:36:21
 */
public class Email implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String email;
	
	private Integer addTime;
	
	private Integer status;
	
	private Integer cancelTime;
	
	private String costNumber;
	
	private String costName;
	
	private String addTimeString;
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddTimeString() {
		return addTimeString;
	}

	public void setAddTimeString(String addTimeString) {
		this.addTimeString = addTimeString;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAddTime() {
		return addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Integer cancelTime) {
		this.cancelTime = cancelTime;
	}
	
}

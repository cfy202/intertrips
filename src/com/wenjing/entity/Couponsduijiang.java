package com.wenjing.entity;

import java.io.Serializable;

public class Couponsduijiang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -348949765579065203L;

	private String id;

	private String code;

	private String expirationdate;

	private String ip;

	private Integer usestatus;

	private Integer emailstatus;

	private String username;

	private String email;

	private Integer releasestatus;

	private String couponsname;

	private String winningtime;

	private String title;

	private String time;

	private String merberid;

	private String couponseid;
	
	private String levelId;
	
	

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	/**
	 * @return the couponseid
	 */
	public String getCouponseid() {
		return couponseid;
	}

	/**
	 * @param couponseid the couponseid to set
	 */
	public void setCouponseid(String couponseid) {
		this.couponseid = couponseid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	/**
	 * @return the expirationdate
	 */
	public String getExpirationdate() {
		return expirationdate;
	}

	/**
	 * @param expirationdate the expirationdate to set
	 */
	public void setExpirationdate(String expirationdate) {
		this.expirationdate = expirationdate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getUsestatus() {
		return usestatus;
	}

	public void setUsestatus(Integer usestatus) {
		this.usestatus = usestatus;
	}

	public Integer getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(Integer emailstatus) {
		this.emailstatus = emailstatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getReleasestatus() {
		return releasestatus;
	}

	public void setReleasestatus(Integer releasestatus) {
		this.releasestatus = releasestatus;
	}

	public String getCouponsname() {
		return couponsname;
	}

	public void setCouponsname(String couponsname) {
		this.couponsname = couponsname;
	}

	public String getWinningtime() {
		return winningtime;
	}

	public void setWinningtime(String winningtime) {
		this.winningtime = winningtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMerberid() {
		return merberid;
	}

	public void setMerberid(String merberid) {
		this.merberid = merberid;
	}
}
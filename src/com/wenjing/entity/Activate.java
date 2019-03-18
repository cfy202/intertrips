package com.wenjing.entity;

import java.io.Serializable;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-25 下午2:56:00
 *  类说明 : 激活表
 */
public class Activate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7548203701283666930L;

	private String id;

	private String memberid; // 会员id

	private String authcode; // 激活码

	private Integer failuretime; // 失效时间

	public Activate() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public Integer getFailuretime() {
		return failuretime;
	}

	public void setFailuretime(Integer failuretime) {
		this.failuretime = failuretime;
	}
}

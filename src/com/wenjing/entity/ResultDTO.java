package com.wenjing.entity;

import java.io.Serializable;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-28 下午7:31:17 
 * 类说明 ：验证成功与否及返回信息
 */
public class ResultDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

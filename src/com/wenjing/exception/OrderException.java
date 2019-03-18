package com.wenjing.exception;

/**
 * 产品价格出错异常
 * 
 * @author Jared
 *
 * Jun 29, 2015
 */
public class OrderException extends RuntimeException{
	
	private static final long serialVersionUID = 5339803917669389879L;
	
	private String errorMessage;
	
	public OrderException(String msg){
		this.errorMessage = msg;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

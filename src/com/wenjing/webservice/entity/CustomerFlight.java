package com.wenjing.webservice.entity;

/**
 * @copyright   Copyright: 2014 
 * @author Jared
 * @create-time Sep 3, 2014 3:21:40 PM
 * @revision  3.0
 */

public class CustomerFlight {
	
	private String flightNumber; //航班号
	
	private String flightCode; //航空公司代码
	
	private String arriveTime; //入境航班:抵达时间; 出境航班:起飞时间

	private String arriveDateStr;
	
	private Integer ifPickUp; //是否接机  1:接机; 2:不接机
	
	private Integer ifSendUp; //是否送机  1:送机; 2:不送机
	
	private Integer outOrEnter; //类型： 1:入境; 2:出境

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getArriveDateStr() {
		return arriveDateStr;
	}

	public void setArriveDateStr(String arriveDateStr) {
		this.arriveDateStr = arriveDateStr;
	}

	public Integer getIfPickUp() {
		return ifPickUp;
	}

	public void setIfPickUp(Integer ifPickUp) {
		this.ifPickUp = ifPickUp;
	}

	public Integer getIfSendUp() {
		return ifSendUp;
	}

	public void setIfSendUp(Integer ifSendUp) {
		this.ifSendUp = ifSendUp;
	}

	public Integer getOutOrEnter() {
		return outOrEnter;
	}

	public void setOutOrEnter(Integer outOrEnter) {
		this.outOrEnter = outOrEnter;
	}
}

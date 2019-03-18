package com.wenjing.webservice.entity;

/**
 * @copyright   Copyright: 2014 
 * @author Jared
 * @create-time Sep 3, 2014 2:30:10 PM
 * @revision  3.0
 */
public class TourInfoOfOrder{
	
	private String arriveTime; 
	
	private String scheduleLineCode; //线路编号
	
	private String lineName; //新增 线路名称
	
	private String specialRequirements; //客人特殊要求
	
	private String tourInfo; //团队注意事项及备注
	
	private String personalRoute; //自主线路行程
     
	private String orderId; //订单ID
	
	private String groupLineId;

	public String getGroupLineId() {
		return groupLineId;
	}

	public void setGroupLineId(String groupLineId) {
		this.groupLineId = groupLineId;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getScheduleLineCode() {
		return scheduleLineCode;
	}

	public void setScheduleLineCode(String scheduleLineCode) {
		this.scheduleLineCode = scheduleLineCode;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getSpecialRequirements() {
		return specialRequirements;
	}

	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}

	public String getTourInfo() {
		return tourInfo;
	}

	public void setTourInfo(String tourInfo) {
		this.tourInfo = tourInfo;
	}

	public String getPersonalRoute() {
		return personalRoute;
	}

	public void setPersonalRoute(String personalRoute) {
		this.personalRoute = personalRoute;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}

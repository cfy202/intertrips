package com.wenjing.webservice.entity;

import java.util.List;

/**
 * @copyright   Copyright: 2014 
 * @author Jared
 * @create-time Aug 25, 2014 3:36:35 PM
 * @revision  3.0
 */


public class GroupLine {
	
	private String id;

	private String tourCode;  //线路编码
    
	private String tourName;  //线路名称
    
	private String brand;  //品牌：中国美,chinatour,文景假期,Nexus Holiday,inbound
	
	private String tourTypeId;  //团队类别
	
	private String departureDate;	//出发日期 (同时在统计中用来存放年份，统计借用字段  格式：YYYY)(借用字段，在同行系统头部使用接收字段)
	
	private Integer isAvailable;   //是否可用(0:可用  1：不可用)
	
	private String specificItems;  //特殊条款
	
	
	private String tripDesc;		//线路描述
	
	private String destination;		//目的地			
	
	private String placeStart;		//出发地			
	
	private String attractions;		//景点	
	
	private String destinationlist;	//每个产品目的地列表
	
	private String remark;			// 产品天数
	
	private Integer addDate;   //抵达日期
	
	public Integer getAddDate() {
		return addDate;
	}

	public void setAddDate(Integer addDate) {
		this.addDate = addDate;
	}

	private List<GroupRoute> groupRoute;  //行程
	
	public List<GroupRoute> getGroupRoute() {
		return groupRoute;
	}

	public void setGroupRoute(List<GroupRoute> groupRoute) {
		this.groupRoute = groupRoute;
	}

	public String getTripDesc() {
		return tripDesc;
	}

	public void setTripDesc(String tripDesc) {
		this.tripDesc = tripDesc;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPlaceStart() {
		return placeStart;
	}

	public void setPlaceStart(String placeStart) {
		this.placeStart = placeStart;
	}

	public String getAttractions() {
		return attractions;
	}

	public void setAttractions(String attractions) {
		this.attractions = attractions;
	}

	public String getDestinationlist() {
		return destinationlist;
	}

	public void setDestinationlist(String destinationlist) {
		this.destinationlist = destinationlist;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTourTypeId() {
		return tourTypeId;
	}

	public void setTourTypeId(String tourTypeId) {
		this.tourTypeId = tourTypeId;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getSpecificItems() {
		return specificItems;
	}

	public void setSpecificItems(String specificItems) {
		this.specificItems = specificItems;
	}
}

package com.wenjing.entity;

import java.util.Date;

public class CharterService {
	
	/** 主键 */
	private String id;
	
	/** 名称 */
    private String name;

    /** 取车时间 */
    private Date pickUpTime;
    
	/** 取车地址 */
    private String pickUpAddr;
    
    /** 服务须知 */
    private String serviceInformation;
    
    /** 服务备注 */
    private String remark;

    /** 封面图片url */
	private String imageUrl;
	
	/** 封面图片Id */
	private String imageId;
	
	/** 服务所关联的busId */
    private String busIds;
    
    /** 目的地ID */
    private String destinationId;
    
    public Date getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public String getPickUpAddr() {
		return pickUpAddr;
	}

	public void setPickUpAddr(String pickUpAddr) {
		this.pickUpAddr = pickUpAddr;
	}

	public String getServiceInformation() {
		return serviceInformation;
	}

	public void setServiceInformation(String serviceInformation) {
		this.serviceInformation = serviceInformation;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBusIds() {
		return busIds;
	}

	public void setBusIds(String busIds) {
		this.busIds = busIds;
	}

	public String getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}

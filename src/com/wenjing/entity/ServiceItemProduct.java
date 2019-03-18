package com.wenjing.entity;

import java.io.Serializable;

public class ServiceItemProduct implements Serializable {
    
	/** 主键 */
	private String id;
    /** 产品ID */
	private String productId;
    /** 服务项ID */
	private String serviceItemId;
	/**
	 * 服务类型
	 * @return
	 */
	private Integer type ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getServiceItemId() {
		return serviceItemId;
	}

	public void setServiceItemId(String serviceItemId) {
		this.serviceItemId = serviceItemId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
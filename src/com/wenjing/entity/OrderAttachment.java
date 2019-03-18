/**
 * 
 */
package com.wenjing.entity;

import java.util.Date;

/**
 * @copyright   Copyright: 2014 
 * @author Nina
 * @create-time 2018-1-1 下午6:25:32
 * @revision  3.0
 */
public class OrderAttachment {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7548203701283666939L;
	
	private String id;
	
	private String name;
	
	private String url;
	
	private Date createTime;
	
	private String ordersId;
	
	private String remark;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

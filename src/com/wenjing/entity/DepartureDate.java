package com.wenjing.entity;

import java.io.Serializable;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-12 下午2:26:12
 * 类说明 ： 出发地与线路价格关联entity
 */
public class DepartureDate implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2385689618325365706L;

	/**
     * departureprice.id (id)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String id;

    /**
     * departureprice.departureid (出发地id)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String departureid;
    
    /**
     * departureprice.tourpriceid (价格id)
     * @ibatorgenerated 2015-04-22 23:01:22
     */
    private String tourdateid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartureid() {
		return departureid;
	}

	public void setDepartureid(String departureid) {
		this.departureid = departureid;
	}

	public String getTourdateid() {
		return tourdateid;
	}

	public void setTourdateid(String tourdateid) {
		this.tourdateid = tourdateid;
	}
}

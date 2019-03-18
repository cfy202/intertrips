package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-7-7 下午4:25:12
 * 类说明 ：接送机
 */
public class AirportPickUp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3426786755683337457L;

	private String id;
	
	private String title; //接送机标题
	
	private BigDecimal price; //价格
	
	private String tourlineid; //线路id
	
	public AirportPickUp() {
		super();
	}

	public AirportPickUp(String id, String title, BigDecimal price,
			String tourlineid) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.tourlineid = tourlineid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTourlineid() {
		return tourlineid;
	}

	public void setTourlineid(String tourlineid) {
		this.tourlineid = tourlineid;
	}

}

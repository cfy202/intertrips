package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2015-7-8 上午11:57:42 类说明
 */
public class AirTicketPrice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8348381710261650754L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 机票价格
	 */
	private BigDecimal price;
	
	/**
	 * 出发地id
	 */
	private String departureId;
	
	/**
	 * 出发地名称
	 */
	private String departureName;
	
	/**
	 * 价格id
	 */
	private String tourpriceId;
	
	/**
	 * 排序
	 */
	private Integer sort;
	

	public AirTicketPrice() {
		super();
	}

	public AirTicketPrice(String id, BigDecimal price, String departureId,
			String departureName, String tourpriceId, Integer sort) {
		super();
		this.id = id;
		this.price = price;
		this.departureId = departureId;
		this.departureName = departureName;
		this.tourpriceId = tourpriceId;
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDepartureId() {
		return departureId;
	}

	public void setDepartureId(String departureId) {
		this.departureId = departureId;
	}

	public String getDepartureName() {
		return departureName;
	}

	public void setDepartureName(String departureName) {
		this.departureName = departureName;
	}

	public String getTourpriceId() {
		return tourpriceId;
	}

	public void setTourpriceId(String tourpriceId) {
		this.tourpriceId = tourpriceId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}

package com.wenjing.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户下订单的数据传输对象
 * 
 * @author Jared
 *
 * Jun 3, 2015
 */
public class BookOrderVO {
	
	//产品ID,用于指定哪个产品
	private String productId;
	
	//产品Code
	private String productCode;
	
	//出发日期,根据用户选择日历产生
	private String departureDate;
	
	//订单的总价格
	private BigDecimal totalPrice; 
	
	//大人的总数量
	private int adultNum;
	
	//小孩的总数量
	private int childrenNum;
	
	//departureId
	private String departureId;
	
	//客人房间的住客情况,包括房号,大人数,小孩数
	private List<RoomInfo> roomInfoList;
	
	//机场接机情况
	private String airportPickUpId;
	
	//机票ID
	private String airTicketPriceId;
	
	//选中出发日期的Id
	private String tourDateId;

	public String getTourDateId() {
		return tourDateId;
	}

	public void setTourDateId(String tourDateId) {
		this.tourDateId = tourDateId;
	}

	public String getAirTicketPriceId() {
		return airTicketPriceId;
	}

	public void setAirTicketPriceId(String airTicketPriceId) {
		this.airTicketPriceId = airTicketPriceId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<RoomInfo> getRoomInfoList() {
		return roomInfoList;
	}

	public void setRoomInfoList(List<RoomInfo> roomInfoList) {
		this.roomInfoList = roomInfoList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public int getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(int adultNum) {
		this.adultNum = adultNum;
	}

	public int getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(int childrenNum) {
		this.childrenNum = childrenNum;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getDepartureId() {
		return departureId;
	}

	public void setDepartureId(String departureId) {
		this.departureId = departureId;
	}
	
	public String getAirportPickUpId() {
		return airportPickUpId;
	}

	public void setAirportPickUpId(String airportPickUpId) {
		this.airportPickUpId = airportPickUpId;
	}
}

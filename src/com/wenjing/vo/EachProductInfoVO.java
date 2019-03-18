package com.wenjing.vo;

import java.math.BigDecimal;
import java.util.List;

import com.wenjing.entity.AirTicketPrice;
import com.wenjing.entity.AirportPickUp;
import com.wenjing.entity.Couponse;
import com.wenjing.entity.Departure;
import com.wenjing.entity.Orderdetail;
import com.wenjing.entity.Product;
import com.wenjing.entity.TourPassenger;

/**
 * 用于购物车页面传递参数
 * 针对每个产品所对应的信息
 * 
 * @author Jared
 *
 * Jun 13, 2015
 */
public class EachProductInfoVO {
	
	//购物车ID
	private String shoppingCartId;

	//产品
	private Product product;
	
	//出发日期
	private String departureDate;
	
	//结束日期
	private String endDate;
	
	//结束日期的周几
	private int weekDay;
	
	//线路ID
	private String tourlineId;
	
	//线路的天数
	private int days;

	//房间住客情况
	private List<RoomInfo> roomInfoList;
	
	//订单的出发地
	private List<Departure> departureList;
	
	//机票的list
	private List<AirTicketPrice> airTicketPriceList;
	
	//机票价格Id
	private AirTicketPrice selectedAirTicketPrice;

	//用户选择的出发地Id
	private Departure selectedDeparture;
	
	//接送机价格
	private AirportPickUp selectedAirportPickUp;
	
	//产品的总价格
	private BigDecimal totalPrice;
	
	//总人数
	private int totalNum;
	
	//大人人数
	private int adultNum;
	
	//小孩人数
	private int childrenNum;
	
	//传输到页面的tourPrice,用于更改人数时进行价格计算
	private StringTourPrice stringTourPrice;
	
	//出发日期所属的tourdate的ID
	private String tourDateId;

	//货币类型
	private String currencySign;
	
	//线路中的自费和小费总额
	private String tourlineTips;
	
	//线路出发日期的日历
	private String tourCalendar;
	
	//子订单
	private Orderdetail orderDetail;
	
	//用于收录该产品的成人客人信息
	private List<TourPassenger> adultPassengerList;
	
	//用于收录该产品的儿童客人信息
	private List<TourPassenger> childrenPassengerList;
	
	//该用户下的couponse
	private List<Couponse> couponseList;
	
	//该产品的线路所对应的接送机价格
	private List<AirportPickUp> airportPickUpList;
	
	//团费
	private BigDecimal baseTourPrice;

	//导游服务费(小费)
	private BigDecimal tipsPrice;
	
	//行程外精彩景点/观光/演出(自费)
	private BigDecimal steamPrice;
	
	//接送机费用
	private BigDecimal pickUpPrice;
	
	//机票费用
	private BigDecimal ticketPrice;
	
	//积分兑换的金额
	private BigDecimal scoreExchangedPrice;
	
	//优惠券兑换的金额
	private BigDecimal couponseExchangedPrice;
	
	public BigDecimal getScoreExchangedPrice() {
		return scoreExchangedPrice;
	}

	public void setScoreExchangedPrice(BigDecimal scoreExchangedPrice) {
		this.scoreExchangedPrice = scoreExchangedPrice;
	}

	public BigDecimal getCouponseExchangedPrice() {
		return couponseExchangedPrice;
	}

	public void setCouponseExchangedPrice(BigDecimal couponseExchangedPrice) {
		this.couponseExchangedPrice = couponseExchangedPrice;
	}

	public BigDecimal getBaseTourPrice() {
		return baseTourPrice;
	}

	public void setBaseTourPrice(BigDecimal baseTourPrice) {
		this.baseTourPrice = baseTourPrice;
	}

	public BigDecimal getTipsPrice() {
		return tipsPrice;
	}

	public void setTipsPrice(BigDecimal tipsPrice) {
		this.tipsPrice = tipsPrice;
	}

	public BigDecimal getSteamPrice() {
		return steamPrice;
	}

	public void setSteamPrice(BigDecimal steamPrice) {
		this.steamPrice = steamPrice;
	}

	public BigDecimal getPickUpPrice() {
		return pickUpPrice;
	}

	public void setPickUpPrice(BigDecimal pickUpPrice) {
		this.pickUpPrice = pickUpPrice;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public List<AirTicketPrice> getAirTicketPriceList() {
		return airTicketPriceList;
	}

	public void setAirTicketPriceList(List<AirTicketPrice> airTicketPriceList) {
		this.airTicketPriceList = airTicketPriceList;
	}
	
	public String getTourlineId() {
		return tourlineId;
	}

	public void setTourlineId(String tourlineId) {
		this.tourlineId = tourlineId;
	}
	
	public Orderdetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Orderdetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	public List<RoomInfo> getRoomInfoList() {
		return roomInfoList;
	}

	public void setRoomInfoList(List<RoomInfo> roomInfoList) {
		this.roomInfoList = roomInfoList;
	}

	public List<Departure> getDepartureList() {
		return departureList;
	}

	public void setDepartureList(List<Departure> departureList) {
		this.departureList = departureList;
	}

	public String getTourCalendar() {
		return tourCalendar;
	}

	public void setTourCalendar(String tourCalendar) {
		this.tourCalendar = tourCalendar;
	}

	public Departure getSelectedDeparture() {
		return selectedDeparture;
	}

	public void setSelectedDeparture(Departure selectedDeparture) {
		this.selectedDeparture = selectedDeparture;
	}
	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getCurrencySign() {
		return currencySign;
	}

	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}
	
	public List<Couponse> getCouponseList() {
		return couponseList;
	}

	public void setCouponseList(List<Couponse> couponseList) {
		this.couponseList = couponseList;
	}
	
	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
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
	
	public List<AirportPickUp> getAirportPickUpList() {
		return airportPickUpList;
	}

	public void setAirportPickUpList(List<AirportPickUp> airportPickUpList) {
		this.airportPickUpList = airportPickUpList;
	}
	
	public StringTourPrice getStringTourPrice() {
		return stringTourPrice;
	}

	public void setStringTourPrice(StringTourPrice stringTourPrice) {
		this.stringTourPrice = stringTourPrice;
	}
	
	public String getTourlineTips() {
		return tourlineTips;
	}

	public void setTourlineTips(String tourlineTips) {
		this.tourlineTips = tourlineTips;
	}
	
	public List<TourPassenger> getAdultPassengerList() {
		return adultPassengerList;
	}

	public void setAdultPassengerList(List<TourPassenger> adultPassengerList) {
		this.adultPassengerList = adultPassengerList;
	}

	public List<TourPassenger> getChildrenPassengerList() {
		return childrenPassengerList;
	}

	public void setChildrenPassengerList(List<TourPassenger> childrenPassengerList) {
		this.childrenPassengerList = childrenPassengerList;
	}
	
	public AirTicketPrice getSelectedAirTicketPrice() {
		return selectedAirTicketPrice;
	}

	public void setSelectedAirTicketPrice(AirTicketPrice selectedAirTicketPrice) {
		this.selectedAirTicketPrice = selectedAirTicketPrice;
	}
	
	public AirportPickUp getSelectedAirportPickUp() {
		return selectedAirportPickUp;
	}

	public void setSelectedAirportPickUp(AirportPickUp selectedAirportPickUp) {
		this.selectedAirportPickUp = selectedAirportPickUp;
	}
	
	public String getTourDateId() {
		return tourDateId;
	}

	public void setTourDateId(String tourDateId) {
		this.tourDateId = tourDateId;
	}
}

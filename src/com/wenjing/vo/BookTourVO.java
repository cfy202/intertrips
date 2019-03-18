package com.wenjing.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Product;
import com.wenjing.entity.TourPassenger;

public class BookTourVO {
	
	//联系人名
	private String firstName;
	
	//联系人姓
	private String lastName;
	
	//联系人邮箱
	private String email;
	
	//联系电话
	private String phone;
	
	//联系地址
	private String address;
	
	//出发日期
	private Date departureDate;
	
	//客人总数量
	private int totalNumber;
	
	//客人大人数量
	private int adultsNumber;
	
	//客人儿童数量
	private int childrenNumber;
	
	//客人婴儿数量
	private int infantsNumber;
	
	//特殊要求
	private String specialrequest;
	
	//三人间的数量
	private Integer tripleRoomNumber;
	
	//twinroom的数量
	private Integer twinRoomNumber;
	
	//doubleroom的数量
	private Integer doubleRoomNumber;
	
	//单间的数量
	private Integer singleRoomNumber;
	
	//价格ID
	private String tourPriceId;
	
	//出发地的ID
	private String departureId;
	
	//接送机Id
	private String transferId;
	
	//自费Id
	private String selfPayId;
	
	//自费项的人数
	private Integer selfPayGuestNumber;
	
	//机票ID
	private String airticketPriceId;
	
	//导服Id
	private String guideServeId;
	
	//行程外精彩景点
	private String steamPriceId;
	
	//订单总价
	private BigDecimal totalPrice;
	
	//平均价格
	private BigDecimal avgPrice;
	
	//兑换的积分数量
	private Integer exchangeScore;
	
	//coupon code
	private String couponCode;
	
	//
	private String securityCode;

	//
	private String voucherCode;
	
	//币种
	private String currencyCode;
	
	//客人列表
	private List<TourPassenger> tourPassengerList;
	
	//线路ID
	private String tourlineId;
	
	//自费项与目的地相关
	private List<AdditionalProductVO> selfPayList;
	
	//自费项与目的地无关
	private List<AdditionalProductVO>  selfPayInTourline;
	
	//自定义的项目
	private List<AdditionalProductVO> additionalProductList;
	
	/**
	 * 向支付页面传递参数
	 */
	//总订单编号
	private String orderNumber;
	
	//总订单ID
	private String ordersId;
	
	//当前product
	private Product product;
	
	//当前cost
	private Cost cost;
	
	//团费
	private BigDecimal tourFee;
	
	//单房差
	private BigDecimal singleSupplementsFee;
	
	//导服费
	private BigDecimal guideServeFee;
	
	//行程外精彩景点/观光/演出
	private BigDecimal steamFee;
	
	//自费项
	private BigDecimal selfPayFee;
	
	//接送机
	private BigDecimal transferFee; 
	
	//机票费用
	private BigDecimal airticketFee;
	
	//coupon兑换金额
	private BigDecimal couponsFee;
	
	//积分兑换的金额
	private BigDecimal bonusPointsFee;
	
	//字符串抵达时间
	private String departureString;
	
	//单价
	private String sellingPrice;
	
	public String getDepartureString() {
		return departureString;
	}

	public void setDepartureString(String departureString) {
		this.departureString = departureString;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getAdultsNumber() {
		return adultsNumber;
	}

	public void setAdultsNumber(int adultsNumber) {
		this.adultsNumber = adultsNumber;
	}

	public int getChildrenNumber() {
		return childrenNumber;
	}

	public void setChildrenNumber(int childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public int getInfantsNumber() {
		return infantsNumber;
	}

	public void setInfantsNumber(int infantsNumber) {
		this.infantsNumber = infantsNumber;
	}

	public List<TourPassenger> getTourPassengerList() {
		return tourPassengerList;
	}

	public void setTourPassengerList(List<TourPassenger> tourPassengerList) {
		this.tourPassengerList = tourPassengerList;
	}

	public String getDepartureId() {
		return departureId;
	}

	public void setDepartureId(String departureId) {
		this.departureId = departureId;
	}

	public String getSelfPayId() {
		return selfPayId;
	}

	public void setSelfPayId(String selfPayId) {
		this.selfPayId = selfPayId;
	}

	public String getGuideServeId() {
		return guideServeId;
	}

	public void setGuideServeId(String guideServeId) {
		this.guideServeId = guideServeId;
	}

	public String getSteamPriceId() {
		return steamPriceId;
	}

	public void setSteamPriceId(String steamPriceId) {
		this.steamPriceId = steamPriceId;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getAirticketPriceId() {
		return airticketPriceId;
	}

	public void setAirticketPriceId(String airticketPriceId) {
		this.airticketPriceId = airticketPriceId;
	}

	public String getTourPriceId() {
		return tourPriceId;
	}

	public void setTourPriceId(String tourPriceId) {
		this.tourPriceId = tourPriceId;
	}
	
	public String getTourlineId() {
		return tourlineId;
	}

	public void setTourlineId(String tourlineId) {
		this.tourlineId = tourlineId;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public BigDecimal getTourFee() {
		return tourFee;
	}

	public void setTourFee(BigDecimal tourFee) {
		this.tourFee = tourFee;
	}

	public BigDecimal getGuideServeFee() {
		return guideServeFee;
	}

	public void setGuideServeFee(BigDecimal guideServeFee) {
		this.guideServeFee = guideServeFee;
	}

	public BigDecimal getSteamFee() {
		return steamFee;
	}

	public void setSteamFee(BigDecimal steamFee) {
		this.steamFee = steamFee;
	}

	public BigDecimal getSelfPayFee() {
		return selfPayFee;
	}

	public void setSelfPayFee(BigDecimal selfPayFee) {
		this.selfPayFee = selfPayFee;
	}

	public BigDecimal getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(BigDecimal transferFee) {
		this.transferFee = transferFee;
	}

	public BigDecimal getAirticketFee() {
		return airticketFee;
	}

	public void setAirticketFee(BigDecimal airticketFee) {
		this.airticketFee = airticketFee;
	}
	
	public BigDecimal getCouponsFee() {
		return couponsFee;
	}

	public void setCouponsFee(BigDecimal couponsFee) {
		this.couponsFee = couponsFee;
	}
	
	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
	
	public Integer getExchangeScore() {
		return exchangeScore;
	}

	public void setExchangeScore(Integer exchangeScore) {
		this.exchangeScore = exchangeScore;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getTripleRoomNumber() {
		return tripleRoomNumber;
	}

	public void setTripleRoomNumber(Integer tripleRoomNumber) {
		this.tripleRoomNumber = tripleRoomNumber;
	}

	public Integer getTwinRoomNumber() {
		return twinRoomNumber;
	}

	public void setTwinRoomNumber(Integer twinRoomNumber) {
		this.twinRoomNumber = twinRoomNumber;
	}

	public Integer getDoubleRoomNumber() {
		return doubleRoomNumber;
	}

	public void setDoubleRoomNumber(Integer doubleRoomNumber) {
		this.doubleRoomNumber = doubleRoomNumber;
	}

	public Integer getSingleRoomNumber() {
		return singleRoomNumber;
	}

	public void setSingleRoomNumber(Integer singleRoomNumber) {
		this.singleRoomNumber = singleRoomNumber;
	}

	public BigDecimal getBonusPointsFee() {
		return bonusPointsFee;
	}

	public void setBonusPointsFee(BigDecimal bonusPointsFee) {
		this.bonusPointsFee = bonusPointsFee;
	}
	
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}	
	
	public List<AdditionalProductVO> getAdditionalProductList() {
		return additionalProductList;
	}

	public void setAdditionalProductList(
			List<AdditionalProductVO> additionalProductList) {
		this.additionalProductList = additionalProductList;
	}	
	
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public Integer getSelfPayGuestNumber() {
		return selfPayGuestNumber;
	}

	public void setSelfPayGuestNumber(Integer selfPayGuestNumber) {
		this.selfPayGuestNumber = selfPayGuestNumber;
	}	
	
	public BigDecimal getSingleSupplementsFee() {
		return singleSupplementsFee;
	}

	public void setSingleSupplementsFee(BigDecimal singleSupplementsFee) {
		this.singleSupplementsFee = singleSupplementsFee;
	}	
	
	public List<AdditionalProductVO> getSelfPayList() {
		return selfPayList;
	}

	public void setSelfPayList(List<AdditionalProductVO> selfPayList) {
		this.selfPayList = selfPayList;
	}

	public List<AdditionalProductVO> getSelfPayInTourline() {
		return selfPayInTourline;
	}

	public void setSelfPayInTourline(List<AdditionalProductVO> selfPayInTourline) {
		this.selfPayInTourline = selfPayInTourline;
	}	
	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getSpecialrequest() {
		return specialrequest;
	}

	public void setSpecialrequest(String specialrequest) {
		this.specialrequest = specialrequest;
	}
}

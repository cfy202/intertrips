package com.wenjing.entity;

import java.util.Date;
import java.util.List;

import com.wenjing.vo.AdditionalProductVO;

/**
 * @author Jared
 *
 * Jun 11, 2015
 */
public class ShoppingCart {
	
	/** 超时时间  3天 */
	public static final int TIMEOUT = 604800;
	
	//购物车ID
	private String id;
	
	//产品ID
	private String productId;
	
	//出发地ID
	private String departureId;
	
	//产品编码
	private String productCode;
	
	//出发日期
	private Date departureDate;

	//总人数
	private Integer totalNumber;
	
	//大人数量
	private	Integer adultNumber;
	
	//小孩数量
	private Integer childrenNumber;
	
	//婴儿数量
	private Integer babyNumber;
	
	//私人订制项目
	private String additionalProducts;
	
	//接送机情况
	private String airportPickUpId;
	
	//机票ID
	private String airTicketPriceId;
	
	//出发日期所在的tourdate记录的ID
	private String tourDateId;

	//用户ID
	private String userId;
	
	//销售中心ID
	private String costNumber;
	
	//联系人电话
	private String phone;
	
	//联系人名
	private String firstName;
	
	//联系人姓
	private String lastName;
	
	//联系人邮箱
	private String email;
	
	//联系人出发城市备注
	private String departCity;
	
	//agent code
	private String agentCode;

	//销售中心
	private Cost cost;
	
	//储存产品
	private Product product;
	
	//自定义的项目
	private List<AdditionalProductVO> additionalProductList;
	
	//自费项
	private List<AdditionalProductVO> selfPayList;
	
	private List<AdditionalProductVO>  selfPayInTourline;
	
	public List<AdditionalProductVO> getSelfPayList() {
		return selfPayList;
	}

	public void setSelfPayList(List<AdditionalProductVO> selfPayList) {
		this.selfPayList = selfPayList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getAdultNumber() {
		return adultNumber;
	}

	public void setAdultNumber(Integer adultNumber) {
		this.adultNumber = adultNumber;
	}

	public Integer getChildrenNumber() {
		return childrenNumber;
	}

	public void setChildrenNumber(Integer childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public Integer getBabyNumber() {
		return babyNumber;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public void setBabyNumber(Integer babyNumber) {
		this.babyNumber = babyNumber;
	}

	public String getAdditionalProducts() {
		return additionalProducts;
	}

	public void setAdditionalProducts(String additionalProducts) {
		this.additionalProducts = additionalProducts;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
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
	
	public String getAirTicketPriceId() {
		return airTicketPriceId;
	}

	public void setAirTicketPriceId(String airTicketPriceId) {
		this.airTicketPriceId = airTicketPriceId;
	}

	public String getTourDateId() {
		return tourDateId;
	}

	public void setTourDateId(String tourDateId) {
		this.tourDateId = tourDateId;
	}
	
	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public List<AdditionalProductVO> getAdditionalProductList() {
		return additionalProductList;
	}

	public void setAdditionalProductList(
			List<AdditionalProductVO> additionalProductList) {
		this.additionalProductList = additionalProductList;
	}
	
	public List<AdditionalProductVO> getSelfPayInTourline() {
		return selfPayInTourline;
	}

	public void setSelfPayInTourline(List<AdditionalProductVO> selfPayInTourline) {
		this.selfPayInTourline = selfPayInTourline;
	}	
	
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getDepartCity() {
		return departCity;
	}

	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}
}

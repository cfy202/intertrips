package com.wenjing.webservice.entity;

import java.util.List;

/**
 * @copyright   Copyright: 2014 
 * @author Jared
 * @create-time Sep 3, 2014 3:00:00 PM
 * @revision  3.0
 */
public class Customer {

	private static final long serialVersionUID = 7065110973203482161L;

	private String customerId; //顾客id编号
	
	private String lastName; //姓
	
	private String firstName; //名
	
	private String middleName; //中间名称
	
    private String birthday;
	
	private Integer sex; //性别   1 女 F；2 男 M
	
	private String memoOfCustomer; //客人备注：新客人、老客人、老人、小孩等
	
	private String nationalityOfPassport; //护照国籍
	
	private String passportNo; //护照号码
	
    private String passportDate; //传输数据用于存储房型(webService)
	
	private String streetAddress; //联系地址
	
	private String city; //城市
	
	private String tel; //联系电话
	
	private String mobile; //移动电话
	
	private String email; //邮箱
	
	private String zip; //邮编
	
	private String otherInfo; //订单中其他备注信息
	
	private String language; //语种
	
	private String state; //州ID
	
	private String country; //国家ID
	
	private String planticket; //定机票类别：1:自订; 2:代订; 3:两者兼有 
	
    private String guestRoomType; //传输数据用于存储房型
    
    private int roomNumber;
    
	private int roomIsFull;
	
	private String payHistoryInfo; //客人备注
	
	private String languageId; //语言
	
	private List<CustomerFlight> customerFlightList;
	
	public List<CustomerFlight> getCustomerFlightList() {
		return customerFlightList;
	}

	public void setCustomerFlightList(List<CustomerFlight> customerFlightList) {
		this.customerFlightList = customerFlightList;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMemoOfCustomer() {
		return memoOfCustomer;
	}

	public void setMemoOfCustomer(String memoOfCustomer) {
		this.memoOfCustomer = memoOfCustomer;
	}

	public String getNationalityOfPassport() {
		return nationalityOfPassport;
	}

	public void setNationalityOfPassport(String nationalityOfPassport) {
		this.nationalityOfPassport = nationalityOfPassport;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPassportDate() {
		return passportDate;
	}

	public void setPassportDate(String passportDate) {
		this.passportDate = passportDate;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPlanticket() {
		return planticket;
	}

	public void setPlanticket(String planticket) {
		this.planticket = planticket;
	}

	public String getGuestRoomType() {
		return guestRoomType;
	}

	public void setGuestRoomType(String guestRoomType) {
		this.guestRoomType = guestRoomType;
	}

	public String getPayHistoryInfo() {
		return payHistoryInfo;
	}

	public void setPayHistoryInfo(String payHistoryInfo) {
		this.payHistoryInfo = payHistoryInfo;
	}
	
    public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomIsFull() {
		return roomIsFull;
	}

	public void setRoomIsFull(int roomIsFull) {
		this.roomIsFull = roomIsFull;
	}
	
	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
}

package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单顾客信息
 * 
 * @author Jared
 *
 * Jun 5, 2015
 */
public class TourPassenger implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1396764261271018169L;

	//主键ID
	private String id;
	
	//用户的姓
	private String lastName;
	
	//用户的名
	private String firstName;
	
	private String middleName;

	//用户性别 男：0 女：1
	private Integer gender;
	
	//用户生日
	private Date birthday;
	
	//用户国籍
	private String nationality;
	
	//用户护照号
	private String passportNo;
	
	//护照有效期
	private Date passportNoExpiryDate;
	
	//用户手机号
	private String mobileNumber;
	
	//房型
	private String roomType;
	
	//房间编号
	private Integer roomNumber;
	
	//身份
	private String identity;
	
	//子订单ID
	private String ordersId;
	
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public Date getPassportNoExpiryDate() {
		return passportNoExpiryDate;
	}

	public void setPassportNoExpiryDate(Date passportNoExpiryDate) {
		this.passportNoExpiryDate = passportNoExpiryDate;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
}

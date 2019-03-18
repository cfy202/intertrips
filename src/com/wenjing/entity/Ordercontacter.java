package com.wenjing.entity;

import java.io.Serializable;

public class Ordercontacter implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5942540443964344393L;

	/**
     * ordercontacter.id (联系人ID)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String id;

    /**
     * ordercontacter.orderId (订单ID)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String orderid;

    /**
     * ordercontacter.title (称谓)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String title;

    /**
     * ordercontacter.name (名)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String firstName;
    
    /**
     * 姓
     */
    private String lastName;

	/**
     * ordercontacter.nationality (国籍)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String nationality;

    /**
     * ordercontacter.email (邮箱)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String email;

    /**
     * ordercontacter.tel (电话)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String tel;

    /**
     * ordercontacter.isDefault (是否默认)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private Boolean isdefault;

    /**
     * ordercontacter.pronvice (省份)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String pronvice;

    /**
     * ordercontacter.city (城市)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String city;

    /**
     * ordercontacter.address (详细地址)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String address;

    /**
     * ordercontacter.postalcode (邮政编码)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String postalcode;

    /**
     * ordercontacter.fax (传真号码)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String fax;

    /**
     * ordercontacter.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String costnumber;

    /**
     * ordercontacter.userId (用户id)
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    private String userid;

    private Member memberUserid;

    private Orders ordersOrderid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    public String getPronvice() {
        return pronvice;
    }

    public void setPronvice(String pronvice) {
        this.pronvice = pronvice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setMemberUserid(Member memberUserid) {
        this.memberUserid=memberUserid;
    }

    public Member getMemberUserid() {
        return memberUserid;
    }

    public void setOrdersOrderid(Orders ordersOrderid) {
        this.ordersOrderid=ordersOrderid;
    }

    public Orders getOrdersOrderid() {
        return ordersOrderid;
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
}
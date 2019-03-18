package com.wenjing.entity;

import java.io.Serializable;

public class CreditCardInfo implements Serializable {
	
	private static String CARDTYPE_VISA = "Visa";
	private static String CARDTYPE_MASTERCARD = "MasterCard";
	private static String CARDTYPE_DISCOVER = "Discover";
	private static String CARDTYPE_AMERICANEXPRESS = "AmericanExpress";
	
    private String id;

    private Integer cardtype;

    private String cardnumber;

    private String expiredate;

    private String cardcode;

    private String nationality;

    private String lastname;

    private String firstname;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    private String tel;

    private String mail;

    private String ordersid;
    
	private String bookingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(String ordersid) {
        this.ordersid = ordersid;
    }
    
    public String getBookingId() {
 		return bookingId;
 	}

 	public void setBookingId(String bookingId) {
 		this.bookingId = bookingId;
 	}
    
    public void setCardTypeString(String cardType){
    	if(CARDTYPE_VISA.equals(cardType)){
    		this.cardtype = 1;
    	}else if(CARDTYPE_MASTERCARD.equals(cardType)){
    		this.cardtype = 2;
    	}else if(CARDTYPE_DISCOVER.equals(cardType)){
    		this.cardtype = 3;
    	}else if(CARDTYPE_AMERICANEXPRESS.equals(cardType)){
    		this.cardtype = 4;
    	}
    }
    
    public String getCardTypeString(){
    	switch(this.cardtype){
    	   case 1:return CARDTYPE_VISA;
    	   case 2:return CARDTYPE_MASTERCARD;
    	   case 3:return CARDTYPE_DISCOVER;
    	   case 4:return CARDTYPE_AMERICANEXPRESS;
    	   default:return "";
    	}
    }
}
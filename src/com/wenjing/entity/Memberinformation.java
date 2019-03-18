package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

public class Memberinformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4171331630203250482L;

	/**
	 * memberinformation.id (用户信息ID)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String id;

	/**
	 * memberinformation.realName (姓)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String lastName;

	/**
	 * memberinformation.firstName (名)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String firstName;

	/**
	 * memberinformation.email (邮箱)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String email;

	/**
	 * memberinformation.nationality (国籍)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String nationality;

	/**
	 * memberinformation.passportNo (护照号)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String passportNo;

	/**
	 * memberinformation.passportValid (护照有效期)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private Date passportValid;

	/**
	 * memberinformation.birthday (生日)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private Date birthday;

	/**
	 * memberinformation.country (国家)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String country;

	/**
	 * memberinformation.province (省)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String province;

	/**
	 * memberinformation.city (城市)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String city;

	/**
	 * memberinformation.userTel (电话)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String usertel;

	/**
	 * memberinformation.userMobile (手机号码)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String usermobile;

	/**
	 * memberinformation.userSex (性别)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String usersex;

	/**
	 * memberinformation.userAddr (地址)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String useraddr;

	/**
	 * memberinformation.postalcode (邮政编码)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String postalcode;

	/**
	 * memberinformation.userLevel (会员级别)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private Integer userlevel;

	/**
	 * memberinformation.imageUrl (会员图像路径)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String imageurl;

	/**
	 * memberinformation.question (密码问题)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String question;

	/**
	 * memberinformation.answer (密码答案)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String answer;

	/**
	 * memberinformation.userId (用户ID)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private String memberid;

	/**
	 * memberinformation.score (用户积分)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:25
	 */
	private Integer score;
	
	/**
	 * 邀请人详细信息id
	 */
	private String inviteId;

	private Member member;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUsermobile() {
		return usermobile;
	}

	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getUseraddr() {
		return useraddr;
	}

	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public Integer getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(Integer userlevel) {
		this.userlevel = userlevel;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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


	public Date getPassportValid() {
		return passportValid;
	}

	public void setPassportValid(Date passportValid) {
		this.passportValid = passportValid;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getInviteId() {
		return inviteId;
	}

	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
}
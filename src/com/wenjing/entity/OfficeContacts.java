package com.wenjing.entity;

/**
 * 
 * 
 * @author Jared
 *
 */
public class OfficeContacts {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 办公室名称
	 */
	private String officeName;
	
	/**
	 * 联系电话
	 */
	private String tel;
	
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 运营中心ID
	 */
	private String costnumber;
	
	/**
	 * 运营中心
	 */
	private Cost cost;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}
	
	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
}

package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

public class ContactUs implements Serializable {
    /**
     * 主键
     * contactus.id
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String id;

    /**
     * 邮件
     * contactus.email
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String email;

    /**
     * 联系人名称
     * contactus.name
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String name;

    /**
     * 所属国籍
     * contactus.nationality
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String nationality;

    /**
     * 电话号码
     * contactus.phoneNo
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String phoneNo;

    /**
     * 主题
     * contactus.subject
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String subject;

    /**
     * 评论内容
     * contactus.comments
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String comments;

    /**
     * 创建时间
     * contactus.createDate
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private Date createDate;

    /**
     * 所属IP
     * contactus.IP
     * @ibatorgenerated 2015-12-18 09:26:30
     */
    private String ip;
    
    /**
     * 所属运营中心
     */
    private String costnumber;
    
    /**
     * Cost
     */
    private Cost cost;

    public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSubject() {
        return subject;
    }

    public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
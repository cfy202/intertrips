package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

public class Activityrules implements Serializable {
    /**
     * activityrules.id
     * @ibatorgenerated 2015-10-21 12:02:58
     */
    private String id;

    /**
     * activityrules.content (内容)
     * @ibatorgenerated 2015-10-21 12:02:58
     */
    private String content;

    /**
     * activityrules.couponseId (优惠券活动ID)
     * @ibatorgenerated 2015-10-21 12:02:58
     */
    private String couponseid;
    /**
     * 添加时间
     */
    private Date addTime;
    
    

    public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCouponseid() {
        return couponseid;
    }

    public void setCouponseid(String couponseid) {
        this.couponseid = couponseid;
    }
}
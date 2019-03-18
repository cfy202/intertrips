package com.wenjing.entity;

import java.io.Serializable;

public class Couponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8306671133183059525L;

	private String id;

    private Integer amount;

    private Integer remaining;

    private String expirationdate;

    private Byte status;

    private Byte type;

    private String name;
    
    private Integer sort;
    
    private String costnumber;

    private Cost cost;
    
    private String infor;
    
    private Integer isCreate;
    
    private String filePath;

    private String pic;
    
    private Integer startTime;
    
    private Integer endTime;
    
    private String beginTime;
    
    private String overTime;
    
    private String couponseCode;
    
    
    
    
    
    public String getCouponseCode() {
		return couponseCode;
	}

	public void setCouponseCode(String couponseCode) {
		this.couponseCode = couponseCode;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public String getInfor() {
		return infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
	}

	public Integer getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Integer isCreate) {
		this.isCreate = isCreate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * @return the cost
	 */
	public Cost getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Cost cost) {
		this.cost = cost;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the costnumber
	 */
	public String getCostnumber() {
		return costnumber;
	}

	/**
	 * @param costnumber the costnumber to set
	 */
	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

   

    /**
	 * @return the expirationdate
	 */
	public String getExpirationdate() {
		return expirationdate;
	}

	/**
	 * @param expirationdate the expirationdate to set
	 */
	public void setExpirationdate(String expirationdate) {
		this.expirationdate = expirationdate;
	}

	public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
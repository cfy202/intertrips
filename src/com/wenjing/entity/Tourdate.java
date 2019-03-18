package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tourdate implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5582576265881681661L;

	/**
     * tourdate.id (线路日期id)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String id;

    /**
     * tourdate.startDate (开始日期)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Integer startdate;
    
    /**
     * tourdate.endDate (结束日期)
     * @ibatorgenerated 2015-05-06 16:43:25
     */
    private Integer enddate;

    /**
     * tourdate.totalNum (成团人数)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Integer totalnum;

    /**
     * tourdate.soldNum (售出人数)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Integer soldnum;

    /**
     * tourdate.remainNum (剩余人数)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Integer remainnum;

    /**
     * tourdate.isShow (显示)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Boolean isshow;

    /**
     * tourdate.isHot (热推)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Boolean ishot;

    /**
     * tourdate.isCall (电话联系)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Boolean iscall;
    
    /**
     * 拨打电话
     */
    private String callNumber;

    /**
     * tourdate.remark (备注)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String remark;

    /**
     * tourdate.updateTime (系统时间)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Date updatetime;

    /**
     * tourdate.editor (编辑人)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String editor;

    /**
     * tourdate.sort (排序)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private Integer sort;

    /**
     * tourdate.tourLineId (线路Id)
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    private String productid;
    
    /**
     * tourdate.dateWeek (周期(周几))
     * 8单天
     * 1..7星期一到星期日
     * 0全周
     * @ibatorgenerated 2015-05-06 16:43:25
     */
    private String dateweek;
    
    /**
     * 运营中心
     */
    private String costNumber;
    
    /**
     * 隔天发团（几天）
     */
    private Integer days;
    
    /**
     * 封团日期（出发日期前几天）
     */
    private Integer sealGroupDate;
    
	/**
     * tourdate.startdatestr 开始字符串日期
     * @ibatorgenerated 2015-05-06 16:43:25
     */
    private String startdatestr;
    
    /**
     * tourdate.startdatestr 结束字符串日期
     * @ibatorgenerated 2015-05-06 16:43:25
     */
    private String enddatestr;
    
    /**
     * tourdate.datelimit 日期区间
     * @ibatorgenerated 2015-05-06 16:43:25
     */
    private String datelimit;
    
   
    private Set<Tourprice> tourpricesTourdateid = new HashSet<Tourprice>(0);
    
    private List<Departure> departureList;

	private Tourline tourlineTourlineid;
    
    private Tourprice tourprice;
    
    private Cost cost;
    


	/**
     * 查询最大值或者最小值时传递价格
     */
    private BigDecimal price;
    

	public Tourdate() {
		super();
	}

	public Tourdate(String id, Integer startdate, Integer totalnum,
			Integer soldnum, Integer remainnum, Boolean isshow, Boolean ishot,
			Boolean iscall, String remark, Date updatetime, String editor,
			Integer sort, String productid, Integer enddate, String dateweek,
			String costNumber, Integer days, Integer sealGroupDate) {
		super();
		this.id = id;
		this.startdate = startdate;
		this.totalnum = totalnum;
		this.soldnum = soldnum;
		this.remainnum = remainnum;
		this.isshow = isshow;
		this.ishot = ishot;
		this.iscall = iscall;
		this.remark = remark;
		this.updatetime = updatetime;
		this.editor = editor;
		this.sort = sort;
		this.productid = productid;
		this.enddate = enddate;
		this.dateweek = dateweek;
		this.costNumber = costNumber;
		this.days = days;
		this.sealGroupDate = sealGroupDate;
	}
	
	

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStartdate() {
        return startdate;
    }

    public void setStartdate(Integer startdate) {
        this.startdate = startdate;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getSoldnum() {
        return soldnum;
    }

    public void setSoldnum(Integer soldnum) {
        this.soldnum = soldnum;
    }

    public Integer getRemainnum() {
        return remainnum;
    }

    public void setRemainnum(Integer remainnum) {
        this.remainnum = remainnum;
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    public Boolean getIshot() {
        return ishot;
    }

    public void setIshot(Boolean ishot) {
        this.ishot = ishot;
    }

    public Boolean getIscall() {
        return iscall;
    }

    public void setIscall(Boolean iscall) {
        this.iscall = iscall;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public void setTourpricesTourdateid(Set<Tourprice> tourpricesTourdateid) {
        this.tourpricesTourdateid=tourpricesTourdateid;
    }

    public Set<Tourprice> getTourpricesTourdateid() {
        return tourpricesTourdateid;
    }

    public void setTourlineTourlineid(Tourline tourlineTourlineid) {
        this.tourlineTourlineid=tourlineTourlineid;
    }

    public Tourline getTourlineTourlineid() {
        return tourlineTourlineid;
    }

	public Integer getEnddate() {
		return enddate;
	}

	public void setEnddate(Integer enddate) {
		this.enddate = enddate;
	}

	public String getDateweek() {
		return dateweek;
	}

	public void setDateweek(String dateweek) {
		this.dateweek = dateweek;
	}

	public String getStartdatestr() {
		return startdatestr;
	}

	public void setStartdatestr(String startdatestr) {
		this.startdatestr = startdatestr;
	}

	public String getEnddatestr() {
		return enddatestr;
	}

	public void setEnddatestr(String enddatestr) {
		this.enddatestr = enddatestr;
	}

	public String getDatelimit() {
		return datelimit;
	}

	public void setDatelimit(String datelimit) {
		this.datelimit = datelimit;
	}

	public Tourprice getTourprice() {
		return tourprice;
	}

	public void setTourprice(Tourprice tourprice) {
		this.tourprice = tourprice;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
    public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getSealGroupDate() {
		return sealGroupDate;
	}

	public void setSealGroupDate(Integer sealGroupDate) {
		this.sealGroupDate = sealGroupDate;
	}
	
    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
    public List<Departure> getDepartureList() {
		return departureList;
	}

	public void setDepartureList(List<Departure> departureList) {
		this.departureList = departureList;
	}
	
    public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
}
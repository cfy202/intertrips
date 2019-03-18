package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

public class PreBookingOfAgent implements Serializable {
   
	private String id;
	
	private String bookingNo;

	private String name;

    private String phoneno;

    private String email;

    private Integer pax;

    private String gateway;

    private String departureid;

    private String creditcardno;

    private String expirationdate;

    private String securitycode;

    private Integer total;

	private Date departuredate;

    private String productcode;

    private String productname;

    private String remarks;

    private Date createtime;

    private String agentcode;

    private String productid;

    private String tourdateid;

    private String currencysign;
    
    private String tourpriceid;
    
	private String expirationDateMonth;
    
    private String expirationDateYear;
    
    private String tourlineId;
    
    private String costnumber;
    
    private Integer type;
    
	private Boolean isSynchronize; 
    
    private String synchronizeNo;
    
    private Boolean isOnlinePay;

	private Product product;
    
    private Departure departure;

	private String departureDate;
    
    private AgentCode agentCode;
    
    public AgentCode getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(AgentCode agentCode) {
		this.agentCode = agentCode;
	}
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPax() {
        return pax;
    }

    public void setPax(Integer pax) {
        this.pax = pax;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getDepartureid() {
        return departureid;
    }

    public void setDepartureid(String departureid) {
        this.departureid = departureid;
    }

    public String getCreditcardno() {
        return creditcardno;
    }

    public void setCreditcardno(String creditcardno) {
        this.creditcardno = creditcardno;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }
    
    
    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(String agentcode) {
        this.agentcode = agentcode;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getTourdateid() {
        return tourdateid;
    }

    public void setTourdateid(String tourdateid) {
        this.tourdateid = tourdateid;
    }

    public String getCurrencysign() {
        return currencysign;
    }

    public void setCurrencysign(String currencysign) {
        this.currencysign = currencysign;
    }
    
    public String getTourpriceid() {
		return tourpriceid;
	}

	public void setTourpriceid(String tourpriceid) {
		this.tourpriceid = tourpriceid;
	}
	
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCostnumber() {
		return costnumber;
	}

	public void setCostnumber(String costnumber) {
		this.costnumber = costnumber;
	}

	public String getTourlineId() {
		return tourlineId;
	}

	public void setTourlineId(String tourlineId) {
		this.tourlineId = tourlineId;
	}

	public String getExpirationDateMonth() {
		return expirationDateMonth;
	}

	public void setExpirationDateMonth(String expirationDateMonth) {
		this.expirationDateMonth = expirationDateMonth;
	}

	public String getExpirationDateYear() {
		return expirationDateYear;
	}

	public void setExpirationDateYear(String expirationDateYear) {
		this.expirationDateYear = expirationDateYear;
	}
	
    public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
    public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	
	public Departure getDeparture() {
		return departure;
	}

	public void setDeparture(Departure departure) {
		this.departure = departure;
	}
	
    public Boolean getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(Boolean isSynchronize) {
		this.isSynchronize = isSynchronize;
	}
	
	public String getSynchronizeNo() {
		return synchronizeNo;
	}

	public void setSynchronizeNo(String synchronizeNo) {
		this.synchronizeNo = synchronizeNo;
	}
	
	public Boolean getIsOnlinePay() {
		return isOnlinePay;
	}

	public void setIsOnlinePay(Boolean isOnlinePay) {
		this.isOnlinePay = isOnlinePay;
	}
	
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
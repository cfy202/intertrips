package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Orders implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 626858612641315582L;
	
	/**
	 * 订单类型
	 */
	public static final int TOURLINE = 1;
	public static final int VISA = 2;
	public static final int SHIP = 3;

	/**
     * orders.Id (订单ID)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String id;

    /**
     * orders.orderNo (订单编号)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String orderno;

    /**
     * orders.quantity (商品数量)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Integer quantity;

    /**
     * orders.totalPrice (总价)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private BigDecimal totalprice;

    /**
     * orders.createTime (生成时间)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Date createtime;

    /**
     * orders.receiveWay (收货方式)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String receiveway;

    /**
     * orders.payway (付款方式)
     * 1.paypal支付
     * 2.支付宝支付
     * 3.财付通支付
     * 4.Authorize
     * 10.支票支付
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String payway;

    /**
     * orders.score (积分)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private Integer score;

    /**
     * orders.remark (备注)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String remark;

    /**
     * orders.IP (ip)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String ip;

    /**
     * orders.statusId (订单状态ID)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String statusid;

    /**
     * orders.userId (用户ID)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String userid;
    
    /**
     * 订单类型
     */
    private int orderType;

	/**
     * orders.costNumber (运营中心ID)
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    private String costnumber;
    
    /**
     * 总订单下的产品名称
     */
    private String productName;
    
	/**
     * 总订单下的产品CODE
     */
    private String productCode;
    
    /**
     * 联系人名
     */
    private String firstName;
    
    /**
     * 联系人名
     */
    private String lastName;
    
    /**
     * 是否同步到内部系统,0:未同步,1:已同步
     */
    private int isSynchronized;
    
    /**
     * 同步到内部系统的订单编号
     */
    private String synchronizedOrderNo;
    
    /**
     * 占位的时间
     */
    private Date occupyTime;
    
    /**
     * 总订单时间轴状态
     */
    private String timeLineStatus;
    
	/**
     * 总订单下的所有子订单ID
     */
	private List<String> orderDetailIds;

	/**
	 * 总订单所对应的状态
	 */
    private Orderstatus orderStatus;
    
    /**
     * 总订单所对应的联系人
     */
    private Ordercontacter orderContacter; 
    
    /**
     * 总订单所对应的子订单
     */
    private List<Orderdetail> orderdetails;
    
    /**
     * 订单费用
     */
    private List<Orderexpense> orderexpenseList;
    
	private List<Product> product;
    
    private Cost cost;
    
    private BigDecimal despotPrice;
    

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getReceiveway() {
        return receiveway;
    }

    public void setReceiveway(String receiveway) {
        this.receiveway = receiveway;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCostnumber() {
        return costnumber;
    }

    public void setCostnumber(String costnumber) {
        this.costnumber = costnumber;
    }
    
    public Orderstatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Orderstatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<String> getOrderDetailIds() {
		return orderDetailIds;
	}

	public void setOrderDetailIds(List<String> orderDetailIds) {
		this.orderDetailIds = orderDetailIds;
	}
	
	public Ordercontacter getOrderContacter() {
		return orderContacter;
	}

	public void setOrderContacter(Ordercontacter orderContacter) {
		this.orderContacter = orderContacter;
	}

	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public BigDecimal getDespotPrice() {
		return despotPrice;
	}

	public void setDespotPrice(BigDecimal despotPrice) {
		this.despotPrice = despotPrice;
	}
	
    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public int getIsSynchronized() {
		return isSynchronized;
	}

	public void setIsSynchronized(int isSynchronized) {
		this.isSynchronized = isSynchronized;
	}
	
    public List<Orderexpense> getOrderexpenseList() {
		return orderexpenseList;
	}

	public void setOrderexpenseList(List<Orderexpense> orderexpenseList) {
		this.orderexpenseList = orderexpenseList;
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
	
	public String getSynchronizedOrderNo() {
		return synchronizedOrderNo;
	}

	public void setSynchronizedOrderNo(String synchronizedOrderNo) {
		this.synchronizedOrderNo = synchronizedOrderNo;
	}
	
	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	
	public Date getOccupyTime() {
		return occupyTime;
	}

	public void setOccupyTime(Date occupyTime) {
		this.occupyTime = occupyTime;
	}

	public String getPayWayDetail(){
		if(StringUtils.isEmpty(payway)){
			return "未支付";
		}else if("1".equals(payway)){
			return "Paypal";
		}else if("2".equals(payway)){
			return "支付宝";
		}else if("3".equals(payway)){
			return "财付通";
		}else if("4".equals(payway)){
			return "Authorize";
		}else if("10".equals(payway)){
			return "支票";
		}else{
			return "";
		}
	}

	public String getTimeLineStatus() {
		return timeLineStatus;
	}

	public void setTimeLineStatus(String timeLineStatus) {
		this.timeLineStatus = timeLineStatus;
	}
}
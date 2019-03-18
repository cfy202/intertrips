package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

public class Cancelrecords implements Serializable {
	
	//id
    private String id;
    
    //备注
    private String remarks;
    
    //操作者
    private String operator;

    //取消日期
    private Date canceldate;

    //订单Id
    private String ordersid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCanceldate() {
        return canceldate;
    }

    public void setCanceldate(Date canceldate) {
        this.canceldate = canceldate;
    }

    public String getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(String ordersid) {
        this.ordersid = ordersid;
    }
}
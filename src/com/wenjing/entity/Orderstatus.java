package com.wenjing.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Orderstatus implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 134818075440092140L;
	
	public static final String PAID = "0e0bdd79-1e47-11e5-9bc2-94de800aa37e";
	
	public static final String NEW = "0df0f0e4-1e47-11e5-9bc2-94de800aa37e";
	
	public static final String CANCELLED = "0e149a16-1e47-11e5-9bc2-94de800aa37e";
	
	public static final String DEPOSIT = "0dff5fd4-1e47-11e5-9bc2-94de800aa37e";

	/**
     * orderstatus.id (状态ID)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String id;

    /**
     * orderstatus.name (名称)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String name;

    /**
     * orderstatus.text (描述)
     * @ibatorgenerated 2015-04-22 23:01:21
     */
    private String text;
    
    private Integer code;

    private Set<Orders> orderssStatusid = new HashSet<Orders>(0);

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOrderssStatusid(Set<Orders> orderssStatusid) {
        this.orderssStatusid=orderssStatusid;
    }

    public Set<Orders> getOrderssStatusid() {
        return orderssStatusid;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
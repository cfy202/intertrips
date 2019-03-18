package com.wenjing.entity;

import java.io.Serializable;

public class Couponseproduct implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9112188985602036882L;

	/**
     * tourlineattraction.id (编号)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String id;

    /**
     * tourlineattraction.tourLineId (线路ID)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String productid;

    /**
     * tourlineattraction.attractionId (景点ID)
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    private String couponseid;

    private Couponse coupsones;

    private Product productIdto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  

    

    public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public Product getProductIdto() {
		return productIdto;
	}

	public void setProductIdto(Product productIdto) {
		this.productIdto = productIdto;
	}

	public String getCouponseid() {
		return couponseid;
	}

	public void setCouponseid(String couponseid) {
		this.couponseid = couponseid;
	}

	public Couponse getCoupsones() {
		return coupsones;
	}

	public void setCoupsones(Couponse coupsones) {
		this.coupsones = coupsones;
	}

	
}
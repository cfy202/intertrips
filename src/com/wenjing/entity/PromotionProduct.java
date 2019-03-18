package com.wenjing.entity;

import java.io.Serializable;

public class PromotionProduct implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4318202376388792430L;

	/**
	 * PromotioProduct.id (活动ID)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:26
	 */
	private String id;

	/**
	 * PromotioProduct.promotionId (促销活动id)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:26
	 */
	private String promotionid;

	/**
	 * PromotioProduct.productId (产品id)
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:26
	 */
	private String productid;

	private Product productProductid;

	private Promotion promotionPromotionid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPromotionid() {
		return promotionid;
	}

	public void setPromotionid(String promotionid) {
		this.promotionid = promotionid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public Product getProductProductid() {
		return productProductid;
	}

	public void setProductProductid(Product productProductid) {
		this.productProductid = productProductid;
	}

	public Promotion getPromotionPromotionid() {
		return promotionPromotionid;
	}

	public void setPromotionPromotionid(Promotion promotionPromotionid) {
		this.promotionPromotionid = promotionPromotionid;
	}

}
package com.wenjing.webservice.entity;

import java.math.BigDecimal;

/**
 * @copyright   Copyright: 2014 
 * @author Jared
 * @create-time Sep 3, 2014 2:09:06 PM
 * @revision  3.0
 */

public class OrderReceiveItem {
	
	private String receivableInfoOfOrderId; //关联表ReceivableInfoOfOrder的ID
	
	
	private Integer type; //《团== 1：常规团费 2：其他费用 3：特殊折扣》《非团 == 1:visa 2:flight ticket 3:hotel 4:ticket 5:insurance 6:busTour 7:cruise 8:other    
	
	
	private BigDecimal itemFee; //费用
	
	
	private Integer itemFeeNum; //数量
	
	
	private String remark; //备注
	
	
	private Integer num; //顺序
	
	
	public String getReceivableInfoOfOrderId() {
		return receivableInfoOfOrderId;
	}


	public void setReceivableInfoOfOrderId(String receivableInfoOfOrderId) {
		this.receivableInfoOfOrderId = receivableInfoOfOrderId;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public BigDecimal getItemFee() {
		return itemFee;
	}


	public void setItemFee(BigDecimal itemFee) {
		this.itemFee = itemFee;
	}


	public Integer getItemFeeNum() {
		return itemFeeNum;
	}


	public void setItemFeeNum(Integer itemFeeNum) {
		this.itemFeeNum = itemFeeNum;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}
}

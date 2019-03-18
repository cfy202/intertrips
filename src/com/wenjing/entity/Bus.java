package com.wenjing.entity;

public class Bus {
	
	/** 主键 */
	private String id;
	
	/** 车型 */
    private String busType;
    
	/** 乘客数 */
    private Integer passengerNumber;
    
    /** 行李位  */
    private Integer luggageNumber;

    /** 备注 */
    private String remark;
    
	/** 价格ID */
    private String priceId;
    
    /** 基础费用 */
    private Price basicFee;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Integer getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(Integer passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	public Integer getLuggageNumber() {
		return luggageNumber;
	}

	public void setLuggageNumber(Integer luggageNumber) {
		this.luggageNumber = luggageNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public Price getBasicFee() {
		return basicFee;
	}

	public void setBasicFee(Price basicFee) {
		this.basicFee = basicFee;
	}
}

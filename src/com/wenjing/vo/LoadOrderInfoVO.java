package com.wenjing.vo;

import java.math.BigDecimal;
import java.util.List;
import com.wenjing.entity.Ordercontacter;
import com.wenjing.entity.Orders;

/**
 * 下订单后客人录入数据页面的数据以及展示给客人数据的传输对象
 * 
 * @author Jared
 *
 * Jun 5, 2015
 */
public class LoadOrderInfoVO {
	
	/**
	 * 每个产品所对应的信息
	 */
	private List<EachProductInfoVO> eachProductInfoVOList;
	
	/**
	 * 用户选择积分的选项
	 */
	private int[] scoreLevelArray;
	
	/**
	 * 订单的联系人
	 */
	private Ordercontacter orderContacter;
	
	/**
	 * 订单的总价
	 */
	private BigDecimal totalPrice;
	
	/**
	 * 总订单信息
	 */
	private Orders orders;
	
	/**
	 * 运营中心编号
	 */
	private String costNumber;
	
	/**
	 * 汇率
	 */
	private String exchangeRate;
	
	/**
	 * 订单的总积分
	 */
	private int score;
	
	/**
	 * 兑换的积分
	 */
	private int exchangedScore;
	/**
	 * 优惠券code
	 */
	private String couposeCode;
	
	

	public String getCouposeCode() {
		return couposeCode;
	}

	public void setCouposeCode(String couposeCode) {
		this.couposeCode = couposeCode;
	}

	public int getExchangedScore() {
		return exchangedScore;
	}

	public void setExchangedScore(int exchangedScore) {
		this.exchangedScore = exchangedScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Ordercontacter getOrderContacter() {
		return orderContacter;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int[] getScoreLevelArray() {
		return scoreLevelArray;
	}

	public void setScoreLevelArray(int[] scoreLevelArray) {
		this.scoreLevelArray = scoreLevelArray;
	}

	public void setOrderContacter(Ordercontacter orderContacter) {
		this.orderContacter = orderContacter;
	}

	public List<EachProductInfoVO> getEachProductInfoVOList() {
		return eachProductInfoVOList;
	}

	public void setEachProductInfoVOList(List<EachProductInfoVO> eachProductInfoVOList) {
		this.eachProductInfoVOList = eachProductInfoVOList;
	}
}

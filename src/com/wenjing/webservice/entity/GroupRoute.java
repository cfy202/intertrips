/**
 * 
 */
package com.wenjing.webservice.entity;

/**
 * 类说明
 * @author xiejin
 * @date 2016-3-10 
 * @date 2016-3-10 下午3:34:49
 */
public class GroupRoute {
    private int dayNum;					//线路是第几天
    
	private String routeName;			//线路名称
	
	private String routeDescribeForEn;	//中文线路描述		
	
	private String routeDescribeForUs;	//英文线路描述	
	
	private String hotel;				//追随的酒店信息

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteDescribeForEn() {
		return routeDescribeForEn;
	}

	public void setRouteDescribeForEn(String routeDescribeForEn) {
		this.routeDescribeForEn = routeDescribeForEn;
	}

	public String getRouteDescribeForUs() {
		return routeDescribeForUs;
	}

	public void setRouteDescribeForUs(String routeDescribeForUs) {
		this.routeDescribeForUs = routeDescribeForUs;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

}

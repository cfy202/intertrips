/**
 * 
 */
package com.wenjing.entity;

import java.math.BigDecimal;

/**
 * @author Jared
 *
 * May 20, 2015
 */
public class Coordinate {
	
	//id
	private String id;
	
	//x坐标
	private BigDecimal xAxis;
	
	//y坐标
	private BigDecimal yAxis;
	
	//目的地ID
	private String destinationId;
	
	//景点ID
	private String attractionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getxAxis() {
		return xAxis;
	}

	public void setxAxis(BigDecimal xAxis) {
		this.xAxis = xAxis;
	}

	public BigDecimal getyAxis() {
		return yAxis;
	}

	public void setyAxis(BigDecimal yAxis) {
		this.yAxis = yAxis;
	}

	public String getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}

	public String getAttractionId() {
		return attractionId;
	}

	public void setAttractionId(String attractionId) {
		this.attractionId = attractionId;
	}
}

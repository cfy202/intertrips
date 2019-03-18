package com.wenjing.vo;

/**
 * 房间住客人情况
 * 
 * @author Jared
 *
 * Jun 4, 2015
 */
public class RoomInfo {
	
	//房间号
	private int roomNum;
	
	//大人数量
	private int adultNum;
	
	//儿童数量
	private int childNum;
	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(int adultNum) {
		this.adultNum = adultNum;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}
}

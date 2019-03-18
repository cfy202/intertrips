package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.AirportPickUp;

public interface AirportPickUpMapper {
    int deleteByPrimaryKey(String id);

    int insert(AirportPickUp record);

    int insertSelective(AirportPickUp record);

    AirportPickUp selectByPrimaryKey(String id);

    int updateByPrimaryKey(AirportPickUp record);

	int batchAdd(List<AirportPickUp> aiList);

	List<AirportPickUp> findAllApickUpsBytourId(String tourlineId);

	int deleteAirportBytourlineId(String tourlineid);
}
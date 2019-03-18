package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Coordinate;

public interface CoordinateMapper {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(Coordinate record);

    Coordinate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Coordinate record);
    
	List<Coordinate> findAll();
	
	Coordinate findByDestinationId(String destinationId);
	
	Coordinate findByAttractionId(String attractionId);
	
	/**
	 * 根据线路ID加载目的地坐标
	 * 
	 * @param tourlineId
	 * @return
	 */
	List<Coordinate> findDesCoordinatesByTourlineId(String tourlineId);
	
	/**
	 * 根据目的地坐标加载景点坐标
	 * 
	 * @param destinationId
	 * @return
	 */
	List<Coordinate> findAttrCoordinatesByDesId(String destinationId);
}
package com.wenjing.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.PreBookingOfAgent;


public interface PreBookingOfAgentMapper {
    int deleteByPrimaryKey(String id);

    int insert(PreBookingOfAgent record);

    int insertSelective(PreBookingOfAgent record);

    PreBookingOfAgent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PreBookingOfAgent record);

    int updateByPrimaryKey(PreBookingOfAgent record);
    
    int getTotalNumber(Map<String,Object> parametersMap);
    
    List<PreBookingOfAgent> getOrdersByPage(Map<String,Object> parametersMap);
    
    PreBookingOfAgent findDetailById(String id);
    
    PreBookingOfAgent selectAllById(String id);
    
    List<PreBookingOfAgent> findByIds(@Param("ids")String[] ids);
    
    List<PreBookingOfAgent> findSynchronizeInfoByIds(@Param("ids")String[] ids);
    
    PreBookingOfAgent getTotalCollectionAndOrderNumber(Map<String,Object> parametersMap);
}
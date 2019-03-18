package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Iata;

public interface IataMapper {
    int deleteByPrimaryKey(String iatacode);

    int insert(Iata record);

    int insertSelective(Iata record);

    Iata selectByPrimaryKey(String iatacode);

    int updateByPrimaryKeySelective(Iata record);

    int updateByPrimaryKey(Iata record);
    
    List<Iata> selectByCityName(String cityName);
}
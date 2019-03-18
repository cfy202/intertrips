package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Couponsactivity;

public interface CouponsactivityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Couponsactivity record);

    int insertSelective(Couponsactivity record);

    Couponsactivity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Couponsactivity record);

    int updateByPrimaryKey(Couponsactivity record);
    
    List<Couponsactivity> findByUstatus(@Param("usestatus")Integer usestatus,@Param("couponseid")String couponseid);
    
    Couponsactivity findByCode(String code);
}
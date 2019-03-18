package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Couponslevel;

public interface CouponslevelMapper {
    int deleteByPrimaryKey(String id);

    int insert(Couponslevel record);

    int insertSelective(Couponslevel record);

    Couponslevel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Couponslevel record);

    int updateByPrimaryKey(Couponslevel record);
    
    List<Couponslevel> findByCouponseId(String couponseId);
    
    Couponslevel findByCode(String code);
}
package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Couponse;

public interface CouponseMapper {
    int deleteByPrimaryKey(String id);

    int insert(Couponse record);

    int insertSelective(Couponse record);

    Couponse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Couponse record);

    int updateByPrimaryKey(Couponse record);
    
    List<Couponse> findAllByCostNumber(List<String> costnumber);
    
    int getMaxSort(@Param("costnumber")String costnumber);
    
    //根据线路ID和用户ID查询出couponse的List
    List<Couponse> findByTourlineIdAndMemberId(@Param("productid")String productid,@Param("memberId")String memberId,@Param("costnumber")String costnumber);
}
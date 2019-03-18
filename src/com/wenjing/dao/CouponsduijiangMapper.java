package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Couponsduijiang;

public interface CouponsduijiangMapper {
    int deleteByPrimaryKey(String id);

    int insert(Couponsduijiang record);

    int insertSelective(Couponsduijiang record);

    Couponsduijiang selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Couponsduijiang record);

    int updateByPrimaryKey(Couponsduijiang record);
    
    List<Couponsduijiang> findByCouponseId(@Param("couponseid")String couponseid,@Param("usestatus")Integer usestatus);
    
    Couponsduijiang selectIp(String ip);
    
    List<Couponsduijiang> findBymailstatus (@Param("emailstatus")Integer emailstatus);
    
    Couponsduijiang findWithExchange(@Param("costnumber") String costnumber,@Param("code")String code);
    
    List<Couponsduijiang> findByMemberId(String memberId);
}
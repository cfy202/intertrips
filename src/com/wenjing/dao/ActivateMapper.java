package com.wenjing.dao;

import com.wenjing.entity.Activate;

public interface ActivateMapper {
    int deleteByPrimaryKey(String id);

    int insert(Activate record);

    int insertSelective(Activate record);

    Activate selectByPrimaryKey(String id);

    int updateByPrimaryKey(Activate record);

	Activate findByAuthcode(String authcode);

	int deleteFailureAuthcode(int nowTime);
	
	int deleteActivateByMemberId(String memberid);
    
}
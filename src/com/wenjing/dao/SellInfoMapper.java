package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.SellInfo;

public interface SellInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SellInfo record);

    int insertSelective(SellInfo record);

    SellInfo selectByPrimaryKey(String id);

    int updateByPrimaryKey(SellInfo record);

	List<SellInfo> findSellInfo(SellInfo sellInfo);

	int updateSellNumById(String id);
}
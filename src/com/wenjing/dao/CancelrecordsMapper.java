package com.wenjing.dao;

import com.wenjing.entity.Cancelrecords;

public interface CancelrecordsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Cancelrecords record);

    int insertSelective(Cancelrecords record);

    Cancelrecords selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Cancelrecords record);

    int updateByPrimaryKey(Cancelrecords record);
    
    Cancelrecords selectByOrdersId(String ordersId);
}
package com.wenjing.dao;

import com.wenjing.entity.CreditCardInfo;

public interface CreditCardInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CreditCardInfo record);

    int insertSelective(CreditCardInfo record);

    CreditCardInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditCardInfo record);

    int updateByPrimaryKey(CreditCardInfo record);
    
    CreditCardInfo selectByOrdersId(String id);
}
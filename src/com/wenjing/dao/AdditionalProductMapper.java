package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.AdditionalProduct;

public interface AdditionalProductMapper {
    int insert(AdditionalProduct record);

    int insertSelective(AdditionalProduct record);
    
    int insertBatch(@Param("additionalProductList")List<AdditionalProduct> additionalProductList);
}
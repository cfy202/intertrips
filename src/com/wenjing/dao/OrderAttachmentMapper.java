package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.OrderAttachment;

public interface OrderAttachmentMapper {

    Integer insert(OrderAttachment record);
    
    List<OrderAttachment> findByOrdersId(@Param("ordersId")String ordersId);
    
}
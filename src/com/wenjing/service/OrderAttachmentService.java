
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.OrderAttachmentMapper;
import com.wenjing.entity.OrderAttachment;

/**
 * 产品标签后台管理
 * @author sevens
 *
 */
@Service
public class OrderAttachmentService {

	
	@Resource
	private OrderAttachmentMapper orderAttachmentMapper;
	
	@Transactional
	public void save(OrderAttachment orderAttachment){
		orderAttachmentMapper.insert(orderAttachment);
	}
	
	@Transactional(readOnly=true)
	public List<OrderAttachment> findByOrdersId(String ordersId){
		return orderAttachmentMapper.findByOrdersId(ordersId);
	}
}

package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenjing.dao.OrderstatusMapper;
import com.wenjing.entity.Orderstatus;

@Service("orderStatusServiceImpl")
public class OrderStatusService {
	
	@Autowired
	private OrderstatusMapper orderStatusMapper;
	
	public List<Orderstatus> findAll(){
		return orderStatusMapper.findAll();
	} 
}

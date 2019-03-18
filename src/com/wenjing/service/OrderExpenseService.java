package com.wenjing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenjing.dao.OrderexpenseMapper;
import com.wenjing.entity.Orderexpense;

@Service
public class OrderExpenseService {
	
	@Autowired
	private OrderexpenseMapper orderExpenseMapper;	
	
	public List<Orderexpense> findByOrderId(String orderId){
		return orderExpenseMapper.findByOrderId(orderId);
	}
}

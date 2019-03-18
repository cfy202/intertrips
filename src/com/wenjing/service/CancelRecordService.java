package com.wenjing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.CancelrecordsMapper;
import com.wenjing.entity.Cancelrecords;

/**
 * Service - 取消订单记录
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("CancelRecordServiceImpl")
public class CancelRecordService {

	@Autowired
	private CancelrecordsMapper cancelRecordsMapper;

	@Transactional
	public void save(Cancelrecords cancelrecords){
		cancelRecordsMapper.insert(cancelrecords);
	}
	
	@Transactional(readOnly=true)
	public Cancelrecords findByOrders(String ordersId){
		return cancelRecordsMapper.selectByOrdersId(ordersId);
	}
}
package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenjing.dao.PreBookingOfAgentMapper;
import com.wenjing.dao.TourdateMapper;
import com.wenjing.entity.PreBookingOfAgent;
import com.wenjing.webservice.BookErpOrdersService;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class PreBookingOfAgentService {
	@Autowired
	private BookErpOrdersService bookErpOrdersService;
	
	@Autowired
	private PreBookingOfAgentMapper preBookingOfAgentMapper;
	@Autowired
	private TourdateMapper tourdateMapper;
	
	public void save(PreBookingOfAgent preBookingOfAgent){
		preBookingOfAgentMapper.insert(preBookingOfAgent);
	}
	
	public int getTotalNumber(Map<String,Object> parametersMap){
		return preBookingOfAgentMapper.getTotalNumber(parametersMap);
	}
	
	public List<PreBookingOfAgent> getOrdersByPage(Map<String,Object> parametersMap){
		return preBookingOfAgentMapper.getOrdersByPage(parametersMap);
	}
	
	public PreBookingOfAgent getTotalCollectionAndOrderNumber(Map<String,Object> parametersMap){
		return preBookingOfAgentMapper.getTotalCollectionAndOrderNumber(parametersMap);
	}
	
	public PreBookingOfAgent findDetailById(String id){
		return preBookingOfAgentMapper.findDetailById(id);
	}
	
	public List<PreBookingOfAgent> findByIds(String[] ids){
		return preBookingOfAgentMapper.findByIds(ids);
	}
	
	public void cancel(String id){
		PreBookingOfAgent preBookingOfAgent =  preBookingOfAgentMapper.findDetailById(id);
		tourdateMapper.updateStore(preBookingOfAgent.getTourdateid(), -(preBookingOfAgent.getPax() + 0));
		preBookingOfAgentMapper.deleteByPrimaryKey(id);
	} 
	
	public int batchSynchronizeToErp(String[] ids){
		List<PreBookingOfAgent> preBookingOfAgentList = preBookingOfAgentMapper.findSynchronizeInfoByIds(ids);
		boolean noProduct = false;
		String bookNo;
		for(PreBookingOfAgent preBookingOfAgent:preBookingOfAgentList){
			bookNo = null;
			bookNo = bookErpOrdersService.bookingToErp(preBookingOfAgent);
			if(bookNo != null && !"notExists".equals(bookNo)){
				preBookingOfAgent.setSynchronizeNo(bookNo);
				preBookingOfAgent.setIsSynchronize(true);
				preBookingOfAgentMapper.updateByPrimaryKeySelective(preBookingOfAgent);
			}else{
				noProduct = true;
			}
		}
		if(noProduct){
			return -1;
		}
		return 0;
	}
}
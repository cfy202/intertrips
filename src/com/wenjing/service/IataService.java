package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.IataMapper;
import com.wenjing.entity.Iata;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class IataService {
	@Autowired
	private IataMapper iataMapper;
		
	@Transactional(readOnly=true)
	public List<Iata> getIataListByCityName(String cityname){
		return iataMapper.selectByCityName(cityname);
	}	
}
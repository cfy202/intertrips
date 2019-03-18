package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenjing.dao.AgentCodeMapper;
import com.wenjing.entity.AgentCode;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class AgentCodeService {
	@Autowired
	private AgentCodeMapper agentCodeMapper;
	
	public AgentCode findById(String id){
		return agentCodeMapper.selectByPrimaryKey(id);
	}
	
	public AgentCode findByLowerCode(String code){
		return agentCodeMapper.selectByLowerCode(code);
	}
}
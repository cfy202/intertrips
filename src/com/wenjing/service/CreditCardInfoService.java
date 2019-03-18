package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.CreditCardInfoMapper;
import com.wenjing.entity.CreditCardInfo;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class CreditCardInfoService {
	
	@Autowired
	private CreditCardInfoMapper creditCardInfoMapper;
	
	@Transactional
	public void save(CreditCardInfo creditCardInfo) {
		creditCardInfoMapper.insertSelective(creditCardInfo);
	}

	@Transactional
	public int update(CreditCardInfo creditCardInfo) {
		return creditCardInfoMapper.updateByPrimaryKeySelective(creditCardInfo);
	}

	@Transactional
	public void delete(String id) {
		creditCardInfoMapper.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public CreditCardInfo findById(String id) {
		return creditCardInfoMapper.selectByPrimaryKey(id);
	}
}
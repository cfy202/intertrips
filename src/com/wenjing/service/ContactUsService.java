package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.ContactUsMapper;
import com.wenjing.entity.ContactUs;

@Service
public class ContactUsService {
	
	@Autowired
	private ContactUsMapper contactUsMapper;
	
	/**
	 * 保存
	 * @param contactUs
	 */
	@Transactional
	public void save(ContactUs contactUs){
		contactUsMapper.insertSelective(contactUs);
	}
	
	/**
	 * 根据costnumber查询contactus的信息
	 * @param costNumberList
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ContactUs> list(List<String> costNumberList){
		return contactUsMapper.selectByCostNumbers(costNumberList);
	}
	
	/**
	 * 修改
	 * 
	 * @param contactUs
	 */
	@Transactional
	public void update(ContactUs contactUs){
		contactUsMapper.updateByPrimaryKey(contactUs);
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	public int delete(String id){
		return contactUsMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public ContactUs findById(String id){
		return contactUsMapper.selectByIdWithCost(id);
	}
}

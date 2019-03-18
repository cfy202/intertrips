package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.OfficeContactsMapper;
import com.wenjing.entity.OfficeContacts;

@Service
public class OfficeContactsService {
	
	@Autowired
	private OfficeContactsMapper officeContactsMapper;
	
	/**
	 * 获取所有的办公室联系信息
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<OfficeContacts> findAll(){
		return officeContactsMapper.selectAll();
	}
	
	/**
	 * 根据Id获取办公室联系信息
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public OfficeContacts findById(String id){
		return officeContactsMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据运营中心加载所有的办公室联系信息
	 * 
	 * @param costnumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<OfficeContacts> findByCostnumber(String costnumber){
		return officeContactsMapper.selectByCostnumber(costnumber);
	}
	
	/**
	 * 根据条件查询办公室联系方式
	 * 
	 * @param officeContacts
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<OfficeContacts> findByConditions(OfficeContacts officeContacts){
		return officeContactsMapper.selectByConditions(officeContacts);
	}
	
	/**
	 * 保存
	 * @param officeContacts
	 */
	@Transactional
	public void save(OfficeContacts officeContacts){
		officeContactsMapper.insert(officeContacts);
	}
	
	/**
	 * 修改
	 * @param officeContacts
	 */
	@Transactional
	public void update(OfficeContacts officeContacts){
		officeContactsMapper.updateSelective(officeContacts);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@Transactional
	public void deleteByPrimaryKey(String id){
		officeContactsMapper.deleteByPrimaryKey(id);
	}
}

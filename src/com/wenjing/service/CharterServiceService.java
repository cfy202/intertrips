package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.CharterServiceMapper;
import com.wenjing.entity.CharterService;

/**
 * Service - 包车服务
 * 
 * @author Jared
 *
 */
@Service
public class CharterServiceService {
	
	@Autowired
	private CharterServiceMapper charterServiceMapper;
	
	/**
	 * 查出所有的包车服务
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<CharterService> findAll(){
		return charterServiceMapper.findAll();
	}
	
	/**
	 * 根据Id查出包车服务
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public CharterService findById(String id){
		return charterServiceMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 保存包车服务
	 */
	@Transactional
	public void save(CharterService charterService){
		charterServiceMapper.insertSelective(charterService);
	}
	
	/**
	 * 更新包车服务
	 * 
	 * @param blog
	 */
	@Transactional
	public void update(CharterService charterService){
		charterServiceMapper.updateByPrimaryKeySelective(charterService);
	}
	
	/**
	 * 根据id删除包车服务
	 */
	@Transactional
	public void deleteById(String id){
		charterServiceMapper.deleteByPrimaryKey(id);
	}
}

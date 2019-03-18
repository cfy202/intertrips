package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.AttractiontypeMapper;
import com.wenjing.entity.Attractiontype;

/**
 * 类说明		景点类型管理
 * @author xiejin
 * @date 2015-4-24
 */
@Service
public class AttractiontypeService {

	
	@Resource
	private AttractiontypeMapper attractiontypeMapper;
	
	/**
	 * 根据costnumber查询所有attractiontype 
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<Attractiontype> findAll(){
		return attractiontypeMapper.findAll();
	}
	
	/**
	 * 删除attractiontype
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return attractiontypeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询attractiontype
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public Attractiontype findById(String id) {
		return attractiontypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存attractiontype
	 * @param attractiontype
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Attractiontype attractiontype) {
		return attractiontypeMapper.insert(attractiontype);
	}
	
	/**
	 * 修改attractiontype
	 * @param attractiontype
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Attractiontype attractiontype) {
		return attractiontypeMapper.updateByPrimaryKey(attractiontype);
	}
	
	/**
	 * 查询最大sort
	 * @return
	 * xiejin
	 */
	@Transactional
	public int getOrderId(){
		return attractiontypeMapper.getMaxSort();
	}
}

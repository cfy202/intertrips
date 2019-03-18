
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.SetoperaterMapper;
import com.wenjing.dao.ShipCompanyMapper;
import com.wenjing.entity.Setoperater;
import com.wenjing.entity.ShipCompany;

/**
 * 说明 后台操作管理
 * @author sevens
 *
 */
@Service
public class ShipcompanyService {

	
	@Resource
	private ShipCompanyMapper shipcompanyMapper;
	
	/**
	 * 查询所有setoperater 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<ShipCompany> findAll(){
		return shipcompanyMapper.findAll();
	}
	
	/**
	 * 根据id删除setoperater
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		return shipcompanyMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询setoperater
	 * @param id
	 * @return
	 */
	@Transactional
	public ShipCompany findById(String id) {
		return shipcompanyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存setoperater
	 * @param setoperater
	 * @return
	 */
	@Transactional
	public int save(ShipCompany shipcompany) {
		return shipcompanyMapper.insert(shipcompany);
	}
	
	/**
	 * 修改region
	 * @param tree
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(ShipCompany shipcompany) {
		return shipcompanyMapper.updateByPrimaryKeySelective(shipcompany);
	}
	
	/**
	 * 查询最大sort
	 * @return
	 * xiejin
	 */
	@Transactional
	public int getOrderId(){
		return shipcompanyMapper.getMaxSort();
	}
}

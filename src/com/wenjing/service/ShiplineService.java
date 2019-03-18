
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.ShipLineMapper;
import com.wenjing.entity.ShipLine;

/**
 * 游船航线管理
 * @author sevens
 *
 */
@Service
public class ShiplineService {

	
	@Resource
	private ShipLineMapper shiplineMapper;
	
	/**
	 * 查询所有shipline 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<ShipLine> findAll(){
		return shiplineMapper.findAll();
	}
	
	/**
	 * 根据id删除shipline
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		return shiplineMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询shipline
	 * @param id
	 * @return
	 */
	@Transactional
	public ShipLine findById(String id) {
		return shiplineMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存shipline
	 * @param shipline
	 * @return
	 */
	@Transactional
	public int save(ShipLine shipline) {
		return shiplineMapper.insert(shipline);
	}
	
	/**
	 * 修改region
	 * @param tree
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(ShipLine shipline) {
		return shiplineMapper.updateByPrimaryKeySelective(shipline);
	}
	
	
}

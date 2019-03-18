
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.SetoperaterMapper;
import com.wenjing.entity.Setoperater;

/**
 * 说明 后台操作管理
 * @author sevens
 *
 */
@Service
public class SetoperaterService {

	
	@Resource
	private SetoperaterMapper setoperaterMapper;
	
	/**
	 * 查询所有setoperater 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<Setoperater> findAll(){
		return setoperaterMapper.findAll();
	}
	
	/**
	 * 根据id删除setoperater
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		return setoperaterMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询setoperater
	 * @param id
	 * @return
	 */
	@Transactional
	public Setoperater findById(String id) {
		return setoperaterMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存setoperater
	 * @param setoperater
	 * @return
	 */
	@Transactional
	public int save(Setoperater setoperater) {
		return setoperaterMapper.insert(setoperater);
	}
	
	/**
	 * 修改region
	 * @param tree
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Setoperater setoperater) {
		return setoperaterMapper.updateByPrimaryKeySelective(setoperater);
	}
	
	/**
	 * 查询最大sort
	 * @return
	 * xiejin
	 */
	@Transactional
	public int getOrderId(){
		return setoperaterMapper.getMaxSort();
	}
}

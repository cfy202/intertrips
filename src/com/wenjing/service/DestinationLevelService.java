/**
 * 
 */
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.DestinationLevelMapper;
import com.wenjing.entity.DestinationLevel;

/**
 * 类说明
 * @author xiejin
 * @date 2015-9-25 
 * @date 2015-9-25 下午3:42:20
 */
@Service
public class DestinationLevelService {

	@Resource
	private DestinationLevelMapper destinationLevelMapper;
	
	/**
	 * 根据costnumber查询所有DestinationLevel 
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<DestinationLevel> findAll(){
		return destinationLevelMapper.findAll();
	}
	
	/**
	 * 删除DestinationLevel
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return destinationLevelMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询DestinationLevel
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public DestinationLevel findById(String id) {
		return destinationLevelMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存DestinationLevel
	 * @param DestinationLevel
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(DestinationLevel destinationLevel) {
		return destinationLevelMapper.insert(destinationLevel);
	}
	
	/**
	 * 修改DestinationLevel
	 * @param DestinationLevel
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(DestinationLevel destinationLevel) {
		return destinationLevelMapper.updateByPrimaryKey(destinationLevel);
	}



}

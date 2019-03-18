/**
 * 
 */
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.AttractionLevelMapper;
import com.wenjing.entity.AttractionLevel;

/**
 * 类说明
 * @author xiejin
 * @date 2015-9-25 
 * @date 2015-9-25 下午2:48:30
 */
@Service
public class AttractionLevelService {

	@Resource
	private AttractionLevelMapper attractionLevelMapper;
	
	/**
	 * 根据costnumber查询所有AttractionLevel 
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<AttractionLevel> findAll(){
		return attractionLevelMapper.findAll();
	}
	
	/**
	 * 删除AttractionLevel
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return attractionLevelMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询AttractionLevel
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public AttractionLevel findById(String id) {
		return attractionLevelMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存AttractionLevel
	 * @param AttractionLevel
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(AttractionLevel attractionLevel) {
		return attractionLevelMapper.insert(attractionLevel);
	}
	
	/**
	 * 修改AttractionLevel
	 * @param AttractionLevel
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(AttractionLevel attractionLevel) {
		return attractionLevelMapper.updateByPrimaryKey(attractionLevel);
	}

}

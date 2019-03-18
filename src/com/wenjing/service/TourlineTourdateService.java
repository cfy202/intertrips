/**
 * 
 */
package com.wenjing.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.TourlineTourdateMapper;
import com.wenjing.entity.TourlineTourdate;

/**
 * 类说明
 * @author xiejin
 * @date 2015-9-11 
 * @date 2015-9-11 下午3:03:17
 */
@Service
public class TourlineTourdateService {
	
	@Resource
	private TourlineTourdateMapper tourlineTourdateMapper;
	
	/**
	 * @Title: Insert
	 * @Description: 添加
	 * @param tourlineTourdate
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional
	public int Insert(TourlineTourdate tourlineTourdate){
		return tourlineTourdateMapper.insert(tourlineTourdate);
	}
	
	/**
	 * @Title: delete
	 * @Description: 删除表所有数据
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional
	public int delete(){
		return tourlineTourdateMapper.delete();
	};
}

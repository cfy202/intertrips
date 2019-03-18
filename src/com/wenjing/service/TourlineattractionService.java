/**
 * 
 */
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.TourlineattractionMapper;
import com.wenjing.entity.Tourlineattraction;

/**
 * 类说明
 * @author xiejin
 * @date 2015-8-27 
 * @date 2015-8-27 下午3:05:54
 */
@Service
public class TourlineattractionService {
	
	@Resource
	TourlineattractionMapper tourlineattractionMapper;
	
	
	/**
	 * @Title: selectByTourlineid
	 * @Description: 根据tourlineID查询相关景点
	 * @param tourlineId
	 * @return    
	 * @return List<Tourlineattraction>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public List<Tourlineattraction> selectByTourlineid(String tourlineId){
		return tourlineattractionMapper.selectByTourlineid(tourlineId);
	};

}

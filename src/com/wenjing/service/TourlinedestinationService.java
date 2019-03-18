/**
 * 
 */
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.TourlinedestinationMapper;
import com.wenjing.entity.Tourlinedestination;

/**
 * 类说明
 * @author xiejin
 * @date 2015-8-27 
 * @date 2015-8-27 下午3:26:30
 */
@Service
public class TourlinedestinationService {

	@Resource
	private TourlinedestinationMapper tourlinedestinationMapper;
	
	
	@Transactional(readOnly=true)
	public List<Tourlinedestination> selectByTourlineid(String tourLineId){
		return tourlinedestinationMapper.selectByTourlineid(tourLineId);
	};
}

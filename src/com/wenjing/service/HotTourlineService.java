package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.HotTourlineMapper;
import com.wenjing.entity.HotTourline;
import com.wenjing.util.Tools;

/**
 * 说明 后台产品分类管理
 * 
 * @author sevens
 * 
 */
@Service
public class HotTourlineService {

	@Resource
	private HotTourlineMapper hottourlineMapper;


	/**
	 * 根据costnumber和线路Id删除Hottourline
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void delete(String costnumber,String tourlineId) {
		hottourlineMapper.deleteBycostnumberAnaTourlineId(costnumber,tourlineId);
		
	}

	
	/**
	 * 保存tree
	 * @author Sevens
	 * 时间2015-8-21
	 * @param hotTourline
	 * @return
	 */
	@Transactional
	public void save(String costNumber,String tourlineId) {
		HotTourline hotTourline = new HotTourline();
		hotTourline.setId(Tools.getUUID());
		hotTourline.setTourlineId(tourlineId);
		hotTourline.setCostNumber(costNumber);
		hotTourline.setAddTime(Tools.getDtimestemp(Tools.getDtime()));
		hottourlineMapper.insert(hotTourline);
		
	}
    /**
     * 根据销售中心Id查询热卖线路
     * @author Sevens
     * 时间2015-8-21
     * @param costNumber
     * @return
     */
	@Transactional(readOnly=true)
	public List<HotTourline> findByCostnumber(String costNumber){
		return hottourlineMapper.findByCostnumber(costNumber);
	}
	

}

package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.HotTourlineMapper;
import com.wenjing.dao.IndexshowtourlineMapper;
import com.wenjing.entity.HotTourline;
import com.wenjing.entity.Indexshowtourline;
import com.wenjing.util.Tools;

/**
 * 说明 后台产品分类管理
 * 
 * @author sevens
 * 
 */
@Service
public class IndexShowTourlineService {

	@Resource
	private IndexshowtourlineMapper indexshowtourlineMapper;


	/**
	 * 根据costnumber和线路Id删除indexshowtourlineMapper
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void delete(String costnumber,String tourlineId) {
		indexshowtourlineMapper.deleteBycostnumberAnaTourlineId(costnumber,tourlineId);
		
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
		Indexshowtourline indexshowtourline = new Indexshowtourline();
		indexshowtourline.setId(Tools.getUUID());
		indexshowtourline.setTourlineid(tourlineId);
		indexshowtourline.setCostnumber(costNumber);
		indexshowtourline.setAddtime(Tools.getDtimestemp(Tools.getDtime()));
		indexshowtourlineMapper.insert(indexshowtourline);
		
	}
    /**
     * 根据销售中心Id查询首页显示线路
     * @author Sevens
     * 时间2015-8-21
     * @param costNumber
     * @return
     */
	@Transactional(readOnly=true)
	public List<Indexshowtourline> findByCostnumber(String costNumber){
		return indexshowtourlineMapper.findByCostnumber(costNumber);
	}
	

}

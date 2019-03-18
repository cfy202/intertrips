package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.HotTourlineMapper;
import com.wenjing.dao.ShowtourlineMapper;
import com.wenjing.entity.HotTourline;
import com.wenjing.entity.Showtourline;
import com.wenjing.util.Tools;

/**
 * 说明 后台产品分类管理
 * 
 * @author sevens
 * 
 */
@Service
public class ShowTourlineService {

	@Resource
	private ShowtourlineMapper showtourlineMapper;


	/**
	 * 根据costnumber和线路Id删除showtourline
	 * @author Sevens
	 * @param id
	 * @return
	 */
	@Transactional
	public void delete(String costnumber,String tourlineId) {
		showtourlineMapper.deleteBycostnumberAnaTourlineId(costnumber,tourlineId);
		
	}

	
	/**
	 * 保存showtourline
	 * @author Sevens
	 * 时间2015-8-21
	 * @param tourlineId
	 * @return
	 */
	@Transactional
	public void save(String costNumber,String tourlineId) {
		Showtourline showtourline = new Showtourline();
		showtourline.setId(Tools.getUUID());
		showtourline.setTourlineid(tourlineId);
		showtourline.setCostnumber(costNumber);
		showtourline.setAddtime(Tools.getDtimestemp(Tools.getDtime()));
		showtourlineMapper.insert(showtourline);
		
	}
    /**
     * 根据销售中心Id查询热卖线路
     * @author Sevens
     * 时间2015-8-21
     * @param costNumber
     * @return
     */
	@Transactional(readOnly=true)
	public List<Showtourline> findByCostnumber(String costNumber,Integer isCreate){
		return showtourlineMapper.findByCostnumber(costNumber,isCreate);
	}
	/**
	 * @author Sevens
	 * 时间2015-8-31
	 * @param costnumber
	 * @param tourlineId
	 * @return
	 */
	@Transactional(readOnly=true)
	public int findCountWithCostnumberAnaTourline(String costnumber,String tourlineId){
		return showtourlineMapper.findCountWithCostnumberAnaTourline(costnumber, tourlineId);
	}
  /**
   * @author Sevens
   * 时间2015-8-31	
   * @param costnumber
   * @param tourlineId
   * @param isCreate
   * @return
   */
  @Transactional
  public int updateByisCreate(String costnumber,String tourlineId,Integer isCreate){
		return showtourlineMapper.updateByisCreate(costnumber, tourlineId, isCreate);
	}

	/**
	 * @author Sevens
	 * 时间2015-8-31
	 * @return
	 */
	@Transactional
	public int resetIsCreate(List<String> tourlineIds){
		return showtourlineMapper.resetIsCreate(tourlineIds);
	}


}

package com.wenjing.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.SelfpayMapper;
import com.wenjing.dao.SelfpaycurrencyMapper;
import com.wenjing.entity.Selfpay;
import com.wenjing.entity.Selfpaycurrency;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-5-4 下午6:21:29
 *  类说明 : 自费项目 Service
 */
@Service
public class SelfPayService {

	@Resource
	private SelfpayMapper selfpayMapper;
	
	@Resource
	private SelfpaycurrencyMapper selfpaycurrencyMapper;
	
	@Resource
	private HttpServletRequest request;

	/**
	 * 根据costnum查询全部自费项目
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Selfpay> findAllByCostNumber(List<String> costnumlist) {
		return selfpayMapper.findAllByCostNumber(costnumlist);
	}

	/**
	 * 获取当前sort最大值
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMaxSort(String costnumber) {
		return selfpayMapper.getMaxSort(costnumber);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Selfpay findById(String id) {
		return selfpayMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改
	 * 
	 * @param selfpay
	 * @return
	 */
	@Transactional
	public int update(Selfpay selfpay) {
		int success = selfpayMapper.updateByPrimaryKeySelective(selfpay); //更新selfpay
		return success;
	}

	/**
	 * 新增
	 * 
	 * @param selfpay
	 * @return
	 */
	@Transactional
	public int insert(Selfpay selfpay) {
		String id = Tools.getUUID();
		selfpay.setId(id);
		int success = selfpayMapper.insertSelective(selfpay);
		return success;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		int deleteAssociation = selfpaycurrencyMapper.deleteBySelfpayId(id);//删除selfpay关联表数据
		int success = 0;
	//	if(deleteAssociation > 0){
			success = selfpayMapper.deleteByPrimaryKey(id);
	//	}
		return success;
	}

	/**
	 * 根据selfpayid查询价格
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Selfpaycurrency> findPriceBySelfpayId(String id) {
		return selfpaycurrencyMapper.findPriceBySelfpayId(id);
	}

	public List<Selfpay> findAll() {
		return selfpayMapper.findAll();
	}

}


package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.CostpaymentMapper;
import com.wenjing.entity.Costpayment;

/**
 * 类说明		costpayment后台管理service
 * @author xiejin
 * @date 2015-7-27 
 * @date 2015-7-27 下午4:28:22
 */
@Service
public class CostpaymentService {
	@Resource
	private CostpaymentMapper costpaymentMapper;
	
	/**
	 * @Title: findByCostnumber
	 * @Description: 根据costnumber查询对应支付账户
	 * @param costnumber
	 * @return    
	 * @return List<Costpayment>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public List<Costpayment> findByCostnumber(String costnumber){
		return costpaymentMapper.findByCostnumber(costnumber);
	}
	
	/**
	 * 删除costpayment
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return costpaymentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询costpayment
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public Costpayment findById(String id) {
		return costpaymentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存costpayment
	 * @param costpayment
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Costpayment costpayment) {
		return costpaymentMapper.insert(costpayment);
	}
	
	/**
	 * 修改costpayment
	 * @param costpayment
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Costpayment costpayment) {
		return costpaymentMapper.updateByPrimaryKeySelective(costpayment);
	}
	
}

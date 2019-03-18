package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.CouponslevelMapper;
import com.wenjing.entity.Couponslevel;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class CouponslevelService {

	@Autowired
	private CouponslevelMapper couponslevelMapper;
	
	@Resource
	private HttpServletRequest request;

    /**
     * @author Sevens	
     * @param couponslevl
     * 时间2015-5-21
     */
	@Transactional
	public void save(Couponslevel couponslevl) {
		couponslevelMapper.insertSelective(couponslevl);
	}
	/**
     * @author Sevens	
     * @param couponslevel
     * 时间2015-5-21
     */
	@Transactional
	public int update(Couponslevel couponslevl) {
		return couponslevelMapper.updateByPrimaryKeySelective(couponslevl);
	}
	/**
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
	@Transactional
	public void delete(String id) {
		couponslevelMapper.deleteByPrimaryKey(id);
	}
	/**
     * @author Sevens	
     * @param coupons
     * 时间2015-5-21
     */
	@Transactional(readOnly = true)
	public List<Couponslevel> findAll(String couponseId) {
		return couponslevelMapper.findByCouponseId(couponseId);
	}
	/**
     * @author Sevens	
     * @param couponslevl
     * 时间2015-5-21
     */
	@Transactional(readOnly = true)
	public Couponslevel findById(String id) {
		return couponslevelMapper.selectByPrimaryKey(id);
	}
	

}
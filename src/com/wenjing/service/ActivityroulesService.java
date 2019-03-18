package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.ActivityrulesMapper;
import com.wenjing.entity.Activityrules;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class ActivityroulesService {

	@Resource
	private ActivityrulesMapper activityrulesMapper;
	@Resource
	private HttpServletRequest request;

	
	@Transactional
	public void save(Activityrules activityrules) {
		activityrulesMapper.insertSelective(activityrules);
	}

	@Transactional
	public int update(Activityrules activityrules) {
		return activityrulesMapper.updateByPrimaryKeySelective(activityrules);
	}

	@Transactional
	public void delete(String id) {
		activityrulesMapper.deleteByPrimaryKey(id);
	}

	

	@Transactional(readOnly = true)
	public Activityrules findById(String id) {
		return activityrulesMapper.selectByPrimaryKey(id);
	}

 
   
   @Transactional(readOnly=true)
   public List<Activityrules> findByCouponseId(String couponseid){
	   return activityrulesMapper.findByCouponseId(couponseid);
   }
 
}
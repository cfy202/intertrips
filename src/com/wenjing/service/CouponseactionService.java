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

import com.wenjing.dao.CouponsactivityMapper;
import com.wenjing.entity.Couponsactivity;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class CouponseactionService {

	@Autowired
	private CouponsactivityMapper couponsactivityMapper;
	
	
	@Resource
	private HttpServletRequest request;
    @Transactional(readOnly=true)
	public Couponsactivity getcouponsactivity(String couponseid) {
		// TODO Auto-generated method stub
		List<?> list = couponsactivityMapper.findByUstatus(0,couponseid);
		int randomNumber = (int) (Math.random() * list.size());
		Couponsactivity activity=(Couponsactivity)list.get(randomNumber);
		if(activity!=null){
			return activity;	
		}else{
		    return null;
		}
		
	}
    
    @Transactional
    public int updateByPrimaryKeySelective(Couponsactivity act){
    	return couponsactivityMapper.updateByPrimaryKeySelective(act);
    }
}
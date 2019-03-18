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

import com.wenjing.dao.CouponsduijiangMapper;
import com.wenjing.entity.Couponsduijiang;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class CouponseduijiangService {

	@Autowired
	private CouponsduijiangMapper couponsduijiangMapper;
	@Resource
	private HttpServletRequest request;
    
    @Transactional
    public int updateByPrimaryKeySelective(Couponsduijiang act){
    	return couponsduijiangMapper.updateByPrimaryKey(act);
    }
    
    @Transactional(readOnly=true)
    public List<Couponsduijiang> findByMemberId(String memberId){
    	return couponsduijiangMapper.findByMemberId(memberId);
    }
    
    @Transactional
    public int save(Couponsduijiang act){
    	return couponsduijiangMapper.insert(act);
    } 
    
    @Transactional(readOnly=true)
    public List<Couponsduijiang> findBymailstatus(Integer status){
    	return couponsduijiangMapper.findBymailstatus(status);
    }
    @Transactional(readOnly=true)
    public List<Couponsduijiang> findByCouponseId(String couponseid,Integer usestatus){
    	return couponsduijiangMapper.findByCouponseId(couponseid, usestatus);
    }
    @Transactional(readOnly=true)
    public Couponsduijiang findWithExchange(String costnumber,String code){
    	return couponsduijiangMapper.findWithExchange(costnumber, code);
    }
    
}
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

import com.wenjing.dao.PageMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.VisaoccupationMapper;
import com.wenjing.entity.Visaoccupation;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class VisaoccupationService {

	@Autowired
	private VisaoccupationMapper visaoccupationMapper;
	@Resource
	private PageMapper pageMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private HttpServletRequest request;

	
	@Transactional
	public void save(Visaoccupation visaoccupation) {
		visaoccupationMapper.insertSelective(visaoccupation);
	}

	@Transactional
	public int update(Visaoccupation visaoccupation) {
		return visaoccupationMapper.updateByPrimaryKeySelective(visaoccupation);
	}

	@Transactional
	public void delete(String id) {
		visaoccupationMapper.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public List<Visaoccupation> findAll(String visaId) {
		return visaoccupationMapper.findAllByVisaId(visaId);
	}

	@Transactional(readOnly = true)
	public Visaoccupation findById(String id) {
		return visaoccupationMapper.selectByPrimaryKey(id);
	}

   @Transactional(readOnly=true)
   public int getMaxSort(){
	   return visaoccupationMapper.getMaxSort();
   }
   @Transactional(readOnly=true)
   public List<Visaoccupation> findByVisaId(String visaId){
	   return visaoccupationMapper.findAllByVisaId(visaId);
   }
   /**
    * @author Sevens
    * 时间2015-7-9
    * @param visaId
    * @return
    */
   @Transactional(readOnly=true)
   public List<Visaoccupation> findByVisaType(String visaId){
	   return visaoccupationMapper.findByVisaType(visaId);
   }   
}
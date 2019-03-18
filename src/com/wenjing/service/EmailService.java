/**
 * 
 */
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.EmailMapper;
import com.wenjing.entity.Email;
import com.wenjing.util.Tools;

/**
 * 类说明		订阅邮件service层
 * @author xiejin
 * @date 2015-6-29 
 * @date 2015-6-29 下午5:55:19
 */
@Service
public class EmailService {
	
	@Resource
	private EmailMapper emailMapper;
	
	/**
	 * @Title: insert
	 * @Description: 添加订阅邮件
	 * @param email
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional
	public int insert(Email email){
		return emailMapper.insert(email);
	}
	
	/**
	 * @Title: findAll
	 * @Description: 根据运营中心查询所有邮件
	 * @param costNumber
	 * @return    
	 * @return List<Email>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public List<Email> findAll(List<String> costNumber){
		return emailMapper.findAll(costNumber);
	}
	
	/**
	 * @Title: deleteByPrimaryKey
	 * @Description: 根据id删除邮件
	 * @param id
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional
	public int deleteByPrimaryKey(String id){
		return emailMapper.deleteByPrimaryKey(id);
	}
	
	 /**
     * @Title: findByEmailAddress
     * @Description: 根据邮箱地址查询邮箱
     * @param emailAddress
     * @return    
     * @return Email    返回类型
     * @author xiejin
     */
	@Transactional(readOnly=true)
    public Email findByEmailAddress(String emailAddress){
    	return emailMapper.findByEmailAddress(emailAddress);
    }
	
	/**
	 * @Title: updateEmailStatus
	 * @Description: 取消邮件订阅
	 * @param id
	 * @param time
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional
	public int updateEmailStatus(String id){
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return emailMapper.updateEmailStatus(id, time);
	}

}

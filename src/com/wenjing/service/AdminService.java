package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.Principal;
import com.wenjing.dao.AdminMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Role;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("adminServiceImpl")
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return adminMapper.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public Admin findByUsername(String username) {
		return adminMapper.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public String findAuthorities(String id) {
		String authorities = null;
		Admin admin = adminMapper.selectByPrimaryKey(id);
		if (admin != null) {
			Role role = admin.getRoleRoleid();
			authorities = role.getOperaterstr();

		}
		return authorities;
	}

	@Transactional(readOnly = true)
	public boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return subject.isAuthenticated();
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Admin getCurrent() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return adminMapper.selectByPrimaryKey(principal.getId());
			}
		}
		return null;
	}

	@Transactional(readOnly = true)
	public String getCurrentUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	@Transactional
	public void save(Admin admin) {
		adminMapper.insertSelective(admin);
	}

	@Transactional
	public int update(Admin admin) {
		return adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Transactional
	public void delete(String id) {
		adminMapper.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public List<Admin> findAll() {
		return adminMapper.findAll();
	}

	@Transactional(readOnly = true)
	public Admin findById(String id) {
		return adminMapper.selectByPrimaryKey(id);
	}

	/**
	 * 后台用户修改登录密码
	 * @param username
	 * @param newpassword
	 * @return
	 */
	@Transactional
	public int modifyPassword(String id, String newpassword) {
		return adminMapper.modifyPassword(id,newpassword);
	}

}
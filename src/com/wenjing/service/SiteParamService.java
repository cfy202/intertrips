package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.SitparamMapper;
import com.wenjing.entity.Sitparam;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2015-4-27 下午1:12:20
 * 类说明 : 页面系统参数   Service
 */
@Service
public class SiteParamService {
	@Resource
	private SitparamMapper siteparamMapper;

	/**
	 * 系统参数列表查询
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Sitparam> findAll() {
		return siteparamMapper.findAll();
	}

	/**
	 * 修改
	 * @param siteparam
	 */
	@Transactional
	public void update(Sitparam siteparam) {
        siteparamMapper.updateByPrimaryKey(siteparam);		
	}

	/**
	 * 新增
	 * @param siteparam
	 */
	@Transactional
	public void insert(Sitparam siteparam) {
		String id = Tools.getUUID();
	    siteparam.setId(id);
	    siteparamMapper.insertSelective(siteparam);
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Transactional
	public Sitparam findById(String id) {
		return siteparamMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除
	 * @param id
	 */
	@Transactional
	public void delete(String id) {
		siteparamMapper.deleteByPrimaryKey(id);
	}

}

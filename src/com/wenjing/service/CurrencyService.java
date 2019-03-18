package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.CurrencyMapper;
import com.wenjing.entity.Currency;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-4-29 上午11:36:12
 *  类说明 : 后台币种管理 Service
 */
@Service
public class CurrencyService {

	@Resource
	private CurrencyMapper currencyMapper;

	/**
	 * 币种列表查询
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Currency> findAll() {
		return currencyMapper.findAll();
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Currency findById(String id) {
		return currencyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改
	 * 
	 * @param currency
	 * @return
	 */
	@Transactional
	public int update(Currency currency) {
		int success = currencyMapper.updateByPrimaryKey(currency);
		return success;
	}

	/**
	 * 新增
	 * 
	 * @param currency
	 * @return
	 */
	@Transactional
	public int insert(Currency currency) {
		String id = Tools.getUUID();
		currency.setId(id);
		int success = currencyMapper.insertSelective(currency);
		return success;
	}

	/**
	 * 获取当前sort最大值
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMaxSort() {
		return currencyMapper.getMaxSort();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		int success = currencyMapper.deleteByPrimaryKey(id);
		return success;
	}

}

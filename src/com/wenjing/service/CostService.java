
package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.CostMapper;
import com.wenjing.entity.Cost;

/**
 * 说明 后台运营中心管理
 * @author sevens
 *
 */
@Service
public class CostService {

	
	@Resource
	private CostMapper costMapper;
	
	/**
	 * 查询所有cost 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<Cost> findAll(){
		return costMapper.findAll();
	}
	
	/**
	 * 根据id删除cost
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		return costMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询cost
	 * @param id
	 * @return
	 */
	@Transactional
	public Cost findById(String id) {
		return costMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存COST
	 * @param COST
	 * @return
	 */
	@Transactional
	public int save(Cost cost) {
		return costMapper.insert(cost);
	}
	
	/**
	 * 修改cost
	 * @param cost
	 * @return
	 * sevens
	 */
	@Transactional
	public int update(Cost cost) {
		return costMapper.updateByPrimaryKeySelective(cost);
	}
	
	public List<Cost> findAllByRoleId(String roleId) {
		return costMapper.findAllByRoleId(roleId);
	}
	
	/**
	 * 获取运营中心列表
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Cost> findAllCostByCostNumber(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Cost> costlist = (List<Cost>) request.getSession().getAttribute("cost");
		return costlist;
	}

	/**
	 * 根据运营中心名查询运营中心id
	 * @param string
	 * @return
	 */
	@Transactional(readOnly = true)
	public String findIdByName(String costname) {
		return costMapper.findIdByName(costname);
	}

	/**
	 * 根据运营中心id cost、currency关联查询
	 * @param string
	 * @return
	 */
	@Transactional(readOnly = true)
	public Cost getCurrencyByid(String costnumber) {
		return costMapper.getCurrencyByid(costnumber);
	}

	/**
	 * 根据code查询
	 * @param string
	 * @return
	 */
	@Transactional(readOnly = true)
	public String findIdByCode(String code) {
		return costMapper.findIdByCode(code);
	}
	
	/**
	 * @Title: findByCode
	 * @Description: 根据code查询
	 * @param code
	 * @return    
	 * @return Cost    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public Cost findByCode(String code){
		return costMapper.findByCode(code);
	}

	@Transactional(readOnly=true)
	public Cost getIsNoThisCostNum(String costnumbers){
		return costMapper.getIsNoThisCostNum(costnumbers);
	}
	/**
	 * @author Sevens
	 * 时间：2015-12-18
	 * @param costnumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Cost> findNotUSDCostNum(String costnumber){
		return costMapper.findNotUSDCostNum(costnumber);
	}
}

package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Cost;

public interface CostMapper {
    int deleteByPrimaryKey(String id);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);
    
    List<Cost> findAllByCostNumber(List<String> costnumberlist);
    
    List<Cost> selectByRoleid(String roleId);

	List<Cost> findAllByRoleId(String roleId);
	
	List<Cost> findAll();

	String findIdByName(String costname);

	Cost getCurrencyByid(String costnumber);

	String findIdByCode(String string);

	/**
	 * @Title findNotUSDCostNum
	 * @Description TODO 查询不是美国的costnum
	 * @Author Bowden
	 * @CreateDate 2015-8-6 下午4:30:25
	 */
	List<Cost> findNotUSDCostNum(@Param("id")String id); 
	
	/**
	 * @Title: findByCode
	 * @Description: 根据code查询
	 * @param code
	 * @return    
	 * @return Cost    返回类型
	 * @author xiejin
	 */
	Cost findByCode(String code);

	/**
	 * @Title getIsNoThisCostNo
	 * @Description TODO查询不是此costnum的销售中心
	 * @Author Bowden
	 * @CreateDate 2015-9-14 下午3:35:07
	 */
	Cost getIsNoThisCostNum(@Param("costnumber")String costnumber);
}
package com.wenjing.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tourprice;

public interface TourpriceMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int insert(Tourprice record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int insertSelective(Tourprice record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    Tourprice selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int updateByPrimaryKeySelective(Tourprice record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int updateByPrimaryKey(Tourprice record);

    /**
     * 根据tourdateid 查询
     * @param id
     * @return
     */
	Tourprice selectByTourdateid(String tourDateId);

	/**
	 * 批量插入
	 * @param tourpricesList
	 * @return
	 */
	int batchAddprice(List<Tourprice> tourpricesList);
	
	/**
	 * @Title: getminsprice
	 * @Description: 查询线路最低售价
	 * @param tourlineId
	 * @return    
	 * @return BigDecimal    返回类型
	 */
	BigDecimal getminsprice(String tourlineId);
	
	/**
	 * @Title: getminmprice
	 * @Description: 查询线路最低标价
	 * @param tourlineId
	 * @return    
	 * @return BigDecimal    返回类型
	 * @author xiejin
	 */
	BigDecimal getminmprice(@Param("tourlineId")String tourlineId,
			@Param("costnumber")String costnumber,
			@Param("time")int time);
	
	/**
	 * @Title: getminsalePrice
	 * @Description: 查询线路最低售价
	 * @param tourlineId
	 * @param costnumber
	 * @param time
	 * @return    
	 * @return BigDecimal    返回类型
	 * @author xiejin
	 */
	BigDecimal getminsellPrice(@Param("productId")String productId,
			@Param("costnumber")String costnumber,
			@Param("time")int time);
	
	/**
	 * 根据tourDateId 查询出tourPrice
	 * @param tourDateId
	 * @return
	 */
	Tourprice findByTourDateIdAndCostNumber(@Param("tourDateId")String tourDateId,@Param("costNumber")String costNumber);

	/**
	 * 查询线路下价格对应的costnum
	 * @param productid
	 * @return
	 */
	List<String> getPriceCostNumByProductid(String productid);

	/**
	 * @Title findSellingPriceByCostNum
	 * @Description TODO 根据costnum查询价格列表
	 * @Author Bowden
	 * @CreateDate 2015-8-6 下午4:41:29
	 */
	List<Tourprice> findSellingPriceByCostNum(List<String> costNumList);

	/**
	 * @Title batchUpdateSellingPrice
	 * @Description 批量更新售价sellingprice
	 * @Author Bowden
	 * @CreateDate 2015-8-6 下午4:56:23
	 */
	int batchUpdateSellingPrice(List<Tourprice> tourprices);
	/**
	 * 根据线路Id删除价格记录
	 * @author Sevens
	 * 时间2015-8-17
	 * @param productId
	 * @return
	 */
	int deleteWithProductid(String productId);

	/**
	 * @Title batchDelete
	 * @Description 批量删除
	 * @Author Bowden
	 * @CreateDate 2015-9-15 下午5:17:12
	 */
	int batchDelete(List<String> priceIdList);
}
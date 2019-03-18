package com.wenjing.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tourdate;

public interface TourdateMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int insert(Tourdate record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int insertSelective(Tourdate record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    Tourdate selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int updateByPrimaryKeySelective(Tourdate record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int updateByPrimaryKey(Tourdate record);

    /**
     * 根据tourlineid查询
     * @return
     */
	List<Tourdate> findByProductId(@Param("productid")String productid,@Param("costnumber")List<String> costnumber);
	
	 /**
     * 根据tourlineid和costnumber查询
     * @return
     */
	List<Tourdate> findByproductAndcostnum(@Param("productid")String productid,@Param("costnumber")String costnumber);
	
	/**
	 * 
	 * @param tourlineId
	 * @param costnumber
	 * @param departureDate
	 * @return
	 */
	List<Tourdate> findByProductidAndCostnumberAndDepartureDate(@Param("productid")String productid,@Param("costnumber")String costnumber,@Param("departureDate")Integer departureDate);
	
	/**
	 * 根据产品ID和运营中心ID以及出发地ID查询tourdate(包含tourprice)
	 * 
	 * @param productid
	 * @param costnumber
	 * @param departureId
	 * @return
	 */
	List<Tourdate> findByProductIdAndCostnumberAndDepartureId(@Param("productId")String productId,@Param("departureId")String departureId,@Param("costnumber")String costnumber,@Param("firstDate")long firstDate);
	
	/**
	 * 根据产品ID和运营中心查询tourdate(包含tourprice)
	 * 
	 * @param productId
	 * @param costnumber
	 * @param firstDate
	 * @return
	 */
	List<Tourdate> findByProductIdAndCostnumber(@Param("productId")String productId,@Param("costnumber")String costnumber,@Param("firstDate")long firstDate);

	/**
	 * 获取当前sort最大值
	 * @return
	 */
	int getMaxSort(String costnumber);

	/**
	 * 根据tourdateid查询
	 * @param id
	 * @return
	 */
	Tourdate findByTourDateId(String id);

	/**
	 * 批量插入
	 * @param tourdateList
	 * @return
	 */
	int batchAddDate(List<Tourdate> tourdateList);
	
	/**
	 * @Title: getminStartDate
	 * @Description: 获得最小出发日期
	 * @param tourlineid
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
//	int getMinStartDate(String tourlineid);
	
	/**
	 * @Title: getMaxEndDate
	 * @Description: 获得最大结束日期
	 * @param tourlineid
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
//	int getMaxEndDate(String tourlineid);
	
	/**
	 * @Title: selectByTourlineid
	 * @Description: 根据tourlineid查询tourdate 不包含对应价格
	 * @param tourlineid
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	List<Tourdate> selectByProductId(String productid);
	
	/**
	 * @Title: getDateAndPriceByTourlineid
	 * @Description: 根据tourlineid查询tourdate 包含对应tourprice
	 * @param tourlineid
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	List<Tourdate> getDateAndPriceByProductid(@Param("productid")String productid,
			@Param("costnumber")String costnumber);

	/**
	 * 获取当前线路排序最大值
	 * @param tourlineid
	 * @return
	 */
	int getMaxSortByproductid(@Param("productid")String productid, @Param("costNum")String costNum);
	
	/**
	 * 根据产品ID加载出Tourdate
	 * 
	 * @param productId
	 * @return
	 */
	Tourdate findByProductIdAndDepartureDate(@Param("productid")String productid,@Param("departureDate")long departureDate,@Param("costNumber")String costNumber);


	/**
	 * 根据销售中心查询所有日期和价格（复制日期和价格）
	 * @param costnum
	 * @return
	 */
	List<Tourdate> findAllDateAndPriceByCostnum(String costnum);
	
	/**
	 * 根据销售中心查询所有日期和价格（复制日期和价格）
	 * @param costnum
	 * @return
	 */
	List<Tourdate> findAllDateAndPriceByCostnum2(String costnum);
	/**
	 * 根据线路Id删除出发日期
	 * 时间2015-8-17
	 * @author Sevens
	 * @param tourlineId
	 * @return
	 */
	int deleteWithProductid(String productid);
	
	/**
	 * 查询线路最低售价的日期
	 * @param productid
	 * @param costnumber
	 * @param time
	 * @return
	 */
	BigDecimal getMinSellPrice(@Param("productid")String productid,@Param("costnumber") String costnumber,@Param("time")int time);
	
	/**
	 * 查询线路最低标价的日期
	 * @param productid
	 * @param costnumber
	 * @param time
	 * @return
	 */
	List<Tourdate> getTourdateWithMinMPrice(@Param("productid")String productid,@Param("costnumber")String costnumber,@Param("time")int time);
	
	/**
	 * @Title: findByIdAndCost
	 * @Description: 根据参数查询对应日期
	 * @param productid
	 * @param costnumber
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	List<Tourdate> findByIdAndCost(@Param("productid")String productid,@Param("costnumber")String costnumber);
	
	/**
	 * @Title: getDistinctTourdate
	 * @Description:
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	List<Tourdate> getDistinctTourdate();
	
	/**
	 * @Title: getAllTourdate
	 * @Description: 根据线路id和costnumber查询对应的所有日期
	 * @param productid
	 * @param costnumber
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	List<Tourdate> getAllTourdate(@Param("productid")String productid);

	/**
	 * @Title findNoThisCostNumDate
	 * @Description TODO查询
	 * @Author Bowden
	 * @CreateDate 2015-9-14 下午3:57:31
	 */
	List<Tourdate> findByCostNumAndDateIds(@Param("productid")String productid, @Param("costNum")String costNum);

	/**
	 * @Title findDateNotCostNum
	 * @Description 查询该线路下不是此销售中心的日期和价格
	 * @Author Bowden
	 * @CreateDate 2015-9-15 下午5:12:14
	 */
	List<String> findDateBytourIdNotCostNum(@Param("productid")String productid, @Param("costNum")String costNum);
	
	int batchDelete(@Param("dateIdList")List<String> dateIdList);
	
	void updateDatePeopleRemain(@Param("tourDateId")String tourDateId,@Param("orderPeople")int orderPeople);
	
	/**
	 * 查询最早的出发日期
	 * @param productId
	 * @return
	 */
	int findMineDate(String productId); 
	
	void updateStore(@Param("tourdateId") String paramString, @Param("sail") Integer paramInteger);


	public abstract BigDecimal getMinSellPriceExcludeLandPrice(@Param("productid") String paramString1, @Param("costnumber") String paramString2, @Param("time") int paramInt);

	public abstract BigDecimal getMinSellPriceIncludeLandPrice(@Param("productid") String paramString1, @Param("costnumber") String paramString2, @Param("time") int paramInt);

//	
//	/**
//	 * @Title: findByIdAndCost
//	 * @Description: 
//	 * @param tourlineId
//	 * @param costnumber
//	 * @return    
//	 * @return List<Tourdate>    返回类型
//	 * @author xiejin
//	 */
//	List<Tourdate> findByIdAndCost(@Param("tourlineId")String tourlineId,@Param("costnumber")String costnumber);
}
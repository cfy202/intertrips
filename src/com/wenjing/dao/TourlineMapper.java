package com.wenjing.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tourline;

public interface TourlineMapper {
    
	/**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int insert(Tourline record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int insertSelective(Tourline record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    Tourline selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int updateByPrimaryKeySelective(Tourline record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int updateByPrimaryKeyWithBLOBs(Tourline record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:25
     */
    int updateByPrimaryKey(Tourline record);
    
    /**
     * 根据运营中心id来查询所有线路
     * @author Sevens
     * @param costNumber
     * return 
     */
    
    List<Tourline> findAll(String costNuber);
    /**
     * @author Sevens
     * 时间2015-5-18
     * 
     */
    List<Tourline> findAllInCost(List<String> costnumber);
    
    /**
     * 查询最大sort值  
     * @author Sevens
     * 
     */
    int getMaxSort();
    
    /**
     * @Title: findbyHotIndexShow
     * @Description: 根据ishot 和indexShow查询首页展示热卖线路
     * @param ishot
     * @param indexshow
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findbyHotIndexShow(@Param("ishot")Integer ishot,
    		@Param("indexshow")Integer indexshow,
    		@Param("costnumber")String costnumber,
    		@Param("isshow")Integer isshow,
    		@Param("time")int time);
    
    /**
     * @Title: findbyHotIndexShow
     * @Description: 根据ishot 和indexShow查询首页展示热卖线路
     * @param ishot
     * @param indexshow
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     */
    List<Tourline> findRandomByHotIndexShow(@Param("ishot")Integer ishot,
    		@Param("indexshow")Integer indexshow,
    		@Param("costnumber")String costnumber,
    		@Param("isshow")Integer isshow,
    		@Param("time")Integer time);
    
    
    /**
     * @Title: findbyHotShowRegionid
     * @Description: 根据ishot ,isShow,regionid查询二级页面的热销线路
     * @param ishot
     * @param ishow
     * @param costnumber
     * @param regionid
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findbyHotShowRegionid(@Param("regionid")String regionid,
    		@Param("ishot")Integer ishot,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("time")int time);
    
    /**
     * @Title: findByShowRegionid
     * @Description: 根据regionid isshow costnumber startPos pageSize 分页查询二级页面线路展示
     * @param regionid
     * @param isshow
     * @param costnumber
     * @param startPos
     * @param pageSize
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findByShowRegionid(@Param("regionid")String regionid,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("startPos")Integer startPos,
    		@Param("pageSize")Integer pageSize,
    		@Param("searchAttractions")String searchAttractions,
    		@Param("searchdestination")String searchdestination,
    		@Param("time")int time,
    		@Param("tagName")String tagName);
    
    /**
     * @Title: getTourlineCount
     * @Description: 根据regionid isshow costnumber获得线路总数
     * @param regionid
     * @param isshow
     * @param costnumber
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int getTourlineCount(@Param("regionid")String regionid,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("searchAttractions")String searchAttractions,
    		@Param("searchdestination")String searchdestination,
    		@Param("time")int time,
    		@Param("tagName")String tagName);
    
    /**
     * @Title: findByShowRegionid
     * @Description: 查询二级页面线路, 不带分页
     * @param regionid
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findByShowRegionid2(@Param("regionid")String regionid,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("time")int time);
    
    /**
     * @Title: findByShowRegionid
     * @Description: 根据分类读取线路
     * @param regionid
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findByRegionid(@Param("regionid")String regionid,
    		@Param("costnumber")String costnumber,
    		@Param("time")int time);
    
    /**
     * @Title: findByShowRegionidattra2
     * @Description: 根据景点查询首页显示线路
     * @param attraction
     * @param regionid
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findByShowRegionidattra2(@Param("attrhotname")String attrhotname,
    		@Param("regionid")String regionid,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("indexshow")Integer indexshow,
    		@Param("time")int time);
    
    /**
     * @Title: findByShowRegionid
     * @Description: 根据条件分页查询二级页面线路
     * @param regionid
     * @param isshow
     * @param costnumber
     * @param startPos
     * @param pageSize
     * @param stdest
     * @param attr
     * @param days
     * @param minprice
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findByCondition(@Param("regionid")String regionid,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("startPos")Integer startPos,
    		@Param("pageSize")Integer pageSize,
    		@Param("startCity")String startCity,
    		@Param("attr")String[] attr,
    		@Param("minDay")Integer minDay,
    		@Param("maxDay")Integer maxDay,
    		@Param("minPrice")BigDecimal minPrice,
    		@Param("maxPrice")BigDecimal maxPrice,
    		@Param("sort") String sort,
    		@Param("keyword") String keyword,
    		@Param("time") int time,
    		@Param("tag")String[] tag);

    /**
     * @Title: getCountByCondition
     * @Description: 根据条件查询线路总数
     * @param regionid
     * @param isshow
     * @param costnumber
     * @param startCity
     * @param attr
     * @param minDay
     * @param maxDay
     * @param minPrice
     * @param maxPrice
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int getCountByCondition(@Param("regionid")String regionid,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("startCity")String startCity,
    		@Param("attr")String[] attr,
    		@Param("minDay")Integer minDay,
    		@Param("maxDay")Integer maxDay,
    		@Param("minPrice")BigDecimal minPrice,
    		@Param("maxPrice")BigDecimal maxPrice,
    		@Param("keyword") String keyword,
    		@Param("time") int time,
    		@Param("tag")String[] tag);
    
    /**
     * 根据线路ID查询所有线路相关的内容
     * 
     * @param tourlineId
     * @return
     */
    Tourline findWholeInfoByProductId(String productId);

    /**
     * 根据运营中心ID来查询线路
     * @param costnumber
     * @return
     */
    List<Tourline> findByCostNumber(String costnumber);
    /**
     * 参与优惠券活动的线路
     * @param costnumber
     * @return
     */
    List<Tourline> findWithCouponseChoose(String costnumber);
    
    /**
     * 根据productId查询出tourline
     * 
     * @param productId
     * @return
     */
    Tourline findByProductId(String productId);
    
    /**
     * @Title: searchTourline
     * @Description: 根据关键字搜索线路
     * @param isshow
     * @param costnumber
     * @param startPos
     * @param pageSize
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> searchTourline(@Param("keyword") String keyword,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("startPos")Integer startPos,
    		@Param("pageSize")Integer pageSize,
    		@Param("time") int time);
    
    /**
     * @Title: searchTourlineCount
     * @Description: 根据关键字搜索线路的数量
     * @param keyword
     * @param isshow
     * @param costnumber
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int searchTourlineCount(@Param("keyword") String keyword,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("time") int time);
    
    /**
     * @Title: searchTourline2
     * @Description: 首页根据关键字查询线路，不带分页
     * @param keyword
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> searchTourline2(@Param("keyword") String keyword,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("time") int time);
    
    /**
     * @Title: searchTourlineHot
     * @Description: 查询四条热卖线路
     * @param ishot
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> searchTourlineHot(@Param("ishot")Integer ishot,
    		@Param("isshow")Integer isshow,
    		@Param("costnumber")String costnumber,
    		@Param("time") int time);
    
    /**
     * @Title: findByIdIsshow
     * @Description: 根据id查询前台页面上显示的线路详情
     * @param id
     * @return    
     * @return Tourline    返回类型
     */
    Tourline findByIdIsshow(String id);
    
    /**
     * @Title: findSaleTourline
     * @Description: 查询首页特卖线路
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
    List<Tourline> findSaleTourline(@Param("costnumber") String costnumber,
    		@Param("timeNow")int timeNow);
    /**
     * @author Sevens
     * 时间2015-7-10
     * @param costnumber
     * @return
     */
    int getSTourlineCount(@Param("costnumberTD")String costnumberTD,
    		              @Param("search")String search,
    		              @Param("indexShow") Integer indexShow,
    		              @Param("isShow") Integer isShow,
    		              @Param("isHot") Integer isHot);
    /**
     * @author Sevens
     * 时间2015-6-30
     * @param startPos
     * @param pageSize
     * @param costnumber
     * @return
     */
    List<Tourline> selectAllByPage(@Param(value="startPos") Integer startPos,
			                       @Param(value="pageSize") Integer pageSize,
			                       @Param("costnumberTD") String costnumberTD,
			                       @Param("search")String search,
			                       @Param("indexShow") Integer indexShow,
			    		           @Param("isShow") Integer isShow,
			    		           @Param("isHot") Integer isHot);

    /**
     * 修改costNoIds
     * @param tourlineid
     * @param costNumIds
     * @return
     */
	int updateCostNumIds(@Param("productid") String productid, @Param("costNumIds") String costNumIds);
	
	 /**
     * 修改costNoIds
     * @param tourlineid
     * @param costNumIds
     * @return
     */
	int updatedeparture(@Param("tourlineid") String tourlineid, @Param("departure") String departure);
	
	/**
	 * @Title: findByUrlIsshow
	 * @Description: 根据url查询线路
	 * @param url
	 * @return    
	 * @return Tourline    返回类型
	 * @author xiejin
	 */
	Tourline findByUrlIsshow(String url);
	/**
	 * 查询即将过期的线路价格
	 * @author Sevens
	 * 时间2015-8-31
	 * @param costnumber
	 * @param date
	 * @return
	 */
	List<Tourline> findTourlineWithTourdate(@Param("costnumber")List<String> costnumber,@Param("date")Integer date);
    /**
     * 查询没价格的线路
     * @author Sevens
     * 时间2015-9-1
     * @param costnumber
     * @return
     */
	List<Tourline> findTourlineWithNoprice(@Param("costnumber")List<String> costnumber);
	
	/**
	 * @Title: findByCostNumber
	 * @Description: 根据销售中心id查询显示的线路
	 * @param costNumber
	 * @return    
	 * @return List<Tourline>    返回类型
	 * @author xiejin
	 */
	List<Tourline> getByCostNumber(String costNumber);
	
	/**
	 * @Title: findByPromotionId
	 * @Description: 根据促销活动id读取线路
	 * @param costnumber
	 * @param promotionId
	 * @param time
	 * @return    
	 * @return List<Tourline>    返回类型
	 * @author xiejin
	 */
	List<Tourline> findByPromotionId(@Param("costnumber")String costnumber,
			@Param("promotionId")String promotionId,
			@Param("time") int time);
	
	List<Tourline> findTourlineByCouponseID(@Param("costnumber")String costnumber,@Param("couponseid")String couponseid);
	

	/**
	 * @Title: getTourlineByCondition
	 * @Description: 分页查询intertrips线路
	 * @param costnumber
	 * @param startPos
	 * @param pageSize
	 * @param destination
	 * @param minPrice
	 * @param maxPrice
	 * @param sort
	 * @param keyword
	 * @param time
	 * @param rate
	 * @return    
	 * @return List<Tourline>    返回类型
	 * @author xiejin	
	 */
    List<Tourline> getTourlineByCondition(
			@Param("costnumber")String costnumber,
			@Param("startPos")Integer startPos,
			@Param("pageSize")Integer pageSize,
			@Param("destination")String destination,
			@Param("minPrice")BigDecimal minPrice,
			@Param("maxPrice")BigDecimal maxPrice,
			@Param("sort") String sort,
			@Param("keyword") String keyword,
			@Param("time") int time,
			@Param("rateString") String rateString,
			@Param("dateFrom") int dateFrom,
			@Param("dateTo") int dateTo,
			@Param("regionId")String regionId,
			@Param("promotionId")String promotionId);

    /**
     * @Title: getTourlineCountByCondition
     * @Description:查找intertrips 线路数量
     * @param costnumber
     * @param destination
     * @param minPrice
     * @param maxPrice
     * @param keyword
     * @param rate
     * @param time
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int getTourlineCountByCondition(
			@Param("costnumber")String costnumber,
			@Param("destination")String destination,
			@Param("minPrice")BigDecimal minPrice,
			@Param("maxPrice")BigDecimal maxPrice,
			@Param("keyword") String keyword,
			@Param("rateString")String rateString,
			@Param("time") int time,
			@Param("dateFrom") int dateFrom,
			@Param("dateTo") int dateTo,
			@Param("regionId")String regionId,
			@Param("promotionId")String promotionId);
			
			
	List<Tourline> findAllIsShow();
	
}
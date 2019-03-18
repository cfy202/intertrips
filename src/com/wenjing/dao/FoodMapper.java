package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Food;

public interface FoodMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    int insert(Food record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    int insertSelective(Food record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    Food selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    int updateByPrimaryKeySelective(Food record);

   

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2016-03-12 15:35:12
     */
    int updateByPrimaryKey(Food record);
    

    /**
     * 根据costnumber查询所有hotel 
     * @return
     * xiejin
     */
    List<Food> findAllByCostNumber();
    
    /**
     * 根据costnumber查询最大sort值 
     * @return
     * xiejin
     */
    int getMaxSort();
    
    
    /**
     * 根据目的地ID查询酒店
     * @author Sevens
     * @return
     */
    
    List<Food> selectByDestinationid(String destinationId);
    
    /**
     * @Title: replaceByDestinationId
     * @Description: 替换目的地id
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int replaceByDestinationId(@Param("destinationId")String destinationId,
    		@Param("replaceId")String replaceId);
    
    /**
     * @author Sevens
     * 时间2015-7-10
     * @param costnumber
     * @return
     */
    int getFoodCount(@Param("costnumberTD")String costnumberTD,
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
    List<Food> selectAllByPage(@Param(value="startPos") Integer startPos,
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
	
    Food selectByProductId(String productid);
    
    List<Food> selectByRegionId(String regionid);
    
    List<Food> selectByids(List<String> ids);
    
    Food selectWithPriceByProductId(String productId);
}
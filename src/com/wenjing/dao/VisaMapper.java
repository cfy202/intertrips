package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Visa;

public interface VisaMapper {
    int deleteByPrimaryKey(String id);

    int insert(Visa record);

    int insertSelective(Visa record);

    Visa selectByPrimaryKey(String id);
    
    Visa selectByProductId(String productid);
    
    List<Visa> selectByRegionId(String regionid);

    int updateByPrimaryKeySelective(Visa record);

    int updateByPrimaryKey(Visa record);
    
    List<Visa> findAllByCostNumber(List<String> costnumber);
    
    int getMaxSort();
    
    /**
     * @author Sevens
     * 时间2015-7-10
     * @param costnumber
     * @return
     */
    int getVisaCount(@Param("costnumberTD")String costnumberTD,
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
    List<Visa> selectAllByPage(@Param(value="startPos") Integer startPos,
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
}
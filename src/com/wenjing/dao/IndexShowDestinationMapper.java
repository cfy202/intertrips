/**
 * 
 */
package com.wenjing.dao;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.IndexShowDestination;

/**
 * 类说明
 * @author xiejin
 * @date 2015-11-16 
 * @date 2015-11-16 下午4:05:55
 */
public interface IndexShowDestinationMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int insert(IndexShowDestination record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int insertSelective(IndexShowDestination record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    IndexShowDestination selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int updateByPrimaryKeySelective(IndexShowDestination record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int updateByPrimaryKey(IndexShowDestination record);
    
    /**
     * @Title: getTourlineIdString
     * @Description: 查询目的地字符串
     * @param costNumber
     * @param showType
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    String getDestinationIdString(@Param("costNumber")String costNumber,@Param("showType")Integer showType);
    
    /**
     * @Title: deleteByCondition
     * @Description: 删除关系表
     * @param costNumber
     * @param showType
     * @param destinationId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByCondition(@Param("costNumber")String costNumber,
    		@Param("showType")Integer showType,
    		@Param("destinationId")String destinationId);
    
    /**
     * @Title: updateFileUrl
     * @Description: 根据销售中心和目的地id修改fileUrl
     * @param costNumber
     * @param destinationId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int updateFileUrl(@Param("costnumber")String costnumber,
    		@Param("destinationId")String destinationId,
    		@Param("fileUrl")String fileUrl,
    		@Param("pageId")String pageId);
    
    /**
     * @Title: getFileUrl
     * @Description: 根据销售中心和目的地id查询fileUrl
     * @param costnumber
     * @param destinationId
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    IndexShowDestination getFileUrl(@Param("costnumber")String costnumber,
    		@Param("destinationId")String destinationId);
}

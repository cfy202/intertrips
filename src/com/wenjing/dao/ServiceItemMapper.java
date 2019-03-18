package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.ServiceItem;

public interface ServiceItemMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    int deleteByPrimaryKey(String id);
    
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    int insert(ServiceItem record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    ServiceItem selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 18:21:13
     */
    int updateByPrimaryKey(ServiceItem record);
   
    /**
     * 查询所有服务项
     * @author Sevens
     * 时间2015-9-7
     * @return
     */
    List<ServiceItem> findAll();
    
    /**
     * @Title: findByTourlineId
     * @Description:根据线路查询服务项目
     * @return    
     * @return List<ServiceItem>    返回类型
     * @author xiejin
     */
    List<ServiceItem> findByTourlineId(String tourlineId);
    /**
     * @Title: findByTourlineId
     * @Description:根据线路查询服务项目
     * @return    
     * @return List<ServiceItem>    返回类型
     * @author xiejin
     */
    List<ServiceItem> findBytourlineinclude(@Param("include")String include);
    /**
     * 根据产品ID和类型查出所有的服务项
     * 
     * @param productId
     * @param type
     * @return
     */
    List<ServiceItem> findProductIdAndType(@Param("productId") String productId, @Param("type") int type);
}
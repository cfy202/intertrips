/**
 * 
 */
package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.HotTourline;


/**
 * 类说明
 * @author xiejin
 * @date 2015-8-21 
 * @date 2015-8-21 上午10:58:47
 */
public interface HotTourlineMapper {

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     */
    int insert(HotTourline record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     */
    int insertSelective(HotTourline record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     */
    HotTourline selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKeySelective(HotTourline record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKey(HotTourline record);
    
    /**
     * 根据销售中心id和线路Id删除热卖线路
     * @author Sevens
     * 时间2015-8-21
     * @param costNumber
     * @param tourlineId
     * @return
     */
    int deleteBycostnumberAnaTourlineId(@Param("costNumber")String costNumber,@Param("tourlineId")String tourlineId);
    /**
     * 
     * @author Sevens
     * 时间2015-8-21
     * @param costNumber
     * @return
     */
    List<HotTourline> findByCostnumber(String costNumber);
}

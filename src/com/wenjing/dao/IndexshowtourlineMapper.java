package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.HotTourline;
import com.wenjing.entity.Indexshowtourline;

public interface IndexshowtourlineMapper {
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
    int insert(Indexshowtourline record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int insertSelective(Indexshowtourline record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    Indexshowtourline selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int updateByPrimaryKeySelective(Indexshowtourline record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-08-29 02:38:55
     */
    int updateByPrimaryKey(Indexshowtourline record);
    
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
    List<Indexshowtourline> findByCostnumber(String costNumber);
}
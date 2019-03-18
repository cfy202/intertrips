package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Producttag;

public interface ProducttagMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    int insert(Producttag record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    int insertSelective(Producttag record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    Producttag selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    int updateByPrimaryKeySelective(Producttag record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-09-06 18:21:14
     */
    int updateByPrimaryKey(Producttag record);
    /**
     * @author Sevens
     * 时间2015-9-7
     * @param productid
     * @param costnumber
     * @return
     */
    int deleteWithProductIdAndCostnumber(@Param("productid")String productid,@Param("costnumber")String costnumber);
    /**
     * @author Sevens
     * 时间2015-9-8
     * @param productid
     * @param costnumber
     * @return
     */
    List<Producttag> findByProductid(@Param("productid")String productid,@Param("costnumber")String costnumber);

    /**
     * @Title findByTagId
     * @Description 根据tagId查询
     * @Author Bowden
     * @CreateDate 2015-9-18 下午1:40:09
     */
	int findByTagIdCount(String tagId);
}
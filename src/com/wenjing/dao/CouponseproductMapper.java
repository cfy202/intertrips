package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Couponseproduct;
import com.wenjing.entity.PromotionProduct;

public interface CouponseproductMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int insert(Couponseproduct record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int insertSelective(Couponseproduct record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    Couponseproduct selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKeySelective(Couponseproduct record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:29
     */
    int updateByPrimaryKey(Couponseproduct record);
    /**
     * @author Sevens
     * @param couponseId
     * @return
     * 时间2015-5-20
     */
    List<Couponseproduct> selectByCouponseid(String couponseId);
    
    /**
     * @author Sevens
     * @param tourlineId
     * @return
     * 时间2015-5-20
     */
    int deleteByCouponseid(String couponseId);
    
    /**
     * 批量插入
     * @param pList
     */
	int batchAdd(List<Couponseproduct> pList);
}
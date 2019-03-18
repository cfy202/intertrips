package com.wenjing.dao;

import com.wenjing.entity.Showtourline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShowtourlineMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    int insert(Showtourline record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    int insertSelective(Showtourline record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    Showtourline selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    int updateByPrimaryKeySelective(Showtourline record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-08-29 02:38:54
     */
    int updateByPrimaryKey(Showtourline record);
    
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
    List<Showtourline> findByCostnumber(@Param("costNumber")String costNumber,@Param("isCreate")Integer isCreate);
    /**
     * @author Sevens
     * 时间2015-8-31
     * @param costNumber
     * @param tourlineId
     * @return
     */
    int findCountWithCostnumberAnaTourline(@Param("costNumber")String costNumber,@Param("tourlineId")String tourlineId);
    /**
     * @author Sevens
     * 时间2015-8-31
     * @param costNumber
     * @param tourlineId
     * @param isCreate
     * @return
     */
    int updateByisCreate(@Param("costNumber")String costNumber,@Param("tourlineId")String tourlineId,@Param("isCreate")Integer isCreate);

    /**
     * @author zhaodongdong
     * @return
     */
    int resetIsCreate(@Param("tourlineIds")List<String> tourlineIds);
}
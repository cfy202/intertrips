
package com.wenjing.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Email;

/**
 * 类说明		订阅邮件dao层
 * @author xiejin
 * @date 2015-6-29 
 * @date 2015-6-29 下午5:38:59
 */
public interface EmailMapper {

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
    int insert(Email record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     */
    int insertSelective(Email record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     */
    Email selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKeySelective(Email record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKey(Email record);
    
    /**
     * @Title: findAll
     * @Description: 根据运营中心查询所有邮件
     * @param costNumber
     * @return    
     * @return List<Email>    返回类型
     * @author xiejin
     */
    List<Email> findAll(List<String> costNumber);
    
    /**
     * @Title: findByEmailAddress
     * @Description: 根据邮箱地址查询邮箱
     * @param emailAddress
     * @return    
     * @return Email    返回类型
     * @author xiejin
     */
    Email findByEmailAddress(String emailAddress);
    
    /**
     * @Title: updateEmailStatus
     * @Description: 取消邮件订阅
     * @param id
     * @param time
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int updateEmailStatus(@Param("id")String id ,@Param("time") int time);
}

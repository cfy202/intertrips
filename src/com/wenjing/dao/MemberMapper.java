package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Member;

public interface MemberMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int insert(Member record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int insertSelective(Member record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    Member selectByPrimaryKey(String id);
    
    /**
     * 根据Id查询用户信息
     * 
     * @param id
     * @return
     */
    Member selectWithInfoById(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    int updateByPrimaryKey(Member record);

    /**
     * 根据用户名查询
     * bowden
     * @return ：对象
     */
	Member findByAccount(String account);

	/**
	 * 查询所有会员列表
	 * bowden
	 * @return
	 */
	List<Member> findAll();

	/**
	 * 修改用户状态
	 * @param userstatus
	 * bowden
	 * @return
	 */
	int updateStatus(@Param("id") String id,@Param("userstatus") Integer userstatus);

	/**
	 * 根据memberid修改密码
	 * @param password
	 * @param memberid
	 */
	int updatePasswordById(@Param("password")String password,@Param("memberid") String memberid);

	/**
	 * 修改最后登录时间
	 * @param lasttime
	 */
	int updateLasttime(@Param("lasttime")String lasttime,@Param("memberid") String memberid);
	/**
	 * 查询会员的数量
	 * @author Sevens
	 * 时间2015-8-31
	 * @return
	 */
	int findCount();
	/**
	 * 根据评论id查询用户
	 * @param id
	 * @return
	 */
	Member findByCommentsId(String id);
}
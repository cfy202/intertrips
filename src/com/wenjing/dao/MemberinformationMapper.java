package com.wenjing.dao;


import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Memberinformation;

public interface MemberinformationMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insert(Memberinformation record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insertSelective(Memberinformation record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    Memberinformation selectByPrimaryKey(String id);
    
    /**
     * 根据用户ID查找用户详情
     * 
     * @param memberId
     * @return
     */
    Memberinformation selectByMemberId(String memberId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKeySelective(Memberinformation record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int updateByPrimaryKey(Memberinformation record);

    /**
     * 根据会员id查询会员详细信息
     * @param id
     * @return
     */
	Memberinformation findInfoByMemberId(String memberid);

	/**
	 * 根据memberid删除
	 * @param memberid
	 * @return
	 */
	int deleteByMemberid(String memberid);

	/**
	 * 根据memberid修改score
	 * @param score
	 * @param memberid
	 * @return
	 */
	int updateScoreByMemberId(@Param("score")int score,@Param("memberid") String memberid);
	
	/**
	 * 用户中心用户修改资料
	 */
	int updateProfileInfo(Memberinformation record);
}

package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.GetScore;

/**
 * 类说明		积分获取记录dao层
 * @author bowden
 * @date 2015-6-29 
 * @date 2015-6-29 下午5:38:59
 */
public interface GetScoreMapper {

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
    int insert(GetScore record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     */
    int insertSelective(GetScore record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     */
    GetScore selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKeySelective(GetScore record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKey(GetScore record);

    /**
     * 批量插入基本信息积分
     * @param gScoreList
     * @return
     */
	int batchAddMemberInfo(List<GetScore> gScoreList);

	/**
	 * 根据memberid查询
	 * @param memberid
	 * @return
	 */
	List<GetScore> findBymemberid(@Param("memberid")String memberid, @Param("status") Integer status);

	/**
	 * 根据id批量修改状态
	 * @param getScoeridList
	 * @return
	 */
	int batchUpdateStatusById(@Param("getScoeridList")List<String> getScoeridList, @Param("status") Integer status, @Param("gettime") Integer gettime);

	/**
	 * 根据id修改namepy 和 状态
	 * @param id
	 * @return
	 */
	int updateGetScoreByMemeberid(@Param("namepy")String namepy, @Param("status") int status, @Param("id") String id, @Param("gettime") Integer gettime);

	/**
	 * @Title deleteByMemberId
	 * @Description 删除获取积分记录表中的数据
	 * @Author Bowden
	 * @CreateDate 2015-8-17 上午11:33:38
	 */
	int deleteByMemberId(String memberid);
	
	/**
	 * 
	 * @param memberId
	 * @return
	 */
	List<GetScore> findByMemberId(String memberId);
}

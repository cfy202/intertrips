package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Review;

public interface ReviewMapper {
    
	/**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int insert(Review record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int insertSelective(Review record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    Review selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKeySelective(Review record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKey(Review record);

    /**
     * @Title getCountByCostNum
     * @Description 根据costnum和搜索条件查询总记录数
     * @Author Bowden
     * @CreateDate 2015-9-15 下午3:19:41
     */
	int getCountByCostNum(@Param("costNumList")List<String> costNumList, @Param("search")String search);

	/**
	 * @Title findByCostNumAndPage
	 * @Description 后台展示列表根据costnum查询
	 * @Author Bowden
	 * @CreateDate 2015-9-15 下午3:20:52
	 */
	List<Review> findByCostNumAndPage(@Param("costNumList")List<String> costNumList,
						            @Param("startPos") Integer startPos, 
						            @Param("pageSize") Integer pageSize,
						            @Param("status") Integer status,
						            @Param("search")String search);

	/**
	 * @Title editIsshow
	 * @Description 编辑是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:40:24
	 */
	int editIsshow(Review review);

	/**
	 * @Title updataQStatus
	 * @Description 修改处理状态
	 * @Author Bowden
	 * @CreateDate 2015-9-9 上午10:34:01
	 */
	int updateStatus(@Param("id")String id, @Param("status")int status);

	/**
	 * @Title getQuestionByQid
	 * @Description 评论详情查询
	 * @Author Bowden
	 * @CreateDate 2015-9-15 下午4:20:00
	 */
	Review getReviewByQid(String id);
	
	/**
	 * @Title: getReviewCount
	 * @Description: 获取线路对应的评论数量
	 * @param tourlineId
	 * @param costnumber
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	int getReviewCount(@Param("productId")String productId, @Param("costnumber")String costnumber);
	
	/**
	 * @Title: getReview
	 * @Description: 
	 * @param tourlineId
	 * @param costnumber
	 * @param startPos
	 * @param pageSize
	 * @return    
	 * @return List<Review>    返回类型
	 * @author xiejin
	 */
	List<Review> getReview(@Param("productId")String productId, @Param("costnumber")String costnumber,
			@Param("startPos")int startPos, @Param("pageSize")int pageSize);
	
	/**
	 * 
	 * @param productId
	 * @return
	 */
	List<Review> getAllReviewByProductId(String productId);
}
package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.QnaQuestion;

public interface QnaQuestionMapper {
    
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
    int insert(QnaQuestion record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int insertSelective(QnaQuestion record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    QnaQuestion selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKeySelective(QnaQuestion record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKey(QnaQuestion record);

    /**
     * @Title findByCostNum
     * @Description 后台展示列表根据costnum查询question
     * @Author Bowden
     * @CreateDate 2015-9-7 下午2:51:46
     */
	List<QnaQuestion> findByCostNumAndPage(@Param("costNumList")List<String> costNumList,
			                               @Param("startPos") Integer startPos, 
			                               @Param("pageSize") Integer pageSize,
			                               @Param("status") Integer status,
			                               @Param("tourname")String tourname,
			                               @Param("title")String title,
			                               @Param("code")String code);

	/**
	 * @Title getCountByCostNum
	 * @Description 根据costnum查询总提问记录条数
	 * @Author Bowden
	 * @CreateDate 2015-9-7 下午4:25:57
	 */
	int getCountByCostNum(@Param("costNumList")List<String> costNumList, 
			              @Param("tourname")String tourname,
                          @Param("title")String title,
                          @Param("code")String code);

	/**
	 * @Title getQuestionByQid
	 * @Description 根据questionid 查询咨询问题的详细信息
	 * @Author Bowden
	 * @CreateDate 2015-9-8 上午11:13:32
	 */
	QnaQuestion getQuestionByQid(String id);

	/**
	 * @Title editIsshow
	 * @Description 编辑是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午2:40:24
	 */
	int editIsshow(QnaQuestion question);

	/**
	 * @Title updataQStatus
	 * @Description 修改处理状态
	 * @Author Bowden
	 * @CreateDate 2015-9-9 上午10:34:01
	 */
	int updateQStatus(@Param("id")String id, @Param("status")int status);

	/**
	 * @Title findByTourlineId
	 * @Description 根据tourlineId查询
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午4:06:16
	 */
	List<QnaQuestion> findByproductId(@Param("productId")String productId, @Param("costnumber")String costnumber);
}
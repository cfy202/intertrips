package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.QnaAnswer;

public interface QnaAnswerMapper {
    
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
    int insert(QnaAnswer record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int insertSelective(QnaAnswer record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    QnaAnswer selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKeySelective(QnaAnswer record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:33
     */
    int updateByPrimaryKey(QnaAnswer record);
    
    /**
     * @Title getCountByQuestionId
     * @Description 根据questionid查询总回复条数
     * @Author Bowden
     * @CreateDate 2015-9-7 下午4:56:33
     */
    int getCountByQuestionId(String questionId);
    
    /**
     * @Title getCountByQuestionId
     * @Description 根据questionid查询总回复条数(状态：显示  已处理)
     * @Author Bowden
     * @CreateDate 2015-9-7 下午4:56:33
     */
    int getShowCountByQuestionId(String questionId);
    
    /**
     * @Title getUntreatedByQuestionId
     * @Description 根据questionId查询回复中未处理的条数
     * @Author Bowden
     * @CreateDate 2015-9-7 下午4:58:07
     */
    int getUntreatedByQuestionId(String qusetionId);

    /**
     * @Title getAnswerByQid
     * @Description 根据questionid查询回复
     * @Author Bowden
     * @CreateDate 2015-9-8 下午2:57:46
     */
	List<QnaAnswer> getAnswerByQid(String questionId);
	
	 /**
     * @Title getAnswerByQid
     * @Description 根据questionid查询回复（状态：显示  已处理）
     * @Author Bowden
     * @CreateDate 2015-9-8 下午2:57:46
     */
	List<QnaAnswer> getShowAnswerByQid(String questionId);

	/**
	 * @Title updateAnswerIsshow
	 * @Description 修改回复内容是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-8 下午4:20:17
	 */
	int updateAnswerIsshow(@Param("id")String id, @Param("isshow") Integer isshow, @Param("status")Integer status);

	/**
	 * @Title updateAStatus
	 * @Description 修改处理状态
	 * @Author Bowden
	 * @CreateDate 2015-9-9 上午11:11:32
	 */
	int updateAStatus(@Param("id")String id, @Param("status")int status);

	/**
	 * @Title getAnswerByAnswerId
	 * @Description 根据answerId查询回复详情
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午2:12:47
	 */
	QnaAnswer getAnswerByAnswerId(String id);

	/**
	 * @Title editIsshow
	 * @Description 是否显示
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午2:31:15
	 */
	int editIsshow(QnaAnswer qnaAnswer);

	/**
	 * @Title deleteAByQid
	 * @Description 根据questionid删除
	 * @Author Bowden
	 * @CreateDate 2015-9-9 下午2:47:51
	 */
	int deleteAByQid(String questionId);
}
package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Slider;

public interface SliderMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    int insert(Slider record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    int insertSelective(Slider record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    Slider selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    int updateByPrimaryKeySelective(Slider record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:20
     */
    int updateByPrimaryKey(Slider record);
    
    /**
     * 查询全部幻灯片列表
     * @author bowden
     * @return ：幻灯片图片列表集合
     */
	List<Slider> findAllByCostNumber(List<String> costnumlist);
	
	/**
	 * @Title: findByeType
	 * @Description: 根据type查询幻灯片图片
	 * @param type
	 * @return    
	 * @return List<Slider>    返回类型
	 * @author xiejin
	 */
	List<Slider> findByType(@Param("type")Integer type,@Param("costnumber")String costnumber);

	/**
	 * @Title getMaxSort
	 * @Description 获取运营中心下，对应类型的最大排序号
	 * @Author Bowden
	 * @CreateDate 2015-8-12 下午5:13:36
	 */
	Integer getMaxSort(@Param("costnumber")String costnumber, @Param("type")int type);

	/**
	 * @Title getCount
	 * @Description  查询幻灯片总条数
	 * @Author Bowden
	 * @CreateDate 2015-8-21 下午5:23:05
	 */
	int getCount();

	/**
	 * @Title selectSliderPaging
	 * @Description 分页查询
	 * @Author Bowden
	 * @CreateDate 2015-8-21 下午5:28:49
	 */
	List<Slider> selectSliderPaging(@Param("startPos")int startPos, @Param("pageSize")int pageSize);

}
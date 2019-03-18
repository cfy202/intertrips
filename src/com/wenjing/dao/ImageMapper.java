package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Image;

public interface ImageMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int insert(Image record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int insertSelective(Image record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    Image selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int updateByPrimaryKeySelective(Image record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:28
     */
    int updateByPrimaryKey(Image record);
    
    /**
     * 根据costnumber查询所有image 
     * @param costnumber
     * @return
     * xiejin
     */
    List<Image> findAllByCostNumber();

    /**
     * 获取图片总数
     * @return
     */
	int getImageCount(@Param("usetype")String usetype,@Param("usetype1")String usetype1,@Param("search")String search);

	/**
	 * 选择图片时分页查询图库图片
	 * @param startPos
	 * @param pageSize
	 * @return
	 */
	List<Image> selectImageByPage(@Param(value="startPos") Integer startPos,
			@Param(value="pageSize") Integer pageSize,
			@Param("usetype")String usetype,
			@Param("usetype1")String usetype1,@Param("search")String search);
	
	/**
	 * 根据URL查询图片
	 * @param url
	 * @return
	 * xiejin
	 */
	Image selectByUrl(String url);
	
	/**
	 * @Title: selectAllByPage
	 * @Description: 图库分类时,分页查询所有图片
	 * @param startPos
	 * @param pageSize
	 * @param constnumber
	 * @return    
	 * @return List<Image>    返回类型
	 * @author xiejin
	 */
	List<Image> selectAllByPage(@Param(value="startPos") Integer startPos,
			@Param(value="pageSize") Integer pageSize,
			@Param("usetype")String usetype,@Param("usetype1")String usetype1);
	
	/**
	 * @Title: getImageCountBycondition
	 * @Description: 图库分类时,获取图片总数
	 * @param costnumber
	 * @param usetype
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	int getImageCountBycondition(@Param("usetype")String usetype);
	
	int getImageCountWithTourline(@Param("tourlineid")String tourlineid);
	
	List<Image> selectImageByPageWithTourline(@Param(value="startPos") Integer startPos,
			@Param(value="pageSize") Integer pageSize,@Param("tourlineid")String tourlineid);
	
	/**
	 * @Title: SelectImageUseCount
	 * @Description: 查询图片使用的次数
	 * @param imageId
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	int SelectImageUseCount(String imageId);
	
}



package com.wenjing.dao;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.PageImage;

/**类说明		单页面景点联系表管理
 * @author xiejin
 * @date 2015-5-5 
 */
public  interface PageImageMapper {
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
    int insert(PageImage record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     */
    int insertSelective(PageImage record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     */
    PageImage selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKeySelective(PageImage record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     */
    int updateByPrimaryKey(PageImage record);
    
    /**
     * 根据imageid和pageid删除pageimage信息
     * @param imageId
     * @param pageid
     * @return
     * xiejin
     */
    int deleteByImageId(@Param("imageId")String imageId,@Param("pageid")String pageid);
    
    /**
     * @Title: deleteByPageId
     * @Description: 根据pageId删除单页面图片关联信息
     * @param pageId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByPageId(String pageId);
    
    /**
     * @Title: deleteByImageId
     * @Description: 根据图片id删除关联关系
     * @param imageId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    int deleteByImageId2(String imageId);
    
}

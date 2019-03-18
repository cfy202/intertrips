package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Page;

public interface PageMapper {
    int deleteByPrimaryKey(String id);

    int insert(Page record);

    int insertSelective(Page record);

    Page selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Page record);

    int updateByPrimaryKeyWithBLOBs(Page record);

    int updateByPrimaryKey(Page record);
    
    /**
     * @Title: findAll
     * @Description: 根据costnumber查询所有单页面
     * @return    
     * @return List<Page>    返回类型  
     * @author xiejin
     */
    List<Page> findAll(@Param("list")List<String> costnumbers,@Param("type")Integer type);
    
    /**
     * 
     * @param costnumbers
     * @return
     */
    List<Page> findAllContactUs(@Param("list")List<String> costnumbers);
    
    /**
     * 根据costnumbers
     * 
     * @param costnumbers
     * @return
     */
    List<Page> findAllBlog(@Param("list")List<String> costnumbers);
    
    /**
     * 根据filepath查询所有的page页面
     * 
     * @param filePath
     * @return
     */
    int findByFilePath(String filePath);
    
    /**
     * 根据filepath查询所有不包括id为exceptPageId的page页面
     * 
     * @param filePath
     * @param exceptPageId
     * @return
     */
    int findByFilePathExceptPage(@Param("filePath")String filePath,@Param("exceptPageId")String exceptPageId);
    
    int updateIsCreate(@Param("id")String id,@Param("isCreate")Integer isCreate);
    
    Page findByCostnumber(String costnumber);
    
    List<Page> findNotByCostnumber();
    
    Page findBlogNavigationPage();
    
    String findFilePathByProductId(String productId);
}
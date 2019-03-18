package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Navigation;

public interface NavigationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);
    
    /**
     * @Title: findAll
     * @Description: 查询所有导航
     * @param costnumber
     * @return    
     * @return List<Navigation>    返回类型
     * @author xiejin
     */
	List<Navigation> findAll(String costnumber);

	/**
	 * @Title: getMaxOrderId
	 * @Description: 查询最大排序
	 * @param costnumber
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	int getMaxOrderId(String costnumber);
	
	/**
	 * @Title: findByType
	 * @Description: 根据type查找导航栏表元素
	 * @param type
	 * @return    
	 * @return List<Navigation>    返回类型
	 * @author xiejin
	 */
	List<Navigation> findByType(@Param("type")Integer type,@Param("costnumber")String costnumber);
	
	/**
	 * 根据运营中心查询对应导航列表
	 * @param costnumberlist
	 * @return
	 */
	List<Navigation> findAllByCostNumber(List<String> costnumberlist);

	/**
	 * @Title: findByUpIdAndCostid
	 * @Description: 根据upid、costnumber查找导航
	 * @param upid
	 * @param costnumber
	 * @return    
	 * @return List<Navigation>    返回类型
	 * @author bowden
	 */
	List<Navigation> findByUpIdAndCostid(@Param("costnumber")String costnumber,@Param("type")Integer type);
	
	
	/**
     * @Description: 查询最大排序
     * @return    
     * @return int    返回类型
     */
    int getMaxSort(@Param("costnumber")String costnumber,@Param("type") int type);
    
    /**
	 *  更新排序号,所有>maxSort的sort+1
	 * @param maxSort
	 * @return
	 */
	int updateSort(@Param("sonmaxsort")int sonmaxsort, @Param("costnumber") String costnumber);
	
	/**
	 * 查询upid相同的sort最大值
	 * @param parentId
	 * @return
	 */
	int getMaxSortByUpId(String upid);
	
	/**
	 * 根据sort查询
	 * @param sonmaxsort
	 * @return
	 */
	Navigation getIdBySort(@Param("sort")int sort, @Param("costnumber") String costnumber);
	
	/**
	 * 修改sonmaxsort
	 * @param upId
	 * @param sort
	 * @return
	 */
	int updateSonMaxSort(@Param("upid") String upId, @Param("sort") Integer sort);
	
	/**
	 * 设置儿子的级别
	 * @param i
	 * @param oldsonmaxsort
	 * @param j
	 * @return
	 */
	int updateSonLevel(@Param("start")int start,@Param("end") int end,@Param("a") int a,@Param("costnumber") String costnumber);
	
	/**
	 * 第一个儿子到最后一个儿子的sort-(oldsort-新sort)
	 * @param i
	 * @param oldsonmaxsort
	 * @param a
	 * @return
	 */
	int updateSonSort(@Param("start")int start,@Param("end") int end,@Param("a") int a,@Param("costnumber") String costnumber);
	
	/**
	 * 修改后新sort之后的到oldsort之间的sort+(oldsort-新sort)
	 * @param i
	 * @param j
	 * @param a
	 * @return
	 */
	int updateNewSortBack(@Param("start")int start,@Param("end") int end,@Param("a") int a,@Param("costnumber") String costnumber);
	
	/**
	 * 删除所有的儿子
	 * @param start
	 * @param end
	 * @param costnumber
	 * @return
	 */
	int deleteAllSon(@Param("start")int start, @Param("end") int end, @Param("costnumber") String costnumber);
	
	/**
	 * 修改删除后的排序
	 * @param sort
	 */
	int updateDeleteSort(@Param("sort")int sort, @Param("a") int a, @Param("costnumber") String costnumber);
	
	/**
	 * 查询不等于此orderid的所有
	 * @param orderid
	 * @return
	 */
	List<Navigation> findNotContainSelf(@Param("sort")Integer sort, @Param("costnumber") String costnumber, @Param("type") int type);
	
	/**
	 * 查询不等于此orderid和其儿子的所有
	 * @param orderid
	 * @param sonmaxsort
	 * @return
	 */
	List<Navigation> findNotContainSelfAndSon(@Param("start")Integer start,@Param("end") int end, @Param("costnumber") String costnumber, @Param("type") int type);

	/**
	 * 
	 * @param i
	 * @param oldmaxson
	 * @param newtype
	 * @param costnumber
	 * @return
	 */
	int updateSonType(@Param("start")Integer start,@Param("end") int end, @Param("costnumber") String costnumber, @Param("type") int type);

	/**
	 * 查询帮助中心列表
	 * @param helpName
	 * @param costnumber
	 * @return
	 */
	List<Navigation> findHelpCenterByCostnum(@Param("helpName")String helpName,@Param("costnumber") String costnumber);
	
	/**
	 * @Title: findByType
	 * @Description: 根据type查找导航栏表元素,手机版
	 * @param type
	 * @return    
	 * @return List<Navigation>    返回类型
	 * @author xiejin
	 */
	List<Navigation> findPhoneByType(@Param("type")Integer type,@Param("costnumber")String costnumber);
	/**
	 * 根据url查询
	 * 时间：2015-11-28
	 * @param url
	 * @return
	 */
	List<Navigation> findByurl(String url);

}
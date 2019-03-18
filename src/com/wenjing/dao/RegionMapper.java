package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Region;

public interface RegionMapper {
	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:20
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:20
	 */
	int insert(Region record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:20
	 */
	int insertSelective(Region record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:20
	 */
	Region selectByPrimaryKey(String id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:20
	 */
	int updateByPrimaryKeySelective(Region record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2015-04-22 23:01:20
	 */
	int updateByPrimaryKey(Region record);

	/**
	 * @Description: 查询最大排序
	 * @return
	 * @return int 返回类型
	 */
	int getMaxSort(Integer type);

	/**
	 * @Title: findAllInCost
	 * @Description: 根据costnumber查询
	 * @param costnumber
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */

	List<Region> findAllInCost(List<String> costnumber);

	/**
	 * @Title: findByUpIdAndCostid
	 * @Description: 根据upid、costnumber查找
	 * @param upid
	 * @param costnumber
	 * @return
	 * @return List<Navigation> 返回类型
	 * @author bowden
	 */
	List<Region> findByUpIdAndCostid(@Param("type") int type,@Param("costnumber")String costnumber);

	/**
	 * @Title: findByCostnumber
	 * @Description: 根据costnumber查询所有region
	 * @param costnumber
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */
	List<Region> findByCostnumber(@Param("type") Integer type, @Param("costnumber") String costnumber);

	/**
	 * @Title: findTopRegionByCostnumber
	 * @Description: 查找线路顶层分类
	 * @param costnumber
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */
	List<Region> findTopRegionByCostnumber(@Param("costnumber") String costnumber);

	/**
	 * @Title: findDownRegiion
	 * @Description: 根据上级分类查找下级分类
	 * @param id
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */
	List<Region> findDownRegion(String id);

	/**
	 * 查询upid相同的sort最大值
	 * 
	 * @param parentId
	 * @return
	 */
	int getMaxSortByUpId(String upid);

	/**
	 * 更新排序号,所有>maxSort的sort+1
	 * 
	 * @param maxSort
	 * @return
	 */
	int updateSort(@Param("sonmaxsort") int sonmaxsort, @Param("type") Integer type);

	/**
	 * 修改删除后的排序
	 * 
	 * @param sort
	 */
	int updateDeleteSort(@Param("sort") int sort, @Param("a") int a, @Param("type")Integer type);

	/**
	 * 修改sonmaxsort
	 * 
	 * @param upId
	 * @param sort
	 * @return
	 */
	int updateSonMaxSort(@Param("upId") String upId, @Param("sort") Integer sort);

	/**
	 * 第一个儿子到最后一个儿子的sort-(oldsort-新sort)
	 * 
	 * @param i
	 * @param oldsonmaxsort
	 * @param a
	 * @return
	 */
	int updateSonSort(@Param("start") int start, @Param("end") int end, @Param("a") int a, @Param("type") Integer type);

	/**
	 * 修改后新sort之后的到oldsort之间的sort+(oldsort-新sort)
	 * 
	 * @param i
	 * @param j
	 * @param a
	 * @return
	 */
	int updateNewSortBack(@Param("start") int start, @Param("end") int end, @Param("a") int a, @Param("type") Integer type);

	/**
	 * 设置儿子的级别
	 * 
	 * @param i
	 * @param oldsonmaxsort
	 * @param j
	 * @return
	 */
	int updateSonLevel(@Param("start") int start, @Param("end") int end, @Param("a") int a, @Param("type") Integer type);

	/**
	 * 根据sort查询
	 * 
	 * @param sonmaxsort
	 * @return
	 */
	Region getIdBySort(@Param("sort")int sort, @Param("type")Integer type);

	/**
	 * 删除所有的儿子
	 * 
	 * @param start
	 * @param end
	 * @param costnumber
	 * @return
	 */
	int deleteAllSon(@Param("start") int start, @Param("end") int end, @Param("type") Integer type);

	/**
	 * 查询不等于此orderid的所有
	 * 
	 * @param orderid
	 * @return
	 */
	List<Region> findNotContainSelf(@Param("sort") Integer sort, @Param("type") Integer type);

	/**
	 * @author Sevens 时间2015-6-16
	 * @param upid
	 * @return
	 */
	public String getIdByUpid(String upid);

	/**
	 * 查询不等于此orderid和其儿子的所有
	 * 
	 * @param orderid
	 * @param sonmaxsort
	 * @return
	 */
	List<Region> findNotContainSelfAndSon(@Param("start") Integer start, @Param("end") int end, @Param("type") Integer type);

	/**
	 * @Title: findIdByUrl
	 * @Description: 根据url查询对应线路分类的id
	 * @param url
	 * @return
	 * @return String 返回类型
	 * @author xiejin
	 */
	String findIdByUrl(String url);

	/**
	 * @author Sevens 时间2015-6-28
	 * @param costnumber
	 * @param type
	 * @return
	 */
	List<Region> findAllInCostBytype(
			@Param("costnumber") List<String> costnumber,
			@Param("type") int type);

	/**
	 * 读取所有分类信息（同一运营中心，同一产品分类）
	 * 
	 * @param type
	 * @param costnumber
	 * @return
	 */
	List<Region> findAllByCostnumber(@Param("type")Integer type,@Param("costnumber") String costnumber);

	/**
	 * 查询全部分类
	 * 
	 * @return
	 */
	List<Region> findAll();
	/**
	 * 更新目的地冗余字段
	 * @author Sevens
	 * 时间2015-8-5
	 * @param destinationList
	 * @param id
	 * @return
	 */
	int updateRegionDestinationList(@Param("destinationList")String destinationList,@Param("id")String id);

	/**
	 * @Title findUpSort
	 * @Description 向上移动，查询移动到位置的sort
	 * @Author Bowden
	 * @CreateDate 2015-8-17 上午9:45:15
	 */
	int findUpSort(@Param("type")Integer type, @Param("upid")String upid, @Param("sort")Integer sort);

	/**
	 * @Title updateSortById
	 * @Description TODO
	 * @Author Bowden
	 * @CreateDate 2015-8-17 下午3:25:59
	 */
	int updateSortById(@Param("sort")int sort, @Param("id")String id);

	/**
	 * @Title findDownSort
	 * @Description 向下移动，查询移动到位置的sort
	 * @Author Bowden
	 * @CreateDate 2015-8-17 下午6:09:14
	 */
	int findDownSort(@Param("type")Integer type, @Param("upid")String upid, @Param("sort")Integer sort);

	/**
	 * @Title findByUrl
	 * @Description 根据url查找分类
	 * @Author Bowden
	 * @CreateDate 2015-8-19 下午3:46:54
	 */
	Region findByUrl(String url);

	/**
	 * @Title findRgionAndPageById
	 * @Description 根据regionid查询分类和page
	 * @Author Bowden
	 * @CreateDate 2015-8-21 上午11:40:08
	 */
	Region findRgionAndPageById(String id);
}
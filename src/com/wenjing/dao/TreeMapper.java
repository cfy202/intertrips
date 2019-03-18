package com.wenjing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wenjing.entity.Tree;

public interface TreeMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int insert(Tree record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int insertSelective(Tree record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    Tree selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int updateByPrimaryKeySelective(Tree record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-22 23:01:23
     */
    int updateByPrimaryKey(Tree record);
    
    /**
     * sevens
     * 2015-4-23
     * @return
     */
    List<Tree> findAll();
    
    /**
     * 查询总记录数用于排序
     * @return
     */
    int getMaxSort();
    
    /**
     * 获取指定parentid的列表
     * 参数：parentId -- 上级id
     * 返回：目录树
     * 作者：fred
     * 时间：2015-5-6
     */
    List<Tree> findAllByParent(String parentId);
    
    /**
     * 获取指定目录的最大排序号
     * 参数：parentId -- 上级id
     * 返回：最大数
     * 作者：fred
     * 时间：2015-5-6
     */
    int getParentMaxSort(String parentId);
    
	/**
	 * 获取角色目录
	 * @param roleId
	 * @return
	 */
	List<Tree> findAllByRole(String roleId);

	
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
	Tree getIdBySort(int sort);
	
	/**
	 *  更新排序号,所有>maxSort的sort+1
	 * @param maxSort
	 * @return
	 */
	int updateSort(int sonmaxsort);
	
	/**
	 * 修改sonmaxsort
	 * @param upId
	 * @param sort
	 * @return
	 */
	int updateSonMaxSort(@Param("upId") String upId, @Param("sort") Integer sort);
	
	/**
	 * 设置儿子的级别
	 * @param i
	 * @param oldsonmaxsort
	 * @param j
	 * @return
	 */
	int updateSonLevel(@Param("start")int start,@Param("end") int end,@Param("a") int a);
	
	/**
	 * 第一个儿子到最后一个儿子的sort-(oldsort-新sort)
	 * @param i
	 * @param oldsonmaxsort
	 * @param a
	 * @return
	 */
	int updateSonSort(@Param("start")int start,@Param("end") int end,@Param("a") int a);
	
	/**
	 * 修改后新sort之后的到oldsort之间的sort+(oldsort-新sort)
	 * @param i
	 * @param j
	 * @param a
	 * @return
	 */
	int updateNewSortBack(@Param("start")int start,@Param("end") int end,@Param("a") int a);
	
	/**
	 * 删除所有的儿子
	 * @param start
	 * @param end
	 * @param costnumber
	 * @return
	 */
	int deleteAllSon(@Param("start")int start, @Param("end") int end);
	
	/**
	 * 修改删除后的排序
	 * @param sort
	 */
	int updateDeleteSort(@Param("sort")int sort, @Param("a") int a);

	/**
	 * 查询不等于此orderid的所有
	 * @param orderid
	 * @return
	 */
	List<Tree> findNotContainSelf(Integer orderid);

	/**
	 * 查询不等于此orderid和其儿子的所有
	 * @param orderid
	 * @param sonmaxsort
	 * @return
	 */
	List<Tree> findNotContainSelfAndSon(@Param("start")Integer start,@Param("end") int end);
}
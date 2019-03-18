package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.TreeMapper;
import com.wenjing.entity.Tree;

/**
 * 说明 后台产品分类管理
 * 
 * @author sevens
 * 
 */
@Service
public class TreeService {

	@Resource
	private TreeMapper treeMapper;

	/**
	 * 查询所有tree
	 * 
	 * @return sevens
	 */
	@Transactional(readOnly = true)
	public List<Tree> findAll() {
		return treeMapper.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Tree> findAllByRole(String roleId) {
		return treeMapper.findAllByRole(roleId);
	}

	/**
	 * 根据id删除Tree
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void delete(String id) {
		Tree tree = treeMapper.selectByPrimaryKey(id);
		int sort = 0;
		if (tree != null) {
			sort = tree.getOrderid();
			int oldSonMaxSort = treeMapper.getMaxSortByUpId(id);//查询此修改项儿子的最大排序号
			int maxson = oldSonMaxSort;
			int oldSonMaxSort1 = oldSonMaxSort;
			if(oldSonMaxSort != 0){  //有儿子
				while (true) {
					Tree tree2 = treeMapper.getIdBySort(oldSonMaxSort1);// 查询最大排序号的id
					oldSonMaxSort1 = treeMapper.getMaxSortByUpId(tree2.getId());//查询此修改项儿子的最大排序号
					if(oldSonMaxSort1 == 0){
						break;
					}
					maxson = oldSonMaxSort1;
				}
				treeMapper.deleteAllSon(sort+1, maxson);//删除所有子集
				treeMapper.updateDeleteSort(maxson, maxson-sort+1); // 修改删除后的排序-删除项和儿子的总个数
			}else {
				treeMapper.updateDeleteSort(sort, 1); // 修改删除后的排序-1
			}
			treeMapper.deleteByPrimaryKey(id); //删除
		}
	}

	/**
	 * 根据id查询tree
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Tree findById(String id) {
		return treeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存tree
	 * 
	 * @param tree
	 * @return
	 */
	@Transactional
	public void save(Tree tree) {
		String upId = tree.getParentid();
		if ("root".equals(upId)) {
			tree.setLevel(0);
			tree.setSonmaxsort(0);
			int maxsort = treeMapper.getMaxSort();// 获取当前运营中心下最大排序号
			tree.setOrderid(maxsort + 1);
			treeMapper.insert(tree);
		} else {
			int sonmaxsort = 0;
			Tree treeParent = treeMapper.selectByPrimaryKey(upId); // 获取父级
			int newParentSonMaxSort = treeMapper.getMaxSortByUpId(upId); 
			if (newParentSonMaxSort == 0) {
				sonmaxsort = treeParent.getOrderid();
			}else {
				sonmaxsort = newParentSonMaxSort;
				while (true) {
					Tree tree2 = treeMapper.getIdBySort(newParentSonMaxSort);// 查询最大排序号的id
					newParentSonMaxSort = treeMapper.getMaxSortByUpId(tree2.getId()); 
					if(newParentSonMaxSort == 0){
						break;
					}
					sonmaxsort = newParentSonMaxSort;
				}
			}
			treeMapper.updateSort(sonmaxsort); // 更新排序号,所有>sonmaxsort的sort+1
			tree.setOrderid(sonmaxsort + 1); // 设置当前排序号
			tree.setLevel(treeParent.getLevel() + 1); // 设置级别
			tree.setSonmaxsort(0);
			treeMapper.insert(tree);
		}
	}

	/**
	 * 修改tree
	 * 
	 * @param tree
	 * @return 
	 */
	@Transactional
	public void update(Tree tree, Tree oldtree) {
		String newUpId = tree.getParentid();
		String oldUpId = oldtree.getParentid();
		int oldsort = oldtree.getOrderid();
		int newmaxson = 0;
		int oldmaxson = 0;
		int a = 0;
		int b = 0;
		// 如果上级目录发生变化
		if (!newUpId.equals(oldUpId)) {
			treeMapper.updateSonMaxSort("", 0);//清空sonmaxsort = 0
			int oldSonMaxSort = treeMapper.getMaxSortByUpId(oldtree.getId());//查询此修改项儿子的最大排序号
			int sort1 = oldsort + 1;
			int sort2 = oldSonMaxSort;
			oldmaxson = oldSonMaxSort;
			int oldSonMaxSort1 = oldSonMaxSort;
			if ("root".equals(newUpId)) {
			    newmaxson = treeMapper.getMaxSort();// 获取当前运营中心下最大排序号为新父级儿子最大排序号
			    if (newmaxson == 0) {
					newmaxson = 1;
				}
				tree.setLevel(0);
				int sonlevel = tree.getLevel() + 1;
				if(oldSonMaxSort != 0){
					while (true) {
						treeMapper.updateSonLevel(sort1, sort2, sonlevel++);// 设置儿子级别
						Tree tree2 = treeMapper.getIdBySort(oldSonMaxSort1);// 查询最大排序号的id
						oldSonMaxSort1 = treeMapper.getMaxSortByUpId(tree2.getId());//查询此修改项儿子的最大排序号
						if(oldSonMaxSort1 == 0){
							break;
						}
						sort1 = ++sort2;
						oldmaxson = oldSonMaxSort1;
						sort2 = oldSonMaxSort1;
					}
				}
				if(oldSonMaxSort == 0){
					tree.setOrderid(newmaxson);
					treeMapper.updateSonSort(oldsort+1, newmaxson, 1);//oldsort到新父级最大sort之间的sort-1
				}else {
					a = oldmaxson-oldsort+1; 
					b = newmaxson-oldmaxson;
					tree.setOrderid(oldsort+b);
					treeMapper.updateSonSort(oldmaxson+1, newmaxson, a);//oldsonmaxsort到sonmaxsort之间的sort-a
					treeMapper.updateNewSortBack(oldsort+1, oldmaxson, b);//第一个儿子到最后一个儿子的sort+b
				}
				treeMapper.updateByPrimaryKeySelective(tree);
			} else {
				Tree newtreeParent = treeMapper.selectByPrimaryKey(newUpId); // 获取父级
				tree.setLevel(newtreeParent.getLevel() + 1); // 设置级别
				int newParentSonMaxSort = treeMapper.getMaxSortByUpId(newUpId); 
				if (newParentSonMaxSort == 0) {
					newmaxson = newtreeParent.getOrderid();
				}else {
					newmaxson = newParentSonMaxSort;
					while (true) {
						Tree tree2 = treeMapper.getIdBySort(newParentSonMaxSort);// 查询最大排序号的id
						newParentSonMaxSort = treeMapper.getMaxSortByUpId(tree2.getId()); 
						if(newParentSonMaxSort == 0){
							break;
						}
						newmaxson = newParentSonMaxSort;
					}
				}
				
				int sonlevel = tree.getLevel() + 1;
				if(oldSonMaxSort != 0){
					while (true) {
						treeMapper.updateSonLevel(sort1, sort2, sonlevel++);// 设置儿子级别
						Tree tree2 = treeMapper.getIdBySort(oldSonMaxSort1);// 查询最大排序号的id
						oldSonMaxSort1 = treeMapper.getMaxSortByUpId(tree2.getId());//查询此修改项儿子的最大排序号
						if(oldSonMaxSort1 == 0){
							break;
						}
						sort1 = ++sort2;
						oldmaxson = oldSonMaxSort1;
						sort2 = oldSonMaxSort1;
					}
				}
				if(oldsort > newmaxson){
					tree.setOrderid(newmaxson+1);
					if(oldSonMaxSort == 0){ //无儿子
						treeMapper.updateNewSortBack(tree.getOrderid(), oldsort - 1, 1);// 修改后新sort之后的到oldsort之间的sort+1
					}else {
						a = oldsort - tree.getOrderid();
						b = oldmaxson - oldsort + 1;
						treeMapper.updateSonSort(oldsort + 1, oldmaxson, a);// 第一个儿子到最后一个儿子的sort-(oldsort-新sort)
						treeMapper.updateNewSortBack(tree.getOrderid(), oldsort - 1, b);// 修改后新sort之后的到oldsort之间的sort+(oldsort-新sort)
					}
				}else {
					if(oldSonMaxSort == 0){
						tree.setOrderid(newmaxson);
						treeMapper.updateSonSort(oldsort+1, newmaxson, 1);//oldsort到新父级最大sort之间的sort-1
					}else {
						a = oldmaxson-oldsort+1; 
						b = newmaxson-oldmaxson;
						tree.setOrderid(oldsort+b);
						treeMapper.updateSonSort(oldmaxson+1, newmaxson, a);//oldsonmaxsort到sonmaxsort之间的sort-a
						treeMapper.updateNewSortBack(oldsort+1, oldmaxson, b);//第一个儿子到最后一个儿子的sort+b
					}
				}
				treeMapper.updateByPrimaryKeySelective(tree);
				}
		}else {
			treeMapper.updateByPrimaryKeySelective(tree);
		}
	}

	/**
	 * 查询最大sort
	 * 
	 * @return xiejin
	 */
	@Transactional
	public int getOrderId() {
		return treeMapper.getMaxSort();
	}

	/**
	 * 获取指定 parentid 的目录列表
	 * 
	 * @param parentId
	 * @return fred 2015-5-6
	 */
	@Transactional
	public List<Tree> findAllByParent(String parentId) {
		return treeMapper.findAllByParent(parentId);
	}

	/**
	 * 查询可选上级列表
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Tree> findNotContainSon(Tree tree) {
		int sonmaxsort = 0;
		List<Tree> trees = null;
		int newParentSonMaxSort = treeMapper.getMaxSortByUpId(tree.getId()); 
		if (newParentSonMaxSort == 0) { //没有儿子
			sonmaxsort = tree.getOrderid();
		    trees = treeMapper.findNotContainSelf(tree.getOrderid());
		}else {
			sonmaxsort = newParentSonMaxSort;
			while (true) {
				Tree tree2 = treeMapper.getIdBySort(newParentSonMaxSort);// 查询最大排序号的id
				newParentSonMaxSort = treeMapper.getMaxSortByUpId(tree2.getId()); 
				if(newParentSonMaxSort == 0){
					break;
				}
				sonmaxsort = newParentSonMaxSort;
			}
			trees = treeMapper.findNotContainSelfAndSon(tree.getOrderid(), sonmaxsort);
		}
		return trees;
	}

}

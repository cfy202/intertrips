package com.wenjing.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.dao.NavigationMapper;
import com.wenjing.entity.Navigation;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-4 上午9:29:10
 *  类说明 : 导航管理 Service
 */
@Service
public class NavigationService {
	@Resource
	private NavigationMapper navigationMapper;

	/**
	 * 查询全部导航项
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Navigation> findAllByCostNumber(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> coList = new ArrayList<String>();
		String costId = request.getParameter("costId");
		//分销售中心查询
		if (costId == null || "".equals(costId)) {
			costId = WebUtils.getCookie(request, "navigationCostId");
			if (costId == null || "".equals(costId)) {
				coList = Tools.getCostNumber(request);
			}else {
				coList.add(costId);
			}
		} else {
			coList.add(costId);
			WebUtils.addCookie(request, response, "navigationCostId", costId);
		}
		
		List<Navigation> navigations = navigationMapper.findAllByCostNumber(coList);
		model.addAttribute("costNumList", coList);
		return navigations;
		
	}

	/**
	 * 根据id查询导航项
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Navigation findById(String id) {
		return navigationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改导航项
	 * 
	 * @param navigation
	 * @return
	 */
	@Transactional
	public void update(Navigation navigation, Navigation oldnavigation) {
		String costnumber = navigation.getCostnumber();
		String newUpId = navigation.getUpid();
		int newtype = navigation.getType();
		int oldtype = oldnavigation.getType();
		String oldUpId = oldnavigation.getUpid();
		int oldsort = oldnavigation.getOrderid();
		int newmaxson = 0;
		int oldmaxson = 0;
		int a = 0;
		int b = 0;
		navigationMapper.updateSonMaxSort("", 0);//清空sonmaxsort = 0
		// 如果上级目录发生变化
		if (!newUpId.equals(oldUpId)) {
			int oldSonMaxSort = navigationMapper.getMaxSortByUpId(oldnavigation.getId());//查询此修改项儿子的最大排序号
			int sort1 = oldsort + 1;
			int sort2 = oldSonMaxSort;
			oldmaxson = oldSonMaxSort;
			int oldSonMaxSort1 = oldSonMaxSort;
			if ("root".equals(newUpId)) {
				newmaxson = navigationMapper.getMaxSort(costnumber, newtype);// 获取当前运营中心下最大排序号为新父级儿子最大排序号
			    if (newmaxson == 0) { //如果没有
			    	switch (newtype) {
					case 1:
						newmaxson = 1;
						break;
                    case 2:
						newmaxson = navigationMapper.getMaxSort(costnumber, 1);
						break;
					default:
						break;
					}
				}
				navigation.setLevel(0);
				int sonlevel = navigation.getLevel() + 1;
				if(oldSonMaxSort != 0){
					while (true) {
						navigationMapper.updateSonLevel(sort1, sort2, sonlevel++, costnumber);// 设置儿子级别
						Navigation navigation2 = navigationMapper.getIdBySort(oldSonMaxSort1, costnumber);// 查询最大排序号的id
						oldSonMaxSort1 = navigationMapper.getMaxSortByUpId(navigation2.getId());//查询此修改项儿子的最大排序号
						if(oldSonMaxSort1 == 0){
							break;
						}
						sort1 = ++sort2;
						oldmaxson = oldSonMaxSort1;
						sort2 = oldSonMaxSort1;
					}
				}
				if(oldSonMaxSort == 0){
					navigation.setOrderid(newmaxson);
					navigationMapper.updateSonSort(oldsort+1, newmaxson, 1, costnumber);//oldsort到新父级最大sort之间的sort-1
				}else {
					a = oldmaxson-oldsort+1; 
					b = newmaxson-oldmaxson;
					navigation.setOrderid(oldsort+b);
					if(newtype != oldtype){ //如果导航类型发生改变
						navigationMapper.updateSonType(oldsort+1, oldmaxson, costnumber, newtype);
					}
					navigationMapper.updateSonSort(oldmaxson+1, newmaxson, a, costnumber);//oldsonmaxsort到sonmaxsort之间的sort-a
					navigationMapper.updateNewSortBack(oldsort+1, oldmaxson, b, costnumber);//第一个儿子到最后一个儿子的sort+b
				}
				navigationMapper.updateByPrimaryKeySelective(navigation);
			} else {
				Navigation newnavigationParent = navigationMapper.selectByPrimaryKey(newUpId); // 获取父级
				navigation.setLevel(newnavigationParent.getLevel() + 1); // 设置级别
				int newParentSonMaxSort = navigationMapper.getMaxSortByUpId(newUpId); 
				if (newParentSonMaxSort == 0) {
					newmaxson = newnavigationParent.getOrderid();
				}else {
					newmaxson = newParentSonMaxSort;
					while (true) {
						Navigation navigation2 = navigationMapper.getIdBySort(newParentSonMaxSort, costnumber);// 查询最大排序号的id
						newParentSonMaxSort = navigationMapper.getMaxSortByUpId(navigation2.getId()); 
						if(newParentSonMaxSort == 0){
							break;
						}
						newmaxson = newParentSonMaxSort;
					}
				}
				
				int sonlevel = navigation.getLevel() + 1;
				if(oldSonMaxSort != 0){
					while (true) {
						navigationMapper.updateSonLevel(sort1, sort2, sonlevel++, costnumber);// 设置儿子级别
						Navigation navigation2 = navigationMapper.getIdBySort(oldSonMaxSort1, costnumber);// 查询最大排序号的id
						oldSonMaxSort1 = navigationMapper.getMaxSortByUpId(navigation2.getId());//查询此修改项儿子的最大排序号
						if(oldSonMaxSort1 == 0){
							break;
						}
						sort1 = ++sort2;
						oldmaxson = oldSonMaxSort1;
						sort2 = oldSonMaxSort1;
					}
				}
				if(oldsort > newmaxson){
					navigation.setOrderid(newmaxson+1);
					if(oldSonMaxSort == 0){ //无儿子
						navigationMapper.updateNewSortBack(navigation.getOrderid(), oldsort - 1, 1, costnumber);// 修改后新sort之后的到oldsort之间的sort+1
					}else {
						a = oldsort - navigation.getOrderid();
						b = oldmaxson - oldsort + 1;
						if(newtype != oldtype){ //如果导航类型发生改变
							navigationMapper.updateSonType(oldsort+1, oldmaxson, costnumber, newtype);
						}
						navigationMapper.updateSonSort(oldsort + 1, oldmaxson, a, costnumber);// 第一个儿子到最后一个儿子的sort-(oldsort-新sort)
						navigationMapper.updateNewSortBack(navigation.getOrderid(), oldsort - 1, b, costnumber);// 修改后新sort之后的到oldsort之间的sort+(oldsort-新sort)
					}
				}else {
					if(oldSonMaxSort == 0){
						navigation.setOrderid(newmaxson);
						navigationMapper.updateSonSort(oldsort+1, newmaxson, 1, costnumber);//oldsort到新父级最大sort之间的sort-1
					}else {
						a = oldmaxson-oldsort+1; 
						b = newmaxson-oldmaxson;
						navigation.setOrderid(oldsort+b);
						if(newtype != oldtype){ //如果导航类型发生改变
							navigationMapper.updateSonType(oldsort+1, oldmaxson, costnumber, newtype);
						}
						navigationMapper.updateSonSort(oldmaxson+1, newmaxson, a, costnumber);//oldsonmaxsort到sonmaxsort之间的sort-a
						navigationMapper.updateNewSortBack(oldsort+1, oldmaxson, b, costnumber);//第一个儿子到最后一个儿子的sort+b
					}
				}
				navigationMapper.updateByPrimaryKeySelective(navigation);
				}
		}else {
			if(newtype != oldtype){ //如果导航类型发生改变,即类型为1的root变为类型为2的root
				int oldSonMaxSort = navigationMapper.getMaxSortByUpId(oldnavigation.getId());//查询此修改项儿子的最大排序号
				int sort1 = oldsort + 1;
				int sort2 = oldSonMaxSort;
				oldmaxson = oldSonMaxSort;
				int oldSonMaxSort1 = oldSonMaxSort;
				newmaxson = navigationMapper.getMaxSort(costnumber, newtype);// 获取当前运营中心下最大排序号为新父级儿子最大排序号
			    if (newmaxson == 0) { //如果没有
			    	switch (newtype) {
					case 1:
						newmaxson = 0;
						break;
                    case 2:
						newmaxson = navigationMapper.getMaxSort(costnumber, 1);
						break;
					default:
						break;
					}
				}
				navigation.setLevel(0);
				int sonlevel = navigation.getLevel() + 1;
				if(oldSonMaxSort != 0){
					while (true) {
						navigationMapper.updateSonLevel(sort1, sort2, sonlevel++, costnumber);// 设置儿子级别
						Navigation navigation2 = navigationMapper.getIdBySort(oldSonMaxSort1, costnumber);// 查询最大排序号的id
						oldSonMaxSort1 = navigationMapper.getMaxSortByUpId(navigation2.getId());//查询此修改项儿子的最大排序号
						if(oldSonMaxSort1 == 0){
							break;
						}
						sort1 = ++sort2;
						oldmaxson = oldSonMaxSort1;
						sort2 = oldSonMaxSort1;
					}
				}
				if(oldsort > newmaxson){
					navigation.setOrderid(newmaxson+1);
					if(oldSonMaxSort == 0){ //无儿子
						navigationMapper.updateNewSortBack(navigation.getOrderid(), oldsort - 1, 1, costnumber);// 修改后新sort之后的到oldsort之间的sort+1
					}else {
						a = oldsort - navigation.getOrderid();
						b = oldmaxson - oldsort + 1;
						navigationMapper.updateSonType(oldsort+1, oldmaxson, costnumber, newtype);
						navigationMapper.updateSonSort(oldsort + 1, oldmaxson, a, costnumber);// 第一个儿子到最后一个儿子的sort-(oldsort-新sort)
						navigationMapper.updateNewSortBack(navigation.getOrderid(), oldsort - 1, b, costnumber);// 修改后新sort之后的到oldsort之间的sort+(oldsort-新sort)
					}
				}else {
					if(oldSonMaxSort == 0){
						navigation.setOrderid(newmaxson);
						navigationMapper.updateSonSort(oldsort+1, newmaxson, 1, costnumber);//oldsort到新父级最大sort之间的sort-1
					}else {
						a = oldmaxson-oldsort+1; 
						b = newmaxson-oldmaxson;
						navigation.setOrderid(oldsort+b);
						navigationMapper.updateSonType(oldsort+1, oldmaxson, costnumber, newtype);
						navigationMapper.updateSonSort(oldmaxson+1, newmaxson, a, costnumber);//oldsonmaxsort到sonmaxsort之间的sort-a
						navigationMapper.updateNewSortBack(oldsort+1, oldmaxson, b, costnumber);//第一个儿子到最后一个儿子的sort+b
					}
				}
		}
			navigationMapper.updateByPrimaryKeySelective(navigation);
		}
	}

	/**
	 * 新增导航项
	 * 
	 * @param navigation
	 * @return
	 */
	@Transactional
	public void insert(Navigation navigation) {
		navigation.setId(Tools.getUUID());
		String upId = navigation.getUpid();
		String costnumber = navigation.getCostnumber();
		int type = navigation.getType();//获取类型
		if ("root".equals(upId)) {
			navigation.setLevel(0);
			navigation.setSonmaxsort(0);
			int maxsort = navigationMapper.getMaxSort(costnumber, type);// 获取当前运营中心、此类型下的最大排序号
			navigationMapper.updateSort(maxsort, costnumber);//将此运营中心下>maxsort的排序+1
			navigation.setOrderid(maxsort + 1);
			navigationMapper.insert(navigation);
		} else {
			int sonmaxsort = 0;
			Navigation navigationParent = navigationMapper.selectByPrimaryKey(upId); // 获取父级
			int newParentSonMaxSort = navigationMapper.getMaxSortByUpId(upId); 
			if (newParentSonMaxSort == 0) {
				sonmaxsort = navigationParent.getOrderid();
			}else {
				sonmaxsort = newParentSonMaxSort;
				while (true) {
					Navigation navigation2 = navigationMapper.getIdBySort(newParentSonMaxSort, costnumber);// 查询最大排序号的id
					newParentSonMaxSort = navigationMapper.getMaxSortByUpId(navigation2.getId()); 
					if(newParentSonMaxSort == 0){
						break;
					}
					sonmaxsort = newParentSonMaxSort;
				}
			}
			navigationMapper.updateSort(sonmaxsort, costnumber); // 更新排序号,所有>sonmaxsort的sort+1
			navigation.setOrderid(sonmaxsort + 1); // 设置当前排序号
			navigation.setLevel(navigationParent.getLevel() + 1); // 设置级别
			navigation.setSonmaxsort(0);
			navigationMapper.insert(navigation);
		}
	}

	/**
	 * 根据id删除导航项
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void delete(String id) {
		Navigation navigation = navigationMapper.selectByPrimaryKey(id);
		int sort = 0;
		String costnumber = "";
		if (navigation != null) {
			sort = navigation.getOrderid();
			costnumber = navigation.getCostnumber();
			int oldSonMaxSort = navigationMapper.getMaxSortByUpId(id);//查询此修改项儿子的最大排序号
			int maxson = oldSonMaxSort;
			int oldSonMaxSort1 = oldSonMaxSort;
			if(oldSonMaxSort != 0){  //有儿子
				while (true) {
					Navigation navigation2 = navigationMapper.getIdBySort(oldSonMaxSort1, costnumber);// 查询最大排序号的id
					oldSonMaxSort1 = navigationMapper.getMaxSortByUpId(navigation2.getId());//查询此修改项儿子的最大排序号
					if(oldSonMaxSort1 == 0){
						break;
					}
					maxson = oldSonMaxSort1;
				}
				navigationMapper.deleteAllSon(sort+1, maxson, costnumber);//删除所有子集
				navigationMapper.updateDeleteSort(maxson, maxson-sort+1, costnumber); // 修改删除后的排序-删除项和儿子的总个数
			}else {
				navigationMapper.updateDeleteSort(sort, 1, costnumber); // 修改删除后的排序-1
			}
			navigationMapper.deleteByPrimaryKey(id); //删除
		}
	}

	/**
	 * @Title: findByType
	 * @Description: 根据type查找导航栏表元素
	 * @param type
	 * @return    
	 * @return List<Navigation>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public List<Navigation> findByType(Integer type,String costnumber){
		return navigationMapper.findByType(type,costnumber);
	}
	
	/**
	 * @Title: findByUpIdAndCostid
	 * @Description: 根据upid和costnumber查找导航
	 * @param upid
	 * @param costnumber
	 * @return    
	 * @return List<Navigation>    返回类型
	 * @author bowden
	 */
	@Transactional(readOnly=true)
	public List<Navigation> findByUpIdAndCostid(String costnumber,int type) {
		List<Navigation> navigations = navigationMapper.findByUpIdAndCostid(costnumber,type);
		for (Navigation navigation : navigations) {
			String string = "";
			for(int i=0; i<navigation.getLevel();i++) {
				string += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			string += "|--&nbsp;";
			navigation.setLevelstr(string);
		}
		return navigations;
	}
	
	/**
	 * 根据运营中心costnumber,查询此运营中心orderid最大值
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMaxOrderId(String costnumber) {
		return navigationMapper.getMaxOrderId(costnumber);
	}
	
	/**
	 * 查询可选上级列表
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Navigation> findNotContainSon(Navigation navigation) {
		int sonmaxsort = 0;
		String costnumber = navigation.getCostnumber();
		int type = navigation.getType();
		List<Navigation> navigations = null;
		int newParentSonMaxSort = navigationMapper.getMaxSortByUpId(navigation.getId()); 
		if (newParentSonMaxSort == 0) { //没有儿子
			sonmaxsort = navigation.getOrderid();
			navigations = navigationMapper.findNotContainSelf(sonmaxsort, costnumber, type);
		}else {
			sonmaxsort = newParentSonMaxSort;
			while (true) {
				Navigation navigation2 = navigationMapper.getIdBySort(newParentSonMaxSort, costnumber);// 查询最大排序号的id
				newParentSonMaxSort = navigationMapper.getMaxSortByUpId(navigation2.getId()); 
				if(newParentSonMaxSort == 0){
					break;
				}
				sonmaxsort = newParentSonMaxSort;
			}
			navigations = navigationMapper.findNotContainSelfAndSon(navigation.getOrderid(), sonmaxsort, costnumber, type);
		}
		for (Navigation n : navigations) {
			String string = "";
			for(int i=0; i<n.getLevel();i++) {
				string += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			string += "|--&nbsp;";
			n.setLevelstr(string);
		}
		return navigations;
	}

	/**
	 * 查询帮助中心列表
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Navigation> findHelpCenterByCostnum(String helpName, String costnumber) {
		return navigationMapper.findHelpCenterByCostnum(helpName, costnumber);
	}
	/**
	 * 根据url查询导航的父节点
	 * @author Administrator
	 * 时间：2015-11-28
	 * @param url
	 * @return
	 */
	@Transactional(readOnly=true)
	public Navigation findByurl(String url){
		Navigation nav = null;
		List<Navigation> navigations = navigationMapper.findByurl(url);
		if(navigations!=null&&navigations.size()>0){
			nav = navigations.get(0);			
		}
		return nav;
	}
}

package com.wenjing.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.dao.PageMapper;
import com.wenjing.dao.RegionMapper;
import com.wenjing.entity.Page;
import com.wenjing.entity.Price;
import com.wenjing.entity.Region;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 说明 后台产品分类管理
 * 
 * @author sevens
 * 
 */
@Service
public class RegionService {

	@Resource
	private RegionMapper regionMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private PageMapper pageMapper;

	/**
	 * 根据id删除region
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void delete(String id) {
		Region region = regionMapper.selectByPrimaryKey(id);
		int sort = 0;
		int type = 0;
		if (region != null) {
			sort = region.getSort();
			type = region.getType();
			String pageid = region.getPageid();
			int oldSonMaxSort = regionMapper.getMaxSortByUpId(id);// 查询此删除项儿子的最大排序号
			int maxson = oldSonMaxSort;
			int oldSonMaxSort1 = oldSonMaxSort;
			if (oldSonMaxSort != 0) { // 有儿子
				while (true) {
					Region region2 = regionMapper.getIdBySort(oldSonMaxSort1, type);// 查询次删除项儿子最大排序号的id
					oldSonMaxSort1 = regionMapper.getMaxSortByUpId(region2.getId());// 查询此删除项孙子的最大排序号
					if (oldSonMaxSort1 == 0) {
						break;
					}
					maxson = oldSonMaxSort1;
				}
				regionMapper.deleteAllSon(sort + 1, maxson, type);// 删除所有子集
				regionMapper.updateDeleteSort(maxson, maxson - sort + 1, type); // 修改删除后的排序-删除项和儿子的总个数
			} else {
				regionMapper.updateDeleteSort(sort, 1, type); // 修改删除后的排序-1
			}
			if(pageid !=null && !"".equals(pageid)){
				pageMapper.deleteByPrimaryKey(pageid); //删除page表对应信息
			}
			regionMapper.deleteByPrimaryKey(id); // 删除
		}
	}

	/**
	 * 根据id查询region
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Region findById(String id) {
		return regionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存region
	 * 
	 * @param region
	 * @return
	 */
	@Transactional
	public void save(Region region, Page page) {
		String upId = region.getUpid();
		String pageid = Tools.getUUID();
		int success = 0;
		int type = region.getType();
		if ("root".equals(upId)) {
			region.setLevel(0);
			region.setSonmaxsort(0);
			int maxsort = regionMapper.getMaxSort(type);// 获取当前类型下最大排序号
			region.setSort(maxsort + 1);
			region.setPageid(pageid);
			success = regionMapper.insert(region);
		} else {
			int sonmaxsort = 0;
			Region regionParent = regionMapper.selectByPrimaryKey(upId); // 获取父级
			int newParentSonMaxSort = regionMapper.getMaxSortByUpId(upId);
			if (newParentSonMaxSort == 0) {
				sonmaxsort = regionParent.getSort();
			} else {
				sonmaxsort = newParentSonMaxSort;
				while (true) {
					Region region2 = regionMapper.getIdBySort(newParentSonMaxSort, type);// 查询最大排序号的id
					newParentSonMaxSort = regionMapper.getMaxSortByUpId(region2.getId());
					if (newParentSonMaxSort == 0) {
						break;
					}
					sonmaxsort = newParentSonMaxSort;
				}
			}
			regionMapper.updateSort(sonmaxsort, type); // 更新排序号,所有>sonmaxsort的sort+1
			region.setSort(sonmaxsort + 1); // 设置当前排序号
			region.setLevel(regionParent.getLevel() + 1); // 设置级别
			region.setSonmaxsort(0);
			region.setPageid(pageid);
			region.setCostNumberIds(region.getCostnumber());
		    success = regionMapper.insert(region);
		}
		if(success>0){
			page.setId(pageid);
			page.setFilepath(region.getUrl());
			page.setType(1);
			pageMapper.insert(page);
		}
	}

	/**
	 * 修改region
	 * 
	 * @param region
	 * @return
	 * 
	 */
	@Transactional
	public void update(Region region, Region oldregion, Page page) {
		List<String> costNumIdList = Tools.getCostNumber(request);
		if(costNumIdList!=null && costNumIdList.size()==1){ //非管理员操作
			String[] salesCenter = request.getParameterValues("salesCenter");
			if(salesCenter !=null){
				region.setCostNumberIds(Tools.stringArrToString(salesCenter));
			}
		}
		String newUpId = region.getUpid();
		int type = region.getType();
		String pageid = region.getPageid();
		page.setFilepath(region.getUrl());
		if(pageid !=null && !"".equals(pageid)){
			page.setId(pageid);
			pageMapper.updateByPrimaryKeyWithBLOBs(page);
		}else {
			pageid = Tools.getUUID();
			page.setId(pageid);
			page.setType(1);
			region.setPageid(pageid);
			pageMapper.insert(page);
		}
		String oldUpId = oldregion.getUpid();
		int oldsort = oldregion.getSort();
		int newmaxson = 0;
		int oldmaxson = 0;
		int a = 0;
		int b = 0;
		// 如果上级目录发生变化
		if (!newUpId.equals(oldUpId)) {
			regionMapper.updateSonMaxSort("", 0);// 清空sonmaxsort = 0
			int oldSonMaxSort = regionMapper.getMaxSortByUpId(oldregion.getId());// 查询此修改项儿子的最大排序号
			int sort1 = oldsort + 1;
			int sort2 = oldSonMaxSort;
			oldmaxson = oldSonMaxSort;
			int oldSonMaxSort1 = oldSonMaxSort;
			if ("root".equals(newUpId)) {
				newmaxson = regionMapper.getMaxSort(type);// 获取当前类型下最大排序号为新父级儿子最大排序号
				if (newmaxson == 0) {
					newmaxson = 1;
				}
				region.setLevel(0);
				int sonlevel = region.getLevel() + 1;
				if (oldSonMaxSort != 0) {
					while (true) {
						regionMapper.updateSonLevel(sort1, sort2, sonlevel++, type);// 设置儿子级别
						Region region2 = regionMapper.getIdBySort(oldSonMaxSort1, type);// 查询最大排序号的id
						oldSonMaxSort1 = regionMapper.getMaxSortByUpId(region2.getId());// 查询此修改项儿子的最大排序号
						if (oldSonMaxSort1 == 0) {
							break;
						}
						sort1 = ++sort2;
						oldmaxson = oldSonMaxSort1;
						sort2 = oldSonMaxSort1;
					}
				}
				if (oldSonMaxSort == 0) {
					region.setSort(newmaxson);
					regionMapper.updateSonSort(oldsort + 1, newmaxson, 1, type);// oldsort到新父级最大sort之间的sort-1
				} else {
					a = oldmaxson - oldsort + 1;
					b = newmaxson - oldmaxson;
					region.setSort(oldsort + b);
					regionMapper.updateSonSort(oldmaxson + 1, newmaxson, a, type);// oldsonmaxsort到sonmaxsort之间的sort-a
					regionMapper.updateNewSortBack(oldsort + 1, oldmaxson, b, type);// 第一个儿子到最后一个儿子的sort+b
				}
				regionMapper.updateByPrimaryKeySelective(region);
			} else {
				Region newRegionParent = regionMapper
						.selectByPrimaryKey(newUpId); // 获取父级
				region.setLevel(newRegionParent.getLevel() + 1); // 设置级别
				int newParentSonMaxSort = regionMapper
						.getMaxSortByUpId(newUpId);
				if (newParentSonMaxSort == 0) {
					newmaxson = newRegionParent.getSort();
				} else {
					newmaxson = newParentSonMaxSort;
					while (true) {
						Region region2 = regionMapper.getIdBySort(newParentSonMaxSort, type);// 查询最大排序号的id
						newParentSonMaxSort = regionMapper.getMaxSortByUpId(region2.getId());
						if (newParentSonMaxSort == 0) {
							break;
						}
						newmaxson = newParentSonMaxSort;
					}
				}

				int sonlevel = region.getLevel() + 1;
				if (oldSonMaxSort != 0) {
					while (true) {
						regionMapper.updateSonLevel(sort1, sort2, sonlevel++, type);// 设置儿子级别
						Region region2 = regionMapper.getIdBySort(oldSonMaxSort1, type);// 查询最大排序号的id
						oldSonMaxSort1 = regionMapper.getMaxSortByUpId(region2
								.getId());// 查询此修改项儿子的最大排序号
						if (oldSonMaxSort1 == 0) {
							break;
						}
						sort1 = ++sort2;
						oldmaxson = oldSonMaxSort1;
						sort2 = oldSonMaxSort1;
					}
				}
				if (oldsort > newmaxson) {
					region.setSort(newmaxson + 1);
					if (oldSonMaxSort == 0) { // 无儿子
						regionMapper.updateNewSortBack(region.getSort(), oldsort - 1, 1, type);// 修改后新sort之后的到oldsort之间的sort+1
					} else {
						a = oldsort - region.getSort();
						b = oldmaxson - oldsort + 1;
						regionMapper.updateSonSort(oldsort + 1, oldmaxson, a, type);// 第一个儿子到最后一个儿子的sort-(oldsort-新sort)
						regionMapper.updateNewSortBack(region.getSort(), oldsort - 1, b, type);// 修改后新sort之后的到oldsort之间的sort+(oldsort-新sort)
					}
				} else {
					if (oldSonMaxSort == 0) {
						region.setSort(newmaxson);
						regionMapper.updateSonSort(oldsort + 1, newmaxson, 1, type);// oldsort到新父级最大sort之间的sort-1
					} else {
						a = oldmaxson - oldsort + 1;
						b = newmaxson - oldmaxson;
						region.setSort(oldsort + b);
						regionMapper.updateSonSort(oldmaxson + 1, newmaxson, a, type);// oldsonmaxsort到sonmaxsort之间的sort-a
						regionMapper.updateNewSortBack(oldsort + 1, oldmaxson, b, type);// 第一个儿子到最后一个儿子的sort+b
					}
				}
				regionMapper.updateByPrimaryKeySelective(region);
			}
		} else {
			regionMapper.updateByPrimaryKeySelective(region);
		} 
	}

	/**
	 * 查询最大sort
	 * 
	 * @return
	 * 
	 */
	@Transactional
	public int getOrderId() {
		return regionMapper.getMaxSort(1);
	}

	/**
	 * @Title: findAllInCost
	 * @Description: 根据costnumber查询所有region
	 * @param costnumber
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<Region> findAllInCost(HttpServletRequest request) {
		List<String> coList = Tools.getCostNumber(request);
		return regionMapper.findAllInCost(coList);
	}

	/**
	 * @Title: findByUpIdAndCostid
	 * @Description: 根据upid和costnumber查找
	 * @param upid
	 * @param costnumber
	 * @return
	 * @return List<Navigation> 返回类型
	 * @author bowden
	 */
	@Transactional(readOnly = true)
	public List<Region> findByUpIdAndCostid(int type,String costnumber) {
		List<Region> regions = regionMapper.findByUpIdAndCostid(type,costnumber);
		for (Region region : regions) {
			String string = "";
			for (int i = 0; i < region.getLevel(); i++) {
				string += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			string += "|--&nbsp;";
			region.setLevelstr(string);
		}
		return regions;
	}

	/**
	 * 查询可选上级列表
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Region> findNotContainSon(Region region) {
		int sonmaxsort = 0;
		int type = region.getType();
		List<Region> regions = null;
		int newParentSonMaxSort = regionMapper.getMaxSortByUpId(region.getId());
		if (newParentSonMaxSort == 0) { // 没有儿子
			sonmaxsort = region.getSort();
			regions = regionMapper.findNotContainSelf(sonmaxsort, type);
		} else {
			sonmaxsort = newParentSonMaxSort;
			while (true) {
				Region region2 = regionMapper.getIdBySort(newParentSonMaxSort, type);// 查询最大排序号的id
				newParentSonMaxSort = regionMapper.getMaxSortByUpId(region2.getId());
				if (newParentSonMaxSort == 0) {
					break;
				}
				sonmaxsort = newParentSonMaxSort;
			}
			regions = regionMapper.findNotContainSelfAndSon(region.getSort(), sonmaxsort, type);
		}
		for (Region r : regions) {
			String string = "";
			for (int i = 0; i < r.getLevel(); i++) {
				string += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			string += "|--&nbsp;";
			r.setLevelstr(string);
		}
		return regions;
	}

	/**
	 * @Title: findByCostnumber
	 * @Description: 查找线路所有分类
	 * @param costnumber
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<Region> findByCostnumber(Integer type, String costnumber) {
		return regionMapper.findByCostnumber(type, costnumber);
	}

	/**
	 * @Title: findTopRegionByCostnumber
	 * @Description: 查找线路顶层分类
	 * @param costnumber
	 * @return
	 * @return List<Rrgion> 返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<Region> findTopRegionByCostnumber(String costnumber) {
		return regionMapper.findTopRegionByCostnumber(costnumber);
	}

	/**
	 * @Title: findDownRegion
	 * @Description: 根据上级分类查找下级分类
	 * @param id
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Region> findDownRegion(String id) {
		return regionMapper.findDownRegion(id);
	}

	// StringBuffer buf = new StringBuffer();
	@Transactional(readOnly = true)
	public String getParentids(String upid, StringBuffer buf) {
		String uus = null;
		if (upid.equals("root")) {
		} else {
			upid = regionMapper.getIdByUpid(upid);
			uus = upid;
			getParentids(upid, buf);
			if (uus != null && !uus.equals("root")) {
				buf.append("," + uus);
			}
		}

		return buf.toString();
	}

	/**
	 * @Title: findIdByUrl
	 * @Description: 根据url查询对应分类的id
	 * @param url
	 * @return
	 * @return String 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public String findIdByUrl(String url) {
		return regionMapper.findIdByUrl(url);
	}

	/**
	 * @Title: getRegionidList
	 * @Description: 查找分类上级
	 * @param upid
	 * @param regionlist
	 * @return
	 * @return List<Region> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Region> getRegionidList(String upid, List<Region> regionlist) {
		if (upid.equals("root")) {
		} else {
			Region region = regionMapper.selectByPrimaryKey(upid);
			upid = region.getUpid();
			getRegionidList(upid, regionlist);
			regionlist.add(region);
		}
		return regionlist;
	}

	/**
	 * @author Sevens 时间2015-6-29
	 * @param request
	 * @param type
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Region> findAllInCostBytype(HttpServletRequest request, int type) {
		return regionMapper.findAllInCostBytype(Tools.getCostNumber(request), type);
	}

	/**
	 * 查询全部分类
	 * @author bowden
	 * @param 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Region> findAll( HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> coList = new ArrayList<String>();
		String costId = request.getParameter("costId");
		//分销售中心查询
		if (costId == null || "".equals(costId)) {
			costId = WebUtils.getCookie(request, "regionCostId");
			if (costId == null || "".equals(costId)) {
				coList = Tools.getCostNumber(request);
			}else {
				coList.add(costId);
			}
		} else {
			coList.add(costId);
			WebUtils.addCookie(request, response, "regionCostId", costId);
		}
		List<Region> regions = regionMapper.findAllInCost(coList);
		model.addAttribute("costNumList", coList);
		return regions;
	}
	
	@Transactional
	public void updateRegionDestinationList(String destinationList,String id){
		regionMapper.updateRegionDestinationList(destinationList, id);
	}

	/**
	 * @Title moveUp
	 * @Description 向上移动
	 * @Author Bowden
	 * @CreateDate 2015-8-14 上午9:51:06
	 */
	@Transactional
	public boolean moveUp(String cueerntId) {
		boolean flag = false;
		Region currentRegion = regionMapper.selectByPrimaryKey(cueerntId);
		if(currentRegion !=null){
			String currentUpId = currentRegion.getUpid();
			int currentType = currentRegion.getType();
			int currentSort = currentRegion.getSort();
			String currentId = currentRegion.getId();
			int movetoSort = regionMapper.findUpSort(currentType, currentUpId, currentSort);// 查询上移的sort
			String movetoId = "";
			if(movetoSort == 0){
				return flag;
			}
			Region movetoRegion = regionMapper.getIdBySort(movetoSort, currentType);
		    movetoId = movetoRegion.getId();
			regionMapper.updateSonMaxSort("", 0);// sonmaxsort = 0
			if ("root".equals(currentUpId)) { // root级别的向上移动
				// 判断此移动项是否有下级
				int currentSonMaxSort = regionMapper.getMaxSortByUpId(cueerntId);
				// 判断移动到的位置是否有下级
				int movetoSonMaxSort = regionMapper.getMaxSortByUpId(movetoId);
				int cut = currentSort - movetoSort;// 新旧排序号之差
				int maxCut = currentSonMaxSort - movetoSonMaxSort;
				if (currentSonMaxSort != 0 && movetoSonMaxSort != 0) { // 当前分类有下级、移动到的位置有下级
					// 当前分类及下级 sort - cut
					regionMapper.updateSonSort(currentSort, currentSonMaxSort, cut, currentType);
					// 移动到位置分类及下级 sort + cut
					regionMapper.updateNewSortBack(movetoSort, movetoSonMaxSort, maxCut, currentType);
				} else if (currentSonMaxSort != 0 && movetoSonMaxSort == 0) { // 当前分类有下级
					// 当前分类及下级 sort - cut
					regionMapper.updateSonSort(currentSort, currentSonMaxSort, cut, currentType);
					// 移动到位置的分类 sort = currentSonMaxSort
					regionMapper.updateSortById(currentSonMaxSort, movetoId);
				} else if (movetoSonMaxSort != 0 && currentSonMaxSort == 0) { // 移动到的位置有下级
					// 移动到位置的分类及其下级 sort + 1
					regionMapper.updateNewSortBack(movetoSort,
							movetoSonMaxSort, 1, currentType);
					// 当前分类 sort - cut
					regionMapper.updateSortById(currentSort - cut, currentId);
				} else if (movetoSonMaxSort == 0 && currentSonMaxSort == 0) { // 都无下级
					// 当前分类 sort -1
					regionMapper.updateSortById(currentSort - 1, currentId);
					// 移动到位置的sort + 1
					regionMapper.updateSortById(movetoSort + 1, movetoId);
				}
				flag = true;
			} else { // root下级的向上移动
				Region upRegion = regionMapper.selectByPrimaryKey(currentUpId);// 查询上级
				if (movetoSort == upRegion.getSort()) {
					flag = false;
				} else {
					// 当前分类 sort
					regionMapper.updateSortById(movetoSort, currentId);
					// 移动到位置的sort
					regionMapper.updateSortById(currentSort, movetoId);
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * @Title moveDown
	 * @Description 向下移动
	 * @Author Bowden
	 * @CreateDate 2015-8-14 上午9:51:35
	 */
	@Transactional
	public boolean moveDown(String cueerntId) {
		boolean flag = false;
		Region currentRegion = regionMapper.selectByPrimaryKey(cueerntId);
		if(currentRegion !=null){
			String currentUpId = currentRegion.getUpid();
			int currentType = currentRegion.getType();
			int currentSort = currentRegion.getSort();
			String currentId = currentRegion.getId();
			int movetoSort = regionMapper.findDownSort(currentType, currentUpId, currentSort);// 查询下移的sort
			String movetoId = "";
			if(movetoSort == 0){
				return flag;
			}
			Region movetoRegion = regionMapper.getIdBySort(movetoSort, currentType);
		    movetoId = movetoRegion.getId();
			regionMapper.updateSonMaxSort("", 0);// sonmaxsort = 0
			if ("root".equals(currentUpId)) { // root级别的向下移动
				// 判断此移动项是否有下级
				int currentSonMaxSort = regionMapper.getMaxSortByUpId(cueerntId);
				// 判断移动到的位置是否有下级
				int movetoSonMaxSort = regionMapper.getMaxSortByUpId(movetoId);
				int cut = movetoSort - currentSort;// 新旧排序号之差
				int maxCut = movetoSonMaxSort - currentSonMaxSort;
				if (currentSonMaxSort != 0 && movetoSonMaxSort != 0) { // 当前分类有下级、移动到的位置有下级
					// 当前分类及下级 sort + maxCut
					regionMapper.updateNewSortBack(currentSort, currentSonMaxSort, maxCut, currentType);
					// 移动到位置分类及下级 sort - cut
					regionMapper.updateSonSort(movetoSort, movetoSonMaxSort, cut, currentType);
				} else if (currentSonMaxSort != 0 && movetoSonMaxSort == 0) { // 当前分类有下级
					// 当前分类及下级 sort + 1
					regionMapper.updateNewSortBack(currentSort, currentSonMaxSort, 1, currentType);
					// 移动到位置的分类 sort - cut
					regionMapper.updateSortById(movetoSort - cut, movetoId);
				} else if (movetoSonMaxSort != 0 && currentSonMaxSort == 0) { // 移动到的位置有下级
					// 移动到位置的分类及其下级 sort - cut
					regionMapper.updateSonSort(movetoSort, movetoSonMaxSort, cut, currentType);
					// 当前分类 sort = movetoSonMaxSort
					regionMapper.updateSortById(movetoSonMaxSort, currentId);
				} else if (movetoSonMaxSort == 0 && currentSonMaxSort == 0) { // 都无下级
					// 当前分类 sort +1
					regionMapper.updateSortById(currentSort + 1, currentId);
					// 移动到位置的sort - 1
					regionMapper.updateSortById(movetoSort - 1, movetoId);
				}
				flag = true;
			} else { // root下级的向上移动
				int currentUpIdMaxSort = regionMapper.getMaxSortByUpId(currentUpId); //查询当前分类下的最大排序号
				if (currentSort == currentUpIdMaxSort) {
					flag = false;
				} else {
					// 当前分类 sort
					regionMapper.updateSortById(movetoSort, currentId);
					// 移动到位置的sort
					regionMapper.updateSortById(currentSort, movetoId);
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * @Title findByUrl
	 * @Description 根据url查找分类
	 * @Author Bowden
	 * @CreateDate 2015-8-19 下午3:44:59
	 */
	@Transactional(readOnly=true)
	public Region findByUrl(String url) {
		return regionMapper.findByUrl(url);
	}

	/**
	 * @Title findRgionAndPageById
	 * @Description 根据regionid查询分类和page
	 * @Author Bowden
	 * @CreateDate 2015-8-21 上午11:39:14
	 */
	public Region findRgionAndPageById(String id) {
		return regionMapper.findRgionAndPageById(id);
	}
}

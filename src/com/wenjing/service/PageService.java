
package com.wenjing.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.dao.CostMapper;
import com.wenjing.dao.CurrencyMapper;
import com.wenjing.dao.DestinationMapper;
import com.wenjing.dao.NavigationMapper;
import com.wenjing.dao.OfficeContactsMapper;
import com.wenjing.dao.PageMapper;
import com.wenjing.dao.PromotionMapper;
import com.wenjing.dao.RegionMapper;
import com.wenjing.dao.SliderMapper;
import com.wenjing.dao.TagMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Navigation;
import com.wenjing.entity.OfficeContacts;
import com.wenjing.entity.Page;
import com.wenjing.entity.Promotion;
import com.wenjing.entity.Region;
import com.wenjing.entity.Tag;
import com.wenjing.entity.Tourline;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明		page信息后台管理  
 * @author xiejin
 * @date 2015-4-23 
 */
@Service
public class PageService {
	
	@Resource
	private PageMapper pageMapper;
	@Resource
	private CostMapper costMapper;
	@Resource
	private SliderMapper sliderMapper;
	@Resource
	private NavigationMapper navigationMapper;
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private CurrencyMapper currencyMapper;
	@Resource
	private TourlineService tourlineService;
	@Resource
	private TourDateService tourDateService;
	@Resource
	private AttractionService attractionService;
	@Resource
	private OfficeContactsMapper officeContactsMapper;
	@Resource
	private TagMapper tagMapper;
	@Resource
	private PromotionMapper promotionMapper;
	@Resource
	private DestinationMapper destinationMapper;
	
	public static final Integer SALE = 1;	//打折
	
	public static final Integer MINUS = 2;	//减价
	
	/**
	 * 根据costnumber查询所有page 
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<Page> findAll(List<String> costnumbers,Integer type){
		return pageMapper.findAll(costnumbers,type);
	}
	
	/**
	 * 根据costnumber查询所有的contact us page
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Page> findAllContactUs(List<String> costnumbers){
		return pageMapper.findAllContactUs(costnumbers);
	}
	
	/**
	 * 根据costumbers查询所有的blog list page
	 * @param costnumbers
	 * @return
	 */
	public List<Page> findAllBlogList(List<String> costnumbers){
		return pageMapper.findAllBlog(costnumbers);
	}
	
	/**
	 * 删除page
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return pageMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page findBlogNavigationPage(){
		return pageMapper.findBlogNavigationPage();
	}
	/**
	 * 根据id查询page
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public Page findById(String id) {
		return pageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存page
	 * @param page
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Page page) {
		return pageMapper.insert(page);
	}
	
	/**
	 * 修改page
	 * @param page
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Page page) {
		return pageMapper.updateByPrimaryKeyWithBLOBs(page);
	}	
	
	/**
	 * @author Sevens
	 * 时间2015-8-6
	 * @param page
	 * @return
	 */
	@Transactional
	public int updateByPrimaryKeySelective(Page page){
		return pageMapper.updateByPrimaryKeySelective(page);
	};
	
	/**
	 * 设置页面生成状态
	 * @author Sevens
	 * 时间2015-8-12
	 * @param id
	 * @param isCreate
	 */
	@Transactional
	public void updateIsCreate(String id,Integer isCreate){
		 pageMapper.updateIsCreate(id, isCreate);
	}
	
	/**
	 * @Title: index
	 * @Description: 首页后台预览
	 * @param model
	 * @param costnumber    
	 * @return void    返回类型
	 * @author xiejin
	 */
	@Transactional
	public void index(Model model,String costnumber){
		//获取销售中心的币种符号
		Cost cost = costMapper.selectByPrimaryKey(costnumber);
		//获取上，下导航
		getNavigation(model, costnumber);	
		
		//featured destination
		List<Destination> destinationUp = destinationMapper.searchShowDestination(costnumber, 1);
		model.addAttribute("destinationUp", destinationUp);
		
		//Explore the world
		List<Destination> destinationDown = destinationMapper.searchShowDestination(costnumber, 2);
		model.addAttribute("destinationDown", destinationDown);
		
		//***********首页促销活动**************
		List<Promotion> promotionList = promotionMapper.getByCostnumber(costnumber, Tools.getDtimestemp(Tools.getDtime()));
		for (Promotion promotion : promotionList) {
			String imageUrl = promotion.getImageurl();
			if(imageUrl!=null && !imageUrl.equals("")){
			}else{
				promotion.setImageurl("/assets-web/images/default_bg.jpg");			//设置促销活动封面图路径
			}
		}
		model.addAttribute("promotionList", promotionList);
		model.addAttribute("costSign",cost.getSign());
		model.addAttribute("costCode",cost.getCode());
		//************首页促销活动************
	}

	/**
	 * @Title: create
	 * @Description: 首页生成静态页面
	 * @param costnumber
	 * @param page
	 * @param request
	 * @throws Exception    
	 * @return void    返回类型
	 * @author xiejin
	 */
	public void create(String costnumber,Page page,HttpServletRequest request) throws Exception { 
		Map<String, Object> root = new HashMap<String, Object>(); 
		//获取销售中心的币种
		Cost cost = costMapper.selectByPrimaryKey(costnumber);
		root.put("page", page);
		
		//获取上，下导航
		getNavigationCreate(root, costnumber);											
		
		//featured destination
		List<Destination> destinationUp = destinationMapper.searchShowDestination(costnumber, 1);
		root.put("destinationUp", destinationUp);
		
		//Explore the world
		List<Destination> destinationDown = destinationMapper.searchShowDestination(costnumber, 2);
		root.put("destinationDown", destinationDown);
		root.put("costSign",cost.getSign());
		
		//***********首页促销活动**************
		List<Promotion> promotionList = promotionMapper.getByCostnumber(costnumber, Tools.getDtimestemp(Tools.getDtime()));
		for (Promotion promotion : promotionList) {
			String imageUrl = promotion.getImageurl();
			if(imageUrl!=null && !imageUrl.equals("")){
			}else{
				promotion.setImageurl("/assets-web/images/default_bg.jpg");			//设置促销活动封面图路径
			}
		}
		root.put("promotionList", promotionList);
		//************首页促销活动************
	    root.put("request", request); 
		// 静态页面的完整路径 
		String str = request.getSession().getServletContext().getRealPath("/") +page.getFilepath(); 
		File file = new File(str);
		// 如果静态文件存在，则删除静态页面之后重新生成 
		if (file.isFile() && file.exists()) {
			file.delete(); 
		}
		str = null;
		// 释放资源 if (file != null) file = null;
		FreemarkerUtils.createHTML(request.getSession().getServletContext(),
		root, page.getTemplateUrl(),page.getFilepath());
	}
	
	/**
	 * @Title: create
	 * @Description: contact us生成静态页面
	 * @param costnumber
	 * @param page
	 * @param request
	 * @throws Exception    
	 * @return void    返回类型
	 * @author xiejin
	 */
	public void createContactUs(String costnumber,Page page,HttpServletRequest request) throws Exception { 
		Map<String, Object> root = new HashMap<String, Object>(); 
		root.put("page", page);
		root.put("costnumber",costnumber);
		root.put("request", request);
		//获取上，下导航
		getNavigationCreate(root, costnumber);	
		List<OfficeContacts> officeContactsList = officeContactsMapper.selectByCostnumber(costnumber);
		root.put("officeContactsList", officeContactsList);
		
		// 静态页面的完整路径 
		String str = request.getSession().getServletContext().getRealPath("/") +page.getFilepath(); 
		File file = new File(str);
		// 如果静态文件存在，则删除静态页面之后重新生成 
		if (file.isFile() && file.exists()) {
			file.delete(); 
		}
		str = null;
		// 释放资源 if (file != null) file = null;
		FreemarkerUtils.createHTML(request.getSession().getServletContext(),
		root, page.getTemplateUrl(),page.getFilepath());
	}	
	
	 /**
	 * @Title: getRegionTourline
	 * @Description: 首页生成静态页面，根据分类读取线路
	 * @param regionid
	 * @param costnumber    
	 * @return void    返回类型
	 */
	public void getRegionTourlineCreate(String regionid,String costnumber,Map<String,Object> root,int index,int size,
		List<Object> attrRegion,List<Object> attrRegionHot,List<Object> tourlineHot1,List<Object> tourlineHot2){
	 	Cost cost = costMapper.selectByPrimaryKey(costnumber);			//线路对应的销售中心
		Set<String> attractions = new HashSet<String>();				//分类下线路所有景点的set集合
		List<String> att = new ArrayList<String>();						//分类下线路所有景点的list集合,由set转换
		List<String> attractionsList = new ArrayList<String>();			//页面需要显示的景点list集合
		List<String> attrHot = new ArrayList<String>();					//分类下热门景点集合,最多两个
		List<String> attrHotAll = new ArrayList<String>();				//分类下热门景点集合,最多八个
		List<Tourline> tourlineByAttr1 = new ArrayList<Tourline>();		//分类下热门景点1对应的线路集合
		List<Tourline> tourlineByAttr2 = new ArrayList<Tourline>();		//分类下热门景点2对应的线路集合
		List<Tourline> tourlineRegion = tourlineService.findByRegionid(regionid,costnumber);	//根据regionid查询所有线路
		if (tourlineRegion!=null && !"".equals(tourlineRegion)) {
			for (Tourline tourlineRegion2 : tourlineRegion) {
				String attractionlist = tourlineRegion2.getAttractionlist();		//获得线路相关景点
				if (attractionlist!=null && !("").equals(attractionlist)) {
					String[] attr = (attractionlist+",").split(",");
					for (int i = 0; i < attr.length; i++) {
						attractions.add(attr[i]);		//添加线路景点
					}
				}
			}
		}
		if (attractions.size()!=0) {
			att = new ArrayList<String>(attractions);	//set转换为list
			attrHotAll = attractionService.findHotAttraction(att,8);
			attrHot = attractionService.findHotAttraction(att,2);	//获得线路相关的景点的两个热门景点
			//分类下显示的线路
			tourlineByAttr1 = tourlineService.findByShowRegionidattra2(null, regionid, 1,costnumber,1);//根据第一个热门景点查询线路
			for (Tourline tourline2 : tourlineByAttr1) {
				String imageurl = tourline2.getProductProductid().getImageurl();		//获得产品相关图片路径
				if(imageurl!=null && !imageurl.equals("")){
					String[] url = (imageurl+",").split(",");
					tourline2.setCoverimageurl(url[0]);		//设置线路封面图路径
				}else{
					tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg");		//设置线路封面图路径
				}
				tourline2.setCost(cost);
				BigDecimal lowsprice = tourDateService.getminsellPrice(tourline2.getId(),costnumber);		//产品最低售价
				//判断是否参与促销活动，并修改最低价格		
				lowsprice = tourlineService.changeSellprice(lowsprice, tourline2.getId(), costnumber);
				tourline2.setLowsprice(lowsprice);
				//查询相关标签
				List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductid(), costnumber);
				tourline2.setTagList(tagList);
			}
			if (attrHotAll.size()>8) {
				for (int i = 0; i <8; i++) {
					attractionsList.add(attrHotAll.get(i));
				}
			}else{
				for (int i = 0; i <attrHotAll.size(); i++) {
					attractionsList.add(attrHotAll.get(i));
				}
			}
		}
		attrRegion.add(attractionsList);
		attrRegionHot.add(attrHot);
		tourlineHot1.add(tourlineByAttr1);
		tourlineHot2.add(tourlineByAttr2);
		if (index==size) {
			root.put("attrRegion",attrRegion);
			root.put("attrRegionHot",attrRegionHot);
			root.put("tourlineHot1",tourlineHot1);
			root.put("tourlineHot2",tourlineHot2);
		}
	}
			
	 /**
	 * @Title: getRegionTourline
	 * @Description: 首页后台预览，根据分类读取线路
	 * @param regionid
	 * @param costnumber    
	 * @return void    返回类型
	 */
	public void getRegionTourline(String regionid,String costnumber,Model model,int index,int size,
		List<Object> attrRegion,List<Object> attrRegionHot,List<Object> tourlineHot1,List<Object> tourlineHot2){
		Cost cost = costMapper.selectByPrimaryKey(costnumber);			//线路对应的销售中心
		Set<String> attractions = new HashSet<String>();				//分类下线路所有景点的set集合
		List<String> att = new ArrayList<String>();						//分类下线路所有景点的list集合,由set转换
		List<String> attractionsList = new ArrayList<String>();			//页面需要显示的景点list集合
		List<String> attrHot = new ArrayList<String>();					//分类下热门景点集合,最多两个
		List<String> attrHotAll = new ArrayList<String>();				//分类下所有热门景点集合
		List<Tourline> tourlineByAttr1 = new ArrayList<Tourline>();		//分类下热门景点1对应的线路集合
		List<Tourline> tourlineByAttr2 = new ArrayList<Tourline>();		//分类下热门景点2对应的线路集合
		List<Tourline> tourlineRegion = tourlineService.findByRegionid(regionid,costnumber);	//根据regionid查询所有线路
		if (tourlineRegion!=null && !"".equals(tourlineRegion)) {
			for (Tourline tourlineRegion2 : tourlineRegion) {
				String attractionlist = tourlineRegion2.getAttractionlist();		//获得线路相关景点
				if (attractionlist!=null &&!("").equals(attractionlist)) {
					String[] attr = (attractionlist+",").split(",");
					for (int i = 0; i < attr.length; i++) {
						attractions.add(attr[i]);		//添加线路景点
					}
				}
			}
		}
		if (attractions.size()!=0) {
			att = new ArrayList<String>(attractions);	//set转换为list
			attrHotAll = attractionService.findHotAttraction(att,8);
			attrHot = attractionService.findHotAttraction(att,2);	//获得线路相关的景点的两个热门景点
			//分类下显示的线路
			tourlineByAttr1 = tourlineService.findByShowRegionidattra2(null, regionid, 1,costnumber,1);//根据第一个热门景点查询线路
			for (Tourline tourline2 : tourlineByAttr1) {
				String imageurl = tourline2.getProductProductid().getImageurl();		//获得产品相关图片路径
				if(imageurl!=null && !imageurl.equals("")){
					String[] url = (imageurl+",").split(",");
					tourline2.setCoverimageurl(url[0]);		//设置线路封面图路径
				}else{
					tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg");		//设置线路封面图路径
				}
				tourline2.setCost(cost);
				BigDecimal lowsprice = tourDateService.getminsellPrice(tourline2.getId(),costnumber);		//产品最低售价
				//判断是否参与促销活动，并修改最低价格		
				lowsprice = tourlineService.changeSellprice(lowsprice, tourline2.getId(), costnumber);
				tourline2.setLowsprice(lowsprice);
				//查询相关标签
				List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductid(), costnumber);
				tourline2.setTagList(tagList);
			}
			if (attrHotAll.size()>8) {
				for (int i = 0; i <8; i++) {
					attractionsList.add(attrHotAll.get(i));
				}
			}else{
				for (int i = 0; i <attrHotAll.size(); i++) {
					attractionsList.add(attrHotAll.get(i));
				}
			}
		}
		attrRegion.add(attractionsList);
		attrRegionHot.add(attrHot);
		tourlineHot1.add(tourlineByAttr1);
		tourlineHot2.add(tourlineByAttr2);
		if (index==size) {
			model.addAttribute("attrRegion",attrRegion);
			model.addAttribute("attrRegionHot",attrRegionHot);
			model.addAttribute("tourlineHot1",tourlineHot1);
			model.addAttribute("tourlineHot2",tourlineHot2);
		}
	}

	/**
	 * @Title: getNavigation
	 * @Description: 获取上，下导航数据
	 * @param model
	 * @param costnumber    
	 * @return void    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public void getNavigation(Model model,String costnumber){
		Cost costFresh = costMapper.selectByPrimaryKey(costnumber);
		model.addAttribute("frontCode", costFresh.getCode());
	    if(costFresh!=null){
	    	costFresh.setCode(costFresh.getCode().substring(0,2).toLowerCase());
	    }
		List<Navigation> navigation = navigationMapper.findByType(1,costnumber);		//查找上导航
		model.addAttribute("navigation", navigation);
		List<Navigation> navigation2 = navigationMapper.findByType(2,costnumber);		//查找下导航
		model.addAttribute("navigation2", navigation2);
		List<Navigation> navigationPhoneList = navigationMapper.findPhoneByType(2, costnumber);
		model.addAttribute("navigationPhoneList", navigationPhoneList);					//手机版下导航
		model.addAttribute("costFresh",costFresh);
		
	}
	
	/**
	 * @Title: getNavigation
	 * @Description: 生成静态页面时，获取上，下导航数据
	 * @param model
	 * @param costnumber    
	 * @return void    返回类型
	 * @author xiejin
	 */
	@Transactional
	public void getNavigationCreate(Map<String,Object> root,String costnumber){
		Cost costFresh = costMapper.selectByPrimaryKey(costnumber);
		root.put("frontCode", costFresh.getCode());
	    if(costFresh!=null){
	    	costFresh.setCode(costFresh.getCode().substring(0,2).toLowerCase());
	    }
		List<Navigation> navigation = navigationMapper.findByType(1,costnumber);		//查找上导航
		root.put("navigation", navigation);
		List<Navigation> navigation2 = navigationMapper.findByType(2,costnumber);		//查找下导航
		root.put("navigation2", navigation2);
		List<Navigation> navigationPhoneList = navigationMapper.findPhoneByType(2, costnumber);
		root.put("navigationPhoneList", navigationPhoneList);							//手机版下导航
		root.put("costFresh",costFresh);
		
	}

	/**
	 * 处理首页定时自动生成
	 * @author Sevens
	 * 时间：2015-11-24
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Page> findNotByCostnumber(){
		return pageMapper.findNotByCostnumber();
	}

	/**
	 * @Title: createDestinationPage
	 * @Description: 目的地导航生成静态二级页面
	 * @param costnumber
	 * @param request
	 * @param destinationName
	 * @param fileUrl
	 * @param page
	 * @throws Exception    
	 * @return void    返回类型
	 * @author xiejin
	 */
	public void createDestinationPage(String costnumber,HttpServletRequest request,
			Destination destination,String fileUrl,Page page) throws Exception {
		Map<String, Object> root = new HashMap<String, Object>(); 
		
		//上，下导航
		getNavigationCreate(root, costnumber);	
		
		//产品分类
		List<Region> regionList = regionMapper.findByCostnumber(1, costnumber);	
		root.put("regionList", regionList);
		
		//销售中心促销活动id，title
		List<Promotion> promotionList = promotionMapper.getPartByCostnumber(costnumber, Tools.getDtimestemp(Tools.getDtime()));
		root.put("promotionList", promotionList);
		tourlineService.findByShowRegionidCreate(request, costnumber, destination.getName(), null, null, null, null, null, null, null, null, 12, null, root);
		root.put("tourListPage",page);
		root.put("costnumber",costnumber);
		root.put("destination",destination);
	    root.put("request", request); 
		// 静态页面的完整路径 
		String str = request.getSession().getServletContext().getRealPath("/") + fileUrl; 
		File file = new File(str);
		// 如果静态文件存在，则删除静态页面之后重新生成 
		if (file.isFile() && file.exists()) {
			file.delete(); 
		}
		str = null;
		// 释放资源 if (file != null) file = null;
		FreemarkerUtils.createHTML(request.getSession().getServletContext(),
		root, "/front/tourlineList.ftl",fileUrl);
	}
	
	/**
 	 * 根据filePath查询页面总数
 	 * 
	 * @param filePath
	 * @return
	 */
	public int getPageNumberWithFilePath(String filePath){
		return pageMapper.findByFilePath(filePath);
	}
	
	/**
	 * 根据filePath查询不包括id为exceptPageId的页面总数
	 * @param filePage
	 * @param exceptPageId
	 * @return
	 */
	public int getPageNumberWithFilePathExceptPage(String filePath,String exceptPageId){
		return pageMapper.findByFilePathExceptPage(filePath, exceptPageId);
	}
}

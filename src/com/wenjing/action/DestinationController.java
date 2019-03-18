
package com.wenjing.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.dao.TourlinedestinationMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.DestinationLevel;
import com.wenjing.entity.IndexShowDestination;
import com.wenjing.entity.Page;
import com.wenjing.entity.Promotion;
import com.wenjing.entity.Region;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tourlinedestination;
import com.wenjing.service.AttractionService;
import com.wenjing.service.AttractionimageService;
import com.wenjing.service.CoordinateService;
import com.wenjing.service.CostService;
import com.wenjing.service.DestinationLevelService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.HotelService;
import com.wenjing.service.IndexShowDestinationService;
import com.wenjing.service.PageService;
import com.wenjing.service.PromotionService;
import com.wenjing.service.RegionService;
import com.wenjing.service.ShowTourlineService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明    后台目的地管理
 * @author xiejin
 * @date 2015-4-20 
 */
@Controller
@RequestMapping("/admin/destination")
public class DestinationController {
	
	@Resource
	private DestinationService destinationService;
	
	@Resource
	private CostService costService;
	
	@Resource
	private HttpServletRequest request;
	
	@Autowired
	private CoordinateService coordinateService;
	@Resource
	private AttractionService attractionService;
	@Resource
	private AttractionimageService attractionimageService;
	@Resource
	private HotelService hotelService;
	@Resource
	private DestinationLevelService destinationLevelService;
	@Resource
	private TourlineService tourlineService;
	@Resource
	private TourlinedestinationMapper tourlinedestinationMapper;
	@Resource
	private ShowTourlineService showtourlineService;
	@Resource
	private IndexShowDestinationService indexShowDestinationService;
	@Resource
	private PromotionService promotionService;
	@Resource
	private PageService pageService;
	@Resource
	private RegionService regionService;
		
	/**
	 * 查询所有destination 
	 * @return
	 * xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model,HttpServletResponse response){
		destinationService.findAll(request, response, model);
		return "/admin/manage/destination/destination.ftl";
	}
	
//	/**
//	 * 根据id删除destination
//	 * @param id
//	 * @return
//	 * xiejin
//	 */
//	@RequestMapping("/delete")
//	public String delete(@RequestParam("id") String id) {
//		List<Attraction> attractionList = attractionService.selectByDestinationid(id);	//目的地下所有景点
//		for (Attraction attraction : attractionList) {
//			String attractionId = attraction.getId();
//			attractionimageService.deleteByAttractionId(attractionId);	//删除景点图片关系表
//			attractionService.deleteByAttractionId(attractionId);			//删除景点线路关系表
//			attractionService.delete(attractionId);			//删除关联景点
//		}
//		List<Hotel> hotelList = hotelService.selectByDestinationid(id);		//目的地下所有酒店
//		for (Hotel hotel : hotelList) {
//			String hotelId = hotel.getId();
//			hotelService.deleteByHotelId(hotelId);	//删除线路酒店关联信息
//			hotelService.delete(hotelId);			//删除关联酒店
//		}
//		destinationService.deleteByDestinationId(id);//删除线路目的地关系表
//		destinationService.delete(id);	//删除目的地
//		return "redirect:/admin/destination/list.do";
//	}
	
	/**
	 * 根据id删除destination
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id,RedirectAttributes redirectAttributes) {
		int num = destinationService.delete(id);	//删除目的地
		if (num == 1) {
			redirectAttributes.addFlashAttribute("noticeMessage", "删除成功");
		}else{
			redirectAttributes.addFlashAttribute("noticeMessage", "目的地删除失败");
		}
		return "redirect:/admin/destination/list.do";
	}

	/**
	 * 保存destination
	 * @param destination
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/save")
	public String save(Destination destination,@RequestParam("id") String id,Model model) {
		destination.setId(id);
		Byte ishot = destination.getIshot();
		if (ishot==null) {
			destination.setIshot(Byte.valueOf("0"));
		}
		String upid = destination.getUpid();
		if (upid.equals("0")) {
			destination.setUps("");
		}else{
			StringBuffer bu = new StringBuffer();
			String upsPart = destinationService.getUps(upid,bu);
			destination.setUps(upsPart);
		}
		
//		String fileUrl = "";
		String nameString = destination.getName();
		if (nameString != null && !"".equals(nameString)) {
			nameString = nameString.trim();		//去除前后空格
//			String lowerNameString = nameString.toLowerCase();	//转为小写
//			fileUrl = "/"+lowerNameString.replaceAll("\\s", "-")+"-tours.htm";
		}
		destination.setName(nameString);
//		destination.setFileUrl(fileUrl);
		
		Destination destinationQuery = destinationService.findById(id);
		if (destinationQuery != null && !destinationQuery.equals("")) {
			destinationService.update(destination);
		} else {
			boolean nameExist = destinationService.isExist(destination.getName());
			boolean namecnExist = destinationService.isExist(destination.getNamecn());
			if (nameExist || namecnExist) {		//提交校验目的地中英文名称是否重复
				model.addAttribute("destination", destination);
				List<Destination> destination2 = destinationService.findByCostNumber();
				model.addAttribute("destination2", destination2);
				List<DestinationLevel> destinationLevel = destinationLevelService.findAll();
				model.addAttribute("destinationLevel", destinationLevel);
				return "/admin/manage/destination/destinationAdd.ftl";
			}else {
				destinationService.save(destination);
			}
		}
		return "redirect:/admin/destination/list.do";		
	}

	/**
	 * 修改destination
	 * @param destination
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		Destination destination = destinationService.findById(id);
		model.addAttribute("destination", destination);		
		List<Destination> destination2 = destinationService.findByCostNumber();
		model.addAttribute("destination2", destination2);
//		Cost costlist = costService.findById(destination.getCostnumber());
//		model.addAttribute("costlist", costlist);
		List<DestinationLevel> destinationLevel = destinationLevelService.findAll();
		model.addAttribute("destinationLevel", destinationLevel);
		return "/admin/manage/destination/destinationUpdate.ftl";
	}
	
	/**
	 * 添加destination
	 * @param destination
	 * @param model
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(Destination destination, Model model){
		List<String> costnumber = Tools.getCostNumber(request);
		String id = Tools.getUUID();
		destination.setId(id);
		int sort = destinationService.getOrderId(costnumber.get(0));	
		destination.setSort(sort+1);
		model.addAttribute("destination", destination);
		List<Destination> destination2 = destinationService.findByCostNumber();
		model.addAttribute("destination2", destination2);
//		List<Cost> costlist = new ArrayList<Cost>();
//		for (String costnumber2 : costnumber) {
//			Cost cost = costService.findById(costnumber2);
//			costlist.add(cost);
//		}
//		model.addAttribute("costlist", costlist);
		List<DestinationLevel> destinationLevel = destinationLevelService.findAll();
		model.addAttribute("destinationLevel", destinationLevel);
		return "/admin/manage/destination/destinationAdd.ftl";
	}
	
	/**
	 * 选择运营中心时异步查询
	 * @param conum
	 * @return
	 * xiejin
	 */
	@RequestMapping("/addbycost")
	@ResponseBody
	public List<Object> addbycost(@RequestParam("conum") String conum){
		List<Destination> destination2 = destinationService.findByCostNumber();
		int sort = destinationService.getOrderId(conum);	
		List<Object> obj = new ArrayList<Object>();
		obj.add(sort);
		obj.add(destination2);
		return obj;
	}
	
	/**
	 * @Title: isExist
	 * @Description: 校验目的地是否存在
	 * @param name
	 * @param costnumber
	 * @param model
	 * @return    
	 * @return boolean    返回类型
	 * @author xiejin
	 */
	@ResponseBody
	@RequestMapping(value = "/isExist", method = RequestMethod.POST)
	public boolean isExist(@RequestParam("name") String name,Model model) {
		boolean success = destinationService.isExist(name);
		return success;
	}
	
	/**
	 * @Title: exchange
	 * @Description:     中文转换
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/exchange")
	public void exchange(){
		List<Destination> destinations = destinationService.findAllByCostNumber();
		for (Destination destination : destinations) {
			String namecn = destination.getNamecn();
			if (namecn != null && !"".equals(namecn)) {
//				System.out.println(namecn+"==============namecn");
				String namecn2 = Tools.StringFilter(namecn);
//				System.out.println(namecn2+"==============namecn2");
				String namepy = Tools.converterToSpell(namecn2);
				destination.setNamepy(namepy.substring(0, 1));
				destinationService.updateNamepy(destination);
			}
		}
	}
	/**
	 * @author Sevens
	 * 时间2015-9-28
	 * @param attractionId
	 * @param costids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/createTour")
	public String createTour(HttpServletResponse response,@Param("id")String id) throws Exception{
		String message = null;
		List<Tourlinedestination> tourlinedests = tourlinedestinationMapper.selectByDestinationid(id);
		if(tourlinedests!=null&&tourlinedests.size()>0){
			for (Tourlinedestination tourlinedestination : tourlinedests) {
				Tourline tourline = tourlineService.findByIdIsshow(tourlinedestination.getTourlineid());	//查询线路
				if(tourline.getCostnumberids()!=null&&!"".equals(tourline.getCostnumberids())){
						int count = showtourlineService.findCountWithCostnumberAnaTourline(tourline.getCostnumber(), tourline.getId());
						if(count!=0){
							tourlineService.create(tourline);
						}
					}
				
			}
			message = "生成成功！";
		}else{
			message = "没有需要生成的线路";
		}
	  WebUtils.addCookie(request, response, "Dcreatemessage", message);
	  return "redirect:/admin/destination/list.do";
	}
	
	/**
	 * @Title: isUse
	 * @Description: 异步查询目的地是否有关联关系
	 * @param departureId
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@ResponseBody
	@RequestMapping(value = "/isUse", method = RequestMethod.POST)
	public Map<String, Object> isUse(@RequestParam("destinationId") String destinationId) {
		Map<String, Object> root = new HashMap<String, Object>();
		boolean flag = false;
		if(destinationId != null && !"".equals(destinationId)){
			 flag = destinationService.findByDestinationId(destinationId);	//true表示被关联使用
			 if(flag){
				 List<Destination> destinationList = destinationService.findAllByCostNumber();
				 root.put("destinationList", destinationList);
			 }
		}
		root.put("flag", flag);
		root.put("destinationId", destinationId);
		return root;
	}
	
	/**
	 * @Title: replaceAttraction
	 * @Description: 替换目的地
	 * @param request
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value = "/replaceDestination",method = RequestMethod.POST)
	public String replaceDestination(HttpServletRequest request,RedirectAttributes rAttributes){
		String destinationId = request.getParameter("destinationId");
		String replaceId = request.getParameter("replaceId");
		boolean flag = destinationService.replaceByDestinationId(destinationId, replaceId);
		if (flag) {
			rAttributes.addFlashAttribute("noticeMessage", "替换成功");
		}else{
			rAttributes.addFlashAttribute("noticeMessage", "替换失败，请重新选择");
		}
		return "redirect:/admin/destination/list.do";
	}
	
	/**
	 * @Title: changeindexshow
	 * @Description: 改变目的地首页显示状态
	 * @param destinationId
	 * @param costnumber
	 * @param showType
	 * @param showStatus    
	 * @return void    返回类型
	 * @author xiejin	intertrips
	 */
	@RequestMapping("/changeindexshow")
	@ResponseBody
	public void changeindexshow(@RequestParam("destinationId")String destinationId,
			@RequestParam("costNumber")String costNumber,
			@RequestParam("showType") String showType,
			@RequestParam("showStatus")String showStatus){
		if ("show".equals(showStatus)) {
			indexShowDestinationService.deleteByCondition(costNumber, Integer.valueOf(showType), destinationId);
		}
		if ("noneShow".equals(showStatus)) {
			IndexShowDestination indexShowDestination = new IndexShowDestination();
			indexShowDestination.setId(Tools.getUUID());
			indexShowDestination.setAddTime(Tools.getDtimestemp(Tools.getHHtime()));
			indexShowDestination.setCostNumber(costNumber);
			indexShowDestination.setDestinationId(destinationId);
			if (showType != null) {
				indexShowDestination.setShowType(Integer.valueOf(showType));
			}
			//****生成二级页面目的地路径*****
			Cost cost = costService.findById(costNumber);
			String costCode = cost.getCode();
			Destination destination = destinationService.findById(destinationId);
			String desName = destination.getName();
			String desUrl = "";										//目的地列表页路径
			String partUrl = "";
			if (desName != null && !"".equals(desName)) {
				desName = desName.trim();						//去除前后空格
				String lowerDesName = desName.toLowerCase();	//转为小写
				partUrl = lowerDesName.replaceAll("\\s", "-");
				if (costCode.equals("USD")) {
					desUrl = "/us/"+partUrl+"-tours.htm";
				}else if(costCode.equals("CNY")){
					desUrl = "/cn/"+partUrl+"-tours.htm";
				}else if(costCode.equals("CAD")){
					desUrl = "/ca/"+partUrl+"-tours.htm";
				}else if(costCode.equals("EUR")){
					desUrl = "/eu/"+partUrl+"-tours.htm";
				}else if(costCode.equals("AUD")){
					desUrl = "/au/"+partUrl+"-tours.htm";
				}
			}
			indexShowDestination.setFileUrl(desUrl);
			//****生成二级页面目的地路径*****
			indexShowDestinationService.insert(indexShowDestination);
		}
	}
	
	/**
	 * @Title: destinationPreview
	 * @Description: 目的地后台列表页预览
	 * @param model
	 * @param costnumber
	 * @param destination
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/preview")
	public String destinationPreview(Model model,@RequestParam("costnumber") String costnumber,
			@RequestParam("id") String id){
		
		//上，下导航
		pageService.getNavigation(model, costnumber);	
		
		//产品分类
		List<Region> regionList = regionService.findByCostnumber(1, costnumber);	
		model.addAttribute("regionList", regionList);
		
		//目的地名称
		Destination destination = destinationService.findById(id);
		String destinationName = destination.getName();
		model.addAttribute("destination",destination);
		
		//页面meta信息
		IndexShowDestination indexShowDestination = destinationService.getFileUrl(costnumber, id);
		Page page = pageService.findById(indexShowDestination.getPageId());
		
		model.addAttribute("tourListPage",page);
		
		//销售中心促销活动id，title
		List<Promotion> promotionList = promotionService.getPartByCostnumber(costnumber, Tools.getDtimestemp(Tools.getDtime()));
		model.addAttribute("promotionList", promotionList);
		
		model.addAttribute("costnumber",costnumber);
		tourlineService.findByShowRegionid(request, model, costnumber, destination.getName(), null, null, null,null, null, null, null, null, 12, null);
		return "/front/tourlineList.ftl";
	}
	
	/**
	 * @Title: create
	 * @Description: 生成目的地导航二级页面
	 * @param destinationIdList
	 * @param costnumber
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/create")
	public String create(@RequestParam("destinationIdList")String[] destinationIdList,
			@RequestParam("costnumber")String costnumber){
		for (int i = 0; i < destinationIdList.length; i++) {
			Destination destination = destinationService.findById(destinationIdList[i]);
			
			IndexShowDestination indexShowDestination = destinationService.getFileUrl(costnumber, destinationIdList[i]);
			String fileUrl = indexShowDestination.getFileUrl();			//目的地列表页路径
			Page page = pageService.findById(indexShowDestination.getPageId());
			
				try {
					pageService.createDestinationPage(costnumber, request,destination, fileUrl,page);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
	
		return "redirect:/admin/destination/desnav.do?costnumber="+costnumber;
	}
	
	/**
	 * @Title: create
	 * @Description: 目的地导航管理列表页
	 * @param costnumber    
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/desnav")
	public String list(@RequestParam("costnumber")String costnumber,Model model){
		List<Destination> destinationList = destinationService.findDesNav(costnumber);
		model.addAttribute("destinationList", destinationList);
		Cost cost = costService.findById(costnumber);
		model.addAttribute("costObject", cost);
		return "/admin/manage/destinationNavigation/desNav.ftl";
	}
	
	/**
	 * @Title: updateUrl
	 * @Description: 修改目的地导航url
	 * @param costnumber
	 * @param id
	 * @param model
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/updateUrl")
	public String updateUrl(@RequestParam("costnumber")String costnumber,
			@RequestParam("id")String id,Model model){
		Cost cost = costService.findById(costnumber);
		String costCode = cost.getCode();
		
		Destination destination = destinationService.findById(id);
		String desName = destination.getName();
		IndexShowDestination indexShowDestination = destinationService.getFileUrl(costnumber, id);
		//页面meta信息
		String pageId = indexShowDestination.getPageId();
		Page page = new Page();
		if (pageId != null && !"".equals(pageId)) {
			page = pageService.findById(pageId);
		}else{
			pageId = Tools.getUUID();
			page.setId(pageId);
		}
		model.addAttribute("page", page);
		
		String desUrl = indexShowDestination.getFileUrl();			//目的地列表页路径
		if (desUrl == null || "".equals(desUrl)) {
			String partUrl = "";
			if (desName != null && !"".equals(desName)) {
				desName = desName.trim();						//去除前后空格
				String lowerDesName = desName.toLowerCase();	//转为小写
				partUrl = lowerDesName.replaceAll("\\s", "-");
				if (costCode.equals("USD")) {
					desUrl = "/us/"+partUrl+"-tours.htm";
				}else if(costCode.equals("CNY")){
					desUrl = "/cn/"+partUrl+"-tours.htm";
				}else if(costCode.equals("CAD")){
					desUrl = "/ca/"+partUrl+"-tours.htm";
				}else if(costCode.equals("EUR")){
					desUrl = "/eu/"+partUrl+"-tours.htm";
				}else if(costCode.equals("AUD")){
					desUrl = "/au/"+partUrl+"-tours.htm";
				}
			}
		}
		
		
		model.addAttribute("costnumberAdmin", costnumber);
		model.addAttribute("id", id);
		model.addAttribute("desUrl", desUrl);
		return "/admin/manage/destinationNavigation/desNavUpdate.ftl";
	}
	
	/**
	 * @Title: saveUrl
	 * @Description: 保存目的地高航路径
	 * @param costnumber
	 * @param id
	 * @param desUrl
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/saveUrl")
	public String saveUrl(@RequestParam("costnumber")String costnumber,
			@RequestParam("id")String id,
			@RequestParam("desUrl")String desUrl,
			@RequestParam("pageId")String pageId,
			Page page){
		page.setId(pageId);
		Page page2 = pageService.findById(pageId);
		if (page2 != null) {
			//修改page信息
			pageService.updateByPrimaryKeySelective(page);
		}else{
			//保存page信息
			pageService.save(page);
		}
		//修改indexshowdestination信息
		destinationService.updateFileUrl(costnumber, id, desUrl,pageId);
		return "redirect:/admin/destination/desnav.do?costnumber="+costnumber;
	}
}

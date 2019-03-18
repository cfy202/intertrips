
package com.wenjing.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.dao.TourlineattractionMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Attraction;
import com.wenjing.entity.AttractionLevel;
import com.wenjing.entity.Attractionimage;
import com.wenjing.entity.Attractiontype;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Image;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tourlineattraction;
import com.wenjing.service.AttractionLevelService;
import com.wenjing.service.AttractionService;
import com.wenjing.service.AttractionimageService;
import com.wenjing.service.AttractiontypeService;
import com.wenjing.service.CoordinateService;
import com.wenjing.service.CostService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.ImageService;
import com.wenjing.service.ShowTourlineService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明		景点后台管理controller
 * @author xiejin
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/attraction")
public class AttractionController {

	@Resource
	private AttractionService attractionService;
	@Resource 
	private DestinationService destinationService;
	@Resource
	private AttractiontypeService attractiontypeService;
	@Resource
	private ImageService imageService;
	@Resource
	private AttractionimageService attractionimageService;
	@Resource
	private CostService costService;
	
	@Resource
	private CoordinateService coordinateService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private AttractionLevelService attractionLevelService;
	
	@Resource
	private TourlineService tourlineService;
	@Resource
	private TourlineattractionMapper tourlineattractionMapper;
	@Resource
	private ShowTourlineService showtourlineService;
	
	/**
	 * 根据costnumber查询所有attractions 
	 * @return
	 * xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model,HttpServletResponse response){
		 attractionService.findAll(request, response, model);	
		return "/admin/manage/attractions/attraction.ftl";
	}
	
//	/**
//	 * 根据id删除attractions
//	 * @param id
//	 * @return
//	 * xiejin
//	 */
//	@RequestMapping("/delete")
//	public String delete(@RequestParam("id") String id) {
//		attractionimageService.deleteByAttractionId(id);	//删除景点图片关系表
//		attractionService.deleteByAttractionId(id);			//删除景点线路关系表
//		attractionService.delete(id);
//		return "redirect:/admin/attraction/list.do";
//	}
	
	/**
	 * 根据id删除attractions
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id,RedirectAttributes rAttributes) {
		int deletecount = attractionService.delete(id);
		if (deletecount == 1) {	//删除成功
			rAttributes.addFlashAttribute("noticeMessage", "删除成功");
		}else {
			rAttributes.addFlashAttribute("noticeMessage", "景点删除失败");
		}
		return "redirect:/admin/attraction/list.do";
	}

	/**
	 * 保存attractions和景点图片联系表
	 * @param attraction
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/save")
	public String save(Attraction attraction,@RequestParam("id") String id,
			@RequestParam("imageid") String[] imageid,Model model) {
		attraction.setId(id);
		Integer ishot = attraction.getIshot();
		if (ishot==null) {
			attraction.setIshot(0);
		}
		//保存景点
		Attraction attraction2 = attractionService.findById(id);
		if (attraction2 != null && !attraction2.equals("")) {
			attractionService.update(attraction);
		} else {
			boolean nameExist = attractionService.isExist(attraction.getName());
			boolean namecnExist = attractionService.isExist(attraction.getNamecn());
			if (nameExist || namecnExist) {		//提交校验目的地中英文名称是否重复
				model.addAttribute("attraction", attraction);
				List<Destination> destination = destinationService.findCityAndProvinceByCostNumber();
				model.addAttribute("destination", destination);
				List<Attractiontype> attractiontype = attractiontypeService.findAll();
				model.addAttribute("attractiontype", attractiontype);
				List<AttractionLevel> attractionLevel = attractionLevelService.findAll();
				model.addAttribute("attractionLevel", attractionLevel);
				return "/admin/manage/attractions/attractionAdd.ftl";
			}else {
				attractionService.save(attraction);
			}
		}
		//保存景点图片联系表
		if(imageid !=null && !imageid.equals("")){ 				//判断是否选择或上传图片
			for (int i = 0; i < imageid.length; i++) {			
				if(!imageid[i].equals("")){						//去除为""的imageid
					String id1 = Tools.getUUID();
//					System.out.println(imageid[i]+"++++++++++++++++++++++++++++++++++++++++++++++++");
					Attractionimage attractionimage = new Attractionimage();
					attractionimage.setId(id1);
					attractionimage.setAttractionid(id);
					attractionimage.setImageid(imageid[i]);
					attractionimageService.save(attractionimage);
				}
			}		
		}
		return "redirect:/admin/attraction/list.do";
	}
	

	/**
	 * 修改attractions
	 * @param attraction
	 * @param model
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(Attraction attraction, Model model) {
		String id = attraction.getId();			
		attraction = attractionService.findById(id);
		model.addAttribute("attraction", attraction);
		List<Destination> destination = destinationService.findCityAndProvinceByCostNumber();
		model.addAttribute("destination", destination);
		List<Attractiontype> attractiontype = attractiontypeService.findAll();
		model.addAttribute("attractiontype", attractiontype);
		List<AttractionLevel> attractionLevel = attractionLevelService.findAll();
		model.addAttribute("attractionLevel", attractionLevel);
		String imageurl = attraction.getImageurl();
		if(imageurl!=null && !imageurl.equals("")){//判断景点是否有图片，并读出图片url
			imageurl = imageurl+",";
			String[] imgurl = imageurl.split(",");
			model.addAttribute("imgurl", imgurl);
			model.addAttribute("img", 1);
		}else{
			model.addAttribute("img", 0);
		}
		return "/admin/manage/attractions/attractionUpdate.ftl";
	}
	
	/**
	 * 添加attraction
	 * @param attraction
	 * @param model
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(Attraction attraction, Model model){
		String id = Tools.getUUID();
		attraction.setId(id);
//		List<String> costnumber = Tools.getCostNumber(request);
		int sort = attractionService.getOrderId();	
		attraction.setSort(sort+1);
		model.addAttribute("attraction", attraction);
		List<Destination> destination = destinationService.findCityAndProvinceByCostNumber();
		model.addAttribute("destination", destination);
		List<Attractiontype> attractiontype = attractiontypeService.findAll();
		model.addAttribute("attractiontype", attractiontype);
		List<AttractionLevel> attractionLevel = attractionLevelService.findAll();
		model.addAttribute("attractionLevel", attractionLevel);
//		List<Cost> costlist = new ArrayList<Cost>();
//		for (String costnumber2 : costnumber) {
//			Cost cost = costService.findById(costnumber2);
//			costlist.add(cost);
//		}
//		model.addAttribute("costlist", costlist);
		return "/admin/manage/attractions/attractionAdd.ftl";
	}
	
	/**
	 * 景点图片上传保存到图库
	 * @param picaddress
	 * @return
	 * xiejin
	 */
	@RequestMapping("/savepic")
	@ResponseBody
	public Image savepictoria(@RequestParam("picaddress") String picaddress,
			@RequestParam("namecn") String namecn){
		//保存image
		String id = Tools.getUUID();
		Image image = new Image();
		image.setId(id);
		image.setUrl(picaddress);
		String[] name = picaddress.split("images/");
		image.setName(name[1]);
		String createtime = Tools.getTime();
		image.setCreatetime(createtime);
		image.setUsetype("atractions");
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
		image.setTitle(namecn);
		imageService.save(image);
		return image;
	}
	
	/**
	 * 根据imageid和attractionid删除景点图片联系表
	 * @param url
	 * @param attractionid
	 * xiejin
	 */
	@RequestMapping("/deletebyimageid")
	@ResponseBody
	public String deletePageImageByImageId(@RequestParam("url")String url,@RequestParam("attractionid")String attractionid){
		Image image = imageService.selectByUrl(url);		//根据url查询图片
		String imageId = image.getId();		//获取图片id
		attractionimageService.deleteByImageId(imageId,attractionid);		//根据imageid和attractionid删除景点图片联系表信息
		return null;
	}
	
//	/**
//	 * 选择运营中心时异步查询
//	 * @param conum
//	 * @return
//	 * xiejin
//	 */
//	@RequestMapping("/addbycost")
//	@ResponseBody
//	public List<Object> addbycost(@RequestParam("conum") String conum){
//		List<Destination> destination2 = destinationService.findCityByCostNumber(conum);
//		int sort = attractionService.getOrderId(conum);	
//		List<Object> obj = new ArrayList<Object>();
//		obj.add(sort);
//		obj.add(destination2);
//		return obj;
//	}
	
	/**
	 * @Title: exchange
	 * @Description:     中文转换
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/exchange")
	public void exchange(){
		List<Attraction> attractions = attractionService.findAllByCostNumber();
		for (Attraction attraction : attractions) {
			String namecn = attraction.getNamecn();
			if (namecn != null && !"".equals(namecn)) {
//				System.out.println(namecn+"==============namecn");
				String namecn2 = Tools.StringFilter(namecn);
//				System.out.println(namecn2+"==============namecn2");
				String namepy = Tools.converterToSpell(namecn2);
				attraction.setNamepy(namepy.substring(0, 1));
				attractionService.updateNamepy(attraction);
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
	public String createTour(HttpServletResponse response,@Param("id")String id,@Param("costids") String costids) throws Exception{
		List<Tourlineattraction> tourlineattrs = tourlineattractionMapper.selectByAttractionid(id);
		String message = null;
		if(tourlineattrs!=null&&tourlineattrs.size()>0){
			for (Tourlineattraction tourlineattraction : tourlineattrs) {
				Tourline tourline = tourlineService.findByIdIsshow(tourlineattraction.getTourlineid());	//查询线路
				if(tourline.getCostnumberids()!=null&&!"".equals(tourline.getCostnumberids())){
					if(costids!=null&&!"".equals(costids)&&tourline.getCostnumberids().indexOf(costids)!=-1){
						int count = showtourlineService.findCountWithCostnumberAnaTourline(costids, tourline.getId());
						if(count!=0){
							tourlineService.create(tourline);
						}
					}else{
						String [] costs =tourline.getCostnumberids().split(",");
						for (String string2 : costs) {
							int count = showtourlineService.findCountWithCostnumberAnaTourline(string2, tourline.getId());
							if(count!=0){
								tourlineService.create(tourline);
							}
						}
					}
				}
			}
		   message= "页面生成成功！";
		}else{
			message = "没有需要生成的线路页面";
		}
	  WebUtils.addCookie(request, response, "Acreatemessage", message);	
 	  return "redirect:/admin/attraction/list.do";
	}
	
	/**
	 * @Title: isExist
	 * @Description: 根据名称校验景点是否存在
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
		boolean success = attractionService.isExist(name);
		return success;		//存在为true，不存在为false
	}
	
	/**
	 * @Title: isUse
	 * @Description: 异步查询景点是否有关联关系
	 * @param departureId
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@ResponseBody
	@RequestMapping(value = "/isUse", method = RequestMethod.POST)
	public Map<String, Object> isUse(@RequestParam("attractionId") String attractionId) {
		Map<String, Object> root = new HashMap<String, Object>();
		boolean flag = false;
		if(attractionId != null && !"".equals(attractionId)){
			 flag = attractionService.findByAttractionId(attractionId);
			 if(flag){
				 List<Attraction> attractionList = attractionService.findAllByCostNumber();
				 root.put("attractionList", attractionList);
			 }
		}
		root.put("flag", flag);
		root.put("attractionId", attractionId);
		return root;
	}
	
	/**
	 * @Title: replaceAttraction
	 * @Description: 替换景点
	 * @param request
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value = "/replaceAttraction",method = RequestMethod.POST)
	public String replaceAttraction(HttpServletRequest request,RedirectAttributes rAttributes){
		String attractionId = request.getParameter("attractionId");
		String replaceId = request.getParameter("replaceId");
		if (attractionId != null && !"".equals(attractionId) && replaceId != null && !"".equals(replaceId)) {
			int countAttractionImage = attractionService.replaceByAttractionId(attractionId, replaceId);
			int countTourlineAttraction = attractionService.replaceByAttractionId2(attractionId, replaceId);
			if (countAttractionImage == 0 && countTourlineAttraction == 0) {
				rAttributes.addFlashAttribute("noticeMessage", "替换失败，请重新选择");
			}else{
				attractionService.delete(attractionId);
				rAttributes.addFlashAttribute("noticeMessage", "替换成功");
			}
		}
		return "redirect:/admin/attraction/list.do";
	}
}

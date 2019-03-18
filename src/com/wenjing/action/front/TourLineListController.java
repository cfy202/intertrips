package com.wenjing.action.front;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Navigation;
import com.wenjing.entity.Promotion;
import com.wenjing.entity.Region;
import com.wenjing.service.AttractionService;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.PageService;
import com.wenjing.service.PromotionService;
import com.wenjing.service.RegionService;
import com.wenjing.service.SliderService;
import com.wenjing.service.TagService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourlineService;
import com.wenjing.service.TourlineattractionService;
import com.wenjing.service.TourlinedestinationService;
import com.wenjing.util.Tools;

/**
 * 类说明		线路展示页面controller
 * @author xiejin
 * @date 2015-5-18 下午8:49:40
 */
@Controller
@RequestMapping("/front/tourlinelist")
public class TourLineListController {

	@Autowired
	private HttpServletRequest request;
	@Resource
	private SliderService sliderService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private DestinationService destinationService;
	@Resource
	private RegionService regionService;
	@Resource
	private TourlineService tourlineService;
	@Resource
	private TourDateService tourDateService;
	@Resource
	private CostService costService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private PageService pageService;
	@Resource
	private TourlineattractionService tourlineattractionService;
	@Resource
	private AttractionService attractionService;
	@Resource
	private TourlinedestinationService tourlinedestinationService;
	@Resource
	private TagService tagservice;
	@Resource
	private PromotionService promotionService;
	
	@RequestMapping("/list")
	public String tourlinelist(Model model){
		
		String costnumber = request.getParameter("costnumberF");
		pageService.getNavigation(model, costnumber);					//上，下导航
		String destination = request.getParameter("destination");		//定义首页目的地名称
		String dateFrom = request.getParameter("dateFrom");
		if (destination!= null) {
			try {
				destination = java.net.URLDecoder.decode(destination, "utf-8");	//获取首页点击景点获得的目的地名称
				destination = new String(destination.getBytes("ISO-8859-1"),"utf-8");
				destination = destination.replaceAll("\'", "");	//去除单引号
				destination = destination.replaceAll(" ", "");//去除字符串中的所有空格
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String keyword = request.getParameter("keyword");	//定义搜索关键字名称
		if (keyword!= null) {
			keyword = keyword.replaceAll("\'", "");		//去除单引号
//			try {
//				keyword = java.net.URLDecoder.decode(keyword, "utf-8");	//获取搜索关键字名称
//				keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
//				keyword = keyword.replaceAll("\'", "");		//去除单引号
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
		}
		
		String regioId = null;
		String regionUrl = request.getParameter("url");
		String navUrl = null;
		if (regionUrl != null && !"".equals(regionUrl)) {
			navUrl = regionUrl;
			regionUrl = "/destinations/"+regionUrl+".htm";
			regioId = regionService.findIdByUrl(regionUrl);		//查询分类url对应的分类id
		}
		StringBuffer SUF = new StringBuffer(); 
        if(regioId == null && navUrl != null){
        	Navigation nav = navigationService.findByurl(regionUrl);
        	if(nav!=null && nav.getPname().equals("Destinations")){
        		String [] des = navUrl.split("-");
        		for (int i = 0; i < des.length-1; i++) {
        			SUF.append(des[i].replaceFirst(des[i].substring(0, 1),des[i].substring(0, 1).toUpperCase())) ; 
        		}
        	}else{
        		return "redirect:"+regionUrl;
        	}
        }
   		if(SUF != null&&SUF.length()>0){
   			destination = SUF.toString();	
   		}
   		
   		//产品分类
		List<Region> regionList = regionService.findByCostnumber(1, costnumber);	
		model.addAttribute("regionList", regionList);
		
		//销售中心促销活动id，title
		List<Promotion> promotionList = promotionService.getPartByCostnumber(costnumber, Tools.getDtimestemp(Tools.getDtime()));
		model.addAttribute("promotionList", promotionList);
		model.addAttribute("destination",destination);
		model.addAttribute("keyword",keyword);
		model.addAttribute("regioId",regioId);
		model.addAttribute("dateFrom",dateFrom);
		int isreload = 1;
		model.addAttribute("isreload",isreload);
		return "/front/tourlineList.ftl";
	}

	/**
	 * @Title: tourlinelist2
	 * @Description: 条件分页查询intertrips二级页面线路展示
	 * @param model
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/tourlinePage")
	@ResponseBody
	public Map<String ,Object> tourlinePage(Model model){
		String regionId = request.getParameter("regionId");				//线路分类
		String costnumber = request.getParameter("costnumberF");			//某一销售中心id
		String destination =request.getParameter("destination");		//选择的目的地字符串
		String keyword = request.getParameter("keyword");				//选择的搜索关键字
		String dateFrom = request.getParameter("dateFrom");				//选择的开始日期
		String dateTo = request.getParameter("dateTo");					//选择的结束日期
		String sort = request.getParameter("sort");						//选择的排序
		String rateString = request.getParameter("rateString");			//选择的线路评级
		String price = request.getParameter("priceString");				//选择的线路价格
		String sizeString = request.getParameter("size");
		Integer size = 12;
		if (sizeString !=null && !"".equals(sizeString)) {
			size = Integer.valueOf(sizeString);						//每页显示线路条数
		}
		String promotionId = request.getParameter("promotionId");		//促销活动id
		BigDecimal minPrice = null;
		BigDecimal maxPrice = null;
		if (!"".equals(price) && price!=null) {
			if (price.indexOf("0-200")!=-1) {
				minPrice = new BigDecimal(0);
				maxPrice = new BigDecimal(200);
			}else if(price.indexOf("201-500")!=-1){
				minPrice = new BigDecimal(201);
				maxPrice = new BigDecimal(500);
			}else if(price.indexOf("501-1000")!=-1){
				minPrice = new BigDecimal(501);
				maxPrice = new BigDecimal(1000);
			}else if(price.indexOf("1001-")!=-1){
				minPrice = new BigDecimal(1001);
				maxPrice = new BigDecimal(1000000);
			}	
		}
		Map<String ,Object> root = tourlineService.findTourlineByCondition(request, model, costnumber, destination,
				 minPrice, maxPrice, sort, keyword,rateString,dateFrom,dateTo,regionId,size,promotionId);
		return root;
	}

}

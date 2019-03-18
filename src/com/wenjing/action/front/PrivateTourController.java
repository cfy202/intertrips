
package com.wenjing.action.front;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.Mail;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Email;
import com.wenjing.entity.Navigation;
import com.wenjing.entity.PromotionProduct;
import com.wenjing.entity.Region;
import com.wenjing.entity.Slider;
import com.wenjing.entity.Tag;
import com.wenjing.entity.Tourline;
import com.wenjing.service.AttractionService;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.EmailService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.RegionService;
import com.wenjing.service.SliderService;
import com.wenjing.service.TagService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * 类说明		首页合成action
 * @author xiejin
 * @date 2015-5-13 上午11:09:26
 */
@Controller
@RequestMapping("/front/private")
public class PrivateTourController {
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
	private AttractionService attractionService;
	@Resource
	private EmailService emailService;
	@Resource
	private CostService costService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private TagService tagService;
	
	/**
	 * 中国运营中心
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/index")
	public String index(Model model){
		include(model);
		return"/front/private/privatetour.ftl";
	}
	
	@RequestMapping("/losangeles")
	public String losangeles(Model model){
		include(model);
		return"/front/private/Car/losangeles_car.ftl";
	}
	@RequestMapping("/newyork")
	public String newyork(Model model){
		include(model);
		return"/front/private/Car/newyork_car.ftl";
	}
	@RequestMapping("/sanfrancisco")
	public String sanfrancisco(Model model){
		include(model);
		return"/front/private/Car/sanfrancisco_car.ftl";
	}
	@RequestMapping("/seattle")
	public String seattle(Model model){
		include(model);
		return"/front/private/Car/seattle_car.ftl";
	}
	
	@RequestMapping("/team")
	public String team(Model model){
		include(model);
		return"/front/private/Car/team_car.ftl";
	}
	
	@RequestMapping("/commendation")
	public String commendation(Model model){
		include(model);
		return"/front/private/Gifts/commendation.ftl";
	}
	@RequestMapping("/climbing")
	public String climbing(Model model){
		include(model);
		return"/front/private/Gifts/climbing.ftl";
	}
	@RequestMapping("/luxury_yacht")
	public String luxury_yacht(Model model){
		include(model);
		return"/front/private/Gifts/luxury_yacht.ftl";
	}
	@RequestMapping("/seafood")
	public String seafood(Model	model){
		include(model);
		return"/front/private/Gifts/seafood.ftl";
	}
	@RequestMapping("/Temecula_1")
	public String Temecula_1(Model model){
		include(model);
		return"/front/private/Gifts/Temecula_1.ftl";
	}
	@RequestMapping("/Temecula_2")
	public String Temecula_2(Model model){
		include(model);
		return"/front/private/Gifts/Temecula_2.ftl";
	}
	
	
	@RequestMapping("/F1")
	public String F1(Model model){
		include(model);
		return"/front/private/Gifts/F1.ftl";
	}
	@RequestMapping("/Medical")
	public String Mediacl(Model model){
		include(model);
		return"/front/private/Gifts/Medical.ftl";
	}
	@RequestMapping("/Parasail")
	public String Parasail(Model model){
		include(model);
		return"/front/private/Gifts/Parasail.ftl";
	}
	@RequestMapping("/QUEENMARY")
	public String QUEENMARY(Model model){
		include(model);
		return"/front/private/Gifts/QUEENMARY.ftl";
	}
	@RequestMapping("/Firegun")
	public String Firegun(Model model){
		include(model);
		return"/front/private/Gifts/Firegun.ftl";
	}
	@RequestMapping("/Wedding1")
	public String Wendding1(Model model){
		include(model);
		return"/front/private/Gifts/Wedding1.ftl";
	}
	@RequestMapping("/Wedding2")
	public String Wendding2(Model model){
		include(model);
		return"/front/private/Gifts/Wedding2.ftl";
	}
	@RequestMapping("/train")
	public String train(Model model){
		include(model);
		return"/front/private/Gifts/train.ftl";
	}
	public void include (Model model){
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		Cost cost = costService.findById(costnumber);	//对应的销售中心
		model.addAttribute("frontCode", cost.getCode());
		
		List<Slider> slider = sliderService.findByType(1,costnumber);						//查找首页幻灯片图片
		model.addAttribute("slider", slider);
		
		List<Navigation> navigation = navigationService.findByType(1,costnumber);			//查找上导航
		for (Navigation navigation2 : navigation) {
			String regionId = regionService.findIdByUrl(navigation2.getUrl());	//查询导航对应的分类
			if (regionId !=null) {
				List<Tag> tagList = tagService.findRegionShow(regionId, costnumber);	//查询分类对应的标签集合
				navigation2.setTagList(tagList);
			}
		}
		model.addAttribute("navigation", navigation);
		
		List<Navigation> navigation2 = navigationService.findByType(2,costnumber);			//查找下导航
		model.addAttribute("navigation2", navigation2);
		
		List<Region> region = regionService.findByCostnumber(1,costnumber);					//查找产品分类
		model.addAttribute("region", region);
		
		List<Currency> currencies = currencyService.findAll(); //获取币种列表
		model.addAttribute("currencies", currencies);
		
		
		
		
		//根据region读取线路
		 List<Object> attrRegion = new ArrayList<Object>();
		 List<Object> attrRegionHot = new ArrayList<Object>();
		 List<Object> tourlineHot1 = new ArrayList<Object>();
		 List<Object> tourlineHot2 = new ArrayList<Object>();
		int index = 1;
		List<Region> reg = regionService.findTopRegionByCostnumber(costnumber);
		for (Region region2 : reg) {
			String id = region2.getId();
			int size = reg.size();
			getRegionTourline(id,costnumber,model,index,size,attrRegion,attrRegionHot,tourlineHot1,tourlineHot2);
			index++;
		}
		model.addAttribute("reg", reg);
		model.addAttribute("costnumber", costnumber);
		
	}
	
	/**
	 * @Title: getRegionTourline
	 * @Description: 根据分类读取线路
	 * @param regionid
	 * @param costnumber    
	 * @return void    返回类型
	 * @author xiejin
	 */
	public void getRegionTourline(String regionid,String costnumber,Model model,int index,int size,
		List<Object> attrRegion,List<Object> attrRegionHot,List<Object> tourlineHot1,List<Object> tourlineHot2){
		Cost cost = costService.findById(costnumber);					//线路对应的销售中心
		Set<String> attractions = new HashSet<String>();				//分类下线路所有景点的set集合
		List<String> att = new ArrayList<String>();						//分类下线路所有景点的list集合,由set转换
		List<String> attractionsList = new ArrayList<String>();			//页面需要显示的景点list集合
		List<String> attrHot = new ArrayList<String>();					//分类下热门景点集合,最多两个
		List<String> attrHotAll = new ArrayList<String>();				//分类下所有热门景点集合,最多8个
		List<Tourline> tourlineByAttr1 = new ArrayList<Tourline>();		//分类下热门景点1对应的线路集合
		List<Tourline> tourlineByAttr2 = new ArrayList<Tourline>();		//分类下热门景点2对应的线路集合
		List<Tourline> tourlineRegion = tourlineService.findByShowRegionid2(regionid, 1, costnumber);	//根据regionid查询所有线路
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
				List<Tag> tagList = tagService.findByIdCostnumber(tourline2.getProductid(), costnumber);
				tourline2.setTagList(tagList);
			}
//			if (attrHot!=null && !("").equals(attractions) && attrHot.size()!=0) {
//				//第一个热门景点对应线路
//				tourlineByAttr1 = tourlineService.findByShowRegionidattra2(attrHot.get(0), regionid, 1,costnumber,1);//根据第一个热门景点查询线路
//				for (Tourline tourline2 : tourlineByAttr1) {
//					String imageurl = tourline2.getProductProductid().getImageurl();		//获得产品相关图片路径
//					if(imageurl!=null && !imageurl.equals("")){
//						String[] url = (imageurl+",").split(",");
//						tourline2.setCoverimageurl(url[0]);		//设置线路封面图路径
//					}else{
//						tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg");		//设置线路封面图路径
//					}
//					tourline2.setCost(cost);
//					BigDecimal lowsprice = tourDateService.getminsellPrice(tourline2.getId(),costnumber);		//产品最低售价
//					//判断是否参与促销活动，并修改最低价格		
//					lowsprice = tourlineService.changeSellprice(lowsprice, tourline2.getId(), costnumber);
//					tourline2.setLowsprice(lowsprice);
//				}
//				if (attrHot.size()==2) {
//					//第二个热门景点对应线路
//					tourlineByAttr2 = tourlineService.findByShowRegionidattra2(attrHot.get(1), regionid, 1,costnumber,1);//根据第二个热门景点查询美东线路
//					for (Tourline tourline2 : tourlineByAttr2) {
//						String imageurl = tourline2.getProductProductid().getImageurl();		//获得产品相关图片路径
//						if(imageurl!=null && !imageurl.equals("")){
//							String[] url = (imageurl+",").split(",");
//							tourline2.setCoverimageurl(url[0]);		//设置线路封面图路径
//						}else{
//							tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg");		//设置线路封面图路径
//						}
//						tourline2.setCost(cost);
//						BigDecimal lowsprice = tourDateService.getminsellPrice(tourline2.getId(),costnumber);		//产品最低售价
//						//判断是否参与促销活动，并修改最低价格		
//						lowsprice = tourlineService.changeSellprice(lowsprice, tourline2.getId(), costnumber);
//						tourline2.setLowsprice(lowsprice);
//					}
//				}
//			}
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
	 * @Title: search
	 * @Description: 首页搜索
	 * @param model
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/search")
	public String search(Model model){
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		Cost cost = costService.findById(costnumber);					//线路对应的销售中心
		model.addAttribute("frontCode", cost.getCode());
		String keyword = request.getParameter("keyword");
		if (keyword!=null && !"".equals(keyword)) {
//			keyword =keyword.replaceAll("\\s*", "");
			try {
					keyword = java.net.URLDecoder.decode(request.getParameter("keyword"), "utf-8");
					keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8").replaceAll("\\s*", "");
					keyword = keyword.replaceAll("\'", "");		//去除单引号
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<Navigation> navigation = navigationService.findByType(1,costnumber);		//查找上导航
		for (Navigation navigation2 : navigation) {
			String regionId = regionService.findIdByUrl(navigation2.getUrl());	//查询导航对应的分类
			if (regionId !=null) {
				List<Tag> tagList = tagService.findRegionShow(regionId, costnumber);	//查询分类对应的标签集合
				navigation2.setTagList(tagList);
			}
		}
		model.addAttribute("navigation", navigation);
		
		List<Navigation> navigation2 = navigationService.findByType(2,costnumber);		//查找下导航
		model.addAttribute("navigation2", navigation2);
		
		List<Region> region = regionService.findByCostnumber(1,costnumber);		//查找产品分类
		model.addAttribute("region", region);
		
		List<Currency> currencies = currencyService.findAll(); //获取币种列表
		model.addAttribute("currencies", currencies);
		
		//二级页面热卖线路
		List<Tourline> tourlinehot = tourlineService.searchTourlineHot(1, 1, costnumber);		//查找二级页面显示的热卖线路
		for (Tourline tourline2 : tourlinehot) {
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
		}
		model.addAttribute("tourlinehot", tourlinehot);
		
		//二级页面线路分页和筛选条件展示
		tourlineService.searchTourline(request, keyword, 1, costnumber, model);		//分页查询二级页面线路展示
		
//		List<Tourline> tourlines = tourlineService.searchTourline2(keyword, 1, costnumber);	//二级页面所有线路
//		Set<String> start = new HashSet<String>();	//出发地集合
//		Set<String> attractions = new HashSet<String>();	//景点,目的地集合
//		for (Tourline tourline2 : tourlines) {
//			String startCity = tourline2.getStartDestination();			//获得参团地
//			if (startCity!=null && !("").equals(startCity)) {
//				start.add(startCity);								//集合添加参团地
//			}
//			String attractionlist = tourline2.getAttractionlist();		//获得线路相关景点
//			if (attractionlist!=null && !("").equals(attractionlist)) {
//				String[] attr = (attractionlist+",").split(",");
//				for (int i = 0; i < attr.length; i++) {
//					if (!"".equals(attr[i])) {			//去除空字符串景点
//						attractions.add(attr[i]);		//添加景点
//					}
//				}
//			}
//			String destinationList = tourline2.getDestinationlist();		//获得线路相关目的地
//			if (destinationList!=null&&!("").equals(destinationList)) {
//				String[] des = (destinationList+",").split(",");
//				for (int i = 0; i < des.length; i++) {
//					if (!"".equals(des[i])) {			//去除空字符串目的地
//						attractions.add(des[i]);		//添加目的地
//					}
//				}
//			}
//		}
//		Comparator<Object> com=Collator.getInstance(java.util.Locale.CHINA);
//		String[] attractionsArray = attractions.toArray(new String[0]);	//转化为字符串数组
//		Arrays.sort(attractionsArray,com); 	//数组排序
//		String[] startArray = start.toArray(new String[0]);//转化为字符串数组
//		Arrays.sort(startArray,com);	//数组排序
//		model.addAttribute("start",startArray);
//		model.addAttribute("attractions",attractionsArray);
		model.addAttribute("costnumber",costnumber);
		model.addAttribute("keyword",keyword);
		return"/front/search.ftl";
	}
	
	/**
	 * @Title: email
	 * @Description: 保存订阅邮箱
	 * @param email
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/email")
	@ResponseBody
	public boolean email(Email email){
		Boolean flag = true;
		String emailAddress = email.getEmail();
		Email email2= emailService.findByEmailAddress(emailAddress);	//查询是邮箱是否存在
		if (email2==null) {
			String id = Tools.getUUID();
			email.setId(id);
			email.setStatus(1);
			String timeNow = Tools.getnowtime();
			email.setAddTime(Tools.getTimestemp(timeNow));
			String costnumber = (String) request.getSession().getAttribute("costnumber");
			email.setCostNumber(costnumber);
			String ip = Tools.getRemortIP(request);
			email.setIp(ip);
			emailService.insert(email);
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @Title: sendEmail
	 * @Description: 发送邮件
	 * @param emailAddress    
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/send")
	@ResponseBody
	public void sendEmail(@RequestParam("email") String emailAddress){
		//发送邮件功能
		String[] emails = (emailAddress+",").split(",");	//将邮件地址转换成字符串数组
		Mail gmail = new Mail();
		gmail.setEmailSubjectTxt("文景假期：订阅通知");
		String MsgTxt = FreemarkerUtils.getOrderEmailSuccess(request.getSession().getServletContext());
		gmail.setEmailMsgTxt(MsgTxt);
		gmail.setSendTo(emails);
		try {
			gmail.sendSSLMessage();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

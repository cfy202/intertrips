package com.wenjing.action.front;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Navigation;
import com.wenjing.entity.Orders;
import com.wenjing.entity.Product;
import com.wenjing.entity.Region;
import com.wenjing.entity.Slider;
import com.wenjing.entity.Tag;
import com.wenjing.entity.Visa;
import com.wenjing.entity.Visaoccupation;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.ProductService;
import com.wenjing.service.RegionService;
import com.wenjing.service.SliderService;
import com.wenjing.service.TagService;
import com.wenjing.service.VisaService;
import com.wenjing.service.VisaoccupationService;
import com.wenjing.util.Tools;

/**
 * 类说明		首页合成action
 * @author xiejin
 * @date 2015-5-13 上午11:09:26
 */
@Controller
@RequestMapping("/front/visaindex")
public class VisaIndexController {
	@Autowired
	private HttpServletRequest request;
	@Resource
	private SliderService sliderService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private DestinationService destinationService;
	@Resource
	private ProductService productService;
	@Resource
	private RegionService regionService;
	@Resource
	private VisaService visaService;
	@Resource
	private VisaoccupationService visaoccupationService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private CostService costService;
	@Resource
	private TagService tagservice;
	
	/**
	 * 中国运营中心
	 * @param model
	 * @return
	 * xiejin
	 * @throws ParseException 
	 */
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("us","$");	//默认币种
		//String costnumber = "33e25a5f67274fab94b84e21adb95ef0";
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		List<Slider> slider = sliderService.findByType(2,costnumber);		//查找首页幻灯片图片
		model.addAttribute("slider", slider);
		
		List<Navigation> navigation = navigationService.findByType(1,costnumber);		//查找上导航
		for (Navigation navigation2 : navigation) {
			String regionId = regionService.findIdByUrl(navigation2.getUrl());	//查询导航对应的分类
			if (regionId !=null) {
				List<Tag> tagList = tagservice.findRegionShow(regionId, costnumber);	//查询分类对应的标签集合
				navigation2.setTagList(tagList);
			}
		}
		model.addAttribute("navigation", navigation);
		
		List<Navigation> navigation2 = navigationService.findByType(2,costnumber);		//查找下导航
		model.addAttribute("navigation2", navigation2);
		
		List<Region> region = regionService.findByCostnumber(1,costnumber);		//查找线路产品分类
		model.addAttribute("region", region);
		List<Region> regionvisa = regionService.findByCostnumber(2,costnumber);		//查找签证产品分类
		model.addAttribute("regionvisa", regionvisa);
		List<Currency> currencies = currencyService.findAll(); //获取币种列表
		model.addAttribute("currencies", currencies);
		Cost cost = costService.findById(costnumber);
		model.addAttribute("frontCode", cost.getCode());
		List<Product> productlist = productService.findIshotVisa(2, costnumber);//查询签证热推产品
		for (Product product : productlist) {
			String nameString = product.getName().replace("个人旅游签证", "");
			product.setName(nameString);
			
		}
		model.addAttribute("productlist",productlist);
		return"/front/visa/visa.ftl";
	}
	
	@RequestMapping("/visaByRegin")
	public String findVisaById(Model model,HttpServletRequest request){
		String upurl = request.getHeader("Referer");
		String Turl = request.getParameter("url");
		Turl = "/visa-"+Turl+".htm";
		model.addAttribute("us","$");	//默认币种
		//String costnumber = "33e25a5f67274fab94b84e21adb95ef0";
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		List<Slider> slider = sliderService.findByType(2,costnumber);		//查找首页幻灯片图片
		model.addAttribute("slider", slider);
		
		List<Navigation> navigation = navigationService.findByType(1,costnumber);		//查找上导航
		model.addAttribute("navigation", navigation);
		
		List<Navigation> navigation2 = navigationService.findByType(2,costnumber);		//查找下导航
		model.addAttribute("navigation2", navigation2);
		List<Region> region = regionService.findByCostnumber(1,costnumber);		//查找线路产品分类
		model.addAttribute("region", region);
		List<Region> regionvisa = regionService.findByCostnumber(2,costnumber);		//查找签证产品分类
		model.addAttribute("regionvisa", regionvisa);
		List<Currency> currencies = currencyService.findAll(); //获取币种列表
		model.addAttribute("currencies", currencies);
		
		Cost cost = costService.findById(costnumber);
		model.addAttribute("frontCode", cost.getCode());
		
		Region regionv = regionService.findByUrl(Turl);
		
		List<Visa> visalist =  visaService.selectByRegionId(Turl);
		
		model.addAttribute("visalist",visalist);
		model.addAttribute("regionv",regionv);
			
		return"/front/visa/visa_list.ftl";
		
	}
	
	@RequestMapping("/visaById")
	public String visaById(Model model){
		model.addAttribute("us","$");	//默认币种
		//String costnumber = "33e25a5f67274fab94b84e21adb95ef0";
		String Turl = request.getParameter("url");
		Turl = "/visa-"+Turl+".htm";
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		List<Slider> slider = sliderService.findByType(2,costnumber);		//查找首页幻灯片图片
		model.addAttribute("slider", slider);
		
		List<Navigation> navigation = navigationService.findByType(1,costnumber);		//查找上导航
		model.addAttribute("navigation", navigation);
		
		List<Navigation> navigation2 = navigationService.findByType(2,costnumber);		//查找下导航
		model.addAttribute("navigation2", navigation2);
		
		List<Region> region = regionService.findByCostnumber(1,costnumber);		//查找线路产品分类
		model.addAttribute("region", region);
		List<Currency> currencies = currencyService.findAll(); //获取币种列表
		model.addAttribute("currencies", currencies);
		Cost cost = costService.findById(costnumber);
		model.addAttribute("frontCode", cost.getCode());
		Visa visa =  visaService.selectByProductId(Turl);
		visa.setAcceptancerange(Tools.replaceStr(visa.getAcceptancerange()));
		visa.setBookingPolicy(visa.getBookingPolicy().replace("\r\n", "</li><br><li>"));
		List<Visaoccupation> visaocctype = visaoccupationService.findByVisaType(visa.getId());
		List<Visaoccupation> visaocclist =  visaoccupationService.findByVisaId(visa.getId());
		for (Visaoccupation visaoccupation : visaocclist) {
			visaoccupation.setContent(Tools.replaceStr(visaoccupation.getContent()));
		}
		
		model.addAttribute("visa",visa);
		model.addAttribute("visaocclist",visaocclist);
		model.addAttribute("visaocctype",visaocctype);
		return"/front/visa/visa_usa.ftl";
		
	}
	
	@RequestMapping("visaOrders")
	@ResponseBody
	public List<Orders> visaOrders() throws ParseException{
		List<Orders> orderslist = visaService.findWithVisa(2);
		for (Orders orders : orderslist) {
			orders.setReceiveway(Tools.date_pcon(orders.getCreatetime(), new Date()));
		}
		return orderslist;
	}
	
}

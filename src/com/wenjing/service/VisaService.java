package com.wenjing.service;

/**
 * Created by XuXuebin on 2014/7/9.
 */

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.Pages;
import com.wenjing.dao.OrdersMapper;
import com.wenjing.dao.PageMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.ProducttagMapper;
import com.wenjing.dao.TagMapper;
import com.wenjing.dao.VisaMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Navigation;
import com.wenjing.entity.Orders;
import com.wenjing.entity.Page;
import com.wenjing.entity.Product;
import com.wenjing.entity.Producttag;
import com.wenjing.entity.Region;
import com.wenjing.entity.Slider;
import com.wenjing.entity.Tag;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Visa;
import com.wenjing.entity.Visaoccupation;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * Service - 管理员
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service
public class VisaService {

	@Autowired
	private VisaMapper visaMapper;
	@Resource
	private PageMapper pageMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private SliderService sliderService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private CostService costService;
	@Resource
	private RegionService regionService;
	@Resource
	private VisaoccupationService visaoccupationService;
	@Resource
	private PageService pageService;
	@Resource
	private ShowTourlineService showtourlineService;
	@Resource
	private  ProducttagMapper producttagMapper;
	@Resource
	private TagMapper tagMapper;
	
	@Transactional
	public void save(Visa visa, Page page, Product product) {
		page.setId(Tools.getUUID());
		pageMapper.insert(page);
		product.setId(Tools.getUUID());
		product.setPageid(page.getId());
		product.setExcludeLandPrice(0);
		productMapper.insert(product);
		visa.setProductid(product.getId());
		visaMapper.insertSelective(visa);
	}

	@Transactional
	public int update(Visa visa, Page page, Product product) {
		page.setId(product.getPageid());
		pageMapper.updateByPrimaryKeySelective(page);
		product.setId(visa.getProductid());
		product.setExcludeLandPrice(0);
		productMapper.updateByPrimaryKey(product);
		return visaMapper.updateByPrimaryKeySelective(visa);
	}

	@Transactional
	public void delete(String id) {
		visaMapper.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public String findAll(HttpServletRequest request,HttpServletResponse response, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String costid = request.getParameter("costId");
		String pageNow = request.getParameter("TpageNow");
		String search = request.getParameter("Tsearch");
		String pageSize = request.getParameter("pageSize");
		if (costid == null || "".equals(costid)) {
			costid = WebUtils.getCookie(request, "costId");
			if (costid == null) {
				List<String> costnumber = Tools.getCostNumber(request);
				if(costnumber!=null&&costnumber.size()==1){
					costid = costnumber.get(0);
				}
			}
		} else {
			WebUtils.addCookie(request, response, "costId", costid);
			pageNow = "1";
		}
		Integer indexShowInt = null;
		Integer isShowInt = null;
		Integer ishotInt = null;
		//是否首页显示
		String indexShow = request.getParameter("indexShow");
		if(indexShow == null || "".equals(indexShow)){
			indexShow = WebUtils.getCookie(request, "tourIndexShow");
		}else {
			WebUtils.addCookie(request, response, "tourIndexShow", indexShow);
			pageNow = "1";
		}
		if(indexShow !=null && !"".equals(indexShow)){
			indexShowInt = Integer.parseInt(indexShow);
		}
		//是否显示
		String isshow = request.getParameter("isshow");
		if(isshow == null || "".equals(isshow)){
			isshow = WebUtils.getCookie(request, "tourIsshow");
		}else {
			WebUtils.addCookie(request, response, "tourIsshow", isshow);
			pageNow = "1";
		}
		if(isshow !=null && !"".equals(isshow)){
			isShowInt = Integer.parseInt(isshow);
		}
		//是否热推
		String ishot = request.getParameter("ishot");
		if(ishot == null || "".equals(ishot)){
			ishot = WebUtils.getCookie(request, "tourIshot");
		}else {
			WebUtils.addCookie(request, response, "tourIshot", ishot);
			pageNow = "1";
		}
		if(ishot !=null && !"".equals(ishot)){
			ishotInt = Integer.parseInt(ishot);
		}
		
		if(pageSize==null||"".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "TpageSize");
		}else{
			WebUtils.addCookie(request, response, "TpageSize", pageSize);
			pageNow = 1+"";
		}
		if (search == null || "".equals(search)) {
			search = WebUtils.getCookie(request, "Tsearch");
		} else {
			WebUtils.addCookie(request, response, "Tsearch", search);
			pageNow = 1+"";
		}
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "TpageNow");
		} else {
			WebUtils.addCookie(request, response, "TpageNow", pageNow);
		}

		Pages page = null;
		List<Visa> viaslist = new ArrayList<Visa>();
		int totalCount = visaMapper.getVisaCount(costid, search, indexShowInt, isShowInt, ishotInt);
        if(pageSize==null){
        	pageSize=10+"";
        }
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
			viaslist = this.visaMapper.selectAllByPage(page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
			viaslist = this.visaMapper.selectAllByPage(
					page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);	
		} 
		
		for (Visa visalist : viaslist) {
			List<Producttag> tags = producttagMapper.findByProductid(visalist.getProductid(), costid);
			visalist.setProductTagList(tags);
		}
		 List<Tag> taglist = tagMapper.findAll(costid, 1);
		model.addAttribute("taglist",taglist);
		model.addAttribute("viaslist", viaslist);
		model.addAttribute("page", page);
		model.addAttribute("Tsearch", search);
		model.addAttribute("costIdo",costid);
		model.addAttribute("indexShow", indexShowInt);
		model.addAttribute("isshow", isShowInt);
		model.addAttribute("ishot", ishotInt);
		return costid;
	}

	@Transactional(readOnly = true)
	public Visa findById(String id) {
		return visaMapper.selectByPrimaryKey(id);
	}

   @Transactional(readOnly=true)
   public int getMaxSort(){
	   return visaMapper.getMaxSort();
   }

   @Transactional(readOnly=true)
   public Visa selectByProductId(String productid){
	   return visaMapper.selectByProductId(productid);
	   
   }
   @Transactional(readOnly=true)
   public List<Visa> selectByRegionId(String regionid){
	   return visaMapper.selectByRegionId(regionid);
   }
   
   @Transactional
   public List<Orders> findWithVisa(Integer type){
	   return ordersMapper.findWithVisa(type); 
   }
   @Transactional(readOnly=true)
   public void view(Model model,String id){
		model.addAttribute("us","$");	//默认币种
		//String costnumber = "33e25a5f67274fab94b84e21adb95ef0";
		
		String costnumber = null;
		List<Cost> costlist = (List<Cost>)request.getSession().getAttribute("cost");
		if(costlist!=null){
			costnumber = costlist.get(0).getId();
		}
		request.getSession().setAttribute("costnumber", costnumber);
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
		Visa visa =  visaMapper.selectByPrimaryKey(id);
		
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
		
	}
	
   
   public void create(String id,String costnumber) throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		request.getSession().setAttribute("costnumber", costnumber);
		List<Slider> slider = sliderService.findByType(2,costnumber);		//查找首页幻灯片图片
		root.put("slider", slider);
		
		List<Navigation> navigation = navigationService.findByType(1,costnumber);		//查找上导航
		root.put("navigation", navigation);
		
		List<Navigation> navigation2 = navigationService.findByType(2,costnumber);		//查找下导航
		root.put("navigation2", navigation2);
		
		List<Region> region = regionService.findByCostnumber(1,costnumber);		//查找线路产品分类
		root.put("region", region);
		List<Currency> currencies = currencyService.findAll(); //获取币种列表
		root.put("currencies", currencies);
		Cost cost = costService.findById(costnumber);
		root.put("frontCode", cost.getCode());
		Visa visa =  visaMapper.selectByPrimaryKey(id);
		
			visa.setAcceptancerange(Tools.replaceStr(visa.getAcceptancerange()));		
		
		
			visa.setBookingPolicy(visa.getBookingPolicy().replace("\r\n", "</li><br><li>"));
		
		List<Visaoccupation> visaocctype = visaoccupationService.findByVisaType(visa.getId());
		List<Visaoccupation> visaocclist =  visaoccupationService.findByVisaId(visa.getId());
		for (Visaoccupation visaoccupation : visaocclist) {
			visaoccupation.setContent(Tools.replaceStr(visaoccupation.getContent()));
		}
		//设置线路页面的title
		Page visaPage = visa.getProductProductid().getPagePageid(); 
		String title = visaPage.getMetatitle();
		if (title==null || ("").equals(title)) {
			String tourname = visa.getProductProductid().getName();
			if (tourname!=null && !("").equals(tourname)) {
				visaPage.setMetatitle(tourname);
			}else{
				visaPage.setMetatitle(visa.getProductProductid().getName());
			}
		}
		root.put("visa",visa);
		root.put("visaocclist",visaocclist);
		root.put("visaocctype",visaocctype);
		root.put("request", request);
		
		
		// 静态页面的完整路径
		String str = request.getSession().getServletContext().getRealPath("/")+cost.getCode()+"/"+ visaPage.getFilepath();
		if (str.indexOf("/") != -1) {

			String folder = str.substring(0, str.lastIndexOf("/"));
			File f = new File(folder);
			if (!f.exists() && !f.isDirectory()) { // 是文件夹，且文件夹不存在则创建文件夹
				f.mkdir();
			}
			File file = new File(str);
			// 如果静态文件存在，则删除之前的静态页面，重新生成新的静态页面
			if (file.isFile() && file.exists()) {
				file.delete();
			}
			str = null;// 释放资源
			if (file != null) {
				file = null;
			}
			FreemarkerUtils.createHTML(
					request.getSession().getServletContext(), root,
					"/front/visa/visa_usa.ftl", cost.getCode()+"/"+visaPage.getFilepath());
		}
		showtourlineService.updateByisCreate(costnumber, visa.getId(), 2);
	} 
}
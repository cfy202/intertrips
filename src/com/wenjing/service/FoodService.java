package com.wenjing.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.Pages;
import com.wenjing.dao.FoodMapper;
import com.wenjing.dao.HotTourlineMapper;
import com.wenjing.dao.IndexshowtourlineMapper;
import com.wenjing.dao.OrderdetailMapper;
import com.wenjing.dao.OrdersMapper;
import com.wenjing.dao.PageMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.ProducttagMapper;
import com.wenjing.dao.ProductvideoMapper;
import com.wenjing.dao.PromotionMapper;
import com.wenjing.dao.PromotionProductMapper;
import com.wenjing.dao.ServiceItemProductMapper;
import com.wenjing.dao.ShowtourlineMapper;
import com.wenjing.dao.TagMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Food;
import com.wenjing.entity.Hotel;
import com.wenjing.entity.Navigation;
import com.wenjing.entity.Orders;
import com.wenjing.entity.Page;
import com.wenjing.entity.Product;
import com.wenjing.entity.Producttag;
import com.wenjing.entity.Region;
import com.wenjing.entity.Slider;
import com.wenjing.entity.Tag;
import com.wenjing.util.FileUtil;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明 
 * @author xiejin
 * @date 2015-4-23
 */
@Service
public class FoodService {

	@Resource
	private FoodMapper foodMapper;
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
	@Resource
	private OrderdetailMapper orderdetailMapper;
	@Resource
	private PromotionMapper promotionMapper;
	@Resource
	private PromotionProductMapper promotionproductMapper;
	@Resource
	private ProductvideoMapper productvideoMapper;
	@Resource
	private ServiceItemProductMapper serviceItemproductMapper;
	@Resource
	private ShowtourlineMapper showtourlineMapper;
	@Resource
	private IndexshowtourlineMapper indexshowtourlineMapper;
	@Resource
	private HotTourlineMapper hotTourlineMapper;
	
	@Transactional
	public void save(Food food, Page page, Product product) {
		page.setId(Tools.getUUID());
		pageMapper.insert(page);
		product.setId(Tools.getUUID());
		product.setPageid(page.getId());
		product.setExcludeLandPrice(0);
		productMapper.insert(product);
		food.setProductid(product.getId());
		foodMapper.insertSelective(food);
	}

	@Transactional
	public int update(Food food, Page page, Product product) {
		page.setId(product.getPageid());
		pageMapper.updateByPrimaryKeySelective(page);
		product.setId(food.getProductid());
		product.setExcludeLandPrice(0);
		productMapper.updateByPrimaryKey(product);
		return foodMapper.updateByPrimaryKeySelective(food);
	}

	@Transactional
	public String delete(String foodId,String productId,String pageId) {
		String notice ="";
		String filepath = null;
		Page  page = pageService.findById(pageId);
		if(page!=null){
			filepath = page.getFilepath();
		}
		List<Cost> listCost = costService.findAll();
		int ops = orderdetailMapper.selectCountByProductId(productId);
		if(ops==0){
			foodMapper.deleteByPrimaryKey(foodId);
			promotionproductMapper.deleteWithProductId(productId);
			producttagMapper.deleteWithProductIdAndCostnumber(productId, null);
			productvideoMapper.deleteWithProductIdAndCostnumber(productId, null);
			serviceItemproductMapper.deleteByProductId(productId);
			productMapper.deleteByPrimaryKey(productId);
			pageService.delete(pageId);
			//删除首页显示
			indexshowtourlineMapper.deleteBycostnumberAnaTourlineId(null, foodId);
			//删除显示
			showtourlineMapper.deleteBycostnumberAnaTourlineId(null, foodId);
			//删除热推
			hotTourlineMapper.deleteBycostnumberAnaTourlineId(null, foodId);
			for (Cost cost : listCost) {
				FileUtil util = new FileUtil();
				if(filepath!=null){
					util.DeleteFolder(request.getSession().getServletContext().getRealPath("/")+cost.getCode()+"/"+filepath);	
				}
			}
			notice = "删除成功！";
			
		}else{
			notice = "该线路产品有订单存在，不能删除！";
		}
		return notice;
	}

	@Transactional(readOnly = true)
	public String findAll(HttpServletRequest request,HttpServletResponse response, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String costid = request.getParameter("costId");
		String pageNow = request.getParameter("FopageNow");
		String search = request.getParameter("Fosearch");
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
			indexShow = WebUtils.getCookie(request, "foodIndexShow");
		}else {
			WebUtils.addCookie(request, response, "foodIndexShow", indexShow);
			pageNow = "1";
		}
		if(indexShow !=null && !"".equals(indexShow)){
			indexShowInt = Integer.parseInt(indexShow);
		}
		//是否显示
		String isshow = request.getParameter("isshow");
		if(isshow == null || "".equals(isshow)){
			isshow = WebUtils.getCookie(request, "foodIsshow");
		}else {
			WebUtils.addCookie(request, response, "foodIsshow", isshow);
			pageNow = "1";
		}
		if(isshow !=null && !"".equals(isshow)){
			isShowInt = Integer.parseInt(isshow);
		}
		//是否热推
		String ishot = request.getParameter("ishot");
		if(ishot == null || "".equals(ishot)){
			ishot = WebUtils.getCookie(request, "foodIshot");
		}else {
			WebUtils.addCookie(request, response, "fooodIshot", ishot);
			pageNow = "1";
		}
		if(ishot !=null && !"".equals(ishot)){
			ishotInt = Integer.parseInt(ishot);
		}
		
		if(pageSize==null||"".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "FopageSize");
		}else{
			WebUtils.addCookie(request, response, "FopageSize", pageSize);
			pageNow = 1+"";
		}
		if (search == null || "".equals(search)) {
			search = WebUtils.getCookie(request, "Fosearch");
		} else {
			WebUtils.addCookie(request, response, "Fosearch", search);
			pageNow = 1+"";
		}
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "FopageNow");
		} else {
			WebUtils.addCookie(request, response, "FopageNow", pageNow);
		}

		Pages page = null;
		List<Food> foodlist = new ArrayList<Food>();
		int totalCount = foodMapper.getFoodCount(costid, search, indexShowInt, isShowInt, ishotInt);
        if(pageSize==null){
        	pageSize=10+"";
        }
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
			foodlist = this.foodMapper.selectAllByPage(page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
			foodlist = this.foodMapper.selectAllByPage(
					page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);	
		} 
		
		for (Food food : foodlist) {
			List<Producttag> tags = producttagMapper.findByProductid(food.getProductid(), costid);
			food.setProductTagList(tags);
		}
		List<Tag> taglist = tagMapper.findAll(costid, 1);
		model.addAttribute("taglist",taglist);
		model.addAttribute("foodlist", foodlist);
		model.addAttribute("page", page);
		model.addAttribute("Hsearch", search);
		model.addAttribute("costIdo",costid);
		model.addAttribute("indexShow", indexShowInt);
		model.addAttribute("isshow", isShowInt);
		model.addAttribute("ishot", ishotInt);
		return costid;
	}

	@Transactional(readOnly = true)
	public Food findById(String id) {
		return foodMapper.selectByPrimaryKey(id);
	}

   @Transactional(readOnly=true)
   public int getMaxSort(){
	   return foodMapper.getMaxSort();
   }

   @Transactional(readOnly=true)
   public Food selectByProductId(String productid){
	   return foodMapper.selectByProductId(productid);
	   
   }
   @Transactional(readOnly=true)
   public List<Food> selectByRegionId(String regionid){
	   return foodMapper.selectByRegionId(regionid);
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
		Food food =  foodMapper.selectByPrimaryKey(id);
		
		model.addAttribute("food",food);
		
		
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
		Food food =  foodMapper.selectByPrimaryKey(id);
		
		
		//设置线路页面的title
		Page visaPage = food.getProductProductid().getPagePageid(); 
		String title = visaPage.getMetatitle();
		if (title==null || ("").equals(title)) {
			String tourname = food.getProductProductid().getName();
			if (tourname!=null && !("").equals(tourname)) {
				visaPage.setMetatitle(tourname);
			}else{
				visaPage.setMetatitle(food.getProductProductid().getName());
			}
		}
		root.put("food",food);
		
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
		showtourlineService.updateByisCreate(costnumber, food.getId(), 2);
	} 
   
   @Transactional(readOnly=true)
   public List<Food> selectByids(List<String> ids){
	   return foodMapper.selectByids(ids);
   }
}

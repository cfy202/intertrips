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
import com.wenjing.dao.AdmissionticketMapper;
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
import com.wenjing.entity.Admissionticket;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Food;
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
public class AdmissionticketService {

	@Resource
	private AdmissionticketMapper admissionticketMapper;
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
	public void save(Admissionticket admissionticket, Page page, Product product) {
		page.setId(Tools.getUUID());
		pageMapper.insert(page);
		product.setId(Tools.getUUID());
		product.setPageid(page.getId());
		product.setExcludeLandPrice(0);
		productMapper.insert(product);
		admissionticket.setProductid(product.getId());
		admissionticketMapper.insertSelective(admissionticket);
	}

	@Transactional
	public int update(Admissionticket admissionticket, Page page, Product product) {
		page.setId(product.getPageid());
		pageMapper.updateByPrimaryKeySelective(page);
		product.setId(admissionticket.getProductid());
		product.setExcludeLandPrice(0);
		productMapper.updateByPrimaryKey(product);
		return admissionticketMapper.updateByPrimaryKeySelective(admissionticket);
	}

	@Transactional
	public String  delete(String admissionId,String productId,String pageId) {
		String notice ="";
		String filepath = null;
		Page  page = pageService.findById(pageId);
		if(page!=null){
			filepath = page.getFilepath();
		}
		List<Cost> listCost = costService.findAll();
		int ops = orderdetailMapper.selectCountByProductId(productId);
		if(ops==0){
			admissionticketMapper.deleteByPrimaryKey(admissionId);
			promotionproductMapper.deleteWithProductId(productId);
			producttagMapper.deleteWithProductIdAndCostnumber(productId, null);
			productvideoMapper.deleteWithProductIdAndCostnumber(productId, null);
			serviceItemproductMapper.deleteByProductId(productId);
			productMapper.deleteByPrimaryKey(productId);
			pageService.delete(pageId);
			//删除首页显示
			indexshowtourlineMapper.deleteBycostnumberAnaTourlineId(null, admissionId);
			//删除显示
			showtourlineMapper.deleteBycostnumberAnaTourlineId(null, admissionId);
			//删除热推
			hotTourlineMapper.deleteBycostnumberAnaTourlineId(null, admissionId);
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
		String pageNow = request.getParameter("AtpageNow");
		String search = request.getParameter("Atsearch");
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
			indexShow = WebUtils.getCookie(request, "admissionticketIndexShow");
		}else {
			WebUtils.addCookie(request, response, "admissionticketIndexShow", indexShow);
			pageNow = "1";
		}
		if(indexShow !=null && !"".equals(indexShow)){
			indexShowInt = Integer.parseInt(indexShow);
		}
		//是否显示
		String isshow = request.getParameter("isshow");
		if(isshow == null || "".equals(isshow)){
			isshow = WebUtils.getCookie(request, "admissionticketIsshow");
		}else {
			WebUtils.addCookie(request, response, "admissionticketIsshow", isshow);
			pageNow = "1";
		}
		if(isshow !=null && !"".equals(isshow)){
			isShowInt = Integer.parseInt(isshow);
		}
		//是否热推
		String ishot = request.getParameter("ishot");
		if(ishot == null || "".equals(ishot)){
			ishot = WebUtils.getCookie(request, "admissionticketIshot");
		}else {
			WebUtils.addCookie(request, response, "admissionticketIshot", ishot);
			pageNow = "1";
		}
		if(ishot !=null && !"".equals(ishot)){
			ishotInt = Integer.parseInt(ishot);
		}
		
		if(pageSize==null||"".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "AtpageSize");
		}else{
			WebUtils.addCookie(request, response, "AtpageSize", pageSize);
			pageNow = 1+"";
		}
		if (search == null || "".equals(search)) {
			search = WebUtils.getCookie(request, "Atsearch");
		} else {
			WebUtils.addCookie(request, response, "Atsearch", search);
			pageNow = 1+"";
		}
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "AtpageNow");
		} else {
			WebUtils.addCookie(request, response, "AtpageNow", pageNow);
		}

		Pages page = null;
		List<Admissionticket> admissionlist = new ArrayList<Admissionticket>();
		int totalCount = admissionticketMapper.getAdmissionticketCount(costid, search, indexShowInt, isShowInt, ishotInt);
        if(pageSize==null){
        	pageSize=10+"";
        }
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
			admissionlist = this.admissionticketMapper.selectAllByPage(page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
			admissionlist = this.admissionticketMapper.selectAllByPage(
					page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);	
		} 
		
		for (Admissionticket admission : admissionlist) {
			List<Producttag> tags = producttagMapper.findByProductid(admission.getProductid(), costid);
			admission.setProductTagList(tags);
		}
		List<Tag> taglist = tagMapper.findAll(costid, 1);
		model.addAttribute("taglist",taglist);
		model.addAttribute("admissionlist", admissionlist);
		model.addAttribute("page", page);
		model.addAttribute("Atsearch", search);
		model.addAttribute("costIdo",costid);
		model.addAttribute("indexShow", indexShowInt);
		model.addAttribute("isshow", isShowInt);
		model.addAttribute("ishot", ishotInt);
		return costid;
	}

	@Transactional(readOnly = true)
	public Admissionticket findById(String id) {
		return admissionticketMapper.selectByPrimaryKey(id);
	}

   @Transactional(readOnly=true)
   public int getMaxSort(){
	   return admissionticketMapper.getMaxSort();
   }

   @Transactional(readOnly=true)
   public Admissionticket selectByProductId(String productid){
	   return admissionticketMapper.selectByProductId(productid);
	   
   }
   @Transactional(readOnly=true)
   public List<Admissionticket> selectByRegionId(String regionid){
	   return admissionticketMapper.selectByRegionId(regionid);
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
		Admissionticket admissionticket =  admissionticketMapper.selectByPrimaryKey(id);
		
		model.addAttribute("admissionticket",admissionticket);
		
		
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
		Admissionticket admissionticket =  admissionticketMapper.selectByPrimaryKey(id);
		
		
		//设置线路页面的title
		Page visaPage = admissionticket.getProductProductid().getPagePageid(); 
		String title = visaPage.getMetatitle();
		if (title==null || ("").equals(title)) {
			String tourname = admissionticket.getProductProductid().getName();
			if (tourname!=null && !("").equals(tourname)) {
				visaPage.setMetatitle(tourname);
			}else{
				visaPage.setMetatitle(admissionticket.getProductProductid().getName());
			}
		}
		root.put("food",admissionticket);
		
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
		showtourlineService.updateByisCreate(costnumber, admissionticket.getId(), 2);
	} 
   
   @Transactional(readOnly=true)
   public List<Admissionticket> selectByids(List<String> ids){
	   return admissionticketMapper.selectByids(ids);
   }
}

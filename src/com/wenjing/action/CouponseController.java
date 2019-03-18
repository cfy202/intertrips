package com.wenjing.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.dao.CouponseproductMapper;
import com.wenjing.dao.CouponslevelMapper;
import com.wenjing.dao.MemberMapper;
import com.wenjing.dao.TourlineMapper;
import com.wenjing.entity.Activityrules;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Couponsactivity;
import com.wenjing.entity.Couponsduijiang;
import com.wenjing.entity.Couponse;
import com.wenjing.entity.Couponslevel;
import com.wenjing.entity.Member;
import com.wenjing.entity.Product;
import com.wenjing.service.ActivityroulesService;
import com.wenjing.service.CostService;
import com.wenjing.service.CouponseService;
import com.wenjing.service.CouponseduijiangService;
import com.wenjing.service.ProductService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-24 下午6:49:23 
 * 类说明 ：幻灯片管理 - Controller
 */
@Controller
@RequestMapping("/admin/couponse")
public class CouponseController {
	@Resource
	private CouponseService couponseSerivce;
	@Resource
	private CouponslevelMapper couponselevelMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CostService costService;
	@Resource
	private MemberMapper memberMapper;
	@Resource
	private TourlineMapper turlineMapper;
	@Resource
	private CouponseproductMapper couponseproductMapper;
	
	@Resource
	private ProductService productService; 
	@Resource
	private CouponseduijiangService couponseduijiangService;
	@Resource
	private ActivityroulesService activityroulesService;
	@Resource
	private TourlineService tourlineService;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Couponse> couponselist = couponseSerivce.findAll();
		model.addAttribute("couponselist", couponselist);
		return "/admin/manage/couponse/couponselist.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(Couponse couponse,Model model) {
		List<String> coList = Tools.getCostNumber(request);
		String costnumber = coList.get(0);
		String id = Tools.getUUID();
		couponse.setId(id);
		int sort = couponseSerivce.getMaxSort(null);
		couponse.setSort(sort+1);
		String nowhour = Tools.getDtime();
		model.addAttribute("couponse",couponse);
		model.addAttribute("nowhour",nowhour);
		List<Product> products = productService.findAllByCostnumber(costnumber);
		model.addAttribute("products",products);
		// 获取运营中心列表
		List<Cost> costs = costService.findAllCostByCostNumber(request);
		model.addAttribute("costs", costs);
		return "/admin/manage/couponse/couponseadd.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		if (id != null && !"".equals(id)) {
			Couponse couponse = couponseSerivce.findById(id);
			Cost costs = costService.findById(couponse.getCostnumber());
			// 获取参与活动产品列表
			List<Product> promotionproduct = productService.findByCouponseId(id);
			// 根据costnumber获取产品列表
			List<Product> products = productService.findAllByCostnumber(couponse
					.getCostnumber());
			model.addAttribute("products", products);
			model.addAttribute("promotionproduct", promotionproduct);
			model.addAttribute("couponse", couponse);
			model.addAttribute("costs", costs);
		}
		return "/admin/manage/couponse/couponseupdate.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Couponse couponse) {
		String id = couponse.getId();
		String [] tourlinesC = request.getParameterValues("productIds"); 
		String starttime = couponse.getBeginTime();
		String endtime = couponse.getOverTime();
		if(starttime!=null && endtime!=null){
			couponse.setStartTime(Tools.getDtimestemp(starttime));
			couponse.setEndTime(Tools.getDtimestemp(endtime));
		}
		if (id != null && !"".equals(id)) {
			// update
			couponseSerivce.update(couponse,tourlinesC);
		} else {
			// insert
			couponse.setRemaining(couponse.getAmount());
			couponse.setId(Tools.getUUID());
			couponseSerivce.save(couponse,tourlinesC);
		}
		return "redirect:/admin/couponse/list.do";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		if (id != null && !"".equals(id)) {
			couponseSerivce.delete(id);
		} 
		return "redirect:/admin/couponse/list.do";
	}
   /**
    * 
    * @param id
    * @param model
    * @return
    */
	@RequestMapping("/realse")
	public String realse(@RequestParam("id") String id,Model model){
	String notice = null;	
    List<Couponsduijiang>  couponsduijinags = couponseduijiangService.findByCouponseId(id, 0);
    Couponse couponse = couponseSerivce.findById(id);
    if(couponsduijinags!=null&&couponsduijinags.size()>0&&couponse.getType()==3){
    	notice = "该优惠券已经成功发布过";
    	model.addAttribute("notice",notice);
    	return "redirect:/admin/couponse/list.do";
    }else{
    	List<Member> memberlist = memberMapper.findAll();
    	model.addAttribute("memberlist",memberlist);
    	List<Couponslevel> couponslevel = couponselevelMapper.findByCouponseId(id);
    	
    	model.addAttribute("couponslevel",couponslevel);
    	model.addAttribute("id",id);
    	model.addAttribute("couponse",couponse);
    	return "/admin/manage/couponse/realse.ftl";
    	 
    }
		
	}
	
	/**
	 * 给会员派送
	 * @param act
	 * @param place
	 * @return
	 */
	@RequestMapping("/give")
	public String give(Couponsactivity act,@RequestParam("place")Integer place){
		String[] paters = request.getParameterValues("my-select[]");
		Couponse couponse = couponseSerivce.findById(act.getCouponseid());
		if(couponse.getType()!=1){
			couponseSerivce.giveCompany(couponse, act.getLevelid());
		}else{
			couponseSerivce.give(act, paters, place);
		}
		return "redirect:/admin/couponse/list.do";
	}
	
	@RequestMapping("/issued")
	public String issued(@RequestParam("id") String id,Model model){
		List<Couponsduijiang> couponsduijiang = couponseduijiangService.findByCouponseId(id,null);
		List<Couponsduijiang> couponsduijingN = couponseduijiangService.findByCouponseId(id,0);
		List<Couponsduijiang> couponsduijingY = couponseduijiangService.findByCouponseId(id,1);
		model.addAttribute("couponsduijiang",couponsduijiang);
		model.addAttribute("couponsduijingN",couponsduijingN);
		model.addAttribute("couponsduijingY",couponsduijingY);
		return "/admin/manage/couponse/Issuedlist.ftl";
	}
	
	/**
	 * 异步获取orderid最大值和上级导航
	 */


	@RequestMapping("/getsort")
	@ResponseBody
	public Map<String, Object> getSort(
			@RequestParam("costnumber") String costnumber) {
		Map<String, Object> root = new HashMap<String, Object>();
		List<Product> products = productService.findAllByCostnumber(costnumber);
		Integer sort = couponseSerivce.getMaxSort(costnumber);// 根据运营中心costnumber,查询运营中心orderid最大值
		root.put("products", products);
		root.put("sort", sort + 1);
		return root;


	}
	/**
	 * 预览活动页面
	 * @param couponseid
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String view(@Param("couponseid")String couponseid,Model model){
		Couponse couponse = couponseSerivce.findById(couponseid);
		model.addAttribute("couponse",couponse);
		List<Activityrules> activityrules = activityroulesService.findByCouponseId(couponseid);
		if(couponse.getType()==1){
			model.addAttribute("activityrules",activityrules);
			return "/admin/manage/couponse/acty.ftl";
		}else{
			tourlineService.findTourlineByCouponseid(model, couponse.getCostnumber(), couponse.getId());
			return "/admin/manage/couponse/couponseTs.ftl";
		}
	}
	
	@RequestMapping("/createHtml")
	public String create(@RequestParam("tids") String[] tids) throws Exception {
		if (tids != null && tids.length > 0) {
			for (String string : tids) {
				couponseSerivce.create(string);
			}
			
		}
		return "redirect:/admin/couponse/list.do";
	}
	
	
}

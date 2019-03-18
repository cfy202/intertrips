package com.wenjing.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.dao.CouponseMapper;
import com.wenjing.dao.CouponslevelMapper;
import com.wenjing.entity.Couponse;
import com.wenjing.entity.Couponslevel;
import com.wenjing.service.CostService;
import com.wenjing.service.CouponseService;
import com.wenjing.service.CouponslevelService;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-24 下午6:49:23 
 * 类说明 ：幻灯片管理 - Controller
 */
@Controller
@RequestMapping("/admin/level")
public class CouponslevelController {
	@Resource
	private CouponslevelService couponslevelSerivce;
	@Resource
	private CouponslevelMapper couponselevelMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CostService costService;
	@Resource
	private CouponseService couponseService;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(@RequestParam("couponseid")String couponseid,Model model) {
		
		List<Couponslevel> couponslevel = couponslevelSerivce.findAll(couponseid);
		Couponse couponse = couponseService.findById(couponseid);
		model.addAttribute("couponseid",couponseid);
		model.addAttribute("couponslevel", couponslevel);
		model.addAttribute("couponse",couponse);
		return "/admin/manage/level/levellist.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(@RequestParam("couponseid")String couponseid,Couponslevel couponslevel,Model model) {
		String id = Tools.getUUID();
		couponslevel.setId(id);
		
		model.addAttribute("Couponslevel",couponslevel);
		// 获取运营中心列表
		
		model.addAttribute("couponseid", couponseid);
		return "/admin/manage/level/leveladd.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		if (id != null && !"".equals(id)) {
			Couponslevel couponslevel = couponslevelSerivce.findById(id);
			model.addAttribute("couponslevel", couponslevel);
		}
		return "/admin/manage/level/levelupdate.ftl";
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(Couponslevel couponslevel) {
		String id = couponslevel.getId();
		if (id != null && !"".equals(id)) {
			// update
			couponslevelSerivce.update(couponslevel);
		} else {
			// insert
			couponslevel.setRemainingQuota(couponslevel.getPlaces());
			couponslevel.setId(Tools.getUUID());
			couponslevelSerivce.save(couponslevel);
		}
		return "redirect:/admin/level/list.do?couponseid="+couponslevel.getCouponseid();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id,@RequestParam("couponseid")String couponseid) {
		if (id != null && !"".equals(id)) {
			couponslevelSerivce.delete(id);
		} 
		return "redirect:/admin/level/list.do?couponseid="+couponseid;
	}
   
  @RequestMapping(value="/updateplace", method = RequestMethod.POST)
  public @ResponseBody int updateplace(@RequestParam("id")String id){
	  Couponslevel couponslvel = couponslevelSerivce.findById(id);
	  return couponslvel.getPlaces();
  }
}

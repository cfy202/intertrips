package com.wenjing.action.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wenjing.entity.Member;
import com.wenjing.entity.Orders;
import com.wenjing.entity.ResultDTO;
import com.wenjing.entity.ShoppingCart;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.MemberService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.OrderService;
import com.wenjing.service.RSAService;
import com.wenjing.service.RegionService;
import com.wenjing.service.ShoppingCartService;
import com.wenjing.service.TagService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-4-27 下午3:43:33 
 * 类说明 ：会员登录 Controller
 */
@Controller("frontLoginController")
@RequestMapping("/login")
public class LoginController {
	@Resource
	private MemberService memberService;
	@Resource
	private RSAService rsaService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Resource
	private RegionService regionService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private CostService costService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private TagService tagservice;
	
	public static final int SCORE =10;  // 积分

	/**
	 * 用户登录页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(String redirectUrl, HttpServletRequest request, Model model) {
//		if (redirectUrl != null && !redirectUrl.equalsIgnoreCase(siteUrl) && !redirectUrl.startsWith(request.getContextPath() + "/") && !redirectUrl.startsWith(siteUrl + "/")) {
//			redirectUrl = null;
//		}
		model.addAttribute("redirectUrl", redirectUrl);
		return "/front/member/login/user_login.ftl";
	}

	/**
	 * 登录提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	ResultDTO loginSubmit(String account,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String password = rsaService.decryptParameter("enPassword", request);
		rsaService.removePrivateKey(request);
		ResultDTO result = new ResultDTO();
		Member member = memberService.findByAccount(account);
		if (member == null) {
			result.setSuccess(false);
			result.setMessage("The account does not exist.");
			return result;
		}
		if(member.getUserstatus() == 0){
			result.setSuccess(false);
			result.setMessage("The account is not <a class=\"again-activate\" href=\"javascript:;\" onclick=\"againActivate();\">activated</a>");
			return result;
		}
		if (!DigestUtils.md5Hex(password).equals(member.getPassword())) {
			result.setSuccess(false);
			result.setMessage("Your password is incorrect.");
			return result;
		}
		result.setSuccess(true);
		result.setMessage("Login Successful");
		request.getSession().setMaxInactiveInterval(45 * 60);
		request.getSession().setAttribute("islogin", true);
		request.getSession().setAttribute("member", member);
		//如果用户为第一次登陆则送10积分
        String lasttime = Tools.getTime(Tools.getDtimestemp(member.getLasttime()));  
        String current_time = Tools.getTime();
        if(!lasttime.equals(current_time)){  
            // 不同一天  , 积分增加提示信息  
        	memberService.updateScoreByMemberId(SCORE, member.getId());//
        }  
		memberService.updateLasttime(Tools.getHHtime(), member.getId());// 修改最后登录时间为当前时间
		WebUtils.addCookie(request, response, Member.MEMBER_COOKIE_NAME, JSON.toJSONString(member),40 * 60);
		WebUtils.addCookie(request, response, "JSESSIONID", request.getSession().getId(),40 * 60);
		return result;
	}
	
//	/**
//	 * 判断用户是否登录
//	 */
//	@RequestMapping("/isLogin")
//	@ResponseBody
//	public boolean isLogin(HttpServletRequest request){
//		boolean flag = false;
//		Member member = Tools.getMember(request);
//		Boolean islogin = (Boolean) request.getSession().getAttribute("islogin");
//		if(member != null && islogin){
//			flag = true;
//		}
//		return flag;
//	}
	
	/**
	 * 异步检验订单号是否存在
	 */
	@RequestMapping("/isOrderNo")
	@ResponseBody
	public boolean isOrderNo(@RequestParam("orderNo") String orderNo){
		boolean flag = false;
		if(orderNo != null && !"".equals(orderNo)){
			Orders orders = orderService.finByOrderNo(orderNo);
			if(orders != null){
				flag = true;
			}
		}
		return flag;
	}
	
//	/**
//	 * 获得登录用户的信息
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/getMember")   
//	public @ResponseBody Member getLoginMember(HttpServletRequest request,HttpServletResponse response){
//		Member member = (Member)request.getSession().getAttribute("member");
//		Boolean islogin = (Boolean) request.getSession().getAttribute("islogin");
//		if(!islogin){
//			member = null;
//			WebUtils.removeCookie(request, response, Member.MEMBER_COOKIE_NAME);
//		}else{
//			WebUtils.addCookie(request, response, Member.MEMBER_COOKIE_NAME, JSON.toJSONString(member),40 * 60);
//			WebUtils.addCookie(request, response, "JSESSIONID", request.getSession().getId(),40 * 60);
//		}
//		return member;
//	}
	
	/**
	 * 非会员订单查询
	 */
//	@RequestMapping("/non_Members_order")
//	public String non_Members_order(@RequestParam(value="non_user_account", required=false) String nonUserAccount, 
//			                        @RequestParam(value="non_user_orderNo", required=false) String nonUserOrderNo,
//			                        Model model, final RedirectAttributes redirectAttributes){
//		if(nonUserOrderNo != null && !"".equals(nonUserOrderNo) && nonUserAccount != null && !"".equals(nonUserAccount)){
//			String costnumber = (String) request.getSession().getAttribute("costnumber");
//			Cost cost = costService.findById(costnumber);	//对应的销售中心
//			model.addAttribute("frontCode", cost.getCode());
//			List<Navigation> navigation = navigationService.findByType(1, costnumber); // 查找上导航
//			for (Navigation navigation2 : navigation) {
//				String regionId = regionService.findIdByUrl(navigation2.getUrl());	//查询导航对应的分类
//				if (regionId !=null) {
//					List<Tag> tagList = tagservice.findRegionShow(regionId, costnumber);	//查询分类对应的标签集合
//					navigation2.setTagList(tagList);
//				}
//			}
//			model.addAttribute("navigation", navigation);
//			List<Navigation> navigation2 = navigationService.findByType(2, costnumber); // 查找下导航
//			model.addAttribute("navigation2", navigation2);
//			List<Region> region = regionService.findByCostnumber(1, costnumber); // 查找产品分类
//			model.addAttribute("region", region);
//			List<Navigation> navList = navigationService.findHelpCenterByCostnum("帮助中心",costnumber);//帮助中心
//			model.addAttribute("navList", navList);
//			List<Currency> currencies = currencyService.findAll(); //获取币种列表
//			model.addAttribute("currencies", currencies);
//		    Orders orders = orderService.getOrderDetailByOrderNoAndEmail(nonUserOrderNo, nonUserAccount);
//		    if(orders != null){
//		    	model.addAttribute("orders", orders);
//				return "/front/member/orders/non_user_orders.ftl";
//		    }else {
//		    	redirectAttributes.addFlashAttribute("orderError", "订单不存在");
//		    	return "redirect:/login.htm";
//			}
//		}else {
//			redirectAttributes.addFlashAttribute("orderError", "订单不存在");
//			return "redirect:/login.htm";
//		}
//	}
}

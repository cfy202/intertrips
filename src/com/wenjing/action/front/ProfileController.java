package com.wenjing.action.front;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.DateEditor;
import com.wenjing.Mail;
import com.wenjing.dao.OrderdetailMapper;
import com.wenjing.dao.TourPassengerMapper;
import com.wenjing.entity.AdditionalProduct;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Couponsduijiang;
import com.wenjing.entity.GetScore;
import com.wenjing.entity.Member;
import com.wenjing.entity.Memberinformation;
import com.wenjing.entity.OrderAttachment;
import com.wenjing.entity.Orderdetail;
import com.wenjing.entity.Orders;
import com.wenjing.entity.ResultDTO;
import com.wenjing.entity.TourPassenger;
import com.wenjing.service.CostService;
import com.wenjing.service.CouponseduijiangService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.MemberService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.OrderAttachmentService;
import com.wenjing.service.OrderService;
import com.wenjing.service.PageService;
import com.wenjing.service.RegionService;
import com.wenjing.service.ScoreService;
import com.wenjing.service.TagService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-26 上午9:33:10 
 * 类说明 ： 会员中心 - 个人资料
 */
@Controller
@RequestMapping("/member/profile")
public class ProfileController {
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private NavigationService navigationService;
	
	@Resource
	private RegionService regionService;
	
	@Resource
	private TourlineService tourlineService;
	
	@Resource
	private TourDateService tourDateService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private CostService costService;
	
	@Resource
	private CurrencyService currencyService;
	@Resource
	private TagService tagService;
	@Autowired
	private CouponseduijiangService couponsduijiangService;
	@Autowired
	private ScoreService scoreService;
	@Resource
	private TourPassengerMapper tourpassengerMapper;
	@Resource
	private OrderdetailMapper orderdetailMapper;
	@Resource
	private OrderAttachmentService orderAttachmentService;
	
	@Resource
	private PageService pageService;
	private static final String TEMPLATE_URL = "/member/mailtemplate/success_mail.ftl"; // 获取激活邮件模板路径
	
	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * 用户中心页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model,@Param("cValue")String cValue) {
		Member member = Tools.getMember(request); // 用户信息
		Memberinformation memberinformation = memberService.findInfoByMemberId(member.getId());
		
		Cost costFront = costService.findByCode(cValue);
		model.addAttribute("member", member);
		model.addAttribute("info", memberinformation);
		model.addAttribute("frontCode",costFront.getCode());
		
		pageService.getNavigation(model, costFront.getId());
		//币种显示
		model.addAttribute("costFront", costFront);
//		//首页热卖线路
//		List<Tourline> tourline = tourlineService.findRandomByHotIndexShow(1,1, costnumber,1); //查找首页显示的热卖线路
//		for (Tourline tourline2 : tourline) {
//			BigDecimal lowmprice = tourDateService.getminmprice(tourline2.getId(),costnumber); //产品最低标价
//			tourline2.setLowmprice(lowmprice);
//			BigDecimal lowsprice = tourDateService.getminsellPrice(tourline2.getId(),costnumber); //产品最低售价
//			//判断是否参与促销活动，并修改最低价格		
//			lowsprice = tourlineService.changeSellprice(lowsprice, tourline2.getId(), costnumber);
//			tourline2.setLowsprice(lowsprice);
//			String imageurl = tourline2.getProductProductid().getImageurl(); //获得产品相关图片路径
//			if(imageurl!=null && !imageurl.equals("")){
//				String[] url = (imageurl+",").split(",");
//				tourline2.setCoverimageurl(url[0]);	//设置线路封面图路径
//			}else{
//				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); //设置线路封面图路径
//			}
//		}
//		model.addAttribute("tourline", tourline);
		String active = "Personal center";
		GregorianCalendar ca = new GregorianCalendar(); //判断上午下午
		int time = ca.get(GregorianCalendar.AM_PM);
		model.addAttribute("time", time);
		model.addAttribute("active",active);
		return "/front/member/profile/user_center.ftl";
	}

	/**
	 * 编辑个人资料
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model,@Param("cValue")String cValue) {
		Cost costFront = costService.findByCode(cValue);
		pageService.getNavigation(model, costFront.getId());
		Member member = Tools.getMember(request); // 用户信息
		if (member != null) {
			Memberinformation memberinformation = memberService.findInfoByMemberId(member.getId());
			List<GetScore> getScoreList = memberService.findGetScoreBymemberid(member.getId(), 0);
			String active = "Personal Information";
			model.addAttribute("active",active);
			model.addAttribute("info", memberinformation);
			model.addAttribute("member", member);
			model.addAttribute("getScores", getScoreList);
			model.addAttribute("frontCode",costFront.getCode());
			return "/front/member/profile/user_profile.ftl";
		} else {
			return "redirect:/login.htm";
		}
	}

	/**
	 * 更新个人资料
	 */
	@RequestMapping("/update")
	public String update(Memberinformation memberinformation, final RedirectAttributes redirectAttributes,@Param("cValue")String cValue) {
		int success = memberService.updateInfo(memberinformation);
		ResultDTO result = new ResultDTO();
		if (success > 0) {
			result.setSuccess(true);
			result.setMessage("Your information has been successfully updated");
		} else {
			result.setSuccess(false);
			result.setMessage("Your information update failed, please modify it again");
		}
		redirectAttributes.addFlashAttribute("result", result);
		return "redirect:/member/profile/edit.htm?cValue="+cValue;
	}

	/**
	 * 密码修改
	 */
	@RequestMapping("/edit_password")
	public String editPassword(Model model,@Param("cValue")String cValue) {
		Cost costFront = costService.findByCode(cValue);
		pageService.getNavigation(model, costFront.getId());
		Member member = Tools.getMember(request);
		String active = "Change password";
		model.addAttribute("active",active);
		model.addAttribute("cValue",cValue);
		if (member != null) {
			return "/front/member/password/user_password.ftl";
		} else {
			return "redirect:/login.htm";
		}
	}

	/**
	 * 异步验证当前密码
	 */
	@RequestMapping(value = "/check_current_password", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkCurrentPassword(String currentPassword) {
		if (currentPassword == null || "".equals(currentPassword)) {
			return false;
		}
		Member member = Tools.getMember(request);// 获取当前登录会员信息
		if (member != null) {
			if (DigestUtils.md5Hex(currentPassword).equals(member.getPassword())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 更新密码
	 */
	@RequestMapping(value = "/updatepass", method = RequestMethod.POST)
	public String update(String currentPassword, String userpassword, HttpServletRequest request, final RedirectAttributes redirectAttributes,@Param("cValue")String cValue) {
		ResultDTO result = new ResultDTO();
		if (currentPassword != null && !"".equals(currentPassword) && userpassword != null && !"".equals(userpassword)) {
			Member member = (Member) request.getSession().getAttribute("member");// 获取当前登录会员信息
			member.setPassword(DigestUtils.md5Hex(userpassword));
			memberService.updatePassword(member);
			result.setSuccess(true);
			result.setMessage("Your password has been successfully changed");
			sendMail(member.getAccount()); //发送密码修改成功邮件
		} else {
			result.setSuccess(false);
			result.setMessage("Your password change failed, would you please modify again");
		}
		redirectAttributes.addFlashAttribute("result", result);
		
		return "redirect:/member/profile/edit_password.htm?cValue="+cValue;
	}

	/**
	 * @Title sendMail
	 * @Description 密码修改成功后发送邮件
	 * @Author Bowden
	 * @CreateDate 2015-8-14 上午10:48:12
	 */
	private void sendMail(String account) {
		Mail gmail = new Mail();
		List<String> list = new ArrayList<String>();
		list.add(account);
		String[] array = list.toArray(new String[list.size()]);
		gmail.setSendTo(array);
		String emailMsgTxt = FreemarkerUtils.getActivateMail(request.getSession().getServletContext(), account, Tools.getDtime(), TEMPLATE_URL); // 获取激活邮件内容
		gmail.setEmailMsgTxt(emailMsgTxt);
		gmail.setEmailSubjectTxt("intertrips.com - Password is changed");
		try {
			gmail.sendSSLMessage(); // 发送邮件
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 订单
	 */
	@RequestMapping("/user_orders")
	public String orders(Model model,@Param("cValue")String cValue) {
		
		Member member = Tools.getMember(request); // 用户信息
		Cost costFront = costService.findByCode(cValue);
		pageService.getNavigation(model, costFront.getId());
		String active = "My order";
		model.addAttribute("active",active);
		model.addAttribute("frontCode",costFront.getCode());
		List<Orders> orders = orderService.findByMemberId(member.getId(), request, 1);
		
		//线路
		//List<Orders> visaOrders = orderService.findByMemberId(member.getId(), request, 2); //签证
		model.addAttribute("orders", orders);
		//model.addAttribute("visaOrders", visaOrders);
		return "/front/member/orders/user_orders_progress.ftl";
	}
	
	/**
	 * 订单
	 */
	@RequestMapping("/user_orders_more")
	public String user_orders_more(Model model,@Param("cValue")String cValue) {
		
		Member member = Tools.getMember(request); // 用户信息
		Cost costFront = costService.findByCode(cValue);
		pageService.getNavigation(model, costFront.getId());
		String active = "My order";
		model.addAttribute("active",active);
		model.addAttribute("frontCode",costFront.getCode());
		List<Orders> orders = orderService.findByMemberId(member.getId(), request, 1);
		
		//线路
		//List<Orders> visaOrders = orderService.findByMemberId(member.getId(), request, 2); //签证
		model.addAttribute("orders", orders);
		//model.addAttribute("visaOrders", visaOrders);
		return "/front/member/orders/user_orders.ftl";
	}
	
	/**
	 * 积分明细
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user_score")
	public String showGetScore(Model model,@Param("cValue")String cValue){
		Cost costFront = costService.findByCode(cValue);
		pageService.getNavigation(model, costFront.getId());
		Member member = Tools.getMember(request); // 用户信息
		Memberinformation memberinformation = memberService.findInfoByMemberId(member.getId());
		List<GetScore> getScoreList = scoreService.findByMemberId(member.getId());
		for(GetScore getScore : getScoreList){
			getScore.setGetTimeString(Tools.getDtime(getScore.getGettime()));
		}
		model.addAttribute("info", memberinformation);
		model.addAttribute("getScoreList", getScoreList);
		return "/front/member/orders/user_score.ftl";
	}
	
	/**
	 * 优惠券明细
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user_couponse")
	public String showCouponse(Model model,@Param("cValue")String cValue){
		Cost costFront = costService.findByCode(cValue);
		pageService.getNavigation(model, costFront.getId());
		Member member = Tools.getMember(request); // 用户信息
		List<Couponsduijiang> couponseList = couponsduijiangService.findByMemberId(member.getId());
		model.addAttribute("couponseList", couponseList);
		String active = "Coupons";
 		model.addAttribute("active",active);
 		model.addAttribute("frontCode",costFront.getCode());
		return "/front/member/orders/user_couponse.ftl";
	}
	
	/**
	 * 线路订单详情页
	 */
     @RequestMapping("/orderdetail")
     public String orderdetail(@RequestParam("orderid") String orderid, Model model,@Param("cValue")String cValue){
    	 Cost costFront = costService.findByCode(cValue);
 		pageService.getNavigation(model, costFront.getId());
		Orders orders = orderService.getMemberOrderDetailById(orderid);
				
		List<AdditionalProduct> optionalTourList = new ArrayList<AdditionalProduct>();
		List<AdditionalProduct> optionalTourInTourlineList = new ArrayList<AdditionalProduct>();
		List<AdditionalProduct> additionalProductList = new ArrayList<AdditionalProduct>();
		
		BigDecimal optionalTourFee = new BigDecimal(0);
		BigDecimal optionalTourInTourlineFee = new BigDecimal(0);
		BigDecimal additionalProductFee = new BigDecimal(0);
		
		List<AdditionalProduct> productList = orders.getOrderdetails().get(0).getAdditionalProductList();
		if(productList != null && productList.size() > 0 ){
			for(AdditionalProduct additionalProduct : productList){
				if(additionalProduct.getType() == 10){
					optionalTourList.add(additionalProduct);
					optionalTourFee = optionalTourFee.add(additionalProduct.getPrice());
				}else if(additionalProduct.getType() == 11){
					optionalTourInTourlineList.add(additionalProduct);
					optionalTourInTourlineFee = optionalTourInTourlineFee.add(additionalProduct.getPrice());
				}else{
					additionalProductList.add(additionalProduct);
					additionalProductFee = additionalProductFee.add(additionalProduct.getPrice());
				}
			}
		}		
		List<OrderAttachment> orderAttachmentList = new ArrayList<OrderAttachment>();
		orderAttachmentList=orderAttachmentService.findByOrdersId(orderid);
		
		model.addAttribute("optionalTourList",optionalTourList);
		model.addAttribute("optionalTourInTourlineList", optionalTourInTourlineList);
		model.addAttribute("additionalProductList", additionalProductList);
		model.addAttribute("optionalTourFee",optionalTourFee);
		model.addAttribute("optionalTourInTourlineFee", optionalTourInTourlineFee);
		model.addAttribute("additionalProductFee", additionalProductFee);		
		model.addAttribute("orders", orders);
		model.addAttribute("frontCode",costFront.getCode());
		model.addAttribute("orderAttachmentList",orderAttachmentList);
	    	String active = "My order";
	 		model.addAttribute("active",active);
    	 return "/front/member/orders/user_orderdetail.ftl";
     }
     
      @RequestMapping(value="/uploadFile")
      @ResponseBody
      public Map<String,Object> uploadFile(@RequestParam("uploadFile") MultipartFile file,HttpServletRequest request,@RequestParam("orderId") String orderId,@RequestParam("remark") String remark){
    	  Member member = Tools.getMember(request); 
    	  String attachmentPath = request.getSession().getServletContext().getRealPath("/order-attachment");
    	  File folderPath = new File(attachmentPath + File.separator + member.getId() + File.separator + orderId);
    	  if(!folderPath.exists()){
    		  folderPath.mkdirs();
    	  }
    	  
    	  File attachment = new File(folderPath.getAbsolutePath() + File.separator + file.getOriginalFilename());
    	  try {
    		  file.transferTo(attachment);
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
    	  String url = "/order-attachment/" + member.getId() + "/" + orderId + "/" + file.getOriginalFilename();
    	  
    	  OrderAttachment orderAttachment = new OrderAttachment();
    	  orderAttachment.setId(Tools.getUUID());
    	  orderAttachment.setName(file.getOriginalFilename());
    	  orderAttachment.setUrl(url);
    	  orderAttachment.setRemark(remark);
    	  orderAttachment.setOrdersId(orderId);
    	  orderAttachment.setCreateTime(new Date());
    	  orderAttachmentService.save(orderAttachment);
    	  
    	  Map<String,Object> result = new HashMap<String,Object>();
    	  result.put("url", url);
    	  return result;
      } 
      
     
     /**
 	 * 签证订单详情页
 	 */
      @RequestMapping("/visaOrderdetail")
      public String visaOrderdetail(@RequestParam("orderNo") String orderNo, Model model,@Param("cValue")String cValue){
    	  Cost costFront = costService.findByCode(cValue);
  		  pageService.getNavigation(model, costFront.getId());
  		  model.addAttribute("frontCode",costFront.getCode());
     	 if(orderNo !=null && !"".equals(orderNo)){
     		 Orders vsiaOrders = orderService.getMemberOrderDetailByOrderNo(orderNo);
     		 model.addAttribute("vsiaOrders", vsiaOrders);
     	 }
     	 return "/front/member/orders/visa_orderdetail.ftl";
      }
      
    @ResponseBody
  	@RequestMapping(value = "/tourpassegerUpdate", method = RequestMethod.POST)
    public boolean tourpassegerUpdate(@Param("tourpassegerid")String tourpassegerid,@Param("gender")Integer gender,@Param("nationality")String nationality,
    		@Param("passportNo")String passportNo,@Param("passportNoExpiryDate")Date passportNoExpiryDate ){
    	    boolean update = false;
    	    TourPassenger tourpasseger = new TourPassenger();
    	                  tourpasseger.setId(tourpassegerid);
    	                  tourpasseger.setGender(gender);
    	                  tourpasseger.setNationality(nationality);
    	                  tourpasseger.setPassportNo(passportNo);
    	                  tourpasseger.setPassportNoExpiryDate(passportNoExpiryDate);
    	                  
    	    int upda = tourpassengerMapper.updateByPrimaryKeySelective(tourpasseger);
    	    if(upda==1){
    	    	update = true;
    	    }
    	    return update;
    }

     /**
   	 * 查看订单的客人信息
   	 */
        @RequestMapping("/customerinformation")
        public String customerinformation(@RequestParam("orderid") String orderid, Model model,@Param("cValue")String cValue){
        	Cost costFront = costService.findByCode(cValue);
    		pageService.getNavigation(model, costFront.getId());
    		model.addAttribute("frontCode",costFront.getCode());
       	 if(orderid !=null && !"".equals(orderid)){
       		 Orderdetail orderdetail = orderdetailMapper.findByOrderId(orderid);
       		 List<TourPassenger> tourpassengers = tourpassengerMapper.findByOrdersId(orderdetail.getId());
       		 model.addAttribute("tourpassengers", tourpassengers);
       	 }
       	 return "/front/member/orders/order_customer.ftl";
        }
	/**
	 * 邀请好友
	 * @throws IOException 
	 */
	@RequestMapping("/invite")
	public String invite(Model model,@Param("cValue")String cValue) throws IOException{
		Cost costFront = costService.findByCode(cValue);
		pageService.getNavigation(model, costFront.getId());
		model.addAttribute("frontCode",costFront.getCode());
		Member member = Tools.getMember(request); // 用户信息
		Memberinformation memberinformation = memberService.findInfoByMemberId(member.getId());
		model.addAttribute("member", member);
		model.addAttribute("info", memberinformation);
		String refid = Tools.getProperties().getProperty("REFID") +member.getId();
		model.addAttribute("refid", refid);
		return "/front/member/profile/user_invite.ftl";
	}
}

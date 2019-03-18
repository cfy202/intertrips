package com.wenjing.action.front;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.Mail;
import com.wenjing.dao.CouponsactivityMapper;
import com.wenjing.dao.CouponsduijiangMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Couponsactivity;
import com.wenjing.entity.Couponsduijiang;
import com.wenjing.entity.Couponse;
import com.wenjing.entity.Couponslevel;
import com.wenjing.entity.ExchangeCouponse;
import com.wenjing.service.CostService;
import com.wenjing.service.CouponseService;
import com.wenjing.service.CouponseactionService;
import com.wenjing.service.CouponseduijiangService;
import com.wenjing.service.CouponslevelService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-24 下午6:49:23 
 * 类说明 ：幻灯片管理 - Controller
 */
@Controller
@RequestMapping("/front/action")
public class CouponsactionController {
	@Resource
	private CouponsactivityMapper couponsactivityMapper;
	@Resource
	private CouponseactionService couponseactionService;
	@Resource
	private CouponsduijiangMapper couponsduijingMapper;
	@Resource
	private CouponseduijiangService coponseduijinagService;
	@Resource
	private CouponslevelService couponslevelService;
	@Resource
	private CostService costService;
	@Resource
	private CouponseService couponseService;
	@Resource
	private HttpServletRequest request;
		  /**
		   * 异步来完成抽奖动作
		   * @return
		   * @throws IOException
		   */
        @RequestMapping("/activity")
		public @ResponseBody  Couponslevel activity(@RequestParam("couponseid")String couponseid) throws IOException {
			Couponslevel couponsLevel = null;
			Couponse couponse = couponseService.findById(couponseid);
			String duijiangIp=Tools.getRemortIP(request);
			Couponsduijiang ip= couponsduijingMapper.selectIp(duijiangIp);
			Integer start = couponse.getStartTime();
			Integer end = couponse.getEndTime();
			Integer nowtime = Tools.getTimestemp(Tools.getTime());
			if(nowtime<start){
				 couponsLevel = new Couponslevel();
				couponsLevel.setName("start");
				return couponsLevel;
			}else{
				if(nowtime>end){
					 couponsLevel = new Couponslevel();
					couponsLevel.setName("end");
					return couponsLevel;
				}else{
					
					if(ip!=null){
						if(ip.getEmail()==null||ip.getEmailstatus()==0){
							Couponsactivity couponsactivity =couponsactivityMapper.findByCode(ip.getCode());
							couponsLevel = couponslevelService.findById(couponsactivity.getLevelid());
							couponsLevel.setId(ip.getId());
							return couponsLevel;
						}else{
							return couponsLevel;
						}
					}else{
						Couponsactivity activity = couponseactionService.getcouponsactivity(couponseid);
						Couponsactivity act = new Couponsactivity();
						act.setUsestatus(1);
						act.setId(activity.getId());
						couponseactionService.updateByPrimaryKeySelective(act);
						couponsLevel = couponslevelService.findById(activity.getLevelid());
//						if (couponsLevel.getName().equals("一等奖")) {
//							Couponstask.outPutcode(activity.getLevelId());
//							Couponstask.startRun();
//						}
						 
						String id = UUID.randomUUID().toString();
						Couponsduijiang duijiang = new Couponsduijiang();
						duijiang.setId(id);
						duijiang.setCode(activity.getCode());
						duijiang.setExpirationdate(couponse.getExpirationdate());
						duijiang.setIp(Tools.getRemortIP(request));
						duijiang.setUsestatus(0);
						duijiang.setEmailstatus(0);
						duijiang.setTime(Tools.getTime());
						duijiang.setCouponseid(activity.getCouponseid());
						duijiang.setLevelId(couponsLevel.getId());
						if(couponsLevel.getDistype().equals("$")){
							duijiang.setCouponsname("-"+couponsLevel.getDisvalue());
						}else{
							duijiang.setCouponsname(couponsLevel.getDisvalue()+""+couponsLevel.getDistype()+" 折扣券");
						}
						duijiang.setWinningtime(Tools.getDtime());
						coponseduijinagService.save(duijiang);
						couponsLevel.setId(id);
						couponsLevel.setName(activity.getCode());
						return couponsLevel;
					}
				}
			}
			

		}


//	 * 异步更新中奖名单
        @RequestMapping("/couponList.do")
		public @ResponseBody List<Couponsduijiang> couponList() {
				List<Couponsduijiang> couponslist = coponseduijinagService.findBymailstatus(1);
			return couponslist;
		}
		
		/**
		 * 异步给中奖用户发送邮件
		 * 
		 * @return
		 */
        @RequestMapping("/sendMial")
		public @ResponseBody String sendMail() {
			String name = request.getParameter("name");
			String email = request.getParameter("email").trim();
			String title = request.getParameter("title").trim();
			String id = request.getParameter("duijiangId");
			
			Couponsduijiang duijiang = couponsduijingMapper.selectByPrimaryKey(id);
			duijiang.setUsername(name);
			duijiang.setEmail(email);
			duijiang.setEmailstatus(1);
			duijiang.setTitle(title);
			coponseduijinagService.updateByPrimaryKeySelective(duijiang);
			
			String mesge ="";
			Mail gmail = new Mail();
    		gmail.setSendTo(new String[]{email});
    		gmail.setEmailSubjectTxt(duijiang.getCouponsname());
    		String content = FreemarkerUtils.getCouponsePage(request.getServletContext(), duijiang);
    		gmail.setEmailMsgTxt(content);
    		
			try {
				gmail.sendSSLMessage(); // 发送邮件
				mesge = "我们已经将优惠券信息发送至您的邮箱，页面将在5秒中之后自动跳转至网站首页, <a href=\"/\">手动跳转</a>.";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mesge;
		}
		
		//异步查询用户ip地址
		public String findByIp(){
			String duijiangIp=Tools.getRemortIP(request);
			Couponsduijiang ip=couponsduijingMapper.selectIp(duijiangIp);
			String mesge="";
			if(ip==null){
					mesge="Cracking lucky egg,get your Christmas award!!!";
			}
			return mesge;
		}
		/**
		 * 优惠券兑换
		 * 
		 * @return
		 * @throws IOException
		 */
		@RequestMapping(value = "/Exchange", method = RequestMethod.POST)
		public @ResponseBody
		ExchangeCouponse Exchange(@RequestParam("courrysign") String courrysign,@RequestParam("code")String code,@RequestParam("totalPrice")Integer totalPrice)throws IOException {
			Cost cost = costService.findByCode(courrysign);
			ExchangeCouponse exchange =couponseService.Exchange(cost.getId(),totalPrice, code);
			return exchange;

		}
}

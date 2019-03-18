package com.wenjing.action.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.wenjing.Mail;
import com.wenjing.entity.Activate;
import com.wenjing.entity.GetScore;
import com.wenjing.entity.Member;
import com.wenjing.entity.Memberinformation;
import com.wenjing.entity.ResultDTO;
import com.wenjing.service.MemberService;
import com.wenjing.service.RSAService;
import com.wenjing.service.ScoreService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-4-27 下午3:43:33
 *  类说明 ：会员注册 Controller
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Resource
	private MemberService memberService;
	@Resource
	private RSAService rsaService;
	@Resource
	private ScoreService scoreService;
	@Resource
	private HttpServletRequest request;

	
	private static final String PROJECT_NAME = "基本资料完善程度";
	
	private static final String NAME_PY = "name,birthday,nationality,passport,userAddr,userTel,image";
	
	private static final Integer DEFAULT_FAILURE_TIME = 20; //激活码默认失效时间20分钟
	
	public static final String TEMPLATE_URL = "/member/mailtemplate/activatemail.ftl"; // 获取激活邮件模板路径
	
	public static final String AU_TEMPLATE_URL = "/member/mailtemplate/auactivatemail.ftl"; // 获取激活邮件模板路径
	
	private static final Integer INVITE_SCORE = 20; //推荐人送积分20/人

	/**
	 * 注册页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String register(String refid, Model model) {
        if(refid !=null){
        	model.addAttribute("refid", refid);
        }
		return "/front/member/register/user_register.ftl";
	}

	/**
	 * 异步校验用户名是否存在
	 */
	@ResponseBody
	@RequestMapping(value = "/isExist", method = RequestMethod.POST)
	public boolean isExist(@RequestParam("account") String account, Model model) {
		boolean success = memberService.isExistUserName(account);
		return success;
	}
	
	/**
	 * 异步校验验证码是否输入正确
	 */
	@ResponseBody
	@RequestMapping(value = "/isCaptcha")
	public boolean isCaptcha(@RequestParam("captcha") String captcha, Model model) {
		Boolean flag = false;
		if(captcha != null){
        	captcha = captcha.toLowerCase();
		}
		String co = (String) request.getSession().getAttribute("number"); // 获取session中验证码
		if(co != null && co.equals(captcha)){
			flag = true;
		}
		return flag; 
	}

	/**
	 * 注册提交
	 * @throws IOException 
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	ResultDTO submit(String account, HttpServletResponse response, HttpSession session) throws IOException {
		String password = rsaService.decryptParameter("enPassword", request); // 解密参数
		rsaService.removePrivateKey(request);// 移除私钥
		ResultDTO result = new ResultDTO();
		Member member = new Member();
		member.setId(Tools.getUUID());
		member.setCreatetime(Tools.getHHtime()); // 注册时间
		member.setLasttime(Tools.getHHtime()); // 最后登录时间
//		member.setUserstatus((byte) 0); // 用户状态
		member.setUserstatus((byte) 1); // 用户状态
		member.setAccount(account);
		member.setPassword(DigestUtils.md5Hex(password));
		member.setIp(Tools.getRemortIP(request));
		boolean flag = memberService.isExistUserName(account);
		if (flag) {
			result.setSuccess(false);
			result.setMessage("Your email has been registered.");
		} else {
			int success = memberService.insert(member); // 添加会员
			if (success > 0) {
				Memberinformation memberinformation = new Memberinformation();
				memberinformation.setId(Tools.getUUID());
				memberinformation.setEmail(member.getAccount());
				memberinformation.setMemberid(member.getId());
				memberinformation.setUserlevel(1); // 用户等级1
				memberinformation.setScore(100); // 积分
				String inviteId = request.getParameter("refid"); //邀请人id
				if(inviteId !=null && !"".equals(inviteId)){
					Member member2 = memberService.findById(inviteId);
					if(member2!=null){
						memberService.updateScoreByMemberId(INVITE_SCORE, inviteId);
						memberinformation.setInviteId(inviteId.trim());
					}
				}
				memberService.insertInfo(memberinformation); // 插入会员信息表
				GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), PROJECT_NAME, NAME_PY, null, 0, 0, member.getId());
				scoreService.insert(getScore); //插入完善资料送积分数据
//				int failureTime = Tools.getDtimestemp(Tools.dateminit(Tools.getDtime(), DEFAULT_FAILURE_TIME)); // 失效时间，默认二十分钟
//				String secretKey = UUID.randomUUID().toString(); // 密钥
//				String key = member.getAccount() + "$" + failureTime + "$" + secretKey;
//				String authcode = Tools.MD5(key); // 激活码
				// 添加注册码到注册表
//				Activate activate = new Activate();
//				activate.setId(Tools.getUUID());
//				activate.setMemberid(member.getId());
//				activate.setAuthcode(authcode);
//				activate.setFailuretime(failureTime);
//				int successActivate = memberService.insertActivate(activate);
//				if (successActivate > 0) {
					// 发送邮件激活
//					Mail gmail = new Mail();
//					List<String> list = new ArrayList<String>();
//					list.add(account);
//					String[] array = list.toArray(new String[list.size()]);
//					gmail.setSendTo(array);
//					String activateurl;
//					activateurl = Tools.getProperties().getProperty("ACTIVATE_URL") + authcode;// 激活地址+激活码
//					String emailMsgTxt = FreemarkerUtils.getActivateMail(request.getSession().getServletContext(), account, activateurl, TEMPLATE_URL); // 获取激活邮件内容
//					gmail.setEmailMsgTxt(emailMsgTxt);
//					gmail.setEmailSubjectTxt("Welcome to Intertrips");
//					try {
//						gmail.sendSSLMessage(); // 发送邮件
//					} catch (MessagingException e) {
//						e.printStackTrace();
//					}
				    member = memberService.findByAccount(account);
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
			        	memberService.updateScoreByMemberId(LoginController.SCORE, member.getId());//
			        }  
					memberService.updateLasttime(Tools.getHHtime(), member.getId());// 修改最后登录时间为当前时间
					WebUtils.addCookie(request, response, Member.MEMBER_COOKIE_NAME, JSON.toJSONString(member),40 * 60);
					WebUtils.addCookie(request, response, "JSESSIONID", request.getSession().getId(),40 * 60);
//				} else {
//					result.setSuccess(false);
//					result.setMessage("Registration Failed.");
//				}
			} else {
				result.setSuccess(false);
				result.setMessage("Registration Failed.");
			}
		}
		return result;
	}

	/**
	 * 用户激活
	 * 
	 * @param email
	 * @param authcode
	 * @param model
	 * @return
	 */
	@RequestMapping("/activate")
	public String activate(@RequestParam("authcode") String authcode, HttpServletResponse response, final RedirectAttributes rAttributes) {
		String nowTime = Tools.getDtime(); // 获取当前系统时间
		memberService.deleteFailureAuthcode(Tools.getDtimestemp(Tools.getDtime()));// 删除激活表中小于当前时间的激活码
		Activate activate = memberService.findByAuthcode(authcode);
		
		if (activate != null) { // 验证激活码是否存在
			Member member = memberService.findById(activate.getMemberid());
			// 验证用户激活状态
			if (member.getUserstatus() == 0) {
				// 未激活
				int islate = Tools.compare_date2(Tools.getDtime(activate.getFailuretime()),nowTime);
				// 验证激活码是否过期
				if (islate == 1) {
						// 激活成功, 更新用户状态为1,为已激活
						int activatesuccess = memberService.updateStatus(member.getId(), 1);
						if (activatesuccess > 0) {
							memberService.deleteActivate(activate.getId()); // 激活成功删除此激活码
							request.getSession().setAttribute("member", member);
							WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getAccount()); //添加cookie
							rAttributes.addFlashAttribute("message", "The account is successfully activated, please log in.");
							return "redirect:/login.htm";
						} else {
							rAttributes.addFlashAttribute("message", "The account is already verified, please log in directly");
							return "redirect:/register/user_activate.htm";
						}
				} else {
					// 激活码过期
					rAttributes.addFlashAttribute("message", "Verification link has expired, please register again.");
					return "redirect:/register/user_activate.htm";
				}
			} else {
				// 已激活
				rAttributes.addFlashAttribute("message", "The account is already verified, please log in directly.");
				return "redirect:/login.htm";
			}
		} else {
			rAttributes.addFlashAttribute("message", "Verification link has expired, please register again");
			return "redirect:/register/user_activate.htm";
		}
	}
	
	/**
	 * 进入用户填写邮箱激活页面
	 */
	@RequestMapping("/user_activate")
	public String userActivate(@RequestParam(value="account", required=false) String account, Model model){
		if(account !=null && !"".equals(account)){
			model.addAttribute("account", account);
		}
		return "/front/member/register/user_activate.ftl";
	}
	
	
	/**
	 * @Title againActivate
	 * @Description 同步发送激活码
	 * @Author Bowden
	 * @CreateDate 2015-7-23 下午4:45:39
	 */
	@RequestMapping("/sync_activate")
	public String syncAgainActivate(@RequestParam(value="account", required=false)String account, Model model, final RedirectAttributes rAttributes) throws IOException{
		if(account==null || "".equals(account)){
			return "redirect:/register/user_activate.htm";
		}
		Member member = memberService.findByAccount(account);
		int ststus = member.getUserstatus();
		if(ststus == 1){
			rAttributes.addFlashAttribute("message", "Verification link has expired, please register again.");
			return "redirect:/login.htm";
		}
		ResultDTO result = new ResultDTO();
		sendActivate(member, result);
		model.addAttribute("account", account);
		return "/front/member/register/send_success.ftl";
	}
	
	
	/**
	 * @Title againActivate
	 * @Description 异步发送激活码
	 * @Author Bowden
	 * @CreateDate 2015-7-23 下午4:45:57
	 */
	@RequestMapping("/again_activate")
	@ResponseBody
	public ResultDTO againActivate(String account) throws IOException{
		Member member = memberService.findByAccount(account);
		ResultDTO result = new ResultDTO();
		int status = member.getUserstatus();
		if(status == 1){
			result.setSuccess(false);
			result.setMessage("Account is already verified, please log in directly.");
			return result;
		}
		result = this.sendActivate(member, result);
		return result;
	}
	
	/**
	 * @throws IOException 
	 * @Title sendActivate
	 * @Description 发送激活码
	 * @Author Bowden
	 * @CreateDate 2015-7-23 下午4:40:49
	 */
	public ResultDTO sendActivate(Member member, ResultDTO result) throws IOException{
		int failureTime = Tools.getDtimestemp(Tools.dateminit(Tools.getDtime(), DEFAULT_FAILURE_TIME)); // 失效时间，默认二十分钟
		String secretKey = UUID.randomUUID().toString(); // 密钥
		String key = member.getAccount() + "$" + failureTime + "$" + secretKey;
		String authcode = Tools.MD5(key); // 激活码
		// 添加注册码到注册表
		Activate activate1 = new Activate();
		activate1.setId(Tools.getUUID());
		activate1.setMemberid(member.getId());
		activate1.setAuthcode(authcode);
		activate1.setFailuretime(failureTime);
		int successActivate1 = memberService.insertActivate(activate1);
		if (successActivate1 > 0) {
			// 发送邮件激活
			Mail gmail = new Mail();
			List<String> list = new ArrayList<String>();
			list.add(member.getAccount());
			String[] array = list.toArray(new String[list.size()]);
			gmail.setSendTo(array);
//			String activateurl = Tools.getProperties().getProperty("ACTIVATE_URL") + authcode; // 激活地址+激活码
//			String emailMsgTxt = FreemarkerUtils.getActivateMail(request.getSession().getServletContext(), member.getAccount(), activateurl, TEMPLATE_URL); // 获取激活邮件内容
//			gmail.setEmailMsgTxt(emailMsgTxt);
//			gmail.setEmailSubjectTxt("Welcome to Intertrips");
//			try {
//				gmail.sendSSLMessage(); // 发送邮件
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
			result.setSuccess(true);
			result.setMessage(member.getAccount());
		}
		return result;
	}
}

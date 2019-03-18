package com.wenjing.action.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.Mail;
import com.wenjing.entity.Activate;
import com.wenjing.entity.Member;
import com.wenjing.entity.Memberinformation;
import com.wenjing.entity.ResultDTO;
import com.wenjing.service.MemberService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-25 上午9:51:13
 *  类说明 ：用户密码管理
 */

@Controller
@RequestMapping("/password")
public class PasswordController {
	@Resource
	private MemberService memberService;
	@Resource
	private HttpServletRequest request;
	
	private static final String TEMPLATE_URL = "/member/mailtemplate/reset_sendmail.ftl"; // 获取重置密码模板路径
	
	private static final String SUCCESS_TEMPLATE_URL = "/member/mailtemplate/success_mail.ftl"; // 获取密码修改成功模板路径

	private static final String RESET_EMAIL_SUBJECTTXT = "intertrips.com - Password reset intertrips";
	
	private static final String SUCCESS_EMAIL_SUBJECTTXT = "intertrips.com - Password is changed!";
	
	/**
	 * 进入密保找回密码页面
	 */
	@RequestMapping("/question")
	public String question() {

		return "/front/member/password/question.ftl";
	}

	/**
	 * 密保找回密码
	 */
	@RequestMapping("/reset_question")
	public String passwordQuestion(@RequestParam("account") String account,
			Model model) {
		Member member = memberService.findByAccount(account);
		if (member != null) {
			Memberinformation info = memberService.findInfoByMemberId(member.getId());
			model.addAttribute("info", info);
		}
		return "/front/member/password/reset_question.ftl";
	}

	/**
	 * 密保找回提交
	 */
	@RequestMapping("/question_submit")
	public String questionSubmit(@RequestParam("answer") String answer,
			@RequestParam("id") String id,
			@RequestParam("password") String password, Model model) {
		ResultDTO result = new ResultDTO();
		Memberinformation info = memberService.findByInfoid(id);
		if (answer != null && !"".equals(answer)) {
			if (answer.equals(info.getAnswer())) { // 密保答案正确
				memberService.updatePassword(DigestUtils.md5Hex(password), info.getMemberid()); // 修改用户密码
				result.setSuccess(true);
				result.setMessage("密码修改成功");
			} else { // 密保答案不正确
				result.setSuccess(false);
				result.setMessage("密保答案错误");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("密保答案错误");
		}
		model.addAttribute("result", result);
		return "/front/member/register/success.ftl";
	}

	/**
	 * 进入邮件找回页面
	 */
	@RequestMapping("/reset_email")
	public String email() {

		return "/front/member/password/retrieve_password.ftl";
	}

	/**
	 * 邮件找回密码
	 * @throws IOException 
	 */
	@RequestMapping("/send_resetemail")
	@ResponseBody
	public ResultDTO resetEmail(@Param("account") String account) throws IOException {
		ResultDTO result = new ResultDTO();
		Member member = memberService.findByAccount(account);
		if(member.getUserstatus()==0){
			result.setSuccess(false);
			result.setMessage("账号未<a class=\"again-activate\" href=\"javascript:;\" onclick=\"againActivate();\">激活</a>");
			return result;
		}
		int failureTime = Tools.getDtimestemp(Tools.dateminit(Tools.getDtime(), 20)); // 失效时间，默认二十分钟
		String secretKey = UUID.randomUUID().toString(); // 密钥
		String key = member.getAccount() + "$" + failureTime + "$" + secretKey;
		String digitalSignature = Tools.MD5(key); // md5加密
		// 添加注册码到注册表
		Activate activate = new Activate();
		activate.setId(Tools.getUUID());
		activate.setMemberid(member.getId());
		activate.setAuthcode(digitalSignature);
		activate.setFailuretime(failureTime);
		int successActivate = memberService.insertActivate(activate);
		if (successActivate > 0) {
			String activateurl = Tools.getProperties().getProperty("RESET_URL") + digitalSignature; // 激活地址+激活码
			sendMail(account, activateurl, TEMPLATE_URL, RESET_EMAIL_SUBJECTTXT);
			result.setSuccess(true);
			result.setMessage("Email has been sent, please click on the mailbox verify the password.");
		}else {
			result.setSuccess(false);
			result.setMessage("Mail delivery failure, please send it again.");
		}
		return result;
	}

	/**
	 * 邮件找回密码点击链接
	 */
	@RequestMapping("/reset_password")
	public String resetPassword(@RequestParam("sid") String sid, Model model, final RedirectAttributes rAttributes) {
		String nowTime = Tools.getDtime(); // 获取当前系统时间
		memberService.deleteFailureAuthcode(Tools.getDtimestemp(Tools.getDtime()));// 删除激活表中小于当前时间的激活码
		if (sid != null && !"".equals(sid)) {
			Activate activate = memberService.findByAuthcode(sid);
			if (activate != null) {// 验证激活码是否存在
				int islate = Tools.compare_date2(Tools.getDtime(activate.getFailuretime()),nowTime);
				// 验证激活码是否过期
				if (islate == 1) {
					Member member = memberService.findById(activate.getMemberid());
					model.addAttribute("member", member);
					model.addAttribute("message", "Password will be reset, please set your new password.");
					//memberService.deleteActivate(activate.getId()); // 验证成功删除此激活码
					return "/front/member/password/retrieve_pass_change.ftl";
				}else {
					rAttributes.addFlashAttribute("message", "Retrieve password link failure, please to regenerate.");
					return "redirect:/password/reset_email.htm";
				}
			} else {
				rAttributes.addFlashAttribute("message", "Retrieve password link failure, please to regenerate.");
				return "redirect:/password/reset_email.htm";
			}
		} else {
			// 验证码失效
			rAttributes.addFlashAttribute("message", "Retrieve password link failure, please to regenerate.");
			return "redirect:/password/reset_email.htm";
		}
	}

	/**
	 * 异步密码重置提交
	 */
	@RequestMapping("/reset_submit")
	@ResponseBody
	public ResultDTO resetSubmit(@RequestParam("password") String password, @RequestParam("id") String id, Model model) {
		ResultDTO result = new ResultDTO();
		if (password != null && !"".equals(password) && id != null && !"".equals(id)) {
			int success = memberService.updatePassword(DigestUtils.md5Hex(password), id); // 修改用户密码
			if (success > 0) {
				memberService.deleteActivateByMmeberId(id); // 修改成功删除此激活码
				result.setSuccess(true);
				result.setMessage("Password is changed ！");
				Member member = memberService.findById(id);
				sendMail(member.getAccount(), Tools.getDtime(), SUCCESS_TEMPLATE_URL, SUCCESS_EMAIL_SUBJECTTXT);
			} else {
				result.setSuccess(false);
				result.setMessage("Change the password failure!");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("Change the password failed!");
		}
		return result;
	}
	
	/**
	 * @Title sendMail
	 * @Description 密码修改成功后发送邮件
	 * @Author Bowden
	 * @CreateDate 2015-8-14 上午10:48:12
	 */
	private void sendMail(String account, String message, String templateurl, String emailSbujectTxt) {
		Mail gmail = new Mail();
		List<String> list = new ArrayList<String>();
		list.add(account);
		String[] array = list.toArray(new String[list.size()]);
		gmail.setSendTo(array);
		String emailMsgTxt = FreemarkerUtils.getActivateMail(request.getSession().getServletContext(), account, message, templateurl); // 获取激活邮件内容
		gmail.setEmailMsgTxt(emailMsgTxt);
		gmail.setEmailSubjectTxt(emailSbujectTxt);
		try {
			gmail.sendSSLMessage(); // 发送邮件
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

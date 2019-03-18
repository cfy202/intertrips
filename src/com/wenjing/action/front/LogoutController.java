package com.wenjing.action.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.entity.Member;
import com.wenjing.util.WebUtils;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-5-27 下午12:00:01
 * 类说明  : Controller - 会员注销
 */
@Controller
public class LogoutController {
	/** "重定向URL"参数名称 */
	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";
	
	private String urlEscapingCharset = "UTF-8";
	
	/** 默认登录URL */
	private static final String DEFAULT_LOGIN_URL = "/login.htm";

	/**
	 * 注销
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		request.getSession().removeAttribute("islogin");
		request.getSession().removeAttribute("member");
		WebUtils.removeCookie(request, response, Member.MEMBER_COOKIE_NAME);
		return "success";
	}
}

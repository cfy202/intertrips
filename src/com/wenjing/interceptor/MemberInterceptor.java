/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.wenjing.interceptor;


import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wenjing.entity.Member;
import com.wenjing.service.CostService;
import com.wenjing.service.MemberService;
import com.wenjing.util.IPUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * Interceptor - 会员权限
 *
 * @author bowden
 * 
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {

	/** 重定向视图名称前缀 */
	private static final String REDIRECT_VIEW_NAME_PREFIX = "redirect:";

	/** "重定向URL"参数名称 */
	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

	/** "会员"属性名称 */
	private static final String MEMBER_ATTRIBUTE_NAME = "member";

	/** 默认登录URL */
	private static final String DEFAULT_LOGIN_URL = "/login.htm";
	
	/** 中国销售中心 */
	private static final String CHINA = "中国";
	
	/** 中国销售中心币种CODE */
	private static final String CHINA_CODE = "CNY";
	
	/** 美国销售中心 */
	private static final String AMERICA = "美国";
	
	/** 美国销售中心币种CODE */
	private static final String AMERICA_CODE = "USD";
	
	/** 加拿大销售中心 */
	private static final String CANADA = "加拿大";
	
	/** 加拿大销售中心币种CODE */
	private static final String CANADA_CODE = "CAD";
	
	/** 澳大利亚销售中心 */
	private static final String AUSTRALIAN = "澳大利亚";
	
	/** 澳大利亚销售中心币种CODE */
	private static final String AUSTRALIAN_CODE = "AUD";
	
	/** 欧洲销售中心币种CODE */
	private static final String AOZHOU_CODE = "EUR";

	/** 登录URL */
	private String loginUrl = DEFAULT_LOGIN_URL;

	private String urlEscapingCharset = "UTF-8";
	
	@Resource
	private MemberService memberService;
	@Resource
	private CostService costService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		String costnumber = "";
		String cc = WebUtils.getCookie(request, "cc");
		if(cc != null && !"".equals(cc)){
			costnumber = costService.findIdByCode(cc);
			request.getSession().setAttribute("costnumber", costnumber);
		}else {
			costnumber = (String) session.getAttribute("costnumber");
			if(costnumber==null || "".equals(costnumber) || cc == null || "".equals(cc)){
				String ip = Tools.getRemortIP(request);// 获取IP地址
				String path = request.getSession().getServletContext().getRealPath("/");
				IPUtils.load(path + "/ip/17monipdb.dat");
				String[] location = IPUtils.find(ip);
				if (location != null && location.length > 0) {
					if ((location[0].contains(CHINA) || location[0].contains("本机地址") || location[0].contains("局域网"))) {
						costnumber = costService.findIdByCode(CHINA_CODE);
						WebUtils.addCookie(request, response, "cc", CHINA_CODE);
					} else if (location[0].contains(AMERICA)) {
						costnumber = costService.findIdByCode(AMERICA_CODE);
						WebUtils.addCookie(request, response, "cc", AMERICA_CODE);
					} else if (location[0].contains(CANADA)) {
						costnumber = costService.findIdByCode(CANADA_CODE);
						WebUtils.addCookie(request, response, "cc", CANADA_CODE);
					} else if (location[0].contains(AUSTRALIAN)) {
						costnumber = costService.findIdByCode(AUSTRALIAN_CODE);
						WebUtils.addCookie(request, response, "cc", AUSTRALIAN_CODE);
					}else if (Tools.isBelongEurope(location[0])) {
						costnumber = costService.findIdByCode(AOZHOU_CODE);
						WebUtils.addCookie(request, response, "cc", AOZHOU_CODE);
					} else {
						costnumber = costService.findIdByCode(AMERICA_CODE);
						WebUtils.addCookie(request, response, "cc", AMERICA_CODE);
					}
				} else {
					costnumber = costService.findIdByCode(CHINA_CODE);
					WebUtils.addCookie(request, response, "cc", CHINA_CODE);
				}
				request.getSession().setAttribute("costnumber", costnumber);
			}
		}
		if (member != null) {
			return true;
		} else {
			String requestType = request.getHeader("X-Requested-With");//为 null，则为传统同步请求,为 XMLHttpRequest，则为 Ajax 异步请求。
			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
				response.addHeader("loginStatus", "accessDenied");
				response.sendError(HttpServletResponse.SC_FORBIDDEN);//用来向客户端发送错误信息,状态码是403，表示服务器明白客户的请求，但拒绝响应。
				return false;
			} else {
				if (request.getMethod().equalsIgnoreCase("GET")) {  //判断是否为get请求
					String redirectUrl = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
					redirectUrl = redirectUrl.replaceAll(".do", ".htm"); //伪静态处理
					response.sendRedirect(request.getContextPath() + loginUrl + "?" + REDIRECT_URL_PARAMETER_NAME + "=" + URLEncoder.encode(redirectUrl, urlEscapingCharset));
				} else {
					response.sendRedirect(request.getContextPath() + loginUrl);
				}
				return false;
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			String viewName = modelAndView.getViewName();
			if (!StringUtils.startsWith(viewName, REDIRECT_VIEW_NAME_PREFIX)) {
				modelAndView.addObject(MEMBER_ATTRIBUTE_NAME, Tools.getMember(request));
			}
		}
	}

	/**
	 * 获取登录URL
	 * 
	 * @return 登录URL
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * 设置登录URL
	 * 
	 * @param loginUrl
	 *            登录URL
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
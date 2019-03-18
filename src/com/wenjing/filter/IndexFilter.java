/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.wenjing.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wenjing.dao.CostMapper;
import com.wenjing.dao.PageMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Page;
import com.wenjing.entity.Promotion;
import com.wenjing.service.PageService;
import com.wenjing.service.PromotionService;
import com.wenjing.util.IPUtils;
import com.wenjing.util.OrdersNo;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * Filter - 编码格式转换
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public class IndexFilter extends OncePerRequestFilter {

	/**
	 * 原编码格式
	 */
	private String fromEncoding = "ISO-8859-1";

	/**
	 * 目标编码格式
	 */
	private String toEncoding = "UTF-8";

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		OrdersNo ordersno = new OrdersNo();
		int status = 0;
		int orderNumber = 0;
		Object objs = request.getSession().getAttribute("upstatus");
		if(objs!=null){
			status = Integer.parseInt(objs.toString());
		}else{
		  status = ordersno.getStatus();
		}
		
		CostMapper costMapper = (CostMapper) ContextLoader.getCurrentWebApplicationContext().getBean("costMapper");
		PageService pageService =(PageService) ContextLoader.getCurrentWebApplicationContext().getBean("pageService");
		if(status==0){
			List<Page> pages = pageService.findNotByCostnumber();
			for (Page page : pages) {
				try {
					pageService.create(page.getCostnumber(), page, request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			status = 1;
			ordersno.setStuts(status);
			request.getSession().setAttribute("upstatus", status);
		}
		HttpSession session = request.getSession();
		String costnumber = "";
		String cc = WebUtils.getCookie(request, "cc");
		if(cc !=null && !"".equals(cc)){
			costnumber = costMapper.findIdByCode(cc);
			request.getSession().setAttribute("costnumber", costnumber);
			if (cc.equals("AUD")) {
				 request.getRequestDispatcher("au/index.html").forward(request,
				 response);
				 } 
//				 else if(cc.equals("USD")) {
//				 request.getRequestDispatcher("us/index.html").forward(request,
//				 response);
//				 }
//				 else if(cc.equals("AUD")) {
//					 request.getRequestDispatcher("au/index.html").forward(request,
//					 response);
//					 }	
//				 else if(cc.equals("CAD")) {
//					 request.getRequestDispatcher("ca/index.html").forward(request,
//					 response);
//					 }
//				 
			else {
					 request.getRequestDispatcher("us/index.html").forward(request,
					 response);
					 }
		}else {
			costnumber = (String) session.getAttribute("costnumber");
			if (costnumber != null && !"".equals(costnumber)) {
				Cost cost = costMapper.selectByPrimaryKey(costnumber);
				 if (cost.getCode().equals("AUD")) {
					 request.getRequestDispatcher("au/index.html").forward(request,
					 response);
					 } 
//					 else if(cost.getCode().equals("USD")) {
//					 request.getRequestDispatcher("us/index.html").forward(request,
//					 response);
//					 }
//					 else if(cost.getCode().equals("AUD")) {
//						 request.getRequestDispatcher("au/index.html").forward(request,
//						 response);
//						 }	
//					 else if(cost.getCode().equals("CAD")) {
//						 request.getRequestDispatcher("ca/index.html").forward(request,
//						 response);
//						 }
					 else {
						 request.getRequestDispatcher("us/index.html").forward(request,
						 response);
						 }
				
			} else {
				String ip = Tools.getRemortIP(request);// 获取IP地址
				String path = request.getSession().getServletContext().getRealPath("/");
				IPUtils.load(path + "/ip/17monipdb.dat");
				String[] location = IPUtils.find(ip);
				if (location != null && location.length > 0) {
					if ((location[0].contains("澳大利亚") || location[0].contains("本机地址") || location[0].contains("局域网"))) {
						costnumber = costMapper.findIdByCode("AUD");
						WebUtils.addCookie(request, response, "cc", "AUD");
						 request.getRequestDispatcher("cn/index.html").forward(request,
								 response);
					} 
//					else if (location[0].contains("美国")) {
//						costnumber = costMapper.findIdByCode("USD");
//						WebUtils.addCookie(request, response, "cc", "USD");
//						 request.getRequestDispatcher("us/index.html").forward(request,
//								 response);
//					} else if (location[0].contains("加拿大")) {
//						costnumber = costMapper.findIdByCode("CAD");
//						WebUtils.addCookie(request, response, "cc", "CAD");
//						 request.getRequestDispatcher("ca/index.html").forward(request,
//								 response);
//					} else if (location[0].contains("澳大利亚")) {
//						costnumber = costMapper.findIdByCode("AUD");
//						WebUtils.addCookie(request, response, "cc", "AUD");
//						 request.getRequestDispatcher("au/index.html").forward(request,
//								 response);
//					}else if (Tools.isBelongEurope(location[0])) {
//						costnumber = costMapper.findIdByCode("EUR");
//						WebUtils.addCookie(request, response, "cc", "EUR");
//						 request.getRequestDispatcher("eu/index.html").forward(request,
//								 response);
//					} 
					else {
						costnumber = costMapper.findIdByCode("USD");
						WebUtils.addCookie(request, response, "cc", "USD");
						request.getRequestDispatcher("us/index.html").forward(request,
								 response);
					}
				} else {
					costnumber = costMapper.findIdByCode("USD");
					WebUtils.addCookie(request, response, "cc", "USD");
					request.getRequestDispatcher("us/index.html").forward(request,
							 response);
				}
				
				request.getSession().setAttribute("costnumber", costnumber);

			}
		}
	}

	/**
	 * 获取原编码格式
	 * 
	 * @return 原编码格式
	 */
	public String getFromEncoding() {
		return fromEncoding;
	}

	/**
	 * 设置原编码格式
	 * 
	 * @param fromEncoding
	 *            原编码格式
	 */
	public void setFromEncoding(String fromEncoding) {
		this.fromEncoding = fromEncoding;
	}

	/**
	 * 获取目标编码格式
	 * 
	 * @return 目标编码格式
	 */
	public String getToEncoding() {
		return toEncoding;
	}

	/**
	 * 设置目标编码格式
	 * 
	 * @param toEncoding
	 *            目标编码格式
	 */
	public void setToEncoding(String toEncoding) {
		this.toEncoding = toEncoding;
	}

}
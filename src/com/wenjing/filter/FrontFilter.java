/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.wenjing.filter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wenjing.dao.CostMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Promotion;
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
public class FrontFilter extends OncePerRequestFilter {

	/**
	 * 原编码格式
	 */
	private String fromEncoding = "ISO-8859-1";

	/**
	 * 目标编码格式
	 */
	private String toEncoding = "UTF-8";
	
	private String regx1 = "^/([a-z,-]+\\d{2,8})\\.htm";
	
	private String regx2 ="^/([a-z,-]+)/([a-z,-]+)(\\d{2,8})\\.htm";
	
	private String regx3 = "";

	
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
		
		PromotionService promotionService =(PromotionService) ContextLoader.getCurrentWebApplicationContext().getBean("promotionService");
		if(status==0){
			List<Promotion> promotions = promotionService.findAllNotByCostNumber();
			for (Promotion promotion : promotions) {
				promotionService.createTourlinePromotion(promotion,request);
			}
			status = 1;
			ordersno.setStuts(status);
			request.getSession().setAttribute("upstatus", status);
		}
		
		request.setCharacterEncoding(toEncoding);
		String url = request.getRequestURI();
		if(url.indexOf("intertrips")!=-1){
			url = url.replace("/intertrips/", "/");
		}
		
		CostMapper costMapper = (CostMapper) ContextLoader.getCurrentWebApplicationContext().getBean("costMapper");
		HttpSession session = request.getSession();
		Pattern pattern1 = Pattern.compile(regx1);   
		Pattern pattern2 = Pattern.compile(regx2); 
		Matcher matcher1 = pattern1.matcher(url);   
		Matcher matcher2 = pattern2.matcher(url);
		boolean a = matcher1.matches(); //当条件满足时，将返回true，否则返回false   
		boolean b = matcher2.matches(); 
		System.out.println(b);
		System.out.println(a);
		String costnumber = "";
		String cc = WebUtils.getCookie(request, "cc");
		if(cc != null && !"".equals(cc)){
			costnumber = costMapper.findIdByCode(cc);
			request.getSession().setAttribute("costnumber", costnumber);
			if (cc.equals("CNY")&&(a||b)) {
				 request.getRequestDispatcher("/CNY"+url).forward(request,
				 response);
				 } 
				 else if(cc.equals("USD")&&(a||b)) {
				 request.getRequestDispatcher("/USD"+url).forward(request,
				 response);
				 }
				 else if(cc.equals("AUD")&&(a||b)) {
					 request.getRequestDispatcher("/AUD"+url).forward(request,
					 response);
					 }	
				 else if(cc.equals("CAD")&&(a||b)) {
					 request.getRequestDispatcher("/CAD"+url).forward(request,
					 response);
					 }
				 else if(cc.equals("EUR")&&(a||b)) {
					 request.getRequestDispatcher("/EUR"+url).forward(request,
					 response);
					 }
		}else {
		    costnumber = (String) session.getAttribute("costnumber");
			if (costnumber != null && !"".equals(costnumber) && cc != null && !"".equals(cc)) {
				Cost cost = costMapper.selectByPrimaryKey(costnumber);
				if (cost.getCode().equals("CNY")&&(a||b)) {
					 request.getRequestDispatcher("/CNY"+url).forward(request,
					 response);
					 } 
					 else if(cost.getCode().equals("USD")&&(a||b)) {
					 request.getRequestDispatcher("/USD"+url).forward(request,
					 response);
					 }
					 else if(cost.getCode().equals("AUD")&&(a||b)) {
						 request.getRequestDispatcher("/AUD"+url).forward(request,
						 response);
						 }	
					 else if(cost.getCode().equals("CAD")&&(a||b)) {
						 request.getRequestDispatcher("/CAD"+url).forward(request,
						 response);
						 }
					 else if(cost.getCode().equals("EUR")&&(a||b)) {
						 request.getRequestDispatcher("/EUR"+url).forward(request,
						 response);
						 }
				 
			} else {
				String ip = Tools.getRemortIP(request);// 获取IP地址
				String path = request.getSession().getServletContext().getRealPath("/");
				IPUtils.load(path + "/ip/17monipdb.dat");
				String[] location = IPUtils.find(ip);

				if (location != null && location.length > 0) {
					if ((location[0].contains("中国") || location[0].contains("本机地址") || location[0].contains("局域网"))) {
						costnumber = costMapper.findIdByCode("CNY");
						WebUtils.addCookie(request, response, "cc", "CNY");
						if(a||b){
							request.getRequestDispatcher("/CNY"+url).forward(request,response);
						}
						 
					} else if (location[0].contains("美国")) {
						costnumber = costMapper.findIdByCode("USD");
						WebUtils.addCookie(request, response, "cc", "USD");
						if(a||b){
							request.getRequestDispatcher("/USD"+url).forward(request,response);
						}
						 
					} else if (location[0].contains("加拿大")) {
						costnumber = costMapper.findIdByCode("CAD");
						WebUtils.addCookie(request, response, "cc", "CAD");
						if(a||b){
							request.getRequestDispatcher("/CAD"+url).forward(request,response);
						}
						
					} else if (location[0].contains("澳大利亚")) {
						costnumber = costMapper.findIdByCode("AUD");
						WebUtils.addCookie(request, response, "cc", "AUD");
						if(a||b){
							request.getRequestDispatcher("/AUD"+url).forward(request,response);
						}
						
					}else if (Tools.isBelongEurope(location[0])) {
						costnumber = costMapper.findIdByCode("EUR");
						WebUtils.addCookie(request, response, "cc", "EUR");
						if(a||b){
							request.getRequestDispatcher("/EUR"+url).forward(request,response);
						}
						
					} else {
						costnumber = costMapper.findIdByCode("USD");
						WebUtils.addCookie(request, response, "cc", "USD");
						if(a||b){
							request.getRequestDispatcher("/USD"+url).forward(request,response);
						}
					}
				} else {
					costnumber = costMapper.findIdByCode("CNY");
					WebUtils.addCookie(request, response, "cc", "CNY");
					if(a||b){
						request.getRequestDispatcher("/CNY"+url).forward(request,response);
					}
					
				}
				request.getSession().setAttribute("costnumber", costnumber);
//				request.getSession().setAttribute("costnumber", "d8fe5ef1de7747ab86588f9880f1aa77");
				// request.getRequestDispatcher("front/index/list.do").forward(request,
				// response);

			}
		}
		if(!(a||b)){
			filterChain.doFilter(request, response);
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
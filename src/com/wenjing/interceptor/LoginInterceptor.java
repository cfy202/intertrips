package com.wenjing.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wenjing.entity.Admin;
import com.wenjing.entity.Tree;

/**
 * 登陆拦截器.
 * 
 * @author sevens 2014/6/26 16:08
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String[] IGNORE_URI = { "/admin/login.html",
			"/admin/loginout.do", "/admin/passwordsave.do",
			"/admin/modifypassword.do", "/admin/login.do" };

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = false;
		Admin user = (Admin) request.getSession().getAttribute("admin");
		String url = request.getRequestURL().toString();
		String upurl = request.getHeader("Referer");
		String message = null;
		
		if (upurl == null) {
			message = url;
			upurl = request.getContextPath() + "/admin/list.do?message="
					+ message;
		}
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			boolean go = false;
			@SuppressWarnings("unchecked")
			List<Tree> treelist = (List<Tree>) request.getSession()
			.getAttribute("tree");
			
				if (treelist != null && treelist.size() > 0) {
					
					for (Tree tree : treelist) {
						if (url.contains(tree.getUrl())) {
							go = true;
							break;
						}
						if (tree.getOpationIds() != null) {
							String[] opation = tree.getOpationIds().split(",");
							for (String string : opation) {
								if (url.contains(string)) {
									go = true;
									break;
								}
							}
							
						}
						
					}
				}
			
			
			
			String ssmps = (String)request.getSession().getAttribute("ssmp");
			int ssmp = 0;
			if(ssmps!=null){
		        ssmp = Integer.parseInt(ssmps);	
		    }   
			if (user != null) {
				String did = request.getParameter("did");
				String tid = request.getParameter("tid");
				if (did != null || tid != null) {
					request.getSession().setAttribute("dids", did);
					request.getSession().setAttribute("tid", tid);
				} else {
					request.getSession().setAttribute("dids",
							request.getSession().getAttribute("dids"));
					request.getSession().setAttribute("tid",
							request.getSession().getAttribute("tid"));
				}
				if (go == false) {
					request.getSession().setAttribute("Competence",
							"你没有目标页面的访问权限，请与管理员联系！");
					        ssmp = 1;
					        request.getSession().setAttribute("ssmp",ssmp+"");
					        response.sendRedirect(upurl);
				}else{
					if(ssmp==1){
						ssmp ++;
						request.getSession().setAttribute("Competence","你没有目标页面的访问权限，请与管理员联系！");
						request.getSession().setAttribute("ssmp",ssmp+"");
					}else{
						request.getSession().setAttribute("Competence","欢迎光临！");
						request.getSession().removeAttribute("ssmp");
					}
				} 
				flag = true;

			} else {
				response.sendRedirect(request.getContextPath()
						+ "/admin/login.html");

			}
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
package com.wenjing.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.wenjing.EncodingHttpServletRequest;
/** 
 * 编码过滤器 
 *  
 * @author xy 
 *  
 */  
public class MyecondingFilter implements Filter  
{  
    private String encoding;  
  
    public void init(FilterConfig fConfig) throws ServletException  
    {  
        encoding = fConfig.getInitParameter("encoding");  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
    {  
        HttpServletRequest httprequest = (HttpServletRequest) request;  
       
        if ("GET".equals(httprequest.getMethod()))  
        {  
        	
            // 将httpRequest进行包装  
            EncodingHttpServletRequest wrapper = new EncodingHttpServletRequest(httprequest, encoding); 
            
            try {
				chain.doFilter(wrapper, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }  
        else  
        {  
            try {
				request.setCharacterEncoding(encoding);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            response.setContentType("text/html;charset=" + encoding);  
            try {
				chain.doFilter(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }  
    }  
  
    public void destroy()  
    {  
  
    }  
  
}  
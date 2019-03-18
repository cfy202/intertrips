package com.wenjing;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



public class SendmailConnect {
	private static final String path= "http://50.63.27.248:8080/sendmail/servlet/SendmailServlet";
    static {
    	
    }
	
	
	/**
	 * @param args
	 *            将传来的参数转成url发出请求并将响应的参数返回
	 */
	
	
	
	/**
	  * 通过httpClient  以post请求向服务器发送数据
	  * @param path
	  * @param username
	  * @param password
	  * @return
	  * @throws Exception
	  */
	 public static boolean loginHttpClientByPost(String content,String[] address,String subject){
	  boolean issend = false ;
	try {
		//1 得到浏览器
		HttpClient httpClient = new DefaultHttpClient();
		
		//2 指定请求方式
		HttpPost httpPost = new HttpPost(path);
		
		//3构建请求实体的数据
		StringBuffer sf= new StringBuffer();
		for(int i = 0;i<=address.length-1;i++){
			
			if(i==address.length-1){
				sf.append(address[i]);  
			}else{
				sf.append(address[i]+"/");    
			}
		}
		
		
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("content", content));
		parameters.add(new BasicNameValuePair("address", sf.toString()));
		parameters.add(new BasicNameValuePair("subject", subject));
		
		//4 构建实体
		UrlEncodedFormEntity entity;
		entity = new UrlEncodedFormEntity(parameters, "utf-8");
		
		//5 把实体数据设置到请求对象
		httpPost.setEntity(entity);
		
		//6 执行请求
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//7 判断请求是否成功
		if(httpResponse.getStatusLine().getStatusCode() == 200){
			issend = true;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		 issend = false;
	}
	  return issend;
 }
	
	public static void main(String[] args) {
		try {
			String [] address= { "ruanxiaoqiiis@gmail.com"};
			boolean ss=SendmailConnect.loginHttpClientByPost("hello sevens", address,"稳拿反过来");
			System.out.println(ss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

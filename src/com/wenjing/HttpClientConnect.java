package com.wenjing;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpClientConnect {

	/**
	 * @param args
	 *            将传来的参数转成url发出请求并将响应的参数返回
	 * @throws IOException 
	 */
	
	public static String doPost(String callURL,Map<String, String> paramsMap) throws IOException,ClientProtocolException,SocketTimeoutException{
		
		// 响应回来的字符串
		String responseStr = null;

		// httpclient对象,用于连接paypal
		HttpClient httpClient = null;

		
		
	    /*
		 * 将请求发出，如果响应状态不为，方法返回null 若响应状态正常，将响应字符串进行utf-8解码装于 resStr 中
		 */
		httpClient = getSecuredHttpClient(new DefaultHttpClient());
		
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,5000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,10000);
		
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(paramsMap.size());  
        for(Map.Entry<String, String> entry : paramsMap.entrySet()){  
        	valuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
        }  
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(valuePairs, "UTF-8"); 
        
		try {
		    HttpPost httpPost = new HttpPost(callURL); 
		    httpPost.setEntity(formEntity);  
	        HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			responseStr = URLDecoder.decode(EntityUtils.toString(entity),"UTF-8");
		    
		    // Do not feel like reading the response body
			// Call abort on the request object
			httpPost.abort();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpClient.getConnectionManager().shutdown();
		}
		return responseStr;
	}
	
	public static Map<String, String> doECAPI(String callURL,Map<String, String> paramsMap) throws SocketTimeoutException, ClientProtocolException, IOException{
		// 用来装填响应回来的name value对并作为函数参数返回
	    Map<String, String> resultMap = new HashMap<String, String>();
		
		String resStr = doPost(callURL,paramsMap);
		
		/*
		 * 将响应回来的字符串split，将其中的name value取出装入resultMap并返回结果
		 */
		for (String keyValue : resStr.split("&")) {
			String[] kv = keyValue.split("=");
			resultMap.put(kv[0], kv[1]);
		}
		return resultMap;
	}

	// 封装HttpClient，可以不用验证安全凭证
	private static DefaultHttpClient getSecuredHttpClient(HttpClient httpClient) {

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
				
			};
			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());
			SSLSocketFactory ssf = new SSLSocketFactory(ctx,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
			return new DefaultHttpClient(ccm, httpClient.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

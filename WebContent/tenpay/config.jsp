<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% 

//测试账号
//String partner = "1900000113";					//商户号
//String key = "e82573dc7e6136ba414f2e2affbe39fa";	//密钥

//正式账号
String partner = "1251521801";						//商户号
String key = "89b72cd18143f0bc5995969ec62bbbbb";	//密钥

//交易完成后跳转的URL
//String return_url = "https://192.168.1.117:8080/uswenjing/tenpay/return.do";
String return_url = "https://www.wenjing.com/tenpay/return.do";

//接收财付通通知的URL
//String notify_url = "https://192.168.1.117:8080/uswenjing/tenpay/notify.do";
String notify_url = "https://www.wenjing.com/tenpay/notify.do";

%>

package com.wenjing;

import javax.mail.Message;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;




public class Mail { 

	//public String USERNAME = "info@intertrips.com";//邮箱用户�?
	public String USERNAME = "booking@intertrips.com";//邮箱用户�?
	//private String PASSWORD = "In3955as";//密码
	public String PASSWORD = "Angles12";

    private static final String SMTP_HOST_NAME = "smtp.exmail.qq.com"; //smtp服务�?
	
    private static final String SMTP_PORT = "465";  
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  //发�?加密邮件
	private  String[] sendTo = {"2977260348@qq.com"};  
    private  String emailMsgTxt = "test";  
    private  String emailSubjectTxt = "test";  
    private  String emailFromAddress = "booking@intertrips.com";
    private  String[] copyto = {};
    
    public Mail(){
    }
  
    public Mail(String costCode){
    	super();
    	if("AUD".equals(costCode)){
    		USERNAME = "booking@intertrips.com";
    		PASSWORD = "Angles12";
    		emailFromAddress = "booking@intertrips.com";
    	}else if("CNY".equals(costCode)){
    		
    		
    	}else if("CAD".equals(costCode)){
    		
    		
    	}else if("EUR".equals(costCode)){
    		
    		
    	}
    }
  
    public String[] getSendTo() {
		return sendTo;
	}



	public void setSendTo(String[] sendTo) {
		this.sendTo = sendTo;
	}



	public String getEmailMsgTxt() {
		return emailMsgTxt;
	}



	public void setEmailMsgTxt(String emailMsgTxt) {
		this.emailMsgTxt = emailMsgTxt;
	}



	public String getEmailSubjectTxt() {
		return emailSubjectTxt;
	}



	public void setEmailSubjectTxt(String emailSubjectTxt) {
		this.emailSubjectTxt = emailSubjectTxt;
	}



	public String getEmailFromAddress() {
		return emailFromAddress;
	}



	public void setEmailFromAddress(String emailFromAddress) {
		this.emailFromAddress = emailFromAddress;
	}



	public String[] getCopyto() {
		return copyto;
	}



	public void setCopyto(String[] copyto) {
		this.copyto = copyto;
	}



	public void sendSSLMessage()throws MessagingException {  
        boolean debug = false;  
  
        Properties props = System.getProperties(); 
        props.setProperty("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.host", SMTP_HOST_NAME);  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.debug", "false");  
        props.put("mail.smtp.port", SMTP_PORT);  
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);  
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);  
        props.put("mail.smtp.socketFactory.fallback", "false");  
  
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {  
  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }  
        });  
  
        session.setDebug(debug);  
  
        Message msg = new MimeMessage(session);  
        InternetAddress addressFrom = new InternetAddress(emailFromAddress);  
        msg.setFrom(addressFrom);  
  
        InternetAddress[] addressTo = new InternetAddress[sendTo.length];  
        for (int i = 0; i < sendTo.length; i++) {  
            addressTo[i] = new InternetAddress(sendTo[i]);  
        }  
        //设置抄送人
        InternetAddress[] internetAddressCC = new InternetAddress[copyto.length];  
        for (int i = 0; i < copyto.length; i++) {  
        	internetAddressCC[i] = new InternetAddress(copyto[i]);  
        }  
        msg.setRecipients(Message.RecipientType.TO, addressTo); 
        msg.setRecipients(Message.RecipientType.CC, internetAddressCC);
        
  
        // Setting the Subject and Content Type  
        msg.setSubject(emailSubjectTxt);  
        msg.setContent(emailMsgTxt,"text/html;charset=utf-8");  
        Transport.send(msg);  
    }  
	
	public String getUSERNAME() {
		return USERNAME;
	}
	
	
	public static void main(String[] args) throws MessagingException {
		Mail gmail=new Mail();
		gmail.sendSSLMessage();
		System.out.println("======================");
	}
}

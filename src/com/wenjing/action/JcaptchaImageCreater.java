package com.wenjing.action;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/tcaptcha")
public class JcaptchaImageCreater {
  
//  public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
//    try {
//      ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//      String captchaId = request.getSession().getId();
//      BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());
//     System.out.println(challenge);
//      response.setHeader("Cache-Control", "no-store");
//      response.setHeader("Pragma", "no-cache");
//      response.setDateHeader("Expires", 0L);
//      response.setContentType("image/jpeg");
//      
//      ImageIO.write(challenge, "jpeg", jpegOutputStream);
//      byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//
//      ServletOutputStream respOs = response.getOutputStream();
//      respOs.write(captchaChallengeAsJpeg);
//      respOs.flush();
//      respOs.close();
//    } catch (IOException e) {
//      logger.error("generate captcha image error: {}", e.getMessage());
//    }
//  }
// 
  /*
   * 验证码生成工具
   */
  @RequestMapping
  public void execute(HttpServletRequest request, HttpServletResponse response){
		
		   	response.setContentType("image/gif");
		   	String path =  request.getSession().getServletContext().getRealPath("/");
		   	BufferedImage bi =null;
		   	Random bgclor = new Random();
		   	int bgc = bgclor.nextInt(60);
		   	File file = new File(path+"/captcha/captcha_bg_"+bgc+".jpg");
		                    Image image=null;
		                    int width =0;
		                    int height =0;
							try {
								image = ImageIO.read(file);
								 width = 90;
								 height =38;
								bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		   	
							 Graphics2D g = bi.createGraphics();
							 g.drawImage(image, 0, 0, width, height, null);
			String number="";
			
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("幼圆",1,28));
			String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			for(int i=0;i<4;i++){
				number+=chars.charAt((int)(Math.random() * 52));
			}
			Random rr=new Random();
			for(int k=0;k<10;k++){
			int x=	rr.nextInt(80);
			int y=rr.nextInt(40);
			int a=rr.nextInt(80);
			int b=rr.nextInt(40);
			g.drawLine(a, b, x, y);
			}
			g.drawString(number+"", 10,22);
			number = number.toLowerCase();
			System.out.println(number);
			request.getSession().setAttribute("number", number+"");
			try {
				  ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
				  ImageIO.write(bi, "jpeg", jpegOutputStream);
			      byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

			      ServletOutputStream respOs = response.getOutputStream();
			      respOs.write(captchaChallengeAsJpeg);
			      respOs.flush();
			      respOs.close();
			} catch (IOException e) {
				
			}
	      
		     }
		

}

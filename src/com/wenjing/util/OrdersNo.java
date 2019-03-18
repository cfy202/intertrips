package com.wenjing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
/**
 * 订单生成器
 * @author Sevens
 *
 */
public class OrdersNo {
private static File file;
	
	static{
		URL uri = Tools.class.getResource("/");
		file = null;
		try {
			file = new File(uri.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pathname = file.getAbsolutePath()+"/ordersnumber.properties";
		
		System.out.println(pathname);
		
		file = new File(pathname);
		
		if(!file.exists())
		{
			try {
				file.createNewFile();
				//读取配置文件并对其初始化
				PrintStream out = new PrintStream(file);
				out.println("num=0");
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	private static long buffer = 3;
	private static int now = 1;
	private static int max = 1;
	private static int autoupdate = 0 ;
	/**
	 * 获取订单号
	 * @author Sevens
	 * 时间2015-7-23
	 * @return
	 */
	public synchronized static String getordersNumber(){
		
		
		if(now>=max)//如果当前订单编号大于最大序号
		{
			
			Properties p = new Properties();
			try {
				p.load(new FileInputStream(file));//获取配置文件
				now = Integer.parseInt(p.getProperty("num"));
				autoupdate = Integer.parseInt(p.getProperty("status"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			max+=(buffer+now);
			try {
				PrintStream out = new PrintStream(file);;//跟新最新订单序号
				out.println("num="+max);
				out.println("status="+autoupdate);
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		now++;
		String nos=now+"";
		StringBuffer sso=new StringBuffer();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); 
		String years=year+"";
		String yesrt=years.substring(2, 4);
		sso.append(yesrt);
		
		int month = c.get(Calendar.MONTH)+1;
		String temp = ("0"+month);
		sso.append(temp.substring(temp.length()-2));
		
		int date = c.get(Calendar.DATE);
		temp = ("0"+date);
		sso.append(temp.substring(temp.length()-2));
		
		temp = ("000"+nos);
		sso.append(temp.substring(temp.length()-4));
		
		return sso.toString();
	}
	/**
	 * 通过定时任务每天来初始化订单号的配置文件
	 * @author Sevens
	 * 时间2015-7-23
	 */
	public void initNumber(){
        max=1;
		try {
			PrintStream out = new PrintStream(file);
			out.println("num="+0);
			out.println("status="+0);
			out.close();
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getStatus(){
		int autoDelete = 0;
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(file));//获取配置文件
			autoDelete = Integer.parseInt(p.getProperty("status"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoDelete;
	}
	
	/**
	 * 通过定时任务每天来发出删除指令
	 * @author Sevens
	 * 时间2015-7-23
	 */
	public void setStuts(int status){
        max=1;
		try {
			PrintStream out = new PrintStream(file);
			out.println("num="+max);
			out.println("status="+status);
			out.close();
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  public static void main(String[] args) {
	OrdersNo on  = new OrdersNo();
	System.out.println(on.getordersNumber());
}	
	
}

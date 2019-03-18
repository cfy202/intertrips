package com.wenjing.service;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenjing.util.OrdersNo;

@Service	
public class TimeTaskService {
	@Resource
	private PageService pageService;
	@Autowired
	private TourlineService tourlineService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
	private static String tomcatPackageName;
	
	static{
		String tomcatPath = System.getProperty("catalina.base");
		String[] pathArray;
		try{
			pathArray = tomcatPath.split(File.separator);
		}catch(Exception e){
			pathArray = tomcatPath.split("\\\\");
		}
		tomcatPackageName = pathArray[pathArray.length-1];
	}

	public void initorder() {
		OrdersNo ord = new OrdersNo();
		ord.initNumber();
	}

	public void autoCreateTourline() {
		try {
			if("tomcat".equals(tomcatPackageName)){
				tourlineService.autoCreateTourline();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void autoSynchronizeProduct() {
		if("tomcat".equals(tomcatPackageName)){
			productService.autoSynchronizeProductToERP();
		}
	}

	public void autoSynchronizeOrders() {
		if("tomcat".equals(tomcatPackageName)){
			orderService.synchronizeOrdersToERP();
		}
	}

	public void autoReturnStore() {
		if("tomcat".equals(tomcatPackageName)){
			orderService.autoReturnStore();
		}
	}
}

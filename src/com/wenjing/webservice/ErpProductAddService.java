/**
 * 
 */
package com.wenjing.webservice;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wenjing.dao.ItineraryMapper;
import com.wenjing.dao.TourlineMapper;
import com.wenjing.entity.Itinerary;
import com.wenjing.entity.Product;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourline;
import com.wenjing.service.TourDateService;
import com.wenjing.util.Tools;
import com.wenjing.util.WeekDayUtil;
import com.wenjing.webservice.entity.GroupLine;
import com.wenjing.webservice.entity.GroupRoute;
import com.wenjing.webservice.remoteinterface.GroupLineInterface;

/**
 * 类说明	通过接口进行系统产品录入
 * @author xiejin
 * @date 2016-3-10 
 * @date 2016-3-10 下午3:45:49
 */
@Service
public class ErpProductAddService {
	
	@Resource
	TourlineMapper tourlineMapper;
	@Resource
	ItineraryMapper itineraryMapper;
	@Resource
	private TourDateService tourDateService;
	
	public List<Integer> addProduct(List<Product> productList){
		List<GroupLine> grouplineList = getGroupLine(productList);
		org.codehaus.xfire.service.Service srvcModel = new ObjectServiceFactory().create(GroupLineInterface.class);
		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire()); 
		String visitUrl = "http://47.88.0.72/service/serviceInterface";
//		String visitUrl = "http://192.168.1.66/chinatour-3.0/service/serviceInterface";
//		String visitUrl = "http://192.168.1.101:8080/chinatour-3.0/service/serviceInterface";	
		GroupLineInterface groupLineInterface = null;
		try {
			groupLineInterface = (GroupLineInterface)factory.create(srvcModel,visitUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		String groupLineListJson = JSON.toJSONString(grouplineList);
		String resultJson = groupLineInterface.addGroupLine(groupLineListJson);
		List<Integer> resultList = JSON.parseArray(resultJson, Integer.class);
		return resultList;
	}
	
	/**
	 * @Title: getGroupLine
	 * @Description: 获得添加的线路集合
	 * @param tourlineIdList
	 * @return    
	 * @return List<GroupLine>    返回类型
	 * @author xiejin
	 */
	public List<GroupLine> getGroupLine(List<Product> productList){
		List<GroupLine> groupLineList = new ArrayList<GroupLine>();
		for (Product product : productList) {
			Tourline tourline = product.getTourline();
			GroupLine groupline = new GroupLine();
			if (tourline != null) {
				String code = product.getCost().getCode().toUpperCase().substring(0, 2);
				groupline.setTourCode("I-" + product.getProductNo().trim() + "-" + code);
				groupline.setBrand("intertrips");
				groupline.setTourName(tourline.getTourname());
				groupline.setTripDesc(product.getBriefinfo());
				String departureDate = this.getDepartureDate(product.getId());		//线路出发日期
				groupline.setDepartureDate(departureDate);
				groupline.setSpecificItems(tourline.getNotice());
				groupline.setDestination(tourline.getEndDestination());
				groupline.setPlaceStart(tourline.getStartDestination());
				groupline.setAttractions(tourline.getAttractionlist());
				groupline.setRemark(String.valueOf(tourline.getDays()));
				groupline.setDestinationlist(tourline.getDestinationlist());
				if(tourline.getInterval() == null || tourline.getInterval() < 0){
					groupline.setAddDate(0);
				}else{
					groupline.setAddDate(tourline.getInterval());
				}
				
				List<Itinerary> itinerary = tourline.getItinerarys();
				List<GroupRoute> gList = new ArrayList<GroupRoute>();
				for (Itinerary itinerary2 : itinerary) {
					GroupRoute g1 = new GroupRoute();
					g1.setDayNum(itinerary2.getDay());
					g1.setRouteName(itinerary2.getTitle());
					g1.setRouteDescribeForEn(itinerary2.getContent());
					g1.setRouteDescribeForUs(itinerary2.getContent());
					if(itinerary2.getHotel() != null){
						String hotelString = itinerary2.getHotel().getHotelname();
						if (hotelString != null && !hotelString.equals("")) {
							g1.setHotel(hotelString);
						}	
					}
					gList.add(g1);
				}
				groupline.setGroupRoute(gList);
			}
			groupLineList.add(groupline);
		}
		return groupLineList;
	}
	
	/**
	 * @Title: getDepartureDate
	 * @Description: 根据线路id查询出发日期
	 * @param tourlineId
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	public String getDepartureDate(String tourlineId){
		Set<String> dateAll = new HashSet<String>();	//存放日期的set集合
		//线路对应日期
		List<Tourdate> tourDateList = tourDateService.getAllTourdate(tourlineId);
		for (Tourdate tourdate2 : tourDateList) {
			String startTimeString = Tools.getTime(tourdate2.getStartdate());
			String endTimeString = Tools.getTime(tourdate2.getEnddate());
			if (tourdate2.getDays()==0) {	//不是隔几天发团
				if (tourdate2.getDateweek().equals("0") || tourdate2.getDateweek().equals("8")) {//当天或天天发团
					List<String> dateList = WeekDayUtil.findDates(startTimeString,endTimeString);
					for (String date : dateList) {
						dateAll.add(date);
					}
				}else{
					String dateweekString = WeekDayUtil.getStringWeek(tourdate2.getDateweek());
					String[] dateList = WeekDayUtil.getDates(startTimeString, endTimeString, dateweekString);
					for (int i = 0; i < dateList.length; i++) {
						dateAll.add(dateList[i]);
					}
				}
			}else{		//隔几天发团
				int days = tourdate2.getDays();
				if (tourdate2.getDateweek().equals("0") || tourdate2.getDateweek().equals("8")) {	//没有选择星期几
					List<String> dateList = WeekDayUtil.findDates(startTimeString,endTimeString);
					for (int i = 0; i < dateList.size(); i+=days) {
						dateAll.add(dateList.get(i));
					}
				}else{	//选择星期几
					String dateweekString = WeekDayUtil.getStringWeek(tourdate2.getDateweek());
					String[] dateweekArray = dateweekString.split(",");
					for (int i = 0; i < dateweekArray.length; i++) {
						String[] dateList = WeekDayUtil.getDates(startTimeString, endTimeString, dateweekArray[i]);
						for (int j = 0; j < dateList.length; j+=days/7) {
							dateAll.add(dateList[j]);
						}
					}
					
				}
			}
		}
//		String dateAllString = Tools.ListToString(new ArrayList<String>(dateAll));
		StringBuffer buf = new StringBuffer();
		for (String date : dateAll) {
			buf.append(","+date);
		}
		String tourdateString = "";
		if(buf!=null && buf.length()>0){
			tourdateString = buf.substring(1);
		}
		return tourdateString;
	}
}

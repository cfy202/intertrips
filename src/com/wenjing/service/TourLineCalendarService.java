package com.wenjing.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenjing.dao.CostMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.PromotionMapper;
import com.wenjing.dao.TourdateMapper;
import com.wenjing.dao.TourlineMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourline;
import com.wenjing.util.Tools;

/**
 * 类说明
 * @author xiejin
 * @date 2015-5-28 
 * @date 2015-5-28 下午4:46:09
 */
@Service
public class TourLineCalendarService {
	
	@Resource
	private TourdateMapper tourdateMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private PromotionMapper promotionMapper;
	@Resource
	private TourlineService tourlineService;
	@Autowired
	private TourlineMapper tourlineMapper;
	@Autowired
	private TourDateService tourDateService;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CostMapper costMapper;
	
	/**
	 * 购物车页面获取日历
	 * 
	 * @param oyear
	 * @param omonth
	 * @param tourlineid
	 * @param code
	 * @param costnumber
	 * @return
	 */
//	@Transactional(readOnly=true)
//	public String getCanchar(int oyear,int omonth,String tourlineid,String code,String costnumber){
//		//获取该线路在当前的销售中心下所有的出发日期以及对应的价格
//		List<Tourdate> originalTourDateList = tourDateService.getDateAndPrice(tourlineid,costnumber);	//查找线路日期
//		List<Tourdate> tourDateList = null;
//		try {
//			tourDateList = Tools.deepCopy(originalTourDateList);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "";
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "";
//		}
//			
//		//判断是否参与促销活动，并修改售价		<--开始-->
//		for (Tourdate tourdate : tourDateList) {
//			tourdate.setTourprice(tourlineService.changeTourPrice(tourdate.getTourprice(), tourlineid, costnumber));
//		}	
//		
//		//<--结束-->
//		//根据传入年月获得日历起初日期的时间戳
//		int firstDateTimestamp = Tools.getTimestemp(oyear + "-" +omonth + "-1");
//		
//		//如果tourdate的最小出发日期大于传入日期，则将最小出发日期设置为传入日期，并重新设置日历起初日期的时间戳
//		if(tourDateList.size() > 0 && firstDateTimestamp < tourDateList.get(0).getStartdate()) { //线路最小出发日期和传过来的时间戳比较
//			String vdate = Tools.getTime(tourDateList.get(0).getStartdate());  
//			oyear = Integer.valueOf(vdate.split("-")[0]);
//			omonth = Integer.valueOf(vdate.split("-")[1]);
//			firstDateTimestamp = Tools.getTimestemp(oyear + "-" +omonth + "-1");
//		}
//		
//		/**
//		 * 根据年月获得起初日期的星期下标
//		 */
//		Calendar firstDate = Calendar.getInstance();
//		firstDate.set(oyear, omonth-1, 1);
//		int firstDateWeekIndex = firstDate.get(Calendar.DAY_OF_WEEK) - 1;
//		
//		/**
//		 * 日期显示的格式和日期号
//		 */
//		int daysArray[][] = new int[6][7];
//		int dayInMonth = 1;
//
//		//每个月的最大天数
//		int maxDaysInMonth = Tools.getMaxDays(omonth,oyear);
//		
//		for (int i = firstDateWeekIndex; i < 7; i++) {
//			daysArray[0][i] = dayInMonth++;
//		}
//		for (int i = 1; i < 6; i++) {
//			for (int j = 0; j < 7; j++) {
//				if (dayInMonth > maxDaysInMonth) {
//					i = 6;
//					break;
//				}
//				daysArray[i][j] = dayInMonth++;
//			}
//
//		}
//		StringBuffer str = new StringBuffer();
//		String weekTitles[] = {"星期日","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
//		int currentTimestamp = Tools.getTimestemp(Tools.getTime());	//当前时间戳
//		int minStartDateTimestamp = 0;
//		if (tourDateList.size() != 0) {
//			minStartDateTimestamp = tourDateList.get(0).getStartdate();		//最小出发日期时间戳
//		}
//		str.append("<table cellpadding=\"0\" cellspacing=\"0\" class=\"w100\">");
//		
//		//如果当前月份的首日大于当前时间并且当前月份的首日大于最小的出发日期，则含有上月
//		if (firstDateTimestamp > currentTimestamp && firstDateTimestamp > minStartDateTimestamp) {
//			str.append("<tr class=\"line1\">"+
//					"<td class=\"line_td1\" height=\"34\"><a onclick=\"lastMonth(this);\" class=\"a4\">&lt; 前一个月</a></td>"+
//					"<td class=\"line_td1\" colspan=\"5\">"+oyear+"年"+omonth+"月</td>"+
//					"<td>&nbsp;<a onclick=\"nextMonth(this);\" class=\"a4\">后一个月 &gt;</a></td>"+
//					"</tr>"
//					);
//		}else{
//			str.append("<tr class=\"line1\">"+
//					"<td class=\"line_td1\" height=\"34\"></td>"+
//					"<td class=\"line_td1\" colspan=\"5\">"+oyear+"年"+omonth+"月</td>"+
//					"<td>&nbsp;<a onclick=\"nextMonth(this);\" class=\"a4\">后一个月 &gt;</a></td>"+
//					"</tr>"
//					);
//		}
//		//显示周几
//		str.append("<tr class=\"line2\">");
//			for (int i = 0; i < weekTitles.length; i++) {
//				str.append("<td width=\"14.3%\">");
//				str.append(weekTitles[i]);
//				str.append("</td>");
//			}
//		str.append("</tr>");		
//			
//		for (int i = 0; i < 6; i++) {
//			str.append("<tr>");
//			for (int j = 0; j < 7; j++) {
//				if (daysArray[i][j] == 0) {
//					str.append("<td>");
//					str.append("</td>");
//					continue;
//				} else{
//					//产品余量和价格的显示
//					String productRemainderNumberAndPriceHtml = "";
//					//每个单元最外围的DIV,可添加事件
//					String outerDivHtml = "<div class=\" after_today_date\">";
//					
//					String eachDayDateString = oyear + "-" +omonth + "-" + daysArray[i][j];
//					int eachDayTimestamp = Tools.getTimestemp(eachDayDateString);
//					
//					ShowInfo[] showInfoArray = new ShowInfo[tourDateList.size()];
//					for (int tourDateIndex=0; tourDateIndex<tourDateList.size(); tourDateIndex++) {
//						Tourdate tourdate = tourDateList.get(tourDateIndex);
//						
//						//该日期时间戳大于tourdate的起始日期时间戳，小于tourdate的终止日期时间戳并且该日期在当前日期延期tourdate.getSealGroupDate()天后显示
//						if (eachDayTimestamp >= tourdate.getStartdate() && eachDayTimestamp <= tourdate.getEnddate() && eachDayTimestamp >= (currentTimestamp + 3600 * 24 * tourdate.getSealGroupDate())) {
//							//如果是单天发团或全周发团
//							if (tourdate.getDateweek().equals("0") || tourdate.getDateweek().equals("8")) {
//                                //如果间隔日期大于0,则间隔显示
//								if(tourdate.getDays() > 0){
//									int intervalTime = (eachDayTimestamp - tourdate.getStartdate())/3600/24;
//									if(intervalTime % tourdate.getDays() == 0){
//										showInfoArray[tourDateIndex] = fillDateInSingle(eachDayDateString,tourdate,code);
//									}
//								}else{
//									showInfoArray[tourDateIndex] = fillDateInSingle(eachDayDateString,tourdate,code);
//								}
//							//如果是周一到周末发团
//							}else{
//								String dateweek = tourdate.getDateweek();
//								if (dateweek.indexOf("7") != -1) {
//									dateweek =	dateweek.replace("7", "0");
//								}
//								if (dateweek.indexOf(j+"") != -1) {
//									//如果间隔日期大于0,间隔显示
//									if(tourdate.getDays() > 0){
//										int intervalTime = (eachDayTimestamp - tourdate.getStartdate())/3600/24;
//										int totalWeek = intervalTime/7;
//										int actualIntervalWeek = tourdate.getDays()/7;
//										
//										if(totalWeek%actualIntervalWeek == 0){
//											showInfoArray[tourDateIndex] = fillDateInSingle(eachDayDateString,tourdate,code);
//										}
//									}else{
//										showInfoArray[tourDateIndex] = fillDateInSingle(eachDayDateString,tourdate,code);
//									}
//								}
//							}
//						}
//					}
//					/**
//					 * 遍历所有tourdate信息，间隔时间最短的有效
//					 */
//					ShowInfo validShowInfo = null;
//					for(ShowInfo showInfo : showInfoArray){
//						if(showInfo != null){
//							if(validShowInfo == null){
//								validShowInfo = showInfo;
//							}else{
//								if(showInfo.dateInterval < validShowInfo.dateInterval){
//									validShowInfo = showInfo;
//								}
//							}
//						}
//					}
//					str.append("<td>");
//					if(validShowInfo == null){
//						str.append(outerDivHtml +
//                        		"<div class=\"left\">"+ daysArray[i][j] +"</div>"+
//                        		"<div class=\"right\">"+ productRemainderNumberAndPriceHtml +"</div>"+
//                        		"<div class=\"clear\"></div>"+
//                        		"</div>"
//						);	
//					}else{
//						str.append(validShowInfo.outerDivHtml +
//                        		"<div class=\"left\">"+ daysArray[i][j] +"</div>"+
//                        		"<div class=\"right\">"+ validShowInfo.productRemainderNumberAndPriceHtml +"</div>"+
//                        		"<div class=\"clear\"></div>"+
//                        		"</div>"
//						);	
//					}
//					str.append("</td>");
//				}
//			}
//			str.append("</tr>");
//		}
//		str.append("</table>");
//		str.append("<input type=\"hidden\" name=\"month\" class='calendarMonth' value=\""+omonth+"\" id=\"Month\"/>");
//		str.append("<input type=\"hidden\" name=\"year\" class='calendarYear' value=\""+oyear+"\" id=\"Year\"/>");
//		String c = str.toString();
//		return c;
//	}
	
	/**
	 * 线路详情页面获得日历
	 * 
	 * @param oyear
	 * @param omonth
	 * @param tourlineid
	 * @param code
	 * @param costnumber
	 * @return
	 */
//	@Transactional(readOnly=true)
//	public String getCancharWithDouble(int oyear,int omonth,String tourlineid,String code,String costnumber){
//		Tourline tourline = tourlineMapper.selectByPrimaryKey(tourlineid);
//		//获取该线路在当前的销售中心下所有的出发日期以及对应的价格
//		List<Tourdate> tourDateList = tourDateService.getDateAndPrice(tourline.getProductid(),costnumber);	//查找线路日期
//		
//		//判断是否参与促销活动，并修改售价		<--开始-->
//		for (Tourdate tourdate : tourDateList) {
//			tourdate.setTourprice(tourlineService.changeTourPrice(tourdate.getTourprice(), tourlineid, costnumber));
//		}
//		
//		//根据传入年月获得日历起初日期的时间戳
//		int firstDateTimestamp = Tools.getTimestemp(oyear + "-" +omonth + "-1");
//		
//		//如果tourdate的最小出发日期大于传入日期,则将最小出发日期设置为传入日期,并重新设置日历起初日期的时间戳
//		if(tourDateList.size() > 0 && firstDateTimestamp < tourDateList.get(0).getStartdate()) { //线路最小出发日期和传过来的时间戳比较
//			String vdate = Tools.getTime(tourDateList.get(0).getStartdate());
//			oyear = Integer.valueOf(vdate.split("-")[0]);
//			omonth = Integer.valueOf(vdate.split("-")[1]);
//			firstDateTimestamp = Tools.getTimestemp(oyear + "-" +omonth + "-1");
//		}
//		
//		StringBuffer calendarHtml = new StringBuffer();
//		/**
//		 * 当月
//		 */
//		calendarHtml.append("<div class=\"new_calendar\" style=\"border-right:1px dotted #ddd\">");
//		calendarHtml.append(generateSingleMonthHtml(oyear,omonth,tourline.getDays(),tourDateList,firstDateTimestamp,code));
//		
//		calendarHtml.append("</div><div class=\"clear\"></div>");
//		calendarHtml.append("<input type=\"hidden\" name=\"month\" class='calendarMonth' value=\""+omonth+"\" id=\"Month\"/>");
//		calendarHtml.append("<input type=\"hidden\" name=\"year\" class='calendarYear' value=\""+oyear+"\" id=\"Year\"/>");
//		return calendarHtml.toString();
//	}
	
	//生成每个月的日历的html
//	private String generateSingleMonthHtml(int oyear,int omonth,int days,List<Tourdate> tourDateList,int firstDateTimestamp,String code){
//		/**
//		 * 根据年月获得起初日期的星期下标
//		 */
//		Calendar firstDate = Calendar.getInstance();
//		firstDate.set(oyear, omonth-1, 1);
//		int firstDateWeekIndex = firstDate.get(Calendar.DAY_OF_WEEK) - 1;
//		
//		/**
//		 * 日期显示的格式和日期号
//		 */
//		int daysArray[][] = new int[6][7];
//		int dayInMonth = 1;
//
//		/**
//		 * 获取上个月的最大天数
//		 */
//		int lastMonth = omonth - 1;
//		int lastYear = oyear;
//		if(lastMonth == 0){
//			lastMonth = 12;
//			lastYear = oyear - 1;
//		}
//		int lastMonthMaxDays = Tools.getMaxDays(lastMonth,lastYear);
//		
//		//每个月的最大天数
//		int maxDaysInMonth = Tools.getMaxDays(omonth,oyear);
//
//		/**
//		 * 填充日历第一行的日期数据
//		 */
//		for (int i = firstDateWeekIndex; i < 7; i++) {
//			daysArray[0][i] = dayInMonth++;
//		}
//		for(int i = firstDateWeekIndex - 1; i >= 0; i--){
//			daysArray[0][i] = lastMonthMaxDays--;
//		}
//		/**
//		 * 填充日历后续的日期数据
//		 */
//		outer:
//		for (int i = 1; i < 6; i++) {
//			for (int j = 0; j < 7; j++) {
//				if (dayInMonth > maxDaysInMonth) {
//					if(j == 0){
//						break outer;  
//					}
//					daysArray[i][j] = (dayInMonth++) - maxDaysInMonth;
//					if(j == 6){
//						break outer;  
//					}
//				}else{
//					daysArray[i][j] = dayInMonth++;
//				}
//			}
//		}
//		
//		/**
//		 * 存放日历html
//		 */
//		StringBuffer str = new StringBuffer();
////		Calendar calendar2 = Calendar.getInstance();
////		int yearNow = calendar2.get(Calendar.YEAR);		//当前年份
////		int monthNow = calendar2.get(Calendar.MONTH)+1;	//当前月份
//		//一周的标题
//		
//		int currentTimestamp = Tools.getTimestemp(Tools.getTime());	//当前时间戳
//		int minStartDateTimestamp = 0;
//		if (tourDateList.size() != 0) {
//			minStartDateTimestamp = tourDateList.get(0).getStartdate();		//最小出发日期时间戳
//		} 
//		
//		//如果日历首日大于当前日期并且大于最小的出发日期,则含有前一个月的按钮
//		if(firstDateTimestamp > currentTimestamp && firstDateTimestamp > minStartDateTimestamp) {
//			str.append("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
//					+"<tr class=\"line1\">"
//					+"<td height=\"34\"><a href=\"javascript:;\" onclick=\"lastMonth();\" class=\"check_more4\"><i class=\"fa fa-chevron-left\"></i></a></td>"
//					+"<td colspan=\"5\">"+ Tools.getEnglishMonth(omonth) +","+ oyear +"</td>"  
//					+"<td><a href=\"javascript:;\" onclick=\"nextMonth();\" class=\"check_more4\"><i class=\"fa fa-chevron-right\"></i></a></td>"
//				  +"</tr>");
//		}else{
//			str.append("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
//					+"<tr class=\"line1\">"
//				    +"<td height=\"34\"></td>"
//					+"<td colspan=\"5\">"+ Tools.getEnglishMonth(omonth) +","+ oyear +"</td>"  
//					+"<td><a href=\"javascript:;\" onclick=\"nextMonth();\" class=\"check_more4\"><i class=\"fa fa-chevron-right\"></i></a></td>"
//				  +"</tr>");			
//		}
//		str.append("<tr class=\"line2\">"
//				   + "<td height=\"28\" width=\"14.3%\">Su</td>"
//				   + "<td width=\"14.3%\">Mo</td>"
//				   + "<td width=\"14.3%\">Tu</td>"
//				   + "<td width=\"14.3%\">We</td>"
//				   + "<td width=\"14.3%\">Th</td>"
//				   + "<td width=\"14.3%\">Fr</td>"
//				   + "<td width=\"14.3%\" class=\"td7\">Sa</td>"
//				   + "</tr>");
//		
//		//为当前月份的日期计数
//		int count = 0;
//		for (int i = 0; i < 6; i++) {
//			str.append("<tr>");
//			for (int j = 0; j < 7; j++) {
//				/**
//				 * 最后的空行
//				 */
//				if(daysArray[i][j] == 0){
//					continue;
//				/**
//				 * 	上个月
//				 */
//				}else if (i < 1 && daysArray[i][j] > 20) {
//					str.append("<td class=\"\">"
//							+ "<div class=\" pre_month_date\" onclick=\"\">"
//							+ "<div class=\"ac\">"+ daysArray[i][j] +"</div>"
//							+ "<div class=\"ac\"></div>" 
//							+ "<div class=\"clear\"></div>"
//							+ "</div>"
//							+ "</td>");
//					continue;
//				/**
//				 * 	本月
//				 */
//				} else if(count < maxDaysInMonth){
//					count++;
//					
//					String eachDayDateString = oyear + "-" + Tools.turnOneNumberToDoubleString(omonth) + "-" + Tools.turnOneNumberToDoubleString(daysArray[i][j]);
//					int eachDayTimestamp = Tools.getTimestemp(eachDayDateString);
//					
//					ShowInfo[] showInfoArray = new ShowInfo[tourDateList.size()];
//					for (int tourDateIndex=0; tourDateIndex<tourDateList.size(); tourDateIndex++) {
//						Tourdate tourdate = tourDateList.get(tourDateIndex);
//						if (eachDayTimestamp >= tourdate.getStartdate() && eachDayTimestamp <= tourdate.getEnddate() && eachDayTimestamp >= (currentTimestamp + 3600 * 24 * tourdate.getSealGroupDate())) {
//							//如果是单天和全周出发
//							if (tourdate.getDateweek().equals("0") || tourdate.getDateweek().equals("8")) {
//							    //如果有隔天发团
//								if(tourdate.getDays() > 0){
//									//如果当前日期与起始日期间隔的天数是间隔发团天数的倍数,显示为出团日期
//									int intervalTime = (eachDayTimestamp - tourdate.getStartdate())/3600/24;
//									if(intervalTime % tourdate.getDays() == 0){
//										showInfoArray[tourDateIndex] = fillDate(eachDayDateString,days,tourdate,code);
//									}
//								}else{
//									showInfoArray[tourDateIndex] = fillDate(eachDayDateString,days,tourdate,code);
//								}
//							//如果是确定周几发团	
//							}else{
//								String dateweek = tourdate.getDateweek();
//								if (dateweek.indexOf("7") != -1) {
//									dateweek =	dateweek.replace("7", "0");
//								}
//								if (dateweek.indexOf(j+"") != -1) {
//									//如果有隔天发团
//									if(tourdate.getDays() > 0){
//										//当当前日期与起始日期所间隔的周数是间隔发团周数的整数倍时显示为出团日期
//										int intervalTime = (eachDayTimestamp - tourdate.getStartdate())/3600/24;
//										int intervalWeek = intervalTime/7;
//										int actualIntervalWeek = tourdate.getDays()/7;
//										if(intervalWeek%actualIntervalWeek == 0){
//											showInfoArray[tourDateIndex] = fillDate(eachDayDateString,days,tourdate,code);
//										}
//									}else{
//										showInfoArray[tourDateIndex] = fillDate(eachDayDateString,days,tourdate,code);
//									}
//								}
//							}
//						}
//					}
//					/**
//					 * 遍历所有tourdate信息，间隔时间最短的有效
//					 */
//					ShowInfo validShowInfo = null;
//					for(ShowInfo showInfo : showInfoArray){
//						if(showInfo != null){
//							if(validShowInfo == null){
//								validShowInfo = showInfo;
//							}else{
//								if(showInfo.dateInterval < validShowInfo.dateInterval){
//									validShowInfo = showInfo;
//								}
//							}
//						}
//					}
//					str.append("<td>");
//                    /**
//                     * 没有出团的日期
//                     */
//					if(validShowInfo == null){
//						if(j == 0 || j == 6){
//							str.append("<div class=\"weekend pre_month_date\">"
//									+ "<div class=\"ac\">"+ daysArray[i][j] +"</div>"
//									+ "<div class=\"ac\"></div>"
//									+ "<div class=\"clear\"></div>"
//									+ "</div>");	
//							
//						}else{
//							str.append("<div class=\"pre_month_date\">"
//									+ "<div class=\"ac\">"+ daysArray[i][j] +"</div>"
//									+ "<div class=\"ac\"></div>"
//									+ "<div class=\"clear\"></div>"
//									+ "</div>");
//						}
//		
//					/**
//					 * 	有出团的日期
//					 */
//					}else{
//						str.append(validShowInfo.outerDivHtml
//						+ "<div class=\"ac\">"+ daysArray[i][j] +"</div>"
//						+ "<div class=\"ac\">"
//						+ validShowInfo.priceHtml 
//						+ "</div>"
//						+ "<div class=\"ac\">"
//						+ validShowInfo.productRemainderNumber
//						+ "</div>"						
//						+ "<div class=\"clear\"></div>"
//					    + "</div>");	
//					}
//					str.append("</td>");
//				/**
//				 * 下个月	
//				 */
//				}else{
//					str.append("<td class=\"\">"
//						    + "<div class=\" pre_month_date\" onclick=\"\">"
//							+ "<div class=\"ac\">"+ daysArray[i][j] +"</div>"
//						    + "<div class=\"ac\"></div>" 
//						    + "<div class=\"clear\"></div>"
//							+ "</div>"
//						    + "</td>");
//				}
//			}
//			str.append("</tr>");
//		}
//		str.append("</table>");
//		return str.toString();
//	}
	
	/**
	 * 记录每条tourdate所对应的时间间隔和日历展示时
	 * 
	 * @author Jared
	 *
	 * Jul 30, 2015
	 */
	private class ShowInfo{
		int dateInterval;
		String dateweek;
		String unitHtml;
		
		public ShowInfo(int dateInterval,String dateweek,String unitHtml) {
			super();
			this.dateInterval = dateInterval;
			this.dateweek = dateweek;
			this.unitHtml = unitHtml;
		}
	}
	
	/**
	 * 针对线路详情页面的日历做填充
	 * 
	 * @param eachDayDateString
	 * @param tourdate
	 * @param showInfo
	 */
	private ShowInfo fillDate(String eachDayDateString,Tourdate tourdate,String code){
		String tourPriceId = tourdate.getTourprice().getId();
		BigDecimal singleRoomPrice = tourdate.getTourprice().getSingleroomprice();
		BigDecimal sellingPrice = tourdate.getTourprice().getSellingprice();
		BigDecimal childPrice = tourdate.getTourprice().getChildPrice();
		BigDecimal babyPrice = tourdate.getTourprice().getBabyPrice();
		
		StringBuffer unitHtml = new StringBuffer();
		unitHtml.append("<td>");
		//如果是热推
		if(tourdate.getIshot()){
			unitHtml.append("<div class=\"today_hot\"><img class=\"hotImg\"></div>");
		}
		
		//如果库存大于0
		if(tourdate.getRemainnum() > 0){
			//
			if(tourdate.getIscall()){
				unitHtml.append("<div class=\"people-choose-date-list isDeparture after_today_date\">");
				unitHtml.append("<div class=\"ac\">");
				unitHtml.append("<span class=\"no_soldout\">"+ eachDayDateString +"</span>");
				unitHtml.append("</div>");
				unitHtml.append("<div class=\"ac\">");
				unitHtml.append("<span class=\"no_soldout\">Please Call</span>");
				unitHtml.append("</div>");
				unitHtml.append("<div class=\"ac\">");
				unitHtml.append("<span class=\"no_soldout\">"+ tourdate.getCallNumber() +"</span>");
				unitHtml.append("</div>");
				unitHtml.append("<div class=\"ac\">");
				unitHtml.append("<span class=\"no_soldout\"></span>");
				unitHtml.append("</div>");
				unitHtml.append("<div class=\"clear\"></div>");
				unitHtml.append("</div>");				
			}else{
				if(tourdate.getTourprice().getSellingprice().intValue() != 0){
					unitHtml.append("<div class=\"people-choose-date-list isDeparture after_today_date\" onclick=\"chooseDate('"+ eachDayDateString + "',"+ singleRoomPrice + "," + sellingPrice + "," + childPrice  + "," + babyPrice + ",'" + tourdate.getId() + "','"+ tourPriceId + "');\">");
					unitHtml.append("<div class=\"ac\">");
					unitHtml.append("<span class=\"no_soldout\">"+ eachDayDateString +"</span>");
					unitHtml.append("</div>");
					unitHtml.append("<div class=\"ac\">");
					unitHtml.append("<span class=\"no_soldout\">"+ code + sellingPrice.intValue() +"</span>");
					unitHtml.append("</div>");
					unitHtml.append("<div class=\"ac\">");
					unitHtml.append("<span class=\"no_soldout\">Available "+ tourdate.getRemainnum() +"</span>");
					unitHtml.append("</div>");
					unitHtml.append("<div class=\"clear\"></div>");
					unitHtml.append("</div>");
				}else{
					unitHtml.append("<div class=\"people-choose-date-list isDeparture after_today_date\">");
					unitHtml.append("<div class=\"ac\">");
					unitHtml.append("<span class=\"no_soldout\">"+ eachDayDateString +"</span>");
					unitHtml.append("</div>");
					unitHtml.append("<div class=\"ac\">");
					unitHtml.append("<span class=\"no_soldout\">N/A</span>");
					unitHtml.append("</div>");
					unitHtml.append("<div class=\"ac\">");
					unitHtml.append("<span class=\"no_soldout\"></span>");
					unitHtml.append("</div>");
					unitHtml.append("<div class=\"clear\"></div>");
					unitHtml.append("</div>");					
				}
			}
		}else{
			unitHtml.append("<div class=\"people-choose-date-list isDeparture after_today_date\">");
			unitHtml.append("<div class=\"ac\">");
			unitHtml.append("<span class=\"no_soldout\">"+ eachDayDateString +"</span>");
			unitHtml.append("</div>");
			unitHtml.append("<div class=\"ac\">");
			unitHtml.append("<span class=\"no_soldout\">"+ code + sellingPrice.intValue() + "</span>");
			unitHtml.append("</div>");
			unitHtml.append("<div class=\"ac\">");
			unitHtml.append("<span class=\"no_soldout\">Sold Out</span>");
			unitHtml.append("</div>");
			unitHtml.append("<div class=\"clear\"></div>");
			unitHtml.append("</div>");		
		}
		unitHtml.append("</td>");
		return new ShowInfo(tourdate.getEnddate() - tourdate.getStartdate(),tourdate.getDateweek(),unitHtml.toString());
	}
	
	
	/**
	 * 针对购物车页面日历做填充
	 * 
	 * @return
	 */
//	private ShowInfo fillDateInSingle(String eachDayDateString,Tourdate tourDate,String code){
//		//产品余量和价格的显示
//		String productRemainderNumberAndPriceHtml = "";
//		//每个单元最外围的DIV,可添加事件
//		String outerDivHtml = "<div class=\" after_today_date\">";
//		
//		//如果销售价格不为0时
//		if(tourDate.getTourprice().getSellingprice().intValue() != 0){
//			if (tourDate.getRemainnum() > 6) {
////				productRemainderNumberAndPriceHtml = "<span class=\"enough\">充足</span>"+"<span class=\"no_soldout\">" + code + tourDate.getTourprice().getSellingprice() + "起</span>"; 
//				outerDivHtml = "<div class=\" after_today_date\" onclick=\"selectStartDate('"+ eachDayDateString +"',"+ tourDate.getTourprice().getSellingprice() +","+ tourDate.getTourprice().getSingleroomprice() + "," + tourDate.getTourprice().getNobedprice() + "," + tourDate.getTourprice().getThreesellingprice() + "," + tourDate.getTourprice().getFoursellingprice() + "," + tourDate.getTourprice().getExtrabedprice() + "," + tourDate.getTourprice().getBabyPrice() + "," + tourDate.getTourprice().getChildPrice() + ",'"+ tourDate.getId() + "','" + tourDate.getTourprice().getId() + "',this)\">";
//			}else if(tourDate.getRemainnum() == 0){
////				productRemainderNumberAndPriceHtml = "<span class=\"soldout_residue\">已售罄</span>"; 
//			}else{
////				productRemainderNumberAndPriceHtml = "<span class=\"residue\">余"+tourDate.getRemainnum()+"位</span>"+"<span class=\"no_soldout\">" + code + tourDate.getTourprice().getSellingprice() + "起</span>"; 
//				outerDivHtml = "<div class=\" after_today_date\" onclick=\"selectStartDate('"+ eachDayDateString +"',"+ tourDate.getTourprice().getSellingprice()  +","+ tourDate.getTourprice().getSingleroomprice() + "," + tourDate.getTourprice().getNobedprice() + "," + tourDate.getTourprice().getThreesellingprice() + "," + tourDate.getTourprice().getFoursellingprice() + "," + tourDate.getTourprice().getExtrabedprice() + "," + tourDate.getTourprice().getBabyPrice() + "," + tourDate.getTourprice().getChildPrice() + ",'" + tourDate.getId() + "','" + tourDate.getTourprice().getId() + "',this)\">";
//			}		
//		}else{
//			if (tourDate.getRemainnum() > 6) {
////				productRemainderNumberAndPriceHtml = "<span class=\"enough\">充足</span>"+"<span class=\"no_soldout\">Please Call</span>"; 
//				outerDivHtml = "<div class=\" after_today_date\">";
//			}else if(tourDate.getRemainnum() == 0){
////				productRemainderNumberAndPriceHtml = "<span class=\"soldout_residue\">已售罄</span>"; 
//			}else{
////				productRemainderNumberAndPriceHtml = "<span class=\"residue\">余"+tourDate.getRemainnum()+"位</span>"+"<span class=\"no_soldout\">Please Call</span>"; 
//				outerDivHtml = "<div class=\" after_today_date\">";
//			}	
//		}
//		return new ShowInfo(tourDate.getEnddate() - tourDate.getStartdate(),productRemainderNumberAndPriceHtml,outerDivHtml);
//	}
	
	//根据产品ID,DepartureId,costnumber,第一个日期获取日期模版
	public String getDepartureDates(String tourlineId,String departureId,Date firstDay,int dateNumber){
		//给循环calendar设置第一天
		Calendar calendar = Calendar.getInstance();
	    int cyear = calendar.get(Calendar.YEAR);
	    int cmonth = calendar.get(Calendar.MONTH);
	    int cdate = calendar.get(Calendar.DAY_OF_MONTH);
	    calendar.set(cyear, cmonth, cdate, 0, 0, 0);
	    Date currentDay = calendar.getTime();
	    
		if(firstDay != null){
			calendar.setTime(firstDay);
		}else{
			firstDay = currentDay;
		}
		
		//根据产品,出发地,运营中心,初始时间来加载tourdate
		Tourline tourline = tourlineMapper.selectByPrimaryKey(tourlineId);
		
		List<Tourdate> tourDateList = null;
		if(!StringUtils.isEmpty(departureId)){
			tourDateList = tourdateMapper.findByProductIdAndCostnumberAndDepartureId(tourline.getProductid(),departureId,tourline.getCostnumber(),firstDay.getTime()/1000);
		}else{
			tourDateList = tourdateMapper.findByProductIdAndCostnumber(tourline.getProductid(), tourline.getCostnumber(), firstDay.getTime()/1000);
		}
		
		//如果toudate的条数为0,则返回
		if(tourDateList.size() == 0){
			return "";
		}
		
		//判断是否参与促销活动，并修改售价	
		for (Tourdate tourdate : tourDateList) {
			tourdate.setTourprice(tourlineService.changeTourPrice(tourdate.getTourprice(), tourlineId, tourline.getCostnumber()));
		}
		Cost cost = costMapper.selectByPrimaryKey(tourline.getCostnumber());
		
		//获取每行的日期数
		int column = 0;
		if(dateNumber == 21){
			column = 7;
		}else if(dateNumber == 9){
			column = 3;
		}
		
		StringBuffer showHtml = new StringBuffer();
		showHtml.append("<table cellpadding=\"0\" cellspacing=\"0\" class=\"people-choose-date-table\">");
		showHtml.append("<tr>");
		
		//已生成的日期数量
		int generalDateNumber = 0;
		
		//是否有下一页
		boolean isHasNext = true;
		
		String beginDateString = null;
		String lastDateString = null;
		while(generalDateNumber < dateNumber){

			/**
			 * 获取每一天的时间戳以及周标记
			 */
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			long eachDayTimestamp = calendar.getTime().getTime()/1000;
			int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
			
			//每一天的日期String
			String eachDayDateString = Tools.turnOneNumberToDoubleString(month) + "/" + Tools.turnOneNumberToDoubleString(day) + "/" + year;
			
			
			//日历加一天
			calendar.add(Calendar.DATE, 1); 
			
			/**
			 * 生成每一天出发日期的所有信息
			 */
			ShowInfo[] showInfoArray = new ShowInfo[tourDateList.size()];
			
			//最大终止日期
			long maxEndDate = tourDateList.get(0).getEnddate();
			for (int tourDateIndex=0; tourDateIndex<tourDateList.size(); tourDateIndex++) {
				Tourdate tourdate = tourDateList.get(tourDateIndex);
				
				if(maxEndDate < tourdate.getEnddate()){
					maxEndDate = tourdate.getEnddate();
				}
				
				//该日期时间戳大于tourdate的起始日期时间戳，小于tourdate的终止日期时间戳并且该日期在当前日期延期tourdate.getSealGroupDate()天后显示
				if (eachDayTimestamp >= tourdate.getStartdate() && eachDayTimestamp <= tourdate.getEnddate() && eachDayTimestamp >= (currentDay.getTime()/1000 + 3600 * 24 * tourdate.getSealGroupDate())) {
					
					//如果是单天发团或全周发团
					if (tourdate.getDateweek().equals("0") || tourdate.getDateweek().equals("8")) {
                        //如果间隔日期大于0,则间隔显示
						if(tourdate.getDays() > 0){
							int intervalTime = (int) ((eachDayTimestamp - tourdate.getStartdate())/3600/24);
							if(intervalTime % tourdate.getDays() == 0){
								showInfoArray[tourDateIndex] = fillDate(eachDayDateString,tourdate,cost.getSign());
							}
						}else{
							showInfoArray[tourDateIndex] = fillDate(eachDayDateString,tourdate,cost.getSign());
						}
					//如果是周一到周末发团
					}else{
						String dateweek = tourdate.getDateweek();
						if(weekIndex == 1){
							weekIndex = 7;
						}else{
							weekIndex = weekIndex - 1;
						}
						if (dateweek.indexOf(weekIndex+"") != -1) {
							//如果间隔日期大于0,间隔显示
							if(tourdate.getDays() > 0){
								int intervalTime = (int) ((eachDayTimestamp - tourdate.getStartdate())/3600/24);
								int totalWeek = intervalTime/7;
								int actualIntervalWeek = tourdate.getDays()/7;
								
								if(totalWeek%actualIntervalWeek == 0){
									showInfoArray[tourDateIndex] = fillDate(eachDayDateString,tourdate,cost.getSign());
								}
							}else{
								showInfoArray[tourDateIndex] = fillDate(eachDayDateString,tourdate,cost.getSign());
							}
						}
					}
				}
			}
			/**
			 * 遍历所有tourdate信息，间隔时间最短的有效
			 */
			ShowInfo validShowInfo = null;
			for(ShowInfo showInfo : showInfoArray){
				if(showInfo != null){
					if(validShowInfo == null){
						validShowInfo = showInfo;
					}else{
						validShowInfo = compareDate(validShowInfo,showInfo);
					}
				}
			}
			//当换行时
			if(generalDateNumber != 0 && generalDateNumber % column == 0){
				showHtml.append("</tr><tr>");
			}
			if(validShowInfo != null){
				if(beginDateString == null){
					beginDateString = eachDayDateString;
				}
				//给生成天数加1
				generalDateNumber++;
				showHtml.append(validShowInfo.unitHtml);
				lastDateString = eachDayDateString;
			}
			if(eachDayTimestamp >= maxEndDate){
				isHasNext = false;
				break;
			}
		}
		
		//如果没有下一页
		if(!isHasNext){
			showHtml.insert(7,"isHasNext=\"0\" ");
		}
		showHtml.insert(7,"beginDate=\""+ beginDateString + "\" ");
		showHtml.insert(7,"lastDate=\""+ lastDateString + "\" ");
		showHtml.append("</tr>");
		showHtml.append("</table>");
		showHtml.append("<div class=\"clear\"></div>");
		return showHtml.toString();
	}
	
	/**
	 * 比较不同的tourdate,根据规则取出有效的
	 */
	private ShowInfo compareDate(ShowInfo validShowInfo,ShowInfo compareShowhowInfo){
		//根据间隔日期相比较,取间隔日期最小
		if(validShowInfo.dateInterval > compareShowhowInfo.dateInterval){
			return compareShowhowInfo;
		}else if(validShowInfo.dateInterval == compareShowhowInfo.dateInterval){
			if("0".equals(validShowInfo.dateweek)){
				return compareShowhowInfo;
			}
			if("0".equals(compareShowhowInfo.dateweek)){
				return validShowInfo;
			}
		}
		return validShowInfo;
	}
	
	/**
	 * 比较不同的tourdate,根据规则取出有效的
	 */
	private Tourdate compareTourdate(Tourdate validTourdate,Tourdate compareTourdate){
		int validInterval = validTourdate.getEnddate() - validTourdate.getStartdate();
		int compareInterval = compareTourdate.getEnddate() - compareTourdate.getStartdate();
		
		//根据间隔日期相比较,取间隔日期最小
		if(validInterval > compareInterval){
			return compareTourdate;
		}else if(validInterval == compareInterval){
			if("0".equals(validTourdate.getDateweek())){
				return compareTourdate;
			}
			if("0".equals(compareTourdate.getDateweek())){
				return validTourdate;
			}
		}
		return validTourdate;
	}		
	
	public List<Tourdate> getDepartureDatesWithCa(String tourlineId,String departureId,Date firstDay) throws IOException, ClassNotFoundException{
		//给循环calendar设置第一天
		Calendar calendar = Calendar.getInstance();
	    int cyear = calendar.get(Calendar.YEAR);
	    int cmonth = calendar.get(Calendar.MONTH);
	    int cdate = calendar.get(Calendar.DAY_OF_MONTH);
	    calendar.set(cyear, cmonth, cdate, 0, 0, 0);
	    Date currentDay = calendar.getTime();
		
		//日历加一天
		calendar.add(Calendar.DATE, 1); 
		if(firstDay != null){
			calendar.setTime(firstDay);
		}else{
			firstDay = currentDay;
		}
		
		//根据产品,出发地,运营中心,初始时间来加载tourdate
		Tourline tourline = tourlineMapper.selectByPrimaryKey(tourlineId);
		
		List<Tourdate> tourDateList = null;
		if(!StringUtils.isEmpty(departureId)){
			tourDateList = tourdateMapper.findByProductIdAndCostnumberAndDepartureId(tourline.getProductid(),departureId,tourline.getCostnumber(),firstDay.getTime()/1000);
		}else{
			tourDateList = tourdateMapper.findByProductIdAndCostnumber(tourline.getProductid(), tourline.getCostnumber(), firstDay.getTime()/1000);
		}
		
		//如果toudate的条数为0,则返回
		if(tourDateList.size() == 0){
			return null;
		}
		
		//判断是否参与促销活动，并修改售价	
		for (Tourdate tourdate : tourDateList) {
			tourdate.setTourprice(tourlineService.changeTourPrice(tourdate.getTourprice(), tourlineId, tourline.getCostnumber()));
		}
		
		List<Tourdate> resultTourdate = new ArrayList<Tourdate>(); 
		
		while(true){
			/**
			 * 获取每一天的时间戳以及周标记
			 */
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			long eachDayTimestamp = calendar.getTime().getTime()/1000;
			int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
			
			//每一天的日期String
			String eachDayDateString = year + "-" + Tools.turnOneNumberToDoubleString(month) + "-" + Tools.turnOneNumberToDoubleString(day);
			//日历加一天
			calendar.add(Calendar.DATE, 1); 
			
			/**
			 * 生成每一天出发日期的所有信息
			 */
			List<Tourdate> tourdateList = new ArrayList<Tourdate>();
			
			//最大终止日期
			long maxEndDate = tourDateList.get(0).getEnddate();
			for (int tourDateIndex=0; tourDateIndex<tourDateList.size(); tourDateIndex++) {
				Tourdate tourdate = tourDateList.get(tourDateIndex);
				
				if(maxEndDate < tourdate.getEnddate()){
					maxEndDate = tourdate.getEnddate();
				}
				
				//该日期时间戳大于tourdate的起始日期时间戳，小于tourdate的终止日期时间戳并且该日期在当前日期延期tourdate.getSealGroupDate()天后显示
				if (eachDayTimestamp >= tourdate.getStartdate() && eachDayTimestamp <= tourdate.getEnddate() && eachDayTimestamp >= (currentDay.getTime()/1000 + 3600 * 24 * tourdate.getSealGroupDate())) {
					
					//如果是单天发团或全周发团
					if (tourdate.getDateweek().equals("0") || tourdate.getDateweek().equals("8")) {
                        //如果间隔日期大于0,则间隔显示
						if(tourdate.getDays() > 0){
							int intervalTime = (int) ((eachDayTimestamp - tourdate.getStartdate())/3600/24);
							if(intervalTime % tourdate.getDays() == 0){
								tourdate.setRemark(eachDayDateString);
								tourdateList.add(Tools.deepCopyObject(tourdate));
							}
						}else{
							tourdate.setRemark(eachDayDateString);
							tourdateList.add(Tools.deepCopyObject(tourdate));
						}
					//如果是周一到周末发团
					}else{
						String dateweek = tourdate.getDateweek();
						if(weekIndex == 1){
							weekIndex = 7;
						}else{
							weekIndex = weekIndex - 1;
						}
						if (dateweek.indexOf(weekIndex+"") != -1) {
							//如果间隔日期大于0,间隔显示
							if(tourdate.getDays() > 0){
								int intervalTime = (int) ((eachDayTimestamp - tourdate.getStartdate())/3600/24);
								int totalWeek = intervalTime/7;
								int actualIntervalWeek = tourdate.getDays()/7;
								
								if(totalWeek%actualIntervalWeek == 0){
									tourdate.setRemark(eachDayDateString);
									tourdateList.add(Tools.deepCopyObject(tourdate));
								}
							}else{
								tourdate.setRemark(eachDayDateString);
								tourdateList.add(Tools.deepCopyObject(tourdate));
							}
						}
					}
				}
			}
			/**
			 * 遍历所有tourdate信息，间隔时间最短的有效
			 */
			Tourdate validTourdate = null;
			if(tourdateList.size() > 0){
				for(Tourdate tourdate : tourdateList){
					if(validTourdate == null){
						validTourdate = tourdate;
					}else{
						validTourdate = compareTourdate(validTourdate,tourdate);
					}
				}
			}
			if(validTourdate != null){
				resultTourdate.add(validTourdate);
			}
			if(eachDayTimestamp >= maxEndDate){
				break;
			}
		}
		return resultTourdate;
	}
}
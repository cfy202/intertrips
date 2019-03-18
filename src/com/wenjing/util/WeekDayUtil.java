package com.wenjing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-5-7 下午5:28:41
 * 类说明 :获取某一时间段特定星期几的日期 
 */
/**
 * 获取某一时间段特定星期几的日期
 * @author finder.zhou
 */
public class WeekDayUtil {
	/**
	 * 获取某一时间段特定星期几的日期
	 * @param dateFrom 开始时间
	 * @param dateEnd 结束时间
	 * @param weekDays 星期
	 * @return 返回时间数组
	 */
    public static String[] getDates(String dateFrom, String dateEnd, String weekDays) {
        long time = 1l;
        long perDayMilSec = 24 * 60 * 60 * 1000;
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //需要查询的星期系数
        String strWeekNumber = weekForNum(weekDays);
        try {
			dateFrom = sdf.format(sdf.parse(dateFrom).getTime() - perDayMilSec);
			while (true) {
					time = sdf.parse(dateFrom).getTime();
					time = time + perDayMilSec;
					Date date = new Date(time);
					dateFrom = sdf.format(date);
					if (dateFrom.compareTo(dateEnd) <= 0) {
						//查询的某一时间的星期系数
						Integer weekDay = dayForWeek(date);                    
						//判断当期日期的星期系数是否是需要查询的
						if (strWeekNumber.indexOf(weekDay.toString())!=-1) {
							System.out.println(dateFrom);
							dateList.add(dateFrom);
						}
					} else {
						break;
					}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        String[] dateArray = new String[dateList.size()];
        dateList.toArray(dateArray);
        return dateArray;
    }

    //等到当期时间的周系数。星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer dayForWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 得到对应星期的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
     * @param weekDays 星期格式  星期一|星期二
     */
    public static String weekForNum(String weekDays){
    	//返回结果为组合的星期系数
    	String weekNumber = "";
    	//解析传入的星期
    	if(weekDays.indexOf(",")!=-1){//多个星期数
    		String []strWeeks = weekDays.split(",");
    		for(int i=0;i<strWeeks.length;i++){
    			weekNumber = weekNumber + "" + getWeekNum(strWeeks[i]).toString();
    		}
    	}else{//一个星期数
    		weekNumber = getWeekNum(weekDays).toString();
    	}
    	
    	return weekNumber;
    	
    }
    
    //将星期转换为对应的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer getWeekNum(String strWeek){
    	Integer number = 1;//默认为星期日
    	if("星期日".equals(strWeek)){
    		number = 1;
    	}else if("星期一".equals(strWeek)){
    		number = 2;
    	}else if("星期二".equals(strWeek)){
    		number = 3;
    	}else if("星期三".equals(strWeek)){
    		number = 4;
    	}else if("星期四".equals(strWeek)){
    		number = 5;
    	}else if("星期五".equals(strWeek)){
    		number = 6;
    	}else if("星期六".equals(strWeek)){
    		number = 7;
    	}else{
    		number = 1;
    	}
    	return number;
    }
    
    /**
     * 获取某段时间内的所有日期
     * @param start
     * @param end
     * @return
     */
    public static List<String> findDates(String start, String end) {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	Date dBegin = null;
    	Date dEnd = null;
		try {
			dBegin = sdf.parse(start);
			dEnd = sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        List<String> lDate = new ArrayList<String>();  
        lDate.add(start);  
        Calendar calBegin = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(dBegin);  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(dEnd);  
        // 测试此日期是否在指定日期之后    
        while (dEnd.after(calBegin.getTime())) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
            lDate.add(sdf.format(calBegin.getTime()));  
        }  
        return lDate;  
    }  
    
    public static void main(String[] args) {
//    	System.out.println(Tools.getTime(1438185600)+"============");
//    	System.out.println(Tools.getTime(1438272000)+"============");
//    	getDates(Tools.getTime(1438185600), Tools.getTime(1438272000),"星期六");
    	List<String> sssList = findDates("2015-09-11", "2016-09-11");
    	for (String string : sssList) {
//			System.out.println(string+"============");
		}
    	
    	
//		String dateweekString = WeekDayUtil.getStringWeek("5");
//		String[] dateweekArray = dateweekString.split(",");
//		for (int i = 0; i < dateweekArray.length; i++) {
//			String[] dateList = WeekDayUtil.getDates(Tools.getTime(1438963200), Tools.getTime(1483113600), dateweekArray[i]);
//			for (int j = 0; j < dateList.length; j+=14/7) {
//				System.out.println(dateList[j]+dateweekString);
//			}
//		}
    	
    	List<String> dateList = WeekDayUtil.findDates(Tools.getTime(1443024000), Tools.getTime(1474646400));
		for (int i = 0; i < dateList.size(); i+=14) {
			System.out.println(dateList.get(i));
		}
		
    }
    
    
    
    public static String getStringWeek(String intWeek){
    	if (intWeek.indexOf("7") != -1) {
    		intWeek =	intWeek.replace("7", "星期日");
		}
    	if (intWeek.indexOf("1") != -1) {
    		intWeek =	intWeek.replace("1", "星期一");
		}
    	if (intWeek.indexOf("2") != -1) {
    		intWeek =	intWeek.replace("2", "星期二");
		}
    	if (intWeek.indexOf("3") != -1) {
    		intWeek =	intWeek.replace("3", "星期三");
		}
    	if (intWeek.indexOf("4") != -1) {
    		intWeek =	intWeek.replace("4", "星期四");
		}
    	if (intWeek.indexOf("5") != -1) {
    		intWeek =	intWeek.replace("5", "星期五");
		}
    	if (intWeek.indexOf("6") != -1) {
    		intWeek =	intWeek.replace("6", "星期六");
		}
    	return intWeek;
    }
    
    /**
     * 找出距离当前日期最近的日期
     * @param dateSet
     * @return
     * @throws ParseException 
     */
    public static String getCloserDate(Set<String> dateSet) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	Date currentDate = new Date();
    	
    	String dateString = "";
    	long distance = 0L;
    	
    	for(Iterator<String> iterator = dateSet.iterator();iterator.hasNext();){
    		String eachDateString = iterator.next();
    		Date eachDate = sdf.parse(eachDateString);
    		long currentTime = currentDate.getTime();
    		long eachDateTime = eachDate.getTime();
    		
    		//日期大于当前时间
    		if(eachDateTime >= currentTime){
    			if(distance == 0L){
    				distance = eachDateTime - currentTime;
    				dateString = eachDateString;
    			}else{
    				if(eachDateTime - currentTime < distance){
    					distance = eachDateTime - currentTime;
    					dateString = eachDateString;
    				}
    			}
    		}
    	}
    	return dateString;
    }
}

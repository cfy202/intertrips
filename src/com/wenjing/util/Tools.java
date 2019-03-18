package com.wenjing.util;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.spreada.utils.chinese.ZHConverter;
import com.wenjing.dao.CostMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.CommentsToBlog;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Member;
import com.wenjing.entity.TourPassenger;
import com.wenjing.entity.Tourprice;
import com.wenjing.vo.BookTourVO;
import com.wenjing.vo.RoomInfo;

/**
 * 
 * @author Sevens
 * 
 */
public class Tools {

	private static File file;

	static {
		URL uri = Tools.class.getResource("/");
		file = null;
		try {
			file = new File(uri.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pathname = file.getAbsolutePath() + "/identity.properties";
		file = new File(pathname);

		if (!file.exists()) {
			try {
				file.createNewFile();
				// д���ʼ������
				PrintStream out = new PrintStream(file);
				out.println("num=0");
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static long buffer = 20;
	private static int now = 1;
	private static int max = 1;

	/**
	 * 主键生成机制
	 * 
	 * @return
	 */
	public synchronized static int getNum() {

		if (now >= max) {
			Properties p = new Properties();
			try {
				p.load(new FileInputStream(file));
				now = Integer.parseInt(p.getProperty("num"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			max += (buffer + now);
			try {
				PrintStream out = new PrintStream(file);
				;// д��Դ�ļ�
				out.println("num=" + max);
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		now++;
		return now;
	}

	/**
	 * 
	 * @return
	 */
	public static String getDtime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 获取系统当前时间转换成24小时制 bowden
	 * 
	 * @return
	 */
	public static String getHHtime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @return
	 */
	public static String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static int getDtimestemp(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int timestemp = 0;
		Date date;
		try {
			date = format.parse(time);
			long l = date.getTime();
			String str = String.valueOf(l);

			String tme = str.substring(0, str.length() - 3);

			timestemp = Integer.valueOf(tme);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestemp;
	}

	/**
	 * 获取时间戳
	 * 
	 * @param time
	 * @return
	 * 
	 */
	public static int getTimestemp(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int timestemp = 0;
		Date date;
		try {
			date = format.parse(time);
			long l = date.getTime();
			String str = String.valueOf(l);

			String tme = str.substring(0, str.length() - 3);

			timestemp = Integer.valueOf(tme);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestemp;
	}

	/**
	 * 获取时间戳
	 * 
	 * @param time
	 * @return
	 * 
	 */
	public static int getTimestemp3(String time) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		int timestemp = 0;
		Date date;
		try {
			date = format.parse(time);
			long l = date.getTime();
			String str = String.valueOf(l);

			String tme = str.substring(0, str.length() - 3);

			timestemp = Integer.valueOf(tme);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestemp;
	}

	/**
	 * 根据顾客信息,费用用
	 * 
	 * @param roomInfoList
	 * @param childrenPassengerList
	 * @param tourPrice
	 * @param promotion
	 * @return
	 */
//	public static String getBasicPriceDetail(List<RoomInfo> roomInfoList, List<TourPassenger> childrenPassengerList, Tourprice tourPrice,Promotion promotion,String code){
//		StringBuffer showDetailString = new StringBuffer();
//		
//		BigDecimal sellingprice = tourPrice.getSellingprice();
//		BigDecimal threeprice = tourPrice.getThreesellingprice();
//		BigDecimal fourprice = tourPrice.getFoursellingprice();
//		BigDecimal singleroomprice = tourPrice.getSingleroomprice();
//		BigDecimal extraBedPrice = tourPrice.getExtrabedprice();
//		BigDecimal babyPrice = tourPrice.getBabyPrice();
//		BigDecimal childPrice = tourPrice.getChildPrice();
//		
//		StringTourPrice stringTourPrice = new StringTourPrice();
//		if(TourlineService.SALE.equals(promotion.getType())){
//			if(promotion.getDiscount() != null && !"".equals(promotion.getDiscount())){
//				BigDecimal discountBig = promotion.getDiscount();
//				stringTourPrice.setSellingprice(code + sellingprice.toString() + "x" + discountBig.toString());
//				stringTourPrice.setThreesellingprice(code + threeprice.toString() + "x" + discountBig.toString());
//				stringTourPrice.setFoursellingprice(code + fourprice.toString() + "x" + discountBig.toString());
//				stringTourPrice.setExtrabedprice(code + extraBedPrice.toString() + "x" + discountBig.toString());
//				stringTourPrice.setBabyPrice(code + babyPrice.toString() + "x" + discountBig.toString());
//				stringTourPrice.setChildPrice(code + childPrice.toString() + "x" + discountBig.toString());
//				stringTourPrice.setSinglePrice(code + sellingprice.add(singleroomprice) + "x" + discountBig.toString());
//			}else{
//				return showDetailString.toString();
//			}
//		}else if(TourlineService.MINUS.equals(promotion.getType())){
//			Integer reduce = promotion.getReduce();
//			if(reduce != null){
//				stringTourPrice.setSellingprice("(" + code + sellingprice.toString() + "-" + reduce.toString() + ")");
//				stringTourPrice.setThreesellingprice("(" + code + threeprice.toString() + "-" + reduce.toString() + ")");
//				stringTourPrice.setFoursellingprice("(" + code + fourprice.toString() + "-" + reduce.toString() + ")");
//				stringTourPrice.setExtrabedprice("(" + code + extraBedPrice.toString() + "-" + reduce.toString() + ")");
//				stringTourPrice.setBabyPrice(code + babyPrice.toString());
//				stringTourPrice.setChildPrice(code + childPrice.toString());
//				stringTourPrice.setSinglePrice("(" + code + sellingprice.add(singleroomprice).toString() + "-" + reduce.toString() + ")");
//			}else{
//				return showDetailString.toString();
//			}
//		}
//		Calendar now = Calendar.getInstance();
//		int currentYear = now.get(Calendar.YEAR);
//
//		for (int i = 0; i < roomInfoList.size(); i++) {
//			RoomInfo roomInfo = roomInfoList.get(i);
//			showDetailString.append("房间" + (i + 1) + ":成人" + roomInfo.getAdultNum() + "位,价格(");
//			if (roomInfo.getAdultNum() == 2) {
//				showDetailString.append(stringTourPrice.getSellingprice() + " x 2");
//			} else if (roomInfo.getAdultNum() == 1) {
//				showDetailString.append(stringTourPrice.getSinglePrice());
//			} else if (roomInfo.getAdultNum() == 3) {
//				showDetailString.append(stringTourPrice.getThreesellingprice() + " x 3");
//			} else if (roomInfo.getAdultNum() == 4) {
//			    showDetailString.append(stringTourPrice.getFoursellingprice() + " x 4");
// 			}
//			showDetailString.append(");<br/>");
//		}
//		if(childrenPassengerList != null && childrenPassengerList.size() != 0){
//			showDetailString.append("儿童:共" + childrenPassengerList.size() + "位,<br/>");
//			Calendar birthday = Calendar.getInstance();
//			if (childrenPassengerList != null) {
//				for (int i = 0; i < childrenPassengerList.size(); i++) {
//					showDetailString.append("儿童" + (i + 1));
//					if (childrenPassengerList.get(i).getBirthday() != null) {
//						birthday.setTime(childrenPassengerList.get(i).getBirthday());
//						int year = birthday.get(Calendar.YEAR);
//						int age = currentYear - year;
//						if (age >= 0 && age < 2) {
//							showDetailString.append(":0-2周岁" + stringTourPrice.getBabyPrice() + ";");
//						} else if (age >= 2 && age < 5) {
//							showDetailString.append(":2-5周岁," + stringTourPrice.getChildPrice() + ";");
//						} else if (age >= 5 && age < 11) {
//							showDetailString.append(":5-11周岁" + stringTourPrice.getExtrabedprice() + ";");
//						}
//					} else {
//						showDetailString.append(":默认," + stringTourPrice.getExtrabedprice() + ";");
//					}
//					showDetailString.append("<br/>");
//				}
//			}	
//		}
//		return showDetailString.toString();
//	}
	
	/**
	 * (年，月)转时间戳
	 * 
	 * @param time
	 *            xiejin
	 */
	public static int getTimestemp2(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		int timestemp = 0;
		Date date;
		try {
			date = format.parse(time);
			long l = date.getTime();
			String str = String.valueOf(l);

			String tme = str.substring(0, str.length() - 3);

			timestemp = Integer.valueOf(tme);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestemp;
	}

	/**
	 * 时间戳转 年-月
	 * 
	 * @param timestemp
	 * @return xiejin
	 */
	public static String getTime2(int timestemp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return format.format(new Date((timestemp) * 1000L));
	}

	/**
	 * 时间戳转 年-月-日
	 * 
	 * @param timestemp
	 * @return
	 */
	public static String getTime(int timestemp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return format.format(new Date((timestemp) * 1000L));
	}

	/**
	 * 时间戳转 年-月-日 时-分-秒
	 * 
	 * @param timestemp
	 * @return
	 */
	public static String getDtime(int timestemp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date((timestemp) * 1000L));
	}

	/**
	 * 时间戳转英文时间 如：Dec 27, 2013
	 * 
	 * @param getDate
	 * @return string
	 */
	public static String getDate(int timestemp) {
		SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy",
				Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return format.format(new Date((timestemp) * 1000L));
	}

	/**
	 * 时间戳转英文时间 如：Dec 27, 2013
	 * 
	 * @param getDate
	 * @return string
	 */
	public static String getEnglishShow(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy",
				Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return format.format(date);
	}
	/**
	 * 时间戳转英文时间 如：Dec 27
	 * 
	 * @param getDate
	 * @return string
	 */
	public static String getEtime(int timestemp) {
		SimpleDateFormat format = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return format.format(new Date((timestemp) * 1000L));
	}

	/**
	 * 时间戳转星期 xiejin
	 */
	public static String getWeek(int timestemp) {
		SimpleDateFormat format = new SimpleDateFormat("E");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return format.format(new Date((timestemp) * 1000L));
	}

	/**
	 * 英文时间转时间戳 xiejin
	 * 
	 * @param time
	 * @return
	 */
	public static int getTimestemp1(String time) {
		SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy",
				Locale.ENGLISH);
		int timestemp = 0;
		Date date;
		try {
			date = format.parse(time);
			long l = date.getTime();
			String str = String.valueOf(l);

			String tme = str.substring(0, str.length() - 3);

			timestemp = Integer.valueOf(tme);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestemp;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String objToString(Object o) {
		String rs = null;
		try {
			rs = JSONSerializer.toJSON(o).toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return rs;
	}

	/**
	 * 根据月份计算当月最大天数
	 * 
	 * @return
	 */
	public static int getMaxDays(int monthNumber, int oyear) {
		int maxDaysInMonth = 31;
		switch (monthNumber) {
		case 1:
			maxDaysInMonth = 31;
			break;
		case 2:
			if ((oyear % 4 == 0 && oyear % 100 != 0) || oyear % 100 == 0
					&& oyear % 400 == 0) {
				maxDaysInMonth = 29;
				break;
			} else {
				maxDaysInMonth = 28;
				break;
			}
		case 3:
			maxDaysInMonth = 31;
			break;
		case 4:
			maxDaysInMonth = 30;
			break;
		case 5:
			maxDaysInMonth = 31;
			break;
		case 6:
			maxDaysInMonth = 30;
			break;
		case 7:
			maxDaysInMonth = 31;
			break;
		case 8:
			maxDaysInMonth = 31;
			break;
		case 9:
			maxDaysInMonth = 30;
			break;
		case 10:
			maxDaysInMonth = 31;
			break;
		case 11:
			maxDaysInMonth = 30;
			break;
		case 12:
			maxDaysInMonth = 31;
			break;
		}
		return maxDaysInMonth;
	}

	/*
	 * 日期运算精确到日
	 */
	public static String date(String str, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String reStr = null;
		Date dt;
		try {
			dt = sdf.parse(str);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			// rightNow.add(Calendar.YEAR,-1);//���ڼ�1��
			// rightNow.add(Calendar.MONTH,3);//���ڼ�3����
			rightNow.add(Calendar.DAY_OF_YEAR, days);// ���ڼ�10��
			Date dt1 = rightNow.getTime();
			reStr = sdf.format(dt1);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reStr;
	}

	/**
	 * 日期运算精确到分钟
	 */
	public static String dateminit(String str, int mint) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String reStr = null;
		Date dt;
		try {
			dt = sdf.parse(str);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			// rightNow.add(Calendar.YEAR,-1);//���ڼ�1��
			// rightNow.add(Calendar.MONTH,3);//���ڼ�3����
			rightNow.add(Calendar.MINUTE, mint);// 提前或退后的时间
			Date dt1 = rightNow.getTime();
			reStr = sdf.format(dt1);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reStr;
	}

	/**
	 * 获得IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");

		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");

		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		//本地出现 0:0:0:0:0:0:1情况处理
		if (ip.indexOf(":")!=-1) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	public static String getOrdernumber() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		String ordernumber = year + " " + month + " " + date + " " + hour + ":"
				+ minute + " ";
		return ordernumber;
	}

	/**
	 * 获得当前系统时间
	 * 
	 * @return
	 */
	public static String getnowtime() {
		String ordernumber;
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		if (month < 10) {
			ordernumber = year + "-0" + month;
		} else {
			ordernumber = year + "-" + month;
		}
		if (date < 10) {
			ordernumber += "-0" + date;
		} else {
			ordernumber += "-" + date;
		}
		return ordernumber;
	}

	/**
	 * double取整
	 * 
	 * @param temp
	 * @return
	 */
	public static String Decimal(double temp) {
		BigDecimal bg = new BigDecimal(temp);
		bg = bg.setScale(0, BigDecimal.ROUND_HALF_UP);
		return bg.toString();
	}

	/**
	 * double保留两位小数
	 * 
	 * @param temp
	 * @return
	 */
	public static double TowDecimal(double temp) {
		BigDecimal bg = new BigDecimal(temp);
		double sum = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return sum;
	}

	/**
	 * 判断日期的大小
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compare_date(String DATE1, String DATE2) {
		int result = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				result = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				result = 0;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}

	public static void logErrorTransaction(String str) {
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter(Tools.class.getResource("/")
					.getPath().replace("%20", " ")
					+ "/ErrorTransaction.text", true);
			bw = new BufferedWriter(fw);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.write(str + "\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * mymd5加密
	 */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	public static File getfile() {
		URL uri = Tools.class.getResource("/");
		File file = null;
		try {
			file = new File(uri.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pathname = file.getAbsolutePath()
				+ "/couponscreatetime.properties";
		file = new File(pathname);

		if (!file.exists()) {
			try {
				file.createNewFile();
				// д���ʼ������
				PrintStream out = new PrintStream(file);
				out.println("createTime=0");
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}

	public static void setCouponsTime(int nowtime) {
		File file = Tools.getfile();
		try {
			PrintStream out = new PrintStream(file);
			;// д��Դ�ļ�
			out.println("createTime=" + nowtime);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getCouponTime() {
		File file = Tools.getfile();
		Properties p = new Properties();
		int createTime = 0;
		try {
			p.load(new FileInputStream(file));
			createTime = Integer.parseInt(p.getProperty("createTime"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createTime;

	}

	/**
	 * 日期月份加减
	 */
	public static String monthModify(String date, int month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
		Calendar calendar = Calendar.getInstance();// 日历对象
		try {
			calendar.setTime(sdf.parse(date));// 设置当前日期
			calendar.add(Calendar.MONTH, month);// 月份加减
			date = sdf.format(calendar.getTime());// 输出格式化的日期
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * sevens 205-3-23 获取某月的最后一天
	 * 
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());

		return lastDayOfMonth;
	}
	
	/**
	 * 给当前时间加一天
	 * @param today
	 * @return
	 */
	
	public static Date getDate(String today){   
        SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try   {   
            Date  d  =  new Date(f.parse(today).getTime());     
              return  d;   
        }   
        catch(Exception ex) {   
            return   null;     
        }   
    }
	
	/**
	 * 给当前时间加一天
	 * @param today
	 * @return
	 */
	
	public static Date addOneday(String today){   
        SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try   {   
            Date  d  =  new Date(f.parse(today).getTime()-24*3600*1000);     
              return  d;   
        }   
        catch(Exception ex) {   
            return   null;     
        }   
    }

	/**
	 * 给当前时间加一天
	 * @param today
	 * @return
	 */
	
	public static Date addmint(String today){   
        SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try   {   
            Date  d  =  new Date(f.parse(today).getTime()-10*60*1000);     
              return  d;   
        }   
        catch(Exception ex) {   
            return   null;     
        }   
    }
	
	/**
	 * sevens 2015-3-23 获得当前的年份
	 * 
	 * @return
	 */
	public static int findYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * sevens 2015-3-23 获得当前月
	 * 
	 * @return
	 */
	public static int fiedMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;

	}

	/**
	 * 随机获得uuid
	 * 
	 * @return xiejin
	 */
	public static String getUUID() {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (id != null && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		return id;
	}

	/**
	 * 获取当前时间的时分秒
	 */
	public static String getHour() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	/**
	 * 去掉数组中重复的值
	 */
	public static String[] arrRepetition(String[] arr) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			if (!list.contains(arr[i])) {
				list.add(arr[i]);
			}
		}
		String[] newArr = list.toArray(new String[1]); // 返回一个包含所有对象的指定类型的数组
		return newArr;
	}

	/**
	 * 获取costnumber
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getCostNumber(HttpServletRequest request) {
		List<Cost> costlist = (List<Cost>) request.getSession().getAttribute(
				"cost");
		List<String> costnumberlist = new ArrayList<String>();
		if (costlist != null) {
			for (Cost cost : costlist) {
				costnumberlist.add(cost.getId());
			}
		}
		return costnumberlist;
	}

	/**
	 * 获取激活码失效时间(当前时间加二十分钟时间)
	 */
	public static String getFailureTime(int failureTime) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, failureTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(now.getTimeInMillis());
		return dateStr;
	}

	/**
	 * 判断日期的大小(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compare_date2(String DATE1, String DATE2) {

		int result = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				result = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				result = 0;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}

	 
	/**
	 * 获取登录用户信息
	 */
	public static Member getMember(HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("member");
		return member;
	}
	
	/**
	 * 获取管理员信息
	 * @param request
	 * @return
	 */
	public static Admin getAdmin(HttpServletRequest request){
		return (Admin)request.getSession().getAttribute("admin");
	}
	
	/**
	 * 获取销售中心
	 * 
	 * @param request
	 * @param costMapper
	 * @return
	 */
	public static Cost getCost(HttpServletRequest request, CostMapper costMapper) {
		String costNumber = (String) request.getSession().getAttribute(
				"costnumber");
		return costMapper.selectByPrimaryKey(costNumber);
	}

	public static String replaceStr(String str) {
		return str.replace("\r\n", "<br>");
	}

	/**
	 * 根据顾客信息，费用计算基础团费
	 * 
	 * @param passengerList
	 * @param tourPrice
	 * @return
	 */
	public static BigDecimal getBasicPrice(List<RoomInfo> roomInfoList,
			List<TourPassenger> childrenPassengerList, Tourprice tourPrice) {
		BigDecimal totalPrice = new BigDecimal(0);
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);

		for (int i = 0; i < roomInfoList.size(); i++) {
			RoomInfo roomInfo = roomInfoList.get(i);
			if (roomInfo.getAdultNum() == 2) {
				totalPrice = totalPrice.add((tourPrice.getSellingprice().multiply(
						new BigDecimal(2))));
			} else if (roomInfo.getAdultNum() == 1) {
				totalPrice = totalPrice.add(tourPrice.getSellingprice().add(
						tourPrice.getSingleroomprice()));
			} else if (roomInfo.getAdultNum() == 3) {
				totalPrice = totalPrice.add(tourPrice.getThreesellingprice().multiply(
						new BigDecimal(3)));
			} else if (roomInfo.getAdultNum() == 4) {
				totalPrice = totalPrice.add(tourPrice.getFoursellingprice().multiply(
						new BigDecimal(4)));
			}
		}
		Calendar birthday = Calendar.getInstance();
		if (childrenPassengerList != null) {
			for (int i = 0; i < childrenPassengerList.size(); i++) {
				if (childrenPassengerList.get(i).getBirthday() != null) {
					birthday.setTime(childrenPassengerList.get(i).getBirthday());
					int year = birthday.get(Calendar.YEAR);
					int age = currentYear - year;
					if (age >= 0 && age < 2) {
						totalPrice = totalPrice.add(tourPrice.getBabyPrice());
					} else if (age >= 2 && age < 5) {
						totalPrice = totalPrice.add(tourPrice.getChildPrice());
					} else if (age >= 5 && age < 11) {
						totalPrice = totalPrice.add(tourPrice
								.getExtrabedprice());
					}
				} else {
					totalPrice = totalPrice.add(tourPrice.getExtrabedprice());
				}
			}
		}
		return totalPrice;
	}
	
	/**
	 * 根据顾客信息，费用计算基础团费
	 * 
	 * @param passengerList
	 * @param tourPrice
	 * @return
	 */
	public static BigDecimal getBasicPriceWithoutTourPassengerInfo(List<RoomInfo> roomInfoList, Tourprice tourPrice) {
		BigDecimal totalPrice = new BigDecimal(0);
		for (int i = 0; i < roomInfoList.size(); i++) {
			RoomInfo roomInfo = roomInfoList.get(i);
			if (roomInfo.getAdultNum() == 2) {
				totalPrice = totalPrice.add((tourPrice.getSellingprice().multiply(
						new BigDecimal(2))));
			} else if (roomInfo.getAdultNum() == 1) {
				totalPrice = totalPrice.add(tourPrice.getSellingprice().add(
						tourPrice.getSingleroomprice()));
			} else if (roomInfo.getAdultNum() == 3) {
				totalPrice = totalPrice.add(tourPrice.getThreesellingprice().multiply(
						new BigDecimal(3)));
			} else if (roomInfo.getAdultNum() == 4) {
				totalPrice = totalPrice.add(tourPrice.getFoursellingprice().multiply(
						new BigDecimal(4)));
			}
			totalPrice = totalPrice.add(tourPrice.getExtrabedprice().multiply(new BigDecimal(roomInfo.getChildNum())));
		}
		return totalPrice;
	}

	/**
	 * 根据客人信息,小费来计算线路小费和自费
	 * 
	 * @param passengerList
	 * @param tourlineTips
	 * @return
	 */
	public static BigDecimal getTips(List<TourPassenger> adultPassengerList,
			List<TourPassenger> childrenPassengerList, BigDecimal tourlineTips) {
		BigDecimal totalPrice = new BigDecimal(0);
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);

		Calendar birthday = Calendar.getInstance();
		for (TourPassenger tourPassenger : adultPassengerList) {
			totalPrice = totalPrice.add(tourlineTips);
		}
		if (childrenPassengerList != null) {
			for (int i = 0; i < childrenPassengerList.size(); i++) {
				if (childrenPassengerList.get(i).getBirthday() != null) {
					birthday.setTime(childrenPassengerList.get(i).getBirthday());
					int year = birthday.get(Calendar.YEAR);
					int age = currentYear - year;
					if (age >= 5) {
						totalPrice = totalPrice.add(tourlineTips);
					}
				} else {
					totalPrice = totalPrice.add(tourlineTips);
				}
			}
		}
		return totalPrice;
	}

	/**
	 * 根据客人信息获得需要收取线路小费自费的人数
	 * 
	 * @param passengerList
	 * @return
	 */
	public static int getCustomerNumWithTips(List<TourPassenger> passengerList) {
		int num = 0;
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR);

		Calendar birthday = Calendar.getInstance();
		for (int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getBirthday() != null) {
				birthday.setTime(passengerList.get(i).getBirthday());
				int year = birthday.get(Calendar.YEAR);
				int age = currentYear - year;
				if (age >= 5) {
					num += 1;
				}
			} else {
				num += 1;
			}
		}
		return num;
	}

	/**
	 * List转换String
	 * 
	 * @param list
	 *            :需要转换的List
	 * @return String转换后的字符串
	 */
	public static String ListToString(List<String> list) {
		String SEP1 = ",";
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || "".equals(list.get(i))) {
					continue;
				}
				sb.append(list.get(i));
				sb.append(SEP1);
			}
			return sb.substring(0, sb.length() - 1).toString();
		} else {
			return "";
		}
	}

	/**
	 * 字符串数组转换成字符串
	 */
	public static String stringArrToString(String[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null || "".equals(arr[i])) {
				continue;
			}
			sb.append(arr[i]);
			sb.append(",");
		}
		return sb.substring(0, sb.length() - 1).toString();
	}

	/**
	 * 读取配置文件
	 */
	public static Properties getProperties() throws IOException {
		Tools tools = new Tools();
		InputStream inputStream = tools.getClass().getClassLoader()
				.getResourceAsStream("config.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException ioE) {
			ioE.printStackTrace();
		} finally {
			inputStream.close();
		}
		return properties;
	}

	/**
	 * @author Sevens 时间2015-7-25 计算两个时间的差值
	 * @param beginstr
	 * @param endstr
	 * @return
	 * @throws ParseException
	 */
	public static String date_pcon(Date beginstr, Date endstr)
			throws ParseException {
		String resgtime = "";
		long between = (endstr.getTime() - beginstr.getTime()) / 1000;// 除以1000是为了转换成秒
		long day1 = between / (24 * 3600);
		long hour1 = between % (24 * 3600) / 3600;
		long minute1 = between % 3600 / 60;
		long second1 = between;
		if (day1 > 1) {
			resgtime = "" + day1 + "天";
		} else {
			if (hour1 > 1) {
				resgtime = "" + hour1 + "小时";
			} else {
				if (minute1 > 1) {
					resgtime = "" + minute1 + "分钟";
				} else {
					if (second1 > 1) {
						resgtime = "" + second1 + "秒";
					}
				}
			}
		}
		return resgtime;
	}

	/**
	 * @Title isBelongList
	 * @Description 判断是否属于欧洲国家
	 * @Author Bowden
	 * @CreateDate 2015-8-21 上午9:56:27
	 */
	public static boolean isBelongEurope(String country) {
		boolean flag = false;
		String[] europeArr = { "英国", "爱尔兰", "荷兰", "比利时", "卢森堡", "法国", "摩纳哥",
				"德国", "奥地利", "瑞士", "列支敦士登", "波兰", "捷克", "斯洛伐克", "匈牙利", "俄罗斯",
				"白俄罗斯", "乌克兰", "爱沙尼亚", "拉脱维亚", "立陶宛", "摩尔多瓦", "冰岛", "丹麦", "挪威",
				"瑞典", "芬兰", "葡萄牙", "西班牙", "安道尔", "意大利", "梵蒂冈", "圣马力诺", "马耳他",
				"塞尔维亚", "克罗地亚", "斯洛文尼亚", "波斯尼亚和黑塞哥维那", "黑山", "马其顿", "科索沃",
				"阿尔巴尼亚", "罗马尼亚", "保加利亚", "希腊", "土耳其", "乔治亚", "阿塞拜疆", "亚美尼亚",
				"哈萨克" };
		List<String> europeList = new ArrayList<String>();
		europeList = Arrays.asList(europeArr);
		if (europeList.contains(country) == true) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 汉字转换为汉语拼音首字母，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * 汉字转换位汉语拼音，英文字符不变
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	public static void main(String[] args) {
//		System.out.println(converterToSpell("’'''''11欢迎来到最棒的Java中文社区"));
		
		// Instantiation will fetch the property file which load the Chinese character mappings  
		ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);  
		
		String traditionalSrc = "++  ++有背光的機械式鍵盤22sssdgf....繁體字，三，我的，帳號>>><<<'''::::::";  
		String simplified = converter.convert(traditionalSrc);  
		System.out.println(simplified+"=============繁体转简体============");  
		System.out.println(new StringBuffer().toString());  
		
		// Or   
		String simplifiedSrc = "简体字，我的账号";  
		String traditional = ZHConverter.convert(simplifiedSrc, ZHConverter.TRADITIONAL);  
		System.out.println(traditional+"=============简体转繁体============");      
	}
	
	/**
	 * 过滤特殊字符
	 * 
	 * @Title: StringFilter
	 * @Description:
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 * @return String 返回类型
	 * @author xiejin
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？〝〞·é]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	/**
	 * 
	 * @param src
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {  
	    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
	    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
	    out.writeObject(src);  
	  
	    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
	    ObjectInputStream in = new ObjectInputStream(byteIn);  
	    @SuppressWarnings("unchecked")  
	    List<T> dest = (List<T>) in.readObject();  
	    return dest;  
	}  
	
	/**
	 * 
	 * @param src
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> T deepCopyObject(T src) throws IOException, ClassNotFoundException {  
	    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
	    ObjectOutputStream out = new ObjectOutputStream(byteOut);  
	    out.writeObject(src);  
	  
	    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());  
	    ObjectInputStream in = new ObjectInputStream(byteIn);  
	    @SuppressWarnings("unchecked")  
	    T dest = (T) in.readObject();  
	    return dest;  
	}  	
	
	/**
	 * 计算团费
	 * 
	 * @param tourPrice
	 */
	public static BigDecimal getTourFee(Tourprice tourPrice,BookTourVO bookTourVO){
		BigDecimal totalFee = tourPrice.getSellingprice().multiply(new BigDecimal(bookTourVO.getAdultsNumber())).add(tourPrice.getChildPrice().multiply(new BigDecimal(bookTourVO.getChildrenNumber()))).add(tourPrice.getBabyPrice().multiply(new BigDecimal(bookTourVO.getInfantsNumber())));
		return totalFee;
	}
	
	/**
	 * 计算导服，行程外观光等其他费用
	 * 
	 * @return
	 */
	public static BigDecimal getFee(BigDecimal price,int peopleNumber){
		return price.multiply(new BigDecimal(peopleNumber));
	}
	
	/**
	 * 根据月份数字得到英文名
	 * 
	 * @param month
	 * @return
	 */
	public static String getEnglishMonth(int month){
		switch(month){
			case 1:return "January";
			case 2:return "February";
			case 3:return "March";
			case 4:return "April";
			case 5:return "May";
			case 6:return "June";
			case 7:return "July";
			case 8:return "August";
			case 9:return "September";
			case 10:return "October";
			case 11:return "November";
			case 12:return "December";
		}
		return "";
	}
	
	/**
	 * 将孙子评论插入到子评论中
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static CommentsToBlog clearGrandchild(CommentsToBlog commentsToBlog) throws IOException, ClassNotFoundException {
		List<CommentsToBlog> childrenCommentsToBlogList = commentsToBlog.getCommentsToBlogList();
		if(childrenCommentsToBlogList != null && childrenCommentsToBlogList.size() > 0){
			List<CommentsToBlog> resultCommentsToBlogList = deepCopy(childrenCommentsToBlogList);
			int index = 0;
			for(CommentsToBlog comToBlog:childrenCommentsToBlogList){
				index++;
				if(comToBlog.getCommentsToBlogList() != null && comToBlog.getCommentsToBlogList().size() > 0){
					comToBlog = clearGrandchild(comToBlog);
					resultCommentsToBlogList.addAll(index, comToBlog.getCommentsToBlogList());
					index += comToBlog.getCommentsToBlogList().size();
				}
			}	
			commentsToBlog.setCommentsToBlogList(resultCommentsToBlogList);
		}
		return commentsToBlog;
	} 
	
	/**
	 * 把个位数字变成两位
	 * 
	 * @return
	 */
	public static String turnOneNumberToDoubleString(int number){
		if(number < 10){
			return "0" + number;
		}else{
			return "" + number;
		}
	}
}

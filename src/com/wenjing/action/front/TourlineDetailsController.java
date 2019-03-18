package com.wenjing.action.front;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wenjing.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.dao.CurrencyMapper;
import com.wenjing.dao.NavigationMapper;
import com.wenjing.dao.RegionMapper;
import com.wenjing.dao.TagMapper;
import com.wenjing.dao.VideoMapper;
import com.wenjing.entity.AirTicketPrice;
import com.wenjing.entity.Tourline;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.ItineraryService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.OrderService;
import com.wenjing.service.PageService;
import com.wenjing.service.ProductService;
import com.wenjing.service.PromotionService;
import com.wenjing.service.RegionService;
import com.wenjing.service.ReviewService;
import com.wenjing.service.ServiceItemService;
import com.wenjing.service.ShoppingCartService;
import com.wenjing.service.SliderService;
import com.wenjing.service.TagService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourLineCalendarService;
import com.wenjing.service.TourlineService;
import com.wenjing.service.VideoService;

/**
 * 类说明		线路详情页展示
 * @author xiejin
 * @date 2015-5-26 
 * @date 2015-5-26 下午5:29:09
 */
@Controller
@RequestMapping("/front/tourlineDetails")
public class TourlineDetailsController {
	
	public static final String HOTEL_SERVICE_ID = "920ffa6fea384f23abe6f5cb4cad28ec";
	public static final String AIRTICKET_SERVICE_ID = "99b21834e66441829910bf214f01e90b";
	public static final int INCLUDE = 1;
	public static final int NOT_INCLUDE = 0;
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private ConcurrentHashMap<String, Map<String, Map>> tourMinPriceMap = new ConcurrentHashMap();
	
	@Resource
	private HttpServletRequest request;
	@Resource
	private SliderService sliderService;
	@Resource 
	TourlineService tourlineService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private DestinationService destinationService;
	@Autowired
	private OrderService orderService;
	@Resource
	private RegionService regionService;
	@Resource
	private TourDateService tourDateService;
	@Resource
	private ItineraryService itineraryService;
	@Resource
	private TourLineCalendarService tourLineCalendarService;
	@Resource
	private CostService costService; 
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Resource
	private PromotionService promotionService;
	@Resource
	private CurrencyService currencyService;
	@Autowired
	private ProductService productService;
	@Resource
	private TagService tagService;
	@Resource
	private ReviewService reviewService;
	@Resource
	private VideoService videoService;
	@Resource
	private ServiceItemService serviceItemService;
	@Autowired
	private NavigationMapper navigationMapper;
	@Autowired
	private RegionMapper regionMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private CurrencyMapper currencyMapper;
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private PageService pageService;
	
	
	/**
	 * 根据费用ID加载机票费用
	 * 
	 * @param departureId
	 * @param tourPriceId
	 * @return
	 */
	@RequestMapping(value="/getAirTicketPrices", method = RequestMethod.POST)
	public @ResponseBody List<AirTicketPrice> getAirTicketPrices(String tourPriceId){
		return orderService.getAirTicketPrice(tourPriceId);
	}
	
	/**
	 * 
	 * @param tourlineId
	 * @param departureId
	 * @param dateNumber
	 * @param firstDate
	 * @return
	 */
	@RequestMapping(value="/nextDates",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String nextDates(String tourlineId,String departureId,int dateNumber,String lastDateString){
		Date lastDate;
		try {
			lastDate = dateFormat.parse(lastDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(lastDate);
		beginDate.add(Calendar.DATE,1);
		String html = tourLineCalendarService.getDepartureDates(tourlineId,departureId,beginDate.getTime(),dateNumber);
		return html;
	}
	
	/**
	 * 
	 * @param tourlineId
	 * @param departureId
	 * @param dateNumber
	 * @param firstDate
	 * @return
	 */
	@RequestMapping(value="/beforeDates",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String beforeDates(String tourlineId,String departureId,int dateNumber,String firstDateString){
		Date firstDate;
		try {
			firstDate = dateFormat.parse(firstDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		String html = tourLineCalendarService.getDepartureDates(tourlineId,departureId,firstDate,dateNumber);
		return html;
	} 
	
	/**
	 * @Title: getTags
	 * @Description: 进入页面异步获取线路评价
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value = "/getReview")
	@ResponseBody
	public List<String> getReview(String productId,String costnumber){
		return reviewService.getReview(productId, costnumber);
	}	
	
	
	/**
	 * @Title: getPrice
	 * @Description: 异步查询标价，售价
	 * @return    
	 * @return Tourline    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value="/getPrice")
	@ResponseBody
	public Map<String,Object> getPrice(String tourlineId){
		Map<String, Object> resultMap = new HashMap();
		Map<String, Map> priceDay = (Map)this.tourMinPriceMap.get(tourlineId);
		if (priceDay == null)
		{
			Tourline tourline = this.tourlineService.findById(tourlineId);



			BigDecimal minSellPrice = this.tourDateService.getMinSellPrice(tourline.getProductid(), tourline.getCostnumber());
			if (minSellPrice == null)
			{
				resultMap.put("lowsprice", Integer.valueOf(0));
				resultMap.put("discountPrice", Integer.valueOf(0));
			}
			else
			{
				BigDecimal discountPrice = this.tourlineService.changeSellprice(minSellPrice, tourline.getId(), tourline.getCostnumber());

				resultMap.put("lowsprice", minSellPrice);

				resultMap.put("discountPrice", discountPrice);
			}
			Map result = new HashMap();
			result.put(Tools.getTime(), resultMap);
			this.tourMinPriceMap.put(tourlineId, result);
			return resultMap;
		}
		String dayValue = null;
		Iterator i$ = priceDay.keySet().iterator();
		if (i$.hasNext())
		{
			String day = (String)i$.next();
			dayValue = day;
		}
		if (dayValue.equals(Tools.getTime())) {
			return (Map)priceDay.get(dayValue);
		}
		Tourline tourline = this.tourlineService.findById(tourlineId);



		BigDecimal minSellPrice = this.tourDateService.getMinSellPrice(tourline.getProductid(), tourline.getCostnumber());
		if (minSellPrice == null)
		{
			resultMap.put("lowsprice", Integer.valueOf(0));
			resultMap.put("discountPrice", Integer.valueOf(0));
		}
		else
		{
			BigDecimal discountPrice = this.tourlineService.changeSellprice(minSellPrice, tourline.getId(), tourline.getCostnumber());

			resultMap.put("lowsprice", minSellPrice);

			resultMap.put("discountPrice", discountPrice);
		}
		this.tourMinPriceMap.remove(tourlineId);
		Map result = new HashMap();
		result.put(Tools.getTime(), resultMap);
		this.tourMinPriceMap.put(tourlineId, result);
		return resultMap;
	}
	
	/**
	 * 根据出发地加载出发日期
	 * 
	 * @param departureId
	 * @return
	 */
	@RequestMapping(value="/getDepartureDatesByDeparture",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDepartureDates(String tourlineId,String departureId,int dateNumber){
		return tourLineCalendarService.getDepartureDates(tourlineId,departureId,null,dateNumber);
	}
	
}

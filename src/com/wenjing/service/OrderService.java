package com.wenjing.service;

import com.wenjing.ExpenseName;
import com.wenjing.Mail;
import com.wenjing.action.front.OrdersController;
import com.wenjing.dao.*;
import com.wenjing.entity.*;
import com.wenjing.exception.OrderException;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.OrdersNo;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;
import com.wenjing.vo.AdditionalProductVO;
import com.wenjing.vo.BookTourVO;
import com.wenjing.webservice.BookErpOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 订单模块业务层
 * 
 * @author Jared
 *
 * May 25, 2015
 */
@Service
public class OrderService {
	
	private static final int TOURLINE_TYPE = 0;
	
	private static final int VISA_TYPE = 100;
	
	private static final String ORDER_MAIL_TEMPLATE_URL = "/order/orderCompleteMail.ftl";

	private static final String TORDER_MAIL_TEMPLATE_URL = "/order/tOrderCompleteMail.ftl";

	private static final String BOOK_MAIL_TEMPLATE_URL = "/order/bookCompleteMail.ftl";

	private static final String TBOOK_MAIL_TEMPLATE_URL = "/order/tBookCompleteMail.ftl";
	
	private static final String AU_ORDER_MAIL_TEMPLATE_URL = "/order/auOrderCompleteMail.ftl";
	
	/**
	 * 订单提交时使用积分的项目名称
	 */
	private static final String ORDER_SCORE_PRODUCT_NAME = "订单兑换";
	
	private static final BigDecimal EXCHANGE_SCORE_LIMIT = new BigDecimal(0.03);
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String NEW_ORDER_STATUS_ID = "0df0f0e4-1e47-11e5-9bc2-94de800aa37e";
	
	@Autowired
	private TourLineCalendarService tourlineCalendarService;
	
	@Autowired
	private BookErpOrdersService bookErpOrdersService;
	
	@Autowired
	private TourlineService tourlineService;
	
	@Autowired
	private TourDateService tourDateService;
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private OrderdetailMapper orderDetailMapper;
	
	@Autowired
	private TourlineMapper tourlineMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private DepartureMapper departureMapper;
	
	@Autowired
	private TourPassengerMapper tourPassengerMapper;
	
	@Autowired
	private OrdercontacterMapper orderContacterMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private PromotionMapper promotionMapper;
	
	@Autowired
	private TourdateMapper tourDateMapper;
	
	@Autowired
	private TourpriceMapper tourPriceMapper;
	
	@Autowired
	private CouponseMapper couponseMapper;
	
	@Autowired
	private CostMapper costMapper;
	
	@Autowired
	private PageMapper pageMapper;
	
	@Autowired
	private AirportPickUpMapper airportPickUpMapper;
	
	@Autowired
	private OrderexpenseMapper orderExpenseMapper;
	
	@Autowired
	private MemberinformationMapper memberinformationMapper;
	
	@Autowired
	private AirTicketPriceMapper airTicketPriceMapper;

	@Autowired
	private VisaMapper visaMapper;
	
	@Resource
	private PayDetailMapper payDetailMapper;
	
	@Resource
	private MemberMapper memberMapper;
	
	@Resource
	private CouponsduijiangMapper couponsduijiangMapper;
	
	@Autowired
	private MemberinformationMapper memberInformationMapper;
	
	@Autowired
	private GetScoreMapper getScoreMapper;
	@Autowired
	private PriceMapper priceMapper;
	@Autowired
	private AirTicketPriceMapper airticketPriceMapper;
	@Autowired
	private AdmissionticketMapper admissionticketMapper;
	@Autowired
	private FoodMapper foodMapper;
	@Autowired
	private AdditionalProductMapper additionalProductMapper;
	@Autowired
	private OrdercontacterMapper ordercontacterMapper;
	@Autowired
	private SelfpayMapper selfPayMapper;
	@Autowired
	private TourdateMapper tourdateMapper;
	@Autowired
	private PreBookingOfAgentMapper preBookingOfAgentMapper;
	
	/**
	 * 根据传来的条件查询orders的总数
	 * 
	 * @return
	 */
	public int getOrdersTotalNumber(Map<String,Object> paramtersMap){
		return ordersMapper.getOrdersTotalNumber(paramtersMap);
	}
	
	public Orders getTotalCollectionAndTotalPeople(Map<String,Object> paramtersMap){
		return ordersMapper.getTotalCollectionAndTotalPeople(paramtersMap);
	}
	
	/**
	 * 根据传来的条件和分页信息查询出每页的orders
	 * 
	 * @param paramtersMap
	 * @return
	 */
	public List<Orders> getOrdersByPage(Map<String,Object> paramtersMap){
		return ordersMapper.getOrdersByPage(paramtersMap);
	}
	
	/**
	 * 加载总订单详情
	 * 
	 * 包括
	 * 	ordercontacter,
	 * 	orders,
	 * 	orderstatus,
	 *  orderdetail,
	 *  product,
	 * 	promotion,
	 * 	tourline,
	 * 	region,
	 * 	selfpay,
	 * 	itineray,
	 * 	hotel,
	 * 	destination,
	 * 	attraction
	 * 
	 * @param ordersId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Orders findOrders(String ordersId){
		Orders order = ordersMapper.findOrders(ordersId);
		for(Orderdetail orderDetail  : order.getOrderdetails()){
			orderDetail.setTourline(tourlineMapper.findWholeInfoByProductId(orderDetail.getProductid()));
		}
		return order;
	}
	
	@Transactional(readOnly=true)
	public Orders findPayInfo(String orderId){
		return ordersMapper.findPayInfo(orderId);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Orders findWithOccupyTimeById(String id){
		return ordersMapper.findWithOccupyTimeById(id);
	}
	
	/**
	 * 
	 * 根据价格ID查询出机票价格
	 * 
	 * @param
	 * @param tourPriceId
	 * @return
	 */
	@Transactional
	public List<AirTicketPrice> getAirTicketPrice(String tourPriceId){
		return airTicketPriceMapper.getAirTicketPriceByTourpriceId(tourPriceId);
	}
	
	/**
	 * 加载线路详情页面时
	 * 根据TourDateId获取departure
	 * 
	 * @param tourDateId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Departure> getDeparturesByTourDateId(String tourDateId){
		return departureMapper.findByTourDateId(tourDateId);
	}
	
	
	/**
	 * 根据用户ID获取需要加载的产品信息
	 * 
	 * @param userId
	 * @return
	 */
//	@Transactional(readOnly = true)
//	public LoadOrderInfoVO loadOrderInfoByUserId(HttpServletRequest request){
//		LoadOrderInfoVO loadOrderInfoVO = new LoadOrderInfoVO();
//		List<EachProductInfoVO> eachProductInfoVOList = new ArrayList<EachProductInfoVO>();
//		Product product = null;
//		Tourline tourline = null;
//		EachProductInfoVO eachProductInfoVO = null;
//		
//		Member member = Tools.getMember(request);
//		Memberinformation memberInformation = null;
//		//当前用户的ID
//		String memberId = null;
//		if(member != null){
//			memberId = member.getId();
//			memberInformation = memberInformationMapper.findInfoByMemberId(memberId);
//			Ordercontacter orderContacter = new Ordercontacter();
//			orderContacter.setEmail(member.getAccount());
//			loadOrderInfoVO.setOrderContacter(orderContacter);
//		}else{
//			memberId = WebUtils.getCookie(request,"memberId");
//		}
//		
//		//得到对应的运营中心
//		String costnumber = (String) request.getSession().getAttribute("costnumber");
//		Cost cost = costMapper.selectByPrimaryKey(costnumber);
//		
//		//根据用户ID获取的所有购物车信息
//		List<ShoppingCart> shoppingCartList = shoppingCartMapper.findByUserIdAndCostNumber(memberId,costnumber);
//		
//		//获取当前年和月,用于获取日历以及获得当前周
//		Calendar currentDate = Calendar.getInstance();
//		int currentYear = currentDate.get(Calendar.YEAR);		//获得当前年份
//		int currentMonth = currentDate.get(Calendar.MONTH) + 1; //获得当前月份
//		
//		//总价格
//		BigDecimal totalPriceUSD = new BigDecimal(0);
//		
//		//遍历购物车取出其中的每条记录,查询出关联的相关信息
//		for(ShoppingCart shoppingCart : shoppingCartList){
//			product = productMapper.selectByPrimaryKey(shoppingCart.getProductId());
//			product.setScoreString(product.getScore().toString());
//			tourline = tourlineMapper.findByProductId(shoppingCart.getProductId());
//			
//			List<Couponse> couponseList = couponseMapper.findByTourlineIdAndMemberId(tourline.getProductid(), null,costnumber);
//			List<AirportPickUp> airportPickUpList = airportPickUpMapper.findAllApickUpsBytourId(tourline.getId());
//			
//			//根据tourDateID查找出tourPrice和departure
//			Tourprice tourPrice = tourPriceMapper.findByTourDateIdAndCostNumber(shoppingCart.getTourDateId(),costnumber);
//			List<Departure> departureList = departureMapper.findByTourDateId(shoppingCart.getTourDateId());
//			List<AirTicketPrice> airTicketPriceList = airTicketPriceMapper.getAirTicketPriceByTourpriceId(tourPrice.getId());
//			
//			//如果有促销活动，对价格进行处理
//			BigDecimal sellPrice = tourPrice.getSellingprice();
//			sellPrice = tourlineService.changeSellprice(sellPrice, tourline.getId(), costnumber);
//			tourPrice.setSellingprice(sellPrice);
//			
//			List<RoomInfo> roomInfoList = JSON.parseArray(shoppingCart.getRoomInfo(),RoomInfo.class);
//			
//			//将数据放入eachProductInfoVO中
//			eachProductInfoVO = new EachProductInfoVO();
//			
//			//团费
//			BigDecimal basicPrice = Tools.getBasicPriceWithoutTourPassengerInfo(roomInfoList, tourPrice);
//			
//			totalPriceUSD = totalPriceUSD.add(basicPrice.divide(cost.getExchangerate(), BigDecimal.ROUND_DOWN));
//			
//			//导游服务费和行程外精彩景点/观光/演出之和
//			int tourlineTips = shoppingCart.getTotalNumber() * (tourline.getTipPrice().add(tourline.getSteamPrice()).intValue()); 
//			
//			totalPriceUSD = totalPriceUSD.add(new BigDecimal(tourlineTips));
//			
//			/**
//			 * 接送机费用(美元)
//			 */
//			BigDecimal airportPickUpPriceUSD = new BigDecimal(0);
//			
//			/**
//			 * 如果产品有接送机，则存入接送机，并计算接送机费用
//			 */
//			if(shoppingCart.getAirportPickUpId() != null && !"".equals(shoppingCart.getAirportPickUpId())){
//				AirportPickUp airportPickUp = new AirportPickUp();
//				if("12345678".equals(shoppingCart.getAirportPickUpId())){
//					airportPickUp.setId("12345678");
//					airportPickUp.setTitle("不使用该服务");
//					airportPickUp.setPrice(new BigDecimal(0));
//					airportPickUp.setTourlineid(tourline.getId());
//					eachProductInfoVO.setSelectedAirportPickUp(airportPickUp);
//				}else{
//					airportPickUp = airportPickUpMapper.selectByPrimaryKey(shoppingCart.getAirportPickUpId());
//					eachProductInfoVO.setSelectedAirportPickUp(airportPickUp);
//					airportPickUpPriceUSD = airportPickUp.getPrice().multiply(new BigDecimal(shoppingCart.getTotalNumber()));
//				}
//			}
//			totalPriceUSD = totalPriceUSD.add(airportPickUpPriceUSD);
//			
//			/**
//			 * 机票费用(美元)
//			 */
//			BigDecimal airTicketPriceUSD = new BigDecimal(0);
//			
//			/**
//			 * 计算机票费用
//			 */
//			if(shoppingCart.getAirTicketPriceId() != null && !"".equals(shoppingCart.getAirTicketPriceId())){
//				AirTicketPrice airTicketPrice = airTicketPriceMapper.selectByPrimaryKey(shoppingCart.getAirTicketPriceId());
//				airTicketPriceUSD = airTicketPrice.getPrice().multiply(new BigDecimal(shoppingCart.getTotalNumber()));
//				eachProductInfoVO.setSelectedAirTicketPrice(airTicketPrice);
//			}
//			totalPriceUSD = totalPriceUSD.add(airTicketPriceUSD);
//			
//			//根据departureDate和days算出enddate
//			Calendar endDate = Calendar.getInstance();
//			endDate.setTime(shoppingCart.getDepartureDate());
//			endDate.add(Calendar.DAY_OF_MONTH,tourline.getDays()-1);
//			
//			eachProductInfoVO.setShoppingCartId(shoppingCart.getId());
//			eachProductInfoVO.setProduct(product);
//			eachProductInfoVO.setDepartureDate(dateFormat.format(shoppingCart.getDepartureDate()));
//			eachProductInfoVO.setEndDate(dateFormat.format(endDate.getTime()));
//			eachProductInfoVO.setWeekDay(currentDate.get(Calendar.DAY_OF_WEEK));
//			eachProductInfoVO.setTourlineId(tourline.getId());
//			eachProductInfoVO.setDays(tourline.getDays());
//			eachProductInfoVO.setRoomInfoList(roomInfoList);
//			eachProductInfoVO.setCouponseList(couponseList);
//			eachProductInfoVO.setDepartureList(departureList);
//			eachProductInfoVO.setTotalNum(shoppingCart.getTotalNumber());
//			eachProductInfoVO.setAdultNum(shoppingCart.getAdultNumber());
//			eachProductInfoVO.setChildrenNum(shoppingCart.getChildrenNumber());
//			eachProductInfoVO.setStringTourPrice(new StringTourPrice(tourPrice));
//			eachProductInfoVO.setAirTicketPriceList(airTicketPriceList);
//			eachProductInfoVO.setAirportPickUpList(airportPickUpList);
//			eachProductInfoVO.setTourDateId(shoppingCart.getTourDateId());
//			eachProductInfoVO.setCurrencySign(cost.getCode());
//			eachProductInfoVO.setTourCalendar(tourlineCalendarService.getCanchar(currentYear, currentMonth, tourline.getId(), cost.getCode(),costnumber));
//			eachProductInfoVO.setTourlineTips(tourline.getTipPrice().add(tourline.getSteamPrice()).multiply(cost.getExchangerate()).toString());
//			eachProductInfoVOList.add(eachProductInfoVO);
//		}
//		if(memberInformation != null){
//			int maxExchangeScore = totalPriceUSD.multiply(EXCHANGE_SCORE_LIMIT).setScale(0, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).intValue();
//			int userScore = memberInformation.getScore();
//			int userMaxScore = 100;
//			
//			//如果用户的积分数大于100分
//			if(userScore > 100){
//				if(userScore < maxExchangeScore){
//					userMaxScore = userScore/100 * 100; 
//				}else{
//					userMaxScore = maxExchangeScore;
//				}	
//			}else{
//				userMaxScore = 0;
//			}
//			int[] chooseLevel = new int[userMaxScore/100];
//			for(int level = 100,i=0; level <= userMaxScore; level += 100,i++){
//				chooseLevel[i] = level;
//			}
//			loadOrderInfoVO.setScoreLevelArray(chooseLevel);
//		}
//		
//		loadOrderInfoVO.setEachProductInfoVOList(eachProductInfoVOList);
//		loadOrderInfoVO.setExchangeRate(cost.getExchangerate().toString());
//		return loadOrderInfoVO;
//	}
	
	/**
	 * @param bookTourVO
	 * @param request
	 * @return
	 */
	public BookTourVO submitBookTour(BookTourVO bookTourVO,HttpServletRequest request,HttpServletResponse response){
		String memberId = Tools.getMember(request).getId();
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(bookTourVO.getDepartureDate());
		
		Tourline tourline = tourlineMapper.selectByPrimaryKey(bookTourVO.getTourlineId());
		Tourprice tourPrice = tourPriceMapper.selectByPrimaryKey(bookTourVO.getTourPriceId());
		
	    Product product = this.productMapper.getWithPageById(tourline.getProductid());

	    int sumPeople = bookTourVO.getAdultsNumber() + bookTourVO.getChildrenNumber() + bookTourVO.getInfantsNumber();
	    Tourdate tourdate = this.tourDateMapper.selectByPrimaryKey(tourPrice.getTourdateid());
	    if ((tourdate.getRemainnum() == null) || (tourdate.getRemainnum().intValue() < sumPeople)) {
	       bookTourVO.setProduct(product);
	       throw new OrderException("sold_out");
	    }
		Selfpay guideServicePrice = selfPayMapper.selectByPrimaryKey(bookTourVO.getGuideServeId());
		Selfpay steamPrice = selfPayMapper.selectByPrimaryKey(bookTourVO.getSteamPriceId());
		Selfpay transfer = selfPayMapper.selectByPrimaryKey(bookTourVO.getTransferId());
		AirTicketPrice airticketPrice = airticketPriceMapper.selectByPrimaryKey(bookTourVO.getAirticketPriceId());
		Cost cost = costMapper.selectByPrimaryKey(tourline.getCostnumber());
		bookTourVO.setCost(cost);

		tourPrice = tourlineService.changeTourPrice(tourPrice, bookTourVO.getTourlineId(), tourline.getCostnumber());
		
		//页面传输单价
		bookTourVO.setSellingPrice(tourPrice.getSellingprice().toString());
		
		BigDecimal tourFee = Tools.getTourFee(tourPrice,bookTourVO);
		BigDecimal singleSupplementsFee = new BigDecimal(0);
		BigDecimal guideServeFee = new BigDecimal(0);
		BigDecimal steamFee = new BigDecimal(0);
		BigDecimal transferFee = new BigDecimal(0);
		BigDecimal airticketFee = new BigDecimal(0);
		BigDecimal optionalTourFee = new BigDecimal(0);
		BigDecimal additionalFee = new BigDecimal(0);
		BigDecimal couponsFee = new BigDecimal(0);
		bookTourVO.setTourFee(tourFee);
		
		//单房差
		if(bookTourVO.getSingleRoomNumber() != null && bookTourVO.getSingleRoomNumber().intValue() != 0){
			singleSupplementsFee = tourPrice.getSingleroomprice().multiply(new BigDecimal(bookTourVO.getSingleRoomNumber()));
			bookTourVO.setSingleSupplementsFee(singleSupplementsFee);
		}
		//导服
		if(guideServicePrice != null){
			guideServeFee = Tools.getFee(guideServicePrice.getPrice(), bookTourVO.getTotalNumber());
			bookTourVO.setGuideServeFee(guideServeFee);
		}
		//行程外精彩景点观光
		if(steamPrice != null){
			steamFee = Tools.getFee(steamPrice.getPrice(),bookTourVO.getTotalNumber());
			bookTourVO.setSteamFee(steamFee);
		}
		//接送机费用
		if(transfer != null){
			transferFee = Tools.getFee(transfer.getPrice(), bookTourVO.getTotalNumber());
			bookTourVO.setTransferFee(transferFee);
		}
		//机票费用
		if(airticketPrice != null){
			airticketFee = Tools.getFee(airticketPrice.getPrice(), bookTourVO.getTotalNumber());
			bookTourVO.setAirticketFee(airticketFee);
		}
	
		List<AdditionalProduct> additionalProductList = new ArrayList<AdditionalProduct>();
		AdditionalProduct additionalProduct = null;
		
		Pattern p = Pattern.compile("[^0-9]");   
		/**
		 * 自费项目与目的地有关
		 */
		if(bookTourVO.getSelfPayList() != null && bookTourVO.getSelfPayList().size() > 0){
			for(AdditionalProductVO additionalProductVO : bookTourVO.getSelfPayList()){
				Matcher m = p.matcher(additionalProductVO.getItineryDay());
				int dayNumber = Integer.parseInt(m.replaceAll("").trim());
				dateCalendar.add(Calendar.DAY_OF_MONTH,dayNumber - 1);
				additionalProductVO.setDayString(Tools.getEnglishShow(dateCalendar.getTime()));
				Selfpay selfPay = selfPayMapper.selectByPrimaryKey(additionalProductVO.getProductId());
				BigDecimal totalPrice = selfPay.getPrice().multiply(new BigDecimal(additionalProductVO.getQuantity()));
				additionalProduct = new AdditionalProduct();
				additionalProduct.setId(Tools.getUUID());
				additionalProduct.setName(selfPay.getName());
				additionalProduct.setImageurl(selfPay.getImgUrl());
				additionalProduct.setDiscription(selfPay.getRemark());
				additionalProduct.setUnitcost(selfPay.getPrice());
				additionalProduct.setQuantity(additionalProductVO.getQuantity());
				additionalProduct.setPrice(totalPrice);
				additionalProduct.setType(AdditionalProduct.OPTIONAL_TOUR_TYPE);
				additionalProduct.setItinerarayid(additionalProductVO.getItineryId());
				additionalProduct.setItinerarayDay(additionalProductVO.getItineryDay());
				additionalProduct.setDestinationid(additionalProductVO.getDestinationId());
				additionalProduct.setDestinationName(additionalProductVO.getDestinationName());
				additionalProduct.setProductid(additionalProductVO.getProductId());
				additionalProduct.setDate(dateCalendar.getTime());
				additionalProductList.add(additionalProduct);
				optionalTourFee = optionalTourFee.add(totalPrice);
				additionalProductVO.setUnitPrice(additionalProduct.getUnitcost());
				additionalProductVO.setTotalFee(totalPrice);
				additionalProductVO.setProductName(additionalProduct.getName());
				dateCalendar.setTime(bookTourVO.getDepartureDate());
			}
		}
		
		/**
		 * 自费项目与目的地无关
		 */
		if(bookTourVO.getSelfPayInTourline() != null && bookTourVO.getSelfPayInTourline().size() > 0){
			for(AdditionalProductVO additionalProductVO : bookTourVO.getSelfPayInTourline()){
				Selfpay selfPay = selfPayMapper.selectByPrimaryKey(additionalProductVO.getProductId());
				BigDecimal totalPrice = selfPay.getPrice().multiply(new BigDecimal(additionalProductVO.getQuantity()));
				additionalProduct = new AdditionalProduct();
				additionalProduct.setId(Tools.getUUID());
				additionalProduct.setName(selfPay.getName());
				additionalProduct.setImageurl(selfPay.getImgUrl());
				additionalProduct.setDiscription(selfPay.getRemark());
				additionalProduct.setUnitcost(selfPay.getPrice());
				additionalProduct.setQuantity(additionalProductVO.getQuantity());
				additionalProduct.setPrice(totalPrice);
				additionalProduct.setType(AdditionalProduct.OPTIONAL_TOUR_TYPE_IN_TOURLINE);
				additionalProduct.setProductid(additionalProductVO.getProductId());
				additionalProductList.add(additionalProduct);
				optionalTourFee = optionalTourFee.add(totalPrice);
				additionalProductVO.setUnitPrice(additionalProduct.getUnitcost());
				additionalProductVO.setTotalFee(totalPrice);
				additionalProductVO.setProductName(additionalProduct.getName());
			}
		}
		
		/**
		 * 自定义项目
		 */
		if(bookTourVO.getAdditionalProductList() != null && bookTourVO.getAdditionalProductList().size() > 0 ){
			for(AdditionalProductVO additionalProductVO : bookTourVO.getAdditionalProductList()){
				Matcher m = p.matcher(additionalProductVO.getItineryDay());
				int dayNumber = Integer.parseInt(m.replaceAll("").trim());
				dateCalendar.add(Calendar.DAY_OF_MONTH,dayNumber - 1);
				additionalProductVO.setDayString(Tools.getEnglishShow(dateCalendar.getTime()));
				BigDecimal totalPrice = null;
				
				additionalProduct = new AdditionalProduct();
				additionalProduct.setId(Tools.getUUID());
				if(additionalProductVO.getType() == 1){
					Admissionticket admissionticket = admissionticketMapper.selectWithPriceByProductId(additionalProductVO.getProductId());
					additionalProduct.setName(admissionticket.getTicketname());
					additionalProduct.setImageurl(admissionticket.getImageurl());
					additionalProduct.setDiscription(admissionticket.getTicketdescription());
					additionalProduct.setUnitcost(admissionticket.getPrice());
					totalPrice = admissionticket.getPrice().multiply(new BigDecimal(additionalProductVO.getQuantity()));
				}else{
					Food food = foodMapper.selectWithPriceByProductId(additionalProductVO.getProductId());
					additionalProduct.setName(food.getFoodname());
					additionalProduct.setImageurl(food.getImageurl());
					additionalProduct.setDiscription(food.getFooddescription());
					additionalProduct.setUnitcost(food.getPrice());
					totalPrice = food.getPrice().multiply(new BigDecimal(additionalProductVO.getQuantity()));
				}
				additionalProduct.setQuantity(additionalProductVO.getQuantity());
				additionalProduct.setPrice(totalPrice);
				additionalProduct.setType(additionalProductVO.getType());
				additionalProduct.setItinerarayid(additionalProductVO.getItineryId());
				additionalProduct.setItinerarayDay(additionalProductVO.getItineryDay());
				additionalProduct.setDestinationid(additionalProductVO.getDestinationId());
				additionalProduct.setDestinationName(additionalProductVO.getDestinationName());
				additionalProduct.setProductid(additionalProductVO.getProductId());
				additionalProduct.setDate(dateCalendar.getTime());
				additionalProductList.add(additionalProduct);
				additionalFee = additionalFee.add(totalPrice);
				additionalProductVO.setUnitPrice(additionalProduct.getUnitcost());
				additionalProductVO.setTotalFee(totalPrice);
				additionalProductVO.setProductName(additionalProduct.getName());
				dateCalendar.setTime(bookTourVO.getDepartureDate());
			}
		}
		Couponse couponse = null;
		//coupon
		if(!StringUtils.isEmpty(bookTourVO.getCouponCode())){
			Couponsduijiang couponsduij = couponsduijiangMapper.findWithExchange(cost.getId(), bookTourVO.getCouponCode());
			if(couponsduij!=null){
				 couponse = couponseMapper.selectByPrimaryKey(couponsduij.getCouponseid());
				 if(couponse.getType()!=3){
					 couponsduij.setUsestatus(1);
				 }
				 couponse.setRemaining(couponse.getRemaining()-1);
				 couponsduij.setReleasestatus(couponsduij.getReleasestatus()-1);
				 couponsduijiangMapper.updateByPrimaryKeySelective(couponsduij);
				 couponseMapper.updateByPrimaryKeySelective(couponse);
			}
			Object object = request.getSession().getAttribute("couponsePrice");
		    if(object != null){
		        Integer couponsePrice = Integer.parseInt(object.toString());
		        request.getSession().removeAttribute("couponsePrice");
		        couponsFee = new BigDecimal(couponsePrice);
		    }
		    bookTourVO.setCouponsFee(couponsFee);
		}
		
		//总价格
		BigDecimal totalFee = tourFee.add(singleSupplementsFee).add(guideServeFee).add(steamFee).add(transferFee).add(airticketFee).add(optionalTourFee).add(additionalFee).subtract(couponsFee);
		
		//如果价格不符合后台计算所得数据
		if(bookTourVO.getTotalPrice() == null || totalFee.compareTo(bookTourVO.getTotalPrice()) != 0){
			throw new OrderException("incorrect_price");
		}
		
		/**
		 * 将总价格根据汇率换算成积分
		 */
		BigDecimal exchange = cost.getExchangerate();
		BigDecimal score = bookTourVO.getTotalPrice().divide(exchange,0, BigDecimal.ROUND_DOWN);
		bookTourVO.setProduct(product);
		
		/**
		 * 总订单
		 */
		Orders orders = new Orders();
		orders.setId(Tools.getUUID());
		orders.setOrderno("I" + OrdersNo.getordersNumber());
		orders.setQuantity(1);
		orders.setTotalprice(bookTourVO.getTotalPrice());
		orders.setRemark(bookTourVO.getSecurityCode());
		orders.setReceiveway(bookTourVO.getVoucherCode());
		orders.setIp(Tools.getRemortIP(request));
		orders.setUserid(memberId);
		orders.setScore(score.intValue());
		orders.setCreatetime(new Date());
		orders.setStatusid(NEW_ORDER_STATUS_ID);
		orders.setCostnumber(cost.getId());
		orders.setOrderType(Orders.TOURLINE);
		ordersMapper.insert(orders);
		bookTourVO.setOrderNumber(orders.getOrderno());
		bookTourVO.setOrdersId(orders.getId());
		
		/**
		 * 联系人信息
		 */
		Ordercontacter orderContacter = new Ordercontacter();
		orderContacter.setId(Tools.getUUID());
		orderContacter.setEmail(bookTourVO.getEmail());
		orderContacter.setFirstName(bookTourVO.getFirstName());
		orderContacter.setLastName(bookTourVO.getLastName());
		orderContacter.setAddress(bookTourVO.getAddress());
		orderContacter.setTel(bookTourVO.getPhone());
		orderContacter.setOrderid(orders.getId());
		orderContacterMapper.insert(orderContacter);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateCalendar.setTime(bookTourVO.getDepartureDate());
		dateCalendar.add(Calendar.DAY_OF_MONTH,tourline.getDays()-1);
		
		/**
		 * 子订单
		 */
		Orderdetail orderDetail = new Orderdetail();
		orderDetail.setId(Tools.getUUID());
		orderDetail.setOrderid(orders.getId());
		orderDetail.setDays(tourline.getDays());
		orderDetail.setDeparturedate(sdf.format(bookTourVO.getDepartureDate()));
		orderDetail.setEnddate(sdf.format(dateCalendar.getTime()));
		orderDetail.setRoomcount(bookTourVO.getDoubleRoomNumber() + bookTourVO.getSingleRoomNumber() + bookTourVO.getTwinRoomNumber() + bookTourVO.getTripleRoomNumber());
		orderDetail.setTripleRoomNumber(bookTourVO.getTripleRoomNumber());
		orderDetail.setDoubleRoomNumber(bookTourVO.getDoubleRoomNumber());
		orderDetail.setTwinRoomNumber(bookTourVO.getTwinRoomNumber());
		orderDetail.setSingleRoomNumber(bookTourVO.getSingleRoomNumber());
		orderDetail.setAdults(bookTourVO.getAdultsNumber());
		orderDetail.setChildren(bookTourVO.getChildrenNumber());
		orderDetail.setBabies(bookTourVO.getInfantsNumber());
		orderDetail.setPrice(bookTourVO.getTotalPrice());
		orderDetail.setDespotprice(new BigDecimal(0));
		orderDetail.setFinalprice(bookTourVO.getTotalPrice());
		orderDetail.setPayStatus(0);
		orderDetail.setTourdateId(tourdate.getId());
		orderDetail.setSpecialrequest(bookTourVO.getSpecialrequest());
		if(couponse != null){
			orderDetail.setCouponseId(couponse.getId());
			orderDetail.setRoomInfo(couponse.getCouponseCode());
		}
		
		
		int timeNow = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
		List<Promotion> promotionList = promotionMapper.findBytourlineId(bookTourVO.getTourlineId(), cost.getId(), timeNow);

		if(promotionList.size() > 0){
			orderDetail.setPromotionId(promotionList.get(0).getId());
		}
		orderDetail.setProductid(tourline.getProductid());
		orderDetail.setCostnumber(cost.getId());
		orderDetail.setCurrencySign(cost.getCode());
		orderDetail.setDepartureId(bookTourVO.getDepartureId());
		orderDetail.setAirportPickUpId(bookTourVO.getTransferId());
		orderDetail.setAirTicketPriceId(bookTourVO.getAirticketPriceId());
		orderDetail.setSelfPayId(bookTourVO.getSelfPayId());
		orderDetailMapper.insert(orderDetail);
		
		List<Orderexpense> orderExpenseList = new ArrayList<Orderexpense>();
		
		/**
		 * 团费
		 */
		Orderexpense tourExpense = new Orderexpense();
		tourExpense.setId(Tools.getUUID());
		tourExpense.setName(ExpenseName.TOUR_FEE);
		tourExpense.setVisitors(bookTourVO.getTotalNumber());
		tourExpense.setPrice(tourFee);
		tourExpense.setOrderid(orders.getId());
		tourExpense.setCostnumber(cost.getId());
		orderExpenseList.add(tourExpense);
		
		/**
		 * 单房差费用
		 */
		if(singleSupplementsFee.intValue() != 0){
			Orderexpense singleSupplementsExpense = new Orderexpense();
			singleSupplementsExpense.setId(Tools.getUUID());
			singleSupplementsExpense.setName(ExpenseName.SINGLE_SUPPLEMENTS_FEE);
			singleSupplementsExpense.setVisitors(bookTourVO.getSingleRoomNumber());
			singleSupplementsExpense.setPrice(singleSupplementsFee);
			singleSupplementsExpense.setOrderid(orders.getId());
			singleSupplementsExpense.setCostnumber(cost.getId());
			orderExpenseList.add(singleSupplementsExpense);
		}		
		
		/**
		 * 导服费
		 */
		if(guideServeFee.intValue() != 0){
			Orderexpense guideServeExpense = new Orderexpense();
			guideServeExpense.setId(Tools.getUUID());
			guideServeExpense.setName(ExpenseName.GUIDE_SERVE_FEE);
			guideServeExpense.setVisitors(bookTourVO.getTotalNumber());
			guideServeExpense.setPrice(guideServeFee);
			guideServeExpense.setOrderid(orders.getId());
			guideServeExpense.setCostnumber(cost.getId());
			orderExpenseList.add(guideServeExpense);
		}

		/**
		 * 行程外精彩演出
		 */
		if(steamFee.intValue() != 0){
			Orderexpense steamExpense = new Orderexpense();
			steamExpense.setId(Tools.getUUID());
			steamExpense.setName(ExpenseName.STEAM_FEE);
			steamExpense.setVisitors(bookTourVO.getTotalNumber());
			steamExpense.setPrice(steamFee);
			steamExpense.setOrderid(orders.getId());
			steamExpense.setCostnumber(cost.getId());
			orderExpenseList.add(steamExpense);		
		}
		
		/**
		 * 接送机
		 */
		if(transferFee.intValue() != 0){
			Orderexpense transferExpense = new Orderexpense();
			transferExpense.setId(Tools.getUUID());
			transferExpense.setName(ExpenseName.TRANSFER_FEE);
			transferExpense.setVisitors(bookTourVO.getTotalNumber());
			transferExpense.setPrice(transferFee);
			transferExpense.setOrderid(orders.getId());
			transferExpense.setCostnumber(cost.getId());
			orderExpenseList.add(transferExpense);
		}
		
		/**
		 * 机票
		 */
		if(airticketFee.intValue() != 0){
			Orderexpense airlineTicketsExpense = new Orderexpense();
			airlineTicketsExpense.setId(Tools.getUUID());
			airlineTicketsExpense.setName(ExpenseName.AIRLINE_TICKETS_FEE);
			airlineTicketsExpense.setVisitors(bookTourVO.getTotalNumber());
			airlineTicketsExpense.setPrice(airticketFee);
			airlineTicketsExpense.setOrderid(orders.getId());
			airlineTicketsExpense.setCostnumber(cost.getId());
			orderExpenseList.add(airlineTicketsExpense);
		}
		
		int selfPaySize = 0;
		if(bookTourVO.getSelfPayList() != null){
			selfPaySize += bookTourVO.getSelfPayList().size();
		}
		if(bookTourVO.getSelfPayInTourline() != null){
			selfPaySize += bookTourVO.getSelfPayInTourline().size();
		}
		
		/**
		 * 自费项目
		 */
		if(optionalTourFee.intValue() != 0){
			Orderexpense optionalTourExpense = new Orderexpense();
			optionalTourExpense.setId(Tools.getUUID());
			optionalTourExpense.setName(ExpenseName.OPTIONAL_TOUR_FEE);
			optionalTourExpense.setVisitors(selfPaySize);
			optionalTourExpense.setPrice(optionalTourFee);
			optionalTourExpense.setOrderid(orders.getId());
			optionalTourExpense.setCostnumber(cost.getId());
			orderExpenseList.add(optionalTourExpense);
		}
		
		/**
		 * 自定义产品
		 */
		if(additionalFee.intValue() != 0){
			Orderexpense additionalExpense = new Orderexpense();
			additionalExpense.setId(Tools.getUUID());
			additionalExpense.setName(ExpenseName.ADDITIONAL);
			additionalExpense.setVisitors(bookTourVO.getAdditionalProductList().size());
			additionalExpense.setPrice(additionalFee);
			additionalExpense.setOrderid(orders.getId());
			additionalExpense.setCostnumber(cost.getId());
			orderExpenseList.add(additionalExpense);
		}

		/**
		 * coupon兑换金额
		 */
		if(couponsFee.intValue() != 0){
			Orderexpense couponsExpense = new Orderexpense();
			couponsExpense.setId(Tools.getUUID());
			couponsExpense.setName(ExpenseName.COUPON);
			couponsExpense.setVisitors(bookTourVO.getTotalNumber());
			couponsExpense.setPrice(couponsFee);
			couponsExpense.setOrderid(orders.getId());
			couponsExpense.setCostnumber(cost.getId());
			orderExpenseList.add(couponsExpense);
		}
		
		orderExpenseMapper.insertBatch(orderExpenseList);
		
		if(additionalProductList.size() > 0){
			for(AdditionalProduct eachAdditionalProduct: additionalProductList){
				eachAdditionalProduct.setOrderdetailid(orderDetail.getId());
			}
			additionalProductMapper.insertBatch(additionalProductList);
		}
		/**
		 * 如果客人房型没有选择则设置空
		 */
		for(TourPassenger tourPassenger : bookTourVO.getTourPassengerList()){
			tourPassenger.setId(Tools.getUUID());
			tourPassenger.setOrdersId(orderDetail.getId());
			if(!"ADULT".equals(tourPassenger.getIdentity())){
				tourPassenger.setRoomNumber(0);
			}
		}
		if(bookTourVO.getTourPassengerList().size() != 0){
			tourPassengerMapper.insertBatch(bookTourVO.getTourPassengerList());
		}
		return bookTourVO;
	}
	
	public String agentBooking(Tourdate tourdate,Tourline tourline,PreBookingOfAgent preBookingOfAgent){
		if(tourdate.getRemainnum()  == null || tourdate.getRemainnum() < preBookingOfAgent.getPax()){
			return tourline.getProductProductid().getPagePageid().getFilepath() + "?soldout";
		}else{
			//减去库存
			tourdateMapper.updateStore(preBookingOfAgent.getTourdateid(), preBookingOfAgent.getPax() + 0);
		}
		Cost cost = costMapper.selectByPrimaryKey(tourline.getCostnumber());
		preBookingOfAgent.setId(Tools.getUUID());
		preBookingOfAgent.setBookingNo("I" + OrdersNo.getordersNumber());
		preBookingOfAgent.setExpirationdate(preBookingOfAgent.getExpirationDateMonth()+"/"+preBookingOfAgent.getExpirationDateYear());
		preBookingOfAgent.setCreatetime(new Date());
		preBookingOfAgent.setProductid(tourline.getProductProductid().getId());
		preBookingOfAgent.setProductname(tourline.getProductProductid().getName());
		preBookingOfAgent.setProductcode(tourline.getProductProductid().getCode());
		preBookingOfAgent.setCurrencysign(cost.getCode());
		preBookingOfAgent.setIsSynchronize(false);
		preBookingOfAgent.setCostnumber(tourline.getProductProductid().getCostnumber());
		preBookingOfAgentMapper.insert(preBookingOfAgent);
		return "/agentPay.htm?bookId=" + preBookingOfAgent.getId();
	}
	
	
	/**
	 * 
	 * @param bookingId
	 * @return
	 */
	public PreBookingOfAgent getAgentBookingById(String bookingId){
		return preBookingOfAgentMapper.selectAllById(bookingId);
	}
	
	/**
	 * 
	 * @param ordersId
	 * @return
	 */
	@Transactional
	public int synchronizeTourlineOrdersToERP(String ordersId){
		Orders orders = ordersMapper.findAllInfoByOrdersId(ordersId);
	    if(orders != null){
	    	String orderNo = null;
			orderNo = bookErpOrdersService.bookOrders(orders);
			if(orderNo != null){
				if("notExists".equals(orderNo)){
					return 2;
				}
				orders.setSynchronizedOrderNo(orderNo);
				orders.setIsSynchronized(1);
				ordersMapper.updateByPrimaryKeySelective(orders);
				return 1;
			}else{
				return 0;
			}
	    }else{
	    	return 0;
	    }
	}
	
	/**
	 * 批量同步订单
	 * 
	 * @param orderIds
	 */
	@Transactional
	public int batchSynchronizeOrdersToERP(String[] orderIds){
		List<Orders> ordersList = ordersMapper.findAllInfoByOrderIds(orderIds);
		String orderNo;
		boolean noProduct = false;
		for(Orders order:ordersList){
			orderNo = null;
			orderNo = bookErpOrdersService.bookOrders(order);
			if(orderNo != null && !"notExists".equals(orderNo)){
				order.setSynchronizedOrderNo(orderNo);
				order.setIsSynchronized(1);
				ordersMapper.updateByPrimaryKeySelective(order);
			}else{
				noProduct = true;
			}
		}
		if(noProduct){
			return -1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 每天同步erp
	 * 
	 * @return
	 */
	@Transactional
	public void synchronizeOrdersToERP(){
		Calendar calendar = Calendar.getInstance();
	    int cyear = calendar.get(Calendar.YEAR);
	    int cmonth = calendar.get(Calendar.MONTH);
	    int cdate = calendar.get(Calendar.DAY_OF_MONTH);
	    calendar.set(cyear, cmonth, cdate, 14, 0);
	    long endTime = calendar.getTime().getTime()/1000;
	    calendar.add(Calendar.DAY_OF_MONTH, -1);
		long startTime = calendar.getTime().getTime()/1000;
		String orderNo;
		List<Orders> ordersList = ordersMapper.findAllTourlineOrders(startTime,endTime);
		for(Orders order:ordersList){
			orderNo = null;
			orderNo = bookErpOrdersService.bookOrders(order);
			if(orderNo != null){
				order.setSynchronizedOrderNo(orderNo);
				order.setIsSynchronized(1);
				ordersMapper.updateByPrimaryKeySelective(order);
			}
		}
	}	
	
	/**
	 * 保存签证订单
	 * @author Sevens
	 * @param orders
	 * @param orderdetail
	 * @param ordercontacter
	 * @param request
	 * @return
	 */
	@Transactional
	public int savaVisaOrders(Orders orders,Orderdetail orderdetail,Ordercontacter ordercontacter,HttpServletRequest request){
		Member member = Tools.getMember(request);
		//当前用户的ID
		String memberId = null;
		if(member != null){
			memberId = member.getId();
		}else{
			memberId = WebUtils.getCookie(request,"memberId");
		}
		String costnumber = (String) request.getSession().getAttribute("costnumber");
		Cost cost = costMapper.selectByPrimaryKey(costnumber);
		
		orders.setId(Tools.getUUID());
		orders.setOrderno(OrdersNo.getordersNumber());
		orders.setIp(Tools.getRemortIP(request));
		orders.setUserid(memberId);
		orders.setCostnumber(costnumber);
		orders.setStatusid(NEW_ORDER_STATUS_ID);
		ordersMapper.insert(orders);
		orderdetail.setId(Tools.getUUID());
		orderdetail.setOrderid(orders.getId());
		orderdetail.setDespotprice(new BigDecimal(0));
		orderdetail.setFinalprice(orders.getTotalprice());
		orderdetail.setPayStatus(0);
		orderdetail.setCurrencySign(cost.getCode());
		orderDetailMapper.insert(orderdetail);
		
		Orderexpense base = new Orderexpense();
		base.setId(Tools.getUUID());
		base.setName("签证代办费");
		base.setVisitors(orderdetail.getAdults());
		base.setPrice(orderdetail.getPrice());
		base.setOrderid(orders.getId());
		
		orderExpenseMapper.insert(base);
		ordercontacter.setId(Tools.getUUID());
		ordercontacter.setOrderid(orders.getId());
		return orderContacterMapper.insert(ordercontacter);
	}
	
	/**
	 * 根据订单编号和邮件查询出总订单
	 * 
	 * @param orderNo
	 * @param email
	 * @return
	 */
	public Orders queryByOrderNoAndEmail(String orderNo,String email){
		Orders orders = ordersMapper.findByOrderNoAndEmail(orderNo,email);
		return orders;
	}
	
	/**
	 * 根据用户id查询用户订单（会员中心）
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public List<Orders> findByMemberId(String memberid, HttpServletRequest request, Integer type) {
		List<Orders> ordersList = null;
//		String pageNow = request.getParameter("pageNow");
//		Pages page = null;
//		int totalCount = ordersMapper.getCountByMemberid(memberid);
//		if (pageNow != null) {
//			page = new Pages(totalCount, Integer.parseInt(pageNow));
//			page.setPageSize(6);
//			ordersList = ordersMapper.findByMemberId(memberid, page.getStartPos(), page.getPageSize());
//		} else {
//			page = new Pages(totalCount, 1);
//			page.setPageSize(6);
//			ordersList = ordersMapper.findByMemberId(memberid, page.getStartPos(), page.getPageSize());
//		}
		ordersList = ordersMapper.findByMemberId(memberid, 0, 0, type);
		for (Orders orders : ordersList) {
			List<Orderdetail> orderdetails = orders.getOrderdetails();
			BigDecimal despotPrice = new BigDecimal(0);
			for (Orderdetail orderdetail : orderdetails) {
				if(orderdetail.getDespotprice()!=null){
					despotPrice = despotPrice.add(orderdetail.getDespotprice());
				}
			}
			orders.setDespotPrice(despotPrice);
		}
		return ordersList;
	}
	
	/**
	 * 获取子订单详细信息
	 * 
	 * @param orderDetailId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Orderdetail getOrderDetailById(String orderDetailId){
		return orderDetailMapper.selectByPrimaryKey(orderDetailId);
	}
	
	/**
	 * 根据订单号查询获订单详细信息   (会员中心)
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Orders getMemberOrderDetailById(String id) {
		return ordersMapper.getMemberOrderDetailById(id);
	}

	/**
	 * 根据订单号查询获订单详细信息   (会员中心)
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Orders getMemberOrderDetailByOrderNo(String orderNo) {
		return ordersMapper.getMemberOrderDetailByOrderNo(orderNo);
	}
	/**
	 * 根据订单号查询(非注册用户订单查询)
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Orders finByOrderNo(String orderNo) {
		return ordersMapper.finByOrderNo(orderNo);
	}
	
	/**
	 * @Title: updateByOrderNo
	 * @Description: 支付完后修改订单状态
	 * @param orderNo
	 * @param statusId
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional
	public int updateByOrderNo(String orderNo,String statusId){
		return ordersMapper.updateByOrderNo(orderNo, statusId);
	}

	/**
	 * 非注册用户根据订单号查询获订单详细信息   (会员中心)
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Orders getOrderDetailByOrderNoAndEmail(String orderNo, String email) {
		return ordersMapper.getOrderDetailByOrderNoAndEmail(orderNo, email);
	}
	
    /**
     * @Title: findByOrdersId
     * @Description: 根据总单id查询订单详情
     * @param orderId
     * @return    
     * @return Orderdetail    返回类型
     * @author xiejin
     */
	@Transactional(readOnly = true)
    public Orderdetail findByOrderId(String orderId){
		return orderDetailMapper.findByOrderId(orderId);
	}
	
    /**
     * @Title: updateOrderDetailPart
     * @Description: 付款完成后修改订单详情
     * @param record
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int updateOrderDetailPart(Orderdetail record){
		return orderDetailMapper.updateOrderDetailPart(record);
	};
	
	/**
	 * @Title: payComplete
	 * @Description:    支付完成
	 * @return void    返回类型
	 * @author xiejin
	 * @throws ParseException 
	 */
	@Transactional
	public void payComplete(HttpServletRequest request,int payStatus,String ordersId,String orderNo,BigDecimal total_fee,int payway,String payName){
		Orders orders = findPayInfo(ordersId);
		Ordercontacter orderContacter = ordercontacterMapper.selectByOrderId(ordersId);
		String time = Tools.getDtime();
		//订单详情表
		Orderdetail orderdetail = orders.getOrderdetails().get(0);
		
	    int total = orderdetail.getAdults() + orderdetail.getChildren() + orderdetail.getBabies();
	    if(Orderstatus.NEW.equals(orders.getStatusid())){
	    	if(orders.getOccupyTime() != null){
				orders.setOccupyTime(null);
			}else{
				tourDateMapper.updateStore(orderdetail.getTourdateId(), total);
			}
		    
			BigDecimal depositePrice = orderdetail.getDespotprice();
			BigDecimal price = orderdetail.getPrice();	//订单详情总价
			BigDecimal PriceNow = depositePrice.add(total_fee);
			orderdetail.setDespotprice(PriceNow);		//设置已付价格
			orderdetail.setDepostidate(time);	//设置付款时间
			BigDecimal finalPrice = price.subtract(PriceNow);	//剩余尾款
			orderdetail.setFinalprice(finalPrice);
			orderdetail.setPayStatus(payStatus);
			orderDetailMapper.updateOrderDetailPart(orderdetail);	//修改订单详情
			//支付详情表
			PayDetail payDetail = new PayDetail();
			payDetail.setId(Tools.getUUID());
			payDetail.setOrderNo(orderNo);
			payDetail.setPayPrice(total_fee);
			payDetail.setPayTime(time);
			payDetail.setPayWay(payway);
			payDetailMapper.insert(payDetail);
			
			orders.setPayway(""+payway);
			//总订单表
			if (payStatus==3 || payStatus==4 || payStatus==5) {
				orders.setStatusid(Orderstatus.PAID);
				Integer score = orders.getScore();
				if (score!=null && !"".equals(score)) {		//判断积分是否有值
					String memberId = orders.getUserid();
					Member member = memberMapper.selectByPrimaryKey(memberId);
					if (member!=null) {		//判断是否是注册用户
						GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), orderdetail.getProduct().getName(), orderdetail.getProduct().getCode(), orders.getOrderno(), score, 0, member.getId());
						getScoreMapper.insert(getScore);
						memberinformationMapper.updateScoreByMemberId(score,memberId);
					}
				}
			}
			if (payStatus==1 || payStatus==2) {
				orders.setStatusid(Orderstatus.DEPOSIT);
			}
			if (payStatus==0) {
				orders.setStatusid(Orderstatus.NEW);
			}
			orders.setReceiveway(payName);
			ordersMapper.updateByPrimaryKey(orders);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Orderdetail orderDetail = orders.getOrderdetails().get(0);
			int totalPeople = orderDetail.getAdults() + orderDetail.getChildren() + orderDetail.getBabies();
			BigDecimal avgPrice = orderDetail.getPrice().divide(new BigDecimal(totalPeople),0,BigDecimal.ROUND_UP);
			
			List<AdditionalProduct> optionalTourList = new ArrayList<AdditionalProduct>();
			List<AdditionalProduct> optionalTourInTourlineList = new ArrayList<AdditionalProduct>();
			List<AdditionalProduct> additionalProductList = new ArrayList<AdditionalProduct>();
			List<AdditionalProduct> productList = orders.getOrderdetails().get(0).getAdditionalProductList();
			if(productList != null && productList.size() > 0 ){
				for(AdditionalProduct additionalProduct : productList){
					if(additionalProduct.getType() == 10){
						additionalProduct.setDayString(additionalProduct.getDate() != null?Tools.getEnglishShow(additionalProduct.getDate()):"");
						optionalTourList.add(additionalProduct);
					}else if(additionalProduct.getType() == 11){
						optionalTourInTourlineList.add(additionalProduct);
					}else{
						additionalProduct.setDayString(additionalProduct.getDate() != null?Tools.getEnglishShow(additionalProduct.getDate()):"");
						additionalProductList.add(additionalProduct);
					}
				}
			}
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orders", orders);		
			paramMap.put("avgPrice", avgPrice);
			paramMap.put("optionalTourList", optionalTourList);
			paramMap.put("optionalTourInTourlineList", optionalTourInTourlineList);
			paramMap.put("additionalProductList", additionalProductList);
			paramMap.put("orderContacter", orderContacter);
			paramMap.put("departure", orderdetail.getDeparture());
			try {
				paramMap.put("departureString", Tools.getEnglishShow(sdf.parse(orderDetail.getDeparturedate())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Orderexpense orderExpense : orders.getOrderexpenseList()){
				if(ExpenseName.TOUR_FEE.equals(orderExpense.getName())){
					paramMap.put("tourFee", orderExpense.getPrice());
				}else if(ExpenseName.SINGLE_SUPPLEMENTS_FEE.equals(orderExpense.getName())){
					paramMap.put("singleSupplementsFee", orderExpense.getPrice());
				}else if(ExpenseName.GUIDE_SERVE_FEE.equals(orderExpense.getName())){
					paramMap.put("guideServeFee", orderExpense.getPrice());
				}else if(ExpenseName.STEAM_FEE.equals(orderExpense.getName())){
					paramMap.put("steamFee", orderExpense.getPrice());
				}else if(ExpenseName.TRANSFER_FEE.equals(orderExpense.getName())){
					paramMap.put("transferFee", orderExpense.getPrice());
				}else if(ExpenseName.AIRLINE_TICKETS_FEE.equals(orderExpense.getName())){
					paramMap.put("airticketFee", orderExpense.getPrice());
				}else if(ExpenseName.OPTIONAL_TOUR_FEE.equals(orderExpense.getName())){
					paramMap.put("optionalTourFee", orderExpense.getPrice());
				}else if(ExpenseName.COUPON.equals(orderExpense.getName())){
					paramMap.put("couponsFee", orderExpense.getPrice());
				}else if(ExpenseName.ADDITIONAL.equals(orderExpense.getName())){
					paramMap.put("Additional", orderExpense.getPrice());
				}
			}
			final Mail gmail;
			String emailMsgTxt;
			if("AUD".equals(orderdetail.getCurrencySign())){
				gmail = new Mail("AUD");
				emailMsgTxt = FreemarkerUtils.getOrderNoticeMail(request.getSession().getServletContext(),AU_ORDER_MAIL_TEMPLATE_URL,paramMap);
			}else{
				gmail = new Mail();
				emailMsgTxt = FreemarkerUtils.getOrderNoticeMail(request.getSession().getServletContext(),TORDER_MAIL_TEMPLATE_URL,paramMap);
			}
			gmail.setSendTo(new String[]{orderContacter.getEmail()});
			gmail.setEmailSubjectTxt(orders.getOrderdetails().get(0).getProduct().getName());
			gmail.setEmailMsgTxt(emailMsgTxt);
			new Thread() {
				public void run() {
					try {
						gmail.sendSSLMessage();
					} catch (MessagingException e) {
						e.printStackTrace();
						try {
							gmail.sendSSLMessage();
						} catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							try {
								gmail.sendSSLMessage();
							} catch (MessagingException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					}
					gmail.setSendTo(new String[]{gmail.getUSERNAME()});
					try {
						gmail.sendSSLMessage();
					} catch (MessagingException e) {
						e.printStackTrace();
						try {
							gmail.sendSSLMessage();
						} catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							try {
								gmail.sendSSLMessage();
							} catch (MessagingException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					}
				}
			}.start();
	    }
	}
	
	/**
	 * @Title: payComplete
	 * @Description:    支付完成
	 * @return void    返回类型
	 * @author xiejin
	 * @throws ParseException 
	 */
	@Transactional
	public void bookComplete(HttpServletRequest request,String bookId,String bookingNo,BigDecimal total_fee,int payway,String payName){
		PreBookingOfAgent preBookingOfAgent = preBookingOfAgentMapper.selectAllById(bookId);
		String time = Tools.getDtime();
	    
		//支付详情表
		PayDetail payDetail = new PayDetail();
		payDetail.setId(Tools.getUUID());
		payDetail.setOrderNo(bookingNo);
		payDetail.setPayPrice(total_fee);
		payDetail.setPayTime(time);
		payDetail.setPayWay(payway);
		payDetailMapper.insert(payDetail);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("booking", preBookingOfAgent);		
		paramMap.put("departure", preBookingOfAgent.getDeparture());

		paramMap.put("departureString", Tools.getEnglishShow(preBookingOfAgent.getDeparturedate()));
			// TODO Auto-generated catch block

		preBookingOfAgent.setIsOnlinePay(true);	
		preBookingOfAgentMapper.updateByPrimaryKeySelective(preBookingOfAgent);
		
		final Mail gmail = new Mail();
		String emailMsgTxt = FreemarkerUtils.getOrderNoticeMail(request.getSession().getServletContext(),TBOOK_MAIL_TEMPLATE_URL,paramMap);
		gmail.setSendTo(new String[]{preBookingOfAgent.getEmail()});
		gmail.setEmailSubjectTxt(preBookingOfAgent.getProduct().getName());
		gmail.setEmailMsgTxt(emailMsgTxt);
		new Thread(){
			public void run(){
				try {
					gmail.sendSSLMessage();
				} catch (MessagingException e) {
					e.printStackTrace();
					try {
						gmail.sendSSLMessage();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						try {
							gmail.sendSSLMessage();
						} catch (MessagingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				}
				gmail.setSendTo(new String[]{gmail.getUSERNAME()});
				try {
					gmail.sendSSLMessage();
				} catch (MessagingException e) {
					e.printStackTrace();
					try {
						gmail.sendSSLMessage();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						try {
							gmail.sendSSLMessage();
						} catch (MessagingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				}
			}
		}.start();

	}
	
	/**
	 * 如果订单已取消
	 * 检查库存是否够用,如果够用返回空字符串
	 * 如果不够用,返回产品路径
	 * 
	 * @param ordersId
	 * @return
	 */
	@Transactional
	public String checkStore(String ordersId,BigDecimal price){
		Orders order = ordersMapper.selectByIdWithOccupyTime(ordersId);
		if(order == null){
			return "invalid"; //该订单不是新订单
		}else if(order.getTotalprice().compareTo(price) != 0){
			return "uncorrectprice"; //支付金额不正确
		}
		if(order.getOccupyTime() != null){
			ordersMapper.setOccupyTimeById(ordersId, new Date());
			return "success"; //成功
		}else{
			synchronized(OrdersController.sychronizedObject){
				Orderdetail orderdetail = orderDetailMapper.findWithTourdateByOrdersId(ordersId);
				int totalPeople = orderdetail.getAdults() + orderdetail.getChildren() + orderdetail.getBabies();
				int remainNumber = orderdetail.getTourdate().getRemainnum();
				if(remainNumber < totalPeople){
					return pageMapper.findFilePathByProductId(orderdetail.getProductid()); //库存不够
				}else{
					tourDateService.updateStore(orderdetail.getTourdateId(), totalPeople);
					ordersMapper.setOccupyTimeById(ordersId, new Date()); 
					return "success"; //成功
				}
			}
		}
		
	}
	
	/**
	 * @Title: countPrice
	 * @Description: 根据付款方式计算价格
	 * @param orderNo
	 * @param payStatus
	 * @return    
	 * @return BigDecimal    返回类型
	 * @author xiejin
	 */
	public BigDecimal countPrice(String ordersId,int payStatus){
		BigDecimal countPrice = null;
		Orders orders = ordersMapper.selectByPrimaryKey(ordersId);	
		String id = orders.getId();
		//订单详情
		Orderdetail orderdetail = orderDetailMapper.findByOrderId(id);
		BigDecimal price = orderdetail.getPrice();		//订单价格
		BigDecimal finalPrice = orderdetail.getFinalprice();	//剩余金额
		
		//币种换算
		String currencySign = orderdetail.getCurrencySign();
		int lowPrice = 300;			//300美金
		Cost cost = costMapper.findByCode(currencySign);
		if (cost != null) {
			BigDecimal exchangeRate = cost.getExchangerate();
			lowPrice = new BigDecimal(300).multiply(exchangeRate).intValue();	//转换其他金额币种
		}
		
		int babyNumber = 0;
		if(orderdetail.getBabies() != null){
			babyNumber = orderdetail.getBabies();
		}
		int adultsNumber = 0;
		if(orderdetail.getAdults() != null){
			adultsNumber = orderdetail.getAdults();
		}
		int childrenNumber = 0;
		if(orderdetail.getChildren() != null){
			childrenNumber = orderdetail.getChildren();
		}
		int totalNum = adultsNumber + babyNumber + childrenNumber;	//总人数
		switch (payStatus) {
		case 1:
			countPrice = new BigDecimal(lowPrice).multiply(new BigDecimal(totalNum));
			break;
		case 2:
			countPrice = new BigDecimal((int)(finalPrice.intValue()*0.5));
			break;
		case 3:
			countPrice = finalPrice;
			break;
		case 4:
			countPrice = finalPrice;
			break;
		case 5:
			countPrice = price;
		}
		return countPrice;
	}
	
	/**
	 * 更新总订单
	 * 
	 * @param order
	 */
	public void update(Orders order){
		ordersMapper.updateByPrimaryKeySelective(order);
	}
	
	
	/**
	 * 根据订单编号查询到联系人
	 * 
	 * @return
	 */
	public Ordercontacter findContacterByOrdersId(String ordersId){
		return orderContacterMapper.findByOrdersId(ordersId);
	}
	
	/**
	 * 将十分钟前的订单所占的库存返回回去
	 */
	@Transactional
	public void autoReturnStore(){
	   	List<String> orderIds = ordersMapper.findCancelingOrders(10);
	   	if(orderIds != null && orderIds.size() > 0){
		   	ordersMapper.setOccupyTimeByIds(orderIds, null);
		   	ordersMapper.returnStores(orderIds);
	   	}
	}
	
	@Transactional
	public void setOccupyTime(String orderId,Date occupyTime){
		ordersMapper.setOccupyTimeById(orderId, occupyTime);
	}
	
	@Transactional
	public void cancelOrders(String orderId){
		List<String> orderIds = new ArrayList<String>();
		orderIds.add(orderId);
		ordersMapper.cancelOrders(orderIds);
	}
}

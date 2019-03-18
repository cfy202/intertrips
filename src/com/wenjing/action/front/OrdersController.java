package com.wenjing.action.front;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.DateEditor;
import com.wenjing.ExpenseName;
import com.wenjing.PayAttributes;
import com.wenjing.dao.AirTicketPriceMapper;
import com.wenjing.dao.DepartureMapper;
import com.wenjing.entity.AdditionalProduct;
import com.wenjing.entity.AgentCode;
import com.wenjing.entity.Orderdetail;
import com.wenjing.entity.Orderexpense;
import com.wenjing.entity.Orders;
import com.wenjing.entity.Orderstatus;
import com.wenjing.entity.PreBookingOfAgent;
import com.wenjing.entity.ShoppingCart;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourline;
import com.wenjing.exception.OrderException;
import com.wenjing.service.AgentCodeService;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.ItineraryService;
import com.wenjing.service.MemberService;
import com.wenjing.service.OrderService;
import com.wenjing.service.PageService;
import com.wenjing.service.ProductService;
import com.wenjing.service.RegionService;
import com.wenjing.service.ReviewService;
import com.wenjing.service.ServiceItemService;
import com.wenjing.service.ShoppingCartService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourlineService;
import com.wenjing.service.VideoService;
import com.wenjing.util.Tools;
import com.wenjing.vo.BookTourVO;

/**
 * 订单模块
 * 
 * @author Jared
 *
 * May 26, 2015
 */
@Controller("frontOrders")
@RequestMapping("/front/orders")
public class OrdersController {
	
	public static final Object sychronizedObject = new Object();

	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *        WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Resource
	private CostService costService;
	@Resource
	private TourlineService tourlineService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ServiceItemService serviceItemService;
	@Autowired	
	private ItineraryService itineraryService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private PageService pageService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private TourDateService tourDateService;
	@Autowired
	private ProductService productService;
	@Autowired
	private DepartureMapper departureMapper;
	@Autowired
	private AirTicketPriceMapper airTicketPriceMapper;
	@Autowired
	private AgentCodeService agentCodeService;
	@Autowired
	private MemberService memberService;

//	/**
//	 * 根据tourDateId获取出发地
//	 * 
//	 * @param tourDateId
//	 * @return
//	 */
//	@RequestMapping(value="/getDepartures",method = RequestMethod.POST)
//	public @ResponseBody List<Departure> getDeparturesByTourDateId(String tourDateId){
//		return orderService.getDeparturesByTourDateId(tourDateId);
//	}
	
	/**
	 * 订购产品
	 * 
	 * @param shoppingCart
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/bookTour",method = RequestMethod.POST)
	public String bookTour(ShoppingCart shoppingCart,HttpServletRequest request,Model model){  
//	    Tourline tourline = tourlineService.findByProductId(shoppingCart.getProductId());
//		Tourprice tourprice = tourDateService.getTourPriceByTourdateid(shoppingCart.getTourDateId());
//		tourprice = tourlineService.changeTourPrice(tourprice, tourline.getId(), tourline.getCostnumber());
//		Cost cost = costService.findById(tourline.getCostnumber());
		request.getSession().setAttribute("bookingTourName","tourname");
		model.addAttribute("shoppingCart", shoppingCart);
		if(!StringUtils.isEmpty(shoppingCart.getAgentCode())){
			AgentCode agentCode = agentCodeService.findByLowerCode(shoppingCart.getAgentCode().toLowerCase().trim());
			if(agentCode != null){
				return "/front/order/agentBooking.ftl";
			}
		}
		return "/front/order/bookTour.ftl";
	}	
	
	/**
	 * 提交订单
	 * 
	 * @param bookTourVO
	 * @return
	 */
	@RequestMapping(value="/confirmOrder",method = RequestMethod.POST)
	public String confirmOrder(BookTourVO bookTourVO,HttpServletRequest request,HttpServletResponse response,Model model){
		String bookingTourName = (String)request.getSession().getAttribute("bookingTourName");
		if(StringUtils.isEmpty(bookingTourName)){
			model.addAttribute("errorMsg", "Please do not repeat submit.");
			return "/front/order/error.ftl";
		}
		request.getSession().removeAttribute("bookingTourName");
		BookTourVO bookInfo;
		try {
			memberService.getCurrentMember(request,response,bookTourVO);
			bookInfo = orderService.submitBookTour(bookTourVO, request,response);
		} catch (OrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    if ("sold_out".equals(e.getErrorMessage()))
		          return "redirect:" + bookTourVO.getProduct().getPagePageid().getFilepath() + "?soldout";
		    else if("incorrect_price".equals(e.getErrorMessage()) || "incorrect_date_format".equals(e.getErrorMessage())){
				model.addAttribute("errorMsg", "Parameter error.");
			}
			return "/front/order/error.ftl";
		}catch (Exception e){
			e.printStackTrace();
			model.addAttribute("errorMsg", "Parameter error.");
			return "/front/order/error.ftl";
		}
		BigDecimal avgPrice = bookInfo.getTotalPrice().divide(new BigDecimal(bookInfo.getTotalNumber()), 0,BigDecimal.ROUND_UP);
		bookInfo.setAvgPrice(avgPrice);
		bookInfo.setDepartureString(Tools.getEnglishShow(bookInfo.getDepartureDate()));
		request.getSession().setAttribute("bookInfo", bookInfo);
		return "redirect:/payment.htm?orderId=" + bookInfo.getOrdersId();
	}	
	
	/**
	 * agent订单提交
	 * 
	 * @param preBookingOfAgent
	 * @return
	 */
	@RequestMapping(value="/agentBooking",method = RequestMethod.POST)
	public String agentBooking(HttpServletRequest request,Model model,PreBookingOfAgent preBookingOfAgent){
		String bookingTourName = (String)request.getSession().getAttribute("bookingTourName");
		if(StringUtils.isEmpty(bookingTourName)){
			model.addAttribute("errorMsg", "Please do not repeat submit.");
			return "/front/order/error.ftl";
		}
		Tourdate tourdate = tourDateService.findByTourDateId(preBookingOfAgent.getTourdateid());
		Tourline tourline = tourlineService.findById(preBookingOfAgent.getTourlineId());
		String filePath = orderService.agentBooking(tourdate,tourline,preBookingOfAgent);
		if(preBookingOfAgent.getType() != null && !filePath.endsWith("?soldout")){
			return "redirect:/book_complete.htm";
		}
		
		return "redirect:" + filePath;
	}
	
	/**
	 * 
	 * @param request
	 * @param orderId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/agentPay",method = RequestMethod.GET)
	public String payComplete(HttpServletRequest request,String bookId,Model model){
		PreBookingOfAgent booking = orderService.getAgentBookingById(bookId);
		booking.setDepartureDate(Tools.getEnglishShow(booking.getDeparturedate()));
		String[] expiraDate = booking.getExpirationdate().split("/");
		booking.setExpirationDateMonth(expiraDate[0]);
		booking.setExpirationDateYear(expiraDate[1]);
		model.addAttribute("booking", booking);
		model.addAttribute("paypalGateway", PayAttributes.PAYPALURL);
		model.addAttribute("loginId",PayAttributes.LOGINID);
		model.addAttribute("simGateway", PayAttributes.SIMGATEWAY);
		model.addAttribute("paypalAccount", PayAttributes.getPayAccount("USD"));
		pageService.getNavigation(model, booking.getCostnumber());
		return "/front/order/bookPayment.ftl";
	}
	
	/**
	 * 预订成功
	 * 
	 * @return
	 */
	@RequestMapping("/booksuccess")
	public String booksuccess(){
		return "/front/payNotice/bookComplete.ftl";
	}
	
	/**
	 * 支付
	 * @param orderId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/pay",method = RequestMethod.GET)
	public String toPay(HttpServletRequest request,String orderId,Model model) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Orders orders = orderService.findPayInfo(orderId);
		if(orders == null){
			model.addAttribute("noticeString", "The order does not exist.");
			return "/front/payNotice/tourFail.ftl"; 
		}else if(!Orderstatus.NEW.equals(orders.getStatusid())){
			model.addAttribute("noticeString", "Your order has been paid or cancelled.");
			return "/front/payNotice/tourFail.ftl"; 
		}
		Orderdetail orderDetail = orders.getOrderdetails().get(0); 
		
		String filePath = productService.findFilePathByProductId(orderDetail.getProductid());
		int totalPeople = orderDetail.getAdults() + orderDetail.getChildren() + orderDetail.getBabies();

		if(orders.getOccupyTime() == null){
			//如果订单是新订单
			if(!StringUtils.isEmpty(orderDetail.getTourdateId())){
				   synchronized(sychronizedObject){
						Tourdate tourdate = tourDateService.findByTourDateId(orderDetail.getTourdateId());
						if(tourdate.getRemainnum()  == null || tourdate.getRemainnum() < totalPeople){
							return "redirect:" + filePath + "?soldout";
						}else{
							//减去库存
							tourDateService.updateStore(orderDetail.getTourdateId(), totalPeople);
							orderService.setOccupyTime(orderId, new Date());
						}
					}
			}else{
				return "redirect:" + filePath + "?invalid";
			}
		}else{
			orderService.setOccupyTime(orderId, new Date());
		}
		
		BigDecimal avgPrice = orderDetail.getPrice().divide(new BigDecimal(totalPeople), 0,BigDecimal.ROUND_UP);
		String areaSuffix = orders.getCost().getCode().toLowerCase().substring(0, 2);
		
		for(Orderexpense orderExpense : orders.getOrderexpenseList()){
			if(ExpenseName.TOUR_FEE.equals(orderExpense.getName())){
				model.addAttribute("tourFee", orderExpense.getPrice());
			}else if(ExpenseName.SINGLE_SUPPLEMENTS_FEE.equals(orderExpense.getName())){
				model.addAttribute("singleSupplementsFee", orderExpense.getPrice());
			}else if(ExpenseName.GUIDE_SERVE_FEE.equals(orderExpense.getName())){
				model.addAttribute("guideServeFee", orderExpense.getPrice());
			}else if(ExpenseName.STEAM_FEE.equals(orderExpense.getName())){
				model.addAttribute("steamFee", orderExpense.getPrice());
			}else if(ExpenseName.TRANSFER_FEE.equals(orderExpense.getName())){
				model.addAttribute("transferFee", orderExpense.getPrice());
			}else if(ExpenseName.AIRLINE_TICKETS_FEE.equals(orderExpense.getName())){
				model.addAttribute("airticketFee", orderExpense.getPrice());
			}else if(ExpenseName.OPTIONAL_TOUR_FEE.equals(orderExpense.getName())){
				model.addAttribute("optionalTourFee", orderExpense.getPrice());
			}else if(ExpenseName.COUPON.equals(orderExpense.getName())){
				model.addAttribute("couponsFee", orderExpense.getPrice());
			}else if(ExpenseName.ADDITIONAL.equals(orderExpense.getName())){
				model.addAttribute("Additional", orderExpense.getPrice());
			}
		}
		
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
		model.addAttribute("sendMail", 0);
		model.addAttribute("optionalTourList",optionalTourList);
		model.addAttribute("optionalTourInTourlineList", optionalTourInTourlineList);
		model.addAttribute("additionalProductList", additionalProductList);
		model.addAttribute("orders", orders);
		model.addAttribute("departureString", Tools.getEnglishShow(sdf.parse(orderDetail.getDeparturedate())));
		model.addAttribute("avgPrice", avgPrice);
		model.addAttribute("areaSuffix", areaSuffix);
		model.addAttribute("paypalGateway", PayAttributes.PAYPALURL);
		model.addAttribute("loginId",PayAttributes.LOGINID);
		model.addAttribute("simGateway", PayAttributes.SIMGATEWAY);
		model.addAttribute("paypalAccount", PayAttributes.getPayAccount(orders.getCost().getCode()));
		pageService.getNavigation(model, orders.getCost().getId());
//		orderService.payComplete(request, 5, orders.getId(), orders.getOrderno(), orderDetail.getPrice(), 1,"");
		return "/front/order/tourPayment_again.ftl";
	}
	
	/**
	 * 发送邮件
	 * @return
	 */
//	@RequestMapping(value="/sendMail",method = RequestMethod.GET)
//	public @ResponseBody String sendMail(HttpServletRequest request){
//		BookTourVO bookInfo = (BookTourVO)request.getSession().getAttribute("bookInfo");
//		request.getSession().removeAttribute("bookInfo");
//		if(bookInfo != null){
//			Mail gmail = new Mail();
//			gmail.setSendTo(new String[]{bookInfo.getEmail()});
//			gmail.setEmailSubjectTxt(bookInfo.getProduct().getName());
//			gmail.setCopyto(new String[]{Mail.USERNAME});
//			Map<String,Object> paramMap = new HashMap<String,Object>();
//			paramMap.put("bookInfo", bookInfo);
//			String emailMsgTxt = FreemarkerUtils.getOrderNoticeMail(request.getSession().getServletContext(),ORDER_MAIL_TEMPLATE_URL,paramMap);
//			gmail.setEmailMsgTxt(emailMsgTxt);
//			try {
//				gmail.sendSSLMessage(); 
//			} catch (MessagingException e) {
//				e.printStackTrace();
//				try {
//					gmail.sendSSLMessage(); 
//				} catch (MessagingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					try {
//						gmail.sendSSLMessage();
//					} catch (MessagingException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//						return "error";
//					}
//				}
//			}
//		}
//		return "ok";
//	}
	
//	/**
//	 * 支票订单的结束提醒页面
//	 */
//	@RequestMapping(value="/finishChequeOrder",method = RequestMethod.POST)
//	public String chequeOrder(String payway,String orderNo,Model model){
//		Orders order = orderService.finByOrderNo(orderNo);
//		order.setPayway(payway);
//		orderService.update(order);
//		model.addAttribute("orders", order);
//		return "/front/order/chequeOrderNotice.ftl";
//	}
	
	/**
	 * 跳至订单查询页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/goForQueryOrders",method = RequestMethod.GET)
	public String goForQueryOrders(){
		return "/front/order/showOrders.ftl";
	}
	
	/**
	 * 根据订单编号或邮箱查询总订单
	 * 
	 * @param orderId
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/queryOrdersByOrderNoAndEmail", method = RequestMethod.POST)
	public @ResponseBody Orders queryOrdersByOrderNoAndEmail(String orderNo,String email,Model model){
		return orderService.queryByOrderNoAndEmail(orderNo, email);
	}
	
	/**
	 * 根据子订单ID查询出子订单详情
	 * 
	 * @param orderDetailId
	 * @return
	 */
	@RequestMapping(value="/detail",method = RequestMethod.GET)
	public String getOrderdetail(String orderDetailId,Model model){
		model.addAttribute("orderDetail", orderService.getOrderDetailById(orderDetailId));
		return "/front/order/showOrderDetail.ftl";
	}
	
	/**
	 * 
	 * @param ordersId
	 * @return
	 */
	@RequestMapping(value="/checkStore",method=RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public @ResponseBody String checkStore(String ordersId,BigDecimal price){
		String result = orderService.checkStore(ordersId,price);
		if("success".equals(result)){
			return "";
		}else if("uncorrectprice".equals(result)){
			return "-1";
		}else if("invalid".equals(result)){
			return "-2";
		}else{
			return result + "?soldout";
		}
	}
	
//	/**
//	 * @Title: testPay
//	 * @Description: 测试
//	 * @return    
//	 * @return String    返回类型
//	 * @author xiejin
//	 */
//	@RequestMapping(value="/test")
//	public String testPay(Model model){
//		Fingerprint fingerprint = Fingerprint.createFingerprint(PayAttributes.LOGINID,
//				PayAttributes.TRANSACTIONKEY, 1, "0.01","USD");
//		model.addAttribute("loginId", PayAttributes.LOGINID);
//		model.addAttribute("fingerprint", fingerprint);
//		model.addAttribute("simGateway",PayAttributes.SIMGATEWAY);
//		model.addAttribute("transactionKey",PayAttributes.TRANSACTIONKEY);
//		return "/front/order/testPay.ftl";
//	}
	
//	/**
//	 * @Title: payAgain
//	 * @Description: 用户在中心支付
//	 * @return    
//	 * @return String    返回类型
//	 * @author xiejin
//	 */
//	@RequestMapping(value="/payAgain")
//	public String payAgain(@RequestParam("orderNo")String orderNo,Model model){
//		Orders order = orderService.finByOrderNo(orderNo); 
//		Orderdetail orderdetail = orderService.findByOrderId(order.getId());	//订单详情
//		int payStatus = orderdetail.getPayStatus();
//		BigDecimal price = orderdetail.getPrice();	//单订价格
//		BigDecimal finalPrice = orderdetail.getFinalprice();	//剩余金额
//		int departuredateInt = Tools.getTimestemp(orderdetail.getDeparturedate());	//出发日期时间戳
//		int timeNow = Tools.getTimestemp(Tools.getTime());		//当前日期时间戳
//		int subtime = departuredateInt - timeNow;		//时间差
//		
//		String currencySign = orderdetail.getCurrencySign();
//		int lowPrice = 300;			//300美金
//		int upPrice = 2000;			//2000美金
//		Cost cost = costService.findByCode(currencySign);
//		if (cost != null) {
//			BigDecimal exchangeRate = cost.getExchangerate();
//			lowPrice = new BigDecimal(300).multiply(exchangeRate).intValue();	//转换其他金额币种
//			upPrice = new BigDecimal(2000).multiply(exchangeRate).intValue();	//转换其他金额币种
//		}
//		
//		int babyNumber = 0;
//		if(orderdetail.getBabies() != null){
//			babyNumber = orderdetail.getBabies();
//		}
//		int adultsNumber = 0;
//		if(orderdetail.getAdults() != null){
//			adultsNumber = orderdetail.getAdults();
//		}
//		int childrenNumber = 0;
//		if(orderdetail.getChildren() != null){
//			childrenNumber = orderdetail.getChildren();
//		}
//		int totalNum = adultsNumber + babyNumber + childrenNumber;	//总人数
//		int avgPrice = orderdetail.getPrice().divide(BigDecimal.valueOf(totalNum),0,BigDecimal.ROUND_HALF_DOWN).intValue();	//平均价
//		if (payStatus ==0) {//订单未支付
//			orderNo = orderNo+"_1";
//			orderdetail.setFullPrice(price);
//			if (subtime/(24*3600)>45) {
//				if (avgPrice>lowPrice) {
//					orderdetail.setPrePrice(new BigDecimal(lowPrice).multiply(new BigDecimal(totalNum)));	//设置预付金额
//				}
//			}
//		}
//		if (payStatus == 1) {//支付订金
//			orderNo = orderNo+"_2";
//			orderdetail.setTotalFinalPrice(finalPrice);
//			if (subtime/(24*3600)>45) {
//				if (avgPrice>upPrice) {
//					BigDecimal finaPrice1 = new BigDecimal((int)(finalPrice.intValue()*0.5));	
//					orderdetail.setFinalPrice1(finaPrice1);	//设置部分尾款
//				}
//			}
//		}
//		if (payStatus == 2) {//支付部分尾款
//			orderNo = orderNo+"_3";
//			orderdetail.setFinalPrice2(finalPrice);	//设置剩余尾款
//		}
//		order.setOrderno(orderNo);
//		model.addAttribute("orderdetail", orderdetail);
//		model.addAttribute("order", order);
//		if ("CAD".equals(currencySign)) {
//			model.addAttribute("paypalAccount", BUSINESS_ACCOUNT_CA);
//		}
//		if ("USD".equals(currencySign) || "AUD".equals(currencySign)||"EUR".equals(currencySign)) {
//			model.addAttribute("paypalAccount", BUSINESS_ACCOUNT_US);
//		}
//		model.addAttribute("paypalGateway", PAYPALURL);
//		return "/front/order/payAgain.ftl";
//	}
	
//	/**
//	 * @Title: getFingerprint
//	 * @Description: 异步获取fingerprint
//	 * @param price
//	 * @param currencySign
//	 * @return    
//	 * @return Fingerprint    返回类型
//	 * @author xiejin
//	 */
//	@ResponseBody
//	@RequestMapping("/getFingerprint")
//	public Fingerprint getFingerprint(@RequestParam("price") String price,
//			@RequestParam("currencySign") String currencySign){
//		Fingerprint fingerprint = Fingerprint.createFingerprint(PayAttributes.LOGINID,
//				PayAttributes.TRANSACTIONKEY, 1, price,currencySign);
//		return fingerprint;
//	}
	
//	/**
//	 * @Title: payAgain
//	 * @Description: 用户在中心支付
//	 * @return    
//	 * @return String    返回类型
//	 * @author xiejin
//	 */
//	@RequestMapping(value="/payAgain")
//	public String payAgain(@RequestParam("orderNo")String orderNo,Model model){
//		Orders order = orderService.finByOrderNo(orderNo); 
//		Orderdetail orderdetail = orderService.findByOrderId(order.getId());	//订单详情
//		BigDecimal price = orderdetail.getPrice();	//单订价格
//		orderdetail.setFullPrice(price);
//		String currencySign = orderdetail.getCurrencySign();
//		orderNo = orderNo+"_1";
//		order.setOrderno(orderNo);
//		model.addAttribute("orderdetail", orderdetail);
//		model.addAttribute("order", order);
//		if ("CAD".equals(currencySign)) {
//			model.addAttribute("paypalAccount", PayAttributes.BUSINESS_ACCOUNT_CA);
//		}
//		if ("USD".equals(currencySign) || "AUD".equals(currencySign)) {
//			model.addAttribute("paypalAccount", PayAttributes.BUSINESS_ACCOUNT_US);
//		}
//		if ("EUR".equals(currencySign)) {
//			model.addAttribute("paypalAccount", PayAttributes.BUSINESS_ACCOUNT_EUR);
//		}
//		model.addAttribute("paypalGateway", PayAttributes.PAYPALURL);
//		return "/front/order/payAgain.ftl";
//	}
}
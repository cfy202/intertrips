package com.wenjing.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wenjing.Pages;
import com.wenjing.entity.AdditionalProduct;
import com.wenjing.entity.Cancelrecords;
import com.wenjing.entity.Cost;
import com.wenjing.entity.OrderAttachment;
import com.wenjing.entity.Orderexpense;
import com.wenjing.entity.Orders;
import com.wenjing.service.CancelRecordService;
import com.wenjing.service.CostService;
import com.wenjing.service.OrderAttachmentService;
import com.wenjing.service.OrderExpenseService;
import com.wenjing.service.OrderService;
import com.wenjing.service.OrderStatusService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;
import com.wenjing.vo.OrderQueryVO;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/**
 * 订单管理后台
 * 
 * @author Jared
 *
 * May 26, 2015
 */
@Controller
@RequestMapping("/admin/orders") 
public class OrdersController {
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final TemplateHashModel CONSTANT;
	static {
		TemplateHashModel constant = null;
		try {
			constant = BeansWrapper.getDefaultInstance().getStaticModels();
			constant = (TemplateHashModel) constant.get("com.wenjing.Constant");
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		CONSTANT = constant;
	}
	
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
	private CostService costService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private OrderExpenseService orderExpenseService;
	
	@Autowired
	private CancelRecordService cancelrecordService;
	
	@Resource
	private OrderAttachmentService orderAttachmentService;
	
	
	/**
	 * 后台订单管理加载订单list
	 * 
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/list")
	public String findOrders(Model model,OrderQueryVO orderQueryVO,HttpServletRequest request,HttpServletResponse response) throws ParseException{
		List<Cost> costlist = (List<Cost>) request.getSession().getAttribute("cost");
		
		String pageNow = orderQueryVO.getPageNow();
		String pageSize = orderQueryVO.getPageSize(); 
		
		/**
		 * 获取传递过来的参数，如果不存在于cookie中获取，获取到设入到参数对象中
		 * 如果存在设入到cookie中，并将初始页设置为第一页
		 */
		//订单编号
		String orderNo = WebUtils.getCookie(request, "orderNo");
		if(orderQueryVO.getOrderNo() == null || "".equals(orderQueryVO.getOrderNo())){
			if(orderNo != null && !"".equals(orderNo)){
				orderQueryVO.setOrderNo(orderNo);
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			//如果传来参数与cookie中的不相同,则搜索参数变动过,页数变为1
			if(!orderQueryVO.getOrderNo().equals(orderNo)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "orderNo", orderQueryVO.getOrderNo());
		}
		//产品编号或者产品CODE
		String productNoOrCode = WebUtils.getCookie(request, "productNoOrCode");
		if(orderQueryVO.getProductNoOrCode() == null || "".equals(orderQueryVO.getProductNoOrCode())){
			if(productNoOrCode != null && !"".equals(productNoOrCode)){
				orderQueryVO.setProductNoOrCode(productNoOrCode);
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(!orderQueryVO.getProductNoOrCode().equals(productNoOrCode)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "productNoOrCode", orderQueryVO.getProductNoOrCode());
		}
		//联系人名称
		String contacterName = WebUtils.getCookie(request, "contacterName");
		if(orderQueryVO.getContacterName() == null || "".equals(orderQueryVO.getContacterName())){
			if(contacterName != null && !"".equals(contacterName)){
				orderQueryVO.setContacterName(contacterName);
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(!orderQueryVO.getContacterName().equals(contacterName)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "contacterName", orderQueryVO.getContacterName());
		}
		//订单状态ID
		String orderStatusId = WebUtils.getCookie(request, "orderStatusId");
		if(orderQueryVO.getOrderStatusId() == null || "".equals(orderQueryVO.getOrderStatusId())){
			if(orderStatusId != null && !"".equals(orderStatusId)){
				orderQueryVO.setOrderStatusId(orderStatusId);
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(!orderQueryVO.getOrderStatusId().equals(orderStatusId)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "orderStatusId", orderQueryVO.getOrderStatusId());
		}
		//出发日期的最小日期
		String departureDateBeforeLimit = WebUtils.getCookie(request, "departureDateBeforeLimit");
		if(orderQueryVO.getDepartureDateBeforeLimit() == null){
			if(departureDateBeforeLimit != null){
				orderQueryVO.setDepartureDateBeforeLimit(DEFAULT_DATE_FORMAT.parse(departureDateBeforeLimit));
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(departureDateBeforeLimit != null && !"".equals(departureDateBeforeLimit) && !orderQueryVO.getDepartureDateBeforeLimit().equals(DEFAULT_DATE_FORMAT.parse(departureDateBeforeLimit))){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "departureDateBeforeLimit", DEFAULT_DATE_FORMAT.format(orderQueryVO.getDepartureDateBeforeLimit()));
		}
		//出发日期的最大日期
		String departureDateAfterLimit = WebUtils.getCookie(request, "departureDateAfterLimit");
		if(orderQueryVO.getDepartureDateAfterLimit() == null){
			if(departureDateAfterLimit != null){
				orderQueryVO.setDepartureDateAfterLimit(DEFAULT_DATE_FORMAT.parse(departureDateAfterLimit));
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(departureDateAfterLimit != null && !"".equals(departureDateAfterLimit) && !orderQueryVO.getDepartureDateAfterLimit().equals(departureDateAfterLimit)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "departureDateAfterLimit", DEFAULT_DATE_FORMAT.format(orderQueryVO.getDepartureDateAfterLimit()));
		}
		//下单日期的最小日期
		String bookingTimeBeforeLimit = WebUtils.getCookie(request, "bookingTimeBeforeLimit");
		if(orderQueryVO.getBookingTimeBeforeLimit() == null){
			if(bookingTimeBeforeLimit != null){
				orderQueryVO.setBookingTimeBeforeLimit(DEFAULT_DATE_FORMAT.parse(bookingTimeBeforeLimit));
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(bookingTimeBeforeLimit != null && !"".equals(bookingTimeBeforeLimit) && !orderQueryVO.getBookingTimeBeforeLimit().equals(bookingTimeBeforeLimit)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingTimeBeforeLimit", DEFAULT_DATE_FORMAT.format(orderQueryVO.getBookingTimeBeforeLimit()));
		}
		//下单日期的最大日期
		String bookingTimeAfterLimit = WebUtils.getCookie(request, "bookingTimeAfterLimit");
		if(orderQueryVO.getBookingTimeAfterLimit() == null){
			if(bookingTimeAfterLimit != null){
				orderQueryVO.setBookingTimeAfterLimit(DEFAULT_DATE_FORMAT.parse(bookingTimeAfterLimit));
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(bookingTimeAfterLimit != null && !"".equals(bookingTimeAfterLimit) && !orderQueryVO.getBookingTimeAfterLimit().equals(DEFAULT_DATE_FORMAT.parse(bookingTimeAfterLimit))){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingTimeAfterLimit", DEFAULT_DATE_FORMAT.format(orderQueryVO.getBookingTimeAfterLimit()));
		}
		//运营中心
		String costId = WebUtils.getCookie(request, "costId");
		if(StringUtils.isEmpty(orderQueryVO.getCostId())){
			if(!StringUtils.isEmpty(costId)){
				orderQueryVO.setCostId(costId);
			}else if("".equals(orderNo)){
				pageNow = "1";
			}
		}else{
			if(!StringUtils.isEmpty(costId) && !orderQueryVO.getCostId().equals(costId)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "costId", orderQueryVO.getCostId());
		}
		
		/**
		 * 如果页容量参数为空，则在cookie里查找，如果不存在，则设为10
		 */
		if(pageSize == null || "".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "orderTpageSize");
	        if(pageSize == null || "".equals(pageSize)){
	        	pageSize = "10";
	        }
		}else{
			WebUtils.addCookie(request, response, "orderTpageSize", pageSize);
			pageNow = 1 + "";
		}
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "orderTpageNow");
		} else {
			WebUtils.addCookie(request, response, "orderTpageNow", pageNow);
		}
		Map<String,Object> parametersMap = new HashMap<String,Object>();
		parametersMap.put("orderNo", orderQueryVO.getOrderNo());
		parametersMap.put("productNoOrCode", orderQueryVO.getProductNoOrCode());
		parametersMap.put("contacterName", orderQueryVO.getContacterName());
		parametersMap.put("orderStatusId", orderQueryVO.getOrderStatusId());
		parametersMap.put("departureDateBeforeLimit", orderQueryVO.getDepartureDateBeforeLimit());
		parametersMap.put("departureDateAfterLimit", orderQueryVO.getDepartureDateAfterLimit());
		parametersMap.put("bookingTimeBeforeLimit", orderQueryVO.getBookingTimeBeforeLimit());
		parametersMap.put("bookingTimeAfterLimit", orderQueryVO.getBookingTimeAfterLimit());
		if(StringUtils.isEmpty(orderQueryVO.getCostId())){
		    for(Cost cost : costlist){
		    	if("USD".equals(cost.getCode())){
		    		orderQueryVO.setCostId(cost.getId());
		    		break;
		    	}
		    }
		    if(StringUtils.isEmpty(orderQueryVO.getCostId())){
		    	orderQueryVO.setCostId(costlist.get(0).getId());
		    }
		}
		parametersMap.put("costNumber", orderQueryVO.getCostId());
		
		Pages page = null;
		List<Orders> orderList;
		int totalCount = orderService.getOrdersTotalNumber(parametersMap);
		Orders orders = orderService.getTotalCollectionAndTotalPeople(parametersMap);
		if (pageNow != null && !"".equals(pageNow)) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
		} 
		parametersMap.put("startPos", page.getStartPos());
		parametersMap.put("pageSize", page.getPageSize());
		orderList = orderService.getOrdersByPage(parametersMap);
		model.addAttribute("orderStatusId", orderQueryVO.getOrderStatusId());
		model.addAttribute("orderQueryVO", orderQueryVO);
		model.addAttribute("page", page);
		model.addAttribute("orderStatusList", orderStatusService.findAll());
		model.addAttribute("ordersList", orderList);
		model.addAttribute("costList", costlist);
		if(orders != null){
			model.addAttribute("totalCollection", orders.getTotalprice());
			model.addAttribute("totalPeople", orders.getQuantity());
		}
		return "/admin/manage/orders/list.ftl";
	}
	
	/**
	 * 展示总订单详情
	 * 
	 * @param ordersId
	 * @return
	 */
	@RequestMapping(value="/showOrders", method = RequestMethod.GET)
	public String showOrders(String ordersId,Model model){
		Orders orders = orderService.getMemberOrderDetailById(ordersId);
		
		List<AdditionalProduct> optionalTourList = new ArrayList<AdditionalProduct>();
		List<AdditionalProduct> optionalTourInTourlineList = new ArrayList<AdditionalProduct>();
		List<AdditionalProduct> additionalProductList = new ArrayList<AdditionalProduct>();
		
		BigDecimal optionalTourFee = new BigDecimal(0);
		BigDecimal optionalTourInTourlineFee = new BigDecimal(0);
		BigDecimal additionalProductFee = new BigDecimal(0);
		
		List<AdditionalProduct> productList = orders.getOrderdetails().get(0).getAdditionalProductList();
		if(productList != null && productList.size() > 0 ){
			for(AdditionalProduct additionalProduct : productList){
				if(additionalProduct.getType() == 10){
					optionalTourList.add(additionalProduct);
					optionalTourFee = optionalTourFee.add(additionalProduct.getPrice());
				}else if(additionalProduct.getType() == 11){
					optionalTourInTourlineList.add(additionalProduct);
					optionalTourInTourlineFee = optionalTourInTourlineFee.add(additionalProduct.getPrice());
				}else{
					additionalProductList.add(additionalProduct);
					additionalProductFee = additionalProductFee.add(additionalProduct.getPrice());
				}
			}
		}		
		
		List<OrderAttachment> orderAttachmentList = new ArrayList<OrderAttachment>();
		orderAttachmentList=orderAttachmentService.findByOrdersId(ordersId);
		
		model.addAttribute("optionalTourList",optionalTourList);
		model.addAttribute("optionalTourInTourlineList", optionalTourInTourlineList);
		model.addAttribute("additionalProductList", additionalProductList);
		model.addAttribute("optionalTourFee",optionalTourFee);
		model.addAttribute("optionalTourInTourlineFee", optionalTourInTourlineFee);
		model.addAttribute("additionalProductFee", additionalProductFee);		
		model.addAttribute("orders", orders);
		model.addAttribute("orderAttachmentList",orderAttachmentList);
		return "/admin/manage/orders/showOrders.ftl";
	}
	
	/**
	 * 显示订单的费用
	 * 
	 * @param ordersId
	 * @return
	 */
	@RequestMapping(value="/showOrderBills", method = RequestMethod.GET)
	public String showOrdersBills(String ordersId,Model model){
		List<Orderexpense> orderExpenses = orderExpenseService.findByOrderId(ordersId);
		model.addAttribute("orderExpenses", orderExpenses);
		return "/admin/manage/orders/showOrderBills.ftl";
	}
	
	/**
	 * 同步线路订单
	 * 
	 * @return
	 */
	@RequestMapping(value="/synchronizeOrder",method = RequestMethod.POST)
	public @ResponseBody String synchronizeOrder(String ordersId){
		int result = orderService.synchronizeTourlineOrdersToERP(ordersId);
		if(result == 1){
			return "ok";
		}else if(result == 2){
			return "notExists";
		}else{
			return "error";
		}
	}
	
	/**
	 * 更新订单时间轴状态
	 * 
	 * @return
	 */
	@RequestMapping(value="/updateTimeLineStatus",method = RequestMethod.POST)
	public @ResponseBody String updateTimeLineStatus(String orderNo,String timeLineStatus){
		Orders order = orderService.finByOrderNo(orderNo);
		order.setTimeLineStatus(timeLineStatus);
		orderService.update(order);
		return "ok";
	}
	
	/**
	 * 批量同步线路订单
	 * 
	 * @param ordersIds
	 * @return
	 */
	@RequestMapping(value="/synchronizeOrders",method = RequestMethod.POST)
	public @ResponseBody int synchronizeOrders(String[] orderIds){
		int result = orderService.batchSynchronizeOrdersToERP(orderIds);
		if(result == -1){
			return 0;
		}
		return 1;
	}
	
	/**
	 * 取消订单页面
	 * @return
	 */
	@RequestMapping(value="/cancelOrder",method = RequestMethod.GET)
	public String cancelOrder(String ordersId,Model model){
		Orders order = orderService.findWithOccupyTimeById(ordersId);
		if(order.getOccupyTime() != null){
			return "redirect:list.do";
		}
		model.addAttribute("orders", order);
		return "/admin/manage/orders/cancelOrder.ftl";
	}
	
	/**
	 * 取消订单
	 * @return
	 */
	@RequestMapping(value="/cancelOrder",method = RequestMethod.POST)
	public String cancelOrder(Cancelrecords cancelrecords,Model model){
		cancelrecords.setId(Tools.getUUID());
		cancelrecords.setCanceldate(new Date());
		cancelrecordService.save(cancelrecords);
		orderService.cancelOrders(cancelrecords.getOrdersid());
		return "redirect:list.do";
	}
	
	/**
	 * 查看取消记录
	 * @return
	 */
	@RequestMapping(value="/showCancelRecord",method = RequestMethod.GET)
	public String showCancelRecord(String ordersId,Model model){
		Cancelrecords cancelrecords = cancelrecordService.findByOrders(ordersId);
		if(cancelrecords == null){
			return "redirect:list.do";
		}
		model.addAttribute("cancelrecords", cancelrecords);
		return "/admin/manage/orders/showCancelRecords.ftl";
	}
}

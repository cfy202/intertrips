package com.wenjing.webservice;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wenjing.ExpenseName;
import com.wenjing.entity.AdditionalProduct;
import com.wenjing.entity.Ordercontacter;
import com.wenjing.entity.Orderdetail;
import com.wenjing.entity.Orderexpense;
import com.wenjing.entity.Orders;
import com.wenjing.entity.PreBookingOfAgent;
import com.wenjing.entity.Product;
import com.wenjing.entity.TourPassenger;
import com.wenjing.util.Tools;
import com.wenjing.webservice.entity.Customer;
import com.wenjing.webservice.entity.CustomerFlight;
import com.wenjing.webservice.entity.GroupLine;
import com.wenjing.webservice.entity.Order;
import com.wenjing.webservice.entity.OrderReceiveItem;
import com.wenjing.webservice.entity.ReceivableInfoOfOrder;
import com.wenjing.webservice.entity.TourInfoOfOrder;
import com.wenjing.webservice.remoteinterface.GroupLineInterface;

@Service
public class BookErpOrdersService {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String visitUrl = "http://47.88.0.72/service/serviceInterface";
//	private static String visitUrl = "http://127.0.0.1/chinatour-3.0/service/serviceInterface";
//	String visitUrl = "http://192.168.1.101:8080/chinatour-3.0/service/serviceInterface";	
	
	/**
	 * 通过接口获得系统产品名称
	 * @param product
	 * @return
	 */
	public Product findErpProduct(Product product){
		org.codehaus.xfire.service.Service srvcModel = new ObjectServiceFactory().create(GroupLineInterface.class);
		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire()); 
		GroupLineInterface groupLineInterface = null;
		try {
			groupLineInterface = (GroupLineInterface)factory.create(srvcModel,visitUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		String code = product.getCostnumber().toUpperCase().substring(0, 2);
		if(!StringUtils.isEmpty(product.getProductNo())){
			String grouplineJson = groupLineInterface.getGroupLineByCode("I-"+product.getProductNo().trim() + "-" + code,null);
			if(!"该团不存在".equals(grouplineJson)){
				product.setBriefinfo(JSON.parseObject(grouplineJson,GroupLine.class).getTourName());
			}else{
				product.setBriefinfo("系统中不存在该产品");
			}
		}
		return product;
	}
	
	/**
	 * 通过接口给系统下订单
	 * 如果成功,返回true
	 * 否则返回false
	 * 
	 * @param orders
	 */
	public String bookOrders(Orders orders){
		org.codehaus.xfire.service.Service srvcModel = new ObjectServiceFactory().create(GroupLineInterface.class);
		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire()); 
		GroupLineInterface groupLineInterface = null;
		try {
			groupLineInterface = (GroupLineInterface)factory.create(srvcModel,visitUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		List<Orderexpense> orderExpenseList = orders.getOrderexpenseList();
		Ordercontacter orderContacter = orders.getOrderContacter();
		Orderdetail orderDetail = orders.getOrderdetails().get(0);
		List<AdditionalProduct> additionalProductList = orderDetail.getAdditionalProductList();
		List<TourPassenger> tourPassengerList = orderDetail.getTourPassengerList();
		
		String erpCode = orderDetail.getProduct().getProductNo();
		
		if(StringUtils.isEmpty(erpCode)){
			return null;
		}
		String code = orders.getCost().getCode().toUpperCase().substring(0, 2);
		String grouplineJson = groupLineInterface.getGroupLineByCode("I-" + erpCode.trim() + "-" + code,null);
		if("该团不存在".equals(grouplineJson)){
			return "notExists";
		}
		GroupLine groupline = JSON.parseObject(grouplineJson,GroupLine.class);
		
		//填充总订单信息
		Order order = new Order();
		//Helen Quach
		//order.setUserId("9a75b3c0-334e-11e4-b177-00163e002f1d");
		//helen
		/**
		 * 美国销售中心
		 */
		if("d8fe5ef1de7747ab86588f9880f1aa77".equals(orders.getCostnumber())){
			order.setUserId("140B2B2C-E132-427A-8236-A5B019CE6915");
		/**
		 * 澳洲销售中心
		 */
		}else if("bbebc7de2fdf470c854620501fef4dd1".equals(orders.getCostnumber())){
			order.setUserId("9AF5F3B6-70E1-4FDD-92F0-1E607994E9F6");
		}else{
			return null;
		}
		order.setTotalPeople(tourPassengerList.size());
		order.setTourCode(groupline.getTourCode());
		order.setContactName(orderContacter.getFirstName() + orderContacter.getLastName());
		order.setTax(0);
		order.setOrderNoIn("intertrips");
		order.setYear(orderContacter.getEmail());
		order.setTime(orderContacter.getTel());
		order.setRefNo(orders.getOrderno());
		order.setArrivaDate(orders.getOrderdetails().get(0).getDepostidate());
		if("1".equals(orders.getPayway().trim())){
			order.setSorceId(1);
			order.setUserName("Payment from " + orders.getReceiveway());
		}else{
			order.setSorceId(2);
		}
		
		List<List<TourPassenger>> roomList = new ArrayList<List<TourPassenger>>();
		int lastRoomNumber = 0;
		
		for(TourPassenger tourPassenger : tourPassengerList){
			//如果是婴儿和儿童,则房型为 Sharing Existing Bed
			if("INFANTS".equals(tourPassenger.getIdentity()) || "CHILDREN".equals(tourPassenger.getIdentity())){
				roomList.add(new ArrayList<TourPassenger>());
			//如果房间发生变化
			}else if(lastRoomNumber == 0 || lastRoomNumber != tourPassenger.getRoomNumber()){
				lastRoomNumber = tourPassenger.getRoomNumber();
				roomList.add(new ArrayList<TourPassenger>());
			}
			roomList.get(roomList.size() - 1).add(tourPassenger);
		}
		
		//填充客人信息
		List<Customer> customerList = new ArrayList<Customer>();
		for(List<TourPassenger> roomInfo : roomList){
			for(int passengerIndex=0; passengerIndex<roomInfo.size(); passengerIndex++){
				TourPassenger tourPassenger = roomInfo.get(passengerIndex);
				Customer customer = new Customer();
				setCustomerInfo(customer,tourPassenger);
			    if("INFANTS".equals(tourPassenger.getIdentity()) || "CHILDREN".equals(tourPassenger.getIdentity())){
					customer.setGuestRoomType("Sharing Existing Bed");
					customer.setRoomNumber(0);
					customer.setRoomIsFull(0);
			    }else{
				    if("Single Room".equals(tourPassenger.getRoomType())){
		 	        	customer.setGuestRoomType("Single");
		 	        	customer.setRoomIsFull(0);
					}else if("Double Room".equals(tourPassenger.getRoomType())){
						customer.setGuestRoomType("King Bed");
						customer.setRoomIsFull(1);
					}else if("Twin Room".equals(tourPassenger.getRoomType())){
						customer.setGuestRoomType("Twin Bed");
						customer.setRoomIsFull(1);
					}else if("Triple Room".equals(tourPassenger.getRoomType())){
						if(passengerIndex < 2){
							customer.setGuestRoomType("Twin Bed");
						}else{
							customer.setGuestRoomType("Extra Bed");
						}
						customer.setRoomIsFull(2);
					}
				    customer.setRoomNumber(tourPassenger.getRoomNumber());
			    }
				customerList.add(customer);
			}
		}
		
		//团信息
		TourInfoOfOrder tourInfoOfOrder = new TourInfoOfOrder();
		tourInfoOfOrder.setScheduleLineCode(groupline.getTourCode());
		tourInfoOfOrder.setGroupLineId(groupline.getId());
		tourInfoOfOrder.setLineName(groupline.getTourName());
		tourInfoOfOrder.setArriveTime(orderDetail.getDeparturedate());    //必填
		tourInfoOfOrder.setTourInfo(orderDetail.getSpecialrequest());
		if(orderDetail.getDeparture() != null){
			tourInfoOfOrder.setSpecialRequirements(orderDetail.getDeparture().getName());
		}else{
			tourInfoOfOrder.setSpecialRequirements("");
		}
		
		
		ReceivableInfoOfOrder receivableInfoOfOrder = new ReceivableInfoOfOrder();
		//除团费外其他价格总数
		BigDecimal totalFeeOfOthers = new BigDecimal(0);
		
		List<OrderReceiveItem> orderReceiveItemList = new ArrayList<OrderReceiveItem>();
		for(Orderexpense orderExpense:orderExpenseList){
			OrderReceiveItem orderReceiveItem = new OrderReceiveItem();
			if(ExpenseName.TOUR_FEE.equals(orderExpense.getName())){
				receivableInfoOfOrder.setTotalCommonTourFee(orderExpense.getPrice());
				BigDecimal avgPrice = orderExpense.getPrice().divide(new BigDecimal(orderExpense.getVisitors()),2);
				orderReceiveItem.setType(1);
				orderReceiveItem.setItemFee(avgPrice);
				orderReceiveItem.setItemFeeNum(orderExpense.getVisitors());
				orderReceiveItem.setRemark("Tour Cost");
				orderReceiveItem.setNum(101);	
			}else if(ExpenseName.SINGLE_SUPPLEMENTS_FEE.equals(orderExpense.getName())){
				orderReceiveItem.setType(2);
				BigDecimal avgPrice = orderExpense.getPrice().divide(new BigDecimal(orderExpense.getVisitors()),2);
				orderReceiveItem.setItemFee(avgPrice);
				orderReceiveItem.setItemFeeNum(orderExpense.getVisitors());
				orderReceiveItem.setRemark("Single supplements fee");
				orderReceiveItem.setNum(201);
				totalFeeOfOthers = totalFeeOfOthers.add(orderExpense.getPrice());
			}else if(ExpenseName.GUIDE_SERVE_FEE.equals(orderExpense.getName())){
				orderReceiveItem.setType(2);
				BigDecimal avgPrice = orderExpense.getPrice().divide(new BigDecimal(orderExpense.getVisitors()),2);
				orderReceiveItem.setItemFee(avgPrice);
				orderReceiveItem.setItemFeeNum(orderExpense.getVisitors());
				orderReceiveItem.setRemark("Guides service fees");
				orderReceiveItem.setNum(202);	
				totalFeeOfOthers = totalFeeOfOthers.add(orderExpense.getPrice());
			}else if(ExpenseName.STEAM_FEE.equals(orderExpense.getName())){
				orderReceiveItem.setType(2);
				BigDecimal avgPrice = orderExpense.getPrice().divide(new BigDecimal(orderExpense.getVisitors()),2);
				orderReceiveItem.setItemFee(avgPrice);
				orderReceiveItem.setItemFeeNum(orderExpense.getVisitors());
				orderReceiveItem.setRemark("Optional attractions and performances");
				orderReceiveItem.setNum(203);	
				totalFeeOfOthers = totalFeeOfOthers.add(orderExpense.getPrice());
			}else if(ExpenseName.TRANSFER_FEE.equals(orderExpense.getName())){
				orderReceiveItem.setType(2);
				BigDecimal avgPrice = orderExpense.getPrice().divide(new BigDecimal(orderExpense.getVisitors()),2);
				orderReceiveItem.setItemFee(avgPrice);
				orderReceiveItem.setItemFeeNum(orderExpense.getVisitors());
				orderReceiveItem.setRemark("Airport transfer fees");
				orderReceiveItem.setNum(204);	
				totalFeeOfOthers = totalFeeOfOthers.add(orderExpense.getPrice());
			}else if(ExpenseName.AIRLINE_TICKETS_FEE.equals(orderExpense.getName())){
				orderReceiveItem.setType(2);
				BigDecimal avgPrice = orderExpense.getPrice().divide(new BigDecimal(orderExpense.getVisitors()),2);
				orderReceiveItem.setItemFee(avgPrice);
				orderReceiveItem.setItemFeeNum(orderExpense.getVisitors());
				orderReceiveItem.setRemark("Air fares");
				orderReceiveItem.setNum(205);	
				totalFeeOfOthers = totalFeeOfOthers.add(orderExpense.getPrice());
			}else if(ExpenseName.COUPON.equals(orderExpense.getName())){
				orderReceiveItem.setType(3);
				orderReceiveItem.setItemFee(orderExpense.getPrice());
				orderReceiveItem.setItemFeeNum(1);
				orderReceiveItem.setRemark("couponse");
				orderReceiveItem.setNum(301);	
				totalFeeOfOthers = totalFeeOfOthers.subtract(orderExpense.getPrice());
			}
			if(!(ExpenseName.ADDITIONAL.equals(orderExpense.getName()) || ExpenseName.OPTIONAL_TOUR_FEE.equals(orderExpense.getName()))){
				orderReceiveItemList.add(orderReceiveItem);
			}
		}
		int num = 206;
		if(additionalProductList != null && additionalProductList.size() > 0 ){
			for(AdditionalProduct additionalProduct : additionalProductList){
				OrderReceiveItem orderReceiveItem = new OrderReceiveItem();
				orderReceiveItem.setType(2);
				orderReceiveItem.setNum(num++);
				orderReceiveItem.setItemFee(additionalProduct.getUnitcost());
				orderReceiveItem.setItemFeeNum(additionalProduct.getQuantity());
				String dateString = "";
				if(additionalProduct.getDate() != null){
					dateString = sdf.format(additionalProduct.getDate());
					if("0000-00-00".equals(dateString)){
						dateString = "";
					}
				}else{
					dateString = "";
				}
				orderReceiveItem.setRemark(additionalProduct.getName());
				orderReceiveItemList.add(orderReceiveItem);
				totalFeeOfOthers = totalFeeOfOthers.add(additionalProduct.getPrice());
			}
		}
		receivableInfoOfOrder.setTotalFeeOfOthers(totalFeeOfOthers);
		receivableInfoOfOrder.setSumFee(orderDetail.getPrice());
		receivableInfoOfOrder.setOrderReceiveItemList(orderReceiveItemList);
		receivableInfoOfOrder.setTotalPeople(tourPassengerList.size());
		
		String orderNo = null;
		try {
			orderNo = groupLineInterface.saveOrderWeb(JSON.toJSONString(order), JSON.toJSONString(customerList), JSON.toJSONString(tourInfoOfOrder), JSON.toJSONString(receivableInfoOfOrder));
		} catch (ParseException e) {
		  //TODO Auto-generated catch block
			e.printStackTrace();
			return null;
    	}
		if(orderNo.endsWith("Save Failed")){
			return null;
		}
		return JSON.parseObject(orderNo,String.class);
	}
	
	//给erp的客人设置房间信息
	public static void setCustomerInfo(Customer customer,TourPassenger tourPassenger){
		//姓名
		customer.setFirstName(tourPassenger.getFirstName());
		customer.setLastName(tourPassenger.getLastName());
		customer.setMiddleName(tourPassenger.getMiddleName());
		//生日
		if(tourPassenger.getBirthday() != null){
			customer.setBirthday(sdf.format(tourPassenger.getBirthday()));
		}else{
			customer.setBirthday("");
		}
		//国籍
		customer.setNationalityOfPassport(tourPassenger.getNationality());
		//护照号
		customer.setPassportNo(tourPassenger.getPassportNo());
		//护照有效期
		if(tourPassenger.getPassportNoExpiryDate() != null){
			customer.setPassportDate(sdf.format(tourPassenger.getPassportNoExpiryDate()));
		}else{
			customer.setPassportDate(""); 
		}
		//性别
		if(tourPassenger.getGender() == 0){
			customer.setSex(2);  
		}else{
			customer.setSex(1);  
		}
		//电话
		customer.setTel(tourPassenger.getMobileNumber());
		//语言
		customer.setLanguageId("118c8700-3cfa-11e2-9ada-fcde614b56cb");
		
		//客人类型
		customer.setMemoOfCustomer("New Clients");
			//客人航班
			List<CustomerFlight> customerFlightList = new ArrayList<CustomerFlight>();
			CustomerFlight enterCustomerFlight = new CustomerFlight();
			enterCustomerFlight.setArriveDateStr("");
			enterCustomerFlight.setIfPickUp(2);
			enterCustomerFlight.setOutOrEnter(1);
			customerFlightList.add(enterCustomerFlight);
			
			CustomerFlight outCustomerFlight = new CustomerFlight();
			outCustomerFlight.setArriveDateStr("");
			outCustomerFlight.setIfSendUp(2);
			outCustomerFlight.setOutOrEnter(2);
			customerFlightList.add(outCustomerFlight);
		customer.setCustomerFlightList(customerFlightList);	
	}
	
	
	public String bookingToErp(PreBookingOfAgent preBookingOfAgent){
		org.codehaus.xfire.service.Service srvcModel = new ObjectServiceFactory().create(GroupLineInterface.class);
		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire()); 
		GroupLineInterface groupLineInterface = null;
		try {
			groupLineInterface = (GroupLineInterface)factory.create(srvcModel,visitUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		
		String erpCode = preBookingOfAgent.getProduct().getProductNo();
		if(StringUtils.isEmpty(erpCode)){
			return null;
		}
		
		String code = preBookingOfAgent.getCurrencysign().toUpperCase().substring(0, 2);
		String grouplineJson = groupLineInterface.getGroupLineByCode("I-" + erpCode.trim() + "-" + code,null);
		if("该团不存在".equals(grouplineJson)){
			return "notExists";
		}
		GroupLine groupline = JSON.parseObject(grouplineJson,GroupLine.class);
		
		//填充总订单信息
		Order order = new Order();
		//Helen Quach
		//order.setUserId("9a75b3c0-334e-11e4-b177-00163e002f1d");
		//helen
		/**
		 * 美国销售中心
		 */
		if("d8fe5ef1de7747ab86588f9880f1aa77".equals(preBookingOfAgent.getCostnumber())){
			order.setUserId(preBookingOfAgent.getAgentCode().getAgentId());
		/**
		 * 澳洲销售中心
		 */
		}else if("bbebc7de2fdf470c854620501fef4dd1".equals(preBookingOfAgent.getCostnumber())){
			order.setUserId("9AF5F3B6-70E1-4FDD-92F0-1E607994E9F6");
		}else{
			return null;
		}
		order.setTotalPeople(preBookingOfAgent.getPax());
		order.setTourCode(groupline.getTourCode());
		order.setContactName(preBookingOfAgent.getName());
		order.setTax(0);
		order.setOrderNoIn("intertrips");
		order.setYear(preBookingOfAgent.getEmail());
		order.setTime(preBookingOfAgent.getPhoneno());
		order.setRefNo(preBookingOfAgent.getBookingNo());
		order.setArrivaDate(Tools.getDtime((int)(preBookingOfAgent.getCreatetime().getTime()/1000)));  //付款时间
		
		if(preBookingOfAgent.getIsOnlinePay() != null && preBookingOfAgent.getIsOnlinePay()){
			order.setSorceId(2);
		}else{
			order.setSorceId(3);
		}
		
		BigDecimal totalFee = new BigDecimal(preBookingOfAgent.getTotal());
		
		//填充客人信息
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customer = new Customer();
		String[] name = preBookingOfAgent.getName().split(" ");
		customer.setFirstName(name[0]);
		StringBuilder lastName = new StringBuilder();
		for(int i=1; i<name.length; i++){
			lastName.append(name[i] + " ");
		}
		customer.setLastName(lastName.toString());
		//生日
		customer.setBirthday("");
		//国籍
		customer.setNationalityOfPassport("");
		//护照号
		customer.setPassportNo("");
		//护照有效期
		customer.setPassportDate(""); 
		//性别
		customer.setSex(1);  
		//电话
		customer.setTel(preBookingOfAgent.getPhoneno());
		//语言
		customer.setLanguageId("118c8700-3cfa-11e2-9ada-fcde614b56cb");
		//客人类型
		customer.setMemoOfCustomer("New Clients");
			//客人航班
			List<CustomerFlight> customerFlightList = new ArrayList<CustomerFlight>();
			CustomerFlight enterCustomerFlight = new CustomerFlight();
			enterCustomerFlight.setArriveDateStr("");
			enterCustomerFlight.setIfPickUp(2);
			enterCustomerFlight.setOutOrEnter(1);
			customerFlightList.add(enterCustomerFlight);
			
			CustomerFlight outCustomerFlight = new CustomerFlight();
			outCustomerFlight.setArriveDateStr("");
			outCustomerFlight.setIfSendUp(2);
			outCustomerFlight.setOutOrEnter(2);
			customerFlightList.add(outCustomerFlight);
		customer.setCustomerFlightList(customerFlightList);			
		customer.setGuestRoomType("Single");
		customer.setRoomIsFull(0);
		customer.setRoomNumber(0);
		customerList.add(customer);
		
		//团信息
		TourInfoOfOrder tourInfoOfOrder = new TourInfoOfOrder();
		tourInfoOfOrder.setScheduleLineCode(groupline.getTourCode());
		tourInfoOfOrder.setGroupLineId(groupline.getId());
		tourInfoOfOrder.setLineName(groupline.getTourName());
		tourInfoOfOrder.setArriveTime(sdf.format(preBookingOfAgent.getDeparturedate()));    //必填
		if(preBookingOfAgent.getDeparture() != null){
			tourInfoOfOrder.setTourInfo("Departure City:" + preBookingOfAgent.getDeparture().getName());
		}else{
			tourInfoOfOrder.setTourInfo("");
		}
		
		ReceivableInfoOfOrder receivableInfoOfOrder = new ReceivableInfoOfOrder();
		//除团费外其他价格总数
		BigDecimal totalFeeOfOthers = new BigDecimal(0);
		
		List<OrderReceiveItem> orderReceiveItemList = new ArrayList<OrderReceiveItem>();
		OrderReceiveItem orderReceiveItem = new OrderReceiveItem();
		receivableInfoOfOrder.setTotalCommonTourFee(totalFee);
		
		BigDecimal avgPrice = totalFee.divide(new BigDecimal(preBookingOfAgent.getPax()),2);
		orderReceiveItem.setType(1);
		orderReceiveItem.setItemFee(avgPrice);
		orderReceiveItem.setItemFeeNum(preBookingOfAgent.getPax()+0);
		orderReceiveItem.setRemark("Tour Cost");
		orderReceiveItem.setNum(101);
		orderReceiveItemList.add(orderReceiveItem);
		
		receivableInfoOfOrder.setTotalFeeOfOthers(totalFeeOfOthers);
		receivableInfoOfOrder.setSumFee(totalFee);
		receivableInfoOfOrder.setOrderReceiveItemList(orderReceiveItemList);
		receivableInfoOfOrder.setTotalPeople(preBookingOfAgent.getPax() + 0);
		
		String orderNo = null;
		try {
			orderNo = groupLineInterface.saveOrderWeb(JSON.toJSONString(order), JSON.toJSONString(customerList), JSON.toJSONString(tourInfoOfOrder), JSON.toJSONString(receivableInfoOfOrder));
		} catch (ParseException e) {
		  //TODO Auto-generated catch block
			e.printStackTrace();
			return null;
    	}
		if(orderNo.endsWith("Save Failed")){
			return null;
		}
		return JSON.parseObject(orderNo,String.class);
	}
}
package com.wenjing.action.front;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.DateEditor;
import com.wenjing.dao.CostMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Orders;
import com.wenjing.entity.Product;
import com.wenjing.exception.OrderException;
import com.wenjing.service.OrderService;
import com.wenjing.service.ShoppingCartService;
import com.wenjing.vo.BookOrderVO;

/**
 * 订单模块
 * 
 * @author Jared
 *
 * May 26, 2015
 */
@Controller
@RequestMapping("/front/visaorders")
public class VisaOrdersController {
	private static final String ORDER_MAIL_TEMPLATE_URL = "/visa/VisaOrderMail.ftl";	
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
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CostMapper costMapper;
	
	
	/**
	 * 预订产品
	 * 
	 * 重定向至购物车页面
	 * 
	 * @param bookOrderVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bookOrder", method = RequestMethod.POST)
	public String bookOrder(BookOrderVO bookOrderVO,Model model){
		String costnumber = (String)request.getSession().getAttribute("costnumber");
		Cost costVisa = null;
		if(costnumber!=null){
			costVisa = costMapper.selectByPrimaryKey(costnumber);
		}
		Product product = productMapper.selectByPrimaryKey(bookOrderVO.getProductId());
		BigDecimal a = new BigDecimal(bookOrderVO.getAdultNum()); 
		bookOrderVO.setTotalPrice(a.multiply(product.getMinprice()));
		request.getSession().setAttribute("product", product);
		request.getSession().setAttribute("bookOrderVO", bookOrderVO);
		request.getSession().setAttribute("costVisa", costVisa);
		return "redirect:/front/visaorders/shoppingCart.do";
	}
	
	/**
	 * 购物车页面
	 * 加载购物车产品信息
	 * 填写顾客信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/shoppingCart",method = RequestMethod.GET)
	public String shoppingCar(Model model,HttpServletRequest request){
	    Product product = (Product)request.getSession().getAttribute("product");
		BookOrderVO	bookOrderVO = (BookOrderVO)request.getSession().getAttribute("bookOrderVO");
		Cost costVisa = (Cost)request.getSession().getAttribute("costVisa");
		model.addAttribute("product",product);
		model.addAttribute("bookOrderVO",bookOrderVO);
		model.addAttribute("costVisa",costVisa);
		return "/front/visa/shoppingCart.ftl";
	}
	/**
	 * 将产品加入购物车
	 * 返回该用户下购物车中的条数
	 * 
	 * @param bookOrderVO
	 * @return
	 */
	@RequestMapping(value="/addToShoppingCart", method = RequestMethod.POST)
	public @ResponseBody int addToShoppingCar(BookOrderVO bookOrderVO,HttpServletRequest request,HttpServletResponse response){
		int shoppingCartNum = 0;
		try {
			shoppingCartNum = shoppingCartService.addToShoppingCart(bookOrderVO,request,response);
		} catch (OrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return shoppingCartNum;
	}
	
	
	/**
	 * 从购物车中删除产品
	 * 
	 * @param shoppingCartId
	 * @return
	 */
	@RequestMapping(value="/removeProduct",method = RequestMethod.POST)
	public @ResponseBody String romoveProductFromShoppingCart(String shoppingCartId){
		shoppingCartService.removeProductFromCart(shoppingCartId);
		return "success";
	}
	
	/**
	 * 下订单并进入结算页面
	 * 
	 * @param loadOrderInfoVO
	 * @return
	 */
//	@RequestMapping(value="/submitOrderInfo", method = RequestMethod.POST)
//	public String order(@ModelAttribute("orders") Orders orders,
//			@ModelAttribute("orderdetail") Orderdetail orderdetail,
//			@ModelAttribute("ordercontacter") Ordercontacter ordercontacter,Model model
//			){
//		
//		try {
//			orderService.savaVisaOrders(orders,orderdetail,ordercontacter, request);
//			 orders = orderService.getMemberOrderDetailByOrderNo(orders.getOrderno());
//			 orderdetail = orders.getOrderdetails().get(0);
//	     		Mail gmail = new Mail();
//	    		gmail.setSendTo(new String[]{ordercontacter.getEmail()});
//	    		gmail.setEmailSubjectTxt(orderdetail.getProduct().getName());
//	    		Map<String,Object> paramMap = new HashMap<String,Object>();
//	    		paramMap.put("orderdetail", orderdetail);
//	    		paramMap.put("orders", orders);
//	    		paramMap.put("ordercontacter", ordercontacter);
//	    		String emailMsgTxt = FreemarkerUtils.getOrderNoticeMail(request.getSession().getServletContext(),ORDER_MAIL_TEMPLATE_URL,paramMap);
//	    		gmail.setEmailMsgTxt(emailMsgTxt);
//	    		try {
//	    			gmail.sendSSLMessage(); // 发送邮件
//	    		} catch (MessagingException e) {
//	    			e.printStackTrace();
//	    		}
//		}catch (OrderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			if("resubmit".equals(e.getErrorMessage())){
//				model.addAttribute("errorMsg", "请勿重复提交！");
//			}else if("incorrect_price".equals(e.getErrorMessage()) || "incorrect_date_format".equals(e.getErrorMessage())){
//				model.addAttribute("errorMsg", "参数出错！");
//			}
//			return "/front/order/error.ftl";
//		}
//		
//		return "redirect:/front/visaorders/payOrders.do?orderno="+orders.getOrderno();
//	}
	
	
	
	@RequestMapping(value="/payOrders")
	public String goForQueryOrders(@RequestParam("orderno")String orderno,Model model){
		 if(orderno !=null && !"".equals(orderno)){
     		 Orders vsiaOrders = orderService.getMemberOrderDetailByOrderNo(orderno);
     		 model.addAttribute("vsiaOrders",vsiaOrders); 
     	 }
		return "/front/visa/ordercomfrim.ftl";
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
	 * @Title: testPay
	 * @Description: 测试
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping(value="/test")
	public String testPay(){
		return "/front/order/testPay.ftl";
	}
}
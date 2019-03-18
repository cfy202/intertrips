package com.wenjing.action;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.DateEditor;
import com.wenjing.Pages;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Cost;
import com.wenjing.entity.PreBookingOfAgent;
import com.wenjing.service.CostService;
import com.wenjing.service.PreBookingOfAgentService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;
import com.wenjing.vo.BookingQueryVO;

/**
 * 类说明后台优惠券抽奖活动规则管理ontroller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/prebookingofagent")
public class PreBookingOfAgentController {
	
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
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
	private PreBookingOfAgentService preBookingOfAgentService;
	@Autowired
	private CostService costService;
	
	/**
	 * 后台订单管理加载订单list
	 * 
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/list")
	public String findOrders(Model model,BookingQueryVO bookingQueryVO,HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Admin admin = Tools.getAdmin(request);
		if(StringUtils.isEmpty(admin.getName()) || "Nancy7A3N".equalsIgnoreCase(admin.getName()) || "Helen4FC3".equalsIgnoreCase(admin.getName())){
		}else{
			bookingQueryVO.setAgentCode(admin.getName());
		}
		List<Cost> costlist = (List<Cost>) request.getSession().getAttribute("cost");
		
		String pageNow = bookingQueryVO.getPageNow();
		String pageSize = bookingQueryVO.getPageSize(); 
		
		/**
		 * 获取传递过来的参数，如果不存在于cookie中获取，获取到设入到参数对象中
		 * 如果存在设入到cookie中，并将初始页设置为第一页
		 */
		//名称
		String name = WebUtils.getCookie(request, "bookingName");
		if(StringUtils.isEmpty(bookingQueryVO.getName())){
			if(!StringUtils.isEmpty(name)){
				bookingQueryVO.setName(name);
			}
		}else{
			//如果传来参数与cookie中的不相同,则搜索参数变动过,页数变为1
			if(!bookingQueryVO.getName().equals(name)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingName", bookingQueryVO.getName());
		}
		//产品名称或者产品CODE
		String productNameOrCode = WebUtils.getCookie(request, "bookingProductNameOrCode");
		if(StringUtils.isEmpty(bookingQueryVO.getProductNameOrCode())){
			if(!StringUtils.isEmpty(productNameOrCode)){
				bookingQueryVO.setProductNameOrCode(productNameOrCode);
			}
		}else{
			if(!bookingQueryVO.getProductNameOrCode().equals(productNameOrCode)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingProductNameOrCode", bookingQueryVO.getProductNameOrCode());
		}		
		//出发地
		String gateWay = WebUtils.getCookie(request, "bookingGateWay");
		if(StringUtils.isEmpty(bookingQueryVO.getGateWay())){
			if(!StringUtils.isEmpty(gateWay)){
				bookingQueryVO.setGateWay(gateWay);
			}
		}else{
			if(!bookingQueryVO.getGateWay().equals(gateWay)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingGateWay", bookingQueryVO.getGateWay());
		}		
		//agent code
		String agentCode = WebUtils.getCookie(request, "bookingAgentCode");
		if(StringUtils.isEmpty(bookingQueryVO.getAgentCode())){
			if(!StringUtils.isEmpty(agentCode)){
				bookingQueryVO.setAgentCode(agentCode);
			}
		}else{
			//如果传来参数与cookie中的不相同,则搜索参数变动过,页数变为1
			if(!bookingQueryVO.getAgentCode().equals(agentCode)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingAgentCode", bookingQueryVO.getAgentCode());
		}		
		//出发日期的最小日期
		String departureDateBeforeLimit = WebUtils.getCookie(request, "bookingDepartureDateBeforeLimit");
		if(bookingQueryVO.getDepartureDateBeforeLimit() == null){
			if(!StringUtils.isEmpty(departureDateBeforeLimit)){
				bookingQueryVO.setDepartureDateBeforeLimit(DEFAULT_DATE_FORMAT.parse(departureDateBeforeLimit));
			}
		}else{
			if(departureDateBeforeLimit != null && !"".equals(departureDateBeforeLimit) && !bookingQueryVO.getDepartureDateBeforeLimit().equals(DEFAULT_DATE_FORMAT.parse(departureDateBeforeLimit))){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingDepartureDateBeforeLimit", DEFAULT_DATE_FORMAT.format(bookingQueryVO.getDepartureDateBeforeLimit()));
		}
		//出发日期的最大日期
		String departureDateAfterLimit = WebUtils.getCookie(request, "bookingDepartureDateAfterLimit");
		if(bookingQueryVO.getDepartureDateAfterLimit() == null){
			if(!StringUtils.isEmpty(departureDateAfterLimit)){
				bookingQueryVO.setDepartureDateAfterLimit(DEFAULT_DATE_FORMAT.parse(departureDateAfterLimit));
			}
		}else{
			if(departureDateAfterLimit != null && !"".equals(departureDateAfterLimit) && !bookingQueryVO.getDepartureDateAfterLimit().equals(departureDateAfterLimit)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingDepartureDateAfterLimit", DEFAULT_DATE_FORMAT.format(bookingQueryVO.getDepartureDateAfterLimit()));
		}
		//下单日期的最小日期
		String bookingTimeBeforeLimit = WebUtils.getCookie(request, "preBookingTimeBeforeLimit");
		if(bookingQueryVO.getBookingTimeBeforeLimit() == null){
			if(bookingTimeBeforeLimit != null){
				bookingQueryVO.setBookingTimeBeforeLimit(DEFAULT_DATE_FORMAT.parse(bookingTimeBeforeLimit));
			}
		}else{
			if(bookingTimeBeforeLimit != null && !"".equals(bookingTimeBeforeLimit) && !bookingQueryVO.getBookingTimeBeforeLimit().equals(bookingTimeBeforeLimit)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "preBookingTimeBeforeLimit", DEFAULT_DATE_FORMAT.format(bookingQueryVO.getBookingTimeBeforeLimit()));
		}
		//下单日期的最大日期
		String bookingTimeAfterLimit = WebUtils.getCookie(request, "preBookingTimeAfterLimit");
		if(bookingQueryVO.getBookingTimeAfterLimit() == null){
			if(bookingTimeAfterLimit != null){
				bookingQueryVO.setBookingTimeAfterLimit(DEFAULT_DATE_FORMAT.parse(bookingTimeAfterLimit));
			}
		}else{
			if(bookingTimeAfterLimit != null && !"".equals(bookingTimeAfterLimit) && !bookingQueryVO.getBookingTimeAfterLimit().equals(DEFAULT_DATE_FORMAT.parse(bookingTimeAfterLimit))){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "preBookingTimeAfterLimit", DEFAULT_DATE_FORMAT.format(bookingQueryVO.getBookingTimeAfterLimit()));
		}
		//运营中心
		String costId = WebUtils.getCookie(request, "bookingCostId");
		if(StringUtils.isEmpty(bookingQueryVO.getCostId())){
			if(!StringUtils.isEmpty(costId)){
				bookingQueryVO.setCostId(costId);
			}
		}else{
			if(!StringUtils.isEmpty(costId) && !bookingQueryVO.getCostId().equals(costId)){
				pageNow = "1";
			}
			WebUtils.addCookie(request, response, "bookingCostId", bookingQueryVO.getCostId());
		}
		
		if(StringUtils.isEmpty(bookingQueryVO.getCostId())){
		    for(Cost cost : costlist){
		    	if("USD".equals(cost.getCode())){
		    		bookingQueryVO.setCostId(cost.getId());
		    		break;
		    	}
		    }
		    if(StringUtils.isEmpty(bookingQueryVO.getCostId())){
		    	bookingQueryVO.setCostId(costlist.get(0).getId());
		    }
		}
		/**
		 * 如果页容量参数为空，则在cookie里查找，如果不存在，则设为10
		 */
		if(pageSize == null || "".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "bookingPageSize");
	        if(pageSize == null || "".equals(pageSize)){
	        	pageSize = "10";
	        }
		}else{
			WebUtils.addCookie(request, response, "bookingPageSize", pageSize);
			pageNow = 1 + "";
		}
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "bookingPageNow");
		} else {
			WebUtils.addCookie(request, response, "bookingPageNow", pageNow);
		}
		Map<String,Object> parametersMap = new HashMap<String,Object>();
		parametersMap.put("costNumber", bookingQueryVO.getCostId());
		parametersMap.put("name", bookingQueryVO.getName());
		parametersMap.put("productNameOrCode", bookingQueryVO.getProductNameOrCode());
		parametersMap.put("gateWay", bookingQueryVO.getGateWay());
		parametersMap.put("departureDateBeforeLimit", bookingQueryVO.getDepartureDateBeforeLimit());
		parametersMap.put("departureDateAfterLimit", bookingQueryVO.getDepartureDateAfterLimit());
		parametersMap.put("bookingTimeBeforeLimit", bookingQueryVO.getBookingTimeBeforeLimit());
		parametersMap.put("bookingTimeAfterLimit", bookingQueryVO.getBookingTimeAfterLimit());
		parametersMap.put("agentCode", bookingQueryVO.getAgentCode());
		Pages page = null;
		List<PreBookingOfAgent> preBookingOfAgentList;
		int totalCount = preBookingOfAgentService.getTotalNumber(parametersMap);
		if (pageNow != null && !"".equals(pageNow)) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
		} 
		parametersMap.put("startPos", page.getStartPos());
		parametersMap.put("pageSize", page.getPageSize());
		preBookingOfAgentList = preBookingOfAgentService.getOrdersByPage(parametersMap);
		PreBookingOfAgent preBookingOfAgent = preBookingOfAgentService.getTotalCollectionAndOrderNumber(parametersMap);
		model.addAttribute("bookingQueryVO", bookingQueryVO);
		model.addAttribute("page", page);
		model.addAttribute("preBookingOfAgentList", preBookingOfAgentList);
		model.addAttribute("costList", costlist);
		model.addAttribute("totalAndNumber", preBookingOfAgent);
		return "/admin/manage/preBookingOfAgent/list.ftl";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model){
		PreBookingOfAgent preBookingOfAgent = preBookingOfAgentService.findDetailById(id);
		model.addAttribute("preBookingOfAgent", preBookingOfAgent);
		return "/admin/manage/preBookingOfAgent/orderDetail.ftl";
	}
	
	@RequestMapping("/cancel")
	@ResponseBody
	public int cancelBooking(String id){
		preBookingOfAgentService.cancel(id);
		return 1;
	}
	
	@RequestMapping("/exportExcel")
	public void export(String[] ids,HttpServletResponse res) throws IOException{
		if(ids == null){
			return;
		}
		List<PreBookingOfAgent> preBookingOfAgentList = preBookingOfAgentService.findByIds(ids);
		SimpleDateFormat departureDateTransfer = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat createTimeTransfer = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
        Workbook workbook = null;  
        String fileName = "agent_book.xls";
        
        if(fileName.endsWith("xlsx")){  
            workbook = new XSSFWorkbook();  
        }else if(fileName.endsWith("xls")){  
            workbook = new HSSFWorkbook();  
        }
        Sheet sheet = workbook.createSheet("peopleList");  
        Row row = sheet.createRow(0);  
        row.createCell(0).setCellValue("BookNo.");
        row.createCell(1).setCellValue("ProductName");
        row.createCell(2).setCellValue("ProductCode");
        row.createCell(3).setCellValue("Name");
        row.createCell(4).setCellValue("PhoneNo");
        row.createCell(5).setCellValue("Email");
        row.createCell(6).setCellValue("Pax");
        row.createCell(7).setCellValue("Gateway");
        row.createCell(8).setCellValue("DepartureDate");
        row.createCell(9).setCellValue("CreditCardNo");
        row.createCell(10).setCellValue("ExpirationDate");
        row.createCell(11).setCellValue("SecurityCode");
        row.createCell(12).setCellValue("Remarks");
        row.createCell(13).setCellValue("Total");
        row.createCell(14).setCellValue("CreateTime");
        row.createCell(15).setCellValue("AgentCode");
        
        Iterator<PreBookingOfAgent> iterator = preBookingOfAgentList.iterator();  
           
        int rowIndex = 1;  
        while(iterator.hasNext()){  
        	PreBookingOfAgent preBookingOfAgent = iterator.next();  
            row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(preBookingOfAgent.getBookingNo());
            row.createCell(1).setCellValue(preBookingOfAgent.getProductname());
            row.createCell(2).setCellValue(preBookingOfAgent.getProductcode());
            row.createCell(3).setCellValue(preBookingOfAgent.getName());
            row.createCell(4).setCellValue(preBookingOfAgent.getPhoneno());
            row.createCell(5).setCellValue(preBookingOfAgent.getEmail());
            row.createCell(6).setCellValue(preBookingOfAgent.getPax());
            row.createCell(7).setCellValue(preBookingOfAgent.getGateway());
            row.createCell(8).setCellValue(departureDateTransfer.format(preBookingOfAgent.getDeparturedate()));
            row.createCell(9).setCellValue(preBookingOfAgent.getCreditcardno());
            row.createCell(10).setCellValue(preBookingOfAgent.getExpirationdate());
            row.createCell(11).setCellValue(preBookingOfAgent.getSecuritycode());
            row.createCell(12).setCellValue(preBookingOfAgent.getRemarks());
            row.createCell(13).setCellValue(preBookingOfAgent.getCurrencysign() + preBookingOfAgent.getTotal());
            row.createCell(14).setCellValue(createTimeTransfer.format(preBookingOfAgent.getCreatetime()));
            row.createCell(15).setCellValue(preBookingOfAgent.getAgentcode());
        }  
           
        //lets write the excel data to file now  
        ByteArrayOutputStream fos = null;
        byte[] retArr = null;
		try {
			fos = new ByteArrayOutputStream();
			workbook.write(fos);
			retArr = fos.toByteArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}  
	    OutputStream os = res.getOutputStream();  
	    try {  
	        res.reset();  
	        res.setHeader("Content-Disposition", "attachment; filename=agent_book.xls");  
	        res.setContentType("application/octet-stream; charset=utf-8");  
	        os.write(retArr);  
	        os.flush();  
	    } finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    }  
	}
	
	@RequestMapping("/synchorizeToErp")
	@ResponseBody
	public int synchorizeToErp(String[] ids){
		if(ids != null){
			int result = preBookingOfAgentService.batchSynchronizeToErp(ids);
			if(result == -1){
				return 0;
			}
		}
		return 1;
	}
}

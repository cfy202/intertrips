package com.wenjing.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.entity.AirTicketPrice;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Departure;
import com.wenjing.entity.Product;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.TourlineTourdate;
import com.wenjing.entity.Tourprice;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DepartureDateService;
import com.wenjing.service.DepartureService;
import com.wenjing.service.ProductService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourLineCalendarService;
import com.wenjing.service.TourlineService;
import com.wenjing.service.TourlineTourdateService;
import com.wenjing.util.Tools;
import com.wenjing.util.WeekDayUtil;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-6 下午3:17:20 
 * 类说明 : 线路日期 Controller
 */
@Controller
@RequestMapping("/admin/tourdate")
public class TourDateController {

	@Resource
	private TourDateService tourDateService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private CostService costService;
	@Resource
	private ProductService productService;
	@Resource
	private DepartureService departureService;
	@Resource
	private DepartureDateService departureDateService;
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private TourlineTourdateService tourlineTourdateService;
	@Resource
	private TourLineCalendarService tourLineCalendarService;
	@Resource
	private TourlineService tourlineService;

	/**
	 * 列表展示
	 */
	@RequestMapping("/list")
	public String list(@RequestParam("productId") String productId, HttpServletResponse response, Model model) {
		Product product = productService.findById(productId);
		Integer type = product.getType();
		String manager = getmangerUrl(productId, type);
		product.setManagerUrl(manager);
		List<Tourdate> tourdates = tourDateService.findByProductId(productId, request, response, model);
		model.addAttribute("tourdates", tourdates);
		model.addAttribute("product", product);
		return "/admin/manage/tourdate/list.ftl";
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public String add(@RequestParam("productId") String productId, Model model) {
		List<String> costs = Tools.getCostNumber(request);
		Product product = productService.findById(productId);
		Integer maxSort = tourDateService.getMaxSortBytourlineid(productId, costs.get(0));
		String nowtime = Tools.getTime();
		List<Currency> currencies = currencyService.findAll();
		List<Departure> departures = departureService.findByDepartureIds(product.getDepartureIds());
		List<String> costIdList = Tools.getCostNumber(request);
		Cost costCurrency = costService.getCurrencyByid(costIdList.get(0)); //默认查询第一个costid
		model.addAttribute("maxSort", maxSort + 1);
		model.addAttribute("nowtime", nowtime);
		model.addAttribute("productId", productId);
		model.addAttribute("currencies", currencies);
		model.addAttribute("departures", departures);
		model.addAttribute("product", product);
		model.addAttribute("costCurrency", costCurrency);
		return "/admin/manage/tourdate/add.ftl";
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		Tourdate tourdate = tourDateService.findByTourDateId(id);
		Product product = productService.findById(tourdate.getProductid());
		List<Currency> currencies = currencyService.findAll();
		String departureidstr = departureDateService.findByTourDateId(id);
		List<Departure> departures = departureService.findByDepartureIds(product.getDepartureIds());
		List<AirTicketPrice> aTicketPrices = tourDateService.findAirTicketByPriceId(tourdate.getTourprice().getId());
		model.addAttribute("tourdate", tourdate);
		model.addAttribute("currencies", currencies);
		model.addAttribute("departureidstr", departureidstr);
		model.addAttribute("departures", departures);
		model.addAttribute("product", product);
		model.addAttribute("aTicketPrices", aTicketPrices);
		return "/admin/manage/tourdate/update.ftl";
	}

	/**
	 * 新增保存
	 */
	@RequestMapping("/save")
	public String save(Tourdate tourdate, Tourprice tourprice, HttpServletRequest request) {
		String productId = tourdate.getProductid();
		// 批量保存
		// tourDateService.batchInsertTourDateAndPrice(tourdate,
		// tourprice,request);
		// 非批量保存
		tourDateService.insertTourDateAndPrice(tourdate, tourprice, request);
		return "redirect:/admin/tourdate/list.do?productId=" + productId;
	}

	/**
	 * 修改保存
	 */
	@RequestMapping("/updatesave")
	public String updatesave(Tourdate tourdate, Tourprice tourprice,
			HttpServletRequest request) {
		String productId = tourdate.getProductid();
		// 批量添加出发日期修改保存
		// tourDateService.batchUpdateTourDateAndPrice(tourdate, tourprice,
		// request);
		// 非批量添加出发日期修改保存
		tourDateService.updateTourDateAndPrice(tourdate, tourprice, request);
		return "redirect:/admin/tourdate/list.do?productId=" + productId;
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String tourdateid,
			@RequestParam("productid") String productid) {
		tourDateService.delete(tourdateid, productid);
		tourDateService.addCostNumToTourline(productid);

		return "redirect:/admin/tourdate/list.do?productId=" + productid;
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping("/deleteBatch")
	public String deleteBatch(String[] dateIds,String productid) {
		for(String tourdateid : dateIds){
			tourDateService.delete(tourdateid, productid);
		}

		return "redirect:/admin/tourdate/list.do?productId=" + productid;
	}

	/**
	 * 异步获取orderid最大值和上级导航
	 */
	@RequestMapping("/getsort")
	@ResponseBody
	public Map<String, Object> getSort(@RequestParam("costnumber") String costnumber, @RequestParam("tourlineid")String tourlineid) {
		Map<String, Object> root = new HashMap<String, Object>();
		int maxSort = tourDateService.getMaxSortBytourlineid(tourlineid, costnumber);// 根据运营中心costnumber,查询orderid最大值
		root.put("maxSort", maxSort + 1);
		return root;
	}
	
	/**
	 * 异步获取出发地
	 */
	@RequestMapping("/getDeparture")
	@ResponseBody
	public List<Departure> getDeparture(@RequestParam("costnumber") String costnumber) {
		List<Departure> departures = departureService.findAll();
		return departures;
	}
	
	/**
	 * @Title: getTourlineTourdate
	 * @Description:     导出线路对应的出发日期
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/tourlineTourdate")
	public void tourlineTourdate(){
		tourlineTourdateService.delete();	//删除表里所有数据
		List<Tourdate> tourdates = tourDateService.getDistinctTourdate();
		for (Tourdate tourdate : tourdates) {
			if (tourdate.getCostNumber().equals("d8fe5ef1de7747ab86588f9880f1aa77")) {
				
				TourlineTourdate tourlineTourdate = new TourlineTourdate(); 
//				tourlineTourdate.setId(Tools.getUUID());
				String productId = tourdate.getProductid();
				tourlineTourdate.setTourlineId(productId);
				String costNumber = tourdate.getCostNumber();
				tourlineTourdate.setCostNumber(costNumber);
				Product product = productService.findById(productId);
				if (product !=null) {
					tourlineTourdate.setTourCode(product.getCode());
				}
				Set<String> dateAll = new HashSet<String>();	//存放日期的set集合
				//线路对应日期
				List<Tourdate> tourDateList = tourDateService.getAllTourdate(tourdate.getProductid());
				for (Tourdate tourdate2 : tourDateList) {
					String startTimeString = Tools.getTime(tourdate2.getStartdate());
					String endTimeString = Tools.getTime(tourdate2.getEnddate());
					if (tourdate2.getDays()==0) {	//不是隔几天发团
						if (tourdate2.getDateweek().equals("0") || tourdate2.getDateweek().equals("8")) {//当天或天天发团
							List<String> dateList = WeekDayUtil.findDates(startTimeString,endTimeString);
							for (String date : dateList) {
								dateAll.add(date);
							}
						}else{
							String dateweekString = WeekDayUtil.getStringWeek(tourdate2.getDateweek());
							String[] dateList = WeekDayUtil.getDates(startTimeString, endTimeString, dateweekString);
							for (int i = 0; i < dateList.length; i++) {
								dateAll.add(dateList[i]);
							}
						}
					}else{		//隔几天发团
						int days = tourdate2.getDays();
						if (tourdate2.getDateweek().equals("0") || tourdate2.getDateweek().equals("8")) {	//没有选择星期几
							List<String> dateList = WeekDayUtil.findDates(startTimeString,endTimeString);
							for (int i = 0; i < dateList.size(); i+=days) {
								dateAll.add(dateList.get(i));
							}
						}else{	//选择星期几
							String dateweekString = WeekDayUtil.getStringWeek(tourdate2.getDateweek());
							String[] dateweekArray = dateweekString.split(",");
							for (int i = 0; i < dateweekArray.length; i++) {
								String[] dateList = WeekDayUtil.getDates(startTimeString, endTimeString, dateweekArray[i]);
								for (int j = 0; j < dateList.length; j+=days/7) {
									dateAll.add(dateList[j]);
								}
							}
							
						}
					}
				}
//				String dateAllString = Tools.ListToString(new ArrayList<String>(dateAll));
//				tourlineTourdate.setDepartureDates(dateAllString);
//				tourlineTourdateService.Insert(tourlineTourdate);
				for (String date : dateAll) {
					tourlineTourdate.setId(Tools.getUUID());
					tourlineTourdate.setDepartureDates(date);
					tourlineTourdateService.Insert(tourlineTourdate);
				}
			}
		}
	}
	
	/**
	 * @Title copyPrice
	 * @Description 价格复制
	 * @Author Bowden
	 * @CreateDate 2015-9-14 上午11:25:25
	 */
	@RequestMapping("/copyPrice")
	public String copyPrice(@RequestParam("tourlineId") String tourlineId, 
			                @RequestParam("costNum") String costNum,
			                final RedirectAttributes rAttributes){
		boolean flag = tourDateService.copyPrice(tourlineId, costNum,null,null);
		if(flag){
			rAttributes.addFlashAttribute("message", "复制成功，请检查核对！");
		}else {
			rAttributes.addFlashAttribute("message", "复制失败，请选择重试！");
		}
		return "redirect:/admin/tourdate/list.do?tourlineId=" + tourlineId;
	}
	
	private String getmangerUrl(String productId,Integer type){
		String path = request.getContextPath();
		String managerUrl = null;
		switch (type) {
		case 1:managerUrl="<a href="+path+"/admin/tourline/list.do?productId="+productId+">线路管理</a>";break;
		case 2:managerUrl="<a href="+path+"/admin/visa/list.do?productId="+productId+">签证管理</a>";break;
		case 3:managerUrl="<a href="+path+"/admin/tourline/list.do?productId="+productId+">线路管理</a>";break;
		case 4:managerUrl="<a href="+path+"/admin/hotel/list.do?productId="+productId+">酒店管理</a>";break;
		case 5:managerUrl="<a href="+path+"/admin/tourline/list.do?productId="+productId+">线路管理</a>";break;
		case 6:managerUrl="<a href="+path+"/admin/tourline/list.do?productId="+productId+">线路管理</a>";break;

		default:managerUrl="<a href="+path+"/admin/tourline/list.do?productId="+productId+">线路管理</a>";break;
		}
		return managerUrl;
	}

	/**
	 * 查看销售情况
	 * @param productId
	 * @param model
	 * @return
	 */
	@RequestMapping("/inventory")
	public String viewDate(@RequestParam("productId") String productId,@Param("departureId") String departureId, Model model){
		Tourline tourline = tourlineService.findByProductId(productId);
		Product product = productService.findById(productId);
		
		if(!StringUtils.isEmpty(product.getDepartureIds())){
		    List<Departure> departureList = departureService.findByDepartureIds(product.getDepartureIds());
		    model.addAttribute("departureList", departureList);
	    }
		
		long startdate =  tourDateService.findMineDate(productId);
		
		Date start = new Date(startdate * 1000);
		String startStr = Tools.getTime((int)startdate);
		List<Tourdate> tourdatelist = null;
		try {
			tourdatelist = tourLineCalendarService.getDepartureDatesWithCa(tourline.getId(),departureId,start);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> costs = Tools.getCostNumber(request);
		model.addAttribute("tourline", tourline);
		model.addAttribute("costNum", costs.get(0));
		model.addAttribute("tourdatelist",tourdatelist);
		model.addAttribute("product",product);
		model.addAttribute("count",tourdatelist.size()-1);
		model.addAttribute("startStr",startStr);
		return "/admin/manage/tourdate/view.ftl";
	}

	
}

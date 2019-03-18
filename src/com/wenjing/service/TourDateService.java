package com.wenjing.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.dao.AirTicketPriceMapper;
import com.wenjing.dao.CostMapper;
import com.wenjing.dao.CurrencyMapper;
import com.wenjing.dao.DepartureDateMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.TourdateMapper;
import com.wenjing.dao.TourlineMapper;
import com.wenjing.dao.TourpriceMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.AirTicketPrice;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Currency;
import com.wenjing.entity.DepartureDate;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tourprice;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;
import com.wenjing.util.WeekDayUtil;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-6 下午3:18:36 类说明 : 线路日期 Service
 */
@Service
public class TourDateService {
	@Resource
	private TourdateMapper tourdateMapper;
	@Resource
	private TourpriceMapper tourpriceMapper;
	@Resource
	private DepartureDateMapper departureDateMapper;
	@Resource
	private TourlineMapper tourlineMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private AirTicketPriceMapper airTicketPriceMapper;
	@Resource
	private CurrencyMapper currencyMapper;
	@Resource
	private CostMapper costMapper;
	@Resource
	private ShowTourlineService showtourlineService;
	
	private static String singleday = "8"; //单日发团
	
	private static String everyday = "0"; //天天发团

	/**
	 * 根据productId对应下的出发日期
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tourdate> findByProductId(String productId, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> coList = new ArrayList<String>();
		String costId = request.getParameter("costId");
		//分销售中心查询
		if (costId == null || "".equals(costId)) {
			costId = WebUtils.getCookie(request, "dateCostId");
			if (costId == null || "".equals(costId)) {
				coList = Tools.getCostNumber(request);
			}else {
				coList.add(costId);
			}
		} else {
			coList.add(costId);
			WebUtils.addCookie(request, response, "dateCostId", costId);
		}
		List<Tourdate> tourdates = tourdateMapper.findByProductId(productId, coList);
		for (Tourdate tourdate : tourdates) {
			if (tourdate.getStartdate() != null) {
				tourdate.setStartdatestr(Tools.getTime(tourdate.getStartdate()));
			} else {
				tourdate.setStartdatestr("");
			}
			if (tourdate.getEnddate() != null) {
				tourdate.setEnddatestr(Tools.getTime(tourdate.getEnddate()));
			} else {
				tourdate.setEnddatestr("");
			}
		}
		model.addAttribute("costNumList", coList);
		return tourdates;
	}

	/**
	 * 出发日期减少剩余数量
	 * 
	 * @param tourDateId
	 */
	@Transactional(readOnly = true)
	public void updateDatePeopleRemain(String tourDateId,int orderPeople){
		tourdateMapper.updateDatePeopleRemain(tourDateId, orderPeople);
	}
	
	/**
	 * 根据tourlineid对应下的出发日期
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tourdate> findBytourlineid(String productid, String costnumber) {
		List<Tourdate> tourdates = tourdateMapper.findByproductAndcostnum(productid, costnumber);
		for (Tourdate tourdate : tourdates) {
			if (tourdate.getStartdate() != null) {
				tourdate.setStartdatestr(Tools.getTime(tourdate.getStartdate()));
			} else {
				tourdate.setStartdatestr("");
			}
			if (tourdate.getEnddate() != null) {
				tourdate.setEnddatestr(Tools.getTime(tourdate.getEnddate()));
			} else {
				tourdate.setEnddatestr("");
			}
		}
		return tourdates;
	}

	/**
	 * 获取当前排序最大值
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMaxSort(String costnumber) {
		return tourdateMapper.getMaxSort(costnumber);
	}

	/**
	 * 获取当前线路排序最大值
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMaxSortBytourlineid(String productid, String costNum) {
		return tourdateMapper.getMaxSortByproductid(productid, costNum);
	}

	
	/**
	 * 批量添加出发日期保存
	 * 
	 * @param tourdate
	 */
	@Transactional
	public void batchInsertTourDateAndPrice(Tourdate tourdate,
			Tourprice tourprice, HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String[] departureidArr = request.getParameterValues("departureid");
		String datelimit = tourdate.getDatelimit();
		String dateweek = tourdate.getDateweek();
		String dateFrom = "";
		String dateEnd = "";
		int successdate = 0;
		int successprice = 0;
		if (datelimit != null && !"".equals(datelimit)) {
			String[] arrdate = datelimit.split("~");
			dateFrom = arrdate[0];
			dateEnd = arrdate[1];
		}

		if (dateFrom.equals(dateEnd)) {
			tourdate.setStartdate(Tools.getTimestemp(dateFrom));
			if (tourdate.getIsshow() == null) {
				tourdate.setIsshow(false);
			}
			if (tourdate.getIshot() == null) {
				tourdate.setIshot(false);
			}
			if (tourdate.getIscall() == null) {
				tourdate.setIscall(false);
			}
			tourdate.setUpdatetime(new Date());
			tourdate.setEditor(admin.getUsername());// 获取编辑人名称
			tourdate.setId(Tools.getUUID());

			tourprice.setTourdateid(tourdate.getId());
			tourprice.setId(Tools.getUUID());

			successdate = tourdateMapper.insertSelective(tourdate);
			successprice = tourpriceMapper.insertSelective(tourprice);
			if (successdate > 0 && successprice > 0 && departureidArr != null) {
				List<DepartureDate> departureDateList = new ArrayList<DepartureDate>();
				for (int i = 0; i < departureidArr.length; i++) {
					DepartureDate departureDate = new DepartureDate();
					departureDate.setId(Tools.getUUID());
					departureDate.setDepartureid(departureidArr[i]);
					departureDate.setTourdateid(tourdate.getId());
					departureDateList.add(departureDate);
				}
				departureDateMapper.batchAdd(departureDateList); // 关联表数据保存
			}
		} else {
			if (dateweek != null && !"".equals(dateweek)) {
				String[] dateArray = WeekDayUtil.getDates(dateFrom, dateEnd,
						dateweek); // 获取指定星期对应的日期
				int sort = tourdate.getSort();
				List<Tourdate> tourdateList = new ArrayList<Tourdate>();
				List<Tourprice> tourpricesList = new ArrayList<Tourprice>();
				List<DepartureDate> departureDateList = new ArrayList<DepartureDate>();
				for (int i = 0; i < dateArray.length; i++) {
					Tourdate tdate = new Tourdate();
					Tourprice tprice = new Tourprice();
					tdate.setStartdate(Tools.getTimestemp(dateArray[i]));
					if (tourdate.getIsshow() == null) {
						tdate.setIsshow(false);
					} else {
						tdate.setIsshow(tourdate.getIsshow());
					}
					if (tourdate.getIshot() == null) {
						tdate.setIshot(false);
					} else {
						tdate.setIshot(tourdate.getIshot());
					}
					if (tourdate.getIscall() == null) {
						tdate.setIscall(false);
					} else {
						tdate.setIscall(tourdate.getIscall());
					}
					tdate.setSort(sort++);
					tdate.setUpdatetime(new Date());
					tdate.setEditor(admin.getUsername());
					tdate.setDateweek(dateweek);
					tdate.setTotalnum(tourdate.getTotalnum());
					tdate.setSoldnum(tourdate.getSoldnum());
					tdate.setRemainnum(tourdate.getRemainnum());
					tdate.setRemark(tourdate.getRemark());
					tdate.setProductid(tourdate.getProductid());
					tdate.setId(Tools.getUUID());

					tprice.setId(Tools.getUUID());
					tprice.setMarkedprice(tourprice.getMarkedprice());
					tprice.setSellingprice(tourprice.getSellingprice());
					tprice.setSingleroomprice(tourprice.getSingleroomprice());
					tprice.setExtrabedprice(tourprice.getExtrabedprice());
					tprice.setNobedprice(tourprice.getNobedprice());
					tprice.setCurrencyid(tourprice.getCurrencyid());
					tprice.setTourdateid(tdate.getId());
					tprice.setCostnumber(tourprice.getCostnumber());

					tourdateList.add(tdate);
					tourpricesList.add(tprice);
					if (departureidArr != null) {
						for (int j = 0; j < departureidArr.length; j++) {
							DepartureDate departureDate = new DepartureDate();
							departureDate.setId(Tools.getUUID());
							departureDate.setDepartureid(departureidArr[j]);
							departureDate.setTourdateid(tdate.getId());
							departureDateList.add(departureDate);
						}
					}
				}
				successdate = tourdateMapper.batchAddDate(tourdateList); // 保存tourdate
				if (successdate > 0) {
					successprice = tourpriceMapper
							.batchAddprice(tourpricesList); // 保存tourprice
				}
				if (successprice > 0 && departureidArr != null) {
					departureDateMapper.batchAdd(departureDateList); // 关联表数据保存
				}
			} else {
				List<String> dateList = WeekDayUtil
						.findDates(dateFrom, dateEnd); // 获取时间段内所有日期
				int sort = tourdate.getSort();
				List<Tourdate> tourdateList = new ArrayList<Tourdate>();
				List<Tourprice> tourpricesList = new ArrayList<Tourprice>();
				List<DepartureDate> departureDateList = new ArrayList<DepartureDate>();
				for (String date : dateList) {
					Tourdate tdate = new Tourdate();
					Tourprice tprice = new Tourprice();
					tdate.setStartdate(Tools.getTimestemp(date));
					if (tourdate.getIsshow() == null) {
						tdate.setIsshow(false);
					} else {
						tdate.setIsshow(tourdate.getIsshow());
					}
					if (tourdate.getIshot() == null) {
						tdate.setIshot(false);
					} else {
						tdate.setIshot(tourdate.getIshot());
					}
					if (tourdate.getIscall() == null) {
						tdate.setIscall(false);
					} else {
						tdate.setIscall(tourdate.getIscall());
					}
					tdate.setSort(sort++);
					tdate.setUpdatetime(new Date());
					tdate.setEditor(admin.getUsername());
					tdate.setDateweek(dateweek);
					tdate.setTotalnum(tourdate.getTotalnum());
					tdate.setSoldnum(tourdate.getSoldnum());
					tdate.setRemainnum(tourdate.getRemainnum());
					tdate.setRemark(tourdate.getRemark());
					tdate.setProductid(tourdate.getProductid());
					tdate.setId(Tools.getUUID());

					tprice.setId(Tools.getUUID());
					tprice.setMarkedprice(tourprice.getMarkedprice());
					tprice.setSellingprice(tourprice.getSellingprice());
					tprice.setSingleroomprice(tourprice.getSingleroomprice());
					tprice.setExtrabedprice(tourprice.getExtrabedprice());
					tprice.setNobedprice(tourprice.getNobedprice());
					tprice.setCurrencyid(tourprice.getCurrencyid());
					tprice.setTourdateid(tdate.getId());
					tprice.setCostnumber(tourprice.getCostnumber());

					tourdateList.add(tdate);
					tourpricesList.add(tprice);
					if (departureidArr != null) {
						for (int j = 0; j < departureidArr.length; j++) {
							DepartureDate departureDate = new DepartureDate();
							departureDate.setId(Tools.getUUID());
							departureDate.setDepartureid(departureidArr[j]);
							departureDate.setTourdateid(tdate.getId());
							departureDateList.add(departureDate);
						}
					}
				}
				successdate = tourdateMapper.batchAddDate(tourdateList); // 保存tourdate
				if (successdate > 0) {
					successprice = tourpriceMapper
							.batchAddprice(tourpricesList); // 保存tourprice
				}
				if (successprice > 0 && departureidArr != null) {
					departureDateMapper.batchAdd(departureDateList); // 关联表数据保存
				}
			}
		}
	}

	/**
	 * 新增单条出发日期保存
	 * 
	 * @param tourdate
	 */
	@Transactional
	public void insertTourDateAndPrice(Tourdate tourdate, Tourprice tourprice,
			HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String[] departureidArr = request.getParameterValues("departureid"); //获取出发地id数组
		String[] airTicketDepartureArr = request.getParameterValues("airTicketDeparture");
		String[] airTicketPriceArr = request.getParameterValues("airTicketPrice");
		String[] sortArr = request.getParameterValues("sortArr");
		String[] departureNameArr = request.getParameterValues("departureName");
		String productid = request.getParameter("productid");
		List<DepartureDate> departureDateList = new ArrayList<DepartureDate>();
		String datelimit = tourdate.getDatelimit();
		String dateweek = tourdate.getDateweek();
		String dateFrom = "";
		String dateEnd = "";
		int successdate = 0;
		int successprice = 0;
		if (datelimit != null && !"".equals(datelimit)) {
			String[] arrdate = datelimit.split("~");
			dateFrom = arrdate[0];
			dateEnd = arrdate[1];
		}
		if (dateweek != null && !"".equals(dateweek)) {  // 如果选择星期几
			if(dateFrom.equals(dateEnd)){  //开始日期=结束日期为单日发团
				tourdate.setDateweek(singleday);
			}else {
				if("1,2,3,4,5,6,7".equals(dateweek)){ //如果星期全选
					if(tourdate.getDays()==0){
						tourdate.setDateweek(everyday); //每日发团
					}else {
						tourdate.setDateweek(dateweek);
					}
				}else {
					tourdate.setDateweek(dateweek);
				}
			}
		} else {
			if(dateFrom.equals(dateEnd)){  // 如果开始时间等于结束时间
				tourdate.setDateweek(singleday);
			}else {
				tourdate.setDateweek(everyday);
			}
		}
		tourdate.setStartdate(Tools.getTimestemp(dateFrom));
		tourdate.setEnddate(Tools.getTimestemp(dateEnd));
		if (tourdate.getIsshow() == null) {
			tourdate.setIsshow(false);
		}
		if (tourdate.getIshot() == null) {
			tourdate.setIshot(false);
		}
		if (tourdate.getIscall() == null) {
			tourdate.setIscall(false);
		}
		tourdate.setUpdatetime(new Date());
		tourdate.setEditor(admin.getUsername());// 获取编辑人名称
		tourdate.setId(Tools.getUUID());
		tourdate.setCostNumber(tourprice.getCostnumber());

		Currency currency = currencyMapper.findByCostNumId(tourprice.getCostnumber());
		tourprice.setCurrencyid(currency.getId());
		tourprice.setTourdateid(tourdate.getId());
		tourprice.setId(Tools.getUUID());
		productMapper.setProductUnsynchronizeByProductId(tourdate.getProductid());
		successdate = tourdateMapper.insertSelective(tourdate);
		successprice = tourpriceMapper.insertSelective(tourprice);
		if (successdate > 0 && successprice > 0 && departureidArr != null) {
			for (int j = 0; j < departureidArr.length; j++) {
				DepartureDate departureDate = new DepartureDate();
				departureDate.setId(Tools.getUUID());
				departureDate.setDepartureid(departureidArr[j]);
				departureDate.setTourdateid(tourdate.getId());
				departureDateList.add(departureDate);
			}
			departureDateMapper.batchAdd(departureDateList); // 关联表数据保存
		}
		if(airTicketDepartureArr !=null && airTicketPriceArr !=null && sortArr!=null && departureNameArr!=null){
			List<AirTicketPrice> aList = new ArrayList<AirTicketPrice>();
			for (int i = 0; i < airTicketPriceArr.length; i++) {
				AirTicketPrice airTicketPrice = new AirTicketPrice(Tools.getUUID(), new BigDecimal(airTicketPriceArr[i].trim()), 
						          airTicketDepartureArr[i], departureNameArr[i], tourprice.getId(), Integer.parseInt(sortArr[i]));
				aList.add(airTicketPrice);
			}
			airTicketPriceMapper.batchAdd(aList);
		}
		//modPrductMinprice(tourdate.getTourlineid(), productid); //修改最低价
		addCostNumToTourline(productid);//将价格对应的costnumid添加到线路表
        this.updatetourlineTourdate(tourdate.getProductid(), tourdate.getCostNumber());
	}

	/**
	 * 将价格对应的costnumid添加到线路表
	 * @param tourlineid
	 * @param costnumber
	 */
	@Transactional
	public void addCostNumToTourline(String productid) {

		List<String> costNumIdList = tourpriceMapper.getPriceCostNumByProductid(productid);
		String costNumIds =  "";
		if(costNumIdList!=null && costNumIdList.size()>0){
		   costNumIds = Tools.ListToString(costNumIdList);
		}
		showtourlineService.delete(null,productid );
		for (String string : costNumIdList) {
			showtourlineService.save(string,productid);
		}
		//tourlineMapper.updateCostNumIds(tourline.getId(), costNumIds);
		productMapper.updateCostNumIds(productid, costNumIds);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Tourdate findByTourDateId(String id) {
		Tourdate tourdate = tourdateMapper.findByTourDateId(id);
		if (tourdate.getStartdate() != null) {
			tourdate.setStartdatestr(Tools.getTime(tourdate.getStartdate()));
		} else {
			tourdate.setStartdatestr("");
		}
		if (tourdate.getEnddate() != null) {
			tourdate.setEnddatestr(Tools.getTime(tourdate.getEnddate()));
		} else {
			tourdate.setEnddatestr("");
		}
		tourdate.setDatelimit(tourdate.getStartdatestr() + "~"
				+ tourdate.getEnddatestr());
		return tourdate;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	public void delete(String tourdateid, String productid) {
		departureDateMapper.deletebytourdateid(tourdateid); // 删除出发地等于此dateid的关联表中的数据
		Tourprice tourprice = tourpriceMapper.selectByTourdateid(tourdateid);
		int success = 0;
		if (tourprice != null) {
			String priceId = tourprice.getId();
			airTicketPriceMapper.deleteAirTickerByPriceId(priceId);// 删除关联机票价格信息
			success = tourpriceMapper.deleteByPrimaryKey(priceId);
		}
		if (success > 0) {
			productMapper.setProductUnsynchronizeByProductId(productid);
			tourdateMapper.deleteByPrimaryKey(tourdateid);
		}
		//modPrductMinprice(tourlineid, productid); //修改最低价
	}

	/**
	 * 批量添加出发日期修改保存
	 * 
	 * @param tourdate
	 * @param tourprice
	 */
	@Transactional
	public void batchUpdateTourDateAndPrice(Tourdate tourdate,
		Tourprice tourprice, HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String tourdateid = request.getParameter("dateid");
		String tourpriceid = request.getParameter("priceid");
		if (tourdateid != null && tourpriceid != null && !"".equals(tourdateid)
				&& !"".equals(tourpriceid)) {
			tourdate.setId(tourdateid);
			tourprice.setId(tourpriceid);
		}
		int successdate = 0;
		int successprice = 0;
		String[] departureidArr = request.getParameterValues("departureid");
		departureDateMapper.deletebytourdateid(tourdateid); // 删除等于此dateid的关联表中的数据
		if (tourdate.getIsshow() == null) {
			tourdate.setIsshow(false);
		}
		if (tourdate.getIshot() == null) {
			tourdate.setIshot(false);
		}
		if (tourdate.getIscall() == null) {
			tourdate.setIscall(false);
		}
		tourdate.setStartdate(Tools.getTimestemp(tourdate.getStartdatestr()));
		tourdate.setUpdatetime(new Date());
		tourdate.setEditor(admin.getUsername());
		tourprice.setTourdateid(tourdate.getId());
		productMapper.setProductUnsynchronizeByTourdate(tourdate.getId());
		successdate = tourdateMapper.updateByPrimaryKey(tourdate);
		successprice = tourpriceMapper.updateByPrimaryKey(tourprice);
		if (successdate > 0 && successprice > 0 && departureidArr != null) {
				for (int i = 0; i < departureidArr.length; i++) {
					DepartureDate departureDate = new DepartureDate();
					departureDate.setId(Tools.getUUID());
					departureDate.setDepartureid(departureidArr[i]);
					departureDate.setTourdateid(tourdate.getId());
					departureDateMapper.insert(departureDate);
				}
		}
	}

	/**
	 * 非批量添加的出发日期修改保存
	 * 
	 * @param tourdate
	 * @param tourprice
	 */
	@Transactional
	public void updateTourDateAndPrice(Tourdate tourdate, Tourprice tourprice,
			HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		List<DepartureDate> departureDateList = new ArrayList<DepartureDate>();
		String tourdateid = request.getParameter("dateid");
		String tourpriceid = request.getParameter("priceid");
		String productid = request.getParameter("productid");
		String[] airTicketDepartureArr = request.getParameterValues("airTicketDeparture");
		String[] airTicketPriceArr = request.getParameterValues("airTicketPrice");
		String[] sortArr = request.getParameterValues("sortArr");
		String[] departureNameArr = request.getParameterValues("departureName");
		if (tourdateid != null && tourpriceid != null && !"".equals(tourdateid)
				&& !"".equals(tourpriceid)) {
			tourdate.setId(tourdateid);
			tourprice.setId(tourpriceid);
		}
		String datelimit = tourdate.getDatelimit();
		String dateweek = tourdate.getDateweek();
		String dateFrom = "";
		String dateEnd = "";
		int successdate = 0;
		int successprice = 0;
		if (datelimit != null && !"".equals(datelimit)) {
			String[] arrdate = datelimit.split("~");
			dateFrom = arrdate[0];
			dateEnd = arrdate[1];
		}
		String[] departureidArr = request.getParameterValues("departureid");
		departureDateMapper.deletebytourdateid(tourdateid); // 删除等于此dateid的关联表中的数据
		if (tourdate.getIsshow() == null) {
			tourdate.setIsshow(false);
		}
		if (tourdate.getIshot() == null) {
			tourdate.setIshot(false);
		}
		if (tourdate.getIscall() == null) {
			tourdate.setIscall(false);
		}
		tourdate.setStartdate(Tools.getTimestemp(dateFrom));
		tourdate.setEnddate(Tools.getTimestemp(dateEnd));
		if (dateweek != null && !"".equals(dateweek)) {  // 如果选择星期几
			if(dateFrom.equals(dateEnd)){  //开始日期=结束日期为单日发团
				tourdate.setDateweek(singleday);
			}else {
				if("1,2,3,4,5,6,7".equals(dateweek)){ //如果星期全选
					if(tourdate.getDays()==0){
						tourdate.setDateweek(everyday); //每日发团
					}else {
						tourdate.setDateweek(dateweek);
					}
				}else {
					tourdate.setDateweek(dateweek);
				}
			}
		} else {
			if(dateFrom.equals(dateEnd)){  // 如果开始时间等于结束时间
				tourdate.setDateweek(singleday);
			}else {
				tourdate.setDateweek(everyday);
			}
		}
		tourdate.setUpdatetime(new Date());
		tourdate.setEditor(admin.getUsername());
		tourdate.setCostNumber(tourprice.getCostnumber());
		tourprice.setTourdateid(tourdate.getId());
		Currency currency = currencyMapper.findByCostNumId(tourprice.getCostnumber());
		tourprice.setCurrencyid(currency.getId());
		productMapper.setProductUnsynchronizeByTourdate(tourdate.getId());
		successdate = tourdateMapper.updateByPrimaryKey(tourdate);
		successprice = tourpriceMapper.updateByPrimaryKey(tourprice);
		if (successdate > 0 && successprice > 0 && departureidArr != null) {
			for (int j = 0; j < departureidArr.length; j++) {
				DepartureDate departureDate = new DepartureDate();
				departureDate.setId(Tools.getUUID());
				departureDate.setDepartureid(departureidArr[j]);
				departureDate.setTourdateid(tourdate.getId());
				departureDateList.add(departureDate);
			}
			departureDateMapper.batchAdd(departureDateList); // 关联表数据保存
		}
		
		airTicketPriceMapper.deleteAirTickerByPriceId(tourprice.getId()); 
		if(airTicketDepartureArr !=null && airTicketPriceArr !=null && sortArr!=null && departureNameArr!=null){
			List<AirTicketPrice> aList = new ArrayList<AirTicketPrice>();
			for (int i = 0; i < airTicketPriceArr.length; i++) {
				AirTicketPrice airTicketPrice = new AirTicketPrice(Tools.getUUID(), new BigDecimal(airTicketPriceArr[i].trim()), 
						          airTicketDepartureArr[i], departureNameArr[i], tourprice.getId(), Integer.parseInt(sortArr[i]));
				aList.add(airTicketPrice);
			}
			airTicketPriceMapper.batchAdd(aList);
		}
		//modPrductMinprice(tourdate.getTourlineid(), productid); //修改最低价
		addCostNumToTourline(productid);//将价格对应的costnumid添加到线路表
		this.updatetourlineTourdate(tourdate.getProductid(), tourdate.getCostNumber());
	}

	/**
	 * @Title: getminsprice
	 * @Description: 查询线路最低售价
	 * @param tourlineId
	 * @return
	 * @return BigDecimal 返回类型
	 * @author xiejin
	 */
	@Transactional
	public BigDecimal getminsellPrice(String tourlineId,String costnumber) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourpriceMapper.getminsellPrice(tourlineId,costnumber,time);
	}

	/**
	 * @Title: getminmprice
	 * @Description: 查询线路最低标价
	 * @param tourlineId
	 * @return
	 * @return BigDecimal 返回类型
	 * @author xiejin
	 */
	@Transactional
	public BigDecimal getminmprice(String tourlineId,String costnumber) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourpriceMapper.getminmprice(tourlineId,costnumber,time);
	}
	
	/**
	 * 查出最低标价的出发日期
	 * @param tourlineId
	 * @param costnumber
	 * @return
	 */
	public List<Tourdate> getTourdateWithMinMPrice(String tourlineId,String costnumber){
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourdateMapper.getTourdateWithMinMPrice(tourlineId, costnumber, time);
	}
	
	/**
	 * 查出最低售价
	 * @param tourlineId
	 * @param costnumber
	 * @return
	 */
	public BigDecimal getMinSellPrice(String tourlineId,String costnumber){
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		BigDecimal min_0 = this.tourdateMapper.getMinSellPriceExcludeLandPrice(tourlineId, costnumber, time);
		if (min_0 == null) {
			min_0 = new BigDecimal(1000000000);
		}
		BigDecimal min_1 = this.tourdateMapper.getMinSellPriceIncludeLandPrice(tourlineId, costnumber, time);
		if (min_1 == null) {
			min_1 = new BigDecimal(1000000000);
		}
		if (min_0.compareTo(min_1) > 0) {
			return min_1;
		}
		return min_0;
	}
	
	/**
	 * 修改产品最低价
	 */
	@Transactional
	public void modPrductMinprice(String tourlineId, String productid){
		if(tourlineId !=null && !"".equals(tourlineId)){
			BigDecimal minprice = BigDecimal.ZERO;
			BigDecimal minsellingPrice = tourpriceMapper.getminsprice(tourlineId);
			if(minsellingPrice !=null){
				minprice = minsellingPrice;
			}
			productMapper.updateminprice(minprice, productid); //修改最低价
		}
	}
	
	/**
	 * @Title: getDateAndPriceByTourlineid
	 * @Description: 查询日期和对应的价格
	 * @param tourlineid
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<Tourdate> getDateAndPriceByProductid(String productid,String costnumber){
		return tourdateMapper.getDateAndPriceByProductid(productid,costnumber);
	}

	/**
	 * 根据tourpriceid查询机票
	 * @param id
	 * @return
	 */
	@Transactional
	public List<AirTicketPrice> findAirTicketByPriceId(String tourpriceId) {
		return airTicketPriceMapper.findAirTicketByPriceId(tourpriceId);
	}

	/**
	 * 根据销售中心查询所有日期和价格（复制日期和价格）
	 * @return
	 */
	@Transactional
	public List<Tourdate> findAllDateAndPriceByCostnum(String costnum) {
		return tourdateMapper.findAllDateAndPriceByCostnum(costnum);
	}
	
	/**
	 * 根据销售中心查询所有日期和价格（复制日期和价格）
	 * @return
	 */
	@Transactional
	public List<Tourdate> findAllDateAndPriceByCostnum2(String costnum) {
		return tourdateMapper.findAllDateAndPriceByCostnum2(costnum);
	}
	
	/**
	 * @Title: getDistinctTourdate
	 * @Description:
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Tourdate> getDistinctTourdate(){
		return tourdateMapper.getDistinctTourdate();
	};
	
	/**
	 * @Title: findByIdAndCost
	 * @Description: 根据参数查询对应日期
	 * @param tourlineId
	 * @param costnumber
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Tourdate> findByIdAndCost(String tourlineId,String costnumber){
		return tourdateMapper.findByIdAndCost(tourlineId, costnumber);
	};
	
	/**
	 * @Title: getAllTourdate
	 * @Description: 根据线路id和costnumber查询对应的所有日期
	 * @param tourlineid
	 * @param costnumber
	 * @return    
	 * @return List<Tourdate>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public List<Tourdate> getAllTourdate(String tourlineid){
		return tourdateMapper.getAllTourdate(tourlineid);
	};

	/**
	 * @Title copyPrice
	 * @Description 价格复制
	 * @Author Bowden
	 * @CreateDate 2015-9-14 下午3:29:09
	 */
	@Transactional
	public boolean copyPrice(String tourlineId, String costNum,String startCost,String tourlineTo) {
		boolean flag = false;
		Cost costa = costMapper.getIsNoThisCostNum(costNum);
		if(tourlineId !=null && !"".equals(tourlineId) && costNum!=null && !"".equals(costNum)){
//			String[] dateArr = dateIds.split(",");
//			List<String> dateIdList = Arrays.asList(dateArr); 
			List<String> costNumList = new ArrayList<String>();
			costNumList.add(costNum);
			//删除costnum之外销售中心日期及价格
			List<String> tourDateIdList = tourdateMapper.findDateBytourIdNotCostNum(tourlineId, costNum);
			if(tourDateIdList != null && tourDateIdList.size()>0){
				batchDelete(tourDateIdList, tourlineId);
			}
			//查询此复制日期和价格
			List<Tourdate> dateAndPriceList = tourdateMapper.findByCostNumAndDateIds(tourlineId, startCost);
			if(dateAndPriceList == null || dateAndPriceList.size()==0){
				return false;
			}
			//如果不是美国销售中心，先复制为美国价格，然后在已美国价格复制给其他销售中心
			if(!"d8fe5ef1de7747ab86588f9880f1aa77".equals(startCost)){
				copyToUSD(costNum, dateAndPriceList);
				costNum = "d8fe5ef1de7747ab86588f9880f1aa77";
				dateAndPriceList = tourdateMapper.findByCostNumAndDateIds(tourlineId, startCost);
				costNumList.add(costNum);
			}
			
				List<Tourdate> tourdateList = new ArrayList<Tourdate>();
				List<Tourprice> tourpriceList =new ArrayList<Tourprice>();
				List<DepartureDate> departureDateList = new ArrayList<DepartureDate>();
				List<AirTicketPrice> airTicketPriceList = new ArrayList<AirTicketPrice>();
				BigDecimal exchangeRate = costa.getExchangerate(); // 美元兑换汇率
				for (Tourdate tourdate : dateAndPriceList) {
					//查询出发地
					List<DepartureDate> departureDates = departureDateMapper.findByTourDateId(tourdate.getId());
					//查询机票价格
					List<AirTicketPrice> airTicketPrices = airTicketPriceMapper.findAirTicketByPriceId(tourdate.getTourprice().getId());
					
					BigDecimal markedPrice = tourdate.getTourprice().getMarkedprice();
					if(markedPrice != null ){
						markedPrice = markedPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					BigDecimal sellingPrice = tourdate.getTourprice().getSellingprice();
					if(sellingPrice != null ){
						sellingPrice = sellingPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
//						String sellStr = sellingPrice.toString();
//						if(!sellStr.endsWith("9")){
//							String b1 = sellStr.substring(0, sellStr.length()-1);
//							sellStr = b1 + "9";
//							sellingPrice = new BigDecimal(sellStr);
//						}
					}
					
					BigDecimal threesellingPrice = tourdate.getTourprice().getThreesellingprice();
					if(threesellingPrice != null){
						threesellingPrice = threesellingPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					BigDecimal foursellingPrice = tourdate.getTourprice().getFoursellingprice();
					if(foursellingPrice != null){
						foursellingPrice = foursellingPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					BigDecimal singleRoomPrice = tourdate.getTourprice().getSingleroomprice();
					if(singleRoomPrice != null){
						singleRoomPrice = singleRoomPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					BigDecimal extraBedPrice = tourdate.getTourprice().getExtrabedprice();
					if(extraBedPrice != null){
						extraBedPrice = extraBedPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					BigDecimal noBedPrice = tourdate.getTourprice().getNobedprice();
					if(noBedPrice != null){
						noBedPrice = noBedPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					BigDecimal babyPrice = tourdate.getTourprice().getBabyPrice();
					if(babyPrice != null){
						babyPrice = babyPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					BigDecimal childPrice = tourdate.getTourprice().getChildPrice();
					if(childPrice != null){
						childPrice = childPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
					}
					
					String tourdateid = Tools.getUUID();
					Tourdate tourdate2 = new Tourdate(tourdateid, 
														tourdate.getStartdate(), 
														tourdate.getTotalnum(), 
														tourdate.getSoldnum(), 
														tourdate.getRemainnum(), 
														tourdate.getIsshow(), 
														tourdate.getIshot(), 
														tourdate.getIscall(), 
														tourdate.getRemark(), 
														tourdate.getUpdatetime(), 
														tourdate.getEditor(), 
														tourdate.getSort(), 
														tourlineTo, 
														tourdate.getEnddate(), 
														tourdate.getDateweek(),
														costa.getId(),
														tourdate.getDays(),
														tourdate.getSealGroupDate());
					tourdateList.add(tourdate2);
					for (DepartureDate departureDate : departureDates) {
						departureDate.setId(Tools.getUUID());
						departureDate.setTourdateid(tourdateid);
					}
					departureDateList.addAll(departureDates);
					
					Tourprice tourprice = new Tourprice(Tools.getUUID(), 
														markedPrice, 
														sellingPrice, 
														singleRoomPrice, 
														extraBedPrice, 
														noBedPrice, 
														costa.getCurrency().getId(), 
														tourdateid, 
														costa.getId(), 
														threesellingPrice, 
														foursellingPrice, 
														babyPrice, 
														childPrice);
					tourpriceList.add(tourprice);
					for (AirTicketPrice airTicketPrice : airTicketPrices) {
						airTicketPrice.setId(Tools.getUUID());
						BigDecimal airPrice = airTicketPrice.getPrice();
						if(singleRoomPrice != null){
							airPrice = airPrice.multiply(exchangeRate).setScale(0, BigDecimal.ROUND_CEILING);
						}
						airTicketPrice.setPrice(airPrice);
						airTicketPrice.setTourpriceId(tourprice.getId());
					}
					airTicketPriceList.addAll(airTicketPrices);
				}
				tourdateMapper.batchAddDate(tourdateList);
				tourpriceMapper.batchAddprice(tourpriceList);
				if(departureDateList !=null && departureDateList.size() > 0){
					departureDateMapper.batchAdd(departureDateList);
				}
				if(airTicketPriceList != null && airTicketPriceList.size() > 0){
					airTicketPriceMapper.batchAdd(airTicketPriceList);
				}
			
				String productid = tourlineMapper.selectByPrimaryKey(tourlineId).getProductid();
				addCostNumToTourline(productid);//将价格对应的costnumid添加到线路表
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Title copyToUSD
	 * @Description 将日期及价格复制成美元
	 * @Author Bowden
	 * @CreateDate 2015-9-15 上午10:22:15
	 */
	@Transactional
	public List<String> copyToUSD(String costnum, List<Tourdate> dateAndPriceList) {
		Cost cost = costMapper.getIsNoThisCostNum(costnum);
		String copyCostnum = "d8fe5ef1de7747ab86588f9880f1aa77"; // 要复制的销售中心id------美元
		String currencyId = "e897e21a556442a583ee14e9c22b0817"; // 美元
		BigDecimal exchangeRate = cost.getExchangerate(); // 美元兑换汇率

		List<Tourdate> tourdateList = new ArrayList<Tourdate>();
		List<Tourprice> tourpriceList = new ArrayList<Tourprice>();
		List<String> dateIds = new ArrayList<String>();
		List<DepartureDate> departureDateList = new ArrayList<DepartureDate>();
		List<AirTicketPrice> airTicketPriceList = new ArrayList<AirTicketPrice>();
		for (Tourdate tourdate : dateAndPriceList) {
			//查询出发地
			List<DepartureDate> departureDates = departureDateMapper.findByTourDateId(tourdate.getId());
			//查询机票价格
			List<AirTicketPrice> airTicketPrices = airTicketPriceMapper.findAirTicketByPriceId(tourdate.getTourprice().getId());
			BigDecimal markedPrice = tourdate.getTourprice().getMarkedprice();
			if (markedPrice != null) {
				markedPrice = markedPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			BigDecimal sellingPrice = tourdate.getTourprice().getSellingprice();
			if (sellingPrice != null) {
				sellingPrice = sellingPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
				// String sellStr = sellingPrice.toString();
				// if(!sellStr.endsWith("9")){
				// String b1 = sellStr.substring(0, sellStr.length()-1);
				// sellStr = b1 + "9";
				// sellingPrice = new BigDecimal(sellStr);
				// }
			}

			BigDecimal threesellingPrice = tourdate.getTourprice() .getThreesellingprice();
			if (threesellingPrice != null) {
				threesellingPrice = threesellingPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			BigDecimal foursellingPrice = tourdate.getTourprice().getFoursellingprice();
			if (foursellingPrice != null) {
				foursellingPrice = foursellingPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			BigDecimal singleRoomPrice = tourdate.getTourprice().getSingleroomprice();
			if (singleRoomPrice != null) {
				singleRoomPrice = singleRoomPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			BigDecimal extraBedPrice = tourdate.getTourprice().getExtrabedprice();
			if (extraBedPrice != null) {
				extraBedPrice = extraBedPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			BigDecimal noBedPrice = tourdate.getTourprice().getNobedprice();
			if (noBedPrice != null) {
				noBedPrice = noBedPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			BigDecimal babyPrice = tourdate.getTourprice().getBabyPrice();
			if (babyPrice != null) {
				babyPrice = babyPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			BigDecimal childPrice = tourdate.getTourprice().getChildPrice();
			if (childPrice != null) {
				childPrice = childPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
			}

			String tourdateid = Tools.getUUID();
			dateIds.add(tourdateid);
			Tourdate tourdate2 = new Tourdate(tourdateid,
											  tourdate.getStartdate(), 
											  tourdate.getTotalnum(),
											  tourdate.getSoldnum(), 
											  tourdate.getRemainnum(),
											  tourdate.getIsshow(), 
											  tourdate.getIshot(),
											  tourdate.getIscall(), 
											  tourdate.getRemark(),
											  tourdate.getUpdatetime(), 
											  tourdate.getEditor(),
											  tourdate.getSort(), 
										      tourdate.getProductid(),
											  tourdate.getEnddate(), 
											  tourdate.getDateweek(), 
											  copyCostnum,
											  tourdate.getDays(), 
											  tourdate.getSealGroupDate());
			tourdateList.add(tourdate2);
			for (DepartureDate departureDate : departureDates) {
				departureDate.setId(Tools.getUUID());
				departureDate.setTourdateid(tourdateid);
			}
			departureDateList.addAll(departureDates);

			Tourprice tourprice = new Tourprice(Tools.getUUID(), 
					                            markedPrice,
												sellingPrice, 
												singleRoomPrice, 
												extraBedPrice, 
												noBedPrice,
												currencyId, 
												tourdateid, 
												copyCostnum, 
												threesellingPrice,
												foursellingPrice, 
												babyPrice, 
												childPrice);
			tourpriceList.add(tourprice);
			for (AirTicketPrice airTicketPrice : airTicketPrices) {
				airTicketPrice.setId(Tools.getUUID());
				BigDecimal airPrice = airTicketPrice.getPrice();
				if(singleRoomPrice != null){
					airPrice = airPrice.divide(exchangeRate, 0, BigDecimal.ROUND_CEILING);
				}
				airTicketPrice.setPrice(airPrice);
				airTicketPrice.setTourpriceId(tourprice.getId());
			}
			airTicketPriceList.addAll(airTicketPrices);
		} 
		tourdateMapper.batchAddDate(tourdateList);
		tourpriceMapper.batchAddprice(tourpriceList);
		if(departureDateList !=null && departureDateList.size() > 0){
			departureDateMapper.batchAdd(departureDateList);
		}
		if(airTicketPriceList != null && airTicketPriceList.size() > 0){
			airTicketPriceMapper.batchAdd(airTicketPriceList);
		}
		return dateIds;
	}
	
	/**
	 * 批量删除
	 * 
	 * @param id
	 */
	@Transactional
	public void batchDelete(List<String> dateIdList, String tourlineId) {
		List<String> priceIdList = new ArrayList<String>();
		for (String dateId : dateIdList) {
			departureDateMapper.deletebytourdateid(dateId); // 删除出发地等于此dateid的关联表中的数据
			Tourprice tourprice = tourpriceMapper.selectByTourdateid(dateId);
			if (tourprice != null) {
				String priceId = tourprice.getId();
				airTicketPriceMapper.deleteAirTickerByPriceId(priceId);// 删除关联机票价格信息
				priceIdList.add(priceId);
			}
			productMapper.setProductUnsynchronizeByTourdate(dateId);
		}
		tourpriceMapper.batchDelete(priceIdList);
		tourdateMapper.batchDelete(dateIdList);
		
		String productid = tourlineMapper.selectByPrimaryKey(tourlineId).getProductid();
		addCostNumToTourline(productid);//将价格对应的costnumid添加到线路表
	}
	
	/**
	 * 
	 * @param tourlineId
	 * @param costNumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Tourdate> getDateAndPrice(String productid,String costNumber){
		return tourdateMapper.getDateAndPriceByProductid(productid,costNumber);
	}
	
	/**
	 * 
	 * @param tourDateId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Tourprice getTourPriceByTourdateid(String tourDateId){
		return tourpriceMapper.selectByTourdateid(tourDateId);
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly=true)
	public Tourdate getTourPriceByDepartureDate(String productid,String costnumber,String departureDate){
		int departureStamp = Tools.getTimestemp(departureDate);
		List<Tourdate> tourdateList = tourdateMapper.findByProductidAndCostnumberAndDepartureDate(productid,costnumber,departureStamp);
		Tourdate confirmTourdate = null;
		for(Tourdate tourdate : tourdateList){
			if(confirmTourdate == null){
				confirmTourdate = tourdate;
			}else{
				if((confirmTourdate.getEnddate() - confirmTourdate.getStartdate()) > (tourdate.getEnddate() - tourdate.getStartdate())){
					confirmTourdate = tourdate;
				}
			}
		}
		if(confirmTourdate == null){
			return null;
		}
		return confirmTourdate;
	}
	
	/**
	 * @Title: getTourlineTourdate
	 * @Description:     导出线路对应的出发日期
	 * @return void    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/tourlineTourdate")
	public void updatetourlineTourdate(String productId,String costnumber){
		        Tourline tourline = tourlineMapper.findByProductId(productId);
				Set<String> dateAll = new HashSet<String>();	//存放日期的set集合
				//线路对应日期
				List<Tourdate> tourDateList = this.getAllTourdate(productId);
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
				 
				StringBuffer buf = new StringBuffer();
				 
				for (String date : dateAll) {
				
					buf.append("','"+date);
				}
				
				String tourdatess = "";
				if(buf!=null&&buf.length()>0){
					tourdatess=buf.substring(3);
				}
				if(tourline!=null){
					tourlineMapper.updatedeparture(tourline.getId(), tourdatess);
				}
	}
	@Transactional(readOnly=true)
	public int findMineDate(String productId){
		return tourdateMapper.findMineDate(productId);
	}
	
	@Transactional
	public void updateStore(String tourdateId,int sumPeople){
		tourdateMapper.updateStore(tourdateId, sumPeople);
	}
}

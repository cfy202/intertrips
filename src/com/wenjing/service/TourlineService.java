package com.wenjing.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.Pages;
import com.wenjing.dao.AirportPickUpMapper;
import com.wenjing.dao.AttractionMapper;
import com.wenjing.dao.CostMapper;
import com.wenjing.dao.CouponseMapper;
import com.wenjing.dao.CurrencyMapper;
import com.wenjing.dao.DepartureDateMapper;
import com.wenjing.dao.DepartureMapper;
import com.wenjing.dao.DestinationMapper;
import com.wenjing.dao.FoodMapper;
import com.wenjing.dao.HotTourlineMapper;
import com.wenjing.dao.HotelMapper;
import com.wenjing.dao.ImageMapper;
import com.wenjing.dao.IndexshowtourlineMapper;
import com.wenjing.dao.ItineraryMapper;
import com.wenjing.dao.ItineraryimageMapper;
import com.wenjing.dao.NavigationMapper;
import com.wenjing.dao.OrderdetailMapper;
import com.wenjing.dao.PageMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.ProducttagMapper;
import com.wenjing.dao.ProductvideoMapper;
import com.wenjing.dao.PromotionMapper;
import com.wenjing.dao.PromotionProductMapper;
import com.wenjing.dao.RegionMapper;
import com.wenjing.dao.ReviewMapper;
import com.wenjing.dao.SelfpayMapper;
import com.wenjing.dao.ServiceItemMapper;
import com.wenjing.dao.ServiceItemProductMapper;
import com.wenjing.dao.ShowtourlineMapper;
import com.wenjing.dao.SliderMapper;
import com.wenjing.dao.TagMapper;
import com.wenjing.dao.TourdateMapper;
import com.wenjing.dao.TourlineMapper;
import com.wenjing.dao.TourlineattractionMapper;
import com.wenjing.dao.TourlinedestinationMapper;
import com.wenjing.dao.TourlinehotelMapper;
import com.wenjing.dao.TourlineimageMapper;
import com.wenjing.dao.TourlineselfpayMapper;
import com.wenjing.dao.TourpriceMapper;
import com.wenjing.dao.VideoMapper;
import com.wenjing.entity.AirportPickUp;
import com.wenjing.entity.Attraction;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Couponse;
import com.wenjing.entity.Currency;
import com.wenjing.entity.Departure;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Hotel;
import com.wenjing.entity.Image;
import com.wenjing.entity.Itinerary;
import com.wenjing.entity.Navigation;
import com.wenjing.entity.Page;
import com.wenjing.entity.Product;
import com.wenjing.entity.Producttag;
import com.wenjing.entity.Productvideo;
import com.wenjing.entity.Promotion;
import com.wenjing.entity.Region;
import com.wenjing.entity.Review;
import com.wenjing.entity.Selfpay;
import com.wenjing.entity.ServiceItem;
import com.wenjing.entity.ServiceItemProduct;
import com.wenjing.entity.Tag;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tourlineattraction;
import com.wenjing.entity.Tourlinedestination;
import com.wenjing.entity.Tourlinehotel;
import com.wenjing.entity.Tourlineimage;
import com.wenjing.entity.Tourlineselfpay;
import com.wenjing.entity.Tourprice;
import com.wenjing.entity.Video;
import com.wenjing.util.FileUtil;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 说明 后台产品分类管理
 * 
 * @author sevens
 * 
 */
@Service
public class TourlineService {

	@Resource
	private TourlineMapper tourlineMapper;
	@Resource
	private PageMapper pageMapper;
	@Resource 
	private PageService pageService;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private TourlineattractionMapper tourlineattractionMapper;
	@Resource
	private TourlinedestinationMapper tourlinedestinationMapper;
	@Resource
	private TourlinehotelMapper tourlinehotelMapper;
	@Resource
	private TourlineselfpayMapper tourlineselfpayMapper;
	@Resource
	private TourlineimageMapper tourlineimageMapper;
	@Resource
	private AirportPickUpMapper airportpickupMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private TourdateMapper tourdateMapper;
	@Resource
	private TourpriceMapper tourpriceMapper;
	@Resource
	private CostMapper costMapper;
	@Resource
	private AirportPickUpMapper airportPickUpMapper;
	@Resource
	private PromotionMapper promotionMapper;
	@Resource
	private PromotionProductMapper promotionproductMapper;
	@Resource
	private DepartureDateMapper departuredateMapper;
    @Resource
    private ItineraryimageMapper itineraryimageMapper;
    @Resource
    private ItineraryMapper itineraryMapper;
	@Resource
	private SliderMapper sliderMapper;
	@Resource
	private NavigationMapper navigationMapper;
	@Resource
	private RegionService regionService;
	@Resource
	private CurrencyMapper currencyMapper;
	@Resource
	private TourDateService tourDateService;
	@Resource
	private AttractionService attractionService;
	@Resource
	private ItineraryService itineraryService;
	@Resource
	private TourLineCalendarService tourLineCalendarService;
	@Resource
	private OrderService orderService;
	@Resource
	private DestinationService destinationService;
	@Resource
	private ReviewService reviewService;
	@Resource
	private OrderdetailMapper orderdetailMapper;
	@Resource
	private ImageMapper imageMapper;
	@Resource
	private ShowTourlineService showtourlineService;
	@Resource
	private IndexShowTourlineService indexshowtourlineService;
	@Resource
    private HotTourlineService hotTourlineService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Resource
	private ProducttagMapper producttagMapper;
	@Resource
	private TagMapper tagMapper;
	
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private ShowtourlineMapper showtourlineMapper;
	@Resource
	private IndexshowtourlineMapper indexshowtourlineMapper;
	@Resource
	private HotTourlineMapper hotTourlineMapper;
	@Resource
	private VideoMapper videoMapper;
	@Resource
	private ProductvideoMapper productvideoMapper;
	@Resource
	private CouponseMapper couponseMapper;
	@Resource
	private ServiceItemMapper serviceItemMapper;
	@Resource
	private ServiceItemProductMapper serviceItemproductMapper;
	@Resource
	private ReviewMapper reviewMapper;
	@Autowired
	private TourlineselfpayMapper tourlineelfpayMapper;
	@Autowired
	private DepartureMapper departureMapper;
	@Autowired
	private DestinationMapper destinationMapper;
	@Autowired
	private FoodMapper foodMapper;
	@Autowired
	private SelfpayMapper selfPayMapper;
	@Autowired
	private AttractionMapper attractionMapper;
	@Autowired
	private HotelMapper hotelMapper;
	
	private  final static int SIZE = 10;		//定义分页显示线路数量
	
	public static final Integer SALE = 1;	//打折
	
	public static final Integer MINUS = 2;	//减价
	
	/**
	 * 查询所有tourline
	 * 
	 * @return sevens
	 */
	@Transactional(readOnly = true)
	public String findAll(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		/***/
		String costid = request.getParameter("costId");
		String pageNow = request.getParameter("TpageNow");
		String search = request.getParameter("Tsearch");
		String pageSize = request.getParameter("pageSize");
		if (costid == null || "".equals(costid)) {
			costid = WebUtils.getCookie(request, "costId");
			if (costid == null) {
				List<String> costnumber = Tools.getCostNumber(request);
				if(costnumber!=null&&costnumber.size()==1){
					costid = costnumber.get(0);
				}
			}
		} else {
			WebUtils.addCookie(request, response, "costId", costid);
			pageNow = "1";
		}
		Integer indexShowInt = null;
		Integer isShowInt = null;
		Integer ishotInt = null;
		//是否首页显示
		String indexShow = request.getParameter("indexShow");
		if(indexShow == null || "".equals(indexShow)){
			indexShow = WebUtils.getCookie(request, "tourIndexShow");
		}else {
			WebUtils.addCookie(request, response, "tourIndexShow", indexShow);
			pageNow = "1";
		}
		if(indexShow !=null && !"".equals(indexShow)){
			indexShowInt = Integer.parseInt(indexShow);
		}
		//是否显示
		String isshow = request.getParameter("isshow");
		if(isshow == null || "".equals(isshow)){
			isshow = WebUtils.getCookie(request, "tourIsshow");
		}else {
			WebUtils.addCookie(request, response, "tourIsshow", isshow);
			pageNow = "1";
		}
		if(isshow !=null && !"".equals(isshow)){
			isShowInt = Integer.parseInt(isshow);
		}
		//是否热推
		String ishot = request.getParameter("ishot");
		if(ishot == null || "".equals(ishot)){
			ishot = WebUtils.getCookie(request, "tourIshot");
		}else {
			WebUtils.addCookie(request, response, "tourIshot", ishot);
			pageNow = "1";
		}
		if(ishot !=null && !"".equals(ishot)){
			ishotInt = Integer.parseInt(ishot);
		}
		
		if(pageSize==null||"".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "TpageSize");
		}else{
			WebUtils.addCookie(request, response, "TpageSize", pageSize);
			pageNow = 1+"";
		}
		if (search == null || "".equals(search)) {
			search = WebUtils.getCookie(request, "Tsearch");
		} else {
			WebUtils.addCookie(request, response, "Tsearch", search);
			pageNow = 1+"";
		}
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "TpageNow");
		} else {
			WebUtils.addCookie(request, response, "TpageNow", pageNow);
		}

		Pages page = null;
		List<Tourline> Alltourline = new ArrayList<Tourline>();
		int totalCount = tourlineMapper.getSTourlineCount(costid, search, indexShowInt, isShowInt, ishotInt);
        if(pageSize==null){
        	pageSize=10+"";
        }
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.parseInt(pageSize));
			Alltourline = this.tourlineMapper.selectAllByPage(
					page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);
		}else{
			page = new Pages(totalCount,1);
			page.setPageSize(Integer.parseInt(pageSize));
			Alltourline = this.tourlineMapper.selectAllByPage(
					page.getStartPos(), page.getPageSize(), costid, search, indexShowInt, isShowInt, ishotInt);	
		} 
		
		for (Tourline tourline : Alltourline) {
			List<Producttag> tags = producttagMapper.findByProductid(tourline.getProductid(), costid);
			List<Productvideo> videos = productvideoMapper.findByProductid(tourline.getProductid(), costid);
			tourline.setProductTagList(tags);
			tourline.setProductVideoList(videos);
		}
		List<Tag> taglist = tagMapper.findAll(costid, 1);
	    model.addAttribute("taglist",taglist);
	    List<Video> videolist = videoMapper.findAll(costid, 1);
	    model.addAttribute("videolist",videolist);
	    List<ServiceItem> serviceItemList = serviceItemMapper.findAll();
	    model.addAttribute("serviceItemList", serviceItemList);
		model.addAttribute("Alltourline", Alltourline);
		model.addAttribute("page", page);
		model.addAttribute("Tsearch", search);
		model.addAttribute("costIdo",costid);
		model.addAttribute("indexShow", indexShowInt);
		model.addAttribute("isshow", isShowInt);
		model.addAttribute("ishot", ishotInt);
		return costid;
	}

	/**
	 * 根据id删除tourline
	 * 
	 * @param id
	 * @return
	 */
	
	@Transactional
	public String delete(String tourlineId,String productId,String pageId) {
		
		String notice ="";
		String filepath = null;
		Page  page = pageService.findById(pageId);
		if(page!=null){
			filepath = page.getFilepath();
		}
		List<Cost> listCost = costMapper.findAll();
		int ops = orderdetailMapper.selectCountByProductId(productId);
		if(ops==0){
			tourpriceMapper.deleteWithProductid(productId);
			departuredateMapper.deleteWithProductid(productId);
			tourdateMapper.deleteWithProductid(productId);
			tourlineattractionMapper.deleteByTourlineId(tourlineId);
			tourlinedestinationMapper.deleteByTourlineId(tourlineId);
			tourlinehotelMapper.deleteByTourlineId(tourlineId);
			airportpickupMapper.deleteAirportBytourlineId(tourlineId);
			itineraryimageMapper.deleteWithTourlineId(tourlineId);
			itineraryMapper.deleteWithTourlineId(tourlineId);
			tourlineimageMapper.deleteByTourlineId(tourlineId);
			tourlineelfpayMapper.deleteByTourlineId(tourlineId);
			tourlineMapper.deleteByPrimaryKey(tourlineId);
			promotionproductMapper.deleteWithProductId(productId);
			producttagMapper.deleteWithProductIdAndCostnumber(productId, null);
			productvideoMapper.deleteWithProductIdAndCostnumber(productId, null);
			serviceItemproductMapper.deleteByProductId(productId);
			productMapper.deleteByPrimaryKey(productId);
			pageService.delete(pageId);
			//删除首页显示
			indexshowtourlineMapper.deleteBycostnumberAnaTourlineId(null, tourlineId);
			//删除显示
			showtourlineMapper.deleteBycostnumberAnaTourlineId(null, tourlineId);
			//删除热推
			hotTourlineMapper.deleteBycostnumberAnaTourlineId(null, tourlineId);
			for (Cost cost : listCost) {
				FileUtil util = new FileUtil();
				if(filepath!=null){
					util.DeleteFolder(request.getSession().getServletContext().getRealPath("/")+cost.getCode()+"/"+filepath);	
				}
			}
			notice = "删除成功！";
			
		}else{
			notice = "该线路产品有订单存在，不能删除！";
		}
		return notice;
	}
	
	/**
	 * 根据id查询tourline
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Tourline findById(String id) {
		return tourlineMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存tourline
	 * 
	 * @param tourline
	 * @return
	 */
	@Transactional
	public int save(Tourline tourline, Page page, Product product,String costlistst) {
		page.setId(Tools.getUUID());
		page.setCostnumber(costlistst);
		pageMapper.insert(page);
		product.setId(Tools.getUUID());
		product.setCostnumber(costlistst);
		product.setCostnumberids(costlistst);
		product.setPageid(page.getId());
		product.setIsSynchronizedToERP(0);
		product.setExcludeLandPrice(0);
		productMapper.insert(product);
		if(product.getIshot()==1){
			hotTourlineService.save(costlistst, tourline.getId());	
		}
		if(product.getIsshow()==1){
			showtourlineService.save(costlistst, tourline.getId());	
		}
		if(product.getIndexShow()==1){
			indexshowtourlineService.save(costlistst, tourline.getId());	
		}
		tourline.setProductid(product.getId());
		tourline.setCostnumber(costlistst);
		tourline.setCostnumberids(costlistst);
		return tourlineMapper.insert(tourline);
	}

	/**
	 * 保存线路产品关系
	 * 
	 * @author Sevens
	 * @return
	 */
	@Transactional
	public void saveregest(Tourline tourline, String[] imageid, String[] dests,
			String[] self, String[] attrs, String[] hotels,String[] include,String[] noinclude, int controll) {
		if (controll == 2) {
			tourlinedestinationMapper.deleteByTourlineId(tourline.getId());
			tourlineattractionMapper.deleteByTourlineId(tourline.getId());
			tourlinehotelMapper.deleteByTourlineId(tourline.getId());
			tourlineselfpayMapper.deleteByTourlineId(tourline.getId());
			serviceItemproductMapper.deleteByProductId(tourline.getProductid());
			//tourlineimageMapper.deleteByTourlineId(tourline.getId());
		}
		if (imageid != null && imageid.length > 0) {
			for (String string : imageid) {
				if (!string.equals("")) {
					Tourlineimage tourlineimage = new Tourlineimage();
					tourlineimage.setId(Tools.getUUID());
					tourlineimage.setTourlineid(tourline.getId());
					tourlineimage.setImageid(string);
					tourlineimageMapper.insert(tourlineimage);
				}
			}
		}
		int stemp = 0;
		if (dests != null && dests.length > 0) {
			for (String string : dests) {
				stemp++;
				Tourlinedestination tourlinede = new Tourlinedestination();
				tourlinede.setId(Tools.getUUID());
				tourlinede.setTourlineid(tourline.getId());
				tourlinede.setDestinationid(string);
				tourlinede.setSort(stemp);
				tourlinedestinationMapper.insert(tourlinede);
			}
		}
		if (self != null && self.length > 0) {
			for (String string : self) {
				Tourlineselfpay selfpay = new Tourlineselfpay();
				selfpay.setId(Tools.getUUID());
				selfpay.setTourlineid(tourline.getId());
				selfpay.setSelfpayid(string);
				tourlineselfpayMapper.insert(selfpay);
			}
		}
		if (attrs != null && attrs.length > 0) {
			for (String string : attrs) {
				System.out.println(string);
				Tourlineattraction tourlineattr = new Tourlineattraction();
				tourlineattr.setId(Tools.getUUID());
				tourlineattr.setTourlineid(tourline.getId());
				tourlineattr.setAttractionid(string);
				tourlineattractionMapper.insert(tourlineattr);
			}
		}
		if (hotels != null && hotels.length > 0) {
			for (String string : hotels) {
				Tourlinehotel tourlineh = new Tourlinehotel();
				tourlineh.setId(Tools.getUUID());
				tourlineh.setTourlineid(tourline.getId());
				tourlineh.setHotelid(string);
				tourlinehotelMapper.insert(tourlineh);
			}
		}
		List<ServiceItemProduct> serviceItemP = new ArrayList<ServiceItemProduct>();
		if(include!=null && include.length > 0){
			for (String string : include) {
				ServiceItemProduct serviceItp = new ServiceItemProduct();
				serviceItp.setId(Tools.getUUID());
				serviceItp.setProductId(tourline.getProductid());
				serviceItp.setServiceItemId(string);
				serviceItp.setType(1);
				serviceItemP.add(serviceItp);
			}
			serviceItemproductMapper.batchInsert(serviceItemP);
		}
		List<ServiceItemProduct> serviceItemB = new ArrayList<ServiceItemProduct>();
		if(noinclude!=null && noinclude.length > 0){
			for (String string : noinclude) {
				ServiceItemProduct serviceItp = new ServiceItemProduct();
				serviceItp.setId(Tools.getUUID());
				serviceItp.setProductId(tourline.getProductid());
				serviceItp.setServiceItemId(string);
				serviceItp.setType(0);
				serviceItemB.add(serviceItp);
			}
			serviceItemproductMapper.batchInsert(serviceItemB);
		}
	}
	
	/**
	 * 根据线路和运营中心查询促销活动
	 * 
	 * @param tourlineId
	 * @param costNumber
	 * @return
	 */
	public Promotion getPromotion(String tourlineId,String costNumber){
		int timeNow = Tools.getDtimestemp(Tools.getDtime());
		List<Promotion> promotionList = promotionMapper.findBytourlineId(tourlineId, costNumber, timeNow);
		if(promotionList.size() != 0){
			for(Promotion promotion : promotionList){
				return promotion;
			}
		}
		return null;
	}
	
	/**
	 * 复制保存线路产品关系
	 * 
	 * @author Sevens
	 * @return
	 */
	@Transactional
	public void Copysaveregest(Tourline tourline,String tuorlineId, int controll) {
		if (controll == 2) {
			tourlinedestinationMapper.deleteByTourlineId(tourline.getId());
			tourlineattractionMapper.deleteByTourlineId(tourline.getId());
			tourlinehotelMapper.deleteByTourlineId(tourline.getId());
			tourlineselfpayMapper.deleteByTourlineId(tourline.getId());
			serviceItemproductMapper.deleteByProductId(tourline.getProductid());
			//tourlineimageMapper.deleteByTourlineId(tourline.getId());
		}
		List<Tourlineimage> tourlineimage = tourlineimageMapper.selectByTourlineid(tuorlineId);
		if (tourlineimage != null && tourlineimage.size()>0) {
			for (Tourlineimage tourlineimage2 : tourlineimage) {
				tourlineimage2.setId(Tools.getUUID());
				tourlineimage2.setTourlineid(tourline.getId());
				tourlineimageMapper.insert(tourlineimage2);
			}
			
		}
		int stemp = 0;
		List<Tourlinedestination> tourlinede =  tourlinedestinationMapper.selectByTourlineid(tuorlineId);
		if (tourlinede != null && tourlinede.size() > 0) {
			for (Tourlinedestination tourlinedestination : tourlinede) {
				stemp++;
				tourlinedestination.setId(Tools.getUUID());
				tourlinedestination.setTourlineid(tourline.getId());
				tourlinedestination.setSort(stemp);
				tourlinedestinationMapper.insert(tourlinedestination);
			}
		}
		List<Tourlineselfpay> selfpay = tourlineselfpayMapper.selectByTourlineid(tuorlineId);
		if (selfpay != null && selfpay.size()> 0) {
			for (Tourlineselfpay tourlineselfpay : selfpay) {
				tourlineselfpay.setId(Tools.getUUID());
				tourlineselfpay.setTourlineid(tourline.getId());
				tourlineselfpayMapper.insert(tourlineselfpay);
			}
		}
		List<Tourlineattraction> tourlineattr = tourlineattractionMapper.selectByTourlineid(tuorlineId);
		if (tourlineattr != null && tourlineattr.size() > 0) {
			for (Tourlineattraction tourlineattraction : tourlineattr) {
				tourlineattraction.setId(Tools.getUUID());
				tourlineattraction.setTourlineid(tourline.getId());
				tourlineattractionMapper.insert(tourlineattraction);
			}
		}
		List<Tourlinehotel> tourlineh = tourlinehotelMapper.selectByTourlineid(tuorlineId);
		if (tourlineh != null && tourlineh.size() > 0) {
			for (Tourlinehotel tourlinehotel : tourlineh) {
				tourlinehotel.setId(Tools.getUUID());
				tourlinehotel.setTourlineid(tourline.getId());
				tourlinehotelMapper.insert(tourlinehotel);
			}
		}
		
		List<ServiceItemProduct> serviceItemP = new ArrayList<ServiceItemProduct>();
		if(tourline.getInclude()!=null && tourline.getInclude().length()>0){
			String [] include = tourline.getInclude().split(",");
			for (String string : include) {
				ServiceItemProduct serviceItp = new ServiceItemProduct();
				serviceItp.setId(Tools.getUUID());
				serviceItp.setProductId(tourline.getProductid());
				serviceItp.setServiceItemId(string);
				serviceItp.setType(1);
				serviceItemP.add(serviceItp);
			}
			serviceItemproductMapper.batchInsert(serviceItemP);
		}
		List<ServiceItemProduct> serviceItemB = new ArrayList<ServiceItemProduct>();
		if(tourline.getExclude()!=null && tourline.getExclude().length()>0){
			String [] noinclude = tourline.getExclude().split(",");
			for (String string : noinclude) {
				ServiceItemProduct serviceItp = new ServiceItemProduct();
				serviceItp.setId(Tools.getUUID());
				serviceItp.setProductId(tourline.getProductid());
				serviceItp.setServiceItemId(string);
				serviceItp.setType(0);
				serviceItemB.add(serviceItp);
			}
			serviceItemproductMapper.batchInsert(serviceItemB);
		}
		List<Itinerary> itinerarys = itineraryMapper.findByTourlineId(tuorlineId);
		if(itinerarys!=null&&itinerarys.size()>0){
			for (Itinerary itinerary : itinerarys) {
				itinerary.setId(Tools.getUUID());
				itinerary.setTourlineid(tourline.getId());
				itineraryMapper.insert(itinerary);
			}
			
		}
	}


	/**
	 * 修改tourline
	 * 
	 * @param tourline
	 */
	@Transactional
	public int update(Tourline tourline, Page page, Product product,String costlistst) {
		page.setId(product.getPageid());
		pageMapper.updateByPrimaryKeySelective(page);
		product.setId(tourline.getProductid());
		product.setIsSynchronizedToERP(0);
		product.setCostnumberids(product.getCostnumber());
		productMapper.updateByPrimaryKeySelective(product);
		hotTourlineService.delete(costlistst, tourline.getId());
		showtourlineService.delete(costlistst, tourline.getId());
		indexshowtourlineService.save(costlistst, tourline.getId());
		if(costlistst!=null&&!"".equals(costlistst)&&product.getIshot()==1){
			hotTourlineService.save(costlistst, tourline.getId());	
		}
		if(costlistst!=null&&!"".equals(costlistst)&&product.getIsshow()==1){
			showtourlineService.save(costlistst, tourline.getId());	
		}
		if(costlistst!=null&&!"".equals(costlistst)&&product.getIndexShow()==1){
			indexshowtourlineService.save(costlistst, tourline.getId());	
		}
		tourline.setCostnumberids(tourline.getCostnumber());
		return tourlineMapper.updateByPrimaryKeySelective(tourline);
	}

	/**
	 * 查询最大sort
	 * 
	 * @return 
	 */
	@Transactional
	public int getOrderId() {
		return tourlineMapper.getMaxSort();
	}

	/**
	 * @Title: findbyHotShow
	 * @Description: 根据ishot 和indexShow查询线路
	 * @param ishot
	 * @param indexshow
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<Tourline> findbyHotIndexShow(Integer ishot, Integer indexshow,
			String costnumber, Integer isshow) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.findbyHotIndexShow(ishot, indexshow, costnumber,
				isshow,time);
	}
	
	/**
	 * 根据产品查询出线路
	 * 
	 * @param productId
	 * @return
	 */
	@Transactional
	public Tourline findByProductId(String productId){
		return tourlineMapper.findByProductId(productId);
	}

	/**
	 * @Title: findbyHotShow
	 * @Description: 根据ishot 和indexShow查询线路
	 * @param ishot
	 * @param indexshow
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<Tourline> findRandomByHotIndexShow(Integer ishot, Integer indexshow, String costnumber, Integer isshow) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.findRandomByHotIndexShow(ishot, indexshow, costnumber, isshow,time);
	}

	/**
	 * @Title: getfilePath
	 * @Description: 获得线路的页面路径
	 * @param id
	 * @return
	 * @return String 返回类型
	 * @author xiejin
	 */
	@Transactional
	public String getfilePath(String id) {
		return productMapper.getfilePath(id);
	}

	/**
	 * @Title: findbyHotShowRegionid
	 * @Description: 根据ishot ,isShow,regionid查询线路
	 * @param regionid
	 * @param ishot
	 * @param isshow
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional
	public List<Tourline> findbyHotShowRegionid(String regionid, Integer ishot,
			Integer isshow, String costnumber) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.findbyHotShowRegionid(regionid, ishot, isshow,
				costnumber,time);
	}

	/**
	 * @Title: findByShowRegionid
	 * @Description: 刚进入二级页面，线路分页查询，不是异步
	 * @param request
	 * @param regionid
	 * @param isshow
	 * @param costnumber
	 * @param model
	 * @return void 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public void findByShowRegionid(HttpServletRequest request,Model model,
			String costnumber,String destination,BigDecimal minPrice, BigDecimal maxPrice, String sort,
			String keyword,String rate,String dateFrom,String dateTo,String regionId,Integer size,
			String promotionId) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cost cost = costMapper.selectByPrimaryKey(costnumber); // 线路对应的销售中心
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Tourline> tourlinelist = new ArrayList<Tourline>();
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString); 		// 当前系统时间戳
		int dateFromIn	= 0;
		int dateToIn	= 0;
		if (dateFrom != null && !"".equals(dateFrom)) {
			dateFromIn = Tools.getTimestemp(dateFrom);	
		}
		if (dateTo != null && !"".equals(dateTo)) {
			dateToIn = Tools.getTimestemp(dateTo);
		}
		int totalCount = tourlineMapper.getTourlineCountByCondition(costnumber, destination,minPrice,
				maxPrice, keyword,rate,time,dateFromIn,dateToIn,regionId,promotionId);
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Pages(totalCount, 1);
		}
		page.setPageSize(size);
		int startPos = page.getStartPos();
		int pageSize = page.getPageSize();
		tourlinelist = this.tourlineMapper.getTourlineByCondition(costnumber, startPos, pageSize,
				destination,minPrice, maxPrice, sort, keyword, time,rate,dateFromIn,dateToIn,regionId,promotionId);
		for (Tourline tourline2 : tourlinelist) {
			String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
			if (imageurl != null && !imageurl.equals("")) {
				String[] url = (imageurl + ",").split(",");
				tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
			} else {
				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
			}
			List<ServiceItem> serviceItemList = serviceItemMapper.findBytourlineinclude(tourline2.getInclude());
			tourline2.setServiceItemList(serviceItemList);
			//线路日期
//			List<Tourdate> tourdatelist = tourdateMapper
//					.getDateAndPriceByTourlineid(tourline2.getId(), costnumber);
//			for (Tourdate tourdatelist2 : tourdatelist) {
//				int startdate = tourdatelist2.getStartdate();
//				tourdatelist2.setStartdatestr(Tools.getTime(startdate));
//				int enddatestr = tourdatelist2.getEnddate();
//				tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
//			}
//			tourline2.setTourdatesTourlineid(tourdatelist);
			tourline2.setCost(cost);
			BigDecimal lowsprice = tourline2.getLowsprice();
			//判断是否参与促销活动，并修改最低价格		
			BigDecimal discountPrice = changeSellprice(lowsprice, tourline2.getId(), costnumber);	//折后价格，若美折扣，为原价
			tourline2.setDiscountPrice(discountPrice);
			List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
			tourline2.setPromotionList(promotionList);
			//查询相关标签
//			List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
//			tourline2.setTagList(tagList);
			//查询线路评价数量
			int reviewCount = reviewMapper.getReviewCount(tourline2.getProductid(), costnumber);
			tourline2.setReviewCount(reviewCount);
			String highlights = tourline2.getHighlights();
			if (highlights!=null) {
				highlights = highlights.replace("\r\n", "^^");		//线路亮点^^替换回车
				tourline2.setHighlights(highlights);
			}
			
		}
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("now", Tools.getTime());
		model.addAttribute("tourlinelist", tourlinelist);
		model.addAttribute("page", page);
	}

	/**
	 * @Title: findByShowRegionid
	 * @Description: 二级页面线路分页查询
	 * @param request
	 * @param regionid
	 * @param isshow
	 * @param costnumber
	 * @param model
	 * @return void 返回类型
	 * @author sevens
	 */
	public void findByShowRegionidCreate(HttpServletRequest request,
			String costnumber,String destination,BigDecimal minPrice, BigDecimal maxPrice, String sort,
			String keyword,String rate,String dateFrom,String dateTo,String regionId,Integer size,
			String promotionId,Map root) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cost cost = costMapper.selectByPrimaryKey(costnumber); // 线路对应的销售中心
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Tourline> tourlinelist = new ArrayList<Tourline>();
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString); 		// 当前系统时间戳
		int dateFromIn	= 0;
		int dateToIn	= 0;
		if (dateFrom != null && !"".equals(dateFrom)) {
			dateFromIn = Tools.getTimestemp(dateFrom);	
		}
		if (dateTo != null && !"".equals(dateTo)) {
			dateToIn = Tools.getTimestemp(dateTo);
		}
		int totalCount = tourlineMapper.getTourlineCountByCondition(costnumber, destination,minPrice,
				maxPrice, keyword,rate,time,dateFromIn,dateToIn,regionId,promotionId);
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Pages(totalCount, 1);
		}
		page.setPageSize(size);
		int startPos = page.getStartPos();
		int pageSize = page.getPageSize();
		tourlinelist = this.tourlineMapper.getTourlineByCondition(costnumber, startPos, pageSize,
				destination,minPrice, maxPrice, sort, keyword, time,rate,dateFromIn,dateToIn,regionId,promotionId);
		for (Tourline tourline2 : tourlinelist) {
			String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
			if (imageurl != null && !imageurl.equals("")) {
				String[] url = (imageurl + ",").split(",");
				tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
			} else {
				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
			}
			List<ServiceItem> serviceItemList = serviceItemMapper.findBytourlineinclude(tourline2.getInclude());
			tourline2.setServiceItemList(serviceItemList);
			//线路日期
//			List<Tourdate> tourdatelist = tourdateMapper
//					.getDateAndPriceByTourlineid(tourline2.getId(), costnumber);
//			for (Tourdate tourdatelist2 : tourdatelist) {
//				int startdate = tourdatelist2.getStartdate();
//				tourdatelist2.setStartdatestr(Tools.getTime(startdate));
//				int enddatestr = tourdatelist2.getEnddate();
//				tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
//			}
//			tourline2.setTourdatesTourlineid(tourdatelist);
			tourline2.setCost(cost);
			BigDecimal lowsprice = tourline2.getLowsprice();
			//判断是否参与促销活动，并修改最低价格		
			BigDecimal discountPrice = changeSellprice(lowsprice, tourline2.getId(), costnumber);	//折后价格，若美折扣，为原价
			tourline2.setDiscountPrice(discountPrice);
			List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
			tourline2.setPromotionList(promotionList);
			//查询相关标签
//			List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
//			tourline2.setTagList(tagList);
			//查询线路评价数量
			int reviewCount = reviewMapper.getReviewCount(tourline2.getProductid(), costnumber);
			tourline2.setReviewCount(reviewCount);
			String highlights = tourline2.getHighlights();
			if (highlights!=null) {
				highlights = highlights.replace("\r\n", "^^");		//线路亮点^^替换回车
				tourline2.setHighlights(highlights);
			}
			
		}
		root.put("totalCount", totalCount);
		root.put("now", Tools.getTime());
		root.put("tourlinelist", tourlinelist);
		root.put("page", page);

	}

	/**
	 * @Title: findByShowRegionid2
	 * @Description: 查询二级页面展示线路，不带分页
	 * @param regionid
	 * @param isshow
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Tourline> findByShowRegionid2(String regionid,Integer isshow,String costnumber) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.findByShowRegionid2(regionid, isshow, costnumber,time);
	}
	
    /**
     * @Title: findByShowRegionid
     * @Description: 根据分类读取线路
     * @param regionid
     * @param isshow
     * @param costnumber
     * @return    
     * @return List<Tourline>    返回类型
     * @author xiejin
     */
	@Transactional(readOnly =true)
    public List<Tourline> findByRegionid(String regionid, String costnumber){
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.findByRegionid(regionid, costnumber, time);
    };

	/**
	 * @Title: findByShowRegionidattra2
	 * @Description: 根据景点查询线路
	 * @param attraction
	 * @param regionid
	 * @param isshow
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Tourline> findByShowRegionidattra2(String attrhotname,
			String regionid, Integer isshow, String costnumber,
			Integer indexshow) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.findByShowRegionidattra2(attrhotname, regionid,
				isshow, costnumber, indexshow,time);

	}

	/**
	 * @Title: findByCondition
	 * @Description: 根据条件查询线路二级页面展示,异步
	 * @param request
	 * @param regionid
	 * @param isshow
	 * @param costnumber
	 * @param model
	 * @param startCity
	 * @param attr
	 * @param minDay
	 * @param maxDay
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 * @return Map<String,Object> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findByCondition(HttpServletRequest request,
			String regionid, Integer isshow, String costnumber, Model model,
			String startCity, String[] attr, Integer minDay, Integer maxDay,
			BigDecimal minPrice, BigDecimal maxPrice, String sort,
			String keyword,String[] tag,int size) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cost cost = costMapper.selectByPrimaryKey(costnumber); // 线路对应的销售中心
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Tourline> tourlinelist = new ArrayList<Tourline>();
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString); // 当前系统时间戳
		int totalCount = tourlineMapper.getCountByCondition(regionid, isshow,
				costnumber, startCity, attr, minDay, maxDay, minPrice,
				maxPrice, keyword, time,tag);
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Pages(totalCount, 1);
		}
		page.setPageSize(size);
		int startPos = page.getStartPos();
		int pageSize = page.getPageSize();
		tourlinelist = this.tourlineMapper.findByCondition(regionid,
				isshow, costnumber, startPos, pageSize, startCity, attr,
				minDay, maxDay, minPrice, maxPrice, sort, keyword, time,tag);
		for (Tourline tourline2 : tourlinelist) {
			String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
			if (imageurl != null && !imageurl.equals("")) {
				String[] url = (imageurl + ",").split(",");
				tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
			} else {
				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
			}
			List<Tourdate> tourdatelist = tourdateMapper
					.getDateAndPriceByProductid(tourline2.getProductid(), costnumber);
			for (Tourdate tourdatelist2 : tourdatelist) {
				int startdate = tourdatelist2.getStartdate();
				tourdatelist2.setStartdatestr(Tools.getTime(startdate));
				int enddatestr = tourdatelist2.getEnddate();
				tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
			}
			tourline2.setTourdatesTourlineid(tourdatelist);
			tourline2.setCost(cost);
			BigDecimal lowsprice = tourline2.getLowsprice();
			//判断是否参与促销活动，并修改最低价格		
			lowsprice = changeSellprice(lowsprice, tourline2.getId(), costnumber);
			tourline2.setLowsprice(lowsprice);
			List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
			tourline2.setPromotionList(promotionList);
			//查询相关标签
			List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
			tourline2.setTagList(tagList);
		}
		String upPageContent = FreemarkerUtils.getUpPageContent(request
				.getSession().getServletContext(), page);
		String downPageContent = FreemarkerUtils.getDownPageContent(request
				.getSession().getServletContext(), page);
		String tourContent = FreemarkerUtils.getTourlineListContent(request
				.getSession().getServletContext(), tourlinelist, request);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("tourContent", tourContent);
		root.put("upPageContent", upPageContent);
		root.put("downPageContent", downPageContent);
		return root;
	}

	/**
	 * @Title: searchTourline
	 * @Description: 根据关键字分页搜索线路,不是异步
	 * @param request
	 * @param keyword
	 * @param isshow
	 * @param costnumber
	 * @param model
	 * @return void 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public void searchTourline(HttpServletRequest request, String keyword,
			Integer isshow, String costnumber, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		Cost cost = costMapper.selectByPrimaryKey(costnumber); // 线路对应的销售中心
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Tourline> tourlinelist = new ArrayList<Tourline>();
		int totalCount = tourlineMapper.searchTourlineCount(keyword, isshow,
				costnumber,time);
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Pages(totalCount, 1);
		}
		page.setPageSize(SIZE);
		int startPos = page.getStartPos();
		int pageSize = page.getPageSize();
		tourlinelist = this.tourlineMapper.searchTourline(keyword, isshow,
				costnumber, startPos, pageSize,time);
		for (Tourline tourline2 : tourlinelist) {
			String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
			if (imageurl != null && !imageurl.equals("")) {
				String[] url = (imageurl + ",").split(",");
				tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
			} else {
				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
			}
			List<Tourdate> tourdatelist = tourdateMapper
					.getDateAndPriceByProductid(tourline2.getProductid(), costnumber);
			for (Tourdate tourdatelist2 : tourdatelist) {
				int startdate = tourdatelist2.getStartdate();
				tourdatelist2.setStartdatestr(Tools.getTime(startdate));
				int enddatestr = tourdatelist2.getEnddate();
				tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
			}
			tourline2.setTourdatesTourlineid(tourdatelist);
			tourline2.setCost(cost);
			BigDecimal lowsprice = tourpriceMapper.getminsellPrice(
					tourline2.getId(), costnumber, time); // 产品最低售价
			//判断是否参与促销活动，并修改最低价格		
			lowsprice = changeSellprice(lowsprice, tourline2.getId(), costnumber);
			tourline2.setLowsprice(lowsprice);
			List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
			tourline2.setPromotionList(promotionList);
			//查询相关标签
			List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
			tourline2.setTagList(tagList);
		}
		model.addAttribute("tourlinelist", tourlinelist);
		model.addAttribute("page", page);
	}

	/**
	 * @Title: searchTourline2
	 * @Description: 根据关键字查询线路，不带分页
	 * @param keyword
	 * @param isshow
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Tourline> searchTourline2(@Param("keyword") String keyword,
			@Param("isshow") Integer isshow,
			@Param("costnumber") String costnumber) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.searchTourline2(keyword, isshow, costnumber,time);
	}

	/**
	 * @Title: searchTourlineHot
	 * @Description: 查询四条热卖线路
	 * @param ishot
	 * @param isshow
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Tourline> searchTourlineHot(Integer ishot, Integer isshow,
			String costnumber) {
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		return tourlineMapper.searchTourlineHot(ishot, isshow, costnumber,time);
	}

	/**
	 * @Title: findByIdIsshow
	 * @Description: 根据id查询三级页面上显示的线路详情
	 * @param id
	 * @return
	 * @return Tourline 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public Tourline findByIdIsshow(String id) {
		return tourlineMapper.findByIdIsshow(id);
	}

	/**
	 * @Title: findSaleTourline
	 * @Description: 查询首页特卖线路
	 * @param costnumber
	 * @return
	 * @return List<Tourline> 返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public List<Tourline> findSaleTourline(String costnumber) {
		int timeNow = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
		return tourlineMapper.findSaleTourline(costnumber,timeNow);
	}

	/**
	 * 接送机保存
	 */
	@Transactional
	public void saveAirportPickUp(HttpServletRequest request, String tourlineid) {
		String[] titleArr = request.getParameterValues("airportTitle");
		String[] priceArr = request.getParameterValues("airportPrice");
		if (titleArr != null && priceArr != null) {
			List<AirportPickUp> aiList = new ArrayList<AirportPickUp>();
			for (int i = 0; i < priceArr.length; i++) {
				AirportPickUp airportPickUp = new AirportPickUp(
						Tools.getUUID(), titleArr[i], new BigDecimal(
								priceArr[i].trim()), tourlineid);
				aiList.add(airportPickUp);
			}
			airportPickUpMapper.batchAdd(aiList);
		}
	}

	/**
	 * 根据tourlineid查询接送机
	 * 
	 * @param tourlineId
	 * @return
	 */
	@Transactional
	public List<AirportPickUp> findAllApickUpsBytourId(String tourlineId) {
		return airportPickUpMapper.findAllApickUpsBytourId(tourlineId);
	}

	/**
	 * 修改接送机
	 * 
	 * @param request2
	 * @param id
	 */
	@Transactional
	public void updateAirportPickUp(HttpServletRequest request,
			String tourlineid) {
		airportPickUpMapper.deleteAirportBytourlineId(tourlineid);// 删除tourlineid对应下得airport
		String[] titleArr = request.getParameterValues("airportTitle");
		String[] priceArr = request.getParameterValues("airportPrice");
		if (titleArr != null && priceArr != null) {
			List<AirportPickUp> aiList = new ArrayList<AirportPickUp>();
			for (int i = 0; i < priceArr.length; i++) {
				AirportPickUp airportPickUp = new AirportPickUp(
						Tools.getUUID(), titleArr[i], new BigDecimal(
								priceArr[i].trim()), tourlineid);
				aiList.add(airportPickUp);
			}
			airportPickUpMapper.batchAdd(aiList);
		}
	}
	
	/**
	 * @Title: findByUrlIsshow
	 * @Description: 根据url查找线路
	 * @param url
	 * @return    
	 * @return Tourline    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public Tourline findByUrlIsshow(String url) {
		return tourlineMapper.findByUrlIsshow(url);
	}
	
	/**
	 * @Title: changeSellprice
	 * @Description: 判断是否有促销活动，如果有修改最低价格
	 * @param price
	 * @param id
	 * @param costnumber
	 * @return    
	 * @return BigDecimal    返回类型
	 * @author xiejin
	 */
	public Tourprice changeTourPrice(Tourprice tourprice,String tourlineId,String costnumber){
		if (tourprice != null) {
			int timeNow = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
			List<Promotion> promotionList = promotionMapper.findBytourlineId(tourlineId, costnumber, timeNow);
			if (promotionList.size()!=0) {
				for (Promotion promotion : promotionList) {
//					if (promotion.getCostnumber().equals(costnumber) && timeNow >=promotion.getStarttime() 
//							&& timeNow<=promotion.getEndtime()) {
//					}
					tourprice = changeTourpriceByPromotion(tourprice,promotion);
					break;
				}
			}
		}
		return tourprice;
	}
	
	/**
	 * 根据促销活动变动价格
	 * 
	 * @return
	 */
	private Tourprice changeTourpriceByPromotion(Tourprice tourprice,Promotion promotion){
		Integer type = promotion.getType();
		
		BigDecimal discount = promotion.getDiscount();
		BigDecimal sellingprice = tourprice.getSellingprice();
		BigDecimal threeprice = tourprice.getThreesellingprice();
		BigDecimal fourprice = tourprice.getFoursellingprice();
		BigDecimal singleroomprice = tourprice.getSingleroomprice();
		BigDecimal extraBedPrice = tourprice.getExtrabedprice();
		BigDecimal noBedPrice = tourprice.getNobedprice();
		BigDecimal babyPrice = tourprice.getBabyPrice();
		BigDecimal childPrice = tourprice.getChildPrice();
		
		if (type != null && SALE.equals(type)) {//判断是否参加打折活动
			if (discount!=null && !"".equals(discount)) {
				BigDecimal discountBig = discount;
				
				BigDecimal discountSellingPrice = sellingprice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				BigDecimal discountThreePrice = threeprice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				BigDecimal discountFourPrice = fourprice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				BigDecimal discountSingleRoomPrice = singleroomprice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				BigDecimal discountExtraBedPrice = extraBedPrice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				BigDecimal discountNoBedPrice = noBedPrice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				BigDecimal discountBabyPrice = babyPrice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				BigDecimal discountChildPrice = childPrice.multiply(discountBig).setScale(0, BigDecimal.ROUND_UP);
				
				tourprice.setSellingprice(discountSellingPrice);
				tourprice.setThreesellingprice(discountThreePrice);
				tourprice.setFoursellingprice(discountFourPrice);
				tourprice.setSingleroomprice(discountSingleRoomPrice);
				tourprice.setExtrabedprice(discountExtraBedPrice);
				tourprice.setNobedprice(discountNoBedPrice);
				tourprice.setBabyPrice(discountBabyPrice);
				tourprice.setChildPrice(discountChildPrice);
			}
		}
		if (type != null && MINUS.equals(type)) {//判断是否参与减价活动 
			Integer reduce = promotion.getReduce();
			BigDecimal reducedSellingPrice; 
			BigDecimal reducedThreePrice;
			BigDecimal reducedFourPrice;
			BigDecimal reducedExtraBedPrice;
			BigDecimal reducedNoBedPrice;
			BigDecimal reducedBabyPrice;
			BigDecimal reducedChildPrice;
			
			if (reduce != null) {
				BigDecimal reduceBig = new BigDecimal(reduce);
				
				reducedSellingPrice = sellingprice.subtract(reduceBig);
				
				if(threeprice != null && threeprice.intValue() != 0 && threeprice.compareTo(reduceBig) != -1){
					reducedThreePrice = threeprice.subtract(reduceBig);
				}else{
					reducedThreePrice = threeprice;
				}
				
				if(fourprice != null && fourprice.intValue() != 0 && fourprice.compareTo(reduceBig) != -1){
					reducedFourPrice = fourprice.subtract(reduceBig);
				}else{
					reducedFourPrice = fourprice;
				}
				
				if(extraBedPrice != null && extraBedPrice.intValue() != 0 && extraBedPrice.compareTo(reduceBig) != -1){
					reducedExtraBedPrice = extraBedPrice.subtract(reduceBig);
				}else{
					reducedExtraBedPrice = extraBedPrice;
				}
				
				if(noBedPrice != null && noBedPrice.intValue() != 0 && noBedPrice.compareTo(reduceBig) != -1){
					reducedNoBedPrice = noBedPrice.subtract(reduceBig);
				}else{
					reducedNoBedPrice = noBedPrice;
				}
				
				if(babyPrice != null && babyPrice.intValue() != 0 && babyPrice.compareTo(reduceBig) != -1){
					reducedBabyPrice = babyPrice.subtract(reduceBig);
				}else{
					reducedBabyPrice = babyPrice;
				}
				
				if(childPrice != null && childPrice.intValue() != 0 && childPrice.compareTo(reduceBig) != -1){
					reducedChildPrice = childPrice.subtract(reduceBig);
				}else{
					reducedChildPrice = childPrice;
				}	
				
				tourprice.setSellingprice(reducedSellingPrice);
				tourprice.setThreesellingprice(reducedThreePrice);
				tourprice.setFoursellingprice(reducedFourPrice);
				tourprice.setExtrabedprice(reducedExtraBedPrice);
				tourprice.setNobedprice(reducedNoBedPrice);
				tourprice.setBabyPrice(reducedBabyPrice);
				tourprice.setChildPrice(reducedChildPrice);
			}
		}
		return tourprice;
	}
	
	/**
	 * @Title: changeSellprice
	 * @Description: 判断是否有促销活动，如果有修改最低价格
	 * @param price
	 * @param id
	 * @param costnumber
	 * @return    
	 * @return BigDecimal    返回类型
	 * @author xiejin
	 */
	public BigDecimal changeSellprice(BigDecimal price,String tourlineId,String costnumber){
		if (price!=null) {
			int timeNow = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
			List<Promotion> promotionList = promotionMapper.findBytourlineId(tourlineId, costnumber, timeNow);
			if (promotionList.size()!=0) {
				for (Promotion promotion : promotionList) {
//					if (promotion.getCostnumber().equals(costnumber) && timeNow >=promotion.getStarttime() 
//							&& timeNow<=promotion.getEndtime()) {
//					}
					Integer type = promotion.getType();
					if (type != null && SALE.equals(type)) {//判断是否参加打折活动
						BigDecimal discount = promotion.getDiscount();
						if (discount!=null && !"".equals(discount)) {
							double discountD = discount.doubleValue();
							double priceD = price.doubleValue();
							int price2 = (int)(priceD*discountD);	//打折后价格
							price = new BigDecimal(price2);
						}
					}
					if (type != null && MINUS.equals(type)) {//判断是否参与减价活动
						Integer reduce = promotion.getReduce();
						if (reduce != null) {
							price = new BigDecimal(price.intValue()-reduce);
						}
					}
					break;
				}
			}
		}
	return price;
	}
	
	/**
	 * @Title: toulineDetails
	 * @Description: 线路详情后台预览
	 * @param model
	 * @param id    
	 * @return void 返回类型
	 */
	@Transactional(readOnly=true)
	public Map<String,Object> getToulineDetails(Tourline tourline) {
	    Map<String,Object> paramMap = new HashMap<String,Object>();
	    
	    Cost cost = costMapper.selectByPrimaryKey(tourline.getCostnumber());
	    tourline.setCost(cost);
	    
	    //获得线路出发地
	    Product product = productMapper.findById(tourline.getProductid());
	    if(!StringUtils.isEmpty(product.getDepartureIds())){
		    List<Departure> departureList = departureMapper.findByDepartureIds(product.getDepartureIds());
		    paramMap.put("departureList", departureList);
	    }
	    
		String id = "";
		if(tourline!=null) {
			id = tourline.getId();
		}	    
		if (cost != null) {
		    tourline.setCost(cost);
		}

		paramMap.put("currencySign", cost.getSign());

	    int hotelAssociationNumber = serviceItemproductMapper.findByItemIdAndProductId("bfed41b541d5413d9e67f7ae67236e7f", tourline.getProductid());
	    int airticketAssociationNumber = serviceItemproductMapper.findByItemIdAndProductId("5acaa9d8f3324ea0b9cef05d758ca08d", tourline.getProductid());

	    paramMap.put("hotelAssociationNumber",hotelAssociationNumber);
	    paramMap.put("airticketAssociationNumber", airticketAssociationNumber);

	    List<ServiceItem> includeItems = serviceItemMapper.findProductIdAndType(tourline.getProductid(), 1);
	    List<ServiceItem> notIncludeItems = serviceItemMapper.findProductIdAndType(tourline.getProductid(), 0);

	    paramMap.put("inludeItems",includeItems);
	    paramMap.put("notIncludeItems", notIncludeItems);
	    
		//行程亮点<br>替换回车
		String highlights = tourline.getHighlights();
		if (highlights!=null) {
			highlights = highlights.replace("\r\n", "^^");
			tourline.setHighlights(highlights);
		}
		
		//航空公司说明<br>替换回车
		String flightnotices = tourline.getFlightnotice();
		if (flightnotices!=null) {
			flightnotices = flightnotices.replace("\r\n", "^^");
			tourline.setFlightnotice(flightnotices);
		}
		
		//注意事项<br>替换回车
		String notice = tourline.getNotice();
		if (notice!=null) {
			notice = notice.replace("\r\n", "^^");
			tourline.setNotice(notice);
		}
		
		//线路图片
		List<String> tUrl = new ArrayList<String>();
		String imageurl = tourline.getProductProductid().getImageurl();
		if (!"".equals(imageurl) && imageurl !=null) {
			String[] url = imageurl.split(",");
			for (int i = 0; i < url.length; i++) {
				tUrl.add(url[i]);
			}
		}else{
			for (int i = 0; i < 3; i++) {
				tUrl.add("/assets-web/images/default_bg.jpg");
			}
		}
		paramMap.put("tUrl", tUrl);
		
		List<Video> videoList = videoMapper.findByIdCostnumber(tourline.getProductid(), cost.getId());
		paramMap.put("videoList",videoList);
		
		List<Tag> tagList = tagMapper.findByIdCostnumber(tourline.getProductid(), cost.getId());
		paramMap.put("tagList", tagList);
	    
		//设置线路页面的title
		Page tourPage = tourline.getProductProductid().getPagePageid();
		String title = tourPage.getMetatitle();
		if (title==null || ("").equals(title)) {
			String tourname = tourline.getTourname();
			if (tourname!=null && !("").equals(tourname)) {
				tourPage.setMetatitle(tourname);
			}else{
				tourPage.setMetatitle(tourline.getProductProductid().getName());
			}
		}
		paramMap.put("tourline", tourline);
		
		//线路行程
		List<Itinerary> oritineraryList = itineraryService.findByTourlineId(id);
		List<Itinerary> baseItineraryList = null;
		try {
			baseItineraryList = Tools.deepCopy(oritineraryList);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Itinerary itinerary2 : baseItineraryList) {
			String content = itinerary2.getContent();
			if (content!=null) {
				content = content.replace("\r\n", "<br>");		//行程内容<br>替换回车
				itinerary2.setContent(content);
			}
			String attractionIds = itinerary2.getItintaryCids();
			if(!StringUtils.isEmpty(attractionIds)){
				List<String> attractionIdList = new ArrayList<String>(Arrays.asList(attractionIds.split(",")));
				List<Attraction> attractionList = attractionMapper.selectByids(attractionIdList);
				itinerary2.setAttractions(attractionList);
			}
			String hotelId = itinerary2.getHotelId();
			if(!StringUtils.isEmpty(hotelId)){
				Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
				itinerary2.setHotel(hotel);
			}
		}
		paramMap.put("itineraryList", baseItineraryList);
		
		pageService.getNavigationCreate(paramMap, cost.getId());
		
		Region region = regionMapper.selectByPrimaryKey(tourline.getRegionid());
		paramMap.put("region", region);
		
		List<Selfpay> transferFeeList = new ArrayList<Selfpay>();
		List<Selfpay> optionalFeeList = new ArrayList<Selfpay>();
		List<Selfpay> otherFeeList = selfPayMapper.selectByTourlineId(tourline.getId());
		for(Selfpay selfpay : otherFeeList){
			switch(selfpay.getType()){
				case 1: paramMap.put("guideServiceFee",selfpay);break;
				case 2: paramMap.put("steamFee",selfpay);break;
				case 3: transferFeeList.add(selfpay);break;	
				case 4: optionalFeeList.add(selfpay);
			}
		}
		if(transferFeeList.size() > 0){
			paramMap.put("transferFeeList",transferFeeList);
		}
		if(optionalFeeList.size() > 0){
			paramMap.put("optionalFeeList",optionalFeeList);
		}

		List<Itinerary> itineraryList = null;
		/**
		 * 如果是私人订制团,添加目的地景点数据
		 */
		if("Tailor Made".equals(region.getName())){
			paramMap.put("tailor","1");
			List<Itinerary> itineraryWithAdditionalProduct = new ArrayList<Itinerary>();
			try {
				itineraryList = Tools.deepCopy(oritineraryList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Itinerary itinerary:itineraryList){
				String destinationIds = itinerary.getItintaryDids();
				if(!StringUtils.isEmpty(destinationIds)){
					List<String> destinationIdList = new ArrayList<String>(Arrays.asList(destinationIds.split(",")));
					List<Destination> destinationList = destinationMapper.selectWithAttractionAndFood(destinationIdList,cost.getId());
					
					List<Destination>  destinationWithAdmiAndFoods = new ArrayList<Destination>();
					for(Destination destination : destinationList){
						if((destination.getAdmissiontickets() != null && destination.getAdmissiontickets().size() > 0) || (destination.getFoods() != null && destination.getFoods().size() > 0)){
							destinationWithAdmiAndFoods.add(destination);
						}
					}
					if(destinationWithAdmiAndFoods.size() > 0){
						itinerary.setDestinations(destinationWithAdmiAndFoods);
						itineraryWithAdditionalProduct.add(itinerary);
					}
				}
			}
			paramMap.put("itineraryListWithAdditionalProduct", itineraryWithAdditionalProduct);
		}
		
		/**
		 * 为行程加载自选项
		 */
		List<Itinerary> itineraryWithSelfPay = new ArrayList<Itinerary>();
		try {
			itineraryList = Tools.deepCopy(oritineraryList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Itinerary itinerary:itineraryList){
			String destinationIds = itinerary.getItintaryDids();
			if(!StringUtils.isEmpty(destinationIds)){
				List<String> destinationIdList = new ArrayList<String>(Arrays.asList(destinationIds.split(",")));
				if(itinerary.getItintarySids() != null){
					String[] selfpayIds = itinerary.getItintarySids().split(",");
					List<String> selfpayIdList = Arrays.asList(selfpayIds);
					List<Destination> destinationList = destinationMapper.selectWithSelfPay(destinationIdList,itinerary.getItintarySids());
					List<Destination> destinationWithSelfPay = new ArrayList<Destination>();
					for(Destination destination : destinationList){
						List<Selfpay> selfpayList = selfPayMapper.selectByDestinationAndSelfPayIds(destination.getId(),selfpayIdList);
						if(selfpayList != null && selfpayList.size() > 0){
							destination.setSelfpayList(selfpayList);
							destinationWithSelfPay.add(destination);
						}
					}
					if(destinationWithSelfPay.size() > 0){
						itinerary.setDestinations(destinationWithSelfPay);
						itineraryWithSelfPay.add(itinerary);
					}
				}
			}
		}
		paramMap.put("itineraryListWithSelfPay", itineraryWithSelfPay);
		
		List<Currency> currencies = currencyMapper.findAll(); //获取币种列表
		paramMap.put("currencies", currencies);
		
		//线路分类信息
		List<Region> regionlist = new ArrayList<Region>();
		String regionId = tourline.getRegionid();
		if (regionId != null && !"".equals(regionId)) {
			regionlist = regionService.getRegionidList(regionId,regionlist); //获得线路分类及其上级集合
		}
		paramMap.put("regionlist", regionlist);
		
		/**
		 * 生成线路的最低售价和打折后的售价
		 */
		BigDecimal minSellPrice = tourDateService.getMinSellPrice(tourline.getProductid(), tourline.getCostnumber());	//产品最低售价
		
		if(minSellPrice == null){
		    paramMap.put("lowsprice", 0);
			paramMap.put("discountPrice", 0);
		}else{
			//判断是否参与促销活动，并修改最低价格	
			BigDecimal discountPrice = changeSellprice(minSellPrice, tourline.getId(), tourline.getCostnumber());
			//最低售价
		    paramMap.put("lowsprice", minSellPrice);
		    //打折后的最低售价
		    paramMap.put("discountPrice", discountPrice);
		}
		List<Destination> chinaDestinations = destinationService.findChildrenById(Destination.China);
		List<Destination> tourlineDestinations = destinationService.findByTourline(tourline.getId());
		Map<String,Object> china = new HashMap<String,Object>();
		boolean haveChina = false;
		for(Destination des:chinaDestinations){
			china.put(des.getId(),new Object());
		}
		for(Destination des:tourlineDestinations){
			if(china.get(des.getId()) != null){
				haveChina = true;
			}
		}
		/*
		 * 生成线路的review信息
		 */
	    int reviewNumber = reviewService.getReviewNumber(tourline.getProductid(), tourline.getCostnumber());
	    BigDecimal avgScore = tourline.getAvgScore();
	    int starNumber = avgScore.setScale(0, BigDecimal.ROUND_DOWN).intValue();
	    paramMap.put("starNumber", starNumber);
	    paramMap.put("reviewNumber", reviewNumber);
	    paramMap.put("avgScore", avgScore);
		paramMap.put("haveChina",haveChina);
		return paramMap;
	}
	
	/**
	 * @Title: create
	 * @Description: 线路详情页面静态化
	 * @param tourline
	 * @param cost
	 * @throws Exception    
	 * @return void    返回类型
	 */
	public void create(Tourline tourline) throws Exception {
		Map<String, Object> root = getToulineDetails(tourline);
		Page page = tourline.getProductProductid().getPagePageid();
		FreemarkerUtils.createTourlinePage(this,root,page.getFilepath());
		showtourlineService.updateByisCreate(tourline.getCostnumber(), tourline.getId(), 2);
	}
	
	@Transactional
	public void autoCreateTourline() throws Exception{
		List<Tourline> tourlineList = tourlineMapper.findAllIsShow(); 
		for(Tourline tourline : tourlineList){
			if (tourline.getCostnumberids() != null
					&& !"".equals(tourline.getCostnumberids())) {
				int count = showtourlineService.findCountWithCostnumberAnaTourline(tourline.getCostnumber(),
								tourline.getId());
				if (count != 0) {
					create(tourline);
				}
			}
		}
	}
	
	
   @Transactional
   public void updatePc(Product product,String tourlineId){
	   productMapper.updateByPrimaryKeySelective(product);
	   tourlineimageMapper.deleteByTourlineId(tourlineId);
	   pageService.updateIsCreate(product.getPageid(), 1);
	   String [] imageurl = product.getImageurl().split(",");
	   if(imageurl!=null&&imageurl.length>0){
		   for (String string : imageurl) {
			Image image = imageMapper.selectByUrl(string);
			Tourlineimage tourlineimage = new Tourlineimage();
			tourlineimage.setId(Tools.getUUID());
			tourlineimage.setTourlineid(tourlineId);
			tourlineimage.setImageid(image.getId());
			tourlineimageMapper.insert(tourlineimage);
		}
	   }

	  
   }
   
	/**
	 * @Title: findByCostNumber
	 * @Description: 根据销售中心id查询显示的线路
	 * @param costNumber
	 * @return    
	 * @return List<Tourline>    返回类型
	 * @author xiejin
	 */
	public List<Tourline> getByCostNumber(String costNumber){
		return tourlineMapper.getByCostNumber(costNumber);
	};
	
	/**
	 * @Title: findByPromotionId
	 * @Description: 促销活动线路后台预览
	 * @param costnumber
	 * @param promotionId
	 * @param time
	 * @return    
	 * @return List<Tourline>    返回类型
	 */
	@Transactional
	public void findByPromotionId(String costnumber,String promotionId,Model model){
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		Cost cost = costMapper.selectByPrimaryKey(costnumber); // 线路对应的销售中心
		List<Tourline> tourlinelist = this.tourlineMapper.findByPromotionId(costnumber, promotionId, time);
		for (Tourline tourline2 : tourlinelist) {
			String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
			if (imageurl != null && !imageurl.equals("")) {
				String[] url = (imageurl + ",").split(",");
				tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
			} else {
				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
			}
			List<ServiceItem> serviceItemList = serviceItemMapper.findBytourlineinclude(tourline2.getInclude());
			tourline2.setServiceItemList(serviceItemList);
			//线路日期
//			List<Tourdate> tourdatelist = tourdateMapper
//					.getDateAndPriceByTourlineid(tourline2.getId(), costnumber);
//			for (Tourdate tourdatelist2 : tourdatelist) {
//				int startdate = tourdatelist2.getStartdate();
//				tourdatelist2.setStartdatestr(Tools.getTime(startdate));
//				int enddatestr = tourdatelist2.getEnddate();
//				tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
//			}
//			tourline2.setTourdatesTourlineid(tourdatelist);
			tourline2.setCost(cost);
			BigDecimal lowsprice = tourpriceMapper.getminsellPrice(
					tourline2.getProductProductid().getId(), costnumber, time); 			// 产品最低售价
			tourline2.setLowsprice(lowsprice);
			//判断是否参与促销活动，并修改最低价格		
			BigDecimal discountPrice = changeSellprice(lowsprice, tourline2.getId(), costnumber);	//折后价格，若美折扣，为原价
			tourline2.setDiscountPrice(discountPrice);
			List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
			tourline2.setPromotionList(promotionList);
			//查询相关标签
//			List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
//			tourline2.setTagList(tagList);
			//查询线路评价数量
			int reviewCount = reviewMapper.getReviewCount(tourline2.getProductid(), costnumber);
			tourline2.setReviewCount(reviewCount);
		}
		model.addAttribute("tourlinelist", tourlinelist);
	}
	
	/**
	 * @Title: findByPromotionId
	 * @Description: 促销活动线路静态化
	 * @param costnumber
	 * @param promotionId
	 * @param time
	 * @return    
	 * @return List<Tourline>    返回类型
	 */
	@Transactional
	public void findByPromotionIdCreate(String costnumber,String promotionId,Map<String, Object> root){
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString);
		Cost cost = costMapper.selectByPrimaryKey(costnumber); // 线路对应的销售中心
		List<Tourline> tourlinelist = this.tourlineMapper.findByPromotionId(costnumber, promotionId, time);
		for (Tourline tourline2 : tourlinelist) {
			String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
			if (imageurl != null && !imageurl.equals("")) {
				String[] url = (imageurl + ",").split(",");
				tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
			} else {
				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
			}
			List<ServiceItem> serviceItemList = serviceItemMapper.findBytourlineinclude(tourline2.getInclude());
			tourline2.setServiceItemList(serviceItemList);
			//线路日期
//			List<Tourdate> tourdatelist = tourdateMapper
//					.getDateAndPriceByTourlineid(tourline2.getId(), costnumber);
//			for (Tourdate tourdatelist2 : tourdatelist) {
//				int startdate = tourdatelist2.getStartdate();
//				tourdatelist2.setStartdatestr(Tools.getTime(startdate));
//				int enddatestr = tourdatelist2.getEnddate();
//				tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
//			}
//			tourline2.setTourdatesTourlineid(tourdatelist);
			tourline2.setCost(cost);
			BigDecimal lowsprice = tourpriceMapper.getminsellPrice(
					tourline2.getProductid(), costnumber, time); 			// 产品最低售价
			tourline2.setLowsprice(lowsprice);
			//判断是否参与促销活动，并修改最低价格		
			BigDecimal discountPrice = changeSellprice(lowsprice, tourline2.getId(), costnumber);	//折后价格，若美折扣，为原价
			tourline2.setDiscountPrice(discountPrice);
			List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
			tourline2.setPromotionList(promotionList);
			//查询相关标签
//			List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
//			tourline2.setTagList(tagList);
			//查询线路评价数量
			int reviewCount = reviewMapper.getReviewCount(tourline2.getProductid(), costnumber);
			tourline2.setReviewCount(reviewCount);
		}
		root.put("tourlinelist", tourlinelist);
	}
	
	 public void findTourlineByCouponseid(Model model ,String costnumber,String couponseid){
		  Cost cost = costMapper.selectByPrimaryKey(costnumber);
		  String timeString = Tools.getTime();
		  int time = Tools.getTimestemp(timeString);
		  List<Navigation> navigation = navigationMapper.findByType(1,costnumber);		//查找上导航
			for (Navigation navigation2 : navigation) {
				String regionId = regionService.findIdByUrl(navigation2.getUrl());	//查询导航对应的分类
				if (regionId !=null) {
					List<Tag> tagList = tagMapper.findRegionShow(regionId, costnumber,Tools.getDtimestemp(Tools.getDtime()));	//查询分类对应的标签集合
					navigation2.setTagList(tagList);
				}
			}
			model.addAttribute("navigation", navigation);
			
			List<Navigation> navigation2 = navigationMapper.findByType(2,costnumber);		//查找下导航
			model.addAttribute("navigation2", navigation2);
			
			List<Region> region = regionService.findByCostnumber(1,costnumber);		//查找产品分类
			model.addAttribute("region", region);
			
			List<Currency> currencies = currencyMapper.findAll(); //获取币种列表
			model.addAttribute("currencies", currencies);
			
			//二级页面热卖线路
			List<Tourline> tourlinehot = searchTourlineHot(1, 1, costnumber);		//查找二级页面显示的热卖线路
			for (Tourline tourline2 : tourlinehot) {
				String imageurl = tourline2.getProductProductid().getImageurl();		//获得产品相关图片路径
				if(imageurl!=null && !imageurl.equals("")){
					String[] url = (imageurl+",").split(",");
					tourline2.setCoverimageurl(url[0]);		//设置线路封面图路径
				}else{
					tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg");		//设置线路封面图路径
				}
				tourline2.setCost(cost);
				BigDecimal lowsprice = tourDateService.getminsellPrice(tourline2.getId(),costnumber);		//产品最低售价
				//判断是否参与促销活动，并修改最低价格		
				lowsprice = changeSellprice(lowsprice, tourline2.getId(), costnumber);
				tourline2.setLowsprice(lowsprice);
			}
			List<Tourline> tourlinelist = tourlineMapper.findTourlineByCouponseID(costnumber, couponseid);
			for (Tourline tourline2 : tourlinelist) {
				String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
				if (imageurl != null && !imageurl.equals("")) {
					String[] url = (imageurl + ",").split(",");
					tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
				} else {
					tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
				}
				List<Tourdate> tourdatelist = tourdateMapper
						.getDateAndPriceByProductid(tourline2.getProductid(), costnumber);
				for (Tourdate tourdatelist2 : tourdatelist) {
					int startdate = tourdatelist2.getStartdate();
					tourdatelist2.setStartdatestr(Tools.getTime(startdate));
					int enddatestr = tourdatelist2.getEnddate();
					tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
				}
				tourline2.setTourdatesTourlineid(tourdatelist);
				tourline2.setCost(cost);
				BigDecimal lowsprice = tourpriceMapper.getminsellPrice(
						tourline2.getId(), costnumber, time); // 产品最低售价
				//判断是否参与促销活动，并修改最低价格		
				lowsprice = changeSellprice(lowsprice, tourline2.getId(), costnumber);
				tourline2.setLowsprice(lowsprice);
				List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
				tourline2.setPromotionList(promotionList);
				//查询相关标签
				List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
				tourline2.setTagList(tagList);
			}
			model.addAttribute("tourlinelist", tourlinelist);
			model.addAttribute("tourlinehot", tourlinehot);
			model.addAttribute("frontCode",cost.getCode());
			
	  }
	 
	 public void createWithCouponse(String costnumber,Couponse couponse){
		 Map<String, Object> root = new HashMap<String, Object>();
		 Cost cost = costMapper.selectByPrimaryKey(costnumber);
		  String timeString = Tools.getTime();
		  int time = Tools.getTimestemp(timeString);
		  List<Navigation> navigation = navigationMapper.findByType(1,costnumber);		//查找上导航
			for (Navigation navigation2 : navigation) {
				String regionId = regionService.findIdByUrl(navigation2.getUrl());	//查询导航对应的分类
				if (regionId !=null) {
					List<Tag> tagList = tagMapper.findRegionShow(regionId, costnumber,Tools.getDtimestemp(Tools.getDtime()));	//查询分类对应的标签集合
					navigation2.setTagList(tagList);
				}
			}
			root.put("navigation", navigation);
			
			List<Navigation> navigation2 = navigationMapper.findByType(2,costnumber);		//查找下导航
			root.put("navigation2", navigation2);
			
			List<Region> region = regionService.findByCostnumber(1,costnumber);		//查找产品分类
			root.put("region", region);
			
			List<Currency> currencies = currencyMapper.findAll(); //获取币种列表
			root.put("currencies", currencies);
			
			//二级页面热卖线路
			List<Tourline> tourlinehot = searchTourlineHot(1, 1, costnumber);		//查找二级页面显示的热卖线路
			for (Tourline tourline2 : tourlinehot) {
				String imageurl = tourline2.getProductProductid().getImageurl();		//获得产品相关图片路径
				if(imageurl!=null && !imageurl.equals("")){
					String[] url = (imageurl+",").split(",");
					tourline2.setCoverimageurl(url[0]);		//设置线路封面图路径
				}else{
					tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg");		//设置线路封面图路径
				}
				tourline2.setCost(cost);
				BigDecimal lowsprice = tourDateService.getminsellPrice(tourline2.getId(),costnumber);		//产品最低售价
				//判断是否参与促销活动，并修改最低价格		
				lowsprice = changeSellprice(lowsprice, tourline2.getId(), costnumber);
				tourline2.setLowsprice(lowsprice);
			}
			List<Tourline> tourlinelist = tourlineMapper.findTourlineByCouponseID(costnumber, couponse.getId());
			for (Tourline tourline2 : tourlinelist) {
				String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
				if (imageurl != null && !imageurl.equals("")) {
					String[] url = (imageurl + ",").split(",");
					tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
				} else {
					tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
				}
				List<Tourdate> tourdatelist = tourdateMapper
						.getDateAndPriceByProductid(tourline2.getProductid(), costnumber);
				for (Tourdate tourdatelist2 : tourdatelist) {
					int startdate = tourdatelist2.getStartdate();
					tourdatelist2.setStartdatestr(Tools.getTime(startdate));
					int enddatestr = tourdatelist2.getEnddate();
					tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
				}
				tourline2.setTourdatesTourlineid(tourdatelist);
				tourline2.setCost(cost);
				BigDecimal lowsprice = tourpriceMapper.getminsellPrice(
						tourline2.getId(), costnumber, time); // 产品最低售价
				//判断是否参与促销活动，并修改最低价格		
				lowsprice = changeSellprice(lowsprice, tourline2.getId(), costnumber);
				tourline2.setLowsprice(lowsprice);
				List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
				tourline2.setPromotionList(promotionList);
				//查询相关标签
				List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
				tourline2.setTagList(tagList);
			}
			root.put("tourlinelist", tourlinelist);
			root.put("tourlinehot", tourlinehot);
			root.put("frontCode",cost.getCode());
			root.put("request", request);
					
					
					// 静态页面的完整路径
					String str = request.getSession().getServletContext().getRealPath("/")+ couponse.getFilePath();
					if (str.indexOf("/") != -1) {

						String folder = str.substring(0, str.lastIndexOf("/"));
						File f = new File(folder);
						if (!f.exists() && !f.isDirectory()) { // 是文件夹，且文件夹不存在则创建文件夹
							f.mkdir();
						}
						File file = new File(str);
						// 如果静态文件存在，则删除之前的静态页面，重新生成新的静态页面
						if (file.isFile() && file.exists()) {
							file.delete();
						}
						str = null;// 释放资源
						if (file != null) {
							file = null;
						}
						FreemarkerUtils.createHTML(
								request.getSession().getServletContext(), root,
								"/admin/manage/couponse/couponseTs.ftl", couponse.getFilePath());
					}
					couponse.setIsCreate(1);
					couponseMapper.updateByPrimaryKey(couponse);
	 }
	 
	/**
	 * @Title: findTourlineByCondition
	 * @Description: 分页查询intertrips线路
	 * @param request
	 * @param model
	 * @param costnumber
	 * @param destination
	 * @param minPrice
	 * @param maxPrice
	 * @param sort
	 * @param keyword
	 * @param rate
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> findTourlineByCondition(HttpServletRequest request,Model model,
			String costnumber,String destination,BigDecimal minPrice, BigDecimal maxPrice, String sort,
			String keyword,String rate,String dateFrom,String dateTo,String regionId,Integer size,
			String promotionId) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cost cost = costMapper.selectByPrimaryKey(costnumber); // 线路对应的销售中心
		String pageNow = request.getParameter("pageNow");
		Pages page = null;
		List<Tourline> tourlinelist = new ArrayList<Tourline>();
		String timeString = Tools.getTime();
		int time = Tools.getTimestemp(timeString); 		// 当前系统时间戳
		int dateFromIn	= 0;
		int dateToIn	= 0;
		if (dateFrom != null && !"".equals(dateFrom)) {
			dateFromIn = Tools.getTimestemp(dateFrom);	
		}
		if (dateTo != null && !"".equals(dateTo)) {
			dateToIn = Tools.getTimestemp(dateTo);
		}
		int totalCount = tourlineMapper.getTourlineCountByCondition(costnumber, destination,minPrice,
				maxPrice, keyword,rate,time,dateFromIn,dateToIn,regionId,promotionId);
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
		} else {
			page = new Pages(totalCount, 1);
		}
		page.setPageSize(size);
		int startPos = page.getStartPos();
		int pageSize = page.getPageSize();
		tourlinelist = this.tourlineMapper.getTourlineByCondition(costnumber, startPos, pageSize,
				destination,minPrice, maxPrice, sort, keyword, time,rate,dateFromIn,dateToIn,regionId,promotionId);
		for (Tourline tourline2 : tourlinelist) {
			String imageurl = tourline2.getProductProductid().getImageurl(); // 获得产品相关图片路径
			if (imageurl != null && !imageurl.equals("")) {
				String[] url = (imageurl + ",").split(",");
				tourline2.setCoverimageurl(url[0]); // 设置线路封面图路径
			} else {
				tourline2.setCoverimageurl("/assets-web/images/default_bg.jpg"); // 设置线路封面图路径
			}
			List<ServiceItem> serviceItemList = serviceItemMapper.findBytourlineinclude(tourline2.getInclude());
			tourline2.setServiceItemList(serviceItemList);
			//线路日期
//			List<Tourdate> tourdatelist = tourdateMapper
//					.getDateAndPriceByTourlineid(tourline2.getId(), costnumber);
//			for (Tourdate tourdatelist2 : tourdatelist) {
//				int startdate = tourdatelist2.getStartdate();
//				tourdatelist2.setStartdatestr(Tools.getTime(startdate));
//				int enddatestr = tourdatelist2.getEnddate();
//				tourdatelist2.setEnddatestr(Tools.getTime(enddatestr));
//			}
//			tourline2.setTourdatesTourlineid(tourdatelist);
			tourline2.setCost(cost);
			BigDecimal lowsprice = tourline2.getLowsprice();
			//判断是否参与促销活动，并修改最低价格		
			BigDecimal discountPrice = changeSellprice(lowsprice, tourline2.getId(), costnumber);	//折后价格，若美折扣，为原价
			tourline2.setDiscountPrice(discountPrice);
			List<Promotion> promotionList = promotionMapper.findByProductIdCostnumber(tourline2.getProductid(), costnumber,Tools.getDtimestemp(Tools.getDtime()));
			tourline2.setPromotionList(promotionList);
			//查询相关标签
//			List<Tag> tagList = tagMapper.findByIdCostnumber(tourline2.getProductProductid().getId(), costnumber);
//			tourline2.setTagList(tagList);
			//查询线路评价数量
			int reviewCount = reviewMapper.getReviewCount(tourline2.getProductid(), costnumber);
			tourline2.setReviewCount(reviewCount);
			String highlights = tourline2.getHighlights();
			if (highlights!=null) {
				highlights = highlights.replace("\r\n", "^^");		//线路亮点^^替换回车
				tourline2.setHighlights(highlights);
			}
			
		}
		String downPageContent = FreemarkerUtils.getDownPageContent(request
				.getSession().getServletContext(), page);
		String tourContent = FreemarkerUtils.getTourlineListContent(request
				.getSession().getServletContext(), tourlinelist, request);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("tourContent", tourContent);
		root.put("downPageContent", downPageContent);
		root.put("totalCount", totalCount);
		root.put("now", Tools.getTime());
		root.put("page", page);
		return root;
	}
	
	
	
	
	/**
	 * 根据产品ID重置线路的平均评论分
	 * 
	 * @param tourlineId
	 */
	@Transactional
	public void setAvgReviewScore(String productId){
		Tourline tourline = tourlineMapper.findByProductId(productId);
		List<Review> reviewList = reviewMapper.getAllReviewByProductId(productId);
		if(reviewList.size() > 0){
			int totalScore = 0;
			for(Review review : reviewList){
				totalScore += review.getWenjingScore();
			}
			BigDecimal avgReviewScore = new BigDecimal(totalScore).divide(new BigDecimal(reviewList.size()), 1,BigDecimal.ROUND_UP);
			tourline.setAvgScore(avgReviewScore);
			tourlineMapper.updateByPrimaryKey(tourline);
		}
	}
}

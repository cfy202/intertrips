package com.wenjing.action;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.Pages;
import com.wenjing.dao.*;
import com.wenjing.entity.*;
import com.wenjing.service.*;
import com.wenjing.util.FileUtil;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 类说明 后台线路管理controller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/tourline")
public class TourlineController {

	@Resource
	private TourlineService tourlineService;
	@Resource
	private DestinationService destinationService;
	@Resource
	private TourlinedestinationMapper tourlinedesMapper;
	@Resource
	private SelfpayMapper selfpayMapper;
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private AttractionMapper attractionMapper;
	@Resource
	private HotelMapper hotelMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private PageMapper pageMapper;
	@Resource
	private PageService pageService;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private TourlineselfpayMapper tourlineselfpayMapper;
	@Resource
	private TourlineattractionMapper tourlineattrMapper;
	@Resource
	private TourlinehotelMapper tourlinehotelMapper;
	@Resource
	private ImageService imageService;
	@Resource
	private TourlineImageService tourlineimagteService;
	@Resource
	private ProductService productService;
	@Resource
	private CoordinateService coordinateService;
	@Resource
	private CostService costService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private NavigationMapper navigationMapper;
	@Resource
	private RegionService regionService;
	@Resource
	private HotTourlineService hotTourlineService;
	@Resource
	private TourDateService tourDateService;
	@Resource
	private ItineraryService itineraryService;
	@Resource
	private TourLineCalendarService tourLineCalendarService;

	@Resource
	private OrderService orderService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private ShowTourlineService showtourlineService;
	@Resource
	private IndexShowTourlineService indexshowtourlineService;
	@Resource
	private ServiceItemService serviceItemService;
	@Resource
	private TourDateService tourdateService;
	@Resource
	private DepartureService departureService;

	private ExecutorService threadPool = Executors.newCachedThreadPool();

	private Future<Integer> result = null;

	/**
	 * 查询所有tourline
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model, HttpServletResponse response) {
		String costidss = tourlineService.findAll(request, response, model);
		List<Cost> costlists = costService.findAll();
		String hott = null;
		String show = null;
		String index = null;
		String costNow = null;
		String isCreate = null;
		String costnumberId = null;
		List costlistst = Tools.getCostNumber(request);
		if (costlistst != null && costlistst.size() == 1) {
			costnumberId = costlistst.get(0).toString();
			costNow = costnumberId;
		} else {
			costnumberId = costidss;
		}
		if (costnumberId != null) {
			List<HotTourline> hottourline = hotTourlineService
					.findByCostnumber(costnumberId);
			for (HotTourline hotTourline2 : hottourline) {
				hott += "," + hotTourline2.getTourlineId();
			}
			List<Showtourline> shotourline = showtourlineService
					.findByCostnumber(costnumberId, null);
			for (Showtourline showtourline : shotourline) {
				show += "," + showtourline.getTourlineid();
			}
			List<Showtourline> showtourlineIs = showtourlineService
					.findByCostnumber(costnumberId, 2);
			for (Showtourline showtourline : showtourlineIs) {
				isCreate += "," + showtourline.getTourlineid();
			}
			List<Indexshowtourline> indxs = indexshowtourlineService
					.findByCostnumber(costnumberId);
			for (Indexshowtourline indexshowtourline : indxs) {
				index += "," + indexshowtourline.getTourlineid();
			}
		}
		if (hott != null) {
			hott = hott.substring(1);
		}
		if (show != null) {
			show = show.substring(1);
		}
		if (index != null) {
			index = index.substring(1);
		}
		if (isCreate != null) {
			isCreate = isCreate.substring(1);
		}
		model.addAttribute("costlists", costlists);
		model.addAttribute("hott", hott);
		model.addAttribute("show", show);
		model.addAttribute("index", index);
		model.addAttribute("costNow", costNow);
		model.addAttribute("isCreate", isCreate);
		return "/admin/manage/tourline/tourline.ftl";
	}

	/**
	 * 保存tourline
	 * 
	 * @param tourline
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute("tourline") Tourline tourline,
			@ModelAttribute("page") Page page,
			@ModelAttribute("product") Product product,
			@RequestParam("id") String id) {
		tourline.setId(id);
		
		// System.out.println(product.getIsshow() + "");
		if (product.getIsshow() == null) {
			product.setIsshow((byte) 0);
		}
		if (product.getIshot() == null) {
			product.setIshot((byte) 0);
		}
		if (product.getIndexShow() == null) {
			product.setIndexShow((byte) 0);
		}

		product.setType(1);
		Tourline tourline2 = tourlineService.findById(id);
		String[] dests = request.getParameterValues("destions");
		String[] attrs = request.getParameterValues("attrs");
		String[] hotels = request.getParameterValues("hotels");
		String[] self = request.getParameterValues("self");
		String[] imageid = request.getParameterValues("imageid");
		String[] destionName = request.getParameterValues("denames");
		String[] include = request.getParameterValues("include");
		String[] noinclude = request.getParameterValues("exclude");

		StringBuffer bf = new StringBuffer();
		if (destionName != null && destionName.length > 0) {
			for (String string : destionName) {
				bf.append("," + string);
			}
		}
		tourline.setDestinationlist(bf.substring(1));
		String parentIds = tourline.getRegionid();
		String parentido = null;
		if (parentIds != null) {
			StringBuffer bu = new StringBuffer();
			parentido = regionService.getParentids(parentIds, bu) + ","
					+ parentIds;
		}
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		// image.setOpuser(admin.getUsername());
		product.setEditor(admin.getUsername()); // 写入操作用户 fred 2015-7-17
		product.setUpdatetime(new Date());
		Region regions = regionService.findById(tourline.getRegionid());
		page.setType(1);
		String destinationList = null;
		String desc = null;
		if (regions.getDestinationList() != null
				&& !"".equals(regions.getDestinationList())) {
			destinationList = regions.getDestinationList();
			String deslist[] = tourline.getDestinationlist().split(",");
			for (String string : deslist) {
				if (destinationList.indexOf(string) == -1) {
					desc += "," + string;
				}
			}
			if (desc != null) {
				destinationList = destinationList + desc.substring(4);
			}
		} else {
			destinationList = tourline.getDestinationlist();
		}

		tourline.setParentIds(parentido.substring(1));
		if (tourline.getCostnumberids() == null) {
			tourline.setCostnumberids(tourline.getCostnumber());
		}
		String[] costidsa = tourline.getCostnumberids().split(",");
		for (String string : costidsa) {
			showtourlineService.updateByisCreate(string, tourline.getId(), 1);
		}
		boolean isus = productService.isExistUserCode(product.getCode());
		if (isus) {
		}
		if (tourline2 != null && !tourline2.equals("")) {
			// System.out.println(tourline2.getProductProductid().getCode());
			if (tourline2.getProductProductid().getCode()
					.equals(product.getCode())) {
				tourlineService.updateAirportPickUp(request, id); // 修改接送机
				tourlineService.update(tourline, page, product, tourline.getCostnumber());
				tourlineService.saveregest(tourline, imageid, dests, self,
						attrs, hotels, include, noinclude, 2);
				regionService.updateRegionDestinationList(destinationList,
						regions.getId());
			} else {
				if (isus) {
					tourlineService.updateAirportPickUp(request, id); // 修改接送机
					tourlineService.update(tourline, page, product, tourline.getCostnumber());
					tourlineService.saveregest(tourline, imageid, dests, self,
							attrs, hotels, include, noinclude, 2);
					regionService.updateRegionDestinationList(destinationList,
							regions.getId());
				} else {
					return "redirect:/admin/tourline/update.do?tourlineId="
							+ tourline.getId();
				}

			}

		} else {
			if (isus) {
				tourlineService.saveAirportPickUp(request, id); // 接送机
				tourlineService.save(tourline, page, product,
						tourline.getCostnumber());
				tourlineService.saveregest(tourline, imageid, dests, self,
						attrs, hotels, include, noinclude, 1);
			} else {
				return "redirect:/admin/tourline/add.do";
			}
		}

		return "redirect:/admin/tourline/list.do";
	}

	/**
	 * 添加，修改tourline
	 * 
	 * @param tourline
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Tourline tourline,
			@Param("tourlineId") String tourlineId, Model model) {
		tourline = tourlineService.findById(tourlineId);
		if (tourline != null) {

			model.addAttribute("tourline", tourline);
			List<Tourlinedestination> tourlinedes = tourlinedesMapper
					.selectByTourlineid(tourline.getId());
			StringBuffer bf = new StringBuffer();
			// System.out.println(tourlinedes.size());
			List<String> destinationStr = new ArrayList<String>();
			for (Tourlinedestination tourlinedestination : tourlinedes) {
				bf.append("," + tourlinedestination.getDestinationid());
				destinationStr.add(tourlinedestination.getDestinationid());
			}
			String uf = "";
			if (bf != null && bf.length() > 1) {
				uf = bf.toString().substring(1);
			}
			model.addAttribute("tourlinedes", uf);

			List<Destination> destinations = null; // 初始值 fred 2015-8-21

			if (destinationStr != null && destinationStr.size()>0) { // 增加 ""
																		// 判断
																		// fred
																		// 2015-8-21
				destinations = destinationService
						.selectWithTourline(destinationStr);
			}
			model.addAttribute("destinations", destinations); // 给页面变量赋值 fred
																// 2015-8-21

			List<Tourlineattraction> tourlineattr = tourlineattrMapper
					.selectByTourlineid(tourline.getId());
			String attrs = null;
			for (Tourlineattraction tourlineattraction : tourlineattr) {
				attrs += "," + tourlineattraction.getAttractionid();
			}
			if (attrs != null) {
				attrs = attrs.substring(1);
			}
			model.addAttribute("attrs", attrs);
			List<Tourlinehotel> tourlineht = tourlinehotelMapper
					.selectByTourlineid(tourline.getId());
			String checkedHot = null;
			for (Tourlinehotel tourlinehotel : tourlineht) {
				checkedHot += "," + tourlinehotel.getHotelid();
			}
			if (checkedHot != null) {
				checkedHot = checkedHot.substring(1);
			}
			model.addAttribute("checkedHot", checkedHot);
			List<Tourlineselfpay> tourlinese = tourlineselfpayMapper
					.selectByTourlineid(tourline.getId());
			model.addAttribute("tourlinese", tourlinese);
			Product product = productMapper.selectByPrimaryKey(tourline
					.getProductid());
			Page page = pageMapper.selectByPrimaryKey(product.getPageid());
			model.addAttribute("product", product);
			model.addAttribute("page", page);
			String imageurl = product.getImageurl();
			if (imageurl != null && !imageurl.equals("")) {// 判断景点是否有图片，并读出图片url
				imageurl = imageurl + ",";
				String[] imgurl = imageurl.split(",");
				model.addAttribute("imgurl", imgurl);
				model.addAttribute("img", 1);
			} else {
				model.addAttribute("img", 0);
			}
			List<Destination> delist = destinationService
					.findCityAndProvinceByCostNumber();
			model.addAttribute("delist", delist);
			List<AirportPickUp> aPickUps = tourlineService
					.findAllApickUpsBytourId(tourlineId);
			model.addAttribute("aPickUps", aPickUps);
			List<Selfpay> selflist = selfpayMapper.findWithType(0,tourline.getCostnumber());
			model.addAttribute("selflist", selflist);
			List<Tourlineselfpay> tourlineSE = tourlineselfpayMapper.selectByTourlineid(tourlineId);
			model.addAttribute("tourlineSE",tourlineSE);
			List<ServiceItem> serviceItemList = serviceItemService.findAll();
			model.addAttribute("serviceItemList", serviceItemList);
			List<Departure> departures = departureService.findAll();
			model.addAttribute("departures", departures);

		}
		return "/admin/manage/tourline/tourlineUpdate.ftl";
	}

	/**
	 * 添加tourline
	 * 
	 * @param Tourline
	 * @param model
	 * 
	 * @return sevens
	 */
	@RequestMapping("/add")
	public String add(Tourline tourline, Model model) {
		String id = Tools.getUUID();// 产生uuid
		tourline.setId(id);
		int sort = tourlineService.getOrderId();
		tourline.setSort(sort + 1);
		List<Selfpay> selflist = selfpayMapper.findWithType(0,tourline.getCostnumber());
		List<ServiceItem> serviceItemList = serviceItemService.findAll();
		List<Departure> departures = departureService.findAll();
		List<Destination> destinationlist = destinationService.findAllByCostNumber();
		model.addAttribute("destinationlist",destinationlist);
		model.addAttribute("selflist", selflist);
		model.addAttribute("tourline", tourline);
		model.addAttribute("serviceItemList", serviceItemList);
		model.addAttribute("departures", departures);
		return "/admin/manage/tourline/tourlineAdd.ftl";
	}

	/**
	 * 异步处理价格列表更新
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/destUplist", method = RequestMethod.POST)
	public @ResponseBody List<Destination> uptable(@Param("ids") String ids)
			throws IOException {
		List<Destination> list = new ArrayList<Destination>();
		String[] idlist = ids.split(",");
		for (String string : idlist) {
			Destination destination = destinationService.findById(string);
			list.add(destination);
		}
		return list;
	}

	/**
	 * 异步处理目的地
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateDestion", method = RequestMethod.POST)
	public @ResponseBody List<Destination> updateDestion(
			@Param("costnumber") String costnumber) throws IOException {
		List<Destination> delist = null;
		if (costnumber != null) {
			delist = destinationService.findCityAndProvinceByCostNumber();
		}

		return delist;

	}

	/**
	 * 异步处理线路分类
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateRegion", method = RequestMethod.POST)
	public @ResponseBody List<Region> updateRegion(
			@Param("costnumber") String costnumber, @Param("type") Integer type)
			throws IOException {
		List<Region> regionlist = null;
		if (costnumber != null) {
			regionlist = regionService.findByUpIdAndCostid(type, costnumber);

		}
		return regionlist;

	}

	/**
	 * 异步处理线路分类
	 * 
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = "/updateSelf", method = RequestMethod.POST)
	public @ResponseBody List<Selfpay> updateSelf(@Param("names") String names)
			throws IOException {
		List<Selfpay> selflist = null;
		if (names != null) {
			//selflist = selfpayMapper.findWithD(names);
		}
		return selflist;

	}

	/**
	 * 根据imageid和attractionid删除景点图片联系表
	 * 
	 * @param url
	 * @param attractionid
	 *            xiejin
	 */
	@RequestMapping("/deletebyimageid")
	@ResponseBody
	public String deletePageImageByImageId(@RequestParam("url") String url,
			@RequestParam("tourlineId") String tourlineId) {
		Image image = imageService.selectByUrl(url); // 根据url查询图片
		String imageId = image.getId(); // 获取图片id
		tourlineimagteService.deleteByImageId(imageId, tourlineId); // 根据imageid和attractionid删除景点图片联系表信息
		return null;
	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteTurline(HttpServletResponse response,
			@Param("tourlineId") String tourlineId,
			@Param("productId") String productId,
			@Param("pageId") String pageId, Model model) {
		String message = tourlineService.delete(tourlineId, productId, pageId);
		WebUtils.addCookie(request, response, "message", message);
		return "redirect:/admin/tourline/list.do";
	}
	
	/**
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/remove")
	public String removeTurline(HttpServletResponse response,
			@Param("tourlineId") String tourlineId,
			@Param("productId") String productId,
			@Param("pageId") String pageId,
			@RequestParam("costnumberId") String costnumberId,Model model) {
			FileUtil util = new FileUtil();
			boolean det = false;
			String[] tids = new String[1];
			List costlistst = Tools.getCostNumber(request);
			String costCroll = null;
			if (costlistst != null && costlistst.size() == 1) {
				costCroll = costlistst.get(0).toString();
			} else {
				costCroll = costnumberId;
			}
	
			Page page = pageMapper.selectByPrimaryKey(pageId);
			Cost costa = costService.findById(costCroll);
			String ss = request.getSession().getServletContext().getRealPath("/") + page.getFilepath();
			det = util.DeleteFolder(request.getSession().getServletContext()
			.getRealPath("/") + page.getFilepath());
	   	String message = null;
	   	if(det){
	   		message ="产品下线成功!";
	   	}else{
	   		message = "产品下线失败！";
	   	}
		WebUtils.addCookie(request, response, "message", message);
		return "redirect:/admin/tourline/list.do";
	}

	/**
	 * 异步处理产品是否热卖
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/updateishot")
	@ResponseBody
	public int updateStatus(@RequestParam("ishot") Integer ishot,
			@RequestParam("tourlineId") String tourlineId,
			@RequestParam("pageId") String pageId,
			@RequestParam("costnumberId") String costnumberId) throws Exception {
		List costlistst = Tools.getCostNumber(request);
		String costCroll = null;
		if (costlistst != null && costlistst.size() == 1) {
			costCroll = costlistst.get(0).toString();
		} else {
			costCroll = costnumberId;
		}
		Page page = pageMapper.findByCostnumber(costCroll);
		if (ishot != null && ishot == 1) {
			ishot = 0;
			hotTourlineService.delete(costCroll, tourlineId);
		} else {
			ishot = 1;
			hotTourlineService.save(costCroll, tourlineId);
		}
		pageService.create(costCroll, page, request);
		return ishot;
	}

	/**
	 * 异步处理产品是否首页显示
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/updateindexshow")
	@ResponseBody
	public int updateIsindexshow(
			@RequestParam("isindexshow") Integer isindexshow,
			@RequestParam("tourlineId") String tourlineId,
			@RequestParam("costnumberId") String costnumberId) throws Exception {
		List costlistst = Tools.getCostNumber(request);
		String costCroll = null;
		if (costlistst != null && costlistst.size() == 1) {
			costCroll = costlistst.get(0).toString();
		} else {
			costCroll = costnumberId;
		}
		Page page = pageMapper.findByCostnumber(costCroll);
		if (isindexshow != null && isindexshow == 1) {
			isindexshow = 0;
			indexshowtourlineService.delete(costCroll, tourlineId);
		} else {
			isindexshow = 1;
			indexshowtourlineService.save(costCroll, tourlineId);
		}
		pageService.create(costCroll, page, request);
        
		return isindexshow;
	}

	/**
	 * 异步处理产品是否显示
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/updateisshow")
	@ResponseBody
	public int updateIsshow(@RequestParam("isshow") Integer isshow,
			@RequestParam("tourlineId") String tourlineId,
			@RequestParam("pageId") String pageId,
			@RequestParam("costnumberId") String costnumberId) throws Exception {
		FileUtil util = new FileUtil();
		boolean det = false;
		String[] tids = new String[1];
		List costlistst = Tools.getCostNumber(request);
		String costCroll = null;
		if (costlistst != null && costlistst.size() == 1) {
			costCroll = costlistst.get(0).toString();
		} else {
			costCroll = costnumberId;
		}

		Page page = pageMapper.selectByPrimaryKey(pageId);
		Cost costa = costService.findById(costCroll);
		if (isshow != null && isshow == 1) {
			isshow = 0;
			showtourlineService.delete(costCroll, tourlineId);
//			det = util.DeleteFolder(request.getSession().getServletContext()
//					.getRealPath("/")
//					+ costa.getCode() + "/" + page.getFilepath());

		} else {
			isshow = 1;
			showtourlineService.save(costCroll, tourlineId);
			tids[0] = tourlineId;
			this.create(tids);
			showtourlineService.updateByisCreate(costCroll, tourlineId, 2);
		}
		//pageService.create(costCroll, page, request);

		return isshow;
	}

	/**
	 * 线路图片上传保存到图库
	 * 
	 * @param picaddress
	 * @return
	 */
	@RequestMapping("/savepic")
	@ResponseBody
	public Image savepictoria(@RequestParam("picaddress") String picaddress,
			@Param("costnumber") String costnumber) {
		// 保存image
		String id = Tools.getUUID();
		Image image = new Image();
		image.setId(id);
		image.setUrl(picaddress);
		String[] name = picaddress.split("images/");
		image.setName(name[1]);
		String createtime = Tools.getTime();
		image.setCreatetime(createtime);
		image.setUsetype("tourline");
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
		image.setCostnumber(costnumber);
		imageService.save(image);
		return image;
	}

	/**
	 * 异步校验产品编号是否存在
	 */
	@ResponseBody
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public boolean validateCode(@RequestParam("numbers") String numbers,
			Model model) {
		boolean success = productService.isExistUserCode(numbers);
		return success;
	}
	
	/**
	 * 异步校验产品系统编号是否存在
	 */
	
	@RequestMapping(value = "/validateProductNo", method = RequestMethod.POST)
	public @ResponseBody Integer validateProductNo(@RequestParam("productNo")String productNo,@RequestParam("costnumber")String costnumber) {
		boolean isExist = productService.isExistProductNo(productNo, costnumber);
		if(isExist){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * 预览线路
	 * 
	 * @author Sevens 时间2015-7-30
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/view")
	public String toulineDetails(Model model, @Param("id") String id,
			@Param("costIdo") String costIdo) {
		Tourline tourline = tourlineService.findById(id);
		Map<String,Object> paramMap = tourlineService.getToulineDetails(tourline);
		for(Map.Entry<String, Object> entry : paramMap.entrySet()){
			model.addAttribute(entry.getKey(), entry.getValue());
		}
		return "/front/tourdetails.ftl";
	}

	/**
	 * 生成线路详情页面
	 * 
	 * @author Sevens 时间2015-7-30
	 * @param tids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/create")
	public String create(@Param("tids") final String[] tids) throws Exception {
		if(result == null || result.isDone()){
			showtourlineService.resetIsCreate(Arrays.asList(tids));
			result = threadPool.submit(new Callable<Integer>() {
				public Integer call(){
					if (tids != null && tids.length > 0) {
						for (String string : tids) {
							Tourline tourline = tourlineService.findByIdIsshow(string); // 查询线路
							if (tourline.getCostnumberids() != null
									&& !"".equals(tourline.getCostnumberids())) {
								int count = showtourlineService.findCountWithCostnumberAnaTourline(tourline.getCostnumber(),
										tourline.getId());
								if (count != 0) {
									try {
										tourlineService.create(tourline);
									} catch (Exception e) {
										continue;
									}
								}
							}
						}
					}
					return 1;
				}
			});
		}
		return "redirect:/admin/tourline/list.do"; // ?das="+new Date();
	}

	@RequestMapping("/addTags")
	public String addTags(@Param("tids") String[] tids,
			@Param("tagids") String[] tagids,
			@Param("costnumberId") String costnumberId) throws Exception {
		List<String> productIds = new ArrayList<String>();
		List costlistst = Tools.getCostNumber(request);
		String costCroll = null;
		if (costlistst != null && costlistst.size() == 1) {
			costCroll = costlistst.get(0).toString();
		} else {
			costCroll = costnumberId;
		}
		if (tids != null && tids.length > 0) {
			for (String string : tids) {
				Tourline tourline = tourlineService.findByIdIsshow(string); // 查询线路
				productIds.add(tourline.getProductid());
				productService.deleteWithProductIdAndCostnumber(
						tourline.getProductid(), costCroll);

			}
		}
		if (tids != null && tids.length > 0 && tagids != null
				&& tagids.length > 0) {
			for (String string : tagids) {
				if (!tagids[0].equals("remove")) {
					for (String string2 : tids) {
						Tourline tourline = tourlineService
								.findByIdIsshow(string2); // 查询线路
						productService.addTags(tourline.getProductid(), string,
								costCroll);
					}
				}
			}
		}

		// return create(tids, costCroll);
		return "redirect:/admin/tourline/list.do";
	}

	@RequestMapping("/addVideos")
	public String addVideos(@Param("tids") String[] tids,
			@Param("videoids") String[] videoids,
			@Param("costnumberId") String costnumberId) throws Exception {
		List<String> productIds = new ArrayList<String>();
		List costlistst = Tools.getCostNumber(request);
		String costCroll = null;
		if (costlistst != null && costlistst.size() == 1) {
			costCroll = costlistst.get(0).toString();
		} else {
			costCroll = costnumberId;
		}
		if (tids != null && tids.length > 0) {
			for (String string : tids) {
				Tourline tourline = tourlineService.findByIdIsshow(string); // 查询线路
				productIds.add(tourline.getProductid());
				productService.deleteWithProductIdAndCostnumberV(
						tourline.getProductid(), costCroll);

			}
		}
		if (tids != null && tids.length > 0 && videoids != null
				&& videoids.length > 0) {
			for (String string : videoids) {
				if (!videoids[0].equals("remove")) {
					for (String string2 : tids) {
						Tourline tourline = tourlineService
								.findByIdIsshow(string2); // 查询线路
						productService.addVideo(tourline.getProductid(),
								string, costCroll);
					}
				}
			}
		}
		// return create(tids, costCroll);
		return "redirect:/admin/tourline/list.do";
	}

	/**
	 * @author Sevens
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/tourlinePc")
	public String tourlinePc(Model model, @Param("id") String id) {
		Tourline tourline = tourlineService.findById(id);
		model.addAttribute("tourline", tourline);
		return "/admin/manage/tourline/tourlinePc.ftl";
	}

	@RequestMapping("/updatePc")
	public String updatePc(@Param("id") String id,
			@Param("tourlineId") String tourlineId,
			@Param("imageurl") String imageurl) {
		Product product = productMapper.selectByPrimaryKey(id);
		product.setImageurl(imageurl);
		tourlineService.updatePc(product, tourlineId);
		return "redirect:/admin/tourline/list.do?dat=" + new Date();
	}

	/**
	 * @Title: Tocostnumber
	 * @Description: 异步查询要复制的目标销售中心
	 * @param departureId
	 * @return
	 * @return Map<String,Object> 返回类型
	 * @author xiejin
	 */
	@ResponseBody
	@RequestMapping(value = "/Tocostnumber", method = RequestMethod.POST)
	public List<Cost> Tocostnumber(
			@RequestParam("costnumberToa") String costnumberToa) {
		List<Cost> costlistTo = null;
		if (costnumberToa != null && !"".equals(costnumberToa)) {
			costlistTo = costService.findNotUSDCostNum(costnumberToa);
		}
		return costlistTo;
	}

	/**
	 * @Title: replaceAttraction
	 * @Description: 复制线路
	 * @param request
	 * @return
	 * @return String 返回类型
	 * @author xiejin
	 */
	@ResponseBody
	@RequestMapping(value = "/coptTourline", method = RequestMethod.POST)
	public Map<String, Object> coptTourline(HttpServletRequest request,
			RedirectAttributes rAttributes) {
		Map<String, Object> root = new HashMap<String, Object>();
		String tourlineId = request.getParameter("tourlineId");
		String replaceId = request.getParameter("replaceId");
		String regionid = request.getParameter("regionids");
		String noticeMessage = null;
		if (tourlineId != null && !"".equals(tourlineId) && replaceId != null
				&& !"".equals(replaceId)) {
			Tourline tourline = tourlineService.findById(tourlineId);
			String startcost = tourline.getCostnumber();
			tourline.setId(Tools.getUUID());
			tourline.setRegionid(regionid);
			int blag = tourlineService.save(tourline, tourline
					.getProductProductid().getPagePageid(), tourline
					.getProductProductid(), replaceId);
			tourlineService.Copysaveregest(tourline, tourlineId, 1);
			boolean flag = tourdateService.copyPrice(tourlineId, replaceId,
					startcost, tourline.getId());
			if (blag == 0 && flag) {
				noticeMessage = "复制失败，请重新选择";
			} else {
				noticeMessage = "复制成功";
			}
			root.put("noticeMessage", noticeMessage);
		}
		return root;
	}

	@RequestMapping("/tableDemoAjax")
	@ResponseBody
	public Map<String, Object> tableDemoAjax(@RequestParam String aoData,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray jsonarray = JSONArray.fromObject(aoData);

		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数

		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getInt("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getInt("value");
		}

		// 生成20条测试数据
		List<String> costnumber = Tools.getCostNumber(request);
		tourlineService.findAll(request, response, null);

		JSONObject getObj = new JSONObject();
		// map.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
		// map.put("iTotalRecords", tourlinelist.size());//实际的行数
		// map.put("iTotalDisplayRecords",
		// tourlinelist.size());//显示的行数,这个要和上面写的一样
		//
		// map.put("aaData", tourlinelist.subList(iDisplayStart,iDisplayStart +
		// iDisplayLength));//要以JSON格式返回
		return map;
	}
	
	@RequestMapping("/showProduct")
	public String showAllProduct(HttpServletRequest request,HttpServletResponse response,Model model){
		String pageNow = request.getParameter("pageNow");
		int totalSize = productService.getTourlineTotalSize();
		
		Pages page = null;
		if (pageNow != null && !"".equals(pageNow)) {
			page = new Pages(totalSize, Integer.parseInt(pageNow));
		}else{
			page = new Pages(totalSize,1);
		} 
		page.setPageSize(10);
		
		List<Product> productList = productService.showProductInfo(page);
		model.addAttribute("page", page);
		model.addAttribute("productList", productList);
		return "/admin/manage/tourline/erpTourlineInfo.ftl";
	}
	
	@RequestMapping(value="/synchronizeToERP",method = RequestMethod.POST)
	public @ResponseBody int synchronizeProduct(String productId){
		int result = productService.synchronizeProduct(productId);
		if(result == 1){
			return 1;
		}else{
			return 0;
		}
	}
}

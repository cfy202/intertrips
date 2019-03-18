package com.wenjing.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.dao.AttractionMapper;
import com.wenjing.dao.HotelMapper;
import com.wenjing.dao.NavigationMapper;
import com.wenjing.dao.PageMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.RegionMapper;
import com.wenjing.dao.SelfpayMapper;
import com.wenjing.dao.TourlineattractionMapper;
import com.wenjing.dao.TourlinedestinationMapper;
import com.wenjing.dao.TourlinehotelMapper;
import com.wenjing.dao.TourlineselfpayMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.AirportPickUp;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.HotTourline;
import com.wenjing.entity.Hotel;
import com.wenjing.entity.Image;
import com.wenjing.entity.Indexshowtourline;
import com.wenjing.entity.Page;
import com.wenjing.entity.Product;
import com.wenjing.entity.Region;
import com.wenjing.entity.Selfpay;
import com.wenjing.entity.ServiceItem;
import com.wenjing.entity.Showtourline;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tourlineattraction;
import com.wenjing.entity.Tourlinedestination;
import com.wenjing.entity.Tourlinehotel;
import com.wenjing.entity.Tourlineselfpay;
import com.wenjing.entity.Visa;
import com.wenjing.service.CoordinateService;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.HotTourlineService;
import com.wenjing.service.HotelService;
import com.wenjing.service.ImageService;
import com.wenjing.service.IndexShowTourlineService;
import com.wenjing.service.ItineraryService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.OrderService;
import com.wenjing.service.PageService;
import com.wenjing.service.ProductService;
import com.wenjing.service.RegionService;
import com.wenjing.service.ServiceItemService;
import com.wenjing.service.ShoppingCartService;
import com.wenjing.service.ShowTourlineService;
import com.wenjing.service.SliderService;
import com.wenjing.service.TagService;
import com.wenjing.service.TourDateService;
import com.wenjing.service.TourLineCalendarService;
import com.wenjing.service.TourlineImageService;
import com.wenjing.service.TourlineService;
import com.wenjing.service.VisaService;
import com.wenjing.service.VisaoccupationService;
import com.wenjing.util.FileUtil;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明		酒店后台管理controller	
 * @author xiejin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/hotel")
public class HotelController {

	@Resource
	private HotelService hotelService;
	@Resource
	private PageMapper pageMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private ProductService productService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private ImageService imageService;
	@Resource
	private SliderService sliderService;
	@Resource
	private NavigationService navigationService;
	@Resource
	private CurrencyService currencyService;
	@Resource
	private CostService costService;
	@Resource
	private RegionService regionService;
	@Resource
	private VisaoccupationService visaoccupationService;
	@Resource
	private PageService pageService;
	@Resource
	private ShowTourlineService showtourlineService;
	@Resource
	private IndexShowTourlineService indexshowtourlineService;
	@Resource
	private TagService tagService;
	@Resource
    private HotTourlineService hotTourlineService;
	@Resource 
	private DestinationService destinationService;
	
	
	/**
	 * 查询所有tourline
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model, HttpServletResponse response) {
		String costidss =hotelService.findAll(request, response, model);
        List<Cost> costlists = costService.findAll();
        String hott = null;
        String show = null;
        String index = null;
        String costNow = null;
        String isCreate = null;
        String costnumberId  = null;
        List costlistst = Tools.getCostNumber(request);
        if(costlistst!=null&&costlistst.size()==1){
        	costnumberId = costlistst.get(0).toString();
        	costNow = costnumberId;
        }else{
        	costnumberId = costidss;
        }
        if(costnumberId!=null){
        	List<HotTourline> hottourline = hotTourlineService.findByCostnumber(costnumberId);
        	for (HotTourline hotTourline2 : hottourline) {
        		hott+=","+hotTourline2.getTourlineId();
        	}
        	List<Showtourline> shotourline = showtourlineService.findByCostnumber(costnumberId,null);
        	for (Showtourline showtourline : shotourline) {
        		show +=","+showtourline.getTourlineid();
        	}
        	List<Showtourline> showtourlineIs = showtourlineService.findByCostnumber(costnumberId,2);
        	for (Showtourline showtourline : showtourlineIs) {
        		isCreate +=","+showtourline.getTourlineid();
        	}
        	List<Indexshowtourline> indxs = indexshowtourlineService.findByCostnumber(costnumberId);
        	for (Indexshowtourline indexshowtourline : indxs) {
        		index +=","+indexshowtourline.getTourlineid();
        	}
        }
        if(hott!=null){
        	hott = hott.substring(1);
        }
        if(show!=null){
        	show = show.substring(1);
        }
        if(index!=null){
        	index =index.substring(1);
        }
        if(isCreate!=null){
        	isCreate = isCreate.substring(1);
        }
        model.addAttribute("costlists",costlists);
        model.addAttribute("hott",hott);
        model.addAttribute("show",show);
        model.addAttribute("index",index);
        model.addAttribute("costNow",costNow);
        model.addAttribute("isCreate",isCreate);
		return "/admin/manage/hotel/hotel.ftl";
	}

	/**
	 * 根据id删除visa
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletResponse response,
			@Param("hotelId") String hotelId,
			@Param("productId") String productId,
			@Param("pageId") String pageId, Model model) {
		String message = hotelService.delete(hotelId, productId, pageId);
		WebUtils.addCookie(request, response, "message", message);
		return "redirect:/admin/hotel/list.do";
	}


	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute("hotel") Hotel hotel,
			@ModelAttribute("page") Page page,
			@ModelAttribute("product") Product product,
			@RequestParam("id") String id) {
		 int ss=0;
		    String destinationid = hotel.getDestinationid();
			Destination destination = destinationService.findById(destinationid);//获取酒店对应的城市
			hotel.setCity(destination.getNamecn());//设置酒店对应的城市中文名
		  List costlistst = Tools.getCostNumber(request);
	        if(costlistst!=null&&costlistst.size()==1){
	        	 ss = showtourlineService.updateByisCreate(costlistst.get(0).toString(), hotel.getId(), 1);
	        }
	     if(ss==0){
	    	 showtourlineService.save(costlistst.get(0).toString(), hotel.getId()); 
	     }
		if(product.getIsshow()==null){
			product.setIsshow((byte)0);
		}
		if(product.getIsshow()==null){
			product.setIshot((byte)0);
		}
		if(product.getIndexShow()==null){
			product.setIndexShow((byte)0);
		}
		product.setType(4);
		page.setType(4);
		if(hotel.getCostNumberids()==null||hotel.getCostNumberids().equals("")){
			hotel.setCostNumberids(hotel.getCostnumber());
		}
		String [] costidsa = hotel.getCostNumberids().split(",");
		for (String string : costidsa) {
			showtourlineService.updateByisCreate(string, hotel.getId(), 1);
		}
		Hotel hotel2 = hotelService.findById(id);
		boolean isus = productService.isExistUserCode(product.getCode());
		if (hotel2 != null && !hotel2.equals("")) {
			if (hotel2.getProductProductid().getCode().equals(product.getCode())) {
				hotelService.update(hotel,page,product);
			}else{
				if(isus){
					hotelService.update(hotel,page,product);
				}else{
					return "redirect:/admin/hotel/update.do?id="
							+ hotel.getId();
				}
			}
		} else {
			if(isus){
				hotelService.save(hotel,page,product);
			}else{
				return "redirect:/admin/hotel/add.do";	
			}
			
		}
		return "redirect:/admin/hotel/list.do";
	}

	/**
	 * 添加，修改签证
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Hotel hotel, Model model) {
		String id = hotel.getId();
		hotel = hotelService.findById(id);
		model.addAttribute("hotel", hotel);
		Product product = productMapper.selectByPrimaryKey(hotel.getProductid());
		Page page = pageMapper.selectByPrimaryKey(product.getPageid());
		model.addAttribute("product", product);
		model.addAttribute("page", page);
		List<Destination> destination = destinationService.findCityAndProvinceByCostNumber();
		model.addAttribute("destination", destination);
		String imageurl = product.getImageurl();
		if(imageurl!=null && !imageurl.equals("")){//判断景点是否有图片，并读出图片url
			imageurl = imageurl+",";
			String[] imgurl = imageurl.split(",");
			model.addAttribute("imgurl", imgurl);
			model.addAttribute("img", 1);
		}else{
			model.addAttribute("img", 0);
		}
		return "/admin/manage/hotel/hotelUpdate.ftl";
	}

	/**
	 * 添加签证
	 * 
	 * @param Tree
	 * @param model
	 * 
	 * @return sevens
	 */
	@RequestMapping("/add")
	public String add(Hotel hotel, Model model) {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (null != id && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		hotel.setId(id);
		Integer sort = hotelService.getMaxSort();
		hotel.setSort(sort+1);
		List<Destination> destination = destinationService.findCityAndProvinceByCostNumber();
		model.addAttribute("destination", destination);
		model.addAttribute("visa", hotel);
	   return "/admin/manage/hotel/hotelAdd.ftl";
	}
	
	/**
	 * 签证图片上传保存到图库
	 * @param picaddress
	 * @return
	 * xiejin
	 */
	@RequestMapping("/savepic")
	@ResponseBody
	public Image savepictoria(@RequestParam("picaddress") String picaddress,@Param("costnumber")String costnumber){
		//保存image
		String id = Tools.getUUID();
		Image image = new Image();
		image.setId(id);
		image.setUrl(picaddress);
		String[] name = picaddress.split("images/");
		image.setName(name[1]);
		String createtime = Tools.getTime();
		image.setCreatetime(createtime);
		image.setUsetype("visa");
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
		image.setCostnumber(costnumber);
		imageService.save(image);
		return image;
	}
	
	@RequestMapping("/view")
	public String view(Model model,@Param("id")String id){
		hotelService.view(model, id);
		return"/front/hotel/visa_usa.ftl";
	}
	
	@RequestMapping("/create")
	public String create(@RequestParam("tids") String[] tids) throws Exception {
		String costnumber = costService.findIdByCode("CNY");
		if (tids != null && tids.length > 0) {
			for (String string : tids) {
				hotelService.create(string, costnumber);
			}
		}
		return "redirect:/admin/hotel/list.do";
	}
	

}

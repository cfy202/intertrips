package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.dao.PageMapper;
import com.wenjing.dao.ProductMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Food;
import com.wenjing.entity.HotTourline;
import com.wenjing.entity.Hotel;
import com.wenjing.entity.Image;
import com.wenjing.entity.Indexshowtourline;
import com.wenjing.entity.Page;
import com.wenjing.entity.Product;
import com.wenjing.entity.Showtourline;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.FoodService;
import com.wenjing.service.HotTourlineService;
import com.wenjing.service.ImageService;
import com.wenjing.service.IndexShowTourlineService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.PageService;
import com.wenjing.service.ProductService;
import com.wenjing.service.RegionService;
import com.wenjing.service.ShowTourlineService;
import com.wenjing.service.SliderService;
import com.wenjing.service.TagService;
import com.wenjing.service.VisaoccupationService;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明		酒店后台管理controller	
 * @author xiejin
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/food")
public class FoodController {

	@Resource
	private FoodService foodService;
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
		String costidss =foodService.findAll(request, response, model);
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
		return "/admin/manage/food/food.ftl";
	}

	/**
	 * 根据id删除food
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletResponse response,
			@Param("foodId") String foodId,
			@Param("productId") String productId,
			@Param("pageId") String pageId, Model model) {
		String message = foodService.delete(foodId, productId, pageId);
		WebUtils.addCookie(request, response, "message", message);
		return "redirect:/admin/food/list.do";
	}


	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute("food") Food food,
			@ModelAttribute("page") Page page,
			@ModelAttribute("product") Product product,
			@RequestParam("id") String id) {
		 int ss=0;
		    String destinationid = food.getDestinationid();
			Destination destination = destinationService.findById(destinationid);//获取酒店对应的城市
			food.setCity(destination.getNamecn());//设置酒店对应的城市中文名
		  List costlistst = Tools.getCostNumber(request);
	        if(costlistst!=null&&costlistst.size()==1){
	        	 ss = showtourlineService.updateByisCreate(costlistst.get(0).toString(), food.getId(), 1);
	        }
	     if(ss==0){
	    	 showtourlineService.save(costlistst.get(0).toString(), food.getId()); 
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
		if(food.getCostnumberids()==null||food.getCostnumberids().equals("")){
			food.setCostnumberids(food.getCostnumber());
			
		}
		String [] costidsa = food.getCostnumberids().split(",");
		for (String string : costidsa) {
			showtourlineService.updateByisCreate(string, food.getId(), 1);
		}
		Food food2 = foodService.findById(id);
		boolean isus = productService.isExistUserCode(product.getCode());
		if (food2 != null && !food2.equals("")) {
			if (food2.getProductProductid().getCode().equals(product.getCode())) {
				foodService.update(food,page,product);
			}else{
				if(isus){
					foodService.update(food,page,product);
				}else{
					return "redirect:/admin/food/update.do?id="
							+ food.getId();
				}
			}
		} else {
			if(isus){
				foodService.save(food,page,product);
			}else{
				return "redirect:/admin/food/add.do";	
			}
			
		}
		return "redirect:/admin/food/list.do";
	}

	/**
	 * 添加，美食
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Food food, Model model) {
		String id = food.getId();
		 food = foodService.findById(id);
		model.addAttribute("food", food);
		Product product = productMapper.selectByPrimaryKey(food.getProductid());
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
		return "/admin/manage/food/foodUpdate.ftl";
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
	public String add(Food food, Model model) {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (null != id && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		food.setId(id);
		Integer sort = foodService.getMaxSort();
		food.setSort(sort+1);
		List<Destination> destination = destinationService.findCityAndProvinceByCostNumber();
		model.addAttribute("destination", destination);
		model.addAttribute("food", food);
	   return "/admin/manage/food/foodAdd.ftl";
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
		image.setUsetype("food");
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
		image.setCostnumber(costnumber);
		imageService.save(image);
		return image;
	}
	
	@RequestMapping("/view")
	public String view(Model model,@Param("id")String id){
		foodService.view(model, id);
		return"/front/hotel/visa_usa.ftl";
	}
	
	@RequestMapping("/create")
	public String create(@RequestParam("tids") String[] tids) throws Exception {
		String costnumber = costService.findIdByCode("CNY");
		if (tids != null && tids.length > 0) {
			for (String string : tids) {
				foodService.create(string, costnumber);
			}
		}
		return "redirect:/admin/hotel/list.do";
	}
	

}

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
import com.wenjing.entity.HotTourline;
import com.wenjing.entity.Image;
import com.wenjing.entity.Indexshowtourline;
import com.wenjing.entity.Page;
import com.wenjing.entity.Product;
import com.wenjing.entity.Showtourline;
import com.wenjing.entity.Visa;
import com.wenjing.service.CostService;
import com.wenjing.service.CurrencyService;
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
import com.wenjing.service.VisaService;
import com.wenjing.service.VisaoccupationService;
import com.wenjing.util.Tools;

/**
 * 类说明 后台目录管理controller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/visa")
public class VisaController {

	@Resource
	private VisaService visaService;
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
	
	/**
	 * 查询所有visa
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model,HttpServletResponse response) {
		String costidss =visaService.findAll(request, response, model);
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
		return "/admin/manage/visa/visa.ftl";
	}

	/**
	 * 根据id删除visa
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("productId")String productId) {
		productService.updateWithShow(productId,0);
		return "redirect:/admin/visa/list.do";
	}


	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute("visa") Visa visa,
			@ModelAttribute("page") Page page,
			@ModelAttribute("product") Product product,
			@RequestParam("id") String id) {
		 int ss=0;
		  List costlistst = Tools.getCostNumber(request);
	        if(costlistst!=null&&costlistst.size()==1){
	        	 ss = showtourlineService.updateByisCreate(costlistst.get(0).toString(), visa.getId(), 1);
	        }
	     if(ss==0){
	    	 showtourlineService.save(costlistst.get(0).toString(), visa.getId()); 
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
		product.setType(2);
		page.setType(2);
		if(visa.getCostnumberids()==null||visa.getCostnumberids().equals("")){
			visa.setCostnumberids(visa.getCostnumber());
		}
		String [] costidsa = visa.getCostnumberids().split(",");
		for (String string : costidsa) {
			showtourlineService.updateByisCreate(string, visa.getId(), 1);
		}
		Visa visa2 = visaService.findById(id);
		boolean isus = productService.isExistUserCode(product.getCode());
		if (visa2 != null && !visa2.equals("")) {
			if (visa2.getProductProductid().getCode().equals(product.getCode())) {
				visaService.update(visa,page,product);
			}else{
				if(isus){
				  visaService.update(visa,page,product);
				}else{
					return "redirect:/admin/visa/update.do?id="
							+ visa.getId();
				}
			}
		} else {
			if(isus){
			 visaService.save(visa,page,product);
			}else{
				return "redirect:/admin/visa/add.do";	
			}
			
		}
		return "redirect:/admin/visa/list.do";
	}

	/**
	 * 添加，修改签证
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Visa visa, Model model) {
		String id = visa.getId();
		visa = visaService.findById(id);
		model.addAttribute("visa", visa);
		Product product = productMapper.selectByPrimaryKey(visa.getProductid());
		Page page = pageMapper.selectByPrimaryKey(product.getPageid());
		model.addAttribute("product", product);
		model.addAttribute("page", page);
		String imageurl = product.getImageurl();
		if(imageurl!=null && !imageurl.equals("")){//判断景点是否有图片，并读出图片url
			imageurl = imageurl+",";
			String[] imgurl = imageurl.split(",");
			model.addAttribute("imgurl", imgurl);
			model.addAttribute("img", 1);
		}else{
			model.addAttribute("img", 0);
		}
		return "/admin/manage/visa/visaUpdate.ftl";
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
	public String add(Visa visa, Model model) {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (null != id && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		visa.setId(id);
		Integer sort = visaService.getMaxSort();
		visa.setSort(sort+1);
		model.addAttribute("visa", visa);
		

		return "/admin/manage/visa/visaAdd.ftl";
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
		visaService.view(model, id);
		return"/front/visa/visa_usa.ftl";
	}
	
	@RequestMapping("/create")
	public String create(@RequestParam("tids") String[] tids) throws Exception {
		String costnumber = costService.findIdByCode("CNY");
		if (tids != null && tids.length > 0) {
			for (String string : tids) {
				visaService.create(string, costnumber);
			}
			
		}
		return "redirect:/admin/visa/list.do";
	}
	
}

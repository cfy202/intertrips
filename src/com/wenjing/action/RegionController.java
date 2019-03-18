package com.wenjing.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.dao.NavigationMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Image;
import com.wenjing.entity.Page;
import com.wenjing.entity.Promotion;
import com.wenjing.entity.Region;
import com.wenjing.service.CostService;
import com.wenjing.service.ImageService;
import com.wenjing.service.NavigationService;
import com.wenjing.service.PageService;
import com.wenjing.service.PromotionService;
import com.wenjing.service.RegionService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.Tools;

/**
 * 类说明 产品分类管理controller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/region")
public class RegionController {

	@Resource
	private HttpServletRequest request;

	@Resource
	private RegionService regionService;

	@Resource
	private CostService costService;
	
	@Resource
	private PageService pageService;
	
	@Resource
	private NavigationService navigationService;
	
	@Resource
	private NavigationMapper navigationMapper;
	
	@Resource
	private PromotionService promotionService;
	@Resource
	private TourlineService tourlineService;
	@Resource
	private ImageService imageService;

	/**
	 * 根据costnumber查询所有attractions
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model,HttpServletResponse response) {
		List<Region> region = regionService.findAll( request, response, model);
		model.addAttribute("region", region);
		return "/admin/manage/region/region.ftl";
	}

	/**
	 * 根据id删除region
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		regionService.delete(id);
		return "redirect:/admin/region/list.do";
	}

	/**
	 * 保存region
	 * 
	 * @param region
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/save")
	public String save(Region region, Page page, @RequestParam("id") String id) {
		region.setId(id);
		Integer isShow = region.getIsShow();
		if (isShow==null) {
			region.setIsShow(0);
		}
		Region oldregion = regionService.findById(id);
		if (oldregion != null) {
			regionService.update(region, oldregion, page);
		} else {
			regionService.save(region, page);
		}
		return "redirect:/admin/region/list.do";

	}

	/**
	 * 添加，修改region
	 * 
	 * @param region
	 * @param model
	 * @param costnumber
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Region region, Model model) {
		String id = region.getId();
		region = regionService.findRgionAndPageById(id);
		model.addAttribute("region", region);
		List<Cost> cosList = costService.findAll();
		List<Region> regionlist = regionService.findNotContainSon(region);
		model.addAttribute("regionlist", regionlist);
		model.addAttribute("cosList", cosList);
		Page page = pageService.findById(region.getPageid());
		String imageurl = page.getImageurl();
		if(imageurl!=null && !imageurl.equals("")){//判断景点是否有图片，并读出图片url
			imageurl = imageurl+",";
			String[] imgurl = imageurl.split(",");
			model.addAttribute("imgurl", imgurl);
			model.addAttribute("img", 1);
		}else{
			model.addAttribute("img", 0);
		}
		return "/admin/manage/region/regionUpdate.ftl";
	}

	/**
	 * 添加region
	 * 
	 * @param region
	 * @param model
	 * @param costnumber
	 * @return sevens
	 */
	@RequestMapping("/add")
	public String add(Region region, Model model) {
		String id = Tools.getUUID();// 产生uuid
		region.setId(id);
		String costnum = Tools.getCostNumber(request).get(0);
		model.addAttribute("region", region);
		List<Region> regionlist = regionService.findByUpIdAndCostid(1,costnum); // 根据运营中心costnumber,默认查询第一个运营中心,且类型为线路的上级分类
		model.addAttribute("regionlist", regionlist);
		return "/admin/manage/region/regionAdd.ftl";
	}

	
	
	/**
	 * 异步获取orderid最大值和上级导航
	 */
	@RequestMapping("/getsort")
	@ResponseBody
	public Map<String, Object> getSort(@RequestParam("type") Integer type,@RequestParam("costnumber") String costnumber) {
		Map<String, Object> root = new HashMap<String, Object>();
		List<Region> regions = regionService.findByUpIdAndCostid(type,costnumber);
		root.put("regions", regions);
		return root;
	}
	
	/**
	 * @Title isExistsUrl
	 * @Description 异步校验url是否存在
	 * @Author Bowden
	 * @CreateDate 2015-8-19 下午3:32:53
	 */
	@RequestMapping("/isExistsUrl")
	@ResponseBody
	public boolean isExistsUrl(@RequestParam("url") String url, @RequestParam(value="id", required=false) String id){
		boolean flag = false;
		String currentUrl = "";
		if(id != null && !"".equals(id)){ // 为分类修改
			Region currentRegion = regionService.findById(id);
			currentUrl = currentRegion.getUrl();
		}
		if(url!=null && !"".equals(url)){
			url = url.trim();
			if(currentUrl != null && !"".equals(currentUrl) && currentUrl.equals(url)){
				flag = true;
			}else {
				Region region = regionService.findByUrl(url);
				if(region ==null){
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 向上移动
	 */
	@RequestMapping("/up")
	public String moveUp(@RequestParam("id") String id, final RedirectAttributes rAttributes) {
        boolean flag = regionService.moveUp(id);
        if(!flag){
        	rAttributes.addFlashAttribute("message", "无法向上移动，请选择正确操作");
        }
		return "redirect:/admin/region/list.do";
	}

	/**
	 * 向下移动
	 */
	@RequestMapping("/down")
	public String moveDown(@RequestParam("id") String id, final RedirectAttributes rAttributes) {
		boolean flag = regionService.moveDown(id);
		if(!flag){
        	rAttributes.addFlashAttribute("message", "无法向下移动，请选择正确操作");
        }
		return "redirect:/admin/region/list.do";
	}
	
	/**
	 * 景点图片上传保存到图库
	 * @param picaddress
	 * @return
	 * xiejin
	 */
	@RequestMapping("/savepic")
	@ResponseBody
	public Image savepictoria(@RequestParam("picaddress") String picaddress,
			@RequestParam("namecn") String namecn){
		//保存image
		String id = Tools.getUUID();
		Image image = new Image();
		image.setId(id);
		image.setUrl(picaddress);
		String[] name = picaddress.split("images/");
		image.setName(name[1]);
		String createtime = Tools.getTime();
		image.setCreatetime(createtime);
		image.setUsetype("page");
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
		image.setTitle("page");
		imageService.save(image);
		return image;
	}
	
	@RequestMapping("/typeview")
	public String typeview(@RequestParam("id") String id, Model model) {

		if (id != null) {
			Region regions = null;
			regions = regionService.findById(id);	
			String costnumber = regions.getCostnumber();
			Cost cost = costService.findById(costnumber);					//线路对应的销售中心
			model.addAttribute("frontCode", cost.getCode());
			pageService.getNavigation(model, costnumber);					//上，下导航
			Destination destination = new Destination();
			
			String destinationName = request.getParameter("destination");		//定义首页目的地名称
			if (destinationName!= null) {
				try {
					destinationName = java.net.URLDecoder.decode(destinationName, "utf-8");	//获取首页点击景点获得的目的地名称
					destinationName = new String(destinationName.getBytes("ISO-8859-1"),"utf-8");
					destinationName = destinationName.replaceAll("\'", "");		//去除单引号
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			String keyword = request.getParameter("keyword");	//定义搜索关键字名称
			if (keyword!= null) {
				keyword = keyword.replaceAll("\'", "");		//去除单引号

			}
					//查询分类url对应的分类id
				if(regions.getType()==6){
					destinationName = regions.getName();
	        	}
	       
	   		
			List<Region> regionList = regionService.findByCostnumber(1, costnumber);	//产品分类
			model.addAttribute("regionList", regionList);
			//销售中心促销活动id，title
			List<Promotion> promotionList = promotionService.getPartByCostnumber(costnumber, Tools.getDtimestemp(Tools.getDtime()));
			model.addAttribute("promotionList", promotionList);
			//二级页面线路分页和筛选条件展示
			if(destinationName==null|| "".equals(destinationName)){
				tourlineService.findByShowRegionid(request, model, costnumber, destinationName, null, null, "", keyword, null, null, null, id, 12, null);
			}else{
				tourlineService.findByShowRegionid(request, model, costnumber, destinationName, null, null, "", keyword, null, null, null, null, 12, null);
			}
			Page page = pageService.findById(regions.getPageid());
			destination.setName(destinationName);
			model.addAttribute("costnumber",costnumber);
			model.addAttribute("destination",destination);
			model.addAttribute("keyword",keyword);
			model.addAttribute("regioId",id);
			model.addAttribute("tourListPage",page);
			
		}
		return"/front/tourlineList.ftl";
	}

	@RequestMapping("/tourlineRegionCreate")
	public String createTourlineRegion(@RequestParam("tids") String[] tids)
			throws Exception {
		if (tids != null && tids.length > 0) {
			for (String string : tids) {
				tourlineRegionCreate(string);
			}
		}
		return "redirect:/admin/region/list.do";
	}

	public void tourlineRegionCreate(String id) {
		Map<String, Object> root = new HashMap<String, Object>();
		if (id != null) {
            Region region = regionService.findById(id);
            String costnumber = region.getCostnumber();
            pageService.getNavigationCreate(root, costnumber);//上，下导航
            Destination destination = new Destination();
			String destinationName = request.getParameter("destination");		//定义首页目的地名称
			if (destinationName!= null) {
				try {
					destinationName = java.net.URLDecoder.decode(destinationName, "utf-8");	//获取首页点击景点获得的目的地名称
					destinationName = new String(destinationName.getBytes("ISO-8859-1"),"utf-8");
					destinationName = destinationName.replaceAll("\'", "");		//去除单引号
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			String keyword = request.getParameter("keyword");	//定义搜索关键字名称
			if (keyword!= null) {
				keyword = keyword.replaceAll("\'", "");		//去除单引号

			}
			
			Region regions = null;
			regions = regionService.findById(id);		//查询分类url对应的分类id
			if(regions.getType()==6){
				destinationName = regions.getName();
        	}
			List<Region> regionList = regionService.findByCostnumber(1, costnumber);	//产品分类
			root.put("regionList", regionList);
			//销售中心促销活动id，title
			List<Promotion> promotionList = promotionService.getPartByCostnumber(costnumber, Tools.getDtimestemp(Tools.getDtime()));
			root.put("promotionList", promotionList);
			Page page = pageService.findById(regions.getPageid());
			//二级页面分类信息
//			Region regionlast = regionService.findById(regionid);
//			Page page = pageService.findById(regionlast.getPageid());
//			List<Region> regionlist = new ArrayList<Region>();
//			regionlist = regionService.getRegionidList(regionid,regionlist);
//			model.addAttribute("regionPage", page);
//			model.addAttribute("regionlast", regionlast);
//			model.addAttribute("regionlist", regionlist);
			
			
			//二级页面线路分页和筛选条件展示
//			model.addAttribute("regionUrl",regionUrl);
			if(destinationName==null|| "".equals(destinationName)){
				tourlineService.findByShowRegionidCreate(request, costnumber, destinationName, null, null, null, keyword, null, null, null, id, 12, null, root);
			}else{
				tourlineService.findByShowRegionidCreate(request, costnumber, destinationName, null, null, null, keyword, null, null, null, null, 12, null, root);
			}
			destination.setName(destinationName);
			root.put("costnumber",costnumber);
			root.put("destination",destination);
			root.put("keyword",keyword);
			root.put("regioId",id);
			root.put("tourListPage",page);
			root.put("request", request);
			if (region.getUrl() != null) {

				// 静态页面的完整路径
				String str = request.getSession().getServletContext()
						.getRealPath("/")
						+ region.getUrl();

				File file = new File(str);
				// 如果静态文件存在，则删除之前的静态页面，重新生成新的静态页面
				if (file.isFile() && file.exists()) {
					file.delete();
				}
				str = null;// 释放资源
				if (file != null) {
					file = null;
				}
				FreemarkerUtils.createHTML(request.getSession()
						.getServletContext(), root, "/front/tourlineList.ftl",
						region.getUrl());
			}
		}

	}
	
	
}

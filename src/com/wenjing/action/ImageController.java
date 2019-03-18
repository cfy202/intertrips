package com.wenjing.action;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.Setting.WatermarkPosition;
import com.wenjing.dao.ImageMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Image;
import com.wenjing.service.AttractionimageService;
import com.wenjing.service.CostService;
import com.wenjing.service.FileService;
import com.wenjing.service.ImageService;
import com.wenjing.service.ItineraryImageService;
import com.wenjing.service.PageImageService;
import com.wenjing.service.TourlineImageService;
import com.wenjing.util.ImageUtils;
import com.wenjing.util.Tools;


@RequestMapping("/admin/image")
@Controller
public class ImageController {

	@Resource
	private ImageService imageService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private CostService costService;
	@Resource
	private ImageMapper imageMapper;
	@Resource
	private AttractionimageService attractionimageService;
	@Resource
	private ItineraryImageService itineraryImageService;
	@Resource
	private PageImageService pageImageService;
	@Resource
	private TourlineImageService tourlineImageService;
	@Resource
	private FileService fileService;
	
	/**
	 * 根据costnumber分页查询所有image 
	 * @return
	 * xiejin
	 */
	@RequestMapping("/list")
	public String findAll(Model model,HttpServletResponse response){
		String usetype = request.getParameter("usetype");
//		List<String> costnumber = Tools.getCostNumber(request);
		if (usetype==null || "".equals(usetype)) {
			usetype="all";
		}
		imageService.findAllByCostNumber(request,response, model,usetype);
		model.addAttribute("usetype",usetype);
		return "/admin/manage/image/image.ftl";
	}
	
	
	/**
	 * 根据id删除image
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		attractionimageService.deleteByImageId2(id);
		itineraryImageService.deleteByImageId2(id);
		pageImageService.deleteByImageId2(id);
		tourlineImageService.deleteByImageId2(id);
		Image image = imageService.findById(id);
		String imageUrl = "";
		if (image != null) {
			imageUrl = image.getUrl();
		}
		fileService.deleteAttachment(imageUrl, request);	//删除图片源文件
		imageService.delete(id);
		return "redirect:/admin/image/list.do";
	}
	
	/**
	 * 图片信息修改后保存
	 * @param image
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/saveimage")
	public String save(Image image ,Model model){
		imageService.update(image);
		return "redirect:/admin/image/list.do";
	}

	/**
	 * 修改image
	 * @param image
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(@RequestParam("id") String id, Model model) {
		Image image1 = imageService.findById(id);
		model.addAttribute("image1", image1);
//		Cost costlist = costService.findById(image1.getCostnumber());
//		model.addAttribute("costlist", costlist);
		return "/admin/manage/image/imageUpdate.ftl";
	}	
	
	/**
	 * 添加image
	 * @param image
	 * @param model
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(Image image, Model model){
//		List<String> costnumber = Tools.getCostNumber(request);
//		List<Cost> costlist = new ArrayList<Cost>();
//		for (String costnumber2 : costnumber) {
//			Cost cost = costService.findById(costnumber2);
//			costlist.add(cost);
//		}
//		model.addAttribute("costlist", costlist);
		return "/admin/manage/image/imageAdd.ftl";
	}
	
	/**
	 * @Title: addsave
	 * @Description: 上传图片时保存
	 * @param image
	 * @param url
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@RequestMapping("/addsave")
	public String addsave(Image image,@RequestParam("url") String[] url){
		for (int i = 0; i < url.length; i++) {
			if(!url[i].equals("")){
				image.setUrl(url[i]);
				String[] name = url[i].split("images/");
				image.setName(name[1]);
				String id = Tools.getUUID();
				image.setId(id);
				String createtime = Tools.getTime();
				image.setCreatetime(createtime);
				Admin admin = (Admin)request.getSession().getAttribute("admin");
				image.setOpuser(admin.getUsername());
				imageService.save(image);
			}
		}
		return "redirect:/admin/image/list.do";
	}
	/**
	 * 图片批量水印处理
	 * @author Sevens
	 * 时间2015-7-22
	 * @return
	 */
	@RequestMapping("addWatermark")
	public String addWatermark(@RequestParam("imageids") String[] imageids){
		String path =  request.getSession().getServletContext().getRealPath("/");
		if(imageids!=null&&imageids.length>0){
			for (String string : imageids) {
				Image image = imageMapper.selectByPrimaryKey(string);
				try {
					ImageUtils.addWatermark(new File(path +image.getUrl()), 
							new File(path+image.getUrl()),
							new File(path+ "/assets/images/logo-wenjing-small.png"), WatermarkPosition.topLeft, 37);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return "redirect:/admin/image/list.do";	
	}
}
package com.wenjing.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenjing.dao.ProductMapper;
import com.wenjing.dao.SelfpayMapper;
import com.wenjing.dao.TourlineattractionMapper;
import com.wenjing.dao.TourlinedestinationMapper;
import com.wenjing.dao.TourlinehotelMapper;
import com.wenjing.entity.Admin;
import com.wenjing.entity.Attraction;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Hotel;
import com.wenjing.entity.Image;
import com.wenjing.entity.Itinerary;
import com.wenjing.entity.Itineraryimage;
import com.wenjing.entity.Selfpay;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tourlinedestination;
import com.wenjing.entity.Tourlinehotel;
import com.wenjing.service.AttractionService;
import com.wenjing.service.AttractionimageService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.HotelService;
import com.wenjing.service.ImageService;
import com.wenjing.service.ItineraryImageService;
import com.wenjing.service.ItineraryService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.Tools;

/**
 * 类说明		线路行程管理controller
 * @author xiejin
 * @date 2015-5-8
 */
@Controller
@RequestMapping("/admin/itinerary")
public class ItineraryController {

	@Resource 
	private DestinationService destinationService;
	@Resource
	private ImageService imageService;
	@Resource
	private ItineraryService itineraryService;
	@Resource
	private ItineraryImageService itineraryImageService;
	@Resource
	private TourlineService tourlineService;
	@Resource
	private HttpServletRequest request;
	@Resource
	private AttractionService attractionService;
	@Resource
	private AttractionimageService attractionimageService;
	@Resource
	private TourlinehotelMapper tourlinehotelMapper;
	@Resource
	private HotelService hotelService;
	@Resource
	private TourlinedestinationMapper tourlinedestinationMapper;
	@Resource
	private TourlineattractionMapper tourlineattractionMapper;
	@Resource
	private SelfpayMapper selfpayMapper;
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 根据tourlineid查询所有itinerary
	 * @param model
	 * @param tourlineid
	 * @return
	 * xiejin
	 */	
	@RequestMapping("/list")
	public String findAll(Model model,@RequestParam("tourlineId") String tourlineid){
		List<Itinerary> itinerary = itineraryService.findByTourlineId(tourlineid);		
		model.addAttribute("itinerary", itinerary);
		model.addAttribute("tourlineId", tourlineid);
		Tourline tourline2 = tourlineService.findById(tourlineid);	
		String tourname = tourline2.getTourname();
		model.addAttribute("tourname", tourname);
		return "/admin/manage/itinerary/itinerary.ftl";
	}
	
	/**
	 * 根据id删除行程
	 * @param id
	 * @return
	 * xiejin
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		Itinerary itinerary = itineraryService.findById(id);
		String tourlineId = itinerary.getTourlineid();
		itineraryImageService.deleteByItineraryId(id);	//删除关系表
		itineraryService.delete(id);
		productMapper.setProductUnsynchronizeByTourlineId(tourlineId);
		return "redirect:/admin/itinerary/list.do?tourlineId="+tourlineId;
	}

	/**
	 * 修改后保存itinerary和行程图片联系表
	 * @param itinerary
	 * @param id
	 * @param imageid
	 * @return
	 * xiejin
	 */
	@RequestMapping("/updatesave")
	public String updatesave(Itinerary itinerary,@RequestParam("id") String id,@RequestParam("imageid") String[] imageid) {
		String tourlineId = itinerary.getTourlineid();
		itinerary.setId(id);
		itinerary.setUpdatetime(new Date());
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		itinerary.setEditor(admin.getUsername());
		String [] strs = request.getParameterValues("itintarySids");
		String itintarySids = null;
		StringBuffer buf = new StringBuffer();
		if(strs!=null&&strs.length>0){
			for (String string : strs) {
				buf.append(","+string);
			}
			itintarySids = buf.substring(1);
		}
		itinerary.setItintarySids(itintarySids);
		productMapper.setProductUnsynchronizeByTourlineId(tourlineId);
		//保存行程
		itineraryService.update(itinerary);
		//保存行程图片联系表
		if(imageid !=null && !imageid.equals("")){ 				//判断是否选择或上传图片
			for (int i = 0; i < imageid.length; i++) {			
				if(!imageid[i].equals("")){						//去除为""的imageid
					String id1 = Tools.getUUID();
					Itineraryimage itineraryimage = new Itineraryimage();
					itineraryimage.setId(id1);
					itineraryimage.setItinid(id);
					itineraryimage.setImageid(imageid[i]);
					itineraryImageService.save(itineraryimage);
				}
			}		
		}
		return "redirect:/admin/itinerary/list.do?tourlineId="+tourlineId;
	}
	
	/**
	 * 添加行程后保存itinerary和行程图片联系表
	 * @param itinerary
	 * @param id
	 * @param imageid
	 * @return
	 * xiejin
	 */
	@RequestMapping("/addsave")
	public String addsave(Itinerary itinerary,@RequestParam("id") String id,@RequestParam("imageid") String[] imageid) {
		String tourlineId = itinerary.getTourlineid();
		itinerary.setId(id);
		itinerary.setUpdatetime(new Date());
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		itinerary.setEditor(admin.getUsername());
		String [] strs = request.getParameterValues("itintarySids");
		String itintarySids = null;
		StringBuffer buf = new StringBuffer();
		if(strs!=null&&strs.length>0){
			for (String string : strs) {
				buf.append(","+string);
			}
			itintarySids = buf.substring(1);
		}
		itinerary.setItintarySids(itintarySids);
		productMapper.setProductUnsynchronizeByTourlineId(tourlineId);
		//保存行程
		itineraryService.save(itinerary);
		//保存行程图片联系表
		if(imageid !=null && !imageid.equals("")){ 				//判断是否选择或上传图片
			for (int i = 0; i < imageid.length; i++) {			
				if(!imageid[i].equals("")){						//去除为""的imageid
					String id1 = Tools.getUUID();
					Itineraryimage itineraryimage = new Itineraryimage();
					itineraryimage.setId(id1);
					itineraryimage.setItinid(id);
					itineraryimage.setImageid(imageid[i]);
					itineraryImageService.save(itineraryimage);
				}
			}		
		}
		return "redirect:/admin/itinerary/add.do?tourlineId="+tourlineId;
	}
	
	/**
	 * 修改itinerary
	 * @param itinerary
	 * @param model
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@RequestMapping("/update")
	public String update(Itinerary itinerary, Model model) {
		String id = itinerary.getId();			
		itinerary = itineraryService.findById(id);
		model.addAttribute("itinerary", itinerary);
		Tourline tourline2 = tourlineService.findById(itinerary.getTourlineid());		
//		String costnumber = tourline2.getProductProductid().getCostnumber();		//获得行程所对应线路的运营中心
		List<String> desids = new ArrayList<String>();
		List<Tourlinedestination> destinationlist = tourlinedestinationMapper.selectByTourlineid(itinerary.getTourlineid());
	   
		if(destinationlist!=null&&destinationlist.size()>0){
			for (Tourlinedestination tourlinedestination : destinationlist) {
				desids.add(tourlinedestination.getDestinationid());
			}
			if(desids!=null){
				List<Destination> destination = destinationService.selectByids(desids);	
				model.addAttribute("destination", destination);
				
			}
		}
		if(itinerary.getItintaryDids()!=null){
			List<Selfpay> selfpays = selfpayMapper.findWithD(Arrays.asList(itinerary.getItintaryDids().split(",")));
			model.addAttribute("selfpays",selfpays);
		}
		if(itinerary.getItintaryDids()!=null){
			List<Attraction> attractions = attractionService.selectByDestinationids(Arrays.asList(itinerary.getItintaryDids().split(",")),itinerary.getTourlineid());
			model.addAttribute("attractions",attractions);
		}
		List<Tourlinehotel> tourlinehotel = tourlinehotelMapper.selectByTourlineid(itinerary.getTourlineid());
		
		if(tourlinehotel!=null&&tourlinehotel.size()>0){
			List<String> hotelids = new ArrayList<String>();
			for (Tourlinehotel tourlinehotel2 : tourlinehotel) {
				hotelids.add(tourlinehotel2.getHotelid());
			}
			if(hotelids!=null){
				List<Hotel> hotels = hotelService.selectByids(hotelids);
				model.addAttribute("hotels",hotels);
			}
		}
		
//		model.addAttribute("costnumber", costnumber);
		String tourname = tourline2.getTourname();
		model.addAttribute("tourname", tourname);
		String imageurl = itinerary.getImageurl();
		if(imageurl!=null && !imageurl.equals("")){//判断行程是否有图片，并读出图片url
			imageurl = imageurl+",";
			String[] imgurl = imageurl.split(",");
			model.addAttribute("imgurl", imgurl);
			model.addAttribute("img", 1);
		}else{
			model.addAttribute("img", 0);
		}
		String tourlineId = itinerary.getTourlineid();
		Tourline tourline = tourlineService.findById(tourlineId);
		Integer days = tourline.getDays();		//获得行程所对应线路的天数
		if(days!=null && !days.equals("")){
			List<Integer> daylist = new ArrayList<Integer>();
			for (int i = 1; i < days+1; i++) {
				daylist.add(i);
			}
			model.addAttribute("daylist", daylist);
		}
		model.addAttribute("tourlineId", tourlineId);
		return "/admin/manage/itinerary/itineraryUpdate.ftl";
	}
	
	/**
	 * 添加itinerary
	 * @param itinerary
	 * @param model
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@RequestMapping("/add")
	public String add(@RequestParam("tourlineId") String tourlineId ,Model model){
		Tourline tourline = tourlineService.findById(tourlineId);
		Integer days = tourline.getDays();		//获得行程所对应线路的天数
		List<Itinerary> itinerary = itineraryService.findByTourlineId(tourlineId);
		List<Integer> daylist = new ArrayList<Integer>();
		if(itinerary!=null && !itinerary.equals("")){		//判断是否已添加行程
			for (int i = 1; i < days+1; i++) {		//循环给daylist添加行程天
				daylist.add(i);
			}
			for (Itinerary itinerary2 : itinerary) {		//循环移除已经添加的行程天
				Integer day = itinerary2.getDay();
				daylist.remove(day);
			}
		}else{
			for (int i = 1; i < days+1; i++) {
				daylist.add(i);
			}
		}
		if(daylist.size()!=0){		//判断移除后的daylist长度是否为0
			String id = Tools.getUUID();
			model.addAttribute("id", id);
			model.addAttribute("tourlineId", tourlineId);
			Tourline tourline2 = tourlineService.findById(tourlineId);	
			List<String> desids = new ArrayList<String>();
			List<String> hotelids = new ArrayList<String>();
			List<Tourlinedestination> destinationlist = tourlinedestinationMapper.selectByTourlineid(tourlineId);
			if(destinationlist!=null&&destinationlist.size()>0){
				for (Tourlinedestination tourlinedestination : destinationlist) {
					desids.add(tourlinedestination.getDestinationid());
				}
				if(desids!=null){
					List<Destination> destination = destinationService.selectByids(desids);	
					model.addAttribute("destination", destination);
					
				}
			}
			
			List<Tourlinehotel> tourlinehotel = tourlinehotelMapper.selectByTourlineid(tourlineId);
			if(tourlinehotel!=null&&tourlinehotel.size()>0){
				for (Tourlinehotel tourlinehotel2 : tourlinehotel) {
					hotelids.add(tourlinehotel2.getHotelid());
				}
				if(hotelids!=null){
					List<Hotel> hotels = hotelService.selectByids(hotelids);
					model.addAttribute("hotels",hotels);
				}
			}
			
//			String costnumber = tourline2.getProductProductid().getCostnumber();		//获得行程所对应线路的运营中心
			int daymax = itineraryService.getMaxDay(tourlineId);		//获得行程最大天
			daymax+=1;
			String tourname = tourline2.getTourname();
			model.addAttribute("tourname", tourname);
//			model.addAttribute("costnumber", costnumber);
			model.addAttribute("daymax", daymax);
			model.addAttribute("daylist", daylist);
			
			return "/admin/manage/itinerary/itineraryAdd.ftl";
		}else{
			return "redirect:/admin/itinerary/list.do?tourlineId="+tourlineId;
		}
	}
	
	/**
	 * 行程图片上传保存到图库
	 * @param picaddress
	 * @return
	 * xiejin
	 */
	@RequestMapping("/savepic")
	@ResponseBody
	public Image savepictoria(@RequestParam("picaddress") String picaddress){
		//保存image
		String id = Tools.getUUID();
		Image image = new Image();
		image.setId(id);
		image.setUrl(picaddress);
		String[] name = picaddress.split("images/");
		image.setName(name[1]);
		String createtime = Tools.getTime();
		image.setCreatetime(createtime);
		image.setUsetype("itinerary");
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		image.setOpuser(admin.getUsername());
//		image.setCostnumber(costnumber);
		imageService.save(image);
		return image;
	}
	
	/**
	 * 根据imageid和itineraryid删除行程图片联系表
	 * @param url
	 * @param itineraryid
	 * xiejin
	 */
	@RequestMapping("/deletebyimageid")
	@ResponseBody
	public String deletePageImageByImageId(@RequestParam("url") String url,@RequestParam("itineraryid") String itineraryid){
		Image image = imageService.selectByUrl(url);		//根据url查询图片
		String imageId = image.getId();		//获取图片id
		itineraryImageService.deleteByImageId(imageId,itineraryid);		//根据imageid和itineraryid删除行程图片联系表信息
		return null;
	}
	
	/**
	 * @author Sevens
	 * @param destinationIds
	 * 时间2016-1-26
	 * 
	 */
	@RequestMapping(value = "/getByDestinationids", method = RequestMethod.POST)
	@ResponseBody
	public List<Attraction> getByDestinationids(@RequestParam("destinationIds") String [] destinationIds,@Param("tourlineId")String tourlineId){
		
		List<Attraction>  attractions = new ArrayList<Attraction>();
		if(destinationIds!=null && destinationIds.length>0){
			attractions = attractionService.selectByDestinationids(Arrays.asList(destinationIds),tourlineId);
		}
		
		return attractions;
	}
	
	/**
	 * @author Sevens
	 * @param destinationIds
	 * 时间2016-1-26
	 * 
	 */
	@RequestMapping(value = "/getSelfByDestinationids", method = RequestMethod.POST)
	@ResponseBody
	public List<Selfpay> getSelfByDestinationids(@RequestParam("destinationIds") String [] destinationIds){
		List<Selfpay> selfs = new ArrayList<Selfpay>();
		if(destinationIds!=null&&destinationIds.length>0){
			 selfs = selfpayMapper.findWithD(Arrays.asList(destinationIds));
			
		}
		return selfs;
	}
	
	/**
	 * @Title: showImage
	 * @Description: 行程选择图片时异步查询相关图片
	 * @param request
	 * @param model
	 * @param costnumber
	 * @return    
	 * @return Map<String,Object>    返回类型
	 * @author xiejin
	 */
	@ResponseBody
	@RequestMapping("/image")
	public Map<String, Object> showImage(HttpServletRequest request,
			Model model) {
		String content = request.getParameter("content");	//行程内容
//		System.out.println(content+"============================");
		Map<String, Object> root = null;
//		List<String> cos = new ArrayList<String>();
//		cos.add(costnumber);
		List<Attraction> attractionlist = attractionService.findAllByCostNumber();	//根据costnumber查询所有景点
		List<String> attractionId = new ArrayList<String>();	//行程内容包含的所有景点的中文名集合
		if (content!=null && !"".equals(content)) {
			for (Attraction attraction : attractionlist) {
				if (content.indexOf(attraction.getNamecn()) != -1) {
					attractionId.add(attraction.getId());		
				}
			}
			if (attractionId.size()!=0) {
				root = itineraryService.showImageByPage(request, model, attractionId);
			}
		}
		return root;
	}
}

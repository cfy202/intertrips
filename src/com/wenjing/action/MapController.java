package com.wenjing.action;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wenjing.entity.Attraction;
import com.wenjing.entity.Coordinate;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Tourline;
import com.wenjing.service.AttractionService;
import com.wenjing.service.CoordinateService;
import com.wenjing.service.CostService;
import com.wenjing.service.DestinationService;
import com.wenjing.service.TourlineService;
import com.wenjing.util.Tools;

/**
 * @author Jared
 * 
 * 地图处理controller
 *
 * Jun 1, 2015
 */

@Controller
@RequestMapping(value="/admin/map")
public class MapController {
	
	@Autowired
	private CoordinateService coordinateService;
	
	@Autowired
	private TourlineService tourlineService;
	
	@Autowired
	private DestinationService destinationService;
	
	@Autowired
	private AttractionService attractionService;
	
	@Autowired
	private CostService costService;
	
	/**
	 * 保存或更改坐标
	 * 
	 * @param coordinate
	 * @return
	 */
	@RequestMapping("/saveCoordinate")
	public String saveCoordinate(Coordinate coordinate,String actionDestination){
		if(coordinate.getId() == null || "".equals(coordinate.getId())){
			coordinate.setId(Tools.getUUID());
			coordinateService.save(coordinate);	
		}else{
			coordinateService.modify(coordinate);
		}
		return "redirect:/admin/" + actionDestination + "/list.do";
	}
	
	/**
	 * 根据目的地ID加载景点坐标
	 * 
	 * @param destinationId
	 * @return
	 */
	@RequestMapping(value="/showAttractionMap", method = RequestMethod.GET)
	public String showAttractionMap(String destinationId,Model model){
		//根据目的地ID查询出目的地的坐标
		List<Coordinate> coordinateList = coordinateService.findAttractionCoordinates(destinationId);
		Destination destination = destinationService.findById(destinationId);
		BigDecimal avgXAxis = new BigDecimal(0.0);
		BigDecimal avgYAxis = new BigDecimal(0.0);
		
		//如果有存在坐标,计算其初始化地图的中心坐标
		if(coordinateList.size() > 0){
			for(Coordinate coordinate : coordinateList){
				avgXAxis = avgXAxis.add(coordinate.getxAxis());
				avgYAxis = avgYAxis.add(coordinate.getyAxis());
			}
			avgXAxis = avgXAxis.divide(new BigDecimal(coordinateList.size()), 6, BigDecimal.ROUND_HALF_UP);
			avgYAxis = avgYAxis.divide(new BigDecimal(coordinateList.size()), 6, BigDecimal.ROUND_HALF_UP);		
		}else{
		costService.findById(destination.getCostnumber());
			avgXAxis = new BigDecimal(51.514782);
			avgYAxis = new BigDecimal(-0.078621);
		}
		
		model.addAttribute("destination", destination);
		model.addAttribute("avgXAxis", avgXAxis);
		model.addAttribute("avgYAxis", avgYAxis);
		model.addAttribute("coordinateList", coordinateList);
		return "/admin/manage/map/showAttractionCoordinates.ftl";
	}
	
	/**
	 * 指定目的地的坐标
	 * 
	 * @return
	 */
	@RequestMapping(value="/addDestinationCoordinate", method = RequestMethod.GET)
	public String addDestinationCoordinate(String id,Model model){
		//根据目的地ID查询已存在的坐标
		Coordinate existCoordinate = coordinateService.findByDestinationId(id);
		Destination destination = destinationService.findById(id);
		//坐标已存在,以存在的坐标初始化地图,并在坐标上添加标记
		if(existCoordinate != null){
			model.addAttribute("coordinate", existCoordinate);
			model.addAttribute("exist", 1);
		}else{
		//坐标不存在,新增坐标
			Coordinate parentCoordinate = coordinateService.findByDestinationId(destination.getUpid());
			//如果有父级坐标,则直接以父级坐标初始化地图
			if(parentCoordinate != null){
				model.addAttribute("coordinate", parentCoordinate);
			}else{
			costService.findById(destination.getCostnumber());
				Coordinate coordinate = new Coordinate();
				coordinate.setxAxis(new BigDecimal(51.514782));
				coordinate.setyAxis(new BigDecimal(-0.078621));
				model.addAttribute("coordinate", coordinate);	
			}
		}
		model.addAttribute("destination", destination);
		model.addAttribute("id", id);
		model.addAttribute("flag", 1);
		model.addAttribute("actionDestination", "destination");
		return "/admin/manage/map/addCoordinate.ftl";
	}
	
	/**
	 * 指定景点的坐标
	 * 
	 * @return
	 */
	@RequestMapping("/addAttractionCoordinate")
	public String addAttractionCoordinate(String id,Model model){
		Coordinate existCoordinate = coordinateService.findByAttractionId(id);
		Attraction attraction = attractionService.findById(id);
		//坐标已存在,以存在的坐标初始化地图,并在坐标上添加标记
		if(existCoordinate != null){
			model.addAttribute("coordinate", existCoordinate);
			model.addAttribute("exist", 1);
		}else{
		//坐标不存在,新增坐标
			Coordinate destinationCoordinate = coordinateService.findByDestinationId(attraction.getDestinationid());
			//如果景点所属目的地已经存在坐标,以该坐标初始化地图
			if(destinationCoordinate != null){
				model.addAttribute("coordinate", destinationCoordinate);
			}else{
				costService.findById(attraction.getCostnumber());
				Coordinate coordinate = new Coordinate();
				coordinate.setxAxis(new BigDecimal(51.514782));
				coordinate.setyAxis(new BigDecimal(-0.078621));
				model.addAttribute("coordinate", coordinate);	
			}
		}
		model.addAttribute("attraction", attraction);
		model.addAttribute("id", id);
		model.addAttribute("flag", 2);
		model.addAttribute("actionDestination", "attraction");
		return "/admin/manage/map/addCoordinate.ftl";
	}
	
	/**
	 * 根据线路ID加载目的地坐标
	 * 
	 * @param tourlineId
	 * @return
	 */
	@RequestMapping("/showDestinationMap")
	public String showDestinationMap(String tourlineId,Model model){
		//根据线路ID查询出目的地的坐标
		List<Coordinate> coordinateList = coordinateService.findDestinationCoordinates(tourlineId);
		Tourline tourline = tourlineService.findById(tourlineId);
		BigDecimal avgXAxis = new BigDecimal(0.0);
		BigDecimal avgYAxis = new BigDecimal(0.0);
		
		//如果有存在坐标,计算其初始化地图的中心坐标
		if(coordinateList.size() > 0){
			for(Coordinate coordinate : coordinateList){
				avgXAxis = avgXAxis.add(coordinate.getxAxis());
				avgYAxis = avgYAxis.add(coordinate.getyAxis());
			}
			avgXAxis = avgXAxis.divide(new BigDecimal(coordinateList.size()), 6, BigDecimal.ROUND_HALF_UP);
			avgYAxis = avgYAxis.divide(new BigDecimal(coordinateList.size()), 6, BigDecimal.ROUND_HALF_UP);		
		}else{
		costService.findById(tourline.getCostnumber());
			avgXAxis = new BigDecimal(51.514782);
			avgYAxis = new BigDecimal(-0.078621);
		}
		
		model.addAttribute("tourline", tourline);
		model.addAttribute("avgXAxis", avgXAxis);
		model.addAttribute("avgYAxis", avgYAxis);
		model.addAttribute("coordinateList", coordinateList);
		return "/admin/manage/map/showDestinationCoordinates.ftl";
	}
}

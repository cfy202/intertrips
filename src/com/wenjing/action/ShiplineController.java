
package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.ShipLine;
import com.wenjing.service.ShiplineService;

/**
 * 游船航线管理controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/shipline")
public class ShiplineController {

	@Resource
	private ShiplineService shiplineService;
	
	
	/**
	 * 查询所有游船航线列表
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<ShipLine> shipline = shiplineService.findAll();		
		model.addAttribute("shipline", shipline);
		return "/admin/manage/shipline/shipline.ftl";
	}
	
	/**
	 * 根据id删除航线
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		shiplineService.delete(id);
		return "redirect:/admin/shipcompany/list.do";
	}

	/**
	 * 保存游船航线
	 * @param setoperater
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(ShipLine shipline,@RequestParam("id") String id) {
		shipline.setId(id);
		ShipLine shipline2 = shiplineService.findById(id);
		if (shipline2 != null && !shipline2.equals("")) {
			shiplineService.update(shipline);
		} else {
			shiplineService.save(shipline);
		}
		return "redirect:/admin/shipline/list.do";
		
	}

	/**
	 * 添加，修改游船航线
	 * @param setoperater
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(ShipLine shipline, Model model) {
		String id = shipline.getId();			
		shipline = shiplineService.findById(id);
		model.addAttribute("shipline", shipline);
		return "/admin/manage/shipline/shiplineUpdate.ftl";
	}
	
	/**
	 * 添加添加游船公司
	 * @param setoperater
	 * @param model
	 * 
	 * @return
	 * sevens
	 */
	@RequestMapping("/add")
	public String add(ShipLine shipline, Model model){
		String id = UUID.randomUUID().toString();//产生uuid
		if (null!=id && id.contains("-")){  
			id = id.replaceAll("-", "");  
        }
		shipline.setId(id);
		model.addAttribute("shipline", shipline);
		return "/admin/manage/shipline/shiplineAdd.ftl";
	}
}

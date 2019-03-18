
package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Setoperater;
import com.wenjing.entity.ShipCompany;
import com.wenjing.service.SetoperaterService;
import com.wenjing.service.ShipcompanyService;

/**
 * 类说明		后台操作管理controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/shipcompany")
public class ShipcompanyController {

	@Resource
	private ShipcompanyService ShipcompanyService;
	
	
	/**
	 * 查询所有游船公司列表
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<ShipCompany> shipcompany = ShipcompanyService.findAll();		
		model.addAttribute("shipcompany", shipcompany);
		return "/admin/manage/shipcompany/shipcompany.ftl";
	}
	
	/**
	 * 根据id删除operater
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		ShipcompanyService.delete(id);
		return "redirect:/admin/shipcompany/list.do";
	}

	/**
	 * 保存游船公司
	 * @param setoperater
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(ShipCompany shipcompany,@RequestParam("id") String id) {
		shipcompany.setId(id);
		ShipCompany shipcompany2 = ShipcompanyService.findById(id);
		if (shipcompany2 != null && !shipcompany2.equals("")) {
			ShipcompanyService.update(shipcompany);
		} else {
			ShipcompanyService.save(shipcompany);
		}
		return "redirect:/admin/shipcompany/list.do";
		
	}

	/**
	 * 添加，修改游船公司
	 * @param setoperater
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(ShipCompany shipcompany, Model model) {
		String id = shipcompany.getId();			
		shipcompany = ShipcompanyService.findById(id);
		model.addAttribute("shipcompany", shipcompany);
		return "/admin/manage/shipcompany/shipcompanyUpdate.ftl";
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
	public String add(ShipCompany shipcompany, Model model){
		String id = UUID.randomUUID().toString();//产生uuid
		if (null!=id && id.contains("-")){  
			id = id.replaceAll("-", "");  
        }
		shipcompany.setId(id);
		int sort = ShipcompanyService.getOrderId();			
		shipcompany.setOrderid(sort+1);
		model.addAttribute("shipcompany", shipcompany);
	
		
		return "/admin/manage/shipcompany/shipcompanyAdd.ftl";
	}
}

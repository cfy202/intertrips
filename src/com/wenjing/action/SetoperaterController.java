
package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Setoperater;
import com.wenjing.service.SetoperaterService;

/**
 * 类说明		后台操作管理controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/operater")
public class SetoperaterController {

	@Resource
	private SetoperaterService setoperaterService;
	
	
	/**
	 * 查询所有setoperater
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Setoperater> setoperater = setoperaterService.findAll();		
		model.addAttribute("setoperater", setoperater);
		return "/admin/manage/operater/operater.ftl";
	}
	
	/**
	 * 根据id删除operater
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		setoperaterService.delete(id);
		return "redirect:/admin/operater/list.do";
	}

	/**
	 * 保存setoperater
	 * @param setoperater
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(Setoperater setoperater,@RequestParam("id") String id) {
		setoperater.setId(id);
		Setoperater operater2 = setoperaterService.findById(id);
		if (operater2 != null && !operater2.equals("")) {
			setoperaterService.update(setoperater);
		} else {
			setoperaterService.save(setoperater);
		}
		return "redirect:/admin/operater/list.do";
		
	}

	/**
	 * 添加，修改setoperater
	 * @param setoperater
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(Setoperater setoperater, Model model) {
		String id = setoperater.getId();			
		setoperater = setoperaterService.findById(id);
		model.addAttribute("setoperater", setoperater);
		return "/admin/manage/operater/operaterUpdate.ftl";
	}
	
	/**
	 * 添加setoperater
	 * @param setoperater
	 * @param model
	 * 
	 * @return
	 * sevens
	 */
	@RequestMapping("/add")
	public String add(Setoperater setoperater, Model model){
		String id = UUID.randomUUID().toString();//产生uuid
		if (null!=id && id.contains("-")){  
			id = id.replaceAll("-", "");  
        }
		setoperater.setId(id);
		int sort = setoperaterService.getOrderId();			
		setoperater.setOrderid(sort+1);
		model.addAttribute("setoperater", setoperater);
	
		
		return "/admin/manage/operater/operaterAdd.ftl";
	}
}


package com.wenjing.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.entity.Tag;
import com.wenjing.service.TagService;
import com.wenjing.util.Tools;

/**
 * 类说明		后台产品标签管理controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/tag")
public class TagController {
	@Resource
	private TagService tagService;
	@Resource
	private HttpServletRequest request;
	
	
	/**
	 * 查询所有产品标签列表
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Tag> taglist = new ArrayList<Tag>();
		List<String> costlistst = Tools.getCostNumber(request);
		if(costlistst.size()>0&&costlistst.size()==1){
			taglist = tagService.findAll(costlistst.get(0),1);		
		}else{
			taglist = tagService.findAll(null, null);
		}
		model.addAttribute("taglist", taglist);
		return "/admin/manage/tag/tag.ftl";
	}
	
	/**
	 * 根据id删除tag
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id, final RedirectAttributes rAttributes) {
		boolean flag = tagService.delete(id);
		if(flag){
			rAttributes.addFlashAttribute("message", "删除成功！");
		}else {
			rAttributes.addFlashAttribute("message", "删除失败，此标签正在使用！");
		}
		return "redirect:/admin/tag/list.do";
	}

	/**
	 * 保存产品标签
	 * @param tag
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(Tag tag,@RequestParam("id") String id) {
		tag.setId(id);
		List<String> costlistst = Tools.getCostNumber(request);
		if(costlistst.size()>0&&costlistst.size()==1){
			tag.setCostnumber(costlistst.get(0));
			tag.setType(2);
		}else{
			tag.setType(1);
		}
		Tag tag2 = tagService.findById(id);
		if (tag2 != null && !tag2.equals("")) {
			tagService.update(tag);
		} else {
			tagService.save(tag);
		}
		return "redirect:/admin/tag/list.do";
		
	}

	/**
	 * 添加，修改产品标签
	 * @param tag
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(Tag tag, Model model) {
		String id = tag.getId();			
		tag = tagService.findById(id);
		model.addAttribute("tag", tag);
		return "/admin/manage/tag/tagUpdate.ftl";
	}
	
	/**
	 * 添加产品标签
	 * @param Tag
	 * @param model
	 * 
	 * @return
	 * sevens
	 */
	@RequestMapping("/add")
	public String add(Tag tag, Model model){
		String id = UUID.randomUUID().toString();//产生uuid
		if (null!=id && id.contains("-")){  
			id = id.replaceAll("-", "");  
        }
		tag.setId(id);
		model.addAttribute("tag", tag);
		return "/admin/manage/tag/tagAdd.ftl";
	}
}

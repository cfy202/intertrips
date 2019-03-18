package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wenjing.entity.Setoperater;
import com.wenjing.entity.Tourline;
import com.wenjing.entity.Tree;
import com.wenjing.service.SetoperaterService;
import com.wenjing.service.TreeService;

/**
 * 类说明 后台目录管理controller
 * 
 * @author sevens
 * @date 2015-4-23
 */
@Controller
@RequestMapping("/admin/tree")
public class TreeController {

	@Resource
	private TreeService treeService;
	@Resource
	private SetoperaterService setoperaterService;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 查询所有tree
	 * 
	 * @return sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model, String costnumber) {
		List<Tree> treelist = treeService.findAll();
		model.addAttribute("treelist", treelist);
		return "/admin/manage/tree/tree.ftl";
	}

	/**
	 * 根据id删除Tree
	 * 
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id) {
		treeService.delete(id);
		return "redirect:/admin/tree/list.do";
	}

	/**
	 * 保存tree
	 * 
	 * @param tree
	 * @param id
	 * @return sevens
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute("treeup") Tree treeup, @RequestParam("id") String id) {
		String[] paters = request.getParameterValues("my-select[]");
		StringBuffer buf1 = new StringBuffer();
		StringBuffer buf2 = new StringBuffer();
		String url = treeup.getUrl();
		System.out.println(url);
		String opurls = null;
		if (url != null && !"".equals(url)) {
			opurls = url.substring(0, url.lastIndexOf("/"));
		}
		if (paters != null && paters.length > 0) {
			for (String string : paters) {
				String[] kka = string.split(":");
				buf1.append(kka[0] + ",");
				if (kka[1].length() > 15) {
					buf2.append(kka[1] + ",");
				} else {
					buf2.append(opurls + kka[1] + ",");
				}

			}
			treeup.setOpationNames(buf1.substring(0, buf1.length() - 1));
			treeup.setOpationIds(buf2.substring(0, buf2.length() - 1));
		}
		treeup.setId(id);
		Tree oldTree = treeService.findById(id);
		if (oldTree != null) {
			treeService.update(treeup, oldTree);
		} else {
			treeService.save(treeup);
		}
		return "redirect:/admin/tree/list.do";
	}

	/**
	 * 添加，修改tree
	 * 
	 * @param region
	 * @param model
	 * @return sevens
	 */
	@RequestMapping("/update")
	public String update(Tree treeus, Model model) {
		String id = treeus.getId();
		treeus = treeService.findById(id);
		model.addAttribute("treeus", treeus);
		//List<Tree> treelist = treeService.findAll();
		List<Tree> treelist = treeService.findNotContainSon(treeus);
		model.addAttribute("treelist", treelist);
		List<Setoperater> operaterlist = setoperaterService.findAll();
		model.addAttribute("operaterlist", operaterlist);
		return "/admin/manage/tree/treeUpdate.ftl";
	}

	/**
	 * 添加Tree
	 * 
	 * @param Tree
	 * @param model
	 * 
	 * @return sevens
	 */
	@RequestMapping("/add")
	public String add(Tree trees, Model model) {
		String id = UUID.randomUUID().toString();// 产生uuid
		if (null != id && id.contains("-")) {
			id = id.replaceAll("-", "");
		}
		trees.setId(id);
		int sort = treeService.getOrderId();
		trees.setOrderid(sort + 1);
		model.addAttribute("trees", trees);

		List<Tree> treelist = treeService.findAll();
		model.addAttribute("treelist", treelist);
		List<Setoperater> operaterlist = setoperaterService.findAll();
		model.addAttribute("operaterlist", operaterlist);
		return "/admin/manage/tree/treeAdd.ftl";
	}
}

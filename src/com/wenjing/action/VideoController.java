
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
import com.wenjing.entity.Video;
import com.wenjing.service.TagService;
import com.wenjing.service.VideoService;
import com.wenjing.util.Tools;

/**
 * 类说明		后台产品视频controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/video")
public class VideoController {
	@Resource
	private VideoService videoService;
	@Resource
	private HttpServletRequest request;
	
	
	/**
	 * 查询所有产品标签列表
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model){
		List<Video> videolist = new ArrayList<Video>();
		List<String> costlistst = Tools.getCostNumber(request);
		if(costlistst.size()>0&&costlistst.size()==1){
			videolist = videoService.findAll(costlistst.get(0),1);		
		}else{
			videolist = videoService.findAll(null, null);
		}
		model.addAttribute("videolist", videolist);
		return "/admin/manage/video/videolist.ftl";
	}
	
	/**
	 * 根据id删除video
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id, final RedirectAttributes rAttributes) {
		boolean flag = videoService.delete(id);
		if(flag){
			rAttributes.addFlashAttribute("message", "删除成功！");
		}else {
			rAttributes.addFlashAttribute("message", "删除失败，此标签正在使用！");
		}
		return "redirect:/admin/video/list.do";
	}

	/**
	 * 保存产品视频
	 * @param video
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(Video video,@RequestParam("id") String id) {
		video.setId(id);
		List<String> costlistst = Tools.getCostNumber(request);
		if(costlistst.size()>0&&costlistst.size()==1){
			video.setCostnumber(costlistst.get(0));
			video.setType(2);
		}else{
			video.setType(1);
		}
		Video video2 = videoService.findById(id);
		if (video2 != null && !video2.equals("")) {
			videoService.update(video);
		} else {
			videoService.save(video);
		}
		return "redirect:/admin/video/list.do";
		
	}

	/**
	 * 添加，修改产品视频
	 * @param video
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(Video video, Model model) {
		String id = video.getId();			
		video = videoService.findById(id);
		model.addAttribute("video", video);
		return "/admin/manage/video/videoupdate.ftl";
	}
	
	/**
	 * 添加产品视频
	 * @param video
	 * @param model
	 * 
	 * @return
	 * sevens
	 */
	@RequestMapping("/add")
	public String add(Video video, Model model){
		String id = UUID.randomUUID().toString();//产生uuid
		if (null!=id && id.contains("-")){  
			id = id.replaceAll("-", "");  
        }
		video.setId(id);
		model.addAttribute("video", video);
		return "/admin/manage/video/videoadd.ftl";
	}
}

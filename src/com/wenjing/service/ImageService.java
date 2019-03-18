
package com.wenjing.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.wenjing.Pages;
import com.wenjing.dao.ImageMapper;
import com.wenjing.entity.Image;

/**
 * 类说明
 * @author xiejin
 * @date 2015-4-23 
 */
@Service
public class ImageService {
	
	@Resource
	private ImageMapper imageMapper;
	
	/**
	 * 根据costnumber查询所有Image 
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public void findAllByCostNumber(HttpServletRequest request,HttpServletResponse response,Model model,String usetype){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pageNow = request.getParameter("pageNow");
		
//     	if(pageNow==null||"".equals(pageNow)){
//			
//			Cookie[] cookie = request.getCookies();
//			for (int i = 0; i < cookie.length; i++) {
//				Cookie cook = cookie[i];
//				if(cook.getName().equals("AtpageNow")){ //获取键 
//					pageNow =cook.getValue().toString();    //获取值 
//				}
//			}
//		}else{
//			WebUtils.addCookie(request, response, "AtpageNow", pageNow);
//		}
           
		Pages page = null;
		
		List<Image> allImages = new ArrayList<Image>();
		int totalCount = imageMapper.getImageCountBycondition(usetype);

		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(12);
			allImages = this.imageMapper.selectAllByPage(page.getStartPos(), page.getPageSize(),usetype,null);
		} else {
			page = new Pages(totalCount, 1);
			page.setPageSize(12);
			allImages = this.imageMapper.selectAllByPage(page.getStartPos(), page.getPageSize(),usetype,null);
		}
		for (Image image : allImages) {
			int imageUseCount = SelectImageUseCount(image.getId());
			image.setImageUseCount(imageUseCount);
		}
        model.addAttribute("allImages", allImages);
        model.addAttribute("page", page);
	}
	
	/**
	 * 删除image
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return imageMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询image
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public Image findById(String id) {
		return imageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存image
	 * @param image
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Image image) {
		return imageMapper.insert(image);
	}
	
	/**
	 * 修改image
	 * @param image
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Image image) {
		return imageMapper.updateByPrimaryKeySelective(image);
	}		
	
	/**
	 * 根据URL查询图片
	 * @param url
	 * @return
	 * xiejin
	 */
	@Transactional
	public Image selectByUrl(String url){
		return imageMapper.selectByUrl(url);
	}
	
	/**
	 * @Title: SelectImageUseCount
	 * @Description: 查询图片呗使用的次数
	 * @param imageId
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public int SelectImageUseCount(String imageId){
		return imageMapper.SelectImageUseCount(imageId);
	}
}

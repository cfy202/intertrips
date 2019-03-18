
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
import com.wenjing.dao.AttractionMapper;
import com.wenjing.dao.AttractionimageMapper;
import com.wenjing.dao.TourlineattractionMapper;
import com.wenjing.entity.Attraction;
import com.wenjing.entity.Attractionimage;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Tourlineattraction;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明    景点后台管理
 * @author xiejin
 * @date 2015-4-23 
 */
@Service
public class AttractionService {

	@Resource
	private AttractionMapper attractionMapper;
	@Resource
	private TourlineattractionMapper tourlineattractionMapper;
	@Resource
	private AttractionimageMapper attractionimageMapper;
	
	/**
	 * 查询所有attraction带分页
	 * @return sevens
	 */
	@Transactional(readOnly = true)
	public void findAll(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pageNow = request.getParameter("AtpageNow");	
		String search = request.getParameter("search");
		String pageSize = request.getParameter("pageSize");
		if(pageSize==null||"".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "AtpageSize");
		}else{
			WebUtils.addCookie(request, response, "AtpageSize", pageSize);
			pageNow = 1+"";
		}
		if (search == null || "".equals(search)) {
			search = WebUtils.getCookie(request, "Atsearch");
		} else {
			WebUtils.addCookie(request, response, "Atsearch", search);
			pageNow = 1+"";
		}
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "AtpageNow");
		} else {
			WebUtils.addCookie(request, response, "AtpageNow", pageNow);
		}

		Pages page = null;
		List<Attraction> Allattraction = new ArrayList<Attraction>();
		int totalCount = attractionMapper.getSAttractionCount(search);
		if(pageSize==null){
			pageSize = 10+"";
		}
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.valueOf(pageSize));
			Allattraction = this.attractionMapper.selectAllByPage(page.getStartPos(), page.getPageSize(),search);
		}else{
			page = new Pages(totalCount, 1);
			page.setPageSize(Integer.valueOf(pageSize));
			Allattraction = this.attractionMapper.selectAllByPage(page.getStartPos(), page.getPageSize(),search);
		}
        model.addAttribute("Allattraction", Allattraction);
        model.addAttribute("page", page);
        model.addAttribute("Atsearch",search);		
	}
	
	/**
	 * 根据costnumber查询所有attraction 
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<Attraction> findAllByCostNumber(){
		return attractionMapper.findAllByCostNumber();
	}
	
	/**
	 * 删除attraction
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return attractionMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询attraction
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public Attraction findById(String id) {
		return attractionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存attraction
	 * @param attraction
	 * @return
	 * xiejin
	 */
	@Transactional
	public int save(Attraction attraction) {
		return attractionMapper.insert(attraction);
	}
	
	/**
	 * 修改attraction
	 * @param attraction
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Attraction attraction) {
		return attractionMapper.updateByPrimaryKeyWithBLOBs(attraction);
	}
	
	/**
	 * 查询最大sort
	 * @return
	 * xiejin
	 */
	@Transactional
	public int getOrderId(){
		return attractionMapper.getMaxSort();
	}
	
	/**
	 * @Title: findHotAttraction
	 * @Description: 根据线路景点查询其中热门景点名称
	 * @param costnumber
	 * @return    
	 * @return List<String>    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public List<String> findHotAttraction(List<String> att,Integer num){
		return attractionMapper.findHotAttraction(att,num);
	}
	
    /**
     * @Title: deleteByAttractionId
     * @Description: 根据景点id删除线路图片关联信息
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int deleteByAttractionId(String attractionId){
    	return tourlineattractionMapper.deleteByAttractionId(attractionId);
    };
    
    /**
     * @Title: selectByDestinationid
     * @Description: 根据destinationId查询所有景点
     * @param destinationId
     * @return    
     * @return List<Attraction>    返回类型
     * @author xiejin
     */
    @Transactional(readOnly=true)
    public List<Attraction> selectByDestinationid(String destinationId){
    	return attractionMapper.selectByDestinationid(destinationId);
    };
    
    /**
     * @Title: updateNamepy
     * @Description: 修改拼音
     * @param id
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
    @Transactional
    public int updateNamepy(Attraction attraction){
    	return attractionMapper.updateNamepy(attraction);
    };
    
    /**
     * @Title: findRegionShow
     * @Description: 查询二级页面显示的游玩地区里所有景点
     * @return    
     * @return List<Attraction>    返回类型
     * @author xiejin
     */
    @Transactional(readOnly=true)
    public List<Attraction> findRegionShow(String regionid ,String costnumber){
    	int time = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
    	return attractionMapper.findRegionShow(regionid, costnumber, time);
    };
    
	/**
	 * @Title: isExist
	 * @Description: 判断景点是否存在
	 * @param name
	 * @param costnumber
	 * @return    
	 * @return Boolean    返回类型
	 * @author xiejin
	 */
	@Transactional
	public Boolean isExist(String name){
		boolean flag = true;
		List<Attraction> attraction = attractionMapper.findByNameNamacn(name);
		if (attraction.size()==0) {
			flag = false;	//景点不存在
		}
		return flag;
	}
	
	/**
	 * @Title: findByAttractionId
	 * @Description:  根据景点id判断景点是否被关联使用
	 * @param attractionId
	 * @return    
	 * @return boolean    返回类型
	 * @author xiejin
	 */
	@Transactional
	public boolean findByAttractionId(String attractionId){
		boolean flag = false;
		List<Tourlineattraction> tourlineattractions = tourlineattractionMapper.selectByAttractionid(attractionId);
		List<Attractionimage> attractionimages = attractionimageMapper.selectByAttractionid(attractionId);
		if (tourlineattractions.size()>0 || attractionimages.size()>0) {
			flag = true;
		}
		return flag;
	}
	
    /**
     * @Title: replaceByAttractionId
     * @Description: 景点图片联系表替换景点id
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int replaceByAttractionId(String attractionId,String replaceId){
		return attractionimageMapper.replaceByAttractionId(attractionId, replaceId);
	}
	
    /**
     * @Title: replaceByAttractionId2
     * @Description: 景点线路联系表替换景点id
     * @param attractionId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int replaceByAttractionId2(String attractionId,String replaceId){
		return tourlineattractionMapper.replaceByAttractionId(attractionId, replaceId);
	}
	
   @Transactional(readOnly=true)
   public List<Attraction> selectByids(List<String> ids){
	       return attractionMapper.selectByids(ids);	
   }
	/**
	 * 根据目的地查询关联的景点信息
	 * @author Sevens
	 * 时间2016-1-1
	 * @param destinationIds
	 * @return
	 */
	  @Transactional(readOnly=true)
	  public List<Attraction> selectByDestinationids(List<String> destinationIds,String tourlineId){
		  return attractionMapper.selectByDestinationids(destinationIds,tourlineId);
		  
	  }
}

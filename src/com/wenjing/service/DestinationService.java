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
import com.wenjing.dao.DestinationMapper;
import com.wenjing.dao.HotelMapper;
import com.wenjing.dao.IndexShowDestinationMapper;
import com.wenjing.dao.TourlinedestinationMapper;
import com.wenjing.entity.Attraction;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Destination;
import com.wenjing.entity.Hotel;
import com.wenjing.entity.IndexShowDestination;
import com.wenjing.entity.Tourlinedestination;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;

/**
 * 类说明    后台目的地管理
 * @author xiejin
 * @date 2015-4-20
 */
@Service
public class DestinationService {

	@Resource
	private DestinationMapper destinationMapper;
	@Resource
	private TourlinedestinationMapper tourlinedestinationMapper;
	@Resource
	private AttractionMapper attractionMapper;
	@Resource
	private HotelMapper hotelMapper;
	@Resource
	private IndexShowDestinationMapper indexShowDestinationMapper;
	
	/**
	 * 查询所有tourline
	 * @return sevens
	 */
	@Transactional(readOnly = true)
	public void findAll(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pageNow = request.getParameter("DpageNow");
		String search = request.getParameter("search");
		String pageSize = request.getParameter("pageSize");
		String costId = request.getParameter("costId");
		
		
		//分销售中心查询
		List<String> costNumList = new ArrayList<String>();
		if (costId == null || "".equals(costId)) {
			costId = WebUtils.getCookie(request, "desCostId");
			if (costId == null || "".equals(costId)) {
				costNumList = Tools.getCostNumber(request);
			}else {
				costNumList.add(costId);
			}
		} else {
			costNumList.add(costId);
			WebUtils.addCookie(request, response, "desCostId", costId);
			pageNow = "1";
		}
		
		
		if(pageSize==null||"".equals(pageSize)){
			pageSize = WebUtils.getCookie(request, "DpageSize");
		}else{
			WebUtils.addCookie(request, response, "DpageSize", pageSize);
			pageNow = 1+"";
		}
		
		if (search == null || "".equals(search)) {
			search = WebUtils.getCookie(request, "Dsearch");
		} else {
			WebUtils.addCookie(request, response, "Dsearch", search);
			pageNow = 1+"";
		}
		
		if (pageNow == null || "".equals(pageNow)) {
			pageNow = WebUtils.getCookie(request, "DpageNow");
		} else {
			WebUtils.addCookie(request, response, "DpageNow", pageNow);
		}
		
		Pages page = null;
		List<Destination> Alldestion = new ArrayList<Destination>();
		int totalCount = destinationMapper.getSDestionCount(search);
		if(pageSize==null){
			pageSize =10+ "";
		}
		if (pageNow != null) {
			page = new Pages(totalCount, Integer.parseInt(pageNow));
			page.setPageSize(Integer.valueOf(pageSize));
			Alldestion = this.destinationMapper.selectAllByPage(page.getStartPos(), page.getPageSize(),search);
		}else{
			page = new Pages(totalCount, 1);
			page.setPageSize(Integer.valueOf(pageSize));
			Alldestion = this.destinationMapper.selectAllByPage(page.getStartPos(), page.getPageSize(),search);
		}
		//*************
		if (costNumList.size() == 1) {
			String upTourlineId = indexShowDestinationMapper.getDestinationIdString(costNumList.get(0), 1);
			String downTourlineId = indexShowDestinationMapper.getDestinationIdString(costNumList.get(0), 2);
			model.addAttribute("upTourlineId", upTourlineId);
			model.addAttribute("downTourlineId", downTourlineId);
		}
		List<Cost> cost = (List<Cost>) request.getSession().getAttribute("cost");
		//**********
        model.addAttribute("Alldestion", Alldestion);
        model.addAttribute("page", page);
        model.addAttribute("Dsearch",search);
        model.addAttribute("costNumList", costNumList);
        model.addAttribute("cost", cost);
		
	}

	
	/**
	 * 根据costnumber查询所有destination 
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<Destination> findAllByCostNumber(){
		return destinationMapper.findAllByCostNumber();
	}
	
	/**
	 * 根据costnumber查询除城市外的destination
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	@Transactional(readOnly=true)
	public List<Destination> findByCostNumber(){
		return destinationMapper.findByCostNumber();
	}
	
	/**
	 * 根据costnumber查询所有城市
	 * @param costnumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Destination> findCityByCostNumber(){
		return destinationMapper.findCityByCostNumber();
	}
	
	/**
	 * 删除destination
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public int delete(String id) {
		return destinationMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询destination
	 * @param id
	 * @return
	 * xiejin
	 */
	@Transactional
	public Destination findById(String id) {
		return destinationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存destination
	 * @param destination
	 * @return
	 * xiejin
	 */
	@Transactional
	
	public int save(Destination destination) {
		return destinationMapper.insert(destination);
	}
	
	/**
	 * 修改destination
	 * @param destination
	 * @return
	 * xiejin
	 */
	@Transactional
	
	public int update(Destination destination) {
		return destinationMapper.updateByPrimaryKey(destination);
	}
	
	/**
	 * 根据costnumber查询最大sort
	 * @return
	 * xiejin
	 */
	@Transactional
	public int getOrderId(String costnumber){
		return destinationMapper.getMaxSort();
	}
	
	/**
	 * 根据costnumber查询所有城市和州
	 * @param costnumber
	 * @return
	 * xiejin
	 */
	
//	@Cacheable("DestinationList")
	@Transactional(readOnly=true)
	public List<Destination> findCityAndProvinceByCostNumber(){
		return destinationMapper.findCityAndProvinceByCostNumber();
	}
	
	/**
	 * @Title: getUps
	 * @Description: 递归查询所有目的地隶属
	 * @param upid
	 * @param buf
	 * @return    
	 * @return String    返回类型
	 * @author xiejin
	 */
	@Transactional(readOnly=true)
	public String getUps(String upid,StringBuffer buf){
		if(upid.equals("0")){
		}else{
			Destination destination = destinationMapper.selectByPrimaryKey(upid);
//			String namecn = destination.getNamecn();
			String namecn = destination.getName();
			upid = destination.getUpid();
			getUps(upid,buf);
			buf.append(namecn+"/");
		}
		return buf.toString();
		
	}
	
	/**
	 * @Title: isExist
	 * @Description: 判断目的地是否存在
	 * @param name
	 * @param costnumber
	 * @return    
	 * @return Boolean    返回类型
	 * @author xiejin
	 */
	@Transactional
	public Boolean isExist(String name){
		boolean flag = true;
		List<Destination> destination = destinationMapper.findByNameNamacn(name);
		if (destination.size()==0) {
			flag = false;	//目的地不存在
		}
		return flag;
	}
	
	@Transactional
	public List<Destination> selectWithTourline(List<String> desIds){
		return destinationMapper.selectWithTourline(desIds);
	};
	
    /**
     * @Title: deleteByDestinationId
     * @Description: 根据destinationId删除关系表
     * @param destinationId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int deleteByDestinationId(String destinationId){
    	return tourlinedestinationMapper.deleteByDestinationId(destinationId);
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
    public int updateNamepy(Destination destination){
    	return destinationMapper.updateNamepy(destination);
    };
    
    /**
     * @Title: findNamecnAndNamepy
     * @Description: 根据id查询中文名字和拼音
     * @param id
     * @return    
     * @return Destination    返回类型
     * @author xiejin
     */
    @Transactional(readOnly=true)
    public Destination findNamecnAndNamepy(String id){
    	return destinationMapper.findNamecnAndNamepy(id);
    };
    
    /**
     * @Title: findRegionShow
     * @Description: 查询二级页面显示的游玩地区里所有目的地
     * @return    
     * @return List<Destination>    返回类型
     * @author xiejin
     */
    @Transactional(readOnly=true)
    public List<Destination> findRegionShow(String regionid ,String costnumber){
    	int time = Tools.getDtimestemp(Tools.getDtime()); //当前时间戳
    	return destinationMapper.findRegionShow(regionid, costnumber, time);
    };
    
	/**
	 * @Title: findByDestinationId
	 * @Description:  根据目的地id判断目的地是否被关联使用
	 * @param attractionId
	 * @return    
	 * @return boolean    返回类型
	 * @author xiejin
	 */
	@Transactional
	public boolean findByDestinationId(String destinationId){
		boolean flag = false;
		//查询线路目的地关系表
		List<Tourlinedestination> tourlinedestinations = tourlinedestinationMapper.selectByDestinationid(destinationId);
		//查询相关景点
		List<Attraction> attractions = attractionMapper.selectByDestinationid(destinationId);
		//查询相关酒店
		List<Hotel> hotels = hotelMapper.selectByDestinationid(destinationId);
		if (tourlinedestinations.size()>0 || attractions.size()>0 || hotels.size()>0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Title: replaceByDestinationId
	 * @Description: 替换目的地id
	 * @param destinationId
	 * @param replaceId
	 * @return    
	 * @return boolean    返回类型
	 * @author xiejin
	 */
	@Transactional
	public boolean replaceByDestinationId(String destinationId,String replaceId){
		boolean flag = false;
		if (destinationId != null && !"".equals(destinationId) && replaceId != null && !"".equals(replaceId)) {
			//替换线路目的地关系表
			int tdCount = tourlinedestinationMapper.replaceByDestinationId(destinationId, replaceId);
			//替换景点表相关景点
			int attractionCount = attractionMapper.replaceByDestinationId(destinationId, replaceId);
			//替换酒店表相关酒店
			int hotelCount = hotelMapper.replaceByDestinationId(destinationId, replaceId);
			if (tdCount == 0 && attractionCount == 0 && hotelCount == 0) {
			}else{
				delete(destinationId);
				flag = true;
			}
		}
		return flag;
	}
	
    /**
     * @Title: findDesNav
     * @Description: 查询目的地导航
     * @param costnumber
     * @return    
     * @return List<Destination>    返回类型
     * @author xiejin
     */
	@Transactional(readOnly=true)
    public List<Destination> findDesNav(String costnumber){
    	return destinationMapper.findDesNav(costnumber);
    }
	
    /**
     * @Title: updateFileUrl
     * @Description: 根据销售中心和目的地id修改fileUrl
     * @param costNumber
     * @param destinationId
     * @return    
     * @return int    返回类型
     * @author xiejin
     */
	@Transactional
    public int updateFileUrl(String costnumber,String destinationId,String fileUrl,String pageId){
    	return indexShowDestinationMapper.updateFileUrl(costnumber, destinationId, fileUrl,pageId);
    };
    
    /**
     * @Title: getFileUrl
     * @Description: 根据销售中心和目的地id查询fileUrl
     * @param costnumber
     * @param destinationId
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
	@Transactional(readOnly=true)
    public IndexShowDestination getFileUrl(String costnumber,String destinationId){
    	return indexShowDestinationMapper.getFileUrl(costnumber, destinationId);
    };
    
    @Transactional(readOnly=true)
    public List<Destination> findWithSuoYin(){
    	return destinationMapper.findWithSuoYin();
    }
    
    @Transactional(readOnly=true)
    public List<Destination> selectByids(List<String> ids){
       return destinationMapper.selectByids(ids);	
    }

	@Transactional(readOnly=true)
    public List<Destination> findChildrenById(String id){
		List<Destination> destinationList = destinationMapper.findAllByCostNumber();
		return getChildrenDestination(destinationList,id);
	}

    public List<Destination> getChildrenDestination(List<Destination> root,String destinationId){
    	List<Destination> resultDestination = new ArrayList<Destination>();
		for(Destination destination: root){
			if(destination.getUpid().equals(destinationId)){
				resultDestination.add(destination);
				resultDestination.addAll(getChildrenDestination(root,destination.getId()));
			}
		}
		return resultDestination;
	}

	public List<Destination> findByTourline(String tourlineId){
		return destinationMapper.selectByTourlineId(tourlineId);
	}
}

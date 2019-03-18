package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.DepartureDateMapper;
import com.wenjing.dao.DepartureMapper;
import com.wenjing.entity.Departure;
import com.wenjing.util.Tools;
/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-5-12 上午11:32:33
 * 类说明 :出发地管理   Service
 */
@Service
public class DepartureService {

	@Resource
	private DepartureMapper departureMapper;
	@Resource
	private DepartureDateMapper departureDateMapper;
	
	/**
	 * 根据管理员权限,查询其运营中心下全部全部出发地
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Departure> findAllByCostNumber(List<String> coList) {
		return departureMapper.findAllByCostNumber(coList);
	}

	/**
	 * 获取当前sort最大值
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getMaxSort(String costnumber) {
		return departureMapper.getMaxSort(costnumber);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Departure findById(String id) {
		return departureMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改
	 * 
	 * @param Departure
	 * @return
	 */
	@Transactional
	public int update(Departure Departure) {
		int success = departureMapper.updateByPrimaryKey(Departure);
		return success;
	}

	/**
	 * 新增
	 * 
	 * @param Departure
	 * @return
	 */
	@Transactional
	public int insert(Departure Departure) {
		String id = Tools.getUUID();
		Departure.setId(id);
		int success = departureMapper.insertSelective(Departure);
		return success;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean delete(String id) {
		boolean flag = true;
		int departureDateCount = departureDateMapper.findByDepartureId(id);
		if(departureDateCount > 0){
			flag = false;
		}else {
			int success = departureMapper.deleteByPrimaryKey(id);
		}
		return flag;
	}

	/**
	 * 根据costnumber查询对应出发地
	 * @param costnumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Departure> findByCostid(String costnumber) {
		return departureMapper.findByCostid(costnumber);
	}

	/**
	 * 查询所有出发地
	 * @param costnumber
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Departure> findAll() {
		return departureMapper.findAll();
	}

	public Boolean findByDepartureId(String departureId) {
		boolean flag = false;
		int departureDateCount = departureDateMapper.findByDepartureId(departureId);
		if(departureDateCount > 0){
			flag = true;
		}
		return flag;
	}

	/**
	 * @Title replaceDeparture
	 * @Description 替换出发日期中的出发地
	 * @Author Bowden
	 * @CreateDate 2015-10-23 下午4:14:48
	 */
	public boolean replaceDeparture(HttpServletRequest request) {
		boolean flag = false;
		String replaceId = request.getParameter("replaceId");
		String departureId = request.getParameter("departureId");
		if(replaceId !=null && departureId !=null && !"".equals(replaceId) && !"".equals(departureId)){
			int success = departureDateMapper.replaceDeparture(departureId, replaceId);
			if(success > 0){
				flag = true;
			}
		}
		return flag;
	}
	
	public List<Departure>findByDepartureIds(String departureIds){
		return departureMapper.findByDepartureIds(departureIds);
	}
}

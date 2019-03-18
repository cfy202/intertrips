
package com.wenjing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.ServiceItemMapper;
import com.wenjing.dao.ServiceItemProductMapper;
import com.wenjing.entity.ServiceItem;
import com.wenjing.entity.ServiceItemProduct;

/**
 * 产品标签后台管理
 * @author sevens
 *
 */
@Service("serviceItemService")
public class ServiceItemService {
	
	@Autowired
	private ServiceItemProductMapper serviceItemProductMapper;
	
	@Autowired
	private ServiceItemMapper serviceItemMapper;
	
	/**
	 * 查询所有服务项
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<ServiceItem> findAll(){
		return serviceItemMapper.findAll();
	}
	
	/**
	 * 根据id删除服务项
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean delete(String id) {
		List<ServiceItemProduct> serviceItemProductList = serviceItemProductMapper.selectByServiceItemId(id);
		if(serviceItemProductList.size() == 0){
			serviceItemMapper.deleteByPrimaryKey(id);
			return true;
		}
		return false;
	}

	/**
	 * 根据id查询服务项
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public ServiceItem findById(String id) {
		return serviceItemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存服务项
	 * @param 
	 * @return
	 */
	@Transactional
	public int save(ServiceItem serviceItem) {
		return serviceItemMapper.insert(serviceItem);
	}
	
	/**
	 * 修改服务项
	 * @param 
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(ServiceItem serviceItem) {
		return serviceItemMapper.updateByPrimaryKey(serviceItem);
	}
	
	/**
	 * 根据产品ID删除产品和服务项的关联
	 * @param productId
	 * @return
	 */
	@Transactional
	public int deleteAssociationByProductId(String productId){
		return serviceItemProductMapper.deleteByProductId(productId);
	}
	
	/**
	 * 根据服务项ID删除产品和服务项的关联
	 * @param serviceItemId
	 * @return
	 */
	public int deleteAssociationByServiceItemId(String serviceItemId){
		return serviceItemProductMapper.deleteByServiceItemId(serviceItemId);
	}
	
	/**
	 * 根据服务项ID和产品ID查出关联数量
	 * 
	 * @param serviceItemId
	 * @param productId
	 * @return
	 */
	@Transactional(readOnly=true)
	public int findByServiceItemIdAndProductId(String serviceItemId, String productId) {
	    return serviceItemProductMapper.findByItemIdAndProductId(serviceItemId, productId);
	}
	
	/**
	 * 根据产品ID和服务项类型查出服务项
	 * 
	 * @param productId
	 * @param type
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ServiceItem> findByProductIdAndType(String productId, int type){
	    return serviceItemMapper.findProductIdAndType(productId, type);
	}
}

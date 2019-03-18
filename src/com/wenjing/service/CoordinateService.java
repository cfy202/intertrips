package com.wenjing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wenjing.dao.CoordinateMapper;
import com.wenjing.entity.Coordinate;

/**
 * Service - 坐标
 *
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("coordinateServiceImpl")
public class CoordinateService{

    @Autowired
    private CoordinateMapper coordinateMapper;

    @Transactional
    public void delete(String id) {
    	coordinateMapper.deleteByPrimaryKey(id);
    }

   @Transactional(readOnly=true)
   public List<Coordinate> findAll(){
	   return coordinateMapper.findAll();
   }
   
   @Transactional(readOnly=true)
   public Coordinate findById(String id){
	   return coordinateMapper.selectByPrimaryKey(id);
   }
   
   @Transactional
   public void save(Coordinate coordinate){
	   coordinateMapper.insertSelective(coordinate);
   }

   @Transactional
   public void modify(Coordinate coordinate){
	   coordinateMapper.updateByPrimaryKeySelective(coordinate);
   }
   
   @Transactional
   public Coordinate findByDestinationId(String destinationId){
	   return coordinateMapper.findByDestinationId(destinationId);
   }
   
   @Transactional
   public Coordinate findByAttractionId(String attractionId){
	   return coordinateMapper.findByAttractionId(attractionId);
   }
   
	/**
	 * 根据线路ID加载目的地坐标
	 * 
	 * @param tourlineId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Coordinate> findDestinationCoordinates(String tourlineId){
		return coordinateMapper.findDesCoordinatesByTourlineId(tourlineId);
	}
	
	/**
	 * 根据目的地ID加载景点坐标
	 * 
	 * @param destinationId
	 * @return
	 */
	public List<Coordinate> findAttractionCoordinates(String destinationId){
		return coordinateMapper.findAttrCoordinatesByDesId(destinationId);
	}
	
}
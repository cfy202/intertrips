package com.wenjing.dao;

import java.util.List;
import com.wenjing.entity.AirTicketPrice;

public interface AirTicketPriceMapper {
    
	int deleteByPrimaryKey(String id);

    int insert(AirTicketPrice record);

    int insertSelective(AirTicketPrice record);

    AirTicketPrice selectByPrimaryKey(String id);

    int updateByPrimaryKey(AirTicketPrice record);

    /**
     * 批量插入机票价格信息
     * @param aList
     * @return
     */
	int batchAdd(List<AirTicketPrice> aList);

	/**
	 * 根据tourpriceid查询对应下的机票
	 * @param tourpriceid
	 * @return
	 */
	List<AirTicketPrice> findAirTicketByPriceId(String tourpriceId);
	
    /**
     * 根据tourpriceid删除
     * @param id
     * @return
     */
	int deleteAirTickerByPriceId(String tourpriceId);
	
	/**
	 * 根据费用ID查找出机票费用
	 * @param tourPriceId
	 * @param departureId
	 * @return
	 */
	List<AirTicketPrice> getAirTicketPriceByTourpriceId(String tourPriceId);
}
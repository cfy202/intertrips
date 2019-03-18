package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.DepartureDateMapper;
import com.wenjing.entity.DepartureDate;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-5-12 下午5:19:15
 * 类说明 : 出发地与出发日期关联  Service
 */
@Service
public class DepartureDateService {
	@Resource
	private DepartureDateMapper departureDateMapper;

	/**
	 * 根据tordateid查询
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public String findByTourDateId(String tourdateid) {
		List<DepartureDate> departures = departureDateMapper.findByTourDateId(tourdateid);
		StringBuffer buffer = new StringBuffer();
		String departureidstr = "";
		if(departures!=null && !departures.isEmpty()){
			for (DepartureDate departureDate : departures) {
				buffer.append(", " + departureDate.getDepartureid() + "");
			}
			departureidstr = (buffer.substring(2, buffer.length())).toString();
		}
		return departureidstr;
	}
}

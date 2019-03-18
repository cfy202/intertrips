package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.SellInfoMapper;
import com.wenjing.entity.SellInfo;
import com.wenjing.util.Tools;

/**
 * @ClassName SellInfoService
 * @PackageName com.wenjing.service
 * @Description 成团人数
 * @author Bowden
 * @Date 2016-2-25 下午4:59:14
 * @Version V1.0
 */
@Service
public class SellInfoService {

	@Resource
	private SellInfoMapper sellInfoMapper;

	@Transactional
	public void updateSellInfo(SellInfo sellInfo){
		String departureDate = ""; //出发日期
		String tourlineId = ""; //线路id
		String tourDateId = ""; //出发日期id
		String costNum = ""; //销售中心id
		if(sellInfo != null){
			departureDate = sellInfo.getDate();
			tourlineId = sellInfo.getTourlineId();
			tourDateId = sellInfo.getTourdateId();
			costNum = sellInfo.getCostNumber();
			//查询日期是否存在
			SellInfo sellInfoNew = new SellInfo(departureDate, tourlineId, costNum, tourDateId);
			List<SellInfo> sellInfoList = sellInfoMapper.findSellInfo(sellInfoNew);
			if(sellInfoList != null && sellInfoList.size() > 0){ //日期存在，更新售出人数
				if(sellInfoList.size() == 1){
					String id = sellInfoList.get(0).getId();
					sellInfoMapper.updateSellNumById(id);
				}
			}else { //日期不存在，新增日期
				sellInfo.setId(Tools.getUUID());
				sellInfo.setSellNum(1);
				sellInfoMapper.insert(sellInfo);
			}
		}
	}
}
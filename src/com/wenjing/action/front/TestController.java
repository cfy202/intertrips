package com.wenjing.action.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenjing.entity.SellInfo;
import com.wenjing.service.SellInfoService;

/** 
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2016-2-25 下午5:43:51 
 * 类说明 : 测试
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@Resource
	private SellInfoService sellInfoService;
	
	@RequestMapping("/saveSellInfo")
	public void test(){
//		SellInfo sellInfo = new SellInfo();
//		sellInfo.setTotalNum(50);
//		sellInfo.setDate("2016-02-25");
//		sellInfo.setTourdateId("09cbd4e2aac7495ba17300051989ca7d");
//		sellInfo.setTourlineId("266e887ae66044d4935dea559ff00211");
//		sellInfo.setCostNumber("bbebc7de2fdf470c854620501fef4dd1");
		SellInfo sellInfo = new SellInfo();
		sellInfo.setTotalNum(50);
		sellInfo.setDate("2016-02-25");
		sellInfo.setTourdateId("14d75925e4c44aefbf375801d73daddf");
		sellInfo.setTourlineId("00f40ca98e764a0b8f1baa90ca4c8c36");
		sellInfo.setCostNumber("bbebc7de2fdf470c854620501fef4dd1");
		sellInfoService.updateSellInfo(sellInfo);
	}
}

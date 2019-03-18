
package com.wenjing.dao;

import com.wenjing.entity.TourlineTourdate;

/**
 * 类说明
 * @author xiejin
 * @date 2015-9-11 
 * @date 2015-9-11 下午1:55:28
 */
public interface TourlineTourdateMapper {

	int insert(TourlineTourdate record);
	
	/**
	 * @Title: delete
	 * @Description: 删除表所有数据
	 * @return    
	 * @return int    返回类型
	 * @author xiejin
	 */
	int delete();
	
}

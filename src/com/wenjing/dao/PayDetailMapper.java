/**
 * 
 */
package com.wenjing.dao;

import com.wenjing.entity.PayDetail;

/**
 * 类说明
 * @author xiejin
 * @date 2015-7-21 
 * @date 2015-7-21 上午10:14:16
 */
public interface PayDetailMapper {
	
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-22 23:01:26
     */
    int insert(PayDetail record);

}

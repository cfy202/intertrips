package com.wenjing.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.GetScoreMapper;
import com.wenjing.entity.GetScore;

/**
 * @author 作者 E-mail: bowden
 * @version 创建时间：2015-7-3 下午3:03:01
 * 类说明 : 积分 - service
 */
@Service
public class ScoreService {
	@Resource
	private GetScoreMapper getScoreMapper;

	/**
	 * 批量插入基本信息获取积分项
	 * @param gScoreList
	 */
	@Transactional
	public int batchAddMemberInfo(List<GetScore> gScoreList) {
		return getScoreMapper.batchAddMemberInfo(gScoreList);
	}
	
	/**
	 * 插入获取积分记录
	 */
	@Transactional
	public int insert(GetScore getScore){
		return getScoreMapper.insertSelective(getScore);
	}
	
	/**
	 * 根据会员ID查找
	 * 
	 * @param memberId
	 * @return
	 */
	public List<GetScore> findByMemberId(String memberId){
		return getScoreMapper.findByMemberId(memberId);
	}
}

package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.Visaoccupation;

public interface VisaoccupationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Visaoccupation record);

    int insertSelective(Visaoccupation record);

    Visaoccupation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Visaoccupation record);

    int updateByPrimaryKey(Visaoccupation record);
    
    List<Visaoccupation> findAllByVisaId(String visaId);
    
    int getMaxSort();
    
    List<Visaoccupation> findByVisaId(String visaId);
    
    List<Visaoccupation> findByVisaType(String visaId);
    
}
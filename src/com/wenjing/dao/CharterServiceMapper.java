package com.wenjing.dao;

import java.util.List;

import com.wenjing.entity.CharterService;


public interface CharterServiceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table busservice
     *
     * @mbggenerated Fri Nov 20 17:54:50 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table busservice
     *
     * @mbggenerated Fri Nov 20 17:54:50 CST 2015
     */
    int insert(CharterService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table busservice
     *
     * @mbggenerated Fri Nov 20 17:54:50 CST 2015
     */
    int insertSelective(CharterService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table busservice
     *
     * @mbggenerated Fri Nov 20 17:54:50 CST 2015
     */
    CharterService selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table busservice
     *
     * @mbggenerated Fri Nov 20 17:54:50 CST 2015
     */
    int updateByPrimaryKeySelective(CharterService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table busservice
     *
     * @mbggenerated Fri Nov 20 17:54:50 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(CharterService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table busservice
     *
     * @mbggenerated Fri Nov 20 17:54:50 CST 2015
     */
    int updateByPrimaryKey(CharterService record);
    
    /**
     * 查询所有的busservice
     * @return
     */
    List<CharterService> findAll();
}
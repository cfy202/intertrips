package com.wenjing.dao;

import com.wenjing.entity.AgentCode;

public interface AgentCodeMapper {
    AgentCode selectByPrimaryKey(String id);
    
    
    AgentCode selectByLowerCode(String agentCode);
}
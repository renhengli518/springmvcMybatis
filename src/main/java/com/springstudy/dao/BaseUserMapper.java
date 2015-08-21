package com.springstudy.dao;

import java.util.List;

import com.springstudy.entity.BaseUser;

public interface BaseUserMapper {
    int deleteByPrimaryKey(String baseUserId);

    int insert(BaseUser record);

    int insertSelective(BaseUser record);

    BaseUser selectByPrimaryKey(String baseUserId);

    int updateByPrimaryKeySelective(BaseUser record);

    int updateByPrimaryKey(BaseUser record);
    
    List<BaseUser> getAllUsers();
}
package com.springstudy.dao;

import java.util.List;

import com.springstudy.entity.BaseUser;
import commons.page.Page;

public interface BaseUserMapper {
    int deleteByPrimaryKey(String baseUserId);

    int insert(BaseUser record);

    int insertSelective(BaseUser record);

    BaseUser selectByPrimaryKey(String baseUserId);

    int updateByPrimaryKeySelective(BaseUser record);

    int updateByPrimaryKey(BaseUser record);
    
    List<BaseUser> getAllUsers();
    
    List<BaseUser> getUserInfoPageList(Page page);
}
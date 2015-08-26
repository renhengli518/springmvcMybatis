package com.springstudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.dao.BaseUserMapper;
import com.springstudy.entity.BaseUser;
import commons.page.Page;

/**
 * 
 * @author renhengli
 *
 */
@Service
public class BaseUserService {
	
	@Autowired
	private BaseUserMapper baseUserMapper;
	
	public BaseUser selectByPrimaryKey(String baseUserId){
		return baseUserMapper.selectByPrimaryKey(baseUserId);
	}
	
	public List<BaseUser> getAllUsers(){
		return baseUserMapper.getAllUsers();
	}
	
	public Page getUserInfoPageList(Page page){
		List<BaseUser> list =  baseUserMapper.getUserInfoPageList(page);
		page.setData(list);
		return page;
	}

}

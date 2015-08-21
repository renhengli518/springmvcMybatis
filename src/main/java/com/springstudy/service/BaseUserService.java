package com.springstudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.dao.BaseUserMapper;
import com.springstudy.entity.BaseUser;

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

}

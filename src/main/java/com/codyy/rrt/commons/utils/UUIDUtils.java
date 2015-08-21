package com.codyy.rrt.commons.utils;

import java.util.UUID;

public class UUIDUtils {

	public static String getUUID(){
		
		String uuid = UUID.randomUUID().toString().replace("-", "") ;
		return uuid ;
	}
	
}

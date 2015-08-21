package com.codyy.rrt.commons.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * ClassName:RegexUtils
 * Function: 正则表达式处理工具类
 *
 * @author   zhangtian
 * @Date	 2015	Jan 28, 2015		5:36:28 PM
 *
 */
public class RegexUtils {
	
	private static String[] regexs = new String[]{"*",".","^","?","$","[","]","{","}","(",")","+","|","$","w","W","d","D","s","S"} ;
	/**
	 * 
	 * filterRegex:(过滤正则表达式特殊字符)
	 *
	 * @param regex
	 * @return
	 * @author zhangtian
	 */
	public static String filterRegex(String regex) {
		
		if(StringUtils.isNotBlank(regex)) {
		
			if(StringUtils.contains(regex, "\\")) {
				regex = StringUtils.replace(regex, "\\", "\\\\") ;
			}
			
			for(String reg : regexs) {
				if(!StringUtils.contains(regex, reg)) 
					continue ;
				regex = StringUtils.replace(regex, reg, "\\"+reg) ;
			}
		}
		
		return regex ;
	}
}

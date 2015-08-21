package commons.utils;

import org.apache.commons.lang.StringUtils;

public class OracleKeyWordUtils {
	
	/**
	 * oracle关键字查询转义
	 * @param str
	 * @return
	 */
	public final static String FILTER_CHAR = "/";
	
	public final static String PERCENTAGE_CHAR = "%";
	
	public final static String UNDERLINE_CHAR = "_";
	
	public static String oracleKeyWordReplace(String str){
		if(StringUtils.isNotBlank(str)){
			return str.replaceAll(FILTER_CHAR, FILTER_CHAR+FILTER_CHAR).replaceAll(PERCENTAGE_CHAR, FILTER_CHAR+PERCENTAGE_CHAR).replaceAll(UNDERLINE_CHAR, FILTER_CHAR+UNDERLINE_CHAR);
		}
		return str;
	}
}

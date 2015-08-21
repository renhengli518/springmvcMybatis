package commons.utils;

import java.util.Random;



/**
 * 
 * ClassName:StringUtils
 * Function: 字符串个性化处理
 *
 * @author   zhangtian
 * @Date	 2015	2015年5月5日		上午11:12:06
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static String[] oldValue = new String[]{"&lt;", "&gt;","&amp;"} ;
	public static String[] newValue = new String[]{"<", ">","&"} ;
	
	/**
	 * 替换特殊字符
	 * @param str
	 * @return
	 * @author zhangtian
	 */
	public static String replaceEscapeChar(String str) {
		return StringUtils.replaceEach(str, oldValue, newValue) ;
	}
	
	/**
	* 生成随机码
	*
	* @param pwd_len
	* 生成的码的总长度
	* @return 密码的字符串
	*/
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
		
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
	
		return pwd.toString();
	}
	
	/**
	 * @author xierongbing
	 * @date 2015年7月30日 下午3:16:46
	 * @param str
	 * @return boolean
	 * @description 
	 * 如果字符串是null或者为"",就返回true,否则返回false
	 */
	public static boolean isEmpty(String str){
		return str == null || str.equals("");
	}
	
	/**
	 * @author xierongbing
	 * @date 2015年8月12日 下午2:41:07
	 * @param str
	 * @param size
	 * @param defaultChar
	 * @return String
	 * @description
	 * 填充字符：如果过长就截取，如果过短就填充字符
	 */
	public static String fillFixedLength(String str, int size, char defaultChar){
		if(str.length() > size){
			return str.substring(0,size);
		}else if(str.length()<size){
			StringBuilder sb = new StringBuilder(str);
			for(int i=0;i<size-str.length();i++){
				sb.append(defaultChar);
			}
			return sb.toString();
		}
		return str;
	}
	
	
	/**
	 * 日期转中文
	 * toCNDaySeq
	 *
	 * @param daySeq
	 * @return
	 * @author xiongping
	 */
	public static String toCNDaySeq(int daySeq){
		String dayStr = "";
		switch (daySeq) {
		case 1:
			dayStr = " 星期一";
			break;
		case 2:
			dayStr = " 星期二";
			break;
		case 3:
			dayStr = " 星期三";
			break;
		case 4:
			dayStr = " 星期四";
			break;
		case 5:
			dayStr = " 星期五";
			break;
		case 6:
			dayStr = " 星期六";
			break;
		case 7:
			dayStr = " 星期日";
			break;
		}
		return dayStr;
	}
	/**
	 * 课程转中文
	 * toCNClassSeq
	 *
	 * @param classSeq
	 * @return
	 * @author xiongping
	 */
	public static String toCNClassSeq(int classSeq){
		String classStr = "";
		switch (classSeq) {
		case 1:
			classStr = " 第一节";
			break;
		case 2:
			classStr = " 第二节";
			break;
		case 3:
			classStr = " 第三节";
			break;
		case 4:
			classStr = " 第四节";
			break;
		case 5:
			classStr = " 第五节";
			break;
		case 6:
			classStr = " 第六节";
			break;
		case 7:
			classStr = " 第七节";
			break;
		case 8:
			classStr = " 第八节";
			break;
		}
		return classStr;
	}
	
	/**
	 * getLowerArea:(截取区域的末级区域)
	 * @param areaName
	 * @return
	 * @author zhangtian
	 */
	public static String getLowerArea(String areaName) {
		
		if(StringUtils.isNotBlank(areaName)) {
			String[] str = areaName.split("-") ;
			return str[str.length-1] ;
		}
		return "-" ;
	}
}

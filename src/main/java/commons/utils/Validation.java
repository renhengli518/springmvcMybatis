package commons.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 功能描述：验证类 ，提供常用的转型 <br>
 */
public class Validation {

	private static final Log log = LogFactory.getLog(Validation.class);

	/**
	 * 验证整数的正则式
	 */
	private static final String P_INT = "^\\d+$";
	/**
	 * 验证浮点数的正则式
	 */
	private static final String P_FLOAT = "^\\d+(\\.\\d+){0,1}$";
	/**
	 * 验证电话号码的正则式
	 */
	private static final String P_PHONE = "^\\d+(-\\d+)*$";
	
	/**
	 * 验证联系方式的正则（电话||手机）
	 */
	private static final String P_CONTACT_PHONE = "^\\d{7,8}$|^\\d{3,4}-\\d{7,8}$|^1[3|4|5|8][0-9]\\d{4,8}$";
	
	/*
	 * 邮箱有效性验证
	 */
	private static final String P_CONTACT_EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$" ;
	
	/**
	 * 验证用户名
	 */
	private static final String P_USERNAME = "^[0-9a-zA-Z|,|.|;|~|!|@|@|#|$|%|\\^|&|*|(|)|_|+|-|=|\\\\|/|<|>]{6,18}$";
	/**
	 * 验证 e-mail 的正则式
	 */
	private static final String P_EMAIL = "^[a-zA-Z_]\\w*@\\w+(\\.\\w+)+$";

	/**
	 * 验证是否为整数
	 */
	public static final int INT = 1;
	/**
	 * 验证是否为浮点数
	 */
	public static final int FLOAT = 2;
	/**
	 * 验证是否为电话号码
	 */
	public static final int PHONE = 3;
	/**
	 * 验证是否为 e-mail
	 */
	public static final int EMAIL = 4;
	/**
	 * 验证联系方式（电话||手机）
	 */
	public static final int CONTACT_PHONE = 5;
	
	/**
	 * 验证用户名
	 */
	public static final int USERNAME = 6;

	/**
	 * 对字符串进行验证
	 * @param input
	 * 需要验证的字符串
	 * @param matcher
	 * 验证规则
	 * @return 验证是否通过
	 */
	public static boolean validate(String input, int matcher) {
		if (isNULL(input)) {
			return false;
		}
		String regex = null;
		switch (matcher) {
			case INT:
				regex = P_INT;
				break;
			case FLOAT:
				regex = P_FLOAT;
				break;
			case PHONE:
				regex = P_PHONE;
				break;
			case EMAIL:
				regex = P_EMAIL;
				break;
			case CONTACT_PHONE:
				regex = P_CONTACT_PHONE;
				break;
			default:
				return false;
		}
		return Pattern.matches(regex, input);
	}

	public static Boolean isNULL(Object... value) {
		if (null == value || value.length < 1) {
			return true;
		} else {
			for (int i = 0; i < value.length; i++) {
				if (null == value[i] || "".equals(value[i].toString()) || "null".equals(value[i].toString().toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean strValidate(String str,int minLength,int maxLength,boolean isRequired){
		if(StringUtils.isBlank(str)){
			str = "";
		}
		str = str.trim();
		if(isRequired){
			if(str.length() == 0){
				return false;
			}
		}else{
			if(str.length() == 0){
				return true;
			}
		}
		if(str.length()>=minLength && str.length() <=maxLength){
			return true;
		}else{
			return false;
		}

	}
	
	public static boolean isContactPhone(String str,boolean isRequired){
		if(StringUtils.isBlank(str)){
			str = "";
		}
		str = str.trim();
		if(isRequired){
			if(str.length() == 0){
				return false;
			}
		}else{
			if(str.length() == 0){
				return true;
			}
		}
		return Pattern.matches(Validation.P_CONTACT_PHONE, str);
	}
	
	public static boolean isContactEmail(String str,boolean isRequired) {
		if(StringUtils.isBlank(str)) {
			str = "" ;
		}
		str = str.trim() ;
		if(isRequired) {
			if(str.length() == 0) {
				return false ;
			} 
		} else {
			if(str.length() == 0) {
				return true ;
			}
		}
		
		if(str.length() > 50) {
			return false ;
		}
		
		return Pattern.matches(Validation.P_CONTACT_EMAIL, str) ;
	}
	
	public static boolean isUserName(String str){
		if(StringUtils.isBlank(str)){
			str = "";
		}
		str = str.trim();
		return Pattern.matches(Validation.P_USERNAME, str);
	}
}
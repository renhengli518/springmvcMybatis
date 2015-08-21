package commons.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

public class SecurityWrapper extends HttpServletRequestWrapper {
	
	private final static String SECURITY_PROPERTIES = "securityFilter.properties";// 定义需要过滤的url
	private boolean filterAll = true;
	private static Map<Character, String> transferMap = null;// 需要转换的字符
	private static Map<String, String> transferUrl = null;// 需要转换的URL
	private Set<String> parameters = new HashSet<String>();

	static {
		transferMap = new HashMap<Character, String>();
		transferMap.put('<', "&lt;");
		transferMap.put('>', "&gt;");
		transferMap.put('&', "&amp;");
		InputStream input = SecurityWrapper.class.getResourceAsStream(SECURITY_PROPERTIES);
		Properties prop = new Properties();
		try {
			prop.load(input);
			Set<Object> keySet = prop.keySet();
			input.close();
			transferUrl = new HashMap<String, String>();
			if (keySet.size() > 0) {
				for (Object object : keySet) {
					transferUrl.put(object.toString(), prop.getProperty(object.toString()));
				}
			} else {
			}
		} catch (IOException e) {
			System.out.println("读取安全过滤url失败");
			e.printStackTrace();
		}
	}

	public SecurityWrapper(HttpServletRequest request) throws Exception {
		super(request);
		String location = request.getServletPath();
		Set<String> filterSet = transferUrl.keySet();
		for (String string : filterSet) {
			if (location.contains(string)) {// 判定是否需要过滤
				filterAll = false;
				String strParameter = transferUrl.get(string);
				setParameters(strParameter);
				break;
			}
		}
	}

	private String filterSecurity(String value,String parameterName) {
		if (filterAll) {
			if(StringUtils.isNotBlank(value)){
				value = trans(value);
			}
		}else{
			if(StringUtils.isNotBlank(value) && (!parameters.contains(parameterName))){
				value = trans(value);
			}
		}
		return value;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value == null){
			return null;
		}
		return filterSecurity(value,name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> paramMap = super.getParameterMap();
		Map<String, String[]> map = new HashMap<String, String[]>();
		String parameterKey;
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			String[] values = entry.getValue();
			parameterKey = entry.getKey();
			if (values != null && values.length > 0) {
				String[] newValues = new String[values.length];
				for (int i = 0; i < values.length; i++) {
					newValues[i] = filterSecurity(values[i],parameterKey);
				}
				map.put(parameterKey, newValues);
			}
		}
		return map;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				values[i] = filterSecurity(values[i],name);
			}
		}
		return values;
	}

	private String trans(String value) {
		if (value == null) {
			return value;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			String str = transferMap.get(ch);
			if (str != null) {
				buffer.append(str);
			} else {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	public static String tansValue(String value) {
		if (value == null) {
			return value;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			String str = transferMap.get(ch);
			if (str != null) {
				buffer.append(str);
			} else {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	/*private Map<String, String> getSecurityURLExcept() {
		Map<String,String> result = new HashMap<String, String>();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(SECURITY_PROPERTIES);
		Properties prop = new Properties();
		try {
			prop.load(input);
			Set<Object> keySet = prop.keySet();
			input.close();
			if (keySet.size() > 0) {
				for (Object object : keySet) {
					result.put(object.toString(), "1");
				}
			} else {
			}
		} catch (IOException e) {
			System.out.println("读取安全过滤url失败");
			e.printStackTrace();
		}
		
		StringRedisTemplate template = SpringContext.getBean(StringRedisTemplate.class);
		BoundHashOperations<String, String, String> operations = template.boundHashOps(CommonsConstant.SECURITY_EXCEPT);
		Map<String, String> map = operations.entries();
		if (map.size() > 0) {
			return map;
		} else {
			try {
				InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(SECURITY_PROPERTIES);
				Properties prop = new Properties();
				prop.load(input);
				Set<Object> keySet = prop.keySet();
				input.close();
				if (keySet.size() > 0) {
					for (Object object : keySet) {
						map.put(object.toString(), "1");
					}
					operations.putAll(map);
					return map;
				} else {
					return map;
				}
			} catch (IOException e) {
				System.out.println("读取免过滤url错误");
				e.printStackTrace();
				return map;
			}
		}
	
		return result;
	}*/

	private void setParameters(String strParameter) {
		if(StringUtils.isNotBlank(strParameter)){
			String [] arr = strParameter.split(",");
			for(int i = 0,j = arr.length;i<j;i++){
				parameters.add(arr[i]);
			}
		}
	}
	
	
}

package commons.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class MybatisMapConvert {
	
	public static Map<String, Object> convertMap(Map<String, Object> map){
		return convertMap(map, null);
	}
	
	public static Map<String, Object> convertMap(Map<String, Object> map, String dateFormat){
		Map<String, Object> result = new HashMap<String, Object>();
		for(Map.Entry<String, Object> entry:map.entrySet()){
			String key = convertKey(entry.getKey());
			Object value = entry.getValue();
			if (StringUtils.isNotBlank(dateFormat)){
				System.out.println(value.getClass().getName());
				if (value instanceof java.sql.Timestamp){
					value = DateUtils.format((java.sql.Timestamp)value, dateFormat);
				} else if (value instanceof java.sql.Date){
					value = DateUtils.format((java.sql.Date)value, dateFormat);
				} else if (value instanceof java.sql.Time){
					value = DateUtils.format((java.sql.Time)value, dateFormat);
				} else if (value instanceof java.util.Date){
					value = DateUtils.format((java.util.Date)value, dateFormat);
				} else if (value instanceof oracle.sql.TIMESTAMP){
					oracle.sql.TIMESTAMP t = (oracle.sql.TIMESTAMP)value;
					try {
						value = DateUtils.format(t.timestampValue(), dateFormat);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} 
			}
			result.put(key, value);
		}
		return result;
	}
	
	private static String convertKey(String str){
		StringBuffer buffer = new StringBuffer();
		boolean upper = false;
		for (int i=0;i<str.length();i++){
			char ch = str.charAt(i);
			if (ch == '_'){
				upper = true;
			} else if (upper){
				buffer.append(Character.toUpperCase(ch));
				upper = false;
			} else {
				buffer.append(Character.toLowerCase(ch));
			}
		}
		return buffer.toString();
	}

}

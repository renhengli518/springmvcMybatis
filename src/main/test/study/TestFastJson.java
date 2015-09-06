package study;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springstudy.entity.User;

/**
 * 
 * @author renhengli
 *
 */
public class TestFastJson {
	public static void main (String [] args){
		String test = "{'name':'张三','age':17}";
		//将json字符串转成json对象
		JSONObject json = JSONObject.parseObject(test);
		JSONObject json1 = JSON.parseObject(test);
		System.out.println(json);
		System.out.println(json1);
		
		//将json对象转成javabean
		User user = JSON.toJavaObject(json1, User.class);
		System.out.println(user);
		
		//将json字符串转成javabean
		User user1 = JSON.parseObject(test, User.class);
		System.out.println(user1);
		
		//将javabean 对象转成Json 字符串
		String str = JSON.toJSONString(user1);
		System.out.println(str);
		
		//将javabean 对象转成json对象
		JSONObject json2 = (JSONObject) JSON.toJSON(user1);
		System.out.println(json2);
		
		
	}

}

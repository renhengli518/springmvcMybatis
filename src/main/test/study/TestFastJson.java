package study;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springstudy.entity.User;

/**
 * 
 * @author renhengli
 * 
 */
public class TestFastJson {
	public static void main(String[] args) {
		String test = "{'name':'张三','age':17}";
		// 将json字符串转成json对象
		JSONObject json = JSONObject.parseObject(test);
		JSONObject json1 = JSON.parseObject(test);
		System.out.println(json);
		System.out.println(json1);
		System.out.println(json1==json);
		System.out.println(json1.equals(json));

		// 将json对象转成javabean
		User user = JSON.toJavaObject(json1, User.class);
		System.out.println(user);

		// 将json字符串转成javabean
		User user1 = JSON.parseObject(test, User.class);
		System.out.println(user1);

		// 将javabean 对象转成Json 字符串
		String str = JSON.toJSONString(user1);
		System.out.println(str);

		// 将javabean 对象转成json对象
		JSONObject json2 = (JSONObject) JSON.toJSON(user1);
		System.out.println(json2);

		// map转json
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("name", "zhang");
		hashMap.put("sex", "1");
		hashMap.put("login", "Jack");
		hashMap.put("password", "123abc");
		Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> s = it.next();
			System.out.println(s.getKey()+":"+s.getValue());
		}

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String userMapJson = objectMapper.writeValueAsString(hashMap);
			JsonNode node = objectMapper.readTree(userMapJson);
			// 输出结果转意，输出正确的信息
			System.out.println(node.get("password").asText());
			// 输出不转意,输出结果会包含""，这是不正确的，除非作为json传递，如果是输出结果值，必须如上一行的操作
			System.out.println(node.get("name"));
		} catch (IOException e) {
		
		}

	}

}

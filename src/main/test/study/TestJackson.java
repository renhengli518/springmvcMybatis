package study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.springstudy.entity.User;

public class TestJackson {
	public static void main(String [] args) throws JsonGenerationException, JsonMappingException, IOException{
		//SimpleDateFormat format = new SimpleDateFormat();
		User user = new User();
		user.setAge(16);
		user.setDate(new Date());
		user.setName("张三");
		//java对象转json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		System.out.println(json);
		
		//java集合转json
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		ObjectMapper mapper2 = new ObjectMapper();
		String json2 = mapper2.writeValueAsString(list);
		System.out.println(json2);
		
		/** 
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。 
         */  
        String json1 = "{\"name\":\"小民\",\"age\":20,\"date\":844099200000}";  
        ObjectMapper mapper1 = new ObjectMapper();  
        User user1 = mapper1.readValue(json1, User.class);  
        System.out.println(user1);  
	}

}

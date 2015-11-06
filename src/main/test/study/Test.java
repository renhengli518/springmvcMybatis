package study;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.xml.XMLSerializer;

import com.springstudy.entity.User;

/**
 * Json转换
 * 
 * @author renhengli 2015-09-02 10:11:53
 * 
 */
public class Test {
	// 创建JSONObject对象
	private static JSONObject createJSONObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "huangwuyi");
		jsonObject.put("sex", "男");
		jsonObject.put("QQ", "413425430");
		jsonObject.put("Min.score", new Integer(99));
		jsonObject.put("nickname", "梦中心境");
		return jsonObject;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws ParseException {
		String str = "{'a':'1','b':'2','c':'3'}";
		// Object t = JsonUtil.fromJson(str, null);
		Object t = JSONObject.toBean(JSONObject.fromObject(str));
		JSONObject obj = JSONObject.fromObject(t);
		Set set = obj.keySet();
		for (Object key : set) {
			System.out.println(obj.get(key));
		}
		System.out.println(obj.toString());
		// 把带有日期的user对象转成json 字符串
		User user = new User();
		user.setName("zhangsan");
		user.setDate(new Date());
		JSONObject s = JSONObject.fromObject(user);
		System.out.println("======================" + s.getJSONObject("date").toString());
		System.out.println("======================" + s.get("date").toString());
		
		//将对象转成json 字符串中带有日期的字符串格式化输出 方法一
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		Object time1 =JSONObject.fromObject(s.get("date")).get("time");
		Date date = new Date(Long.valueOf(time1.toString()));
		String date1 = format.format(new Date(date.getTime()));
		System.out.println(date1);
		
		//将对象转成json 字符串中带有日期的字符串格式化输出 方法二
		Object time =JSONObject.fromObject(s.get("date")).get("time");
		long sd=Long.valueOf(time.toString());  
        Date dat=new Date(sd);  
        GregorianCalendar gc = new GregorianCalendar();   
        gc.setTime(dat);  
        String sb=format.format(gc.getTime());  
        System.out.println(sb);  
		
		// 把带有日期的json字符串反转成user对象
		JSONObject y = JSONObject.fromObject(s.toString());
		User x = (User) JSONObject.toBean(y, User.class);
		System.out.println(x.getDate());

		JSONObject jsonObject = Test.createJSONObject();// 静待方法，直接通过类名+方法调用
		// 输出jsonobject对象
		System.out.println("jsonObject：" + jsonObject);

		// 判读输出对象的类型
		boolean isArray = jsonObject.isArray();
		boolean isEmpty = jsonObject.isEmpty();
		boolean isNullObject = jsonObject.isNullObject();
		System.out.println("是否为数组:" + isArray + "， 是否为空:" + isEmpty + "， isNullObject:" + isNullObject);

		// 添加属性，在jsonObject后面追加元素。
		jsonObject.element("address", "福建省厦门市");
		System.out.println("添加属性后的对象：" + jsonObject);

		// 返回一个JSONArray对象
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, "this is a jsonArray value");
		jsonArray.add(1, "another jsonArray value");
		jsonObject.element("jsonArray", jsonArray);
		// 在jsonObject后面住家一个jsonArray
		JSONArray array = jsonObject.getJSONArray("jsonArray");
		System.out.println(jsonObject);

		System.out.println("返回一个JSONArray对象：" + array);
		// 添加JSONArray后的值
		// {"username":"huangwuyi","sex":"男","QQ":"413425430","Min.score":99,"nickname":"梦中心境","address":"福建省厦门市","jsonArray":["this is a jsonArray value","another jsonArray value"]}
		System.out.println("结果=" + jsonObject);

		// 根据key返回一个字符串
		String username = jsonObject.getString("username");
		System.out.println("username==>" + username);

		// 把字符转换为 JSONObject
		String temp = jsonObject.toString();
		JSONObject object = JSONObject.fromObject(temp);
		// 转换后根据Key返回值
		System.out.println("qq=" + object.get("QQ"));

		JSONObject jsonObj0 = new JSONObject();
		JSONObject jsonObj1 = new JSONObject();
		JSONObject jsonObj2 = new JSONObject();
		JSONObject jsonObj3 = new JSONObject();
		JSONArray jsonArray1 = new JSONArray();

		// 创建jsonObj0
		jsonObj0.put("name0", "zhangsan");
		jsonObj0.put("sex1", "female");
		System.out.println("jsonObj0:" + jsonObj0);

		// 创建jsonObj1
		jsonObj1.put("name", "xuwei");
		jsonObj1.put("sex", "male");
		System.out.println("jsonObj1:" + jsonObj1);

		// 创建jsonObj2，包含两个条目，条目内容分别为jsonObj0，jsonObj1
		jsonObj2.put("item0", jsonObj0);
		jsonObj2.put("item1", jsonObj1);
		System.out.println("jsonObj2:" + jsonObj2);

		// 创建jsonObj3，只有一个条目，内容为jsonObj2
		jsonObj3.element("j3", jsonObj2);
		System.out.println("jsonObj3:" + jsonObj3);

		// 往JSONArray中添加JSONObject对象。发现JSONArray跟JSONObject的区别就是JSONArray比JSONObject多中括号[]
		jsonArray1.add(jsonObj0);
		jsonArray1.add(jsonObj1);
		jsonArray1.add(jsonObj2);
		jsonArray1.add(jsonObj3);
		System.out.println("jsonArray:" + jsonArray1);

		JSONObject jsonObj4 = new JSONObject();
		jsonObj4.element("weather", jsonArray1);
		System.out.println("jsonObj4:" + jsonObj4);

		// map 与 json 互转
		// Map转json字符串
		Map map = new HashMap();
		User user1 = new User();
		User user2 = new User();
		user1.setName("zhangsan");
		user1.setDate(new Date());
		user2.setName("lisi");
		user2.setDate(new Date());
		map.put("1", user1);
		map.put("2", user2);
		JSONObject jsonMap = JSONObject.fromObject(map);
		System.out.println(jsonMap);

		// json字符串转Map
		String jsondata = "{\"1\":{\"date\":\"" + new Date() + "\",\"name\":\"nxj\"},\"2\":{\"date\":\"" + new Date()
				+ "\",\"name\":\"lwc\"}}";
		Map map1 = (Map) JSONObject.fromObject(jsondata);
		Set set1 = map1.keySet();
		Iterator ite = set1.iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			JSONObject jsonObject_1 = JSONObject.fromObject(map1.get(key));
			User user3 = (User) JSONObject.toBean(jsonObject_1, User.class);
			System.out.println(key + " " + user3);
		}

		// List转json字符串
		List list = new ArrayList();
		list.add(user1);
		list.add(user2);
		JSONArray _jsonArray = JSONArray.fromObject(list);
		System.out.println("List转json字符串:"+_jsonArray);

		// json字符串转List
		List list1 = new ArrayList();
		String jsondata_1 = "[{\"date\":\"" + new Date() + "\",\"username\":\"lwc\"},{\"date\":\"" + new Date()
				+ "\",\"username\":\"nxj\"}]";
		JSONArray _jsonArray1 = JSONArray.fromObject(jsondata_1);
		for (int i = 0; i < _jsonArray1.size(); i++) {
			JSONObject jsonObject2 = _jsonArray1.getJSONObject(i);
			User stu2 = (User) JSONObject.toBean(jsonObject2, User.class);
			list1.add(stu2);
		}
		System.out.println(list1);

		// List转型JSONArray
		List<User> list_1 = new ArrayList<User>();
		list_1.add(user1);
		list_1.add(user2);
		JSONArray jsonArray_x = JSONArray.fromObject(list_1);
		System.out.println(jsonArray_x.toString());

		// JSONArray转型List
		List<User> list2 = JSONArray.toList(jsonArray_x, new User(), new JsonConfig());
		Iterator<User> ite_x = list2.iterator();
		while (ite_x.hasNext()) {
			User stu = ite_x.next();
			System.out.println("-------------" + stu);
		}

		// Java数组转JSONArray
		boolean[] boolArray = new boolean[] { true, false, true };
		JSONArray jsonArray_y = JSONArray.fromObject(boolArray);
		System.out.println(jsonArray_y.toString());

		// JSONArray转Java数组
		Object obj_y[] = jsonArray_y.toArray();
		for (Object o : obj_y) {
			System.out.println(o + " ");
		}

		// XML转JSON
		String xml = "<root>" + "<name type='type'>zhaipuhong</name>" + "<gender>male</gender>" + "<birthday>"
				+ "<year>1970</year>" + "<month>12</month>" + "<day>17</day>" + "</birthday>" + "</root>";
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		System.out.println(json.toString(2));

		// JSON转XML
		String jsondata_z = "{\"root\":{" + "\"name\":\"zhaipuhong\"," + "\"gender\":\"male\"," + "\"birthday\":{"
				+ "\"year\":\"1970\"," + "\"month\":\"12\"," + "\"day\":\"17\"" + "}" + "}" + "}";
		JSONObject jsonObject_z = JSONObject.fromObject(jsondata_z);
		String xmlstr = new XMLSerializer().write(jsonObject_z);
		System.out.println(xmlstr);

	}

}

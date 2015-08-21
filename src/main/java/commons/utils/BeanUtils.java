package commons.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	/**
	 * 把集合转换为map,map的key和value取集合中bean对象的属性
	 * @param collection
	 * @param keyProperty
	 * @param valueProperty
	 * @return
	 * @throws Exception
	 */
	public static Map<Object, Object> collectionToMap(Collection<?> collection, String keyProperty, String valueProperty) throws Exception {
		Map<Object, Object> retMap = new LinkedHashMap<Object, Object>();
		if (collection != null) {
			for (Object obj : collection) {
				Object key = getProperty(obj, keyProperty);
				Object value = getProperty(obj, valueProperty);
				retMap.put(key, value);
			}
		}
		return retMap;
	}

	/**
	 * 将Bean对象转换成Map对象，将忽略掉值为null或size=0的属性
	 * @param obj
	 * 对象
	 * @return 若给定对象为null则返回size=0的map对象
	 */
	public static Map<String, Object> toMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		BeanMap beanMap = new BeanMap(obj);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Object value = beanMap.get(name);
			// 转换时会将类名也转换成属性，此处去掉
			if (value != null && !name.equals("class")) {
				map.put(name, value);
			}
		}
		return map;
	}
	
	/**
	 * 将对象的所有属性值放到map中,不论数据值是不是null
	 */
	public static Map<String, Object> toMapWithNull(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		BeanMap beanMap = new BeanMap(obj);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Object value = beanMap.get(name);
			map.put(name, value);
		}
		return map;
	}

	/**
	 * 该方法将给定的所有对象参数列表转换合并生成一个Map，对于同名属性，依次由后面替换前面的对象属性
	 * 
	 * @param objs
	 * 对象列表
	 * @return 对于值为null的对象将忽略掉
	 */
	public static Map<String, Object> toMap(Object... objs) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object object : objs) {
			if (object != null) {
				map.putAll(toMap(object));
			}
		}
		return map;
	}

	/**
	 * 获取接口的泛型类型，如果不存在则返回null
	 * @param clazz
	 * @return
	 */
	public static Class<?> getGenericClass(Class<?> clazz) {
		Type t = clazz.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			return ((Class<?>) p[0]);
		}
		return null;
	}
}

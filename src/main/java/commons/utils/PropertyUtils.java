package commons.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PropertyUtils extends org.apache.commons.beanutils.PropertyUtils{
	
	/**
	 * 获取某个集合内所有对象的某个属性
	 * @param collection
	 * @param property
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getProperties(Collection<?> collection, String property)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<T> result = new ArrayList<T>(collection.size());
		for(Object object:collection){
			result.add((T)getProperty(object, property));
		}
		return result;
	}
	
	/**
	 * 功能类似于getProperties, 但结果中的数据不重复
	 * @param collection
	 * @param property
	 * @return
	 * @throws Exception
	 */
	public static List<Object> getNorepeatProperties(Collection<?> collection, String property) throws Exception{
		List<Object> result = new ArrayList<Object>(collection.size());
		for(Object object:collection){
			Object propertyValue = getProperty(object, property);
			if(!result.contains(propertyValue)){
				result.add(propertyValue);
			}
		}
		return result;
	}
	
	
	/**
	 * 设置一个集合内的所有对象的某个属性,这些对象的属性的值相同
	 * @param collection
	 * @param property
	 * @param value
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setProperty(Collection<?> collection, String property, Object value)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		for(Object object:collection){
			setProperty(object, property, value);
		}
	}
	
	/**
	 * 设置对象的属性，属性与值的对应关系封装于map对象中
	 * @param bean
	 * @param propertyValueMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setProperty(Object bean, Map<String, Object> propertyValueMap) 
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		for (Map.Entry<String, Object> entry:propertyValueMap.entrySet()){
			String property = entry.getKey();
			Object value = entry.getValue();
			setProperty(bean, property, value);
		}
	}
	
	/**
	 * 设置集合中的所有对象的属性，属性与值的对应关系封装于map对象中
	 * @param collection
	 * @param propertyValueMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setProperty(Collection<?> collection, Map<String, Object> propertyValueMap) 
	throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		for(Object object:collection){
			setProperty(object, propertyValueMap);
		}
	}
	
}

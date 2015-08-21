package commons.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * CollectionUtil处理集合操作，包括过滤，分割，取并集，交集等。
 * CollectionUtil处理集合类型的转换
 * @author haocongping
 */
public class CollectionUtils {
	
	/**
	 * 过滤出集合中其属性propertyName的值等于value的集合<br>
	 * 比较时,property中的值和value的值都为空时,条件也成立
	 * Example:	集合collection中存放的是一些bean对象，要取得这些对象中name属性为abc的对象集合<br>
	 * 			调用CollectionUtils.filter(collection, "name", "abc");<br>
	 * 			返回的结果就是符合这个条件的结果集<br>
	 * Other:	如果collection中存放的是Map对象，也可以使用此方法，Map就象是一个Bean对象，其key会被作为属性的名称，value就是属性的值
	 * @param collection
	 * @param propertyName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> filter(Collection<T> collection, String propertyName, Object value) throws Exception{
		List<T> result = new ArrayList<T>();
		if(collection != null){
			for(T obj:collection){
				Object propertyValue = PropertyUtils.getProperty(obj, propertyName);
				//value和propertyValue如果都为null,条件也成立
				if((value == null && propertyValue == null) 
						|| (value!=null && propertyValue!=null && value.equals(propertyValue))){
					result.add(obj);
				}
			}
		}
		return result;
	}
	
	public static <T> T filterOne(Collection<T> collection, String propertyName, Object value)throws Exception{
		if(collection != null){
			for(T obj:collection){
				Object propertyValue = PropertyUtils.getProperty(obj, propertyName);
				//value和propertyValue如果都为null,条件也成立
				if((value == null && propertyValue == null) 
						|| (value!=null && propertyValue!=null && value.equals(propertyValue))){
					return obj;
				}
			}
		}
		return null;
	}
	
	/**
	 * 过滤出集合中其属性propertyName的值在values范围内的集合<br>
	 * 此方法与filter方法类似，但其属性的值是一个集合<br>
	 * Example:	集合collection中存放的是一些bean对象，要取得这些对象中parentId属性在1,2,3,4这个范围内的对象集合<br>
	 * 			List values = new ArrayList();
	 * 			values.add(new Integer(1));
	 * 			values.add(new Integer(2));
	 * 			values.add(new Integer(3));
	 * 			values.add(new Integer(4));
	 * 			List result = CollectionUtils.filter(collection, "parentId", values);<br>
	 * 			返回的结果就是符合这个条件的结果集<br>
	 * Other:	如果collection中存放的是Map对象，也可以使用此方法，Map就象是一个Bean对象，其key会被作为属性的名称，value就是属性的值
	 * @param collection
	 * @param propertyName
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> filterAll(Collection<T> collection, String propertyName, Collection<?> values) throws Exception{
		List<T> result = new ArrayList<T>();
		for(T obj:collection){
			Object propertyValue = PropertyUtils.getProperty(obj, propertyName);
			if(values.contains(propertyValue)){
				result.add(obj);
			}
		}
		return result;
	}
	
	/**
	 * 祛除掉集合中其属性propertyName的值等于value的集合<br>
	 * 此方法与filter方法相反，filter是获取符合条件的值，exclude是返回不符合条件的值，也就是将符合条件的值祛除掉
	 * 比较时,property中的值和value的值都为空时,条件也成立

	 * @param collection
	 * @param propertyName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> exclude(Collection<T> collection, String propertyName, Object value) throws Exception{
		List<T> result = new ArrayList<T>();
		for(T obj:collection){
			Object propertyValue = PropertyUtils.getProperty(obj, propertyName);
			if((value == null && propertyValue !=null) 
				|| (value != null && propertyValue ==null)
				|| (value != null && propertyValue !=null && !value.equals(propertyValue))){
				result.add(obj);
			}
		}
		return result;
	}
	
	/**
	 * 祛除掉集合中其属性propertyName的值在values范围内的集合<br>
	 * 与exclude方法类似，其条件是一个范围

	 * @param collection
	 * @param propertyName
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> excludeAll(Collection<T> collection, String propertyName, Collection<?> values) throws Exception{
		List<T> result = new ArrayList<T>();
		for(T obj:collection){
			Object propertyValue = PropertyUtils.getProperty(obj, propertyName);
			if(!values.contains(propertyValue)){
				result.add(obj);
			}
		}
		return result;
	}
	
	/**
	 * 将collection中符合条件的bean分离出来,参考filter()方法
	 * 		返回结果是这些符合条件的bean
	 * 		collection被修改,符合条件的bean已经从其中删除

	 * 		比较时,property中的值和value的值都为空时,条件也成立

	 * @param collection
	 * @param propertyName
	 * @param value
	 * @return 
	 * @throws Exception
	 */
	public static <T> List<T> splite(Collection<T> collection, String propertyName, Object value) throws Exception{
		List<T> result = new ArrayList<T>();
		for(Iterator<T> itr = collection.iterator();itr.hasNext();){
			T bean = itr.next();
			Object propertyValue = PropertyUtils.getProperty(bean, propertyName);
			if((value==null && propertyValue==null) 
					|| (value!=null && propertyValue!=null && value.equals(propertyValue))){
				itr.remove();	//从collection中去除掉符合条件的bean
				result.add(bean);
			}
		}
		return result;
	}
	
	
	public static <T> List<T> spliteAll(Collection<T> collection, String propertyName, Collection<?> values) throws Exception{
		List<T> result = new ArrayList<T>();
		for(Iterator<T> itr = collection.iterator();itr.hasNext();){
			T bean = itr.next();
			Object propertyValue = PropertyUtils.getProperty(bean, propertyName);
			if(values.contains(propertyValue)){
				itr.remove();	//从collection中去除掉符合条件的bean
				result.add(bean);
			}
		}
		return result;
	}
	
	/**
	 * @author xierongbing
	 * @date 2015年8月12日 下午7:17:15
	 * @param collection
	 * @param propertyName
	 * @return List<String>
	 * @description
	 * 提取一个集合中对象的某个属性
	 */
	public static List<String> extractValueToList(Collection<?> collection, String propertyName){
		List<String> list = new ArrayList<String>();
		for(Object obj : collection){
			Object propertyValue;
			try {
				propertyValue = PropertyUtils.getProperty(obj, propertyName);
				if(propertyValue != null)
					list.add(propertyValue.toString());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	
	/**
	 * 把set转换为list
	 * @param set
	 * @return
	 */
	public static <T> List<T> setToList(Set<T> set) {
		List<T> list = new ArrayList<T>(set.size());
		list.addAll(set);
		return list;
	}
	
	/**
	 * 数组转换List
	 * @param array
	 * @return
	 */
	public static <T> List<T> arrayToList(T[] array){
		List<T> list = new ArrayList<T>(array.length);
		for (int i=0;i<array.length;i++){
			list.add(array[i]);
		}
		return list;
	}
	
	/**
	 * 比较两个List是否完全相同, 包括长度, 以及List里的每个对象
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> boolean equals(List<T> a, List<T> b) {
		if (a == b)
			return true;
		if (a == null || b == null)
			return false;
		if (a.size() != b.size())
			return false;

		for (int i=0; i<a.size(); i++) {
			T o1 = a.get(i);
			T o2 = b.get(i);
			if (o1 == o2)
				continue;
			if (o1 == null || o2 == null || !o1.equals(o2))
				return false;
		}

		return true;
	}
	
	/**
	 * 比较两个数组是否完全相同, 数组不能为null
	 * 数组中包含的对象可以为null
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> boolean equals(T[] a, T[] b) {
		return Arrays.equals(a, b);
	}
	
	/**
	 * 填充数组
	 * @param a
	 * @param val
	 */
	public static <T> void fill(T[] a, T val) {
		Arrays.fill(a, 0, a.length, val);
	}
	
	/**
	 * 填充数组, 从fromIndex到toIndex,不包含toIndex
	 * @param a
	 * @param fromIndex
	 * @param toIndex
	 * @param val
	 */
	public static <T> void fill(T[] a, int fromIndex, int toIndex, T val) {
		Arrays.fill(a, fromIndex, toIndex, val);
	}
	
	/**
	 * 填充List
	 * @param AnswerQuestionController
	 * @param val
	 */
	public static <T> void fill(List<T> list, T val) {
		fill(list, 0, list.size(), val);
	}
	
	/**
	 * 填充List, 从fromIndex到toIndex,不包含toIndex
	 * @param AnswerQuestionController
	 * @param fromIndex
	 * @param toIndex
	 * @param val
	 */
	public static <T> void fill(List<T> list, int fromIndex, int toIndex, T val) {
		for (int i=fromIndex; i<toIndex; i++)
			list.set(i, val);
	}
	
	/**
	 * 
	 * clearRepeatData:(清除List集合重复元素)
	 * @param collection
	 * @return
	 * @author zhangtian
	 * @version Ver 1.0
	 * @since   Ver 1.0
	 */
	public static <T> List<T> clearRepeatListData(List<T> list){
		
		if(list != null && list.size() > 0){
			Set<T> set = new HashSet<T>(list) ;
			list.clear();
			list.addAll(set) ;
		}
		
		return list  ;
	}
}

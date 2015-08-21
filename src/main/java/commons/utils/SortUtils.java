package commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * SortUtil主要处理集合或数组的顺序。
 * 排序的方法中，返回的结果是已经排序的结果，对输入数据不做修改
 * @author haocongping
 */
public class SortUtils {
	
	/**
	 * 对Collection进行排序。
	 * Collection中的对象必须是可比较的，必须实现Comparable接口
	 * @param collection
	 */
	public static <T> List<T> sort(Collection<T> collection){
		return sort(collection, (Comparator<T>)null);
	}
	
	/**
	 * 对数组进行排序。
	 * 数组中的对象必须是可比较的，必须实现Comparable接口
	 * @param array
	 */
	public static <T> T[] sort(T[] array){
		return sort(array, (Comparator<T>)null);
	}
	
	/**
	 * 按照指定的排序方法进行排序, 排序不改变输入集合中的顺序
	 * @param collection
	 * @param comparator
	 * @return 排序后的数据
	 */
	public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator){
		List<T> list = new ArrayList<T>(collection.size());
		@SuppressWarnings("unchecked")
		T[] object = (T[])collection.toArray();
		if (comparator == null)
			Arrays.sort(object);
		else
			Arrays.sort(object, comparator);
		for (int i=0;i<object.length;i++){
			list.add(object[i]);
		}
		return list;
	}
	
	/**
	 * 按照指定的排序方法进行排序, 排序不改变输入数组中的顺序
	 * @param array
	 * @param comparator
	 * @return 排序后的数据
	 */
	public static <T> T[] sort(T[] array, Comparator<T> comparator){
		T[] cloneArray = (T[])array.clone();
		if (comparator == null)
			Arrays.sort(cloneArray);
		else
			Arrays.sort(cloneArray, comparator);
		return cloneArray;
	}
	
	private static <T> List<T> sort(Collection<T> collection, PropertyComparator comparator){
		List<T> list = new ArrayList<T>(collection.size());
		@SuppressWarnings("unchecked")
		T[] object = (T[])collection.toArray();
		if (comparator == null)
			Arrays.sort(object);
		else
			Arrays.sort(object, comparator);
		for (int i=0;i<object.length;i++){
			list.add(object[i]);
		}
		return list;
	}
	
	private static <T> T[] sort(T[] array, PropertyComparator comparator){
		T[] cloneArray = (T[])array.clone();
		if (comparator == null)
			Arrays.sort(cloneArray);
		else
			Arrays.sort(cloneArray, comparator);
		return cloneArray;
	}
	
	/**
	 * 对collection进行排序, 按照property属性进行排序
	 * @param collection
	 * @param property
	 * @return
	 */
	public static <T> List<T> sort(Collection<T> collection, String property){
		return sort(collection, new PropertyComparator(property));
	}
	
	/**
	 * 对数组进行排序, 按照property属性进行排序
	 * @param array
	 * @param property
	 * @return
	 */
	public static <T> T[] sort(T[] array, String property){
		return sort(array, new PropertyComparator(property));
	}
	
	/**
	 * 对collection进行排序, 按照多个属性进行排序
	 * @param collection
	 * @param properties
	 * @return
	 */
	public static <T> List<T> sort(Collection<T> collection, String[] properties){
		return sort(collection, new PropertyComparator(properties));
	}
	
	/**
	 * 对数组进行排序, 按照多个属性进行排序
	 * @param array
	 * @param properties
	 * @return
	 */
	public static <T> T[] sort(T[] array, String[] properties){
		return sort(array, new PropertyComparator(properties));
	}
	
	/**
	 * 对collection进行排序, 按照property属性进行排序, ascOrDesc指定顺序或倒序
	 * @param collection
	 * @param property
	 * @param ascOrDesc 为正数时是顺序,负数时是倒序,0无效. 
	 * @return
	 */
	public static <T> List<T> sort(Collection<T> collection, String property, int ascOrDesc){
		return sort(collection, new PropertyComparator(property, ascOrDesc));
	}
	
	/**
	 * 对数组进行排序, 按照property属性进行排序, ascOrDesc指定顺序或倒序
	 * @param collection
	 * @param property
	 * @param ascOrDesc 为正数时是顺序,负数时是倒序,0无效. 
	 * @return
	 */
	public static <T> T[] sort(T[] array, String property, int ascOrDesc){
		return sort(array, new PropertyComparator(property, ascOrDesc));
	}
	
	/**
	 * 对collection进行排序, 按照多个属性进行排序, 同时指定顺序或倒序
	 * @param collection
	 * @param properties
	 * @param ascOrDesc
	 * @return
	 */
	public static <T> List<T> sort(Collection<T> collection, String[] properties, int[] ascOrDesc){
		return sort(collection, new PropertyComparator(properties, ascOrDesc));
	}
	
	/**
	 * 对数组进行排序, 按照多个属性进行排序, 同时指定顺序或倒序
	 * @param collection
	 * @param properties
	 * @param ascOrDesc
	 * @return
	 */
	public static <T> T[] sort(T[] array, String[] properties, int[] ascOrDesc){
		return sort(array, new PropertyComparator(properties, ascOrDesc));
	}
	
    /**
     * 交换数组中两个元素的位置
     * @param array
     * @param a
     * @param b
     */
    public static <T> void swap(T array[], int a, int b) {
    	if (a<0 || b<0)
    		throw new IndexOutOfBoundsException("parameter out of array's size");
    	if (a>=array.length || b>=array.length)
    		throw new IndexOutOfBoundsException("parameter out of array's size");
    	T t = array[a];
    	array[a] = array[b];
    	array[b] = t;
    }
    
    /**
     * 交换List中两个元素的位置
     * @param list
     * @param a
     * @param b
     */
    public static <T> void swap(List<T> list, int a, int b) {
    	if (a<0 || b<0)
    		throw new IndexOutOfBoundsException("parameter out of list's size");
    	if (a>=list.size() || b>=list.size())
    		throw new IndexOutOfBoundsException("parameter out of list's size");
    	T t = list.get(a);
    	list.set(a, list.get(b));
    	list.set(b, t);
    }
    
    public static void swap(long x[], int a, int b) {
    	long t = x[a];
    	x[a] = x[b];
    	x[b] = t;
    }
    
    public static void swap(int x[], int a, int b) {
    	int t = x[a];
    	x[a] = x[b];
    	x[b] = t;
    }
    
    public static void swap(short x[], int a, int b) {
    	short t = x[a];
    	x[a] = x[b];
    	x[b] = t;
    }
    
    public static void swap(char x[], int a, int b) {
    	char t = x[a];
    	x[a] = x[b];
    	x[b] = t;
    }
    
    public static void swap(byte x[], int a, int b) {
    	byte t = x[a];
    	x[a] = x[b];
    	x[b] = t;
    }
    
    public static void swap(double x[], int a, int b) {
    	double t = x[a];
    	x[a] = x[b];
    	x[b] = t;
    }
    
    public static void swap(float x[], int a, int b) {
    	float t = x[a];
    	x[a] = x[b];
    	x[b] = t;
    }

}

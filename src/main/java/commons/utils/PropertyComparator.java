package commons.utils;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * PropertyComparator的功能是对两个对象进行比较时按照对象的属性进行比较
 * order=1表示顺序排列, order=-1倒序排序
 * 如果某个属性为空, 则优先
 * @author Peter Hao
 */
public class PropertyComparator implements Comparator<Object>, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String[] properties;
	private int[] orders;
	private final static boolean NULL_FIRST = true;	//属性为空时优先
	private final static int ASC = 1;				//默认排序方式

	@SuppressWarnings("unused")
	private PropertyComparator() {}

	public PropertyComparator(String[] properties) {
		this.properties = properties;
		orders = new int[properties.length];
		for (int i = 0; i < properties.length; i++) {
			orders[i] = ASC;
		}
	}

	public PropertyComparator(final String[] properties, final int[] orders) {
		if(properties == null || orders == null){
			throw new RuntimeException("Parameter can't be null");
		}
		if(properties.length != orders.length){
			throw new RuntimeException("Parameter's length must be match");
		}
		this.properties = properties;
		this.orders = orders;
	}

	public PropertyComparator(String property, int order) {
		this.properties = new String[] { property };
		this.orders = new int[] { order };
	}

	public PropertyComparator(String property) {
		this.properties = new String[] { property };
		this.orders = new int[] { ASC };
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Object o1, Object o2){
		int result = 0;
		for (int i = 0; i < properties.length; i++) {
			try{
				Object v1 = PropertyUtils.getProperty(o1, properties[i]);
				Object v2 = PropertyUtils.getProperty(o2, properties[i]);
				if (v1 == null && v2 == null) {
					result = 0;
				} else if (v1 == null) {
					result = NULL_FIRST ? orders[i] : orders[i]*-1;
				} else if (v2 == null) {
					result = NULL_FIRST ? orders[i]*-1 : orders[i];
				} else if (v1 instanceof Comparable && v2 instanceof Comparable
						&& v1.getClass().getName().equals(v2.getClass().getName())){
					result = ((Comparable)v1).compareTo(v2) * orders[i];
				}
				if (result != 0) {
					break;
				}
			}catch(Exception ex){
				throw new RuntimeException(ex);
			}
		}
		return result;
	}

}

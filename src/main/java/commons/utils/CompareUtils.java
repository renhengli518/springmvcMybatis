package commons.utils;

public class CompareUtils {

	public static boolean equal(byte[] data1, byte[] data2){
		if ((data1==null && data2!=null) 
				|| (data1!=null && data2==null))
			return false;
		if (data1!=null){
			if (data1.length!=data2.length)
				return false;
			
			for (int i=0;i<data1.length;i++){
				if (data1[i]!=data2[i])
					return false;
			}
			
		}
		return true;
	}
}

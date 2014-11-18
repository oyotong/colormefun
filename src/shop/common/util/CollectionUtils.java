package shop.common.util;

import java.util.Collection;

public class CollectionUtils {
	public static boolean contains(Collection c,Object o){
		if(null == c) return false;
		return c.contains(o);
	}
}

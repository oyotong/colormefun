package shop.common.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArraysUtils {
	public static Map arrayPairListtoMap(List<Object[]> list) {
		Map map = new TreeMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] array = list.get(i);
			map.put(array[0], array[1]);
		}
		return map;
	}
}

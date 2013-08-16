package demostration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class HashMapTest {

	public static void main(String[] args) {

		int[] a = new int[] { 3, 4, 5, 6, 3, 3, 3, 4 };

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : a) {
			if (map.get(a[i]) == null) {
				map.put(a[i], 1);
			} else {
				map.put(a[i], map.get(a[i]) + 1);
			}
		}

		int value = 0;
		int maxKey = 0;

		for (Integer key : map.keySet()) {
			if (map.get(key) > value) {
				value = map.get(key);
				maxKey = key;
			}
		}
		System.out.println(map.containsKey(maxKey));
		System.out.println(maxKey);
	}
}

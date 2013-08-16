package demostration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

public class CollectionUtilsTester {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
//		String[] arrayA = new String[] { "1", "2", "3", "3", "4", "5" };
//		String[] arrayB = new String[] { "3", "4", "4", "5", "6", "7" };
		String[] arrayA = new String[] { "1" };
		String[] arrayB = new String[] { "1"};
		List<String> a = Arrays.asList(arrayA);
		List<String> b = Arrays.asList(arrayB);

		System.out.println("A: " + ArrayUtils.toString(a.toArray()));
		System.out.println("B: " + ArrayUtils.toString(b.toArray()));
		System.out.println("--------------------------------------------");
		// 并集
		Collection<String> union = CollectionUtils.union(a, b);
		System.out.println("Union(A, B): " + ArrayUtils.toString(union.toArray()));

		Map map = CollectionUtils.getCardinalityMap(union);
		Set<Entry> set = map.entrySet();
		for (Entry entry : set) {
			System.out.println("entry-["+entry.getKey()+":"+entry.getValue()+"]"); 
		}
//		Collection<String> allCol = CollectionUtils.addAll(a, b);
		
		// 交集
		Collection<String> intersection = CollectionUtils.intersection(a, b);
		// 交集的补集
		Collection<String> disjunction = CollectionUtils.disjunction(a, b);
		// 集合相减
		Collection<String> subtract = CollectionUtils.subtract(a, b);

		Collections.sort((List<String>) union);
		Collections.sort((List<String>) intersection);
		Collections.sort((List<String>) disjunction);
		Collections.sort((List<String>) subtract);

		System.out.println("Intersection(A, B): " + ArrayUtils.toString(intersection.toArray()));
		System.out.println("Disjunction(A, B): " + ArrayUtils.toString(disjunction.toArray()));
		System.out.println("Subtract(A, B): " + ArrayUtils.toString(subtract.toArray()));
	}

}

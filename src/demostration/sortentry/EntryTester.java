package demostration.sortentry;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

public class EntryTester {
	public static void sort(Comparable[] data, int low, int high) {
		// 枢纽元,一般以第一个元素为基准进行划分
		Comparable pivotKey = data[low];
		// 进行扫描的指针i,j;i从左边开始,j从右边开始
		int i = low;
		int j = high;
		if (low < high) {
			// 从数组两端交替地向中间扫描
			while (i < j) {
				while (i < j && data[j].compareTo(pivotKey) > 0) {
					j--;
				}// end while
				if (i < j) {
					// 比枢纽元素小的移动到左边
					data[i] = data[j];
					i++;
				}// end if
				while (i < j && data[i].compareTo(pivotKey) < 0) {
					i++;
				}// end while
				if (i < j) {
					// 比枢纽元素大的移动到右边
					data[j] = data[i];
					j--;
				}// end if
			}// end while
			// 枢纽元素移动到正确位置
			data[i] = pivotKey;
			// 前半个子表递归排序
			sort(data, low, i - 1);
			// 后半个子表递归排序
			sort(data, i + 1, high);
		}
	}
	public static void main(String[] args) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		Set<Entry<String,Integer>> entrySet = map.entrySet();
		Set<SortEntry> sst = new HashSet<SortEntry>();
		sst.toArray(new SortEntry[sst.size()]);
//		CollectionUtils.index(obj, index)
		SortEntry[] ssts = sst.toArray(new SortEntry[sst.size()]);
		sort(ssts, 0, ssts.length);
		for(SortEntry entry: ssts){
//			System.out.println(entry.getValue()); 
		}
	}
	
}

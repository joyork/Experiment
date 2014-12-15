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
		// ��ŦԪ,һ���Ե�һ��Ԫ��Ϊ��׼���л���
		Comparable pivotKey = data[low];
		// ����ɨ���ָ��i,j;i����߿�ʼ,j���ұ߿�ʼ
		int i = low;
		int j = high;
		if (low < high) {
			// ���������˽�������м�ɨ��
			while (i < j) {
				while (i < j && data[j].compareTo(pivotKey) > 0) {
					j--;
				}// end while
				if (i < j) {
					// ����ŦԪ��С���ƶ������
					data[i] = data[j];
					i++;
				}// end if
				while (i < j && data[i].compareTo(pivotKey) < 0) {
					i++;
				}// end while
				if (i < j) {
					// ����ŦԪ�ش���ƶ����ұ�
					data[j] = data[i];
					j--;
				}// end if
			}// end while
			// ��ŦԪ���ƶ�����ȷλ��
			data[i] = pivotKey;
			// ǰ����ӱ�ݹ�����
			sort(data, low, i - 1);
			// �����ӱ�ݹ�����
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

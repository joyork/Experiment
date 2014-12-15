package algorithm;

public class QuickSort {
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
				}
				if (i < j) {
					// ����ŦԪ��С���ƶ������
					data[i] = data[j];
					i++;
				}
				while (i < j && data[i].compareTo(pivotKey) < 0) {
					i++;
				}
				if (i < j) {
					// ����ŦԪ�ش���ƶ����ұ�
					data[j] = data[i];
					j--;
				}
			}
			// ��ŦԪ���ƶ�����ȷλ��
			data[i] = pivotKey;
			// ǰ����ӱ�ݹ�����
			sort(data, low, i - 1);
			// �����ӱ�ݹ�����
			sort(data, i + 1, high);
		}
	}

	public static void main(String[] args) {
		// ��JDK1.5�汾����,�����������Ϳ����Զ�װ��
		// int,double�Ȼ������͵İ�װ����ʵ����Comparable�ӿ�
		Comparable[] c = { 4, 9, 23, 1, 45, 27, 5, 2 ,4,4,9};
		sort(c, 0, c.length - 1);
		for (Comparable data : c) {
			System.out.println(data);
		}
	}
}

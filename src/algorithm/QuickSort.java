package algorithm;

public class QuickSort {
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
				}
				if (i < j) {
					// 比枢纽元素小的移动到左边
					data[i] = data[j];
					i++;
				}
				while (i < j && data[i].compareTo(pivotKey) < 0) {
					i++;
				}
				if (i < j) {
					// 比枢纽元素大的移动到右边
					data[j] = data[i];
					j--;
				}
			}
			// 枢纽元素移动到正确位置
			data[i] = pivotKey;
			// 前半个子表递归排序
			sort(data, low, i - 1);
			// 后半个子表递归排序
			sort(data, i + 1, high);
		}
	}

	public static void main(String[] args) {
		// 在JDK1.5版本以上,基本数据类型可以自动装箱
		// int,double等基本类型的包装类已实现了Comparable接口
		Comparable[] c = { 4, 9, 23, 1, 45, 27, 5, 2 ,4,4,9};
		sort(c, 0, c.length - 1);
		for (Comparable data : c) {
			System.out.println(data);
		}
	}
}

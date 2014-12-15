package algorithm;

public class BubbleSort {

	public static void bubbleSort(int[] a) {
		int n = a.length;
		int times = 0;
		for (int i = n - 1; i >= 0; i--) {

			for (int j = 0; j < i; j++) {
				times++;
				if (a[j] > a[j + 1]) {
					int tmp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = tmp;
				}

			}
		}
		System.out.println("basic:"+times);
	}

	public static void bubbleSortPro(int[] a) {
		int n = a.length;
		int times = 0;
		int k = 0;
		for (int i = n - 1; i > 0; i = k) {
			k = 0;
			for (int j = 0; j < i; j++) {
				times++;
				if (a[j] > a[j + 1]) {
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					k = j;
				}
			}
		}
		System.out.println("pro:"+times);
	}

	public static void main(String[] args) {
		int[] a = new int[] { 5, 22, 1, 95, 4, 28, 3, 9};
		bubbleSort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+",");
		}
		System.out.println(); 
		
		int[] b = new int[] { 5, 22, 1, 95, 4, 28, 3, 9};
		bubbleSortPro(b);
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]+",");
		}
		System.out.println();
	}

}

package algorithm;

public class SelectionSort {

	public static void selectionSort(int[] a) {
		int i, j;
		int n = a.length;
		for (i = 0; i < n; i++) {
			int min = i;
			for (j = i; j < n; j++) {
				if (a[min] > a[j]) {
					min = j;
				}
			}
			if (min != i) {
				int tmp = a[min];
				a[min] = a[i];
				a[i] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { 2, 14, 44, 3, 9, 6, 13, 8 };
		selectionSort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i] + " ");
		}
		int[] b = new int[] { 2, 14, 44, 3, 9, 6, 13, 8 };
		selectSort(b);
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i] + " ");
		}
		
	}

	public static void selectSort(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = a[i];
			int mi = i;
			for (int j = i; j < n; j++) {
				if (a[j] < min) {
					min = a[j];
					mi = j;
				}
			}
			if (mi != i) {
				int tmp = a[mi];
				a[mi] = a[i];
				a[i] = tmp;
			}
		}
	}
}

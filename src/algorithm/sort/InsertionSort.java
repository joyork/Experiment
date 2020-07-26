package algorithm.sort;

public class InsertionSort {

	public static void exchange(int[] a, int x, int y) {
		int tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}

	public static void insertSort(int[] a) {
		int n = a.length;
		for (int i=0; i<n; i++) {
			for (int j=i; j>0;j--) {
				if (a[j]<a[j-1]) {
					exchange(a, j, j-1);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] a = {21,9,1,58,3,7,24,16};
		insertSort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}


	public static void insertionSort(Comparable[] data) {

		for (int index = 1; index < data.length; index++) {
			Comparable key = data[index];
			int position = index;
			// shift larger values to the right
			while (position > 0 && data[position - 1].compareTo(key) > 0) {
				data[position] = data[position - 1];
				position--;
			}
			data[position] = key;
		}
	}

//	public static void main(String[] args) {
//
//		Comparable[] c = { 4, 9, 23, 1, 45, 27, 5, 2 };
//		insertionSort(c);
//		for (int i = 0; i < c.length; i++)
//			System.out.print(c[i]+",");
//		System.out.println("");
//
//		int[] a ={4, 9, 23, 1, 45, 27, 5, 2 };
//		insertSort4(a);
//		for (int i = 0; i < a.length; i++)
//			System.out.print(a[i]+",");
//		System.out.println("");
//	}
	
	public static void insertSort2(int[] a){
		int n = a.length;
		for(int i=1;i<n;i++){
//			int j=0;
//			while(j<=i && a[j]<a[i]){
//				j++;
//			}
//			if(j!=i){
				int val = a[i];
				int k=i;
				for(;k>0 && a[k-1]>val;k--){
					
						a[k]=a[k-1];
					
				}
				a[k]=val;
//			}
		}
	}
	
	public static void insertSort3(int[] a){
		int n = a.length;
		for(int i=1;i<n;i++){
			int j=0;
			while(j<=i && a[j]<a[i]){
				j++;
			}
			if(j!=i){
				int val = a[i];
				for(int k=i;k>j;k--){
					a[k]=a[k-1];
				}
				a[j]=val;
			}
		}
	}
	
	public static void insertSort4(int[] a){
		int n=a.length;
		for(int i=1;i<n;i++){
			int data = a[i];
			int pos = i;
			while(pos>0 && a[pos-1]>data){
				a[pos]=a[pos-1];
				pos--;
			}
			a[pos]=data;
		}
	}

}

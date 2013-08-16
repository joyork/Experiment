package algorithm;

public class ShellSort {

	public static void shellSort(int[] a) {
		int n = a.length;
		int delta, j, k, t , pos;

		for (delta = n / 2; delta > 0; delta = delta / 2) /* 控制增量 */
		{
			for (j = delta; j < n; j++) /* 这个实际上就是上面的直接插入排序 */
			{
				t = a[j];
				pos = j-delta;
				while(pos>=0 && a[pos]>t){
					a[pos+delta]=a[pos];
					pos-=delta;
				}
//				for (k = j - delta; (k >= 0 && t < a[k]); k -= delta) {
//					a[k + delta] = a[k];
//				}
				a[pos + delta] = t;
			}
		}
	}

	
	public static void shellSort2(int[] a){
		int n = a.length;
		for(int d=n/2; d>0 ; d=d/2){
			for(int i=d;i<n;i++){
				int value = a[i];
				int pos = i-d;
				while(pos>=0 && a[pos]>value){
					a[pos+d]=a[pos];
					pos -= d;
				}
				a[pos+d]=value;
			}
		}
	}
	public static void main(String[] args) {
		int[] a ={4, 9, 23, 1, 45, 27, 5, 2 };
		shellSort2(a);
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]+",");
		System.out.println("");
	}
}

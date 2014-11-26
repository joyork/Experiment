package algorithm;

public class MaxSumSubseq {

	public static int max(int x, int y){
		return x>y?x:y;
	}
	
	public static void main(String[] args) {
		
		int[] a = {4,-2, 5, -8, 7, 6, -4};
		
		int maxEnd = onePassGetMax(a); 
//		int maxEnd = divideRecurseGetMax(a, 0, a.length-1);
//		int maxEnd = twoIterGetMax(a);
		System.out.println(maxEnd);	
	}
	
	public static int twoIterGetMax(int[] a){
		int n = a.length;
		int max = 0;
		int sum = 0;
		for(int i=0;i<n;i++){
			for(int j=i;j<n;j++){
				sum+=a[j];
				if(sum>max){
					max = sum;
				}
			}
			sum=0;
		}
		return max;
	}

	public static int divideRecurseGetMax(int[] a, int l, int r){
		if(l>r)
			return 0;
		if(l==r)
			return max(a[l],0);
		int m = (l+r)>>1;
		
		int sum = 0,lmax=0;
		for(int i=m;i>=0;i--){
			sum+=a[i];
			lmax = max(lmax,sum);
		}
		
		int rmax = 0;
		sum = 0;
		for(int j=m+1;j<=r;j++){
			sum+=a[j];
			rmax = max(rmax,sum);
		}
		
		return max(lmax+rmax,max(divideRecurseGetMax(a, l, m),divideRecurseGetMax(a, m+1, r)));
	}
	
	private static int onePassGetMax(int[] a) {
		int maxsofar = 0, maxEnd = 0;
		for(int i=0;i<a.length;i++){
			maxsofar += a[i];
			maxsofar = max(maxsofar,0);
			maxEnd = max(maxEnd,maxsofar);
		}
		return maxEnd;
	}
}

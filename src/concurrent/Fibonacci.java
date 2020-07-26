package concurrent;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fibonacci implements Runnable {
	
	private final int n ;
	public Fibonacci(int n ) {this.n = n;}
	@Override
	public void run() {
		Integer[] seq = new Integer[n];
		for(int i=0;i<n;i++){
			seq[i] = fib(i);
		}
		System.out.println("seq"+n+":"+Arrays.toString(seq) );
	}
	private int fib(int n){
		if(n<2) return 1;
		return fib(n-2) + fib(n-1);
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(4);
		for(int i = 1;i<=5;i++){
			Fibonacci f = new Fibonacci(i);
			exec.execute(f);
		}
		exec.shutdown();
	}
}

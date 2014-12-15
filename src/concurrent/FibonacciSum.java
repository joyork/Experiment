package concurrent;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibonacciSum implements Callable<Integer>{
	private int count;
	private final int n ;
	
	public FibonacciSum(int n ) {
		this.n = n;
	}
	
	private int fib(int i ){
		if(i<2)return 1;
		return fib(i-2)+ fib(i-1);
	}
	
	private int next(){
		return fib(count++);
	}
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int j = 0;j<=n;j++){
			sum+=next();
		}
		return sum; 
	}

	public Future<Integer> runTask(int n){
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<Integer> fi = exec.submit(new FibonacciSum(n));
		return fi;
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		ArrayList<Future<Integer>> result = new ArrayList<Future<Integer>>();
		for(int i = 0;i<5;i++){
			result.add(exec.submit(new FibonacciSum(i)));
		}
		for(Future<Integer> fi : result){
			try {
				System.out.println(fi.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				exec.shutdown();
			}
		}
	}
}

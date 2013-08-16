package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BusyWait{

//	public static volatile boolean flag ;
	public static int spins;
	
	public static void main(String[] args) throws InterruptedException {
		final Runnable r1 = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						TimeUnit.MILLISECONDS.sleep(100);
//					flag = true;
						synchronized (this) {
							notify();
						}
					} catch (InterruptedException e) {
						return;
					}
				}
			}
			
		};
		Runnable r2 = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					
//					flag = false;
					synchronized (r1) {
						try {
							r1.wait();
						} catch (InterruptedException e) {
							return;
						}
						System.out.println("cycled!"); 
					}
				}
			}
			
		};
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.submit(r1);
		exec.submit(r2);
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
	}
}

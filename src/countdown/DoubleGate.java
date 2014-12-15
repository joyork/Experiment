package countdown;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class DoubleGate {

	public static void main(String[] args) {
		final int Num = 20;
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(Num);
		int i=0;
		for(;i<Num ;i++){
			new Thread(new FutureTask(new Callable(){

				@Override
				public Object call() throws Exception {
					// TODO Auto-generated method stub
					return null;
				}
				
			}));
			Thread t = new Thread(){
				public void run(){
					try {
						startGate.await();
						Thread.sleep(new Random().nextInt(1000));
						System.out.println("i am done! "); 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally{
						endGate.countDown();
					}
				}
			};
			t.start();
		}
		
		startGate.countDown();
		long start = System.nanoTime();
		try {
			endGate.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.nanoTime();
		System.out.println((end-start)/1000000); 
	}
	
}

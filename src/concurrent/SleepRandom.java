package concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepRandom implements Runnable{
	private final int id ; 

	public SleepRandom(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		Random r = new Random();
		int i = r.nextInt(10)+1;
		try {
			TimeUnit.SECONDS.sleep(new Long(i));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println(id+" sleeps for "+i+" secends."); 
		}
	}
	

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0 ;i<10;i++){
			exec.execute(new SleepRandom(i));
		}
		exec.shutdown();
	}
}

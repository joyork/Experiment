package concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class AtomityTest implements Runnable {

	
	private int i = 0;
	public synchronized int getValue(){
		return i;
	}
	private synchronized void evenIncrement(){
		i++;
		i++;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			evenIncrement();
		}
	}
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask(){

			@Override
			public void run() {
				System.out.println("aborting..."); 
				System.exit(0);
			}
			
		}, 5000);
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomityTest at = new AtomityTest();
		exec.execute(at);
		while (true){
			int val = at.getValue();
			if(val %2 !=0){
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}

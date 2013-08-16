package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Gate implements Runnable {

	private Truck truck;
	public Gate(Truck t){
		this.truck = t;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(truck){
			truck.notifyAll();
		}
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Truck t = new Truck();
		Gate gate = new Gate(t);
		exec.execute(t);
		exec.execute(gate);
		Thread.yield();
		exec.shutdown();
	}
}

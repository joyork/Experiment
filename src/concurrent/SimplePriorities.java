package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SimplePriorities implements Runnable {

	private int countdown = 5;
	private volatile double d;
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	public String toString(){
		return Thread.currentThread()+" : "+ countdown;
		
	}
	
	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i = 0; i < 10000000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if(--countdown == 0)return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new PriorityThreadFactory(Thread.MIN_PRIORITY));
		for(int i =0;i<5;i++)
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		Thread.yield();
		exec.shutdown();
		exec = Executors.newCachedThreadPool(new PriorityThreadFactory(Thread.MAX_PRIORITY) );
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}

class PriorityThreadFactory implements ThreadFactory{
	private final int priority;
	public PriorityThreadFactory(int priority) {
		this.priority = priority;
	}
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setPriority(priority);
		return t;
	}
	
}

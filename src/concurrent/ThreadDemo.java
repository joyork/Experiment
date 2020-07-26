package concurrent;

public class ThreadDemo implements Runnable {
	private static int count = 0; 
	private final int id = count++;
	public ThreadDemo() {
		// TODO Auto-generated constructor stub
		System.out.println("thread "+id+" is start"); 
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("thread "+id +" in step 1"); 
		Thread.yield();
		System.out.println("thread "+id +" in step 2"); 
		Thread.yield();
		System.out.println("thread "+id +" in step 3"); 
		Thread.yield();
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("thread "+ id + " ends" ); 
		super.finalize();
	}
}

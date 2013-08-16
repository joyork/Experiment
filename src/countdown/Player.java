package countdown;

import java.util.concurrent.CountDownLatch;

public class Player implements Runnable{
	private int id ;
	private CountDownLatch begin;
	private CountDownLatch end;
	
	public Player(int id,CountDownLatch begin, CountDownLatch end){
		this.id = id;
		this.begin = begin;
		this.end = end;
	}
	@Override
	public void run() {
		try {
			begin.await();
			Thread.sleep((long)Math.random()*1000+100);
			System.out.println("player "+id+" has arrived!");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			end.countDown();
		}
	}
	
}
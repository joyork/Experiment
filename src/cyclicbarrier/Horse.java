package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Runnable {
	
	private static int count = 0;
	private final int id = count++;
	private int strides = 0;
	private static Random rand = new Random(23);
	private static CyclicBarrier barrier;
	
	public Horse(CyclicBarrier b){
		barrier=b;
	}
	
	public synchronized int  getStrides(){
		return strides;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(!Thread.interrupted()){
//				synchronized(this){
					strides += rand.nextInt(2);
//				}
				barrier.await();
			}
		}catch(InterruptedException e){
			
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public String toString(){
		return "Horse "+id+" " ;
	}
	public String tracks(){
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < getStrides(); i++) {
			s.append("*");
		}
		s.append(id);
		return s.toString();
	}
	
}

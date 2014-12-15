package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RadioCounter implements Runnable {

	private static List<RadioCounter> rcList = new ArrayList<RadioCounter>() ;
	private static Count count = new Count();
	
	private int number = 0;
	private final int id;
	private static volatile boolean cancled = false;

	public RadioCounter(int i){
		this.id = i;
		rcList.add(this);
	}
	
	public static void cancle(){
		cancled = true;
	}
	@Override
	public void run() {
		Random ran = new Random();
		while(true){
			synchronized (this) {
				number++;
			}
			System.out.println(this + " total "+count.incre());
		
			try {
				TimeUnit.MILLISECONDS.sleep(ran.nextInt(1000));
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted!"); 
				System.out.println(this);
				return;
			}
		}
//		System.out.println("stopping "+this); 
	}
	
	public synchronized int getValue(){
		return this.number;
	}

	public String toString(){
		return "RadioConter"+id + " "+ getValue();
	}
	
	public static int getTotal(){
		return count.getValue();
	}
	
	public static int getSum(){
		int sum = 0;
		for(int i=0;i<RadioCounter.rcList.size();i++){
			RadioCounter rc = rcList.get(i);
			sum += rc.getValue();
		}
		return sum;
	}
	
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0;i<5;i++){
			exec.execute(new RadioCounter(i));
		}
		try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		RadioCounter.cancle();
		
		exec.shutdownNow();
		try {
			if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS)){
				System.out.println("some task were not terminated!"); 
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total:"+RadioCounter.getTotal());
		System.out.println("Sum:"+RadioCounter.getSum()); 
	}
	
}
class Count{
	private int count =  0;
	private Random r = new Random(37);
	public synchronized int incre(){
		int tmp = count;
		if(r.nextBoolean())
			Thread.yield();
			
		return (count = ++tmp);
	}
	public synchronized int getValue(){
		return count;
	}
	
}
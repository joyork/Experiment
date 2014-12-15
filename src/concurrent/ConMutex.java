package concurrent;

import java.util.Random;

public class ConMutex {
	private int va = 50 ;
	private int vb = 200;
	
	public synchronized void mash(){
		Random r = new Random();
		int k = r.nextInt(10);
		va += k;
		Thread.yield();
		vb -= k;
	}

	public synchronized boolean validate(){
		return (va + vb) == 250;
	}
	
	public static void main(String[] args) {
		ConMutexChecker.test(new ConMutex());
	}
	
}

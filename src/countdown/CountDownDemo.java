package countdown;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownDemo {
	private static int PLAYER_NUM = 10;
	
	public static void main(String[] args) {
	
		ExecutorService exe = Executors.newFixedThreadPool(PLAYER_NUM);
		CountDownLatch begin = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(PLAYER_NUM);
		
		Player[] players = new Player[PLAYER_NUM];
		for (int i = 0; i < PLAYER_NUM; i++) {
			players[i]= new Player(i+1,begin,end);
		}
		for (Player player : players) {
			exe.execute(player);
		}
		System.out.println("match begins:"); 
		begin.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("match end!" ); 
		}
		exe.shutdown();
	}
	

}

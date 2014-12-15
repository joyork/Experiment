package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable {
 
 private CyclicBarrier cyclicBarrier;
 private int id;
 
 public Player(int id, CyclicBarrier cyclicBarrier) {
  this.cyclicBarrier = cyclicBarrier;
  this.id = id;
 }

 @Override
 public void run() {
  try {

   Thread.sleep(new Random().nextInt(2000));
   System.out.println("���" + id + "�������һ��...");
   cyclicBarrier.await();
   System.out.println("���" + id + "����ڶ���...");
  } catch (InterruptedException e) {
   e.printStackTrace();
  } catch (BrokenBarrierException e) {
   e.printStackTrace();
  }
 }
}
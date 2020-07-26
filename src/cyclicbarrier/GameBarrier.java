package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class GameBarrier {
 
 public static void main(String[] args) {
  CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
   
   @Override
   public void run() {
    System.out.println("������ҽ���ڶ��أ�");
   }
  });
  
  for (int i = 0; i < 4; i++) {
   new Thread(new Player(i, cyclicBarrier)).start();
  }
 }
}
package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynAreaTester {

	public class SingleSync{
		public synchronized void f(){
			for(int i = 0;i<5;i++){
				System.out.println("f()");
				Thread.yield();
			}
		}
		public synchronized void g(){
			for(int i = 0;i<5;i++){
				System.out.println("g()");
				Thread.yield();
			}
		}
		public synchronized void h(){
			for(int i = 0;i<5;i++){
				System.out.println("h()");
				Thread.yield();
			}
		}
		
	}
	
	public class TripleSync{
		private Lock lo = new ReentrantLock();
		private Lock ck = new ReentrantLock();
		private Lock lk = new ReentrantLock();
		public void f(){
			lo.lock();
			for(int i = 0;i<5;i++){
				System.out.println("f()"); 
				Thread.yield();
			}
			lo.unlock();
		}
		
		public void g(){
			ck.lock();
			for(int i = 0;i<5;i++){
					System.out.println("g()"); 
					Thread.yield();
				}
			ck.unlock();
		}
		public void h(){
			lk.lock();
				for(int i = 0;i<5;i++){
					System.out.println("h()"); 
					Thread.yield();
				}
			lk.unlock();
		}
	}
	public static void main(String[] args) {
		SynAreaTester st= new SynAreaTester();
		final SingleSync ss = st.new SingleSync();
		final TripleSync tt = st.new TripleSync();
		System.out.println("testing single..."); 
		Thread t = new Thread(){
			@Override
			public void run() {
				ss.f();
			}
		};
		t.start();
		
		Thread s = new Thread(){
			public void run(){
				ss.g();
			}
		};
		s.start();
		try {
			t.join();
			s.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ss.h();
		System.out.println("testing triple..."); 
		new Thread(){
			public void run(){
				tt.f();
			}
		}.start();
		new Thread(){
			public void run(){
				tt.g();
			}
		}.start();
		tt.h();

	}
}

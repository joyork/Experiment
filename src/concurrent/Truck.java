package concurrent;

public class Truck implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("truck is coming!");
		}
	}

}

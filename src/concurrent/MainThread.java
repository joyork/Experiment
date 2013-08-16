package concurrent;

public class MainThread {
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			ThreadDemo td = new ThreadDemo();
			Thread t = new Thread(td);
			t.start();
		}
		System.runFinalization();
	}
}

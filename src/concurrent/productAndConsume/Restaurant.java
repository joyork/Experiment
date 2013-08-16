package concurrent.productAndConsume;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
	public Meal meal;
	public Waitor waitor;
	public Chef chef;
	public ExecutorService exec;
	public Restaurant(){
		this.exec = Executors.newCachedThreadPool();
		this.waitor = new Waitor(this);
		this.chef = new Chef(this);
		this.meal = null;
		exec.submit(waitor);
		exec.submit(chef);
		exec.shutdown();
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
}

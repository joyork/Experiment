package concurrent.productAndConsume;

public class Chef implements Runnable {

	private Restaurant restaurant;
	private int count ;
	public Chef(Restaurant res){
		this.restaurant = res;
		this.count = 0;
	}
	@Override
	public void run() {
		try {
			synchronized (this) {
				while(restaurant.meal!=null){
					wait();
				}
			}
			if(++count>10){
				System.out.println("run out of food.stop cooking."); 
				restaurant.exec.shutdownNow();
			}
			
			System.out.println("order up!");
			synchronized (restaurant.waitor) {
				restaurant.meal = new Meal(count);
				restaurant.waitor.notify();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("chef interrupted!"); 
		}
	}

}

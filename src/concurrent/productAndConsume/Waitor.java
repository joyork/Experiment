package concurrent.productAndConsume;

public class Waitor implements Runnable {

	private Restaurant restaurant;
	public Waitor(Restaurant res){
		this.restaurant = res;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				
				synchronized (this) {
					while(restaurant.meal==null){
						wait();
					}
				}
				System.out.println("waitor has got meal.");
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notify();
				}
			}
		}catch(InterruptedException e ){
			System.out.println("waitor interrupted!"); 
		}
		
	}

}

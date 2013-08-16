package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConMutexChecker implements Runnable{

	private ConMutex cm ;
	
	public ConMutexChecker(ConMutex cm) {
		this.cm = cm;
	}
	
	@Override
	public void run() {
		while(cm.validate()){
			cm.mash();
		}
		System.out.println(Thread.currentThread()+ " done !"); 
	}
	
	public static void test(ConMutex cm ){
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0 ;i<4;i++){
			exec.execute(new ConMutexChecker(cm));
		}
		exec.shutdown();
	}
	
}

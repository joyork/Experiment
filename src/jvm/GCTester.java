package jvm;

public class GCTester {

	
	public static void test1(){
		{
			byte[] b = new byte[6*1024*1024];
//			b = null;
		}
//		int a = 0;
		System.gc();
		System.out.println("first explict gc over"); 
	}
	
	public static void main(String[] args) {
		GCTester.test1();
		System.gc();
		//run jvm as : -XX:+PrintGCDetails
		System.out.println("second explict gc over"); 
		
		
	}
}

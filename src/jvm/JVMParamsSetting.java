package jvm;

import java.util.ArrayList;

public class JVMParamsSetting {
	public static void main(String[] args) {
		ArrayList<Object> a = new ArrayList<Object>();
		for(int i=0;i<100;i++){
			byte[] b = new byte[1024*1024];
			a.add(b);
			System.out.println(i+ " locate 1M"); 
		}
		System.out.println("Max memory: "+Runtime.getRuntime().maxMemory()); 
	}
}

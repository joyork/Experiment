package jvm;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import org.junit.Test;

public class TestStack {

	private int count;
	private void recursion(long a,long c)throws InterruptedException{
		long d=0,e=0,f=0;
		count++;
		recursion(a,c);
	}
	
	@Test
	public void testRecursion(){
		try{
			
			recursion(1L,3L);
		}catch(Throwable t	){
			System.out.println("deep of stack is :"+count);
//			t.printStackTrace();
		}
	}
	
	@Test
	public void permGenGC(){
		for(int i = 0;i<Integer.MAX_VALUE;i++){
			String t = String.valueOf(i).intern();
		}
	}
	

}

package jvm;

import org.junit.Test;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class TestPermClassGC {
	
	@Test 
	public void testOneClassLoad() throws CannotCompileException, InstantiationException, 
	IllegalAccessException, NotFoundException {
		for( int i=0;i<Integer.MAX_VALUE; i++){
			CtClass c = ClassPool.getDefault().makeClass("Geym"+i);
			c.setSuperclass(ClassPool.getDefault().get("jvm.JavaBeanObject")); 
			Class clz = c.toClass();
			JavaBeanObject jbo = (JavaBeanObject)clz.newInstance();
		}
	}
	
	static MyClassLoader mcl = new MyClassLoader();
	
	
	@Test
	public void testNewClassLoad() throws CannotCompileException,InstantiationException,
	IllegalAccessException,NotFoundException{
		for( int i=0;i<Integer.MAX_VALUE; i++){
			CtClass c = ClassPool.getDefault().makeClass("Geym"+i);
			c.setSuperclass(ClassPool.getDefault().get("jvm.JavaBeanObject")); 
			Class clz = c.toClass(mcl,null);
			JavaBeanObject jbo = (JavaBeanObject)clz.newInstance();
			if(i%10 == 0){
				mcl = new MyClassLoader();
			}
		}
	}
}

package demostration.sortedset;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortedSetTester {

	
//	public static void main(String[] args) {
//		SortedSet<One> zset = new TreeSet<One>();
//
//		Random r = new Random();
//		for(int i=0;i<50;i++){
//			One one = new One();
//			one.setPass("baiyw");
//			if(zset.contains(one)){
//				one.setI(zset.)
//			}
//			zset.add(one);
//		}
//		
//		Comparator com = zset.comparator();
//		
//		for (One one : zset) {
//			System.out.println(one.getI()); 
//		}
//				
//	}

	
	 public static void main(String args[]){  
	        SortedMap<String,Object> map = new TreeMap<String,Object>() ;  // 通过子类实例化接口对象
//	        One one = new One("one");
//	        One two = new One("two");
//	        One three = new One("three");
//	        One four = new One("four");
//	        System.out.println(one.equals(two)); 
//	        System.out.println(one.compareTo(two)); 
	        map.put("a",1) ;  
	        map.put("a",2) ;  
	        map.put("b",1) ;  
	        map.put("c",1) ;  
	        if(map.containsKey("b")){
	        	Integer tmp = (Integer)map.get("b");
//	        	one.setI(tmp+1);
	        	map.put("b", tmp+1);
	        }
	        System.out.println(map.size()); 
	        System.out.print("第一个元素的内容的key：" + map.firstKey()) ;  
	        System.out.println("：对应的值：" + map.get(map.firstKey())) ;  
	        System.out.print("最后一个元素的内容的key：" + map.lastKey()) ;  
	        System.out.println("：对应的值：" + map.get(map.lastKey())) ;  
	        System.out.println("返回小于指定范围的集合：") ;  
	        for(Map.Entry<String,Object> me:map.headMap("c").entrySet()){  
	            System.out.println("\t|- " + me.getKey() + " --> " + me.getValue()) ;  
	        }

//	        System.out.println("返回大于指定范围的集合：") ;  
//	        for(Map.Entry<String,String> me:map.tailMap("B、mldnjava").entrySet()){  
//	            System.out.println("\t|- " + me.getKey() + " --> " + me.getValue()) ;  
//	        }  
//	        System.out.println("部分集合：") ;  
//	        for(Map.Entry<String,String> me:map.subMap("A、mldn","C、zhinangtuan").entrySet()){  
//	            System.out.println("\t|- " + me.getKey() + " --> " + me.getValue()) ;  
//	        }  
	    }  
}

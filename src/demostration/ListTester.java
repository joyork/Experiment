package demostration;

import java.util.ArrayList;
import java.util.HashSet;

public class ListTester {
	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		Integer e = 2;
		set.add(e);
		set.add(444);
		set.add(99);
		
		System.out.println(set.contains(new Integer(99))); 
	}
}

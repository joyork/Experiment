package utils;


import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class RandomUtils {

	
	public static void main(String[] args) {
		List<String> files = Lists.newArrayList();
		files.add("one");
		files.add("two");
		files.add("three");
		files.add("four");
		files.add("five");
		files.add("six");
		files.add("seven");
		Random rand = new Random();
	    List<String> r = Lists.newArrayList();
	    for (String file : files) {
	      int i = rand.nextInt(r.size() + 1);
	      if (i == r.size()) {
	        r.add(file);
	      } else {
	        r.add(r.get(i));
	        r.set(i, file);
	      }
	    }
	    System.out.println(r); 
	}
}

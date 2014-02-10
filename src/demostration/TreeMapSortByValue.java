package demostration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeMapSortByValue {

	public static void main(String[] args) {
//		Map<Integer,String> intmap = new HashMap<Integer,String>();
//		intmap.put(2,"d");
//		intmap.put(1,"c");
//		intmap.put(1,"b");
//		intmap.put(3,"a");
//		List<Map.Entry<Integer,String>> entrys =
//			    new ArrayList<Map.Entry<Integer,String>>(intmap.entrySet());
//
//		//≈≈–Ú«∞
//		for (int i = 0; i < entrys.size(); i++) {
//		    String id = entrys.get(i).toString();
//		    System.out.println(id);
//		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("d", 2);
		map.put("c", 1);
		map.put("b", 1);
		map.put("a", 3);

		List<Map.Entry<String, Integer>> infoIds =
		    new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

		//≈≈–Ú«∞
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).toString();
		    System.out.println(id);
		}
		//d 2
		//c 1
		//b 1
		//a 3

		//≈≈–Ú
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        //return (o2.getValue() - o1.getValue()); 
		        return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		}); 

		System.out.println("sorted:");
		//≈≈–Ú∫Û
		for (int i = 0; i < infoIds.size(); i++) {
		    String id = infoIds.get(i).toString();
		    System.out.println(id);
		}
	}
}

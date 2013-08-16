package demostration;

import java.util.ArrayList;

public class Continuer {

	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		String astring = "good";
		arr.add(astring);
		arr.add(null);
		arr.add("find");

		for (String str : arr) {
			if (str == null) {
				continue;
			}
			if (str == null) {
				System.out.println("FIND!");
			}
			System.out.println(str);
		}

		ArrayList<String> brr = new ArrayList<String>();
		brr = arr;
		arr.add("badthing");
		arr.add("some");
		brr.add("time");
		System.out.println("---brr---");
		for (String str : brr) {
			System.out.println(str);
		}
		System.out.println("----arr---");
		for (String str : arr) {
			System.out.println(str);
		}
	}
}

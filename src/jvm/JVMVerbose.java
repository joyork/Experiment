package jvm;

public class JVMVerbose {

	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String ab = "ab";
		System.out.println((a + b) == ab); // false
		System.out.println(("a" + "b") == ab); // true
		final String afinal = "a";
		String result = afinal + "b";
		System.out.println(result == ab); // true
		String plus = a + "b";
		System.out.println(plus == ab); // false
		System.out.println(plus.intern() == ab); // true

	}
}

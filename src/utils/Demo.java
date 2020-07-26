package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Demo {

    public static void main(String[] args) {
//        String roman = "III";
//        System.out.println("to int:" + romanToInt(roman));

        String s= "{[{]}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack st = new Stack<Character>();
        char[] ch = s.toCharArray();
        for(int i=0;i<ch.length;i++) {
            char in = ch[i];
            if(in == '(' || in=='{' || in=='[') {
                st.push(in);
            } else {
                char out = (char)st.pop();
                if(!isPair(out, in)) return false;

            }
        }
        return st.isEmpty();
    }
    private static boolean isPair(char i, char o) {
        return i=='{' && o=='}' || i=='['&& o==']' || i=='{'&& o=='}';
    }

    public static int romanToInt(String s) {
        Map<String, Integer> m = new HashMap<String, Integer>();
        m.put("I", 1);
        m.put("IV", 3);
        m.put("IX", 8);
        m.put("V", 5);
        m.put("X", 10);
        m.put("XL", 30);
        m.put("XC", 80);
        m.put("L", 50);
        m.put("C", 100);
        m.put("CD", 300);
        m.put("CM", 800);
        m.put("D", 500);
        m.put("M", 1000);


        int r = m.get(s.substring(0, 1));
        for(int i=1; i<s.length(); ++i){
            String two = s.substring(i-1, i+1);
            String one = s.substring(i, i+1);
            System.out.println("two:"+ two + "; one:"+ one);
            int t = 0;
            if (m.containsKey(two)) {
                t = m.get(two);
            } else {
                t = m.get(one);
            }
            r = r+t;
        }
        return r;

    }


}

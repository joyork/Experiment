package leetcode;

public class MyAtoI {

    public static void main(String[] args) {
        String s = "   -42";
        System.out.println("int:" + myAtoi(s));
    }

    public static int myAtoi(String str) {
        int i = 0;
        while( i< str.length() && str.charAt(i) == ' ') i++;
        if(i>=str.length()) return 0;
        if(str.charAt(i) != '+' && str.charAt(i) != '-' && !isDigit(str.charAt(i))) {
            return 0;
        }
        int sign = 1;
        if(str.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if(str.charAt(i) == '+') {
            i++;
        }
        long result = 0;
        while(i<str.length() && isDigit(str.charAt(i))) {
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        return (int)result*sign;

    }

    public static boolean isDigit(char c) {
        return c>='0' && c<='9';
    }
}

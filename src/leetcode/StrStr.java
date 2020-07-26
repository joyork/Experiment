package leetcode;

public class StrStr {
    public static int strStr(String haystack, String needle) {
        if(needle == null || needle.equals(" ")) { return 0;}
        int nh = haystack.length();
        int nn = needle.length();
        if(nn >nh) { return -1;}


        for(int i=0; i<nh-nn; i++) {
            boolean neq = false;
            for(int j=0; j<nn; j++) {
                if(haystack.charAt(i+j) != needle.charAt(j)) {
                    neq = true;
                    break;
                }
            }
            if(!neq) {
                return i;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        String a = "hello";
        String b = "ll";
        System.out.println(strStr(a, b));
    }
}

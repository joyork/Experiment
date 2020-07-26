package interview;


public class Better {
    public static void main(String[] args) {
        int a = 14;
        int b = 15;
        System.out.println(a^b);

        String s = "great";
        char[] sc = s.toCharArray();
        int start =0 , end = s.length()-1;
        char tmp;
        while(start<end){
            tmp = sc[start];
            sc[start] = sc[end];
            sc[end] = tmp;
            start++;
            end--;
        }
        System.out.println(new String(sc));

        String intStr = "-394";
        int i = Integer.parseInt(intStr);
        System.out.println(i);

        try{
            int t = "abc".length();
        }finally{
            System.out.println("ok");
        }

        System.out.println(intStr.substring(1));

        for( i =0 ;i<10;i++)
//            Integer k = i+5;
        System.out.println("abc");
    }

}

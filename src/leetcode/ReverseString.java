package leetcode;


public class ReverseString {

    public static void main(String[] args) {
        String s= " good after    noon!   ";
//        String trimed = trimStringSpaces(s);
//
//        System.out.println(trimed);
//        char[] ca = trimed.toCharArray();
//        // reverse all
//        reverseAll(ca, 0, trimed.length()-1);
//        System.out.println(new String(ca));
//
//        // reverse each word
//        reverseEach(ca);
//        System.out.println(new String(ca));

        System.out.println(reverseWordsV2(s));
    }

    public static String reverseWordsV2(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        // 倒着找单词，因为要反转
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索空格，取子串就是单词
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    public static String reverseWordsV3(String s) {
        s = s.trim();
        int j = s.length()-1, i=j;
        StringBuilder res = new StringBuilder();

        while(i>=0) {
            while(i>=0 && s.charAt(i)!=' ') i--;
            res.append(s.substring(i+1, j+1) + " ");
            while(i>=0 && s.charAt(i) == ' ') i--;
            j = i;
        }
        return res.toString().trim();
    }

    public static void reverseEach(char[] ca) {
        int i = 0;
        while(i<ca.length) {
            int end = i;
            while(end <ca.length && ca[end] != ' ') end++;
            reverseAll(ca, i, end-1);
            i = end+1;
        }
    }

    public static void reverseAll(char[] ca, int start, int end) {
        char tmp ;

        while(start<end) {
            tmp = ca[start];
            ca[start++] = ca[end];
            ca[end--] = tmp;
        }

    }

    public static String trimStringSpaces(String s) {
        int n = s.length();

        int start = 0;
        while(start<n && s.charAt(start) == ' ') start++;

        int end = n-1;
        while(end>=0 && s.charAt(end) == ' ') end--;

        char[] ca = new char[end-start+1];
        int idx = 0;
        while(start <= end) {
            char c = s.charAt(start);
            if (c != ' ') {
                ca[idx++] = c;
            } else if (s.charAt(start -1)!=' ') {
                ca[idx++] = c;
            }
            start++;
        }
        return new String(ca).substring(0,idx);
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') --right;

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c);

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') ++end;
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    public String reverseWords(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

}

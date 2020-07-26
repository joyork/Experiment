package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

public class NumSquares {

    public static int numSquares(int n) {
        ArrayList<Integer> sqs = new ArrayList<Integer>();
        for(int i=1;i*i<=n;i++) {
            sqs.add(i*i);
        }

        int level = 0;
        HashSet<Integer> queue = new HashSet<Integer>();
        queue.add(n);

        while(!queue.isEmpty()) {
            level++;
            HashSet<Integer> nextQueue = new HashSet<Integer>();

            for(Integer num : queue) {
                for(Integer sqNum : sqs) {
                    if(num.equals(sqNum)) {
                        return level;
                    } else if(num > sqNum){
                        nextQueue.add(num-sqNum);
                    } else {
                        break;
                    }
                }
            }
            queue = nextQueue;
        }
        return level;
    }

    public static int numSquaresInDP(int n) {
        ArrayList<Integer> sqs = new ArrayList<Integer>();
        for(int i = 1;i*i<=n; i++) {
            sqs.add(i*i);
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1;i<=n; i++) {
            for (Integer sq : sqs) {
                if(i<sq) {
                    break;
                }
                dp[i] = Math.min(dp[i],dp[i-sq]+1);

            }
        }
        return dp[n];
    }

    public static boolean isOperator(String str){
        Pattern pattern = Pattern.compile("\\+|-|\\*|/");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println(numSquaresInDP(15));
        System.out.println(isOperator("+"));
        System.out.println(isOperator("-"));
        System.out.println(isOperator("*"));
        System.out.println(isOperator("/"));

    }
}

package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class OpenLock {
    public static int openLock(String[] deadends, String target) {
        // set to store deadends
        HashSet<String> deadSet = new HashSet<String>(deadends.length);
        for(String dead : deadends) {
            deadSet.add(dead);
        }

        String root = "0000";

        int level = -1;
        // visited
        HashSet<String> visited = new HashSet<>();
        visited.add(root);

        // build queue，cur status enqueue
        Queue<String> queue = new LinkedList<String>();
        queue.offer(root);

        // BFS for 8 neibors for one status
        while(!queue.isEmpty()) {
            level++;
            System.out.println("now level is:"+ level);

            int curLvlSize = queue.size();
            for (int k = 0; k< curLvlSize; k++) {

                String curStatus = queue.peek();
                System.out.println("dequeue:" + curStatus);
                if (!deadSet.contains(curStatus)) {

                    if(curStatus.equals(target)) {
                        return level;
                    }


                    for(int i = 0 ; i<4; i++) {
                        for(int d = -1; d<2; d+=2) {

                            int nextDigit = ((curStatus.charAt(i) - '0') + d + 10) %10;
                            String nextStatus = curStatus.substring(0,i) + ("" + nextDigit) + curStatus.substring(i+1);
                            if(!deadSet.contains(nextStatus) && !visited.contains(nextStatus)) {
                                queue.add(nextStatus);
                                visited.add(nextStatus);
                                System.out.println("enqueue:" + nextStatus);
                            }
                        }
                    }
                }

                queue.poll();
            }

        }

        return -1;
    }

    public static void main(String[] args) {
//        String[] deadends = {"0201","0101","0102","1212","2002"};
//        String target = "0202";

        String[] deadends = {"0000"};
        String target = "8888";

        System.out.println("result:" + openLock(deadends, target));
    }
}

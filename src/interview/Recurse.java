package interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Recurse {
    public static void main(String[] args) {
        String[] array = new String[]{"a","b","c","d","c"};
        listAll(Arrays.asList(array),"");
    }

    public static void listAll(List candidate, String prefix){
        if(candidate.isEmpty()){
            System.out.println(prefix);
        }
        for(int i = 0;i<candidate.size();i++){
            List temp = new LinkedList(candidate);
            String removed = (String) temp.remove(i);
            listAll(temp, prefix+removed);
        }
    }
}

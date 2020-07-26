package algorithm.sort;

import java.util.Random;

public class ShellSort {

    public static int count = 0;

    public static void exchange(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }


    public static void shellSort(int[] a) {
        int n = a.length;
        int d = 1;
        while(d < n/3) d = d*3 + 1;
        while (d>=1) {
            for (int i = d; i<n; i++) {
                for (int j = i; j>=d && a[j] < a[j-d]; j-=d) {
                    exchange(a, j, j-d);
                    print(a);
                    count++;
                }
            }
            d = d/3;
        }
    }


    public static void main(String[] args) {
        int n = 15;
//        int[] a = new int[n];
//        for (int i = 0; i <n; i++) {
//            a[i] = new Random().nextInt(100);
//            System.out.print(a[i] + ", ");
//        }
//        System.out.println();

        int[] a = {74, 27, 1, 66, 93, 14, 88, 37, 52, 27, 68, 2, 20, 37, 36};

        shellSort(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println("\ncount:" + count);

 

    }
}

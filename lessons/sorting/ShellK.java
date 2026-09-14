package sorting;

/**************************************************************************
 *  Class: ShellK.java
 * 
 *  Sorts an array using shell sort, using custom stride functions
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ShellK {

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>>
    void sort(T[] a, List<Integer> sequence) {
        int n = a.length;

        for (int h : sequence) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && Sort.less(a[j], a[j-h]); j -= h) {
                    Sort.exchange(a, j, j-h);
                }
            }
        }
    }

    /**
     * Reads in a sequence of strings from standard input; Shellsorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = 100000;
        Double[] arr = new Double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Math.random();
        }
        ArrayList<Integer> increments = new ArrayList<Integer>();
        int h = 1;
        while (h < n) {
            increments.add(h);
            h = 3*h+1;//10;//n;//2*h+1;
        }
        long start = System.currentTimeMillis();
        ShellK.sort(arr, increments.reversed());
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("The sort of %d numbers took %dms\n", n, elapsed);
    }

}


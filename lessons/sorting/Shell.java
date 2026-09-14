package sorting;
/**************************************************************************
 *  Class: Shell.java
 * 
 *  Sorts an array using shell sort
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

public class Shell {

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>>
    void sort(T[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && Sort.less(a[j], a[j-h]); j -= h) {
                    Sort.exchange(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        assert Sort.isSorted(a);
        System.out.println(Arrays.toString(a));
    }

}

package sorting;
/**************************************************************************
 *  Class: InsertionX.java
 * 
 *  Sorts an array using insertion sort using a half exchange optimization
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

public class InsertionX {

    /**
     * Rearranges the array in ascending order using insertion sort
     * On loop k, will insert item k into the left subarray
     *   (keep moving left until in the right position)
     * 
     * This includes the optimization that it will not swap,
     *   but move each intermediate value right
     *   
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> 
    void sort(T[] a) {
        int n = a.length;

        // insertion sort with half-exchanges
        for (int i = 1; i < n; i++) {
            // store the value at a[i]
            T v = a[i];
            int j = i-1;
            while (j > 0 && Sort.less(v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        InsertionX.sort(a);
        assert Sort.isSorted(a);
        System.out.println(Arrays.toString(a));
    }
}

package sorting;
/**************************************************************************
 *  Class: Insertion.java
 * 
 *  Sorts an array using insertion sort
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

public class Insertion {

    /**
     * Rearranges the array in ascending order using insertion sort
     * On loop k, will insert item k into the left subarray
     *   (keep swapping left until in the right position)
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> 
    void sort(T[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && Sort.less(a[j], a[j-1]); j--) {
                Sort.exchange(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        assert Sort.isSorted(a);
        System.out.println(Arrays.toString(a));
    }
}

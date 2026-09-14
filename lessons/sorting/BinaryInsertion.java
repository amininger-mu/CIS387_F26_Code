package sorting;
/**************************************************************************
 *  Class: BinaryInsertion.java
 * 
 *  Sorts an array using insertion sort, using binary search + half exchange
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;

public class BinaryInsertion {

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>>
    void sort(T[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {

            // binary search to determine index j at which to insert a[i]
            T v = a[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (Sort.less(v, a[mid])) hi = mid;
                else                 lo = mid + 1;
            }

            // insertion sort with "half exchanges"
            // (insert a[i] at index j and shift a[j], ..., a[i-1] to right)
            for (int j = i; j > lo; --j)
                a[j] = a[j-1];
            a[lo] = v;
        }
    }

    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        assert Sort.isSorted(a);
        System.out.println(Arrays.toString(a));
    }
}

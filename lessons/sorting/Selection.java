package sorting;
/**************************************************************************
 *  Class: Selection.java
 * 
 *  Sorts an array using selection sort
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

public class Selection {

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> 
    void sort(T[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (Sort.less(a[j], a[min])) min = j;
            }
            Sort.exchange(a, i, min);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Selection.sort(a);
        assert Sort.isSorted(a);
        System.out.println(Arrays.toString(a));
    }
}

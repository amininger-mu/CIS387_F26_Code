/**************************************************************************
 *  Class: Merge.java
 * 
 *  Sorts an array using top-down merge sort
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.lang.reflect.Array;
import java.util.Arrays;

public class Merge {

    /**
	 * sort(a)
	 *
	 * Arranges the given array in ascending order using merge sort
	 * Divides array in half, recursively merge sorts each half,
	 *   then does a merge operation (using auxilliary array) to combine two halves
     */
    public static void sort(int[] a) {
        int[] aux = Arrays.copyOf(a, a.length);
        sort(a, aux, 0, a.length-1);
    }

    /**
	 * sort(a, aux, lo, hi)
	 *
	 * mergesort a[lo..hi] using auxiliary array aux[lo..hi]
	 **/
    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
    }

    /**
	 * merge(a, aux, lo, mid, hi)
	 *
     * stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
     **/
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		// copy a into aux
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (aux[j] < aux[i])      a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        Sort.runTest(n, Merge::sort);
    }
}

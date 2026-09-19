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

public class Shell {

    /**
	 * sort(a)
	 *
	 * Arranges the given array in ascending order using shell sort
	 *
	 * Starting with stride h, h-sorts the array using insertion sort,
	 *   and repeats, dividing h / 3, until insertion sorts with h=1
     */
    public static void sort(int[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && a[j] < a[j-h]; j -= h) {
                    Sort.exchange(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        Sort.runTest(n, Shell::sort);
    }
}

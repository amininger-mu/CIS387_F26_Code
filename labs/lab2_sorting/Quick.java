/**************************************************************************
 *  Class: Quick.java
 * 
 *  Sorts an array using quick sort
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

import java.util.Arrays;

public class Quick {

    /**
	 * sort(a)
	 * 
	 * Arranges the given array in ascending order using quick sort
	 *
	 * Does a partition operation to choose a pivot 
	 *   and arrange elements on left/right sides
	 * 
	 * Then recursively quick sorts each side
     */
    public static void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

	/**
	 * sort(a, lo, hi)
	 *
     * recursively quicksort the subarray from a[lo] to a[hi]
	 **/
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

	/**
	 * partition(a, lo, hi) -> int
	 *
     * partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
     * and return the index j.
	 **/
    private static int partition(int[] a, int lo, int hi) {
        int pivot = a[lo]; // choose lo item to be pivot
        int left = lo+1; // initialize left + right indices at edge of range
        int right = hi;
        while (true) {
			// Keep increasing left index until you find a value >= pivot
            while (a[left] < pivot) {
				if (left >= hi) break;
                left++;
            }

			// Keep decreasing right index until you find a value <= pivot
            while (a[right] > pivot) {
				if (right <= lo) break;
                right--;
            }

			// If the left/right indices cross, you are done
            if (left >= right) break;

			// swap the out of place values from left and right
            Sort.exchange(a, left, right);
			left++;
			right--;
        }

		// Swap the pivot into position
        Sort.exchange(a, lo, right);
        return right;
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        Sort.runTest(n, Quick::sort);
    }
}

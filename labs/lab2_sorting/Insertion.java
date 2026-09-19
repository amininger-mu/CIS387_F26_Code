/**************************************************************************
 *  Class: Insertion.java
 * 
 *  Sorts an array using insertion sort
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

public class Insertion {

    /**
	 * sort(a)
	 * 
	 * Arranges the given array in ascending order using insertion sort
	 *
	 * On iteration k, will take element at index k and swap left
	 *    until it is in the correct position
	 * (items 0:k will be in correct order)
     */
    public static void sort(int[] a) {
		int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j] < a[j-1]; j--) {
                Sort.exchange(a, j, j-1);
            }
        }
    }

	/***
	 * insertions sorts given range [lo:hi] inclusive
	 **/
    public static void sort(int[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            for (int j = i; j > lo && a[j] < a[j-1]; j--) {
                Sort.exchange(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        Sort.runTest(n, Insertion::sort);
    }
}

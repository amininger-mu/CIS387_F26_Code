/**************************************************************************
 *  Class: Sort.java
 * 
 *  Defines a functional interface: sort(int[] a)
 *    and a collection of static helper functions
 *
 *  Static Helper Functions:
 *  - exchange(a, i, j)
 *  - isSorted(a)  - check if array is sorted
 *  - shuffle(a)   - randomly shuffle array
 *  - makeRandomArray(n), makeOrderedArray(n), makeReverseArray(n)
 *      functions that create arrays of given length
 *  - runTest(n, alg)  - run a standard test of a sorting algorithm
 *  - parseSortAlgorithm(name) - returns the algorithm with the given name
 *
 *  This code is adapted from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 ***********************************************************************/

import java.util.Arrays;

@FunctionalInterface 
public interface Sort {

    /***
     *  The signature for a sorting method that
     *     arranges the given array a in ascending order
     ***/
    void sort(int[] a);


	public static void exchange(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/

    public static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length);
    }

    // is the array a[lo..hi) sorted
    public static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }

   /***************************************************************************
    *  shuffle(arr) - randomly shuffles the array (uniformly)
    ***************************************************************************/

	public static void shuffle(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int j = i + (int)(Math.random() * (a.length - i));
			int swap = a[i];
			a[i] = a[j];
			a[j] = swap;
		}
	}
   /***************************************************************************
	*  Generating Random Arrays of Numbers
	*
    *  makeRandomArray(n) -> int[] - generates an array of n random ints, from 0-10n
    *  makeOrderedArray(n) -> int[] - generates an array of n integers from 1-n
    *  makeReversedArray(n) -> int[] - generates an array of n random integers from n-1
	*
    ***************************************************************************/

	public static int[] makeRandomArray(int n) {
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = (int)(Math.random()*n*10);
		}
		return a;
	}

	public static int[] makeOrderedArray(int n) {
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = i+1;
		}
		return a;
	}

	public static int[] makeReversedArray(int n) {
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = n - i;
		}
		return a;
	}

   /***************************************************************************
	*  runTest(n, sort)
    *  Creates an array of length n, random integers from 0-10n, and sorts it
    ***************************************************************************/
    public static void runTest(int n, Sort sortFunction) {
        int[] a = makeRandomArray(n);
        System.out.println("Sorting array of " + n + " integers");
        System.out.println(Arrays.toString(a));
        sortFunction.sort(a);
        System.out.println(Arrays.toString(a));
    }

   /***************************************************************************
	*  parseSortAlgorithm(n, sort)
	 * Returns the proper sort algorithm associated with the given String
	 * (or throws a IllegalArgumentException)
	 */
    public static Sort parseSortAlgorithm(String alg) {
		alg = alg.toLowerCase();
		switch (alg) {
			case "insertion": return Insertion::sort;
			case "selection": return Selection::sort;
			case "shell":     return Shell::sort;
			case "merge":     return Merge::sort;
			case "quick":     return Quick::sort;
			case "system":    return (a) -> Arrays.sort(a);
			default: 
				throw new IllegalArgumentException("Invalid algorithm: " + alg);
		}
    }

}

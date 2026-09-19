/**************************************************************************
 *  Class: Selection.java
 * 
 *  Sorts an array using selection sort
 * 
 *  This code is taken from the algs4 library by Sedgewick and Wayne in 
 *    Algorithms, 4th Edition (https://algs4.cs.princeton.edu)
 * 
 ***********************************************************************/

public class Selection {

    /**
	 * sort(a)
	 *
	 * Arranges the given array in ascending order using selection sort
	 *
     * On loop k, will swap the smallest element in range [k:n] into spot k
     */
    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (a[j] < a[min]) min = j;
            }
            Sort.exchange(a, i, min);
        }
	}

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        Sort.runTest(n, Selection::sort);
    }
}

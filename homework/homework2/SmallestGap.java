/**************************************************************************
 *  Class: SmallestGap.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find the smallest gap between any two integers in an array
 * 
 *  Example: [4, 11, 1, 20, 13] -> the smallest gap is 2 (11 & 13)
 * 
 *  This file has two functions:
 *  - int smallestGap(int[] arr): the default n^2 version (check every pair)
 *  - int smallestGapFast(int] arr): the faster version using sorting (n lg n)
 * 
 *  To run this program, do either 'java SmallestGap.java' or 'java SmallestGap.java -f' (for fast)
 *     and provide a list of ints in standard in
 * 
 *  To pass data from a file, do: 'cat gap_test_sm.txt | java SmallestGap'
 * 
 ***********************************************************************/

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class SmallestGap {

    // Returns the smallest gap between two values in the given array
    public static int smallestGap(int[] arr) {
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int gap = Math.abs(arr[i] - arr[j]);
                minGap = Math.min(minGap, gap);
            }
        }
        return minGap;
    }

    public static int smallestGapFast(int[] arr) {
        return 0;
    }

	// Creates a test of length n and calls smallestGap
	//   if fast = true, runs smallestGapFast
    public static int runTest(int n, boolean fast) {
        int[] arr = new int[n];
        int num = 1;

        // Fill in the array, using an increasing sequence with random gaps
        for (int i = 0; i < n; i++) {
            arr[i] = num;
            num += 1 + (int)(Math.random() * 100); // add gap between 1 and 100
        }

        // randomly shuffle the array
        shuffle(arr);

        // run the appropriate algorithm
        if (fast) {
            return smallestGapFast(arr);
        } else {
            return smallestGap(arr);
        }
    }

	// Randomly shuffles the given array
	public static void shuffle(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int j = i + (int)(Math.random() * (a.length - i));
			int swap = a[i];
			a[i] = a[j];
			a[j] = swap;
		}
	}

    /******
     * Reads ints from standard in, then runs smallest gap test on them
     * 
     * To run with fast algorithm, give -f as argument
     *******/
    public static void main(String[] args) {

        // boolean fast = whether to use fast version of algorithm, 
        //    defaults to slow (run SmallestGap -f for fast)
        boolean fast = false;
        if (args.length > 0 && args[0].equals("-f")) {
            fast = true;
        }

        System.out.println("Reading numbers from standard in.");
        System.out.println("Enter a sequence of integers, followed by an 'e' to end: ");

        // Read ints from standard in
		List<Integer> nums = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			nums.add(scanner.nextInt());
		}

        // Convert list to int[]
        int[] arr = nums.stream().mapToInt(Integer::intValue).toArray();

        System.out.println("Read " + arr.length + " numbers");

		// true = fast version, false = slow version
        if (fast) {
            System.out.println("Smallest Gap = " + smallestGapFast(arr));
        } else {
            System.out.println("Smallest Gap = " + smallestGap(arr));
        }

        scanner.close();
    }
}

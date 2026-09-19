/******************************************************************************
 *  Compilation:  javac TestSort.java
 *  Execution:    java -ea TestSort alg
 *
 *  Runs the given sorting algorithm:
 *  - on random arrays length n from 1-1000
 *  - sorted arrays length n^2 from 1-100
 *  - reversed arrays length n^2 from 1-100
 *
 ******************************************************************************/

import java.util.Arrays;

public class TestSort {

	/** Given an int array,
	 *  Returns a sorted copy (using Arrays.sort)
	 */
	private static int[] getSolution(int[] a) {
		int[] copy = Arrays.copyOf(a, a.length);
		Arrays.sort(copy);
		return copy;
	}

    public static void main(String[] args) {
		// arg0 = the name of the sorting algorithm
		//        uses Sort::parseSortAlgorithm return the right function
		//        (or raise an exception)
		Sort sortAlg = Sort.parseSortAlgorithm(args[0]);

		System.out.println("Running test of algorithm: " + args[0]);

		System.out.println("Testing random arrays");

		// Test 1: Sort Random arrays of length 1-1000
		for (int n = 1; n <= 1000; n++) {
			int[] arr = Sort.makeRandomArray(n);
			int[] solution = getSolution(arr);
			sortAlg.sort(arr);
			if(!Arrays.equals(arr, solution)) {
				System.out.println("Failed Test size " + n);
				return;
			}
		}

		System.out.println("Testing ordered arrays");

		// Test 2: Sort Ordered arrays of length n^2 (n = 1-10)
		for (int n = 1; n <= 10; n++) {
			int[] arr = Sort.makeOrderedArray(n*n);
			int[] solution = getSolution(arr);
			sortAlg.sort(arr);
			if(!Arrays.equals(arr, solution)) {
				System.out.println("Failed Test size " + n);
				return;
			}
		}

		System.out.println("Testing reversed arrays");

		// Test 3: Sort Reversed arrays of length n^2 (n = 1-10)
		for (int n = 1; n <= 10; n++) {
			int[] arr = Sort.makeReversedArray(n*n);
			int[] solution = getSolution(arr);
			sortAlg.sort(arr);
			if(!Arrays.equals(arr, solution)) {
				System.out.println("Failed Test size " + n);
				return;
			}
		}

		System.out.println("Testing complete");
    }
}

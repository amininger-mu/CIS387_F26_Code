/**************************************************************************
 *  Class: SetEquality.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find whether two sets (arrays) are equal
 *    the arrays contain n distinct values in any order
 * 
 *  Example: [1, 4, 6] and [6, 1, 4] are equal
 *           [1, 4, 6] and [1, 6, 2] are not
 * 
 *  This file has two functions:
 *  - int equalSets(int[] arr): the default n^2 version (checks all items in A are in B, and all items in B are in A)
 *  - int equalSetsFast(int] arr): the faster version using sorting (n lg n)
 * 
 *  To run this program, do either 'java SetEquality.java' or 'java SetEquality.java -f' (for fast)
 *     and provide a list of ints in standard in
 * 
 *  To pass data from a file, do: 'cat gap_test_sm.txt | java SmallestGap'
 * 
 ***********************************************************************/
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class SetEquality {
    // Returns the first index q appears in the array, or -1 if not present
    public static int find(int[] arr, int q) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == q)
                return i;
        }
        return -1;
    }

    // Returns true if the two sets have the same elements
    //   (Assumes that their elements are distinct)
    public static boolean equalSets(int[] a, int[] b) {
        if (a.length != b.length) return false;

        for (int x : a) {
            if (find(b, x) == -1) return false;
        }
        for (int x : b) {
            if (find(a, x) == -1) return false;
        }
        return true;
    }

    // Returns true if the two sets have the same elements
    //   (Assumes that their elements are distinct)
    public static boolean equalSetsFast(int[] a, int[] b) {
        
        return false;
    }

    // Creates two identical arrays of length n, 
    // shuffles them, and then checks equality
    public static boolean runTest(int n, boolean fast) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        int[] b = a.clone();
        shuffle(a);
        shuffle(b);

        // run the appropriate algorithm
        if (fast) {
            return equalSetsFast(a, b);
        } else {
            return equalSets(a, b);
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

	// Runs a test on arrays of length N
    public static void main(String[] args) {
        // boolean fast = whether to use fast version of algorithm, 
        //    defaults to slow (run SmallestGap -f for fast)
        boolean fast = false;
        if (args.length > 0 && args[0].equals("-f")) {
            fast = true;
        }

        // Read ints from standard in for set A
        System.out.println("Reading Set A from standard in");
        System.out.println("Enter a sequence of integers, followed by an 'e' to end: ");

		List<Integer> nums = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			nums.add(scanner.nextInt());
		}

        // Convert list to int[]
        int[] A = nums.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("Read " + A.length + " numbers");

        scanner.next(); // consumes e = end

        // Read ints from standard in for set B
        System.out.println("Reading Set B from standard in");
        System.out.println("Enter a sequence of integers, followed by an 'e' to end: ");

		nums = new ArrayList<Integer>();
		while (scanner.hasNextInt()) {
			nums.add(scanner.nextInt());
		}

        // Convert list to int[]
        int[] B = nums.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("Read " + B.length + " numbers");

		// true = fast version, false = slow version
        if (fast) {
            System.out.println("Sets are equal? " + equalSetsFast(A, B));
        } else {
            System.out.println("Sets are equal? " + equalSets(A, B));
        }

        scanner.close();
    }
}

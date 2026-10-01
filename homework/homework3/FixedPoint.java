/**************************************************************************
 *  Class: FixedPoint.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find a fixed point in a sorted array
 *    (where a[i] == i)
 * 
 *  For example, given the array `[-10, -5, -2, 0, 3, 5, 8, 11, 21]`, the fixed point is 5. 
 *  
 * 
 *  This file has two functions. They assume the given array is sorted,
 *    and will return the index of a fixed point (or -1)
 * 
 *  - int fixedPointSlow(int[] arr): the default linear scan
 *  - int fixedPoint(int[] arr): the divide and conquer you will implement
 * 
 *  To run this program, do 'java FixedPoint.java'
 *     and provide a list of ints
 * 
 *  To pass data from a file, do: 'cat fp_test1.txt | java FixedPoint.java'
 ***********************************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class FixedPoint {

    // Assumes arr is a sorted array of distinct integers
    // Returns a fixed point, an index where arr[i] == i
    //   If multiple exist, may return any of them
    //   If no fixed point exists, returns -1
    public static int findFixedPointSlow(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) {
                return i;
            }
        }
        return -1;
    }

    // Assumes arr is a sorted array of distinct integers
    // Returns the fixed point, an index where arr[i] == i
    //   If multiple exist, may return any of them
    //   If no fixed point exists, returns -1
    public static int findFixedPoint(int[] arr) {

        return -1;
    }

    /*****
     * FixedPoint::main
     * 
     * Reads a list of distinct, sorted integers from StandardIn, 
     *   then calls findFixedPoint, and compares 2 versions
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read ints from standard in
        List<Integer> nums = new ArrayList<>();
        while (scanner.hasNextInt()) {
            nums.add(scanner.nextInt());
        }
        // Convert list to int[]
        int[] arr = nums.stream().mapToInt(Integer::intValue).toArray();

        int slowResult = findFixedPointSlow(arr);
        System.out.println("findFixedPointSlow returned " + slowResult);

        int fastResult = findFixedPoint(arr);
        System.out.println("findFixedPoint returned " + fastResult);

        if (slowResult == fastResult) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed.");
        }

        scanner.close();
    }

    // Runs 1000 random trials of findFixedPoint for an array of size N 
    // It generates an array of lenght N with 1 fixed point at a random index
    // If fast = true, calls findFixedPoint, otherwise, findFixedPointSlow
    // It returns the time to compute in microseconds
    public static long runTest(int n, boolean fast) {
        long totalTime = 0;
        int[] arr = new int[n];

        // Do 1000 random trials
        for (int trial = 0; trial < 1000; trial++) {
            int fp = (int)(Math.random() * n); // fixed point index
            if (Math.random() < 0.25) { // 25% chance of no fixed point
                fp = -1;
            }

            // elements below fp index are i-1, elements above fp index are i+1
            for (int i = 0; i < n; i++) {
                arr[i] = i + Integer.compare(i, fp); // -1, 0, or +1
            }

            int result;
            long startTime = System.nanoTime();
            if (fast) {
                result = findFixedPoint(arr);
            } else {
                result = findFixedPointSlow(arr);
            }
            totalTime += System.nanoTime() - startTime;
            if (result != fp) {
                System.err.println("Test Failed!");
                System.err.println(Arrays.toString(arr));
                System.err.println("Got result " + result + " != answer " + fp);
            }
        }

        return totalTime / 1000;
    }

}


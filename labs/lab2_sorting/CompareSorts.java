/******************************************************************************
 *  Compilation:  javac CompareSorts.java
 *  Execution:    java CompareSorts alg1 alg2 n trials
 *
 *  Sort n random real numbers, trials times using the two
 *  algorithms specified on the command line.
 *
 *  % java CompareSorts Insertion Selection 1000 100
 *  For 1000 random Doubles
 *    Insertion is 1.7 times faster than Selection
 *
 *  Note: this program is designed to compare two sorting algorithms with
 *  roughly the same order of growth, e,g., insertion sort vs. selection
 *  sort or mergesort vs. quicksort. Otherwise, various system effects
 *  (such as just-in-time compiliation) may have a significant effect.
 *  One alternative is to execute with "java -Xint", which forces the JVM
 *  to use interpreted execution mode only.
 *
 ******************************************************************************/

import java.util.Arrays;

public class CompareSorts {

    static class TimingResult {
        double time1 = 0;
        double time2 = 0;
    }

    // Sorts the given array using the given algorithm,
    //   and returns the time in seconds (as a double)
    public static double time(Sort algorithm, int[] a) {
		long start = System.nanoTime();
        algorithm.sort(a);
		long elapsed = System.nanoTime() - start;
        return elapsed / 1000000000.0;
    }

    // Use alg1 and alg2 to sort trials random arrays of length n.
    public static TimingResult compareSorts(Sort alg1, Sort alg2, int n, int trials)  {
        TimingResult result = new TimingResult();

        // In each trial - perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            // Using same array for both
            int[] a = Sort.makeRandomArray(n);
            int[] b = Arrays.copyOf(a, a.length);
            result.time1 += time(alg1, a);
            result.time2 += time(alg2, b);
        }
        return result;
    }

    public static void main(String[] args) {
		if (args.length < 4) {
			System.err.println("Usage: CompareSorts <alg1> <alg2> <size> <nTrials>");
			return;
		}
        // arg0 and arg1 are the 2 sorting algorithms to compare
		String alg1Name = args[0];
		String alg2Name = args[1];
        Sort alg1 = Sort.parseSortAlgorithm(alg1Name);
        Sort alg2 = Sort.parseSortAlgorithm(alg2Name);

        // arg2 = size of arrays to sort
        int n = Integer.parseInt(args[2]);

        // arg3 = number of trials to run
        int trials = Integer.parseInt(args[3]);

        TimingResult result = compareSorts(alg1, alg2, n, trials);;

        System.out.printf("For %d random Integers\n", n);
        System.out.printf("   %10s Sort totalled %10.3fs\n", alg1Name, result.time1);
        System.out.printf("   %10s Sort totalled %10.3fs\n", alg2Name, result.time2);

        if (result.time1 < result.time2) {
            System.out.printf("   %s was %.2f times faster than %s\n", 
                    alg1Name, result.time2/result.time1, alg2Name);
        } else {
            System.out.printf("   %s was %.2f times faster than %s\n", 
                    alg2Name, result.time1/result.time2, alg1Name);
        }
    }
}

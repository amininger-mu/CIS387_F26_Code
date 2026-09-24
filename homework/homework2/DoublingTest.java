/***
 * File: DoublingTest.java
 *
 * Runs a doubling experiment on a given algorithm
 * Starts with problem size N=1000, runs the test, prints the time, 
 *   then doubles N and repeats until time is above 2 seconds
 ***/
public class DoublingTest {

    public static void main(String[] args) {
        System.out.printf(" %8s | %12s | %5s |\n", "N", "Time", "Ratio" );

        int N = 1000;
        long prevElapsed = -1;

        while (true) {
            // Measure Time to add N random edges
            long start = System.nanoTime();

			// !!! RUN YOUR TEST HERE!!!
            SmallestGap.runTest(N, false);

            long elapsed = (System.nanoTime() - start) / 1000; // microseconds

            if (prevElapsed != -1) {
                double ratio = (double)elapsed / prevElapsed;
                System.out.printf(" %8d | %12d | %5.3f |\n", N, elapsed, ratio);
            } else {
                System.out.printf(" %8d | %12d |       |\n", N, elapsed);
			}
            prevElapsed = elapsed;

            // If it took over 10 seconds, quit
            if (elapsed > 2_000_000) {
                break;
            }
            N *= 2; // double N
        }
    }
}

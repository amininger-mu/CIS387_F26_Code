/***
 * File: DoublingTest.java
 * 
 * main program:
 * - args[0]: -s to run slow version of algorithm
 * 
 * Will run a doubling test, starting at N=100, until the time exceeds 10 seconds
 ***/
public class DoublingTest {


    public static void main(String[] args) {
        System.out.printf(" %8s | %12s | %5s |\n", "N", "Time", "Ratio" );

        boolean fast = true;
        if (args.length > 0 && args[0].equals("-s")) {
            fast = false;
        }

        int N = 100;
        long prevElapsed = -1;

        while (true) {
            // Algorithm to call
            long elapsed = FixedPoint.runTest(N, fast);

            if (prevElapsed != -1) {
                double ratio = (double)elapsed / prevElapsed;
                System.out.printf(" %8d | %12d | %5.3f |\n", N, elapsed, ratio);
            }
            prevElapsed = elapsed;

            N *= 2; // double N
            // If it took over 1 second, or N is too big, quit
            if (N > 100_000_000 || elapsed > 10_000_000) {
                break;
            }
        }
    }
}

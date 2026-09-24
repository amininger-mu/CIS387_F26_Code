/***
 * File: DoublingTest.java
 ***/
public class DoublingTest {

    public static void main(String[] args) {
        System.out.printf(" %8s | %12s | %5s |\n", "N", "Time", "Ratio" );

        int N = 100;
        long prevElapsed = -1;

        while (true) {
            // Measure Time to add N random edges
            long start = System.nanoTime();
            HasDuplicate.runTest(N, true);
            long elapsed = (System.nanoTime() - start) / 1000; // microseconds

            if (prevElapsed != -1) {
                double ratio = (double)elapsed / prevElapsed;
                System.out.printf(" %8d | %12d | %5.3f |\n", N, elapsed, ratio);
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

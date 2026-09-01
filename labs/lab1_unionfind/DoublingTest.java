/** *
 * File: DoublingTest.java
 *
 * Runs a doubling test using one of the union find algorithms
 *   Starts at N=100 sites, and does N union operations
 *   Then doubles N and repeats until a run is over 10 seconds.
 ***/
public class DoublingTest {
    public static void main(String[] args) {
        System.out.printf(" %8s | %12s | %5s |\n", "N", "Time", "Ratio" );

        int N = 100;
        long prevElapsed = 100;

        while (true) {
            IUnionFind uf = new QuickFindUF(N);

            // Measure Time to add N random edges
            long start = System.nanoTime();

            for (int e = 0; e < N; e++) {
                int p = (int)(Math.random() * N);
                int q = (int)(Math.random() * N);
                uf.union(p, q);
            }

            long elapsed = (System.nanoTime() - start) / 1000; // microseconds
            System.out.printf(" %8d | %12d | %5.3f |\n", N, elapsed, (double)elapsed / prevElapsed );
            prevElapsed = elapsed;

            // If it took over 10 seconds, quit
            if (elapsed > 10_000_000) {
                break;
            }
            N *= 2; // double N
        }
    }
}

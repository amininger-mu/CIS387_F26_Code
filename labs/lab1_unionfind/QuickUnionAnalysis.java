import java.util.stream.IntStream;

public class QuickUnionAnalysis {

    private final static int N = 1000;

    private static double calcAverageDepth(WeightedQuickUnionUF uf) {
        return IntStream.range(0, N).map(i -> uf.getDepth(i)).average().getAsDouble();
    }

    public static void main(String[] args) {

        // Test 1: Add edges (i -> i+1)
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        for (int i = 0; i < N-1; i++){
            uf.union(i, i+1);
        }
        System.out.printf("Forward: Avg Depth = %.3f\n", calcAverageDepth(uf));

        // Test 2: Add edges (i -> i-1);
        uf = new WeightedQuickUnionUF(N);
        for (int i = 1; i < N; i++){
            uf.union(i, i-1);
        }
        System.out.printf("Backward: Avg Depth = %.3f\n", calcAverageDepth(uf));

        // Test 3: Add edges randomly (5 times)
        for (int t = 0; t < 5; t++) {
            uf = new WeightedQuickUnionUF(N);
            for (int i = 0; i < N-1; i++) {
                int p = (int)(Math.random() * N);
                int q = (int)(Math.random() * N);
                uf.union(p, q);
            }
            System.out.printf("Random: Avg Depth = %.3f\n", calcAverageDepth(uf));
        }
    }
}

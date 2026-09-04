/****
 * program ProbabilityTest
 * 
 * Runs a random Monte Carlo simulation of a percolation system,
 * It creates random NxN grids with a certain probability p each site is open,
 *   then runs 100 trials to estimate the probability the grid percolates
 * 
 * It sweeps through a range of P(open) probability values
 * 
 * Student Name: _YOUR_NAME_HERE_
 * 
 * Write a comment below describing what you observe in the data.
 * - How does the choice of p = P(open) affect the probability a system percolates?
 * - What value of p seems to result in a 50/50 chance of it percolating?
 */
public class ProbabilityTest {

    public static void main(String[] args) {
        final int TRIALS = 100;  // Trials to run for each p value
        final int N = 40;          // Size of grid to create (NxN)

        for (double p = 0.2; p <= 0.8; p += 0.02) {
            int nSuccess = 0;
            
            // Run some number of trials, determine sample probability that it percolates
            for (int i = 0; i < TRIALS; i++) {
                boolean[][] grid = Percolation.createRandomGrid(N, p);
                if (Percolation.percolates(grid)) {
                    nSuccess++;
                }
            }

            System.out.printf("P(open) = %.3f, P(percolates) = %.3f\n", 
								p, (double)nSuccess / TRIALS);
        }
    }
}

/***
 * Implementation of dynamic programming solutions to the problem
 *   of cutting a steel rod into pieces to maximize the revenue
 * 
 * Problem is defined as having a rod of length N, 
 *   and a table of sell prices for each possible integer length
 * 
 * What is the best way to divide up the rod (maximize revenue)?
 */

public class DivideRod {

    /*************** TOP DOWN SOLUTION  *****************/
    static int cutRodTD(int n, int[] prices) {
        int[] cache = new int[n+1];
        return cutRodTD(n, prices, cache);
    }

    // Pass in a cache (lookup table) to store computed answers to subproblems
    // cache[i] = the max revenue for a rod of length i
    static int cutRodTD(int n, int[] prices, int[] cache) {
        if (n == 0) return 0;
        if (cache[n] != 0) return cache[n];

        int best = 0;
        for (int i = 1; i <= n; i++) {
            int revenue = prices[i] + cutRodTD(n-i, prices, cache);
            best = Math.max(best, revenue);
        }
        cache[n] = best;
        return best;
    }

    /*************** BOTTOM UP SOLUTION  *****************/
    static int cutRodBU(int n, int[] prices) {
        int[] cache = new int[n+1];

        for (int j = 1; j <= n; j++) {
            int best = 0;
            for (int i = 1; i <= j; i++) {
                int revenue = prices[i] + cache[j-i];
                best = Math.max(best, revenue);
            }
            cache[j] = best;
        }

        return cache[n];
    }

    /************ BOTTOM UP - RETURN OPTIMAL CUTS **************/
    static int[] bestRodCuts(int n, int[] prices) {
        int[] cache = new int[n+1];
        int[] cuts = new int[n+1];

        for (int j = 1; j <= n; j++) {
            int best = 0;
            for (int i = 1; i <= j; i++) {
                int revenue = prices[i] + cache[j-i];
                if (revenue > best) {
                    best = revenue;
                    cuts[j] = i;
                }
            }
            cache[j] = best;
        }

        return cuts;
    }

    public static void main(String[] args) {
        int[] prices = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30, 30, 32, 32, 40, 50, 65, 80, 81, 82, 83, 85, 88, 93, 97 };

        int n = 4;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        System.out.printf("cutRod(%d) = %d\n", n, cutRodTD(n, prices));
        System.out.printf("cutRod(%d) = %d\n", n, cutRodBU(n, prices));

        int[] cuts = bestRodCuts(n, prices);

        int cost = 0;
        int len = n;
        System.out.print("Optimal cuts: ");
        while (len > 0) {
            int cut = cuts[len];
            System.out.print(cut + ", ");
            cost += prices[cut];
            len -= cut;
        }
        System.out.println();
        System.out.println("Total Cost: " + cost);
    }
}

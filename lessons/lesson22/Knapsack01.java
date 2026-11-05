
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Knapsack01 {
    public static record Item(int value, int weight) { }

    private static record State(int index, int capacity) { 

        // Returns the next state if the current item is skipped
        public State skip() {
            return new State(index+1, capacity);
        }

        // Returns the next state if the given item is kept
        public State keep(Item item) {
            return new State(index+1, capacity - item.weight);
        }
    }

    public static int maxValue(Item[] items, int capacity) {
        var cache = new HashMap<State, Integer>();
        var sol = new HashMap<State, Boolean>();
        Arrays.sort(items, (i1, i2) -> -Integer.compare(i1.weight, i2.weight));
        return maxValue(items, new State(0, capacity), cache, sol);
    }

    public static Map<State, Boolean> getSolution(Item[] items, int capacity) {
        var cache = new HashMap<State, Integer>();
        var sol = new HashMap<State, Boolean>();
        Arrays.sort(items, (i1, i2) -> -Integer.compare(i1.weight, i2.weight));
        maxValue(items, new State(0, capacity), cache, sol);
        return sol;
    }

    // Items are sorted by weight: high - low
    // State S: has current item index and remaining capacity
    // cache: map from State to max value
    // sol: map from capacity to item index to take
    private static int maxValue(Item[] items, State S, Map<State, Integer> cache, Map<State, Boolean> sol) {
        if (S.index == items.length) return 0;
        if (cache.containsKey(S)) return cache.get(S);

        // Value of skipping item
        int best = maxValue(items, S.skip(), cache, sol);

        sol.put(S, false);

        Item item = items[S.index];
        if (item.weight <= S.capacity) {
            // Value of keeping item
            int keep = item.value + maxValue(items, S.keep(item), cache, sol);
            if (keep > best) {
                best = keep;
                sol.put(S, true);
            }
        }

        cache.put(S, best);
        return best;
    }

    /*** Main Program
     * 
     * Reads 0-1 Knapsack Problem from standard in, then solves it
     * 
     * Format:
     *   int N: the number of items
     *   int W: the total weight to carry
     *   N times:
     *     int V_i, int W_i: 2 integers per item (value and weight)
     * 
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int W = scanner.nextInt();

        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            items[i] = new Item(scanner.nextInt(), scanner.nextInt());
        }
        scanner.close();

        // Get solution (mapping from current capacity -> item to choose)
        var solution = getSolution(items, W);

        int total = 0;
        State curState = new State(0, W);
        while (solution.containsKey(curState)) {
            if (solution.get(curState)) {
                Item item = items[curState.index];
                total += item.value;
                System.out.printf("Take Item %d: %s:\n", curState.index, item);
                curState = curState.keep(item);
            } else {
                curState = curState.skip();
            }
        }
        System.out.println("Total Value: " + total);

    }

}

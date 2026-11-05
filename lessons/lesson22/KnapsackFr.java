import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KnapsackFr {
    public static record Item(int value, int weight) { }

    public static record ItemAmount(Item item, double fraction) { }

    private static int densityComparator(Item item1, Item item2) {
        double d1 = (double)item1.value / item1.weight;
        double d2 = (double)item2.value / item2.weight;
        return -Double.compare(d1, d2);
    }

    public static List<ItemAmount> maxValueSelection(Item[] items, double capacity) {
        var solution = new ArrayList<ItemAmount>();
        Arrays.sort(items, KnapsackFr::densityComparator);
        
        for (int i = 0; i < items.length; i++) {
            if (items[i].weight <= capacity) {
                solution.add(new ItemAmount(items[i], 1.0));
                capacity -= items[i].weight;
            } else {
                double frac = capacity / items[i].weight;
                solution.add(new ItemAmount(items[i], frac));
                return solution;
            }
        }

        return solution;
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
        var solution = maxValueSelection(items, W);

        System.out.println("Total Weight Available: " + W);

        double total = 0;
        for (ItemAmount i : solution) {
            double value = i.item.value * i.fraction;
            System.out.printf("Taking %.1f%% of %s: %.2f\n", i.fraction*100, i.item, value);
            total += value;
        }
        System.out.printf("Total Value: %.2f\n", total);
    }
 
}

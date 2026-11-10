/***
 * File: DoublingTest.java
 * 
 * Will run a doubling test, starting at N=100, until the time exceeds 1 seconds
 *   Will test the 3 types of matrix representations
 ***/
import java.util.function.Function;

public class DoublingTest {

    // Add 10 random edges to each vertex
    public static void randomizeGraph(Graph G) {
        for (int i = 0; i < G.V(); i++) {
            for (int j = 0; j < 10; j++) {
                int v2 = (int)(Math.random() * G.V());
                G.addEdge(i, v2);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Must specify one of:");
            System.err.println("  -e (EdgeList)");
            System.err.println("  -m (AdjacencyMatrix)");
            System.err.println("  -l (AdjacencyList)");
            return;
        }

        // Choose the type of graph based on the flag
        Function<Integer, Graph> makeGraph = (V) -> new AdjacencyList(V);
        String graphType = args[0].toLowerCase();
        if (graphType.equals("-e")) {
            System.out.println("Edge List");
            makeGraph = (V) -> new EdgeList(V);

        } else if (graphType.equals("-m")) {
            System.out.println("Adjacency Matrix");
            makeGraph = (V) -> new AdjacencyMatrix(V);

        } else {
            System.out.println("Adjacency List");
            makeGraph = (V) -> new AdjacencyList(V);

        }

        System.out.printf(" %8s | %10s | %10s | %10s | %10s |\n", "N", 
                            "Create", "Add Edges", "Mark Conn.", "Total");

        int N = 100;

        while (true) {
            // Step 1: Create Graph of size N
            long time1 = System.nanoTime();
            Graph G = makeGraph.apply(N);

            // Step 2: Add 10V random edges
            long time2 = System.nanoTime();
            randomizeGraph(G);

            // Step 3: Run Mark Connected Algorithm
            long time3 = System.nanoTime();
            new MarkConnected(G, 0);

            long time4 = System.nanoTime();

            System.out.printf(" %8d | %10d | %10d | %10d | %10d |\n", N, 
                (time2-time1)/1000, (time3-time2)/1000, (time4-time3)/1000, (time4-time1)/1000);

            // If it took over 200ms , quit
            if (time4 - time1 > 200_000_000) {
                break;
            }
            N *= 2; // double N
        }
    }
}


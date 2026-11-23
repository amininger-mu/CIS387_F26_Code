import java.util.Arrays;
import java.util.LinkedList;

public class BellmanFordSP {

    private double[] distTo;         // best distance found so far from s to v
    private DirectedEdge[] edgeTo;   // parent edge to v along shortest path

    private LinkedList<Integer> queue;  // a FIFO queue used to support Bellman-Ford
    private boolean[] inQueue;          // fast lookup table for which vertices are in the queue

    private int iters = 0;              // # of times an edge was relaxed

    // A list of edges making up the negative cycle (if found)
    private Iterable<DirectedEdge> negativeCycle = null;   

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        // Path cost from source to v
        distTo = new double[G.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        // Parent edge in shortest path to v
        edgeTo = new DirectedEdge[G.V()]; 

        // A queue of vertices needing to be processed
        queue = new LinkedList<Integer>();

        // Tracks which vertices are in the queue
        inQueue = new boolean[G.V()];

        // Initialize search starting at s
        distTo[s] = 0.0;
        queue.add(s);
        inQueue[s] = true;

        // Keep relaxing vertices until the queue is empty
        while (!queue.isEmpty() && !this.hasNegativeCycle()) {
            int v = queue.poll();
            inQueue[v] = false;
            relax(G, v);
        }
    }

    // Returns true if BF found a negative cycle
    public boolean hasNegativeCycle() {
        return negativeCycle != null;
    }

    // Returns the negative cycle (if found)
    public Iterable<DirectedEdge> negativeCycle() {
        return negativeCycle;
    }

    /************************ PRIVATE METHODS **************************/

    // Relaxes a vertex in the graph
    // Look at all outgoing edges v -> w and see if any of them improve w's distance
    //   if so, update w and add it to the queue
    private void relax(EdgeWeightedDigraph G, int v) {


    }

    // Extracts the shortest paths tree and checks if a cycle exists
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);

        // Add all non-null edges to SPT
        for (DirectedEdge e : edgeTo) {
            if (e != null) {
                spt.addEdge(e);
            }
        }

        // Check for a cycle
        EdgeWeightedCycleFinder cf = new EdgeWeightedCycleFinder(spt);
        negativeCycle = cf.cycle();
    }

    /************************ TEST PROGRAM **************************/
    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
        G.addEdge(new DirectedEdge(0, 2, 26));
        G.addEdge(new DirectedEdge(0, 4, 38));
        G.addEdge(new DirectedEdge(4, 7, -37));
        G.addEdge(new DirectedEdge(7, 5, 28));
        G.addEdge(new DirectedEdge(5, 1, 32));
        G.addEdge(new DirectedEdge(1, 3, -29));
        G.addEdge(new DirectedEdge(3, 6, 52));
        G.addEdge(new DirectedEdge(2, 7, 34));
        G.addEdge(new DirectedEdge(4, 5, 35));
        G.addEdge(new DirectedEdge(5, 4, 35));
        G.addEdge(new DirectedEdge(5, 7, 28));
        G.addEdge(new DirectedEdge(7, 3, 39));
        G.addEdge(new DirectedEdge(6, 2, 40));
        G.addEdge(new DirectedEdge(6, 0, 58));
        G.addEdge(new DirectedEdge(6, 4, 93));

        BellmanFordSP bf = new BellmanFordSP(G, 0);
		for (int i = 0; i < G.V(); i++) {
			System.out.printf("%d: Cost %.2f, Parent %s\n", i, bf.distTo[i], bf.edgeTo[i]);
		}

    }
}


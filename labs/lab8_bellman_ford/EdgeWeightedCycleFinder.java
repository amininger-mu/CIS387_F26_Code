import java.util.LinkedList;

public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack; // vertices on recursive call stack
    private LinkedList<DirectedEdge> cycle;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        marked = new boolean[G.V()];

        for (int v : G.vertices()) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }
 
    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();

            // already saw cycle, quit early
            if (this.hasCycle()) return;

            // if we have not yet seen w, continue search
            if (!marked[w]) {
                edgeTo[w] = e; 
                dfs(G, w);
            }
            // if w is marked and on the stack, cycle!
            else if (onStack[w]) {
                setCycle(e);
            }
        }
        onStack[v] = false;
    }

    // Given a cycle was detected when adding v -> w,
    //    reconstructs this cycle by backtracking
    //    from v until we get to w
    private void setCycle(DirectedEdge e) {
        cycle = new LinkedList<DirectedEdge>();
        int w = e.to();
        while (e.from() != w) {
            cycle.push(e);
            e = edgeTo[e.from()];
        }
        cycle.push(e);
    }
}

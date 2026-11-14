import java.util.LinkedList;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack; // vertices on recursive call stack
    private LinkedList<Integer> cycle;

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
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

    public Iterable<Integer> cycle() {
        return cycle;
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            // already saw cycle, quit early
            if (this.hasCycle()) return;

            // if we have not yet seen w, continue search
            if (!marked[w]) {
                edgeTo[w] = v; 
                dfs(G, w);
            }
            // if w is marked and on the stack, cycle!
            else if (onStack[w]) {
                setCycle(v, w);
            }
        }
        onStack[v] = false;
    }

    // Given a cycle was detected when adding v -> w,
    //    reconstructs this cycle by backtracking
    //    from v until we get to w
    private void setCycle(int v, int w) {
        cycle = new LinkedList<Integer>();
        int x = v;
        while (x != w) {
            cycle.push(x);
            x = edgeTo[x];
        }
        cycle.push(w);
        cycle.push(v);
    }

}
/***********************************************
 * class: MarkConnected
 * 
 * Based on the DepthFirstSearch example p. 531
 * 
 * Given a graph and a start vertex,
 *   marks all the vertices reachable from that vertex
 *   using depth-first search
 * 
 * API:
 * - MarkConnected(Graph, int s)
 * - int count(): number of reachable vertices
 * - boolean marked(int v): true if v is connected to s
 * 
 **********************************************/
import java.util.LinkedList;

public class MarkConnected {
    private boolean[] marked;
    private int count;

    // Given graph G, marks all vertices connected to s
    public MarkConnected(Graph G, int s) {
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    // Returns true if v is connected to s
    public boolean marked(int v) { 
        return marked[v]; 
    }

    // Returns the number of vertices connected to s (including s)
    public int count() {
        return count;
    }

    // Recursively marks reachable vertices using depth first search
    private void dfs(Graph G, int s) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (marked[v]) {
                continue;
            }
            marked[v] = true;
            count++;

            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    stack.push(w);
                }
            }
        }
    }
}

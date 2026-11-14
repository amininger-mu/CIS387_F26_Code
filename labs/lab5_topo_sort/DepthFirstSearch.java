/****************************************
 * class DepthFirstSearch
 * 
 * A class that performs the DFS algorithm on a graph
 *   given a source vertex
 * 
 * Marks all vertices reachable from that source
 *   and allows you to reconstruct the path
 * 
 ******************************************/
import java.util.List;
import java.util.ArrayList;

public class DepthFirstSearch {
	protected final boolean[] marked;   // the marked state of each vertex (visited)
	protected final ArrayList<Integer> pre;
	protected final ArrayList<Integer> post;

	// Single Source DFS 
	public DepthFirstSearch(Graph G, int source) {
		pre = new ArrayList<Integer>();
		post = new ArrayList<Integer>();
		marked = new boolean[G.V()];
		dfs(G, source);
	}

	// Returns the different orderings during DFS search
	public Iterable<Integer> preorder() {
		return pre;
	}
	public Iterable<Integer> postorder() {
		return post;
	}
	public Iterable<Integer> reversePostorder() {
		return post.reversed();
	}

	/****************** YOUR TASK ********************
	 * Modify this dfs function so it
	 * 1. adds v to the preorder list at the beginning
	 * 2. adds v to the postorder list at the end
	************************************************/
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}
}

/****************************************
 * interface PathSearch
 * 
 * An interface for algorithms that search a graph
 * for a path from a start vertex to a goal vertex
 * 
 ******************************************/
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class PathSearch {
	protected final Graph G;
	protected final boolean[] marked;   // the marked state of each vertex (visited)
	protected final int[] edgeTo;       // the parent of each vertex from the search

	public PathSearch(Graph G) {
		this.G = G;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
	}

	/*** Returns the marked state of each vertex (whether it was visited) **/
	public boolean[] wasVisited() {
		return marked;
	}

	/*** Returns true if vertex v was visited during the search ***/
	public boolean wasVisited(int v) {
		return marked[v];
	}

    /*** Returns the parent of v in the search (or -1 if none)***/
    public int getParent(int v) {
        return edgeTo[v];
    }

	/*** Searches for a path from the start vertex to the goal vertex
	 *   Returns true if the search succeeded
	 ***/ 
	public abstract boolean search(int start, int goal);

	/*** Returns a path of vertices from the start to the given v
	 *      by following back the trail of edgeTo pointers
	 *   (Assumes that v was visited during the search)
	 ***/
	public List<Integer> getPath(int v) {
		var path = new ArrayList<Integer>();
		while (v != -1) {
			path.add(v);
			v = edgeTo[v];
		}
		Collections.reverse(path);
		return path;
	}
}

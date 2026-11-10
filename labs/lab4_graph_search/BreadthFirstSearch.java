/*************************************************
 * class BreadthFirstSearch
 * extends PathSearch
 *
 * Performs Breadth First Search on the given graph
 *   to find a path between two vertices
 *
 * Implements the method:
 * boolean search(int start, int goal)
 *
 * This search updates the two arrays in the PathSearch class:
 *   boolean[] marked - marked[i] is true if vertex i was visited
 *   int[] edgeTo - edgeTo[i] gives the parent of vertex i (if visited)
 * returns true if a path was found
 *
 * ***********************************************/
import java.util.Arrays;
import java.util.LinkedList;

public class BreadthFirstSearch extends PathSearch {
	public BreadthFirstSearch(Graph G) {
		super(G);
	}

	/*** Searches for a path from the start vertex to the goal vertex
	 *      using breadth first search
	 *   Returns true if the search succeeded
	 ***/
	@Override
	public boolean search(int start, int goal) {
		// Set all marked flags to false
		Arrays.fill(marked, false);
		// Set all parent edges to -1 
		Arrays.fill(edgeTo, -1);

		// Implement BFS here using a queue

		return false;
	}
}

/******************************
 * interface: Graph
 * 
 * Defines three methods for interacting with graphs:
 * - Assumes that vertices are numbered 0 to (V-1)
 * 
 * 1. int V()                     : Number of vertices
 * 2. addEdge(v, w)               : Adds a given edge
 * 3. List<Integer> adj(int v)    : Gets all vertices adjacent to v
 ******************************/
import java.util.List;

public interface Graph {

    // Return number of vertices
    int V();

    // Add an edge from vertices v-w
    void addEdge(int v, int w);

    // Returns a list of vertices adjacent to v
    List<Integer> adj(int v);
}

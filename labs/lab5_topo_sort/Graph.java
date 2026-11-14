/******************************
 * interface: Graph
 * 
 * Defines five methods for interacting with graphs:
 * - Assumes that vertices are numbered 0 to (V-1)
 * 
 * 1. int V()                     : Number of vertices
 * 2. int E()                     : Number of edges
 * 3. addEdge(v, w)               : Adds a given edge
 * 4. Iterable<Integer> vertices()  : Iterate over all vertices in G
 * 5. Iterable<Integer> adj(int v)  : Iterate over all vertices adjacent to v
 ******************************/
import java.util.List;

public interface Graph {

    // Return number of vertices
    int V();

    // Return number of edges
    int E();

    // Add an edge from vertices v-w
    void addEdge(int v, int w);

    // Iterates over all vertices in G
    Iterable<Integer> vertices();

    // Returns a list of vertices adjacent to v
    Iterable<Integer> adj(int v);
}

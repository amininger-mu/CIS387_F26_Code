/****************************************************
 * EdgeList
 * 
 * A graph implementation stores a list of edges
 * (each edge is an object with two vertices)
 ***************************************************/

import java.util.List;
import java.util.ArrayList;

public class EdgeList implements Graph {

    // An Edge object connecting vertices v1 and v2
    private static record Edge(int v1, int v2) { } 

    private ArrayList<Edge> edges; // The list of edges
    private int V;                 // The number of vertices

    public EdgeList(int V) {

    }

    // Return number of vertices
    public int V() {
        return 0;
    }

    // Add an edge from vertices v-w
    public void addEdge(int v, int w) {

    }

    // Returns a list of vertices adjacent to v
    public List<Integer> adj(int v) {

        return null;
    }
}

/***********************************
 * AdjecencyListDigraph
 * 
 * A graph implementation that stores an array of lists
 * - List i lists all the vertices adjacent to vertex i
 *********************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class AdjacencyListDigraph implements Digraph {

    private List<List<Integer>> vertices;

    private int E;

    public AdjacencyListDigraph(int V) {
        E = 0;
        vertices = new ArrayList<List<Integer>>();
        for (int i = 0; i < V; i++) {
            vertices.add(new ArrayList<Integer>());
        }
    }

    // Returns number of vertices in graph
    public int V() {
        return vertices.size();
    }

    // Returns number of edges in graph
    public int E() {
        return vertices.size();
    }

    // Adds a directed edge from v -> w
    public void addEdge(int v, int w) {
        vertices.get(v).add(w);
        E++;
    }
    
    // Returns an iterable range from 0 to V-1
    public Iterable<Integer> vertices() {
        return () -> IntStream.range(0, vertices.size()).iterator();
    }

    // Returns vertices adjacent to v (edge from v -> w)
    public Iterable<Integer> adj(int v) {
        return vertices.get(v);
    }

    // Returns a new Digraph that is a copy of this one with all edges reversed
    public Digraph reverse() {
        Digraph G2 = new AdjacencyListDigraph(vertices.size());
        for (int v = 0; v < vertices.size(); v++) {
            for (int w : adj(v)) {
                G2.addEdge(w, v);
            }
        }
        return G2;
    }
}

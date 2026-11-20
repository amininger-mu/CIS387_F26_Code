/******************************
 * interface: EdgeWeightedDigraph
 * 
 * A class that represents an edge-weighted directed graph
 * 
 * Uses an DirectedEdge record that holds the two connected vertices and the weight
 * 
 ******************************/
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.lang.IllegalArgumentException;

public class EdgeWeightedDigraph {
    private int E;                     // number of edges
    private List<List<DirectedEdge>> adjList;  // adjacency list

    public EdgeWeightedDigraph(int V) {
        E = 0;
        adjList = new ArrayList<List<DirectedEdge>>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<DirectedEdge>());
        }
    }

    // Returns number of vertices in graph
    public int V() {
        return adjList.size();
    }

    // Returns number of edges in graph
    public int E() {
        return E;
    }

    // Adds a directed edge to the graph 
    public void addEdge(DirectedEdge e) {
        adjList.get(e.from()).add(e);
        E++;
    }
    
    // Returns an iterable range from 0 to V-1
    public Iterable<Integer> vertices() {
        return () -> IntStream.range(0, adjList.size()).iterator();
    }

    // Returns an iterable view of all the edges in the graph
    public Iterable<DirectedEdge> edges() {
        var edgeList = new ArrayList<DirectedEdge>();
        for (int v : vertices()) {
            for (DirectedEdge e : adjList.get(v)) {
				edgeList.add(e);
            }
        }
        return edgeList;
    }

    // Returns edges incident to v (edge containing v)
    public Iterable<DirectedEdge> adj(int v) {
        return adjList.get(v);
    }
}

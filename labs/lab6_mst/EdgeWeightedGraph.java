/******************************
 * interface: EdgeWeightedGraph
 * 
 * A class that represents an edge-weighted undirected graph
 * 
 * Uses an Edge record that holds the two connected vertices and the weight
 * 
 ******************************/
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.lang.IllegalArgumentException;

public class EdgeWeightedGraph {
    private int E;                 // number of edges
    private List<List<Edge>> adjList;  // adjacency list

    public EdgeWeightedGraph(int V) {
        E = 0;
        adjList = new ArrayList<List<Edge>>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<Edge>());
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

    // Adds an undirected edge to the graph 
    public void addEdge(Edge e) {
        adjList.get(e.v1()).add(e);
        adjList.get(e.v2()).add(e);
        E++;
    }
    
    // Returns an iterable range from 0 to V-1
    public Iterable<Integer> vertices() {
        return () -> IntStream.range(0, adjList.size()).iterator();
    }

    // Returns an iterable view of all the edges in the graph
    public Iterable<Edge> edges() {
        var edgeList = new ArrayList<Edge>();
        for (int v : vertices()) {
            for (Edge e : adjList.get(v)) {
                if (v == e.v1() && v < e.v2()) { // only add each edge once
                    edgeList.add(e);
                }
            }
        }
        return edgeList;
    }

    // Returns edges incident to v (edge containing v)
    public Iterable<Edge> adj(int v) {
        return adjList.get(v);
    }
}

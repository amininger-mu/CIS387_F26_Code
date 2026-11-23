/*************************************************************
 * SymbolEWDigraph
 * 
 * A class that can read a csv file containing weighted directed edge information
 *   with arbitrary names for the nodes
 * and create an EdgeWeightedDigraph from it
 * 
 * It then stores a mapping from name -> vertex id and vertex id -> name
 * 
 * File Format: A CSV file where each line is:
 * FROM,TO,WEIGHT
 ******************************************************************/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SymbolEWDigraph {
    private HashMap<String, Integer> vertices; // String -> index
    private String[] keys; // index -> String
    private EdgeWeightedDigraph G; // the graph

    public SymbolEWDigraph(File file) throws IOException {
        vertices = new HashMap<String, Integer>();

        readCSVFile(file);
    }

    // Returns the created Edge-Weighted Digraph
    public EdgeWeightedDigraph digraph() { 
        return G; 
    }

    // Returns true if a vertex with the given name exists
    public boolean contains(String name) { 
        return vertices.containsKey(name); 
    }

    // Returns the vertex id for the given name
    public int getVertex(String name) { 
        return vertices.get(name); 
    }

    // Returns the name of the given vertex
    public String getName(int v) { 
        return keys[v]; 
    }


    /******************** PRIVATE METHODS ********************/

    // Read the CSV file containing the weighted directed edges
    //    and use it to create the graph
    // Each line is "FROM,TO,WEIGHT"
    private void readCSVFile(File file) throws IOException {
        var edges = new ArrayList<DirectedEdge>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // Each line is 3 CSVof the form: FROM,TO,WEIGHT
                String[] parts = scanner.nextLine().split(",");
                if (parts.length < 3) continue;

                // parts[0] = From vertex name
                String from = parts[0];
                if (!vertices.containsKey(from)) {
                    vertices.put(from, vertices.size());
                }
                int fromV = vertices.get(from);

                // parts[1] = To vertex name
                String to = parts[1];
                if (!vertices.containsKey(to)) {
                    vertices.put(to, vertices.size());
                }
                int toV = vertices.get(to);

                // parts[2] = edge weight
                double weight = Double.parseDouble(parts[2]);

                // Add the edge to the list
                edges.add(new DirectedEdge(fromV, toV, weight));
            }
        }

        // Build the Edge-Weighted Digraph and add all the edges
        G = new EdgeWeightedDigraph(vertices.size());
        for (DirectedEdge e : edges) {
            // System.out.println(e);
            G.addEdge(e);
        }

        // Build the reverse key lookup
        keys = new String[vertices.size()]; 
        for (var e : vertices.entrySet()) {
            // System.out.println(e.getKey() + "=" + e.getValue());
            keys[e.getValue()] = e.getKey(); 
        }
    }
}


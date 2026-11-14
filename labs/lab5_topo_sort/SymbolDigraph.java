import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SymbolDigraph {
    private HashMap<String, Integer> vertices; // String -> index
    private String[] keys; // index -> String
    private Digraph G; // the graph

    public SymbolDigraph(File file, String separator) throws IOException {
        vertices = new HashMap<String, Integer>();

        // Read the file to initialize the vertices map
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(separator);
            for (String s : parts) {
                if (!vertices.containsKey(s)) {
                    vertices.put(s, vertices.size());
                }
            }
        }
        scanner.close();

        // Create the graph
        G = new AdjacencyListDigraph(vertices.size());

        // Re-Read the file, adding edges this time
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(separator);
            int v1 = vertices.get(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                G.addEdge(v1, vertices.get(parts[i]));
            }
        }
        scanner.close();

        // Build the reverse key lookup
        keys = new String[vertices.size()]; 
        for (var e : vertices.entrySet()) {
            keys[e.getValue()] = e.getKey(); 
        }
    }

    public boolean contains(String s) { 
        return vertices.containsKey(s); 
    }

    public int indexOf(String s) { 
        return vertices.get(s); 
    }

    public String nameOf(int v) { 
        return keys[v]; 
    }

    public Digraph digraph() { 
        return G; 
    }
}


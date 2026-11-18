import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SymbolWeightedGraph {
    private HashMap<String, Integer> vertices; // String -> index
    private String[] keys; // index -> String
    private EdgeWeightedGraph G; // the graph

    public SymbolWeightedGraph(File file, String separator) throws IOException {
        vertices = new HashMap<String, Integer>();

        // Read the file to initialize the vertices map
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(separator);
            if (parts.length < 3) continue;
            if (!vertices.containsKey(parts[0])) 
                vertices.put(parts[0], vertices.size());
            if (!vertices.containsKey(parts[1])) 
                vertices.put(parts[1], vertices.size());
        }
        scanner.close();

        // Create the graph
        G = new EdgeWeightedGraph(vertices.size());

        // Re-Read the file, adding edges this time
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(separator);
            if (parts.length < 3) continue;
            int v1 = vertices.get(parts[0]);
            int v2 = vertices.get(parts[1]);
            double weight = Double.parseDouble(parts[2]);
            G.addEdge(v1, v2, weight);
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

    public EdgeWeightedGraph graph() { 
        return G; 
    }
}


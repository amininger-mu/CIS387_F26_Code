public record Edge(int v1, int v2, double weight) { 

    // Given 1 vertex in the edge, returns the other
    public int other(int v) {
        if (v == v1) return v2;
        if (v == v2) return v1;
        throw new IllegalArgumentException(String.format("Vertex %d not part of edge %s", v, this));
    }

    // Compare two edges based on weights
    public static int compareWeights(Edge e1, Edge e2) {
        return Double.compare(e1.weight(), e2.weight());
    }
};

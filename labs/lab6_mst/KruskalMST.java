import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashSet;

public class KruskalMST {
    private LinkedList<Edge> mstEdges;
    private UnionFind UF;

    public KruskalMST(EdgeWeightedGraph G, double res, int minSize) {
        mstEdges = new LinkedList<Edge>();
        UF = new UnionFind(G.V());
        var queue = new PriorityQueue<Edge>(KruskalMST::compareWeights);

        for (Edge e : G.edges()) {
            queue.add(e);
        }

        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int id1 = UF.find(e.v1());
            int id2 = UF.find(e.v2());
            if (id1 == id2) continue;

            UF.union(e.v1(), e.v2());
            mstEdges.add(e);
        }
    }

    // Compare two edges based on weights
    private static int compareWeights(Edge e1, Edge e2) {
        return Double.compare(e1.weight(), e2.weight());
    }

    // Returns an iterable view of the edges in the MST
    public Iterable<Edge> edges() {
        return mstEdges;
    }

    //  Returns the segmentation information (as a UnionFind structure)
    public UnionFind segmentation() {
        return UF;
    }
}


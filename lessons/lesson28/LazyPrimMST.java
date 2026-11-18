import java.util.PriorityQueue;
import java.util.LinkedList;

public class LazyPrimMST implements MST {
    private boolean[] marked;
    private LinkedList<Edge> mst;
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<Edge>(Edge::compareWeights);
        marked = new boolean[G.V()];
        mst = new LinkedList<Edge>();

        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (marked[e.v1()] && marked[e.v2()]) continue;

            mst.add(e);
            if (!marked[e.v1()]) visit(G, e.v1());
            if (!marked[e.v2()]) visit(G, e.v2());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            if (!marked[e.other(v)])
                pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return mst.stream().mapToDouble(e -> e.weight()).sum();
    }
}

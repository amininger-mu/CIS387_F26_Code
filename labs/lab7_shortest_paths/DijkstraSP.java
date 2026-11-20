import java.lang.constant.DirectMethodHandleDesc;
import java.security.spec.EdECPoint;
import java.util.Arrays;
import java.util.LinkedList;


class DijkstraSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;

	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		pq  = new IndexMinPQ<Double>(G.V());

		// Finish Algorithm Here

	}

	// Relax each outgoing edge from v
	private void relax(EdgeWeightedDigraph G, int v) {

	}


	// Returns true if there is a path from source to v
	public boolean hasPathTo(int v) {
		return edgeTo[v] != null;
	}

	// Returns shortest distance from source to v (+inf if no path)
	public double distTo(int v) {
		return distTo[v];
	}

	// Returns parent edge on shortest path to v (null if no path)
	public DirectedEdge edgeTo(int v) {
		return edgeTo[v];
	}

	// Returns an interable set of edges on the shortest path to v
	public Iterable<DirectedEdge> pathTo(int v) {
		var path = new LinkedList<DirectedEdge>();
		for (var e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.addFirst(e);
		}
		return path;
	}

	public static void main(String[] args) {
		// Runs a little test program, following example from class
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
		G.addEdge(new DirectedEdge(0, 2, 0.26));
		G.addEdge(new DirectedEdge(0, 4, 0.38));
		G.addEdge(new DirectedEdge(2, 7, 0.34));
		G.addEdge(new DirectedEdge(4, 5, 0.35));
		G.addEdge(new DirectedEdge(7, 3, 0.39));
		G.addEdge(new DirectedEdge(5, 1, 0.32));
		G.addEdge(new DirectedEdge(3, 6, 0.52));
		G.addEdge(new DirectedEdge(4, 7, 0.37));
		G.addEdge(new DirectedEdge(7, 5, 0.28));
		G.addEdge(new DirectedEdge(5, 4, 0.35));
		G.addEdge(new DirectedEdge(5, 7, 0.28));
		G.addEdge(new DirectedEdge(1, 3, 0.29));
		G.addEdge(new DirectedEdge(6, 2, 0.40));
		G.addEdge(new DirectedEdge(6, 0, 0.58));
		G.addEdge(new DirectedEdge(6, 4, 0.93));

		var SP = new DijkstraSP(G, 0);
		for (int i = 0; i < G.V(); i++) {
			System.out.printf("%d: Cost %.2f, Parent %s\n", i, SP.distTo(i), SP.edgeTo(i));
		}

	}
}

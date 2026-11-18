import java.io.IOException;
import java.io.File;
import java.util.PriorityQueue;

public class FiberNetwork {
    
    public static void main(String[] args) throws IOException {

        var SG = new SymbolWeightedGraph(new File("pa_fiber.txt"), ",");
        var G = SG.graph();

        // MST mst = new KruskalMST(G);
        MST mst = new LazyPrimMST(G);

        for (Edge e : mst.edges()) {
            String v1 = SG.nameOf(e.v1());
            String v2 = SG.nameOf(e.v2());
            System.out.printf("Edge: %s -- %s (%.1f)\n", v1, v2, e.weight());
        }
        System.out.printf("Total cost: %.2f\n", mst.weight());
    }
}

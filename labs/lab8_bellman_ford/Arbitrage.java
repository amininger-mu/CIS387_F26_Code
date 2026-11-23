/************************************************************
 * class: Arbitrage
 * usage: java Arbitrage <csv_file>
 *
 * Given a csv file containing various currency exchange rates,
 *   looks for an arbitrage opportunity
 * (a sequence of trades resulting in net profit)
 *
 * It does this by running Bellman-Ford looking for a negative cycle
 *
 * STUDENT NAME: 
 *
 ************************************************************/
import java.io.File;
import java.io.IOException;

public class Arbitrage {
    
    public static void main(String[] args) throws IOException {

		// Reads the given file and creates a graph from it
        File file = new File(args[0]);
        SymbolEWDigraph SG = new SymbolEWDigraph(file);
        EdgeWeightedDigraph G = SG.digraph();

		// 1. Create a new arbitrage graph
		
		// 2. Add edges to the graph

		// 3. Run Bellman-Ford and check for negative cycle

		// 4. If no negative cycle, print a message

		// 5. If there is a negative cycle, print out a profitable equence of trades

    }
}

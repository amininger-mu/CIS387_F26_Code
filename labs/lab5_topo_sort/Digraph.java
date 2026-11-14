/******************************
 * interface: Digraph
 * extends: Graph
 * 
 * Defines an additional method for interacting with directed graphs:
 * - Digraph reverse()       : Returns an equivalent digraph with all edges flipped
 ******************************/
import java.util.List;

public interface Digraph extends Graph{

    // Returns a digraph with all edges reversed
    Digraph reverse();
}

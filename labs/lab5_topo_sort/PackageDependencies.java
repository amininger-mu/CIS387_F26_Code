import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;


public class PackageDependencies {
    /***************************************************
     * Program: java PackageDependencies <package>
     * 
     * Prints out an install order for the given package
     *   so that all dependencies are respected
     ***************************************************/
    public static void main(String[] args) throws IOException {


    }

    // Writes the given topological order to a dot file
    private static void exportDotFile(SymbolDigraph sg, DepthFirstSearch dfs) {
        Digraph G = sg.digraph();
        try (PrintWriter writer = new PrintWriter(new File("packages.dot"))) {
            writer.println("digraph G {");
            for (int v : dfs.reversePostorder()) {
                writer.printf("  %d [label=\"%s\"];\n", v, sg.nameOf(v));
                for (int w : G.adj(v)) {
                    writer.printf("  %d -> %d;\n", v, w);
                }
            }
            writer.println("}");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

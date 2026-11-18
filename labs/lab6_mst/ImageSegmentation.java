import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

public class ImageSegmentation {

    // Distance between two colors
    private static double dist(int color1, int color2) {
        int d0 = ((color1 >> 16) & 0xFF) - ((color2 >> 16) & 0xFF);
        int d1 = ((color1 >> 8) & 0xFF) - ((color2 >> 8) & 0xFF);
        int d2 = (color1 & 0xFF) - (color2 & 0xFF);
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    // Creates a weighted edge between the two given pixels in the image
    private static Edge createEdge(BufferedImage img, int row1, int col1, int row2, int col2) {
        int v1 = img.getWidth()*row1 + col1;
        int v2 = img.getWidth()*row2 + col2;
        int color1 = img.getRGB(col1, row1);
        int color2 = img.getRGB(col2, row2);
        double d = dist(color1, color2);
        return new Edge(v1, v2, d);
    }

    // Returns a weighted graph representation of the given image
    // Each pixel is connected to its 8 neighbors
    //    the weight is the distance between the colors
    public static EdgeWeightedGraph constructImageGraph(BufferedImage img) {
        final int width = img.getWidth();
        final int height = img.getHeight();

        var G = new EdgeWeightedGraph(width*height);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col > 0 && row < height-1) {
                    G.addEdge(createEdge(img, row, col, row+1, col-1));
                }
                if (row < height-1) {
                    G.addEdge(createEdge(img, row, col, row+1, col));
                }
                if (col < width-1 && row < height-1) {
                    G.addEdge(createEdge(img, row, col, row+1, col+1));
                }
                if (col < width-1) {
                    G.addEdge(createEdge(img, row, col, row, col+1));
                }
            }
        }
        return G;
    }

    /** Paints each segment a random color. */
    public static BufferedImage randomColors(int width, int height, UnionFind UF) {
        Random rnd = new Random(123);

        // Mapping from component id to random color
        var colorMap = new HashMap<Integer, Integer>();

        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int componentId = UF.find(row*width + col);
                if (!colorMap.containsKey(componentId)) {
                    colorMap.put(componentId, rnd.nextInt(0xFFFFFF));
                }
                out.setRGB(col, row, colorMap.get(componentId));
            }
        }

        return out;
    }

    /****************************************************
     * main program
     * java ImageSegmentation in.png out.png [res] [minSize]
     * 
     * Tuned by 2 parameters:
     * - res is the resolution, lower = more merging, higher = more pieces
     * - minSize is the minimum size for a component (smaller ones will be merged)
     *****************************************************/
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java ImageSegmentation in.png out.png [res] [minSize]");
            System.exit(1);
        }

        BufferedImage img = ImageIO.read(new File(args[0]));
        if (img == null) throw new IOException("Could not read image: " + args[0]);

        double res = args.length > 2 ? Double.parseDouble(args[2]) : 5.0;
        int minSize = args.length > 3 ? Integer.parseInt(args[3]) : 10;

        /****** Steps of Segmentation Algorithm ******/
        EdgeWeightedGraph G = constructImageGraph(img);
        KruskalMST mst = new KruskalMST(G, res, minSize);
        UnionFind UF = mst.segmentation();
        BufferedImage out = randomColors(img.getWidth(), img.getHeight(), UF);

        String fmt = args[1].toLowerCase().endsWith(".jpg") ? "jpg" : "png";
        ImageIO.write(out, fmt, new File(args[1]));
    }
}

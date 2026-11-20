import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.core.model.iface.*;
import de.topobyte.osm4j.core.model.util.OsmModelUtil;
import de.topobyte.osm4j.pbf.seq.PbfIterator;
import java.io.FileInputStream;
import java.security.spec.EdECPoint;
import java.io.File;
import java.util.*;

public class Navigator {
    // A Map from OSM ID -> OsmNode
    private HashMap<Long, OsmNode> nodeMap;

    // A Map from OSM ID -> vertex id
    private HashMap<Long, Integer> vertexMap;

    // A lookup table for vertex id -> OsmNode
    private ArrayList<OsmNode> vertexNodes;

    // A list of all the 'OsmWays' in the dataset
    private ArrayList<OsmWay> ways;

    // The extracted weighted-edge digraph structure
    private EdgeWeightedDigraph G;

    public Navigator(File mapFile) throws Exception {
        nodeMap = new HashMap<Long, OsmNode>();
        vertexMap = new HashMap<Long, Integer>();
        vertexNodes = new ArrayList<OsmNode>();
        ways = new ArrayList<OsmWay>();

        // Step 1: Read the data and get the nodes/ways
        try (FileInputStream in = new FileInputStream(mapFile)) {
            for (EntityContainer c : new PbfIterator(in, false)) {
                if (c.getType() == EntityType.Node) {
                    OsmNode osmNode = (OsmNode) c.getEntity();
                    nodeMap.put(osmNode.getId(), osmNode);
                } else if (c.getType() == EntityType.Way) {
                    ways.add((OsmWay)c.getEntity());
                }
            }
        }

        G = constructGraph();
    }

    // Returns the created edge-weighted digraph with the map data
    public EdgeWeightedDigraph digraph() {
        return G;
    }

    // Returns the vertex index for the given node
    public int vertexOf(OsmNode node) {
        return vertexMap.get(node.getId());
    }

    // Returns the node associated with a given vertex
    public OsmNode nodeOf(int vertex) {
        return vertexNodes.get(vertex);
    }

	// Constructs a graph from the extracted OsmNode and OsmWay data
    private EdgeWeightedDigraph constructGraph() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(nodeMap.size());

		// Creates the correspondence between OsmNodes and vertices
        for (OsmNode node : nodeMap.values()) {
            vertexMap.put(node.getId(), vertexNodes.size());
            vertexNodes.add(node);
        }

		// Adds all edges to the graph
        for (OsmWay way : ways) {
            addWay(G, way);
        }

        return G;
    }


	// Adds the given OsmWay to the graph:
	//   Takes each pair of adjacent nodes and adds an edge between them
	//   If it is not one-way, adds back edges as well
	//   Ignores ways that are not highways
    private void addWay(EdgeWeightedDigraph G, OsmWay way) {

		// A map of key/value tags on the OsmWay
        Map<String, String> tags = OsmModelUtil.getTagsAsMap(way);

		// Add the edges to the graph

    }


    /**************************** GET CLOSEST **********************************
     * Given a latitude and longitude,
     * Find the closest node that is on the road network 
     **************************************************************************/
    private OsmNode getClosest(double lat, double lon) {
        double cosLat = Math.cos(Math.toRadians(lat));
        OsmNode closest = null;
        double closestDist = Double.MAX_VALUE;
        for (OsmNode node : nodeMap.values()) {
            int v = vertexMap.get(node.getId());
            var edges = G.adj(v);
            if (!edges.iterator().hasNext()) continue; // If no outgoing edges, don't consider it

            double dist = distance(node.getLatitude(), node.getLongitude(), lat, lon);
            if (dist < closestDist) {
                closest = node;
                closestDist = dist;
            }
        }
        return closest;
    }


    /************************* DISTANCE CALCULATION ****************************
     * These two methods help get the distance between two nodes
     * by using the haversine calculation to get distance on a sphere (earth)
     **************************************************************************/

    private static final double EARTH_RADIUS_M = 6_371_008.8; // mean Earth radius

    /** Great-circle distance in meters between two OSM nodes. */
    public static double distance(OsmNode a, OsmNode b) {
        return distance(a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude());
    }

    /** Great-circle distance in meters between two lat/lon points (degrees). */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dPhi = Math.toRadians(lat2 - lat1);
        double dLambda = Math.toRadians(lon2 - lon1);

        double h = Math.sin(dPhi / 2) * Math.sin(dPhi / 2)
                 + Math.cos(phi1) * Math.cos(phi2)
                 * Math.sin(dLambda / 2) * Math.sin(dLambda / 2);

        return 2 * EARTH_RADIUS_M * Math.asin(Math.min(1.0, Math.sqrt(h)));
    }

    /************************* PATH TO GEO JSON ****************************
     * Given a path found during the search,
     * converts it into a json String representation
     * that can be visualized at https://geojson.io
     **************************************************************************/

    public String pathToGeoJson(Iterable<DirectedEdge> path) {

        StringBuilder sb = new StringBuilder("{\"type\":\"Feature\",\"properties\":{}," + 
            "\"geometry\":{\"type\":\"LineString\",\"coordinates\":[");

        boolean first = true;
        for (var edge : path) {
            OsmNode n1 = vertexNodes.get(edge.from());
            OsmNode n2 = vertexNodes.get(edge.to());
            if (first) {
                // Need to specify [lon,lat]
                sb.append(String.format("[%.6f, %.6f]", n1.getLongitude(), n1.getLatitude()));
                first=false;
            }
            sb.append(String.format(",[%.6f,%.6f]", n2.getLongitude(), n2.getLatitude()));
        }
        return sb.append("]}}").toString();
    }

    public static void main(String[] args) throws Exception{
		// Read the map data and create the graph
        File file = new File(args[0]);
        Navigator nav = new Navigator(file);

		// Get the start and end nodes by lat/long coordinates
		// (You can modify these yourself)
        OsmNode start = nav.getClosest(40.157273,-76.990572); // Messiah
        OsmNode end = nav.getClosest(40.178833, -76.989190); // Chik-fil-a

		// Get the vertices corresponding to the nodes and do a search
        int startV = nav.vertexOf(start);
        int endV = nav.vertexOf(end);

        DijkstraSP search = new DijkstraSP(nav.digraph(), startV);

        if (!search.hasPathTo(endV)) {
            System.out.println("No Path Found");
        } else {
            Iterable<DirectedEdge> path = search.pathTo(endV);

            // Print the total path length in meters
            double total = search.distTo(endV);
            System.out.printf("Path Found, Length = %.1f meters\n", total);
            System.out.println();

            // Print out the path information as a json string
            System.out.println("Path in JSON Format: (Visualize using https://geojson.io)");
            System.out.println();

            String json = nav.pathToGeoJson(path);
            System.out.println(json);
        }
    }
}

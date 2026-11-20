# Lab 7 - Shortest Path

**Course:** CIS 387 - Analysis of Algorithms - Fall 2026 \
**Instructor:** Dr. Aaron Mininger \

## Submission Instructions

**Due Date:** Nov 23 at 11:59am (beginning of class!)

**To Submit:** Upload your completed `Navigator.java` file, plus the json file described. 

Since this is a lab assignment, you are allowed to work with other people. However, you must type up and submit your own individual work.
You may consult other resources, including AI, to write the code, but try to work through things yourself first.

## Setup

All the code and files needed for this homework are available on the course git repository: [`https://github.com/amininger-mu/CIS387_F26_Code`](https://github.com/amininger-mu/CIS387_F26_Code)

In the `labs/lab7_shortest_paths` folder, you will see provided supporting code.

<br>

---

## Lab Overview

One everyday use case for shortest path algorithms is getting driving directions on a road map. Although the complexity of representing real world maps is very high, and many factors go into determining edge weights, the core of the path solving algorithm is likely a relatively simple adaptation of an algorithm like Dijkstra's. 

In this lab, we will use Dijkstra's algorithm to do shortest path route planning on real world maps.

For example: here's the route produced to go from Messiah to the nearest Chick-fil-A. 

![[route_ex.png|190]]

This route was found with the standard Dijkstra's algorithm.

<!-- pb -->


## 1. Project Structure

This section gives an overview of the supporting code in the project. You will not need to edit these files, but should be familiar with them. 

<br>

### `EdgeWeightedDigraph.java` and `DirectedEdge.java`

Since we are working with weighted digraphs, we will use a `DirectedEdge` data structure that contains the two vertices (`from` for the head and `to` for the tail) and the edge weight:

```
public record DirectedEdge(int from, int to, double weight) { }
```

<br>

The provided `EdgeWeightedDigraph` has a similar API to previous graphs, but uses `DirectedEdge`.

<br>

### `IndexMinPQ.java`

Also included is the IndexMinPQ data structure (indexed minimum priority queue), which has the following API:

![[impq_api.png|600]]


<!-- pb -->

## 2. Lab Task 1: Implement `DijkstraSP.java`

Your first step will be implementing the Dijkstra shortest path algorithm. 

Implement the `DijkstraSP` constructor and `relax(G, v)` methods. 

You can refer to the slides in class or in the book to help you.

To test your code, run the provided main program, it should match the results from class. 

<br>

---

## 3. `Navigator.java`

The main part of this lab will be running Dijkstras algorithm to find a route on a map. The challenge here will be converting the open map data to the proper graph representation. Once we have built the graph, we can just run Dijkstra's without any modifications. 

<br>

### A. Raw Map Data

For this lab, we will be using data from [openstreetmap.org](https://openstreetmap.org) (OSM). I have downloaded two map files already: `Messiah.osm.pbf` (a small map around Grantham) and `Harrisburg.osm.pdf` (a map containing Harrisburg, Mechanicsburg, and Carlisle). 

To read this pbf file, we will be using [osm4j](https://github.com/topobyte/osm4j), a Java library for reading and working with OSM data. I have included a `osm4j.jar` file in this folder.

You can read a bit more about the data model [here](https://github.com/topobyte/osm4j), but here is a quick rundown:

<br>

#### `OsmNode`

Points on the map are `OsmNode` objects. These have an id and a latitude/longitude:

![[osmnode_api.png|450]]

<!-- pb -->

#### `OsmWay`

Path segments on the map are `OsmWay` objects. These contain a list of `OsmNode` ids representing a sequence of points that make up that route. 

![[osmway_api.png|450]]

The `OsmWay` objects also have a set of key/value tags. These contain information about the roads. Use the following utility method to get these tags:

`OsmModelUtil.getTagsAsMap(way) -> Map<String, String>`

<br>

For example: here is the data about an `OsmWay` representing the stretch of road between the roundabout and the railroad bridge at the entrace of Messiah:

![[way_example.png|500]]

You can see that this way contains a list of 8 nodes, and has some tags including the type of road (residential), speed limit, and name.

<!-- pb -->

### B. Building a Graph

In the `Navigator.java` constructor, you can see the code will read the given `pbf` file and populate two structures:
- `HashMap<Long, OsmNode> nodeMap` - A map of all the OsmNodes, indexed by their Id
- `ArrayList<OsmWay> ways` - A list of all the OsmWay objects

Then, it will call the `Navigator::constructGraph()` method to turn this data into an `EdgeWeightedDigraph`. It does this in two steps.

First, it builds a mapping from OsmNode to vertex id. This mapping goes both ways:
- `HashMap<Long, Integer> vertexMap` - maps an OsmNode Id to its vertex number
- `ArrayList<OsmWay> vertexNodes` - maps a vertex id (index) to the corresponding OsmNode

Then, it will call `Navigator::addWay(G, OsmWay)`, this is what you will implement

<br>

### C. Main Program

The `Navigator::main` program is written for you. It does the following:
1. Creates the `Navigator nav` object using the file given by `args[0]`
2. Uses `Navigator::getClosest(lat, long)` to get the start/end nodes near the given coordinates. 
3. Does a `DijkstraSP` search from the start vertex to the end vertex
4. If a path is found, prints out the length, then uses `Navigator::pathToGeoJson(path)` to print out a json description of the path. 

You can copy-paste this json data into [https://geojson.io](https://geojson.io) to visualize the route.

<!-- pb -->

## 4. Lab Task 2: Add Edges to Graph

Your task is to take the `OsmWay` objects and convert them to edges in the Graph. You will mostly implement the method `Navigator::addWay(G, OsmWay)`

Remember that an OsmWay contains a list of node id's (Each node id maps to a vertex in the graph). You will need to add an edge for each adjacent pair of nodes in the OsmWay's list. The weight of the edge should be the distance between the nodes (use the provided `Navigator::distance` function). 

For example, consider the way representing Grantham Road:

![[edge_example.png|400]]

For one adjacent pair in the OsmWay (nodes 5 and 6), we would get their ids and the corresponding vertices. We would also calculate the distance between the nodes (78.55 meters) and add two edges to the graph (one in each direction).

**Special Case 1:** Not every OsmWay should be added to the graph. Some of them represent trails, bike paths, parking lets, etc. We will use a very rough filtering method: only consider OsmWay objects that have the tag "highway". If the OsmWay does not have a highway tag, skip it. 

**Special Case 2:** We need to respect one-way and two-way roads. An OsmWay is one-way if it has the key-value pair `oneway=yes`. If it is one-way, only add the edge in the forward direction (nodes i -> i+1). If it is not one-way, add edges in both directions.

### **Running Code**

To compile and run your code, you will need to include the `osm4j.jar` on the CLASSPATH. 

In Windows PowerShell you would do:
```
$env:CLASSPATH = "osm4j.jar;."
```

In Mac/Linux Terminal do:

```
export CLASSPATH=osm4j.jar:.
```

When you run the program, give it a map file: `java Navigator Messiah.osm.pbf`

If a route is found, the program will print out a json representation of the route. Copy-paste this data into [https://geojson.io](https://geojson.io) to visualize the route.

<!-- pb -->

### **Going Further**

Once your code is working, test it out on your own map.
1. Choose a US-city outside PA and download a map of it from [https://extract.bbbike.org](https://extract.bbbike.org). Select Format _Protocol Buffer (PBF)_ and a region, then it will email you a `pbf` file containing the data. 
2. Find two interesting points in the city using: [https://www.latlong.net](https://www.latlong.net). You can select a point on a map and it will tell you the lat/long. In the main method, update the start and end nodes to use these coordinates.
3. Copy-paste the printed json data and save it to a file. _Include this file in your lab sumission_. Then visualize it at [https://geojson.io](https://geojson.io).

<br>

---

## 5. Extra Credit: Calculate Shortest Time

In the above lab, you computed the shortest path based on distance. But this isn't always what we want, since a longer road might be quicker (higher speed limit). 

Modify your code to assign edge weights based on estimated time (distance / speed). Use the `maxspeed` tag to get the speed for a OsmWay. If it does not have a `maxspeed` tag, use 35 mph as the default. Note that the distance between two nodes is given in meters, so make sure you convert to miles first. 



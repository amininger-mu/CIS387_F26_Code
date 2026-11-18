# Lab 6 - Minimum Spanning Trees

**Course:** CIS 387 - Analysis of Algorithms - Fall 2026 \
**Instructor:** Dr. Aaron Mininger \

## Submission Instructions

**Due Date:** Nov 20 at 11:59am (beginning of class!)

**To Submit:** Upload your completed `KruskalMST.java` file, plus the two images described. 

Since this is a lab assignment, you are allowed to work with other people. However, you must type up and submit your own individual work.
You may consult other resources, including AI, to write the code, but try to work through things yourself first.

## Setup

All the code and files needed for this homework are available on the course git repository: [`https://github.com/amininger-mu/CIS387_F26_Code`](https://github.com/amininger-mu/CIS387_F26_Code)

In the `labs/lab6_mst` folder, you will see provided supporting code.

<br>

---

## Lab Overview

Minimum Spanning Trees have been used to perform _clustering_, where you have a large number of data points, a similarly measure, and you want to discover groups, or clusters, in the data. 

For example, in biology clustering is used to identify similar strains of a disease, or proteins that have similar mechanisms.

In this lab, we will use MST-based clustering to segment an image. The purpose is to identify groups of pixels that are visually similar. This can be used later for more advanced image processing. 

For example: here's the result on a giraffe picture

![[giraffes.jpg|400]]

Each color in the right image corresponds to a different segment.

<!-- pb -->

## 1. Project Structure

This section gives an overview of the supporting code in the project. You will not need to edit these files, but should be familiar with them. 

<br>

### `EdgeWeightedGraph.java` and `Edge.java`

Since we are working with weighted graphs, we will use an `Edge` data structure that contains the two vertices and the edge weight:

```
public record Edge(int v1, int v2, double weight) { }
```

<br>

The provided `EdgeWeightedGraph` has a similar API to previous graphs, but uses `Edge` objects:

![[graph_api.png|500]]

<br>

### `UnionFind.java`

Also included is the Union Find algorithm (weighted quick union), which has the following API:

![[uf_api.png|550]]

<br>

<!-- pb -->

## `ImageSegmentation.java`

The overall process is shown in `ImageSegmentation::main`:

#### `EdgeWeightedGraph G = constructImageGraph(img);`

This turns the image into an edge weighted graph where:
- Each pixel becomes a vertex
- Each pixel is connected to its 8 neighbors
- The edge weight is the difference in color

#### `KruskalMST mst = new KruskalMST(G, res, minSize);`

This runs the Kruskal MST algorithm on the image graph to come up with a segmentation. At the moment, it only generates a fully connected result. 

#### `UnionFind UF = mst.segmentation();`

This gets the UnionFind data structure generated from the MST. It contains information about the connected components (each one is different region in the image segmentation).

#### `BufferedImage out = randomColors(img.getWidth(), img.getHeight(), UF);`

This creates an image by looking at every pixel, finding its component id (in the UnionFind data structure), and coloring it according to that id (each component is assigned a random color). 

<br>

---

## 2. Segmentation Algorithm

Open up `KruskalMST.java`, which has been implemented for you. Study the implementation, making sure you understand it. 

At the beginning, 3 data structures are created to support the algorithm:
1. `LinkedList<Edge> mstEdges`: the chosen edges that make up the final MST
2. `UnionFind UF`: a union-find data structure used to track the connected components
3. `PriorityQueue<Edge> queue`: a priority queue of edges, sorted by weight

<br>

The basic version of the algorithm is as follows:
1. Add all edges to the priority queue
2. While the queue is not empty, remove the lowest weight edge
3. If the edge connects two different components, add the edge to the MST (and union the vertices) 

<!-- pb -->

### `KruskalMST`

Your task is to modify the `KruskalMST` algorithm to perform image segmentation. You will be using the approach published by Felzenszwalb et al. (_Efficient Graph-Based Image Segmentation, 2004_). 

<br>

**Step 1:** Setting a Merge Threshold

The idea in this algorithm is to calculate a threshold value for each component. We will refuse to add an edge to a component when its weight exceeds the threshold. This threshold is based on the size of the component and the weight its the largest edge.

This threshold is tuned by a resolution parameter `res` given to the `KruskalMST`

<br>

Instructions:
1. Before the while loop, create a new `double[] thresh`, with length equal to the number of vertices. This stores the threshold value for each component. Initialize all the values in the array to equal `res`.
2. Inside the loop, don't union an edge if its weight is greater than the treshold for both `id1` and `id2`
3. If you do union an edge, get the merged component id, and update its threshold as follows: `threshold[mergedId] = edge_weight + res / sizeof(mergedId)`

<br>

To run the code, use: `java ImageSegmentation <input_image> <output_image>`

<br>

I have a created an image `test.png` (left side) with regions of varying size. If you run `java ImageSegmentation img/test.png out.png` it should generate the image on the right. 

![[result1.png|300]]

Once it is working, run the segmentation on one of the images in the `img` folder with a res of 5.

For example: `java ImageSegmentation img/giraffe.jpg out.png 5`

Try adjusting the resolution parameter and seeing what happens

<!-- pb -->

**Step 2:** Merge Small Components

If you look at the results from step 1 on a real photo, you probably see a lot of very tiny components. We can improve the segmentation by setting a minimum size for each component. If a component is too small after step 1, we merge it into another.

Instructions (After Step 1 is finished):
1. Loop over all edges in the graph again, in order of edge weight. 
2. If the edge connects two vertices in the same component, skip it.
3. If the two vertices are in different components, and one of them has a size smaller than `minSize`, merge the edge.

These steps are very similar to the standard Kruskal MST algorithm, except that we are checking the component sizes as well.

Test your algorithm by running `java ImageSegmentation img/giraffe.jpg out.png 6 100`\
(The 100 is the min size). It should look similar to the image at the intro section of this lab. 

![[giraffes.jpg|300]]

<br>

**Step 3:** Apply to your own image

Once your segmentation algorithm is working, try it out on some different pictures. Vary the two parameters (resolution and minSize) and see what gives you the best results. 

I have provided a few images in the `img` directory, but add your own image and run tests on it. Find a value for the resolution and minSize that seems to work the best. Upload the original photo and the segmented result to Canvas, along with your completed `KruskalMST.java`






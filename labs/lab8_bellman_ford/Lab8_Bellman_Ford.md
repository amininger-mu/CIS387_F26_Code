# Lab 8 - Bellman Ford SP

**Course:** CIS 387 - Analysis of Algorithms - Fall 2026 \
**Instructor:** Dr. Aaron Mininger \

## Submission Instructions

**Due Date:** Nov 30 at 11:59pm (midnight)

**To Submit:** Upload your completed `Arbitrage.java` file 

Since this is a lab assignment, you are allowed to work with other people. However, you must type up and submit your own individual work.
You may consult other resources, including AI, to write the code, but try to work through things yourself first.

## Setup

All the code and files needed for this homework are available on the course git repository: [`https://github.com/amininger-mu/CIS387_F26_Code`](https://github.com/amininger-mu/CIS387_F26_Code)

In the `labs/lab8_bellman_ford` folder, you will see provided supporting code.

<br>

---

## Lab Overview

The Bellman-Ford algorithm solves the single-source shortest paths problem on edge-weighted directed graphs with negative edges.
It can also detect a negative cycle if one exists.

We will apply this to look for arbitrage opportunities in crypto currency exchange rates. 

**Arbitrage** is when you can find a sequence of exchanges that net you a profit. For example, suppose we see the following exchange rates:
- USD to CAD (Canadian Dollar) is 1.43
- CAD to MXN (Mexican Peso) is 13.4
- MXN to USD is 0.055

Then you could trade $100 USD -> 143 CAD -> 1,916 pesos -> $105 USD (profit!)

In reality, there are many traders and trading algorithms looking for these opportunities, but they tend to be rare and have very slim margins. Most trades will involve someone taking a cut or percentage, so an arbitrage loop will not exist.

**Note:** For some additional context, see pages 679-681 in the textbook.

<!-- pb -->

## 1. Project Structure

This section gives an overview of the supporting code in the project. You will not need to edit these files, but should be familiar with them. 

<br>

### `EdgeWeightedDigraph.java` and `DirectedEdge.java`

These are unchanged from Lab 7

<br>

### `EWCycleFinder.java`

This is an adaptation of the cycle finder algorithm for edge-weighted directed graphs. 

![[cycle_finder_api.png|600]]

<br>

### `SymbolEWDigraph.java`

This class reads a CSV file containing weighted edges and constructs an EdgeWeightedDigraph. It supports arbitrary Strings for vertex names and allows you to query the mapping in each direction.

![[symbol_ewd_api.png|600]]

<!-- pb -->

## 2. Bellman Ford SP

Your first step will be implementing the Bellman-Ford shortest path algorithm. Most of it is provided, you only need to implement the `relax(G, v)` method. 

You can refer to the slides in class or in the book to help you, but I recommend hand typing the function to gain an understanding of how it works.

To test your code, run the provided main program, it should match the results from class. 

<br>

---

## 3. `Arbitrage.java`

The main part of this lab will be running Bellman-Ford algorithm to look for arbitrage cycles on an exchange graph. 

Open `Arbitrage.java` and look at the main function. All it currently does is create a SymbolEWDigraph by reading the file given by `args[0]` and extracts the digraph G.

You must extend the main program as follows:
1. Create a _new_ `EdgeWeightedDigraph` which will represent our arbitrage graph.
2. Add edges to the arbitrage graph as described below
3. Run Bellman Ford on the arbitrage graph and check for a negative cycle.
4. If no cycle exists, print a message.
5. If a cycle exists, print out an example sequence of trades that will net a profit

<br>

### **Running the Code:**
First test your code by running `java Arbitrage cycle_test.csv`, then try it on the full `rates.csv` file

<br>

### **Adding Edges:**

Exchanging currency works on multiplication (_amount x rate_). But shortest paths work on adding edge weights. Luckily, we can turn multiplication to addition by using _logarithms_. And since we want the 'greatest' profit, we add a negative sign (lowest path = highest profit). 

Read over all the edges in the original graph `G`. Note that in the `rates.csv` file, each pair of cryptocurrencies is only listed once. For each edge in `G`, you will need to add 2 edges to your arbitrage graph. The forward edge should have weight `-log(rate)`, and the backward edge is `-log(1/rate)`

For example, suppose the exchange rate for Bitcoin to Etherium is 35.4. You would add an edge from BTC -> ETH of weight `-log(35.4) = -3.566` and an edge from ETH -> BTC of weight `3.566`

<!-- pb -->

### **Printing Result**
If a negative cycle is found, print out an example set of trades (one for each edge). Start with 1000 units of the first currency, and print out the intermediate values. (Use 6 decimals of precision)

Output for the given `cycle_test.csv` file:
```
Arbitrage Cycle Found!
Trading 1000.000000 USD for 1430.000000 CAD (x1.430)
Trading 1430.000000 CAD for 19162.000000 MEX (x13.400)
Trading 19162.000000 MEX for 10539.100000 USD (x0.550)
```

<br>

### **Tweaking Bellman Ford**
You may notice if you run your code, you may get disappointing results such as:
```
Arbitrage Cycle Found!
Trading 1000.000000 USD for 1430.000000 CAD (x1.430)
Trading 1430.000000 CAD for 1000.000000 USD (x0.699)
```

The problem here is that Bellman-Ford is only looking for _any_ negative cycle, not the best one, and tiny double precision errors can lead to false cycles. To fix this, we make Bellman-Ford only update a vertex if we find a better path by a given epsilon value. 

**Todo:** Modify your `BellmanFordSP::relax` algorithm as follows:

```
if (newDist + 0.001 < distTo[w]) {
    distTo[w] = newDist; 
    edgeTo[w] = e;
    ...
```

Then run your Arbitrage problem on the given `rates.csv` file. In a comment at the top of `Arbitrage.java` _record the arbitrage cycle you found_. 











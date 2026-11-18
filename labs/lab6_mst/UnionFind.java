/***********************************
 * Union Find Algorithm
 * (Uses weighted quick union)
 ***********************************/
public class UnionFind {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Returns the number of components
    public int count() {
        return count;
    }

    // Returns the size of component that p belongs to
    public int size(int p) {
        return size[find(p)];
    }

    // Returns the component id of p
    public int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // Unions (merges) the components of p and q
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}

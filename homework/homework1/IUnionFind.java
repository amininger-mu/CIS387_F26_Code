
public interface IUnionFind {
	/***
	 *  A Union-Find datatype contains a set of N elements that are 
	 *    disjointly partitioned into subsets, 
	 *    -- meaning each element belongs to exactly 1 subset
	 *    
	 *  Initially, each element belongs to its own unique set
	 ***/

	//  Each subset has a canonical element (or root/identifier/representative)
	//  Calling find(p) returns this identifying element
	//    (All elements in the same subset will returns the same identifying element)
	int find(int p);

	// The union(p, q) operation will merge the subsets that p and q belong to
	void union(int p, int q);

 	//  The connected(p, q) query will return true 
	//     if p and q belong to the same subset (are connected)
	boolean connected(int p, int q);

	// Returns the number of distinct subsets
	int count();
}

/*************************************************
 * Percolation
 * 
 * Student Name: _YOUR_NAME_HERE_
 * 
 * This file contains methods that simulate a simple
 *   percolation model of water passing through a 2D grid
 * 
 * We represent the grid as a 2D n*n grid of booleans,
 *   where grid[i][j] is true if the site at row i, col j is open
 * 
 * We say the system, or grid, percolates
 *   if there is a path from an open site in the top row
 *   to an open site in the bottom row
 * (a path is a sequence of open sites connected in NSEW directions)
 *************************************************/

public class Percolation {
    /*** 
     * readGridFromFile(String filename) -> boolean[][]
     * 
     * Reads a file with the given filename and uses it to 
     *   create a grid of booleans representing a percolation model
     * 
     * The file must be of the following format:
     * - The first line contains a single integer N (the size of the grid)
     * - The next N lines containing N characters: 
     *      either space ' ' for true/open, or 'X' for closed/false
     * 
     * Example
     * ---
     * 3
     * XX 
     * X  
     *   X
     * ---
     * 
     * @param filename - the file to read from
     * 
     * @return n*n boolean array where each element is true with probability p
     ***/
    public static boolean[][] readGridFromFile(String filename) {
        throw new UnsupportedOperationException("This method is not yet implemented");
    }

    /***
     * printGrid(boolean[][] grid)
     * 
     * @param grid - an n*n array of booleans
     * 
     * Prints the given grid to standard out,
     *   (Use the same format as reading from files,
     *    using space for open sites and X for closed sites)
     *
     * Example (4x4):
     * 4 
     *  XX 
     * X X
     * XX X
     * X XX
     ***/
    public static void printGrid(boolean[][] grid) {
        throw new UnsupportedOperationException("This method is not yet implemented");
    }

    /***
     * percolates(boolean[][] grid) -> boolean
     * 
     * Uses the union find algorithm to determine whether a system percolates
     * 
     * @param grid - an n*n grid of booleans, each i,j is true if site at row i, col j is open
     * @return true if the system percolates (there is a path from top row to bottom)
     */
    public static boolean percolates(boolean[][] grid) {
        throw new UnsupportedOperationException("This method is not yet implemented");
    }

    /***
     * createRandomGrid(int n, double p) -> boolean[][]
     * 
     * Creates and returns a random n*n grid of booleans, 
     *   where each is true with probability p
     * 
     * @param n - the size of the grid (n*n)
     * @param p - the probability any given site is open (0-1)
     * 
     * @return n*n boolean array where each element is true with probability p
     ***/
    public static boolean[][] createRandomGrid(int n, double p) {
        throw new UnsupportedOperationException("This method is not yet implemented");
    }


    /***
     * Percolation main function
     * args[0] = percolation grid file to read
     ***/
    public static void main(String[] args) {
        // Arg 1 = filename to read from
        if (args.length == 0) {
            System.err.println("You must give a filename to read");
            return;
        }

        String filename = args[0];
        boolean[][] grid = readGridFromFile(filename);

        printGrid(grid);

        if (percolates(grid)) {
            System.out.println("The grid percolates!");
        } else {
            System.out.println("The grid does not percolate!");
        }
    }
}

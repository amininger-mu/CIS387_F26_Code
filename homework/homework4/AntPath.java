/**************************************************************************
 *  Class: AntPath.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find the best path from the bottom-right corner of a grid
 *   to the top-left corner of the grid
 * 
 *  Each square has a food value, want to maximize the food along the path
 * 
 *  To pass data from a file, do: 'cat ant_test1.txt | java AntPath.java'
 ***********************************************************************/

import java.util.Scanner;

public class AntPath {

	static record Coord(int row, int col) { }

	/***
	 * Returns the maximum possible food gathered
	 *   when travelling from the top-left corner
	 *   to the bottom-right corner
	 ***/
	public static int bestPath(int[][] grid) {

		return 0;
	}

    /*****
     * AntPath::main
     * 
     * Reads from standard in:
	 *   2 int: width height
	 *   w*h ints: the food value of each square in the grid
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

		// 2 ints: grid width/height
		int width = scanner.nextInt();
		int height = scanner.nextInt();

		// Read in the grid of values
		int[][] grid = new int[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				grid[row][col] = scanner.nextInt();
			}
		}

		int solution = bestPath(grid);
		System.out.println("The best path gives " + solution + " food");

        scanner.close();
    }
}


/**************************************************************************
 *  Class: Trucking.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find the least number of trips needed to transport a set of trailers
 *    You can only transport 1 or 2 at a time
 *    There is also a max total weight you can pull
 * 
 *  Given an int[] array of trailers (weight for each trailer) 
 *    and a combined weight limit
 * 
 *  To pass data from a file, do: 'cat truck_test1.txt | java Trucking'
 ***********************************************************************/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Trucking {

	// The indices of the two trailers involved in a single trip
	// Use t2 = -1 if there is only 1 trailer involved
	public static record Trip(int t1, int t2) { }

	// Returns a list of the fewest number of trips needed to move all trailers
	//    when you can either move 1 or 2 at a time, 
	//    and there is a given weight limit
	public static List<Trip> minTrips(int[] trailers, int limit) {

		return null;
	}

    /*****
     * RoomSchedule::main
     * 
     * Reads from standard in:
	 *   1 int: total weight limit (W)
	 *   1 int: number of trailers (T)
	 *   T ints: weight of each trailer
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

		// Read weight limit
		int limit = scanner.nextInt();

		// Read array of trailers
		int T = scanner.nextInt();
		int[] trailers = new int[T];
		for (int i = 0; i < T; i++) {
			trailers[i] = scanner.nextInt();
		}
		Arrays.sort(trailers);

        scanner.close();

		// Get and print solution
		var trips = minTrips(trailers, limit);

		System.out.println("# Trips: " + trips.size());
		for (Trip trip : trips) {
			System.out.printf("--> Trailer %d (%d)", trip.t1, trailers[trip.t1]);
			if (trip.t2 >= 0) {
				System.out.printf(" and %d (%d)", trip.t2, trailers[trip.t2]);
			}
			System.out.println();
		}
    }
}


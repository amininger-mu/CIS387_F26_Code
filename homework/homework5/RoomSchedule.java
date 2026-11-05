/**************************************************************************
 *  Class: RoomSchedule.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find the most conference rooms that can be assigned at the same time
 * 
 *  Given an int[] array of teams (size for each team)
 *    and an int[] array of rooms (size for each room)
 * 
 *  It will determine the maximum number of rooms that can be assigned a team
 *    (without exceeding capacity)
 * 
 *  To pass data from a file, do: 'cat room_test1.txt | java RoomSchedule'
 ***********************************************************************/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class RoomSchedule {

	// A single room assignmet - the index of the room and team assigned to it
	static record Assignment(int room, int team) { } 


	// Returns a mapping from conference room -> team (indices)
	//   that maximizes the number of occupied conference rooms
	// Assumes that teams and rooms are pre-sorted (low to high)
	public static List<Assignment> maxRoomAssignment(int[] teams, int[] rooms) {

		return null;
	}

    /*****
     * RoomSchedule::main
     * 
     * Reads from standard in:
	 *   1 int: number of teams (T)
	 *   T ints: size of each team
	 *   1 int: number of rooms (R)
	 *   R ints: size of each room
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

		// Read array of teams
		int T = scanner.nextInt();
		int[] teams = new int[T];
		for (int i = 0; i < T; i++) {
			teams[i] = scanner.nextInt();
		}
		Arrays.sort(teams);

		// Read array of rooms
		int R = scanner.nextInt();
		int[] rooms = new int[R];
		for (int i = 0; i < R; i++) {
			rooms[i] = scanner.nextInt();
		}
		Arrays.sort(rooms);

        scanner.close();

		// Get and print solution
		var solution = maxRoomAssignment(teams, rooms);

		System.out.println("# Rooms Assigned: " + solution.size());
		for (var a : solution) {
			System.out.println(a);
		}
    }
}


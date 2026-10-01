/**************************************************************************
 *  Class: ClosestPair1D.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find the closest pair of numbers in 1D
 * 
 *  Example: [ 1.4, 6.2, 3.4, 9.1, 4.0 ] -> the closest pair is 3.4 and 4.0
 * 
 *  This file has two functions:
 *  - Pair closestPairSlow(List<Double> points): the default linear scan
 *  - Pair closestPair(List<Double> points): the divide and conquer you will implement
 * 
 *  To run this program, do 'java ClosestPair1D.java'
 *     and provide a list of doubles to standard in
 * 
 *  To pass data from a file, do: 'cat cp1_test1.txt | java ClosestPair1D.java'
 ***********************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class ClosestPair1D {
    // The result structure returned by closestPair
    public static record Pair (double x1, double x2, double distance) { 
        // constructor that takes 2 points and calculates the distance
        public Pair(double x1, double x2) {
            this(x1, x2, Math.abs(x1 - x2));
        }
    }

    // Given a list of points (x values), returns the closest pair of points
    // If there are not 2 points, returns null
    public static Pair closestPairSlow(List<Double> points) {
        Pair closest = null;

        points.sort(Double::compare);

        // Look at each pair of points for the smallest gap
        for (int i = 0; i < points.size()-1; i++) {
            Pair pair = new Pair(points.get(i), points.get(i+1));
            if (closest == null || pair.distance < closest.distance) {
                closest = pair;
            }
        }
        return closest;
    }

    // Given a list of points (x values), 
    //    returns the closest pair of points (minimum separation)
    // If there are not 2 points, returns null
    public static Pair closestPair(List<Double> points) {
        // sort points

        return null;
    }


    /*****
     * ClosestPair1D::main
     * 
     * Reads a list of doubles from standard in,
     *   then calls closestPair
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read doubles from standard in
        List<Double> points = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            points.add(scanner.nextDouble());
        }

        // Run Algorithm with each version

        Pair slowResult = closestPairSlow(points);
        System.out.println("Closest Pair (slow): " + slowResult);

        Pair fastResult = closestPair(points);
        System.out.println("Closest Pair (fast): " + fastResult);

        if ((slowResult.x1 == fastResult.x1 && slowResult.x2 == fastResult.x2) || 
            (slowResult.x1 == fastResult.x2 && slowResult.x2 == fastResult.x1)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        
        scanner.close();
    }
}


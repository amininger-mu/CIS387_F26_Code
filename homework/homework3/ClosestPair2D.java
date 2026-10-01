/**************************************************************************
 *  Class: ClosestPair2D.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find the closest pair of numbers in 2D
 * 
 *  This file has two functions:
 *  - Pair closestPairSlow(List<Point> points): the default pairwise comparison n^2
 *  - Pair closestPair(List<Point> points): the divide and conquer you will implement
 * 
 *  To run this program, do 'java ClosestPair2D.java'
 *     and provide a list of doubles to standard in
 * 
 *  To pass data from a file, do: 'cat cp2_test1.txt | java ClosestPair2D.java'
 ***********************************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class ClosestPair2D {
    // An immutable 2D Point structure
    //   create using: Point p = new Point(3.4, 2.1);
    //   then you can access p.x and p.y
    static record Point (double x, double y) { }

    // Returns the distance between the two points
    static double calcDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // Comparator functions for sorting Points on x and y
    static int compareX(Point p1, Point p2) {
        return Double.compare(p1.x, p2.x);
    }
    static int compareY(Point p1, Point p2) {
        return Double.compare(p1.y, p2.y);
    }

    // The result structure returned by closestPair
    //    create using: Pair pair = new Pair(point1, point2);
    static record Pair (Point p1, Point p2, double distance) { 

        // constructor that takes 2 points and calculates the distance
        public Pair(Point p1, Point p2) {
            this(p1, p2, calcDistance(p1, p2));
        }
    }

    // Given a list of points (x,y coordinates), 
    //    returns the closest pair of points
    // If there are not 2 points, returns null
    public static Pair closestPairSlow(List<Point> points) {
        Pair closest = null;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i+1; j < points.size(); j++) {
                Pair pair = new Pair(points.get(i), points.get(j));
                if (closest == null || pair.distance < closest.distance) {
                    closest = pair;
                }
            }
        }

        return closest;
    }

    // Given a list of points (x values), 
    //    returns the closest pair of points (minimum separation)
    // If there are not 2 points, returns null
    public static Pair closestPair(List<Point> points) {

        return null;
    }

    /*****
     * ClosestPair2D::main
     * 
     * Reads a list of double pairs from standard in,
     *   then calls closestPair with each version
     *   and compares results
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read doubles from standard in
        List<Point> points = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            points.add(new Point(x, y));
        }
        scanner.close();

        // Run Algorithms and compare
        Pair slowResult = closestPairSlow(points);
        System.out.println("Closest Pair (Slow): " + slowResult);

        Pair fastResult = closestPair(points);
        System.out.println("Closest Pair (Fast): " + fastResult);

        if (fastResult == null) return;

        if ((slowResult.p1 == fastResult.p1 && slowResult.p2 == fastResult.p2) || 
            (slowResult.p1 == fastResult.p2 && slowResult.p2 == fastResult.p1)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
    }
}


package lesson3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import lesson3.queues.Queue;
import lesson3.impl.ArrayQueue;
import lesson3.impl.CircularQueue;
import lesson3.impl.LinkedListQueue;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class QueueGraphing 
{
    public static void main( String[] args )
    {
        System.out.print("Enter size N: ");
        int count = StdIn.readInt();

        System.out.printf("Running Array test with %d items\n", count);
        profileQueueTimes(new ArrayQueue<Integer>(), "Array", count);

        System.out.println();

        System.out.printf("Running LinkedList test with %d items\n", count);
        profileQueueTimes(new LinkedListQueue<Integer>(), "Linked List", count);
    }

    private static void profileQueueTimes(Queue<Integer> queue, String name, int count) {

        /*************** STEP 1: ENQUEUE N ITEMS ******************/

        // Map recording the frequency of each reported elapsed time
        Map<Long, Integer> enqueueHistogram = new HashMap<>();

        for (int i = 0; i < count; i++) {
            long start = System.nanoTime();
            queue.enqueue(i);
            long elapsed = (System.nanoTime() - start) / 1000;

            int tally = enqueueHistogram.getOrDefault(elapsed, 0);
            enqueueHistogram.put(elapsed, tally+1);
        }

        scatterPlot("Enqueue: " + name, enqueueHistogram);

        /*************** STEP 2: DEQUEUE N ITEMS ******************/

        // Map recording the frequency of each reported elapsed time
        Map<Long, Integer> dequeueHistogram = new HashMap<>();

        for (int i = 0; i < count; i++) {
            long start = System.nanoTime();
            queue.dequeue();
            long elapsed = (System.nanoTime() - start) / 1000;

            int tally = dequeueHistogram.getOrDefault(elapsed, 0);
            dequeueHistogram.put(elapsed, tally+1);
        }

        scatterPlot("Dequeue: " + name, dequeueHistogram);
    }

    private static void scatterPlot(String title, Map<Long, Integer> points) {
        // Gets the max x and y values in the map
        long maxX = points.keySet().stream().mapToLong(x -> x).max().getAsLong();
        int maxY = points.values().stream().mapToInt(x -> x).max().getAsInt();

        StdDraw.clear();

        // Graphing on log/log scale
        double xScale = Math.log10(maxX + 1);
        double yScale = Math.log10(maxY);

        StdDraw.setXscale(-0.1, 1.1);
        StdDraw.setYscale(-0.1, 1.1);

        // Draw axes
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0.0, 0.0, 1.0, 0.0);
        StdDraw.line(0.0, 0.0, 0.0, 1.0);
        
        StdDraw.text(0.5, 1.05, title);
        StdDraw.text(0.5, -0.05, "Time");
        StdDraw.text(-0.05, 0.5, "N");

        // Draw horizontal/vertical scale lines
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.GRAY);
        for (double x = 1.0; x < xScale; x += 1.0) {
            StdDraw.line(x / xScale, 0.0, x / xScale, 1.0);
        }
        for (double y = 1.0; y < yScale; y += 1.0) {
            StdDraw.line(0.0, y / yScale, 1.0, y / yScale);
        }

        // Draw each point (using log/log scaling)
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        for (var e : points.entrySet()) {
            StdDraw.point(Math.log10(e.getKey()+1)/xScale, Math.log10(e.getValue())/yScale);
        }

        StdDraw.show();
        StdDraw.save(title.replaceAll("[ :]", "") + ".png");
    }
}

package lesson3;

import lesson3.queues.LinkedListQueue;
import lesson3.queues.Queue;

import lesson3.queues.*;
import edu.princeton.cs.algs4.StdIn;

public class QueueTesting 
{
    static Runtime runtime = Runtime.getRuntime();
    static long initialMemory;

    public static void main( String[] args )
    {
        // Memory at beginning of program
        runtime.gc();
        initialMemory = runtime.totalMemory() - runtime.freeMemory();

        System.out.print("Enter size N: ");
        int count = StdIn.readInt();

        // System.out.printf("Running Array test with %d steps\n", count);
        // runRandomTest(new ArrayQueue<Integer>(), count);

        // System.out.println();

        System.out.printf("Running LinkedList test with %d steps\n", count);
        runRandomTest(new LinkedListQueue<Integer>(), count);

        System.out.println();

        System.out.printf("Running Circular Array test with %d steps\n", count);
        runRandomTest(new CircularQueue<Integer>(), count);
    }

    private static void runRandomTest(Queue<Integer> queue, int count) {
        long start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            if (Math.random() < 0.667) {
                queue.enqueue(i);
            } else if (queue.size() > 0) {
                queue.dequeue();
            }
        }
        long elapsed = System.nanoTime() - start;
        System.out.println("Final queue size: " + queue.size());
        System.out.println("Total Time: " + elapsed / 1000);

        profileMemory();
    }

    private static void profileMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory() - initialMemory)/1000;
        System.out.println("Memory Usage: " + usedMemory + "kb");
    }
}

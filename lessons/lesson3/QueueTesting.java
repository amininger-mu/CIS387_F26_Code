package lesson3;

import lesson3.queues.Queue;

import lesson3.impl.ArrayQueue;
import lesson3.impl.LinkedListQueue;

import edu.princeton.cs.algs4.StdIn;

public class QueueTesting 
{
    public static void main( String[] args )
    {
        System.out.print("Enter size N: ");
        int count = StdIn.readInt();

        System.out.printf("Running Array test with %d items\n", count);
        runTotalTimeTest(new ArrayQueue<Integer>(), count);

        System.out.println();

        System.out.printf("Running LinkedList test with %d items\n", count);
        runTotalTimeTest(new LinkedListQueue<Integer>(), count);
    }

    private static void runTotalTimeTest(Queue<Integer> queue, int count) {
        /*************** STEP 1: ENQUEUE N ITEMS ******************/

        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            queue.enqueue(i);
        }
        long elapsed = System.nanoTime() - start;
        System.out.println("Total Enqueueing Time: " + elapsed / 1000);

        profileMemory();

        /*************** STEP 2: TRAVERSE N ITEMS ******************/
        start = System.nanoTime();
        for (Integer i : queue) {
        }
        elapsed = System.nanoTime() - start;
        System.out.println("Total Traversal Time: " + elapsed / 1000);

        /*************** STEP 3: REMOVE N ITEMS ******************/

        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            queue.dequeue();
        }
        elapsed = System.nanoTime() - start;
        System.out.println("Total Dequeueing Time: " + elapsed / 1000);
    }

    private static void profileMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory Usage: " + usedMemory);
    }
}

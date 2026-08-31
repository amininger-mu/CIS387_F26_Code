package lesson3.test;

import lesson3.queues.ArrayQueue;
import lesson3.queues.Queue;

public class ArrayQueueTest extends QueueTest {

    @Override
    protected Queue<Integer> createQueue() {
        return new ArrayQueue<Integer>();
    }
}

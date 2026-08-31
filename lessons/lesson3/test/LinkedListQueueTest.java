package lesson3.test;

import lesson3.queues.LinkedListQueue;
import lesson3.queues.Queue;

public class LinkedListQueueTest extends QueueTest {

    @Override
    protected Queue<Integer> createQueue() {
        return new LinkedListQueue<Integer>();
    }
}

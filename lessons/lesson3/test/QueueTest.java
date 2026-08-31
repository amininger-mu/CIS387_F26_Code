package lesson3.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lesson3.queues.Queue;

abstract class QueueTest {

    private Queue<Integer> queue;

    protected abstract Queue<Integer> createQueue();

    @BeforeEach
    void setUp() {
        queue = createQueue();
    }

    // --- Basic state ---

    @Test
    void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void enqueueMakesQueueNonEmpty() {
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    // --- FIFO ordering ---

    @Test
    void singleEnqueueDequeueReturnsSameItem() {
        queue.enqueue(42);
        assertEquals(42, queue.dequeue());
    }

    @Test
    void maintainsFifoOrder() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    // --- Size tracking ---

    @Test
    void sizeTracksEnqueueAndDequeue() {
        assertEquals(0, queue.size());
        queue.enqueue(10);
        queue.enqueue(20);
        assertEquals(2, queue.size());
        assertEquals(10, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals(20, queue.dequeue());
    }

    // --- Edge cases ---

    @Test
    void survivesResizeUnderLoad() {
        // Pushes past the initial backing-array capacity to force a grow.
        int n = 1000;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        assertEquals(n, queue.size());
        for (int i = 0; i < n; i++) {
            assertEquals(i, queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }

    @Test
    void interleavedEnqueueDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        queue.enqueue(3);
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    // --- Iteration (because Queue extends Iterable) ---

    @Test
    void iteratorYieldsItemsInFifoOrder() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        int expected = 1;
        for (int actual : queue) {
            assertEquals(expected++, actual);
        }
        assertEquals(4, expected); // confirms exactly 3 items were visited
    }

    @Test
    void iteratorDoesNotConsumeQueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        for (int ignored : queue) { /* just walk it */ }
        // Iterating must not mutate the queue.
        assertEquals(2, queue.size());
        assertEquals(1, queue.dequeue());
    }

    @Test
    void iteratorOnEmptyQueueHasNoElements() {
        Iterator<Integer> it = queue.iterator();
        assertFalse(it.hasNext());
    }
}

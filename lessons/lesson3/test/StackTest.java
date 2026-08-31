package lesson3.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lesson3.stacks.Stack;

/**************************************************
 * StackTest
 * JUnit 5 tests for a Stack<Integer> implementation.
 *
 * NOTE: Update the createStack() factory method below to
 * return your concrete Stack implementation.
 *************************************************/
public abstract class StackTest {

    private Stack<Integer> stack;

    /**
     * Factory method for the concrete Stack implementation under test.
     * Replace 'new YourStackImpl<>()' with your actual class.
     */
    protected abstract Stack<Integer> createStack();

    @BeforeEach
    void setUp() {
        stack = createStack();
    }

    // ---------- isEmpty / size ----------

    @Test
    void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void stackNotEmptyAfterPush() {
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    void sizeIncreasesWithPush() {
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.size());
        stack.push(3);
        assertEquals(3, stack.size());
    }

    @Test
    void sizeDecreasesWithPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void stackEmptyAfterPoppingAllItems() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    // ---------- push / pop (LIFO ordering) ----------

    @Test
    void popReturnsPushedItem() {
        stack.push(42);
        assertEquals(42, stack.pop());
    }

    @Test
    void popReturnsItemsInLifoOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void interleavedPushAndPopMaintainsLifoOrder() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        stack.push(4);
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void handlesNullItems() {
        stack.push(null);
        assertEquals(1, stack.size());
        assertNull(stack.pop());
    }

    @Test
    void handlesDuplicateItems() {
        stack.push(7);
        stack.push(7);
        stack.push(7);
        assertEquals(3, stack.size());
        assertEquals(7, stack.pop());
        assertEquals(7, stack.pop());
        assertEquals(7, stack.pop());
    }

    @Test
    void handlesLargeNumberOfItems() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        assertEquals(n, stack.size());
        for (int i = n - 1; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    // ---------- iterator ----------

    @Test
    void iteratorTraversesInOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        List<Integer> seen = new ArrayList<>();
        for (Integer item : stack) {
            seen.add(item);
        }
        assertEquals(List.of(3, 2, 1), seen);
    }

    @Test
    void iteratorOnEmptyStackHasNoElements() {
        assertFalse(stack.iterator().hasNext());
    }

    @Test
    void iteratorDoesNotConsumeStack() {
        stack.push(1);
        stack.push(2);
        for (Integer ignored : stack) {
            // traverse only
        }
        assertEquals(2, stack.size());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }
}
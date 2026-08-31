package lesson3.queues;

/**************************************************
 * Queue
 * @param <Item>
 * An abstract interface for a FIFO queue
 *************************************************/

public interface Queue<Item> extends Iterable<Item> {
    // Adds an item to the end of the queue
    void enqueue(Item item);

    // Removes and returns an item from the front of the queue
    Item dequeue();

    // Returns true if the queue size is 0
    boolean isEmpty();

    // Returns the number of items in the queue
    int size();
}

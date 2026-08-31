package lesson3.stacks;

/**************************************************
 * Queue
 * @param <Item>
 * An abstract interface for a LIFO stack
 *************************************************/

public interface Stack<Item> extends Iterable<Item> {
    // Adds an item to the top of the stack
    void push(Item item);

    // Removes and returns an item from the top of the stack
    Item pop();

    // Returns true if the stack size is 0
    boolean isEmpty();

    // Returns the number of items in the stack
    int size();
}

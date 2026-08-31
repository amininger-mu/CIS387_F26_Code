package lesson3.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<Item> implements Stack<Item> {
    private class Node {
        Item item;
        Node next;
    }

    Node top = null;
    int n = 0;

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Item pop() {
        if (top == null) throw new NoSuchElementException();

        Item item = top.item;
        top = top.next;
        n--;
        return item;
    }

    @Override
    public void push(Item item) {
        Node newTop = new Node();
        newTop.item = item;
        newTop.next = top;
        top = newTop;
        n++;
    }

    private class LLIterator implements Iterator<Item> {
        private Node current;
        public LLIterator() {
            current = top;
        }
        public boolean hasNext() {
            return current != null;
        }
        public void remove() { }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new LLIterator();
    }
    
}

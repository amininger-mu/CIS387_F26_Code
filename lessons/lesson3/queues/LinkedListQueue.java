package lesson3.queues;

import java.util.Iterator;

public class LinkedListQueue<Item> implements Queue<Item> {
    private class Node {
        Item item;
        Node next;
    }

    Node first = null;
    Node last = null;
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
    public Item dequeue() {
        Item front = first.item;
        first = first.next;
        n--;
        if (first == null) {
            last = null;
        }
        return front;
    }

    @Override
    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;
        if (last == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        n++;
    }

    private class LLIterator implements Iterator<Item> {
        private Node current;
        public LLIterator() {
            current = first;
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

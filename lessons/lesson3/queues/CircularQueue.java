package lesson3.queues;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CircularQueue<Item> implements Queue<Item> {
    private Item[] a;
    private int first;
    private int last;
    private int n;

    public CircularQueue () {
        a = (Item[])new Object[4];
        first = 0;
        last = 0;
    }

    @Override
    public Item dequeue() {
        Item front = a[first];
        a[first] = null;
        n--;
        first = (first + 1) % a.length;

        if (n > 1 && n <= a.length / 4) {
            resize(a.length/2);
        }

        return front;
    }

    @Override
    public void enqueue(Item item) {
        if (n == a.length) {
            resize(a.length*2);
        }
        a[last] = item;
        n++;
        last = (last + 1) % a.length;
    }

    private void resize(int newLength) {
        Item[] newArray = (Item[])new Object[newLength];
        for (int i = 0; i < n; i++) {
            newArray[i] = a[(first + i) % a.length];
        }
        first = 0;
        last = n;
        a = newArray;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    private class ArrayIterator implements Iterator<Item> {
        private int index;
        public ArrayIterator() {
            index = first;
        }
        public boolean hasNext() {
            return index != last;
        }
        public Item next() {
            Item item = a[index];
            index = (index + 1) % a.length;
            return item;
        }
        public void remove() { } // not supported
    } 

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
}


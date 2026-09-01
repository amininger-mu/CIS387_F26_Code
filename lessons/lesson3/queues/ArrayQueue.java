package lesson3.queues;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ArrayQueue<Item> implements Queue<Item> {
    private Item[] a;
    private int n;

    public ArrayQueue () {
        a = (Item[])new Object[4];
        n = 0;
    }

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
        Item item = a[0];
        n--;
        for (int i = 0; i < n; i++) {
            a[i] = a[i+1];
        }
        a[n] = null;
        return item;
    }

    @Override
    public void enqueue(Item item) {
        if (n == a.length) {
            resize(a.length*2);
        }
        a[n++] = item;
    }


    private void resize(int newLength) {
        Item[] temp = (Item[])new Object[newLength];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }


    private class ArrayIterator implements Iterator<Item> {
        private int index = 0;
        public boolean hasNext() {
            return index < n;
        }
        public Item next() {
            return a[index++];
        }
        public void remove() { } // not supported
    } 

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
}

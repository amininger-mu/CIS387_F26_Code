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

        return null;
    }

    @Override
    public void enqueue(Item item) {

    }


    private void resize(int newLength) {
        Item[] temp = (Item[])new Object[newLength];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    //private class ArrayIterator implements Iterator<Item> {

    //} 

    @Override
    public Iterator<Item> iterator() {
        return null; //new ArrayIterator();
    }
}

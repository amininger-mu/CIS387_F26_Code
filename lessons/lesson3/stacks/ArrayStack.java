package lesson3.stacks;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ArrayStack<Item> implements Stack<Item> {
    private Item[] a;
    private int n;

    public ArrayStack () {
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
    public Item pop() {
        Item top = a[--n];
        a[n] = null;
        if (n > 1 && n < a.length / 4) {
            resize(a.length/2);
        }
        return top;
    }

    @Override
    public void push(Item item) {
        if (n == a.length) {
            resize(a.length*2);
        }
        a[n++] = item;
    }


    private void resize(int newLength) {
        Item[] newArray = (Item[])new Object[newLength];
        for (int i = 0; i < n; i++) {
            newArray[i] = a[i];
        }
        a = newArray;
    }

    /** Iterates over stack from top-bottom **/
    private class ArrayIterator implements Iterator<Item> {
        private int index = n;
        public boolean hasNext() {
            return index > 0;
        }
        public Item next() {
            return a[--index];
        }
        public void remove() { } // not supported
    } 

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
}

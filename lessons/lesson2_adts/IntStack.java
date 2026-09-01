package lesson2_adts;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class IntStack implements Iterable<Integer> {

    private int[] a;
    private int n;

    public IntStack (int maxSize) {
        a = new int[maxSize];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public int pop() {
        n--;
        return a[n];
    }

    public void push(int item) {
        a[n] = item;
        n++;
    }

    public Iterator<Integer> iterator() {
        return new StackIterator();
    }

    public class StackIterator implements Iterator<Integer> {
        private int index;
        public StackIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < n;
        }

        @Override
        public Integer next() {
            int element = a[index];
            index++;
            return element;
        }
    }

    public static void main(String[] args) {
        IntStack stack = new IntStack(100);
        stack.push(4);
        stack.push(6);
        stack.push(7);

        System.out.println("Values in Stack");
        for (int i : stack) {
            System.out.println(i);
        }

        System.out.println("pop() returned 7? " + (stack.pop() == 7));
        stack.push(8);
        System.out.println("pop() returned 8? " + (stack.pop() == 8));
        System.out.println("pop() returned 6? " + (stack.pop() == 6));
        stack.push(2);
        System.out.println("pop() returned 2? " + (stack.pop() == 2));
        System.out.println("pop() returned 4? " + (stack.pop() == 4));
    }
}



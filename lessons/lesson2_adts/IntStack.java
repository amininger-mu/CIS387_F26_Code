package lesson2_adts;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class IntStack {

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

        return null;
    }

    public static void main(String[] args) {
        IntStack stack = new IntStack(100);
        stack.push(4);
        stack.push(6);
        stack.push(7);
        System.out.println("7? " + (stack.pop() == 7));
        stack.push(8);
        System.out.println("8? " + (stack.pop() == 8));
        System.out.println("6? " + (stack.pop() == 6));
        stack.push(2);
        System.out.println("2? " + (stack.pop() == 2));
        System.out.println("4? " + (stack.pop() == 4));

        // for (int i : stack) {

        // }
    }
}



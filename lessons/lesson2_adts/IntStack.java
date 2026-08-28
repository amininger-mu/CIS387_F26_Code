package lesson2_adts;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class IntStack implements Iterable<Integer> {

    public IntStack () {

    }

    public boolean isEmpty() {

        return true;
    }

    public int size() {

        return 0;
    }

    public int pop() {

        return 0;
    }

    public void push(int item) {

    }

    public Iterator<Integer> iterator() {

        return null;
    }

    public static void main(String[] args) {
        IntStack stack = new IntStack();
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
    }
}



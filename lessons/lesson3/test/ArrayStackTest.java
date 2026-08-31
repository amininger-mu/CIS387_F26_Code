package lesson3.test;

import lesson3.stacks.Stack;
import lesson3.stacks.ArrayStack;

public class ArrayStackTest extends StackTest {
    public Stack<Integer> createStack() {
        return new ArrayStack<Integer>();
    }
}

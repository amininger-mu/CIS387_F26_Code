package lesson3.test;

import lesson3.stacks.Stack;
import lesson3.stacks.LinkedListStack;

public class LinkedListStackTest extends StackTest {
    public Stack<Integer> createStack() {
        return new LinkedListStack<Integer>();
    }
}

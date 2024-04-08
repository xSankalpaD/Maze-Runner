package ca.mcmaster.se2aa4.mazerunner;

import java.util.Stack;

public class Index {
    private Stack<Integer> stack;

    Index() {
        stack = new Stack<>();
    }

    public Index append(int value) {
        Index newIndex = this.clone();
        newIndex.stack.push(value);
        return newIndex;
    }

    int pop() {
        return stack.pop();
    }

    boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Index clone() {
        Index newIndex = new Index();
        // Deep copy the stack contents
        newIndex.stack.addAll(this.stack);
        return newIndex;
    }
}

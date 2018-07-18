package com.jutils.stackqueue;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * 用栈来实现队列
 */
public class TwoStackQueue<E> {
    private Stack<E> stackPush;
    private Stack<E> stackPop;

    public TwoStackQueue() {
        stackPush = new Stack<E>();
        stackPop = new Stack<E>();
    }

    public void add(List<E> list) {
        for (E e : list) {
            stackPush.push(e);
        }
    }

    public void add(E ele) {
        stackPush.push(ele);
    }

    public E poll() {
        if (stackPop.empty()) {
            fillPop();
        }
        return stackPop.pop();
    }

    private void fillPop() {
        while (!stackPush.empty()) {
            stackPop.push(stackPush.pop());
        }
    }

    public E peek() {
        if (stackPop.empty()) {
            fillPop();
        }
        return stackPop.pop();
    }

    public boolean empty() {
        if (stackPop.empty()) {
            fillPop();
        }
        return stackPop.size() <= 0;
    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("A", "B", "C", "D", "E");
        TwoStackQueue<String> twoStackQueue = new TwoStackQueue<>();
        twoStackQueue.add(list);
        while (!twoStackQueue.empty()) {
            System.out.println("output " + twoStackQueue.poll());
        }
    }
}

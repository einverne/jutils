package com.jutils.stackqueue;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * Created by einverne on 7/18/18.
 */
public class RecursiveStack {
    /**
     * 找到栈底的元素并pop
     */
    public static <E> E getLastAndPop(Stack<E> stack) {
        E top = stack.pop();
        System.out.println("stack top " + top);
        if (stack.empty()) {
            System.out.println("stack empty");
            return top;
        } else {
            System.out.println("stack getLastAndPop " + top);
            E last = getLastAndPop(stack);
            stack.push(top);
            return last;
        }
    }

    public static <E> void reverse(Stack<E> stack) {
        if (stack.empty()) {
            return;
        }
        System.out.println("reverse " + stack);
        E last = getLastAndPop(stack);
        reverse(stack);
        stack.push(last);
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        List<String> list = Lists.newArrayList("A", "B", "C", "D");
        stack.addAll(list);
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);
    }
}

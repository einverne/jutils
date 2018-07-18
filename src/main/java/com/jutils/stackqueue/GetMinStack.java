package com.jutils.stackqueue;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 特殊栈 O(1) 时间内 getMin()
 */
public class GetMinStack<E extends Comparable> {

	private Stack<E> stackData;
	private Stack<E> stackMin;

	public GetMinStack() {
		stackData = new Stack<>();
		stackMin = new Stack<>();
	}

	public static void main(String[] args) {
		GetMinStack<Integer> minStack = new GetMinStack<>();
		ArrayList<Integer> demoList = Lists.newArrayList(2, 3, 4, 1, 5, 2, 1, 5);
		for (Integer i : demoList) {
			minStack.push(i);
		}
		for (int i = 0; i < demoList.size(); ++i) {
			Integer min = minStack.getMin();
			System.out.println("min " + min);
			Integer pop = minStack.pop();
			System.out.println("pop " + pop);
		}
	}

	public void push(E item) {
		if (stackMin.empty() || item.compareTo(stackMin.peek()) <= 0) {
			stackData.push(item);
			stackMin.push(item);
		} else {
			stackData.push(item);
		}
	}

	public E pop() {
		if (stackData.empty()) {
			throw new EmptyStackException();
		}
		if (stackData.peek().compareTo(stackMin.peek()) == 0) {
			stackData.pop();
			return stackMin.pop();
		} else {
			return stackData.pop();
		}
	}

	public E getMin() {
		return stackMin.peek();
	}
}

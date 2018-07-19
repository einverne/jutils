package com.jutils.stackqueue;

import com.google.common.collect.Lists;
import java.util.Stack;

public class OrderStack {

	/**
	 * 针对 stack 内容进行排序，栈底到栈顶，从小到大
	 *
	 * 解法
	 *
	 * 引入 helper 栈和中间变量 current，每次取出待排序 stack 栈顶元素放入 current 然后比较 current 与 helper 栈顶元素大小， 如果
	 * current > helper.peek() 则将 helper 中所有元素反push到 stack 中，否则将 current push 到 helper 中
	 */
	public static <E extends Comparable> void orderStack(Stack<E> stack) {
		Stack<E> helper = new Stack<>();
		while (!stack.empty()) {
			E current = stack.pop();
			if (helper.empty() || current.compareTo(helper.peek()) > 0) {
				while (!helper.empty()) {
					stack.push(helper.pop());
				}
			}
			helper.push(current);
		}
		while (!helper.empty()) {
			stack.push(helper.pop());
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.addAll(Lists.newArrayList(2, 1, 4, 5, 3));
		System.out.println(stack);
		orderStack(stack);
		System.out.println(stack);
	}
}

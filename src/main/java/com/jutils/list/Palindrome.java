package com.jutils.list;

import java.util.Stack;

public class Palindrome {

	/**
	 * 判断链表是不是回文（Palindrome）， abba aba 前半段和后半段对称
	 */
	public static <T extends Comparable> boolean isPalindrome(Node<T> head) {
		Stack<Node<T>> stack = new Stack<>();
		Node<T> cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		cur = head;
		while (!stack.empty()) {
			if (stack.pop().data.compareTo(cur.data) != 0) {
				return false;
			}
			cur = cur.next;
		}
		return true;
	}

	public static void main(String[] args) {
		Node<String> head = new Node<>("a");
		head.next = new Node<>("b");
		head.next.next = new Node<>("a");
		boolean palindrome = isPalindrome(head);
		System.out.println(palindrome);
	}

	static class Node<T extends Comparable> {

		public T data;
		public Node<T> next;

		public Node(T data) {
			this.data = data;
		}
	}

}

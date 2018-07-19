package com.jutils.list;

public class ReverseList {

	/**
	 * 反转单链表
	 */
	public static <T> Node<T> reverseList(Node<T> head) {
		Node<T> pre = null;
		Node<T> next;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		Node<String> head = new Node<>("head");
		Node<String> t = head;
		for (int i = 1; i <= 5; i++) {
			t.next = new Node<>("" + i);
			t = t.next;
		}
		t = head;
		while (t != null) {
			System.out.println(t.data);
			t = t.next;
		}
		head = reverseList(head);
		t = head;
		while (t != null) {
			System.out.println(t.data);
			t = t.next;
		}
	}

	static class Node<T> {

		public T data;
		public Node<T> next;

		public Node(T data) {
			this.data = data;
		}
	}
}

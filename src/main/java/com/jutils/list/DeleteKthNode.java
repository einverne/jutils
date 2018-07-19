package com.jutils.list;

public class DeleteKthNode {

	public static void main(String[] args) {
		Node<String> head = new Node<String>("head", null);
		Node<String> tempNode = head;
		for (int i = 0; i < 10; i++) {
			tempNode.next = new Node<>("" + i, null);
			tempNode = tempNode.next;
		}
		tempNode = head;
		while (tempNode != null) {
			System.out.println(tempNode.data);
			tempNode = tempNode.next;
		}
		head = deleteKthNode(head, 9);
		tempNode = head;
		while (tempNode != null) {
			System.out.println(tempNode.data);
			tempNode = tempNode.next;
		}
	}

	/**
	 * 删除链表中倒数第K个节点
	 */
	public static <T> Node<T> deleteKthNode(Node<T> head, int lastKth) {
		if (head == null || lastKth < 1) {
			return head;
		}
		Node temp = head;
		while (temp.next != null) {
			lastKth--;
			temp = temp.next;
		}
		if (lastKth > 0) {
			return head;
		}
		if (lastKth == 0) {
			// delete header
			return head.next;
		}
		temp = head;
		while (temp != null) {
			Node nextNode = temp.next;
			lastKth++;
			if (lastKth == 0) {
				temp.next = nextNode.next;
			}
			temp = temp.next;
		}
		return head;
	}

	static class Node<T> {

		public T data;
		public Node<T> next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

}

package com.jutils.bit;

public class BitUtils {
	public static void swap(int a, int b) {
		// Java 按值传递 所以并不会改变函数外的变量值
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a + " " + b);
	}

	public static void main(String[] args) {
		int a = 2, b = 5;
		swap(a, b);
	}
}

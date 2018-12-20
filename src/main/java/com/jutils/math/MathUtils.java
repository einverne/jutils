package com.jutils.math;

public class MathUtils {

	/**
	 * 这么写是错误的 return i % 2 == 1;  有一般的结果是错误的
	 *
	 * Java 对于 % 的定义，对于所有 int a 和 非零 int b 满足
	 *
	 * (a/b)*b + (a%b) = a
	 *
	 * 该恒等式没有任何问题，当当与 Java 截尾整数整除操作符结合时， 意味着：当取余操作返回一个非零的结果时，与做操作数具有相同的正负符号。 当 i 是负奇数时， i % 2 等于 -1
	 * 而不是 1 。
	 */
	public static boolean isOdd(int i) {
		return (i & 1) != 0;
	}
}



package com.jutils.lambda;

import org.junit.Test;

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
}

public class LambdaTest {

	@Test
	public void testScope() {
		final int num = 1;  // lambda 表达式可以读scope之外的 local final 变量
		Converter<Integer, String> stringConverter = from -> String.valueOf(from + num);
		String convert = stringConverter.convert(2);
		System.out.println(convert);

		int num2 = 1;       // 隐式的定义 final
		Converter<Integer, String> stringConverter2 = from -> String.valueOf(from + num2);
		String convert2 = stringConverter2.convert(2);
		System.out.println(convert2);

	}
}

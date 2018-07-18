package com.jutils.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.util.Arrays;
import org.junit.Test;

/**
 * Created by mi on 18-1-11.
 */
public class GuavaTest {


	@Test
	public void testJoiner() {
		// Joiner 实例是不可变对象，线程安全
		Joiner joiner = Joiner.on(';').skipNulls();
		String ret = joiner.join("one", null, "two", "three");
		System.out.println(ret);

		ret = Joiner.on(",").join(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println(ret);
	}

	@Test
	public void testSplitter() {
		String text = "$a$$b$";
		String[] split = text.split("\\$");
		System.out.println(Arrays.toString(split) + " size: " + split.length);
		// [, a, , b] size: 4
		// 只有尾部的空字符被忽略

		Iterable<String> split1 = Splitter.on("$").trimResults().omitEmptyStrings().split(text);
		for (String s : split1) {
			System.out.println(s);
		}

		String text1 = "[a, b, c, d]";
		Iterable<String> split2 = Splitter.on(",").trimResults().omitEmptyStrings().split(text1);
		for (String s : split2) {
			System.out.println(s);
		}

		// 固定长度分割
		String text2 = "abcabcabc";
		Iterable<String> split3 = Splitter.fixedLength(3).split(text2);
		for (String s : split3) {
			System.out.println(s);
		}

	}

	@Test
	public void testCharMatcher() {
		//一个CharMatcher实例代表着某一类字符，如数字或空白字符
		String string = "11df23sdf234595$%(#$@#@) dfalr34arawef asfdasf ";
		String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); //移除control字符
		String theDigits = CharMatcher.DIGIT.retainFrom(string); //只保留数字字符
		String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
		//去除两端的空格，并把中间的连续空格替换成单个空格
		String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); //用*号替换所有数字
		String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE)
			.retainFrom(string);
		// 只保留数字和小写字母

	}
}

package com.jutils.sequence;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

public class StringHandlerUtils {

	public static void main(String[] args) {
		boolean containSameChar = isContainSameChar("1daabc", "aac1bd");
		System.out.println(containSameChar);

		Assert.assertTrue(strSum("A1CD2B33 ") == 36);
		Assert.assertTrue(strSum("A-1B--2C--D6E") == 7);
	}

	/**
	 * 判断 s1, s2 中包含的单词是否一样，互为变形词，举例 abc 和 acb
	 *
	 * @param s1 String
	 * @param s2 String
	 * @return true if s1 s2 包含字母一致
	 */
	public static boolean isContainSameChar(String s1, String s2) {
		// 只能处理 字母 数字 抛出异常
		if (s1.length() != s2.length()) {
			return false;
		}
		int[] map = new int[256];
		for (char c : s1.toCharArray()) {
			map[c]++;
		}
		for (char c : s2.toCharArray()) {
			if (--map[c] < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 计算字符串 s 中数字片段的和
	 *
	 * <pre>
	 * 比如 A1CD2B33  输出结果为 36
	 * 比如 A-1B--2C--D6E 输出结果为 7
	 * </pre>
	 */
	public static int strSum(String s) {
		if (StringUtils.isBlank(s)) {
			return 0;
		}
		int sum = 0, num = 0, current = 0;
		boolean sign = false; //是否负号
		for (int i = 0; i < s.toCharArray().length; i++) {
			current = s.charAt(i) - '0';
			if (current < 0 || current > 9) {
				sum += num;
				num = 0;
				if (s.charAt(i) == '-') {
					if (i - 1 > -1 && s.charAt(i - 1) == '-') {
						sign = !sign;
					} else {
						sign = true;
					}
				} else {
					sign = false;
				}
			} else {
				num = 10 * num + (sign ? -current : current);
			}
		}
		return sum;
	}
}

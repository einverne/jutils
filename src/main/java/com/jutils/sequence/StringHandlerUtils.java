package com.jutils.sequence;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

public class StringHandlerUtils {

	public static void main(String[] args) {
		boolean containSameChar = isContainSameChar("1daabc", "aac1bd");
		System.out.println(containSameChar);

		Assert.assertTrue(strSum("A1CD2B33 ") == 36);
		Assert.assertTrue(strSum("A-1B--2C--D6E") == 7);

		String rmK = removeContinueK("00A00B000", '0', 2);
		Assert.assertEquals(rmK, "AB000");

		char[] chars = "ABCDE".toCharArray();
		reverse(chars, 1, 3);
		System.out.println(chars);

		char[] chars1 = "In God we trust".toCharArray();
		rotateWord(chars1);
		System.out.println(chars1);

		int i = minLen("adabbca", "acb");
		Assert.assertTrue(i == 3);
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

	/**
	 * 移除字符串中连续k 相连的 c 字符
	 *
	 * <pre>
	 *     比如 A00B000, 0, 2 则输出 AB000
	 * </pre>
	 */
	public static String removeContinueK(String s, char c, int k) {
		if (k < 2 || StringUtils.isBlank(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		char cur;
		int cnt = 0;
		for (int i = 0; i < s.toCharArray().length; i++) {
			if (s.charAt(i) == c) {
				cnt++;
			} else {
				if (cnt > 0 && cnt != k) {
					while (cnt > 0) {
						sb.append(c);
						cnt--;
					}
				} else if (cnt == k) {
					cnt = 0;
				}
				sb.append(s.charAt(i));
			}
		}
		if (cnt > 0 && cnt != k) {
			while (cnt > 0) {
				sb.append(c);
				cnt--;
			}
		}
		return sb.toString();
	}

	/**
	 * 逆转 start end 之间的内容
	 */
	public static void reverse(char[] str, int start, int end) {
		if (start < 0 || end > str.length) {
			return;
		}
		while (start < end) {
			char temp = str[start];
			str[start] = str[end];
			str[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * 翻转句子中的所有单词，比如 "I'm a student" -> "student a I'm" 思路先将句子中所有内容反转，然后在逆序单词
	 */
	public static void rotateWord(char[] chars) {
		if (chars == null || chars.length <= 0) {
			return;
		}
		reverse(chars, 0, chars.length - 1);
		int l = -1, r = -1;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ') {
				if (i == 0 || chars[i - 1] == ' ') {
					l = i;
				} else {
					l = l;
				}
				if (i == chars.length - 1 || chars[i + 1] == ' ') {
					r = i;
				} else {
					r = r;
				}
			}
			if (l != -1 && r != -1) {
				reverse(chars, l, r);
				l = -1;
				r = -1;
			}
		}
	}

	/**
	 * 最小包含字串长度，返回 str1 中包含 str2 所有字符的最小子串长度
	 *
	 * <pre>
	 *     比如 adabbca  acb  -> 输出长度 3
	 * </pre>
	 */
	public static int minLen(String str1, String str2) {
		if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
			return 0;
		}
		int left = 0, right = 0, minLength = Integer.MAX_VALUE;
		int match = str2.length(); // 当前匹配数量
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		int[] map = new int[256];
		for (char c : c2) {
			map[c]++;
		}
		while (right < c1.length) {
			if (--map[c1[right]] >= 0) {
				match--;
			}
			if (match == 0) {
				while (map[c1[left]] < 0) {  // map 中 < 0 表示子串中已经多了
					map[c1[left]]++;
					left++; // 右移 left
				}
				minLength = Math.min(minLength, right - left + 1);
				match++;
				map[c1[left]]++;
				left++;
			}
			right++;
		}
		return minLength == Integer.MAX_VALUE ? 0 : minLength;
	}
}

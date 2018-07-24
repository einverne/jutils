package com.jutils.sequence;

public class StringHandlerUtils {

	public static void main(String[] args){
		boolean containSameChar = isContainSameChar("1daabc", "aac1bd");
		System.out.println(containSameChar);
	}

	/**
	 * 判断 s1, s2 中包含的单词是否一样，互为变形词
	 * @param s1 String
	 * @param s2 String
	 * @return true if s1 s2 包含字母一致
	 */
	public static boolean isContainSameChar(String s1, String s2) {
		// 只能处理 字母 数字 抛出异常
		int[] map = new int[256];
		for (char c : s1.toCharArray()) {
			map[c]++;
		}
		for (char c: s2.toCharArray()) {
			if (--map[c] < 0) {
				return false;
			}
		}
		return true;
	}
}

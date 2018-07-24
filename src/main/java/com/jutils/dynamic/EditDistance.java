package com.jutils.dynamic;

/**
 * 最小编辑距离
 *
 * <pre>
 *     最后一位相同
 *     ED("abc", "adc") = ED("ab", "ad")
 *     最后一位不相同
 *     ED("abc", "add") = ED("ab", "ad") + 编辑成本
 *
 *     其中编辑成本又分为
 *     Insert
 *     Remove
 *     Replace
 *
 *     增加题目难度，如果三种编辑代价都是不一致的
 *     insert 3
 *     remove 2
 *     replace 1
 * </pre>
 */
public class EditDistance {

	private static final int INSERT = 3;
	private static final int REMOVE = 2;
	private static final int REPLACE = 1;

	public static void main(String[] args) {

		String s1 = "abc";
		String s2 = "dadbdc";
		int distance = editDistanceRecursive(s1, s2, s1.length(), s2.length());
		System.out.println(distance);

		distance = editDistanceNonRecursive(s1, s2, s1.length(), s2.length());
		System.out.println(distance);
	}

	/**
	 * 非递归解法
	 */
	static int editDistanceNonRecursive(String str1, String str2, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j <= n; ++j) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + min(
						dp[i - 1][j - 1],
						dp[i - 1][j],
						dp[i][j - 1]
					);
				}
			}
		}
		return dp[m][n];
	}

	/**
	 * 递归解法，对于小规模运算递归可行，但是递归的复杂度是 O(3^m)，重复解了很多子问题
	 */
	static int editDistanceRecursive(String str1, String str2, int m, int n) {
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}
		if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
			return editDistanceRecursive(str1, str2, m - 1, n - 1);
		} else {
			return 1 + min(
				editDistanceRecursive(str1, str2, m - 1, n - 1),     // update
				editDistanceRecursive(str1, str2, m - 1, n),          // remove
				editDistanceRecursive(str1, str2, m, n - 1)            // insert
			);
		}
	}

	static int min(int x, int y, int z) {
		if (x <= y && x <= z) {
			return x;
		}
		if (y <= x && y <= z) {
			return y;
		} else {
			return z;
		}
	}
}

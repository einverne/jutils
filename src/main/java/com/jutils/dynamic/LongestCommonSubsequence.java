package com.jutils.dynamic;

/**
 * 最长公共子序列问题
 *
 */
public class LongestCommonSubsequence {

	public static void main(String[] args) {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";

		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int length = lcsLengthRecursive(str1, str2, str1.length, str2.length);
		System.out.println(length);

		char[] chars = lcsLengthNonRecursive(str1, str2, str1.length, str2.length);
		for (char a : chars){
			System.out.print(a);
		}
	}


	/**
	 * 递归实现，分解问题
	 *
	 * <pre>
	 *     最后一位相同，则化为
	 *      L("ABCD", "BCD") = 1 + L("ABC", "BC")
	 *      如果不相同
	 *      L("ABC", "BCD") = Max( L("AB", "BCD"), L("ABC", "BC") )
	 *
	 * 时间复杂度 O(2^n)
	 * </pre>
	 */
	public static int lcsLengthRecursive(char[] str1, char[] str2, int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		} else if (str1[m - 1] == str2[n - 1]) {
			return 1 + lcsLengthRecursive(str1, str2, m - 1, n - 1);
		} else {
			return Math
				.max(lcsLengthRecursive(str1, str2, m - 1, n),
					lcsLengthRecursive(str1, str2, m, n - 1));
		}
	}

	public static char[] lcsLengthNonRecursive(char[] str1, char[] str2, int m, int n) {
		int[][] L = new int[m + 1][n + 1];
		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j <= n; ++j) {
				if (i == 0 || j == 0) {
					L[i][j] = 0;
				} else if (str1[i - 1] == str2[j - 1]) {
					L[i][j] = L[i - 1][j - 1] + 1;
				} else {
					L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
				}
			}
		}

		int index = L[m][n];
		char[] lcs = new char[index];

		int i = m, j = n;
		while (i > 0 && j > 0) {
			if (str1[i - 1] == str2[j - 1]) {
				lcs[--index] = str1[i - 1];
				i--;
				j--;
			} else if (L[i - 1][j] > L[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		return lcs;
	}
}

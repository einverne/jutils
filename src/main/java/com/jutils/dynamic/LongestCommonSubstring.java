package com.jutils.dynamic;

/**
 * 最长公共子串
 * <pre>
 * Input : X = "GeeksforGeeks", y = "GeeksQuiz"
 * Output : 5
 * The longest common substring is "Geeks" and is of
 * length 5.
 *
 * Input : X = "abcdxyz", y = "xyzabcd"
 * Output : 4
 * The longest common substring is "abcd" and is of
 * length 4.
 *
 * Input : X = "zxabcdezy", y = "yzabcdezx"
 * Output : 6
 * The longest common substring is "abcdez" and is of
 * length 6.
 * </pre>
 *
 * 最长公共后缀 的问题可以优化为如下子问题
 * <pre>
 *     LCSuffix(X, Y, m, n) = if(X[m-1] == Y[n-1]) ? LCSuffix(X, Y, m-1, n-1) + 1 : 0
 * </pre>
 *
 * 最长公共后缀长度就是最长公共子串的长度
 * <pre>
 *     LCSubstr(X, Y, m, n) = Max( LCSuffix(X, Y, i, j)) ( 1<=i<=m, 1<=j<=n)
 * </pre>
 *
 * Time Complexity: O(m*n) A uxiliary Space: O(m*n)
 */
public class LongestCommonSubstring {

	public static void main(String[] args) {
		String s1 = "abcdefxyzd";
		String s2 = "abcdexyzd";

		char[] chars = LCSubstr(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length());
		for (char c : chars) {
			System.out.print(c);
		}
	}

	public static char[] LCSubstr(char[] str1, char[] str2, int m, int n) {
		int[][] lcsuffix = new int[m + 1][n + 1];
		int max = 0, maxi = 0, maxj = 0;

		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j <= n; ++j) {
				if (i == 0 || j == 0) {
					lcsuffix[i][j] = 0;
				} else if (str1[i - 1] == str2[j - 1]) {
					lcsuffix[i][j] = lcsuffix[i - 1][j - 1] + 1;
					if (max < lcsuffix[i][j]) {
						max = lcsuffix[i][j];
						maxi = i;
						maxj = j;
					}
//					max = Math.max(max, lcsuffix[i][j]);
				} else {
					lcsuffix[i][j] = 0;
				}
			}
		}
		char[] lcsubstr = new char[max];
		while (maxi >= 0 && maxj >= 0) {
			if (str1[maxi] == str2[maxj]) {
				lcsubstr[--max] = str1[maxi];
			}
			maxi--;
			maxj--;
		}
		return lcsubstr;
	}
}







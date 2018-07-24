package com.jutils.dynamic;

public class LongestIncreasingSebsequence {

	/**
	 * 首先通过 O(N^2) 找到递增子序列长度数组
	 */
	public static int[] getDp(int[] arr) {
		int[] dp = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			dp[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (arr[i] > arr[j]) { // 当后面数大时 dp 数组递增1
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		return dp;
	}

	/**
	 * 通过递增子序列长度数组找到最长递增子序列
	 *
	 * 首先找到长度中最长的下标，该下标对应的原数组中的值就是最长递增子序列中的最大值
	 */
	public static int[] getLIS(int[] arr, int[] dp) {
		int index = 0;
		int lisLength = Integer.MIN_VALUE;
		for (int i = 0; i < dp.length; ++i) {
			if (dp[i] > lisLength) {
				lisLength = dp[i];
				index = i;
			}
		}
		int[] ret = new int[lisLength];
		// 从 index 起从后往前寻找， 原数组中 arr[i] <
		ret[--lisLength] = arr[index];
		for (int i = index; i >= 0; --i) {
			if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
				ret[--lisLength] = arr[i];
				index = i;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{0, 11, 16, 10, 18, 7, 15};

		int[] dp = getDp(arr);
		for (int i : dp) {
			System.out.print(i + " ");
		}
		System.out.println();
		int[] lis = getLIS(arr, dp);
		for (int i : lis) {
			System.out.print(i + " ");
		}
	}

}

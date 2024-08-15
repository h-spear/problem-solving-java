// https://www.acmicpc.net/problem/1509

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class PalindromeDivision {

	private static final int INF = Integer.MAX_VALUE >> 2;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String string = br.readLine();
		int n = string.length();

		boolean[][] matrix = makePalindromeMatrix(string, n);
		int[] dp = new int[n + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = i; j >= 0; --j) {
				if (matrix[j][i]) {
					dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
				}
			}
		}
		System.out.println(dp[n]);
	    br.close();
	}

	/**
	 * memory 18472 kb
	 * time   128 ms
	 */
	private static boolean[][] makePalindromeMatrix(String string, int n) {
		boolean[][] palindrome = new boolean[n][n];
		for (int i = 0; i < n; ++i)
			palindrome[i][i] = true;

		for (int i = 0; i < n - 1; ++i) {
			if (string.charAt(i) == string.charAt(i + 1))
				palindrome[i][i + 1] = true;
		}

		for (int length = 2; length <= n; ++length) {
			for (int start = 0; start + length - 1 < n; ++start) {
				int end = start + length - 1;
				if (string.charAt(start) == string.charAt(end) && palindrome[start + 1][end - 1])
					palindrome[start][end] = true;
			}
		}
		return palindrome;
	}

	/**
	 * memory 12204 kb
	 * time   1444 ms
	 */
	private static boolean isPalindrome(String string, int left, int right) {
		while (left < right) {
			if (string.charAt(left++) != string.charAt(right--))
				return false;
		}
		return true;
	}
}

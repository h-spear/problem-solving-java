// https://www.acmicpc.net/problem/13398

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class ContinuousSum {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[n][2];
		dp[0][0] = arr[0];

		int answer = arr[0];			// ***
		for (int i = 1; i < n; ++i) {
			dp[i][0] = max(dp[i - 1][0] + arr[i], arr[i]);	// ***
			dp[i][1] = max(dp[i - 1][1] + arr[i], dp[i - 1][0]);
			answer = max(answer, dp[i][0], dp[i][1]);
		}
		System.out.println(answer);
	    br.close();
	}

	private static int max(int... nums) {
		int res = Integer.MIN_VALUE;
		for (int num: nums)
			res = Math.max(res, num);
		return res;
	}
}

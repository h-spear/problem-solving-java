// https://www.acmicpc.net/problem/7570

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class LineUp {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 일반 LIS 풀이로는 오답
		int[] dp = new int[N + 1];
		for (int i = 0; i < N; ++i) {
			dp[nums[i]] = dp[nums[i] - 1] + 1;
		}
		System.out.println(N - max(dp, N + 1));
	    br.close();
	}

	private static int max(int[] array, int n) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i) {
			if (max < array[i])
				max = array[i];
		}
		return max;
	}
}

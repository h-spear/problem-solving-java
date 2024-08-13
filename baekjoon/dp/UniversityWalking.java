// https://www.acmicpc.net/problem/12849

package baekjoon.dp;

import java.io.*;
import java.util.Arrays;

public class UniversityWalking {

	private static final int MOD = 1_000_000_007;
	private static final int[][] graph = {
		{1, 2},
		{0, 2, 3},
		{0, 1, 3, 5},
		{1, 2, 4, 5},
		{3, 5, 6},
		{2, 3, 4, 7},
		{4, 7},
		{5, 6}
	};
	private static final int[][] dp = new int[8][100001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int D = Integer.parseInt(br.readLine());
		fill2D(dp, -1);
		System.out.println(solve(D, 0));
		br.close();
	}

	private static int solve(int depth, int x) {
		if (depth == 0) {
			if (x == 0)
				return 1;
			return 0;
		}
		if (dp[x][depth] != -1)
			return dp[x][depth];
		int temp = 0;
		for (int next: graph[x]) {
			temp = (temp + solve(depth - 1, next)) % MOD;
		}
		return dp[x][depth] = temp;
	}

	private static void fill2D(int[][] array, int value) {
		for (int[] x: array) {
			Arrays.fill(x, value);
		}
	}
}
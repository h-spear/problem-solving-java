// https://www.acmicpc.net/problem/1562

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class StairNumber {

	private static final int P = 1_000_000_000;
	private static final int fullVisit = (1 << 10) - 1;
	private static final long[][][] dp = new long[101][10][1 << 10];
	private static int N;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i <= N; ++i) {
			for (int j = 0; j < 10; ++j)
				Arrays.fill(dp[i][j], -1);
		}

		long answer = 0;
		for (int i = 1; i < 10; ++i) {
			answer += dfs(i, 1, 1 << i);
			answer %= P;
		}
		System.out.println(answer);
	    br.close();
	}

	private static long dfs(int x, int depth, int bm) {
		if (dp[depth][x][bm] != -1)
			return dp[depth][x][bm];

		if (depth == N)
			return bm == fullVisit ? 1 : 0;

		long temp = 0;
		if (x > 0)
			temp = (temp + dfs(x - 1, depth + 1, bm | (1 << (x - 1)))) % P;
		if (x < 9)
			temp = (temp + dfs(x + 1, depth + 1, bm | (1 << (x + 1)))) % P;
		return dp[depth][x][bm] = temp;
	}
}

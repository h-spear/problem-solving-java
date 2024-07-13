// https://www.acmicpc.net/problem/2240

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class PlumTree {

	private static int T, W;
	private static int[] plum;
	private static final int[][][] dp = new int[1001][2][31];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		plum = new int[T];
		for (int i = 0; i < T; ++i) {
			plum[i] = Integer.parseInt(br.readLine()) - 1;
		}

		for (int i = 0; i <= T; ++i) {
			for (int j = 0; j < 2; ++j)
				Arrays.fill(dp[i][j], -1);
		}
		System.out.println(dfs(0, 0, 0));
	    br.close();
	}
	
	private static int dfs(int location, int depth, int move) {
		if (move > W)
			return Integer.MIN_VALUE;
		if (depth == T)
			return 0;
		if (dp[depth][location][move] != -1)
			return dp[depth][location][move];
		return dp[depth][location][move] = Math.max(
			dfs(location, depth + 1, move) + (plum[depth] == location ? 1 : 0),
			dfs(1 - location, depth + 1, move + 1) + (plum[depth] != location ? 1 : 0)
		);
	}
}

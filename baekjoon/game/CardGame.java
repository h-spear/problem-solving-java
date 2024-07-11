// https://www.acmicpc.net/problem/11062

package baekjoon.game;

import java.io.*;
import java.util.*;

public class CardGame {

	private static int N;
	private static final int[] card = new int[1001];
	private static final int[][] dp = new int[1001][1001];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				card[i] = Integer.parseInt(st.nextToken());
			}
			int res = dfs(0, N - 1, 0);
			sb.append(res).append("\n");
			for (int i = 0; i < N; ++i) {
				Arrays.fill(dp[i], 0, N, 0);
			}
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int dfs(int s, int e, int turn) {
		if (s > e)
			return 0;
		if (dp[s][e] > 0)
			return dp[s][e];
		if (turn == 0) {
			return dp[s][e] = Math.max(
				dfs(s + 1, e, 1 - turn) + card[s],
				dfs(s, e - 1, 1 - turn) + card[e]
			);
		} else {
			return dp[s][e] = Math.min(
				dfs(s + 1, e, 1 - turn),
				dfs(s, e - 1, 1 - turn)
			);
		}
	}
}

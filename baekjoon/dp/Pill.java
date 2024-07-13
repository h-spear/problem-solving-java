// https://www.acmicpc.net/problem/4811

package baekjoon.dp;

import java.io.*;

public class Pill {

	private static final long[][] dp = new long[31][31];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N;
		StringBuilder sb = new StringBuilder();
		while ((N = Integer.parseInt(br.readLine())) > 0) {
			sb.append(dfs(N, 0)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static long dfs(int one, int half) {
		if (one + half == 0)
			return 1;
		if (dp[one][half] > 0)
			return dp[one][half];

		long temp = 0;
		if (one > 0)
			temp += dfs(one - 1, half + 1);
		if (half > 0)
			temp += dfs(one, half - 1);
		return dp[one][half] = temp;
	}
}

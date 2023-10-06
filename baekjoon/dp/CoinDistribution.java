// https://www.acmicpc.net/problem/1943

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class CoinDistribution {

	private static int N;
	private static int[] dp = new int[50050];
	private static int[] coin = new int[101];
	private static int[] uses = new int[101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = 3;
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			Arrays.fill(dp, 0, 50050, -1);
			dp[0] = 1;

			int total = 0;
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				coin[i] = Integer.parseInt(st.nextToken());
				uses[i] = Integer.parseInt(st.nextToken());
				total += coin[i] * uses[i];
			}

			if ((total & 1) == 1) {
				sb.append("0\n");
			} else {
				sb.append(dp(total >> 1)).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int dp(int money) {
		if (dp[money] != -1)
			return dp[money];

		dp[money] = 0;
		for (int i = 0; i < N; ++i) {
			if (uses[i] > 0 && money - coin[i] >= 0) {
				--uses[i];
				dp[money] |= dp(money - coin[i]);
				++uses[i];
			}
		}
		return dp[money];
	}
}

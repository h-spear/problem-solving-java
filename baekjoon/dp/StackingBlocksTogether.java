// https://www.acmicpc.net/problem/18427

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class StackingBlocksTogether {

	private static final int P = 10007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N, M, H;
		int[][] block, dp;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		block = new int[N + 1][];
		block[0] = new int[M + 1];
		dp = new int[N + 1][H + 1];

		for (int i = 1, j; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			j = 1;
			block[i] = new int[st.countTokens() + 1];
			while (st.hasMoreTokens()) {
				block[i][j++] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0] = 1;
		for (int i = 1; i <= N; ++i) {
			for (int j = 0; j <= H; ++j) {
				for (int k = 0, length = block[i].length; k < length; ++k) {
					if (j >= block[i][k])
						dp[i][j] = (dp[i][j] + dp[i - 1][j - block[i][k]]) % P;
				}
			}
		}
		System.out.println(dp[N][H]);
		br.close();
	}
}

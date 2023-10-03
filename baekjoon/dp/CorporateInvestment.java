// https://www.acmicpc.net/problem/2662

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class CorporateInvestment {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][M + 1];
		int[][] dp = new int[M + 1][N + 1];

		for (int i = 0, money; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			money = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= M; ++j) {
				arr[money][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= M; ++i) {
			for (int j = 0; j <= N; ++j) {
				for (int k = 0; k <= j; ++k) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k] + arr[k][i]);
				}
			}
		}

		int[] path = tracking(dp, arr);

		StringBuilder sb = new StringBuilder();
		sb.append(dp[M][N]).append("\n");
		for (int i = 1; i <= M; ++i) {
			sb.append(path[i]).append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int[] tracking(int[][] dp, int[][] arr) {
		int i = dp.length - 1;
		int j = dp[0].length - 1;
		int[] path = new int[dp.length];
		while (i > 0 && j >= 0) {
			for (int k = 0; k <= j; ++k) {
				if (dp[i - 1][j - k] + arr[k][i] == dp[i][j]) {
					path[i] = k;
					--i;
					j -= k;
					break;
				}
			}
		}
		return path;
	}
}

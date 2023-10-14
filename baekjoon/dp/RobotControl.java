// https://www.acmicpc.net/problem/2169

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class RobotControl {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N][M];
		int[][] arr = new int[N][M];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0] = arr[0][0];
		for (int j = 1; j < M; ++j)
			dp[0][j] = arr[0][j] + dp[0][j - 1];

		int[][] temp = new int[2][M];
		for (int i = 1; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				temp[0][j] = dp[i - 1][j] + arr[i][j];
				temp[1][j] = dp[i - 1][j] + arr[i][j];
			}

			for (int j = 1; j < M; ++j) {
				temp[0][j] = Math.max(temp[0][j], arr[i][j] + temp[0][j - 1]);
			}

			for (int j = M - 2; j >= 0; --j) {
				temp[1][j] = Math.max(temp[1][j], arr[i][j] + temp[1][j + 1]);
			}
			for (int j = 0; j < M; ++j) {
				dp[i][j] = Math.max(temp[0][j], temp[1][j]);
			}
		}
		System.out.println(dp[N - 1][M - 1]);
		br.close();
	}
}
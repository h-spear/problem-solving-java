// https://www.acmicpc.net/problem/2629

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class TwoArmScale {

	private static boolean[][] dp = new boolean[31][40001];
	private static int[] weight = new int[31];
	private static int N;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		dp[0][0] = true;
		for (int i = 1; i <= N; ++i) {
			for (int j = 0; j <= 40000; ++j) {
				for (int k: new int[] {j + weight[i], Math.abs(j - weight[i]), j}) {
					if (k < 0 || k > 40000)
						continue;
					dp[i][j] |= dp[i - 1][k];
				}
			}
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0, ball; i < M; ++i) {
			ball = Integer.parseInt(st.nextToken());
			bw.write(dp[N][ball] ? "Y " : "N ");
		}
	    bw.flush();
	    bw.close();
	    br.close();
	}
}

class TwoArmScale_Recursive {

	private static boolean[][] dp = new boolean[31][40001];
	private static int[] weight = new int[31];
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		dp(0, 0);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0, ball; i < M; ++i) {
			ball = Integer.parseInt(st.nextToken());
			bw.write(dp[N][ball] ? "Y " : "N ");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dp(int idx, int w) {
		if (idx > N || dp[idx][w])
			return;
		dp[idx][w] = true;
		dp(idx + 1, w + weight[idx]);
		dp(idx + 1, Math.abs(w - weight[idx]));
		dp(idx + 1, w);
	}
}

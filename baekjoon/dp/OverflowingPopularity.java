// https://www.acmicpc.net/problem/17258

package baekjoon.dp;

import java.io.*;
import java.util.*;

class OverflowingPopularity {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[] counter = new int[N + 1];

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Math.min(Integer.parseInt(st.nextToken()) - 1, N);
			++counter[a];
			--counter[b];
		}

		for (int i = 0; i < N; ++i) {
			counter[i + 1] += counter[i];
		}

		int answer = 0;
		List<int[]> ranges = new ArrayList<>();
		for (int i = 0, j = 0; i < N; i = j) {
			if (counter[i] >= T) {
				++answer;
				++j;
				continue;
			}

			while (j < N && counter[j] < T)
				++j;
			ranges.add(new int[]{i, j});
		}

		int countOfRange = ranges.size();
		int[][] arr = generateValueArray(ranges, countOfRange, counter, T, K);

		// knapsack
		int[][] dp = new int[countOfRange + 1][K + 1];
		for (int i = 1; i <= countOfRange; ++i) {
			for (int j = 0; j <= K; ++j) {
				for (int k = 0; k <= j; ++k) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k] + arr[i][k]);
				}
			}
		}
		System.out.println(answer + dp[countOfRange][K]);
		br.close();
	}

	private static int[][] generateValueArray(List<int[]> ranges, int countOfRange, int[] counter, int t, int k) {
		int[][] arr = new int[countOfRange + 1][k + 1];
		for (int i = 1; i <= countOfRange; ++i) {
			int[] range = ranges.get(i - 1);
			int s = range[0];
			int e = range[1];
			for (int j = s; j < e; ++j) {
				if (t - counter[j] <= k)
					++arr[i][t - counter[j]];
			}
			for (int j = 0; j < k; ++j) {
				arr[i][j + 1] += arr[i][j];
			}
		}
		return arr;
	}
}

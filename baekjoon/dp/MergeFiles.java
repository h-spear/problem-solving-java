// https://www.acmicpc.net/problem/11066

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class MergeFiles {

	private static final int[][] cache = new int[502][502];
	private static final int[] prefixSum = new int[502];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			prefixSum[0] = 0;
			for (int i = 1; i <= K; ++i) {
				prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
			}
			sb.append(dfs(1, K)).append("\n");

			for (int i = 0; i <= K; ++i) {
				Arrays.fill(cache[i], 0, K + 1, 0);
			}
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int dfs(int s, int e) {
		if (s >= e)
			return 0;
		if (cache[s][e] != 0)
			return cache[s][e];

		int temp = Integer.MAX_VALUE;
		for (int i = s; i < e; ++i) {
			temp = Math.min(temp, dfs(s, i) + dfs(i + 1, e) + prefixSum[e] - prefixSum[s - 1]);
		}
		return cache[s][e] = temp;
	}
}

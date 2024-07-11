// https://www.acmicpc.net/problem/24512

package baekjoon.bruteforce;

import java.io.*;
import java.util.*;

public class BottleneckTravellingSalesmanProblem_Small {

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] cost = new int[N + 1][N + 1];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cost[u][v] = c;
		}

		int[] perm = new int[N];
		for (int i = 0; i < N; ++i)
			perm[i] = i + 1;

		int minCost = INF;
		int[] path = null;
		do {
			int res = simulation(N, cost, perm);
			if (minCost > res) {
				minCost = res;
				path = perm.clone();
			}
		} while (nextPermutation(perm, N));

		StringBuilder sb = new StringBuilder();
		sb.append(minCost == INF ? -1 : minCost).append("\n");
		if (path != null) {
			for (int x: path)
				sb.append(x).append(" ");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int simulation(int n, int[][] cost, int[] order) {
		int max = -INF;
		for (int i = 0; i < n; ++i) {
			int o1 = order[i];
			int o2 = i < n - 1 ? order[i + 1] : order[0];
			if (cost[o1][o2] == 0)
				return INF;
			max = Math.max(max, cost[o1][o2]);
		}
		return max;
	}

	private static boolean nextPermutation(int[] perm, int n) {
		int i = n - 1;
		while (i > 0 && perm[i - 1] >= perm[i])
			--i;

		if (i == 0)
			return false;

		int j = n - 1;
		while (perm[i - 1] >= perm[j])
			--j;

		swap(perm, i - 1, j);

		int k = n - 1;
		while (i < k)
			swap(perm, i++, k--);
		return true;
	}

	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}

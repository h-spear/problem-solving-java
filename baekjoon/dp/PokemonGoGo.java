// https://www.acmicpc.net/problem/16385

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class PokemonGoGo {

	private static final int INF = Integer.MAX_VALUE >> 2;
	private static int n;
	private static int[] poke = new int[22];
	private static int[][] w;
	private static int[][] dp;
	private static int fullVisit;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		Map<String, Integer> nameToIndexMapper = new HashMap<>();

		n = Integer.parseInt(br.readLine());
		w = new int[n + 1][n + 1];

		int[][] coords = new int[n + 1][];
		coords[0] = new int[] {0, 0};
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			if (!nameToIndexMapper.containsKey(name))
				nameToIndexMapper.put(name, nameToIndexMapper.size());
			coords[i] = new int[] {r, c};
			poke[i] = nameToIndexMapper.get(name);
		}

		fullVisit = (1 << nameToIndexMapper.size()) - 1;
		dp = new int[n + 1][1 << nameToIndexMapper.size()];
		fill2D(dp, -1);

		for (int i = 0; i <= n; ++i) {
			for (int j = i + 1; j <= n; ++j) {
				int d = Math.abs(coords[i][0] - coords[j][0]) + Math.abs(coords[i][1] - coords[j][1]);
				w[i][j] = w[j][i] = d;
			}
		}

		System.out.println(dfs(0, 0));
	    br.close();
	}

	private static int dfs(int x, int visit) {
		if (visit == fullVisit)
			return w[x][0];
		if (dp[x][visit] != -1)
			return dp[x][visit];

		int temp = INF;
		for (int i = 1; i <= n; ++i) {
			int j = poke[i];
			if ((visit & (1 << j)) > 0)
				continue;
			temp = Math.min(temp, dfs(i, visit | (1 << j)) + w[x][i]);
		}
		return dp[x][visit] = temp;
	}

	private static void fill2D(int[][] array, int value) {
		for (int[] row: array) {
			Arrays.fill(row, value);
		}
	}
}

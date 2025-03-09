// https://www.acmicpc.net/problem/9525

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class PlacingRooks {

	private static int n, m;
	private static List<List<Integer>> graph;
	private static int[] visited;
	private static int[] match;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(solve());
	}

	private static int solve() {
		int count = 0;
		for (int i = 1; i <= n; ++i) {
			++call; if (dfs(i))
				++count;
		}
		return count;
	}

	private static boolean dfs(int x) {
		if (visited[x] == call) {
			return false;
		}
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == 0 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		char[][] chess = new char[N][N];
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < N; ++j) {
				chess[i][j] = line.charAt(j);
			}
		}
		br.close();

		int group1 = 0;
		int group2 = 0;
		int[][] indexer1 = new int[N][N];
		int[][] indexer2 = new int[N][N];
		for (int i = 0; i < N; ++i) {
			boolean flag = false;
			for (int j = 0; j < N; ++j) {
				if (chess[i][j] == '.') {
					if (!flag) {
						flag = true;
						++group1;
					}
					indexer1[i][j] = group1;
				} else {
					flag = false;
				}
			}

			flag = false;
			for (int j = 0; j < N; ++j) {
				if (chess[j][i] == '.') {
					if (!flag) {
						flag = true;
						++group2;
					}
					indexer2[j][i] = group2;
				} else {
					flag = false;
				}
			}
		}

		n = group1;
		m = group2;
		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}
		visited = new int[n + 1];
		match = new int[m + 1];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (chess[i][j] == '.')
					graph.get(indexer1[i][j]).add(indexer2[i][j]);
			}
		}
		return;
	}
}

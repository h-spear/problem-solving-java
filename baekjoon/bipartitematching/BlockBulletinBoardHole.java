// https://www.acmicpc.net/problem/2414

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class BlockBulletinBoardHole {

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
			++call;
			if (dfs(i))
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
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] board = new char[N][M];
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				board[i][j] = line.charAt(j);
			}
		}

		int group1 = 0;
		int group2 = 0;
		int[][] indexer1 = new int[N][M];
		int[][] indexer2 = new int[N][M];
		for (int i = 0, number = 0; i < N; ++i) {
			boolean flag = false;
			for (int j = 0; j < M; ++j) {
				if (board[i][j] == '*') {
					if (!flag) {
						++group1;
						++number;
						flag = true;
					}
					indexer1[i][j] = number;
				} else {
					flag = false;
				}
			}
		}

		for (int j = 0, number = 0; j < M; ++j) {
			boolean flag = false;
			for (int i = 0; i < N; ++i) {
				if (board[i][j] == '*') {
					if (!flag) {
						++group2;
						++number;
						flag = true;
					}
					indexer2[i][j] = number;
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
		Arrays.fill(match, 0);

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (board[i][j] == '*') {
					graph.get(indexer1[i][j]).add(indexer2[i][j]);
				}
			}
		}
		br.close();
	}
}

package baekjoon.backtracking;// https://www.acmicpc.net/problem/12094

import java.io.*;
import java.util.*;

public class _2048_Hard {

	private static int N, answer;
	private static Queue<Integer> gQueue;
	private static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		gQueue = new ArrayDeque<>(431);
		dp = new int[11];

		N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = findMaximum(graph);
		dfs(graph, 0);
		System.out.println(answer);
		br.close();
	}

	private static void dfs(int[][] graph, int depth) {
		int max = findMaximum(graph);
		if (max <= dp[depth])
			return;
		if (depth >= 10) {
			answer = Math.max(answer, max);
			int v = max;
			for (int i = depth; i >= 0; --i) {
				dp[depth] = v;
				v >>= 1;
			}
			return;
		}
		int[][] moved;
		for (int i = 0; i < 4; ++i, graph = rotateDegree90(graph)) {
			moved = move(graph);
			if (isSame(graph, moved))
				continue;
			dfs(moved, depth + 1);
		}
	}

	private static boolean isSame(int[][] graph1, int[][] graph2) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (graph1[i][j] != graph2[i][j])
					return false;
			}
		}
		return true;
	}

	private static int findMaximum(int[][] graph) {
		int res = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				res = Math.max(res, graph[i][j]);
			}
		}
		return res;
	}

	private static int[][] move(int[][] graph) {
		Queue<Integer> queue = gQueue;
		int[][] copied = deepCopy(graph);
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (copied[i][j] > 0) {
					queue.add(copied[i][j]);
					copied[i][j] = 0;
				}
			}
			for (int j = 0; !queue.isEmpty() && j < N; ++j) {
				copied[i][j] = queue.poll();
				if (!queue.isEmpty() && copied[i][j] == queue.peek()) {
					copied[i][j] <<= 1;
					queue.poll();
				}
			}
		}
		return copied;
	}

	private static int[][] deepCopy(int[][] graph) {
		int[][] copied = new int[N][N];
		for (int i = 0; i < N; ++i) {
			System.arraycopy(graph[i], 0, copied[i], 0, N);
		}
		return copied;
	}

	private static int[][] rotateDegree90(int[][] graph) {
		int[][] rotated = new int[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				rotated[i][j] = graph[N - j - 1][i];
			}
		}
		return rotated;
	}
}
// https://www.acmicpc.net/problem/30893

package baekjoon.game;

import java.io.*;
import java.util.*;

public class TreeGame {

	private static int N;
	private static List<List<Integer>> graph;
	private static boolean[] visited;
	private static int[] prev;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		prev = new int[N + 1];
		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		dfs(E);
		System.out.println(simulate(S, 0, E) == 1 ? "First" : "Second");
		br.close();
	}

	private static void dfs(int x) {
		visited[x] = true;
		for (int next: graph.get(x)) {
			if (visited[next])
				continue;
			prev[next] = x;
			dfs(next);
		}
	}

	private static int simulate(int x, int turn, int target) {
		if (x == target)
			return 1;
		if (turn == 0) {
			return simulate(prev[x], 1 - turn, target);
		} else {
			if (graph.get(x).size() > 2)
				return 2;
			for (int next: graph.get(x)) {
				if (prev[next] == x)
					continue;
				return simulate(next, 1 - turn, target);
			}
			throw new IllegalStateException("exception");
		}
	}
}

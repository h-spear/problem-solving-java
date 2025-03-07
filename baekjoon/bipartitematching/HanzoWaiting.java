// https://www.acmicpc.net/problem/14433

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class HanzoWaiting {

	private static int N, M;
	private static List<List<Integer>> graph;
	private static List<List<Integer>> graph2;
	private static int[] visited;
	private static int[] match;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		input();
		int ret = solve();
		System.out.println(ret < 0 ? "네 다음 힐딱이" : "그만 알아보자");
	}

	private static int solve() {
		int match1 = 0;
		int match2 = 0;
		for (int i = 1; i <= N; ++i) {
			++call;
			if (dfs(graph, i))
				++match1;
		}

		fill(match, 0);
		for (int i = 1; i <= N; ++i) {
			++call;
			if (dfs(graph2, i))
				++match2;
		}
		return match1 - match2;
	}

	private static boolean dfs(List<List<Integer>> graph, int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;
		for (int y: graph.get(x)) {
			if (match[y] == 0 || dfs(graph, match[y])) {
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K1 = Integer.parseInt(st.nextToken());
		int K2 = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N + 1);
		graph2 = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
			graph2.add(new ArrayList<>());
		}
		visited = new int[N + 1];
		match = new int[M + 1];

		while (K1-- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			graph.get(i).add(j);
		}

		while (K2 -- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			graph2.get(i).add(j);
		}
		br.close();
	}

	private static void fill(int[] arr, int value) {
		Arrays.fill(arr, value);
	}
}

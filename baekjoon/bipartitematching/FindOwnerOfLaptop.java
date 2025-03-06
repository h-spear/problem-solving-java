// https://www.acmicpc.net/problem/1298

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class FindOwnerOfLaptop {

	private static int N;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}
		visited = new int[N + 1];
		match = new int[N + 1];

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}

		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			++call;
			if (dfs(i)) ++answer;
		}
		System.out.println(answer);
		br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call) {
			return false;
		}
		visited[x] = call;
		for (int notebook: graph.get(x)) {
			if (match[notebook] == 0 || dfs(match[notebook])) {
				match[notebook] = x;
				return true;
			}
		}
		return false;
	}
}

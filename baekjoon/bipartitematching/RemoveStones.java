// https://www.acmicpc.net/problem/1867

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class RemoveStones {

	private static int n;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		match = new int[n + 1];
		visited = new int[n + 1];
		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}

		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(r).add(c);
		}

		int answer = 0;
		for (int i = 1; i <= n; ++i) {
			++call;
			if (dfs(i))
				++answer;
		}
		System.out.println(answer);
	    br.close();
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
}

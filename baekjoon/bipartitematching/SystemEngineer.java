// https://www.acmicpc.net/problem/3736

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class SystemEngineer {

	private static int n;
	private static List<List<Integer>> graph;
	private static int[] match = new int[20202];
	private static int[] visited = new int[20202];
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (inputAndInitialize(br)) {
			sb.append(solve()).append('\n');
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int solve() {
		int count = 0;
		for (int i = 0; i < n; ++i) {
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
			if (match[y] == -1 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static boolean inputAndInitialize(BufferedReader br) throws IOException {
		StringTokenizer st;
		String line = br.readLine();

		if (line == null) {
			return false;
		}

		n = Integer.parseInt(line);
		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}
		call = 0;
		Arrays.fill(visited, 0, n, 0);
		Arrays.fill(match, n, n << 1,-1);

		for (int i = 0; i < n; ++i) {
			String input = br.readLine();
			int x = Integer.parseInt(input.substring(0, input.indexOf(':')));
			int m = Integer.parseInt(input.substring(input.indexOf('(') + 1, input.indexOf(')')));
			String list = input.substring(input.indexOf(')') + 2);
			st = new StringTokenizer(list);

			for (int j = 0; j < m; ++j) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		return true;
	}
}

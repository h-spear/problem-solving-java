// https://www.acmicpc.net/problem/9577

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class Torrent {

	private static final int MAX_TIME = 123;
	private static final int MAX_FRAGMENT = 123;
	private static int n;
	private static List<List<Integer>> graph;
	private static int[] match = new int[MAX_FRAGMENT];
	private static int[] visited = new int[MAX_TIME];
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			inputAndPreprocessing(br);
			sb.append(solve()).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int solve() {
		for (int i = 0; i < MAX_TIME; ++i) {
			++call; dfs(i);
		}

		int ret = 0;
		for (int i = 1; i <= n; ++i) {
			if (match[i] == -1)
				return -1;
			ret = Math.max(ret, match[i] + 1);
		}
		return ret;
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == -1 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static void inputAndPreprocessing(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(MAX_TIME);
		for (int i = 0; i < MAX_TIME; ++i) {
			graph.add(new ArrayList<>());
		}
		Arrays.fill(match, -1);
		Arrays.fill(visited, 0);
		call = 0;

		for (int it = 0; it < m; ++it) {
			st = new StringTokenizer(br.readLine());
			int t1 = Integer.parseInt(st.nextToken());
			int t2 = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			for (int i = 0; i < a; ++i) {
				int q = Integer.parseInt(st.nextToken());
				for (int j = t1; j < t2; ++j) {
					graph.get(j).add(q);
				}
			}
		}
	}
}

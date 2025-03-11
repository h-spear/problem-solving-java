// https://www.acmicpc.net/problem/12843

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class MultiMajor {

	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}
		visited = new int[n + 1];
		match = new int[n + 1];

		int[] kind = new int[n + 1];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			kind[num] = c == 'c' ? 1 : 0;
		}
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (kind[a] == 0) {
				int temp = a;
				a = b;
				b = temp;
			}
			graph.get(a).add(b);
		}

		int count = 0;
		for (int i = 1; i <= n; ++i) {
			++call;
			if (dfs(i))
				++count;
		}
		System.out.println(n - count);
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

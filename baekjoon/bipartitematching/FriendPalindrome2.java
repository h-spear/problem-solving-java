// https://www.acmicpc.net/problem/15271

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class FriendPalindrome2 {

	private static int N;
	private static List<List<Integer>> graph;
	private static int[] visited;
	private static int[] match;
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
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if ((u & 1) == (v & 1))
				continue;
			if ((u & 1) == 0) {
				int temp = u;
				u = v;
				v = temp;
			}
			graph.get(u).add(v);
		}

		int count = 0;
		for (int i = 1; i <= N; ++i) {
			++call;
			if (dfs(i))
				count += 2;
		}
		System.out.println(Math.min(N, count + 1));
	    br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
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

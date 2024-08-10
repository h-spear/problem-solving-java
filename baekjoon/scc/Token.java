// https://www.acmicpc.net/problem/25488

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class Token {

	private static int id, sz;
	private static int[] visited;
	private static boolean[] finished;
	private static int[] sccId;
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Deque<Integer> stack = new ArrayDeque<>();
		visited = new int[N + 1];
		finished = new boolean[N + 1];
		sccId = new int[N + 1];

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
		}

		int P = Integer.parseInt(br.readLine());
		if (P == 0) {
			System.out.println("YES");
		} else {
			int[] a = new int[P];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < P; ++i) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			int[] b = new int[P];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < P; ++i) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; ++i) {
				if (!finished[i])
					dfs(i, stack);
			}

			int[] counterA = new int[sz];
			int[] counterB = new int[sz];

			for (int i = 0; i < P; ++i) {
				++counterA[sccId[a[i]]];
				++counterB[sccId[b[i]]];
			}

			boolean possible = true;
			for (int i = 0; i < sz; ++i) {
				if (counterA[i] != counterB[i]) {
					possible = false;
					break;
				}
			}
			System.out.println(possible ? "YES" : "NO");
		}
	    br.close();
	}

	private static int dfs(int x, Deque<Integer> stack) {
		int parent = visited[x] = ++id;
		stack.push(x);

		for (int next: graph.get(x)) {
			if (visited[next] == 0)
				parent = Math.min(parent, dfs(next, stack));
			else if (!finished[next])
				parent = Math.min(parent, visited[next]);
		}

		if (parent == visited[x]) {
			while (!stack.isEmpty()) {
				int node = stack.pop();
				finished[node] = true;
				sccId[node] = sz;
				if (node == x)
					break;
			}
			++sz;
		}
		return parent;
	}
}

// https://www.acmicpc.net/problem/27503

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class YogaClass {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static int N;
	private static List<List<Integer>> graph;
	private static int[] visited;
	private static boolean[] finished;
	private static int id, sz;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int nodes = (N << 1) + 1;

		finished = new boolean[nodes];
		visited = new int[nodes];
		Arrays.fill(visited, -1);

		graph = new ArrayList<>(nodes);
		for (int i = 0; i < nodes; ++i) {
			graph.add(new ArrayList<>());
		}

		int[] A = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] replaceable = new boolean[N + 1];
		for (int i = 0; i < C; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(notX(u)).add(v);
			graph.get(notX(v)).add(u);
			replaceable[u] = true;
			replaceable[v] = true;	// ***
		}

		for (int x: A) {
			if (!replaceable[x])
				graph.get(notX(x)).add(x);
		}

		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(notX(b));
			graph.get(b).add(notX(a));
		}

		for (int i = 1; i < nodes; ++i) {
			if (!finished[i])
				dfs(i);
		}

		boolean valid = true;
		for (int i = 1; i <= N; ++i) {
			if (visited[i] == visited[notX(i)]) {
				valid = false;
				break;
			}
		}

		System.out.println(valid ? "YES" : "NO");
	    br.close();
	}

	private static int dfs(int x) {
		int parent = visited[x] = ++id;
		stack.push(x);

		for (int next: graph.get(x)) {
			if (visited[next] == -1)
				parent = Math.min(parent, dfs(next));
			else if (!finished[next])
				parent = Math.min(parent, visited[next]);
		}

		if (parent == visited[x]) {
			while (!stack.isEmpty()) {
				int node = stack.pop();
				visited[node] = sz;
				finished[node] = true;
				if (node == x)
					break;
			}
			++sz;
		}
		return parent;
	}

	private static int notX(int x) {
		if (x <= N)
			return x + N;
		else return x - N;
	}
}

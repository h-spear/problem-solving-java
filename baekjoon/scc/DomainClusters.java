// https://www.acmicpc.net/problem/13232

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class DomainClusters {

	private static List<List<Integer>> graph;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] visited = new int[5050];
	private static final boolean[] finished = new boolean[5050];
	private static int id;
	private static int biggest;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int D = Integer.parseInt(br.readLine());
		int L = Integer.parseInt(br.readLine());

		graph = new ArrayList<>(D + 1);
		for (int i = 0; i <= D; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
		}

		for (int i = 1; i <= D; ++i) {
			if (!finished[i])
				dfs(i);
		}
		System.out.println(biggest);
	    br.close();
	}

	private static int dfs(int x) {
		int parent = visited[x] = ++id;
		stack.push(x);

		for (int next : graph.get(x)) {
			if (visited[next] == 0)
				parent = Math.min(parent, dfs(next));
			else if (!finished[next])
				parent = Math.min(parent, visited[next]);
		}

		if (parent == visited[x]) {
			int size = 0;
			while (!stack.isEmpty()) {
				int node = stack.pop();
				finished[node] = true;
				++size;
				if (node == x)
					break;
			}
			biggest = Math.max(biggest, size);
		}
		return parent;
	}
}

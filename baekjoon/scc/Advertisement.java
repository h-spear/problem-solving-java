// https://www.acmicpc.net/problem/24131

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class Advertisement {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static int[] visited;
	private static boolean[] finished;
	private static int[] sccId;
	private static int id;
	private static List<List<Integer>> scc;
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i)
			graph.add(new ArrayList<>());
		scc = new ArrayList<>();
		visited = new int[n + 1];
		finished = new boolean[n + 1];
		sccId = new int[n + 1];

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}

		for (int i = 1; i <= n; ++i) {
			if (!finished[i])
				dfs(i);
		}

		int sz = scc.size();
		int[] sccInDegree = new int[sz];
		for (int i = 1; i <= n; ++i) {
			for (int j: graph.get(i)) {
				if (sccId[i] != sccId[j])
					++sccInDegree[sccId[j]];
			}
		}

		int answer = 0;
		for (int i = 0; i < sz; ++i) {
			if (sccInDegree[i] == 0)
				++answer;
		}
		System.out.println(answer);
	    br.close();
	}

	private static int dfs(int x) {
		int parent = visited[x] = ++id;
		stack.push(x);

		for (int next: graph.get(x)) {
			if (visited[next] == 0)
				parent = Math.min(parent, dfs(next));
			else if (!finished[next])
				parent = Math.min(parent, visited[next]);
		}

		if (parent == visited[x]) {
			List<Integer> component = new ArrayList<>();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				component.add(node);
				finished[node] = true;
				sccId[node] = scc.size();
				if (node == x)
					break;
			}
			scc.add(component);
		}
		return parent;
	}
}

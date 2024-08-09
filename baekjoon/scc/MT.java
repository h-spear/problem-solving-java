// https://www.acmicpc.net/problem/10265

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class MT {

	private static int id;
	private static final int[] x = new int[1010];
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] visited = new int[1010];
	private static final boolean[] finished = new boolean[1010];
	private static final int[] sccId = new int[1010];
	private static final List<List<Integer>> scc = new ArrayList<>();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; ++i) {
			x[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; ++i) {
			if (!finished[i])
				dfs(i);
		}

		int countOfComponents = scc.size();
		List<List<Integer>> graph = new ArrayList<>(countOfComponents);
		int[] componentSize = new int[countOfComponents];
		int[] inDegree = new int[countOfComponents];
		for (int i = 0; i < countOfComponents; ++i) {
			graph.add(new ArrayList<>());
			componentSize[i] = scc.get(i).size();
		}

		for (int i = 1; i <= n; ++i) {
			if (sccId[i] != sccId[x[i]]) {
				graph.get(sccId[x[i]]).add(sccId[i]);
				++inDegree[sccId[i]];
			}
		}

		List<int[]> knapsack = new ArrayList<>();
		for (int i = 0; i < countOfComponents; ++i) {
			if (inDegree[i] == 0) {
				knapsack.add(bfs(graph, countOfComponents, componentSize, i));
			}
		}

		boolean[] possible = new boolean[n + 1];
		possible[0] = true;
		for (int[] v: knapsack) {
			boolean[] checker = new boolean[n + 1];
			for (int i = 0; i <= n; ++i) {
				if (!possible[i])
					continue;
				for (int j = i + v[0]; j <= n && j <= i + v[1]; ++j) {
					checker[j] = true;
				}
			}
			for (int i = 0; i <= n; ++i) {
				if (checker[i])
					possible[i] = true;
			}
		}

		for (int i = k; i >= 0; --i) {
			if (possible[i]) {
				System.out.println(i);
				break;
			}
		}
	    br.close();
	}

	private static int[] bfs(List<List<Integer>> graph, int n, int[] size, int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		int sz = size[start];
		while (!queue.isEmpty()) {
			int now = queue.poll();
			++sz;
			queue.addAll(graph.get(now));
		}
		return new int[]{size[start], sz - 1};
	}

	private static int dfs(int now) {
		int parent = visited[now] = ++id;
		stack.push(now);

		int next = x[now];
		if (visited[next] == 0)
			parent = Math.min(parent, dfs(next));
		else if (!finished[next])
			parent = Math.min(parent, visited[next]);

		if (parent == visited[now]) {
			List<Integer> component = new ArrayList<>();
			int sz = scc.size();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				finished[node] = true;
				component.add(node);
				sccId[node] = sz;
				if (node == now)
					break;
			}
			scc.add(component);
		}
		return parent;
	}
}

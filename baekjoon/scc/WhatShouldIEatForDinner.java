// https://www.acmicpc.net/problem/30879

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class WhatShouldIEatForDinner {

	private static final int MAX_SIZE = 200020;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] sccId = new int[MAX_SIZE];
	private static final int[] visited = new int[MAX_SIZE];
	private static final boolean[] finished = new boolean[MAX_SIZE];
	private static final List<List<Integer>> scc = new ArrayList<>();
	private static int id;
	private static int N;
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		Query[] queries = new Query[N];
		List<Integer> queryTwoIndices = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if (q == 1)
				queries[i] = new Query(Integer.parseInt(st.nextToken()),
									   Integer.parseInt(st.nextToken()));
			else {
				queries[i] = new Query();
				queryTwoIndices.add(i);
			}
		}

		int left = 0;
		int right = N - 1;
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (tarjan(queries, 0, mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int index: queryTwoIndices) {
			if (index < left)
				sb.append("YES DINNER\n");
			else
				sb.append("NO DINNER\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static boolean tarjan(Query[] queries, int left, int right) {
		graph = new ArrayList<>(MAX_SIZE);
		for (int i = 0; i <= MAX_SIZE; ++i)
			graph.add(new ArrayList<>());

		for (int i = left; i <= right; ++i) {
			Query query = queries[i];
			if (query.q == 2)
				continue;
			int a = query.a > 0 ? trueX(query.a) : falseX(-query.a);
			int b = query.b > 0 ? trueX(query.b) : falseX(-query.b);
			graph.get(notX(a)).add(b);
			graph.get(notX(b)).add(a);
		}

		id = 0;
		scc.clear();
		Arrays.fill(visited, -1);
		Arrays.fill(sccId, 0);
		Arrays.fill(finished, false);
		for (int i = 2; i < MAX_SIZE; ++i) {
			if (!finished[i])
				dfs(i);
		}

		for (int i = 1; i <= 100000; ++i) {
			if (sccId[trueX(i)] == sccId[falseX(i)])
				return false;
		}
		return true;
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
			List<Integer> component = new ArrayList<>();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				component.add(node);
				sccId[node] = scc.size();
				finished[node] = true;
				if (node == x)
					break;
			}
			scc.add(component);
		}
		return parent;
	}

	private static int notX(int x) {
		return x ^ 1;
	}

	private static int falseX(int x) {
		return (x << 1) | 1;
	}

	private static int trueX(int x) {
		return x << 1;
	}

	private static class Query {
		int q, a, b;

		Query(int a, int b) {
			this.q = 1;
			this.a = a;
			this.b = b;
		}

		Query() {
			this.q = 2;
		}
	}
}

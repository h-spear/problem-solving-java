// https://www.acmicpc.net/problem/20504

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class EasyProblemNumberI {

	private static List<List<Integer>> graph;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] visited = new int[200020];
	private static final boolean[] finished = new boolean[200020];
	private static final int[] sccId = new int[200020];
	private static List<List<Integer>> scc;
	private static int id, sz;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		scc = new ArrayList<>();
		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
		}

		int T = Integer.parseInt(br.readLine());
		boolean[] testcase = new boolean[N + 1];
		for (int i = 0; i < T; ++i) {
			testcase[Integer.parseInt(br.readLine())] = true;
		}

		for (int i = 1; i <= N; ++i) {
			if (!finished[i])
				dfs(i);
		}

		int[] sccInDegree = new int[sz];
		for (int i = 1; i <= N; ++i) {
			for (int j: graph.get(i)) {
				if (sccId[i] != sccId[j]) {
					++sccInDegree[sccId[j]];
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < sz; ++i) {
			if (sccInDegree[i] > 0)
				continue;

			boolean flag = false;
			for (int node: scc.get(i)) {
				if (testcase[node]) {
					flag = true;
					++answer;
					break;
				}
			}
			if (!flag) {
				answer = -1;
				break;
			}
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
				finished[node] = true;
				sccId[node] = sz;
				component.add(node);
				if (node == x)
					break;
			}
			scc.add(component);
			++sz;
		}
		return parent;
	}
}

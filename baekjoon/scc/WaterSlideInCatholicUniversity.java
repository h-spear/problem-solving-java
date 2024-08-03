// https://www.acmicpc.net/problem/18133

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class WaterSlideInCatholicUniversity {

	private static List<List<Integer>> graph;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] visited = new int[100010];
	private static final boolean[] finished = new boolean[100010];
	private static final int[] sccId = new int[100010];
	private static int sz;
	private static int id;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y);
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
		System.out.println(count(sccInDegree, 0));
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

	private static int count(int[] arr, int value) {
		int count = 0;
		for (int x: arr) {
			if (x == value)
				++count;
		}
		return count;
	}
}

// https://www.acmicpc.net/problem/14249

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class JumpJump2 {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static int n;
	private static int[] A;
	private static int[] sccId;
	private static boolean[] finished;
	private static List<List<Integer>> scc;
	private static int id;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		A = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		scc = new ArrayList<>();
		finished = new boolean[n];
		sccId = new int[n];
		Arrays.fill(sccId, -1);

		int s = Integer.parseInt(br.readLine()) - 1;

		for (int i = 0; i < n; ++i) {
			if (!finished[i])
				dfs(i);
		}

		int sz = scc.size();
		int[] sccSize = new int[sz];

		List<List<Integer>> graph2 = new ArrayList<>(sz);
		for (int i = 0; i < sz; ++i) {
			graph2.add(new ArrayList<>());
			sccSize[i] = scc.get(i).size();
		}

		for (int i = 0; i < n; ++i) {
			for (int sign = -1; sign <= 1; sign += 2) {
				int j = i + sign * A[i];
				if (j < 0 || j >= n)
					continue;
				if (sccId[i] != sccId[j]) {
					graph2.get(sccId[i]).add(sccId[j]);
				}
			}
		}

		System.out.println(solve(graph2, new boolean[sz], sccId[s], sccSize));
	    br.close();
	}

	private static int solve(List<List<Integer>> graph, boolean[] visited, int x, int[] size) {
		int temp = 0;
		for (int next: graph.get(x)) {
			visited[next] = true;
			temp = Math.max(temp, solve(graph, visited, next, size));
			visited[next] = false;
		}
		return size[x] + temp;
	}

	private static int dfs(int x) {
		int parent = sccId[x] = ++id;
		stack.push(x);

		for (int sign = -1; sign <= 1; sign += 2) {
			int next = x + sign * A[x];
			if (next < 0 || next >= n)
				continue;
			if (sccId[next] == -1)
				parent = Math.min(parent, dfs(next));
			else if (!finished[next])
				parent = Math.min(parent, sccId[next]);
		}

		if (parent == sccId[x]) {
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

// https://www.acmicpc.net/problem/1143

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class Police {

	private static int[] sccId;
	private static boolean[] finished;
	private static int id, sz;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] cost = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList<>(N);
		for (int i = 0; i < N; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < N; ++j) {
				if (line.charAt(j) == 'Y')
					graph.get(i).add(j);
			}
		}

		finished = new boolean[N];
		sccId = new int[N];
		Arrays.fill(sccId, -1);

		for (int i = 0; i < N; ++i) {
			if (!finished[i])
				dfs(i);
		}

		System.out.println(solve(N, cost));
	    br.close();
	}

	private static double solve(int N, int[] cost) {
		int[] inDegree = new int[sz];
		for (int i = 0; i < N; ++i) {
			for (int j: graph.get(i)) {
				if (sccId[i] != sccId[j])
					++inDegree[sccId[j]];
			}
		}

		int[] minCost = new int[sz];
		int[] minIndex = new int[sz];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		for (int i = 0; i < N; ++i) {
			if (minCost[sccId[i]] > cost[i]) {
				minCost[sccId[i]] = cost[i];
				minIndex[sccId[i]] = i;
			}
		}

		int sum = 0;
		int count = 0;
		boolean[] used = new boolean[N];
		for (int i = 0; i < sz; ++i) {
			if (inDegree[i] == 0) {
				sum += minCost[i];
				used[minIndex[i]] = true;
				++count;
			}
		}

		List<Integer> candidate = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			if (!used[i])
				candidate.add(cost[i]);
		}
		candidate.sort(Comparator.naturalOrder());

		for (int c: candidate) {
			if (sum > c * count) {
				sum += c;
				++count;
			}
		}
		return (double) sum / count;
	}

	private static int dfs(int x) {
		int parent = sccId[x] = ++id;
		stack.push(x);

		for (int next: graph.get(x)) {
			if (sccId[next] == -1)
				parent = Math.min(parent, dfs(next));
			else if (!finished[next])
				parent = Math.min(parent, sccId[next]);
		}

		if (parent == sccId[x]) {
			while (!stack.isEmpty()) {
				int node = stack.pop();
				sccId[node] = sz;
				finished[node] = true;
				if (node == x)
					break;
			}
			++sz;
		}
		return parent;
	}
}

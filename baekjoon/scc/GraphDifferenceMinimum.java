// https://www.acmicpc.net/problem/12880

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class GraphDifferenceMinimum {

	private static int id;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] visited = new int[55];
	private static final boolean[] finished = new boolean[55];
	private static final int[][] graph = new int[55][55];
	private static int N, groupCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		Set<Integer> weightSet = new HashSet<>();

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (i != j)
					weightSet.add(graph[i][j]);
			}
		}

		int[] w = weightSet.stream()
			.mapToInt(Integer::intValue)
			.sorted()
			.toArray();

		int answer = 987654321;
		for (int left = 0, right = 0, sz = w.length; right < sz; ++right) {
			while (left <= right && tarjan(w[left], w[right])) {
				answer = Math.min(answer, w[right] - w[left]);
				++left;
			}
		}
		System.out.println(N == 1 ? 0 : answer);
		br.close();
	}

	private static boolean tarjan(int minWeight, int maxWeight) {
		id = 0;
		groupCount = 0;
		Arrays.fill(visited, 0, N, 0);
		Arrays.fill(finished, 0, N, false);
		dfs(0, minWeight, maxWeight);
		return groupCount == N;
		/***
		 * 	finished로 체크하면 오답!
		 * 	groupCount == N으로 체크해야 정답!
		 *
		 * 	for (int i = 0; i < N; ++i) {
		 * 		if (!finished[i])
		 * 			return false;
		 *  }
		 * 	return true;
		 */
	}

	private static int dfs(int x, int minWeight, int maxWeight) {
		int parent = visited[x] = ++id;
		stack.push(x);

		for (int i = 0; i < N; ++i) {
			if (x == i || graph[x][i] < minWeight || graph[x][i] > maxWeight)
				continue;
			if (visited[i] == 0)
				parent = Math.min(parent, dfs(i, minWeight, maxWeight));
			else if (!finished[i])
				parent = Math.min(parent, visited[i]);
		}

		if (parent == visited[x]) {
			while (!stack.isEmpty()) {
				if (x == 0)
					++groupCount;

				int node = stack.pop();
				finished[node] = true;
				if (node == x)
					break;
			}
		}
		return parent;
	}
}
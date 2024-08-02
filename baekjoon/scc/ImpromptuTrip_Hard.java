// https://www.acmicpc.net/problem/26157

package baekjoon.scc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ImpromptuTrip_Hard {

	private static List<List<Integer>> graph;
	private static int id;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static int[] visited = new int[200020];
	private static boolean[] finished = new boolean[200020];
	private static List<List<Integer>> scc;
	private static int[] sccId = new int[200020];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());
		scc = new ArrayList<>();

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(v).add(w);
		}

		for (int i = 1; i <= N; ++i) {
			if (!finished[i])
				dfs(i);
		}

		int countOfComponents = scc.size();
		int[] sccInDegree = new int[countOfComponents];
		List<List<Integer>> graph2 = new ArrayList<>(countOfComponents);
		for (int i = 0; i < countOfComponents; ++i)
			graph2.add(new ArrayList<>());

		for (int i = 1; i <= N; ++i) {
			for (int j: graph.get(i)) {
				if (sccId[i] != sccId[j]) {
					graph2.get(sccId[i]).add(sccId[j]);
					++sccInDegree[sccId[j]];
				}
			}
		}

		if (isLinear(graph2, countOfComponents, sccInDegree)) {
			List<Integer> component = scc.get(countOfComponents - 1);
			component.sort((o1, o2) -> Integer.compare(o1, o2));
			StringBuilder sb = new StringBuilder();
			sb.append(component.size()).append("\n");
			for (int x: component)
				sb.append(x).append(" ");
			System.out.println(sb.toString());
		} else {
			System.out.println(0);
		}
		br.close();
	}

	private static boolean isLinear(List<List<Integer>> graph, int n, int[] inDegree) {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < n; ++i) {
			if (inDegree[i] == 0)
				queue.add(i);
		}

		while (!queue.isEmpty()) {
			if (queue.size() > 1)
				return false;

			int now = queue.poll();
			for (int next: graph.get(now)) {
				if (--inDegree[next] == 0)
					queue.add(next);
			}
		}
		return true;
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
			int s = scc.size();
			List<Integer> component = new ArrayList<>();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				component.add(node);
				finished[node] = true;
				sccId[node] = s;
				if (node == x)
					break;
			}
			scc.add(component);
		}
		return parent;
	}
}

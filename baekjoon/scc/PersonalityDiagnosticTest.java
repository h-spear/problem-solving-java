// https://www.acmicpc.net/problem/4305

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class PersonalityDiagnosticTest {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] visited = new int[26];
	private static final boolean[] finished = new boolean[26];
	private static int id;
	private static List<List<Integer>> scc;
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N;
		StringBuilder sb = new StringBuilder();
		while ((N = Integer.parseInt(br.readLine())) > 0) {
			scc = new ArrayList<>();
			graph = new ArrayList<>(26);
			boolean[] problems = new boolean[26];
			for (int i = 0; i < 26; ++i) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				int[] activities = new int[5];
				for (int j = 0; j < 5; ++j) {
					activities[j] = st.nextToken().charAt(0) - 'A';
					problems[activities[j]] = true;
				}

				int sel = st.nextToken().charAt(0) - 'A';
				for (int activity: activities) {
					if (activity != sel) {
						graph.get(activity).add(sel);
					}
				}
			}

			for (int i = 0; i < 26; ++i) {
				if (problems[i] && !finished[i])
					dfs(i);
			}

			for (List<Integer> component: scc) {
				component.sort((o1, o2) -> Integer.compare(o1, o2));
			}
			scc.sort((o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));

			for (List<Integer> component: scc) {
				for (int node: component)
					sb.append((char) (node + 'A')).append(" ");
				sb.append("\n");
			}
			sb.append("\n");

			id = 0;
			Arrays.fill(finished, false);
			Arrays.fill(visited, 0);
		}
		System.out.println(sb.toString());
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
				component.add(node);
				if (node == x)
					break;
			}
			scc.add(component);
		}
		return parent;
	}
}

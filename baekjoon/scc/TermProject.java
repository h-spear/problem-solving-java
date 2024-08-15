// https://www.acmicpc.net/problem/9466

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class TermProject {

	private static int n;
	private static int[] s;
	private static int[] visited;
	private static boolean[] finished;
	private static int id;
	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static List<List<Integer>> scc;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			n = Integer.parseInt(br.readLine());
			s = new int[n + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; ++i) {
				s[i] = Integer.parseInt(st.nextToken());
			}

			id = 0;
			visited = new int[n + 1];
			finished = new boolean[n + 1];
			scc = new ArrayList<>();
			for (int i = 1; i <= n; ++i) {
				if (!finished[i])
					dfs(i);
			}

			int answer = 0;
			for (List<Integer> component: scc) {
				if (component.size() == 1) {
					int node = component.get(0);
					if (s[node] != node)
						++answer;
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int dfs(int x) {
		int parent = visited[x] = ++id;
		stack.push(x);

		int next = s[x];
		if (visited[next] == 0)
			parent = Math.min(parent, dfs(next));
		else if (!finished[next])
			parent = Math.min(parent, visited[next]);

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

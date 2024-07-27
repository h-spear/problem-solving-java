// https://www.acmicpc.net/problem/2150

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class StronglyConnectedComponent {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final List<List<Integer>> answer = new ArrayList<>();
	private static boolean[] finished;
	private static int[] labels;
	private static int label;
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(V + 1);
		for (int i = 0; i <= V; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
		}

		finished = new boolean[V + 1];
		labels = new int[V + 1];

		for (int i = 1; i <= V; ++i)
			if (!finished[i])
				scc(i);

		// sorting
		for (List<Integer> component: answer)
			component.sort(((o1, o2) -> Integer.compare(o1, o2)));
		answer.sort((o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));

		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append("\n");
		for (List<Integer> component: answer) {
			for (int x: component)
				sb.append(x).append(" ");
			sb.append("-1\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int scc(int x) {
		++label;
		int parent = labels[x] = label;
		stack.push(x);

		for (int next: graph.get(x)) {
			if (labels[next] == 0)
				parent = Math.min(parent, scc(next));
			else if (!finished[next]) {
				parent = Math.min(parent, labels[next]);
			}
		}

		if (parent == labels[x]) {
			List<Integer> component = new ArrayList<>();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				component.add(node);
				finished[node] = true;
				if (node == x)
					break;
			}
			answer.add(component);
		}
		return parent;
	}
}

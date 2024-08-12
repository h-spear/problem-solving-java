// https://www.acmicpc.net/problem/2416

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class Door {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static int[] labels;
	private static boolean[] finished;
	private static int id, sz;
	private static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int countOfNodes = (M << 1) + 2;

		graph = new ArrayList<>(countOfNodes);
		for (int i = 0; i < countOfNodes; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int sa = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int sb = Integer.parseInt(st.nextToken());

			a = sa == 1 ? trueX(a) : falseX(a);
			b = sb == 1 ? trueX(b) : falseX(b);
			graph.get(notX(a)).add(b);
			graph.get(notX(b)).add(a);
		}

		finished = new boolean[countOfNodes];
		labels = new int[countOfNodes];
		Arrays.fill(labels, -1);

		for (int i = 2; i < countOfNodes; ++i) {
			if (!finished[i])
				dfs(i);
		}

		boolean valid = true;
		for (int i = 1; i <= M; ++i) {
			if (labels[trueX(i)] == labels[falseX(i)]) {
				valid = false;
				break;
			}
		}

		if (!valid) {
			System.out.println("IMPOSSIBLE");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= M; ++i)
				sb.append(labels[trueX(i)] < labels[falseX(i)] ? 1 : 0)
					.append("\n");
			System.out.println(sb.toString());
		}
		System.out.println(Arrays.toString(labels));
	    br.close();
	}

	private static int dfs(int x) {
		int parent = labels[x] = ++id;
		stack.push(x);

		for (int next: graph.get(x)) {
			if (labels[next] == -1)
				parent = Math.min(parent, dfs(next));
			else if (!finished[next])
				parent = Math.min(parent, labels[next]);
		}

		if (parent == labels[x]) {
			while (!stack.isEmpty()) {
				int node = stack.pop();
				finished[node] = true;
				labels[node] = sz;
				if (node == x)
					break;
			}
			++sz;
		}
		return parent;
	}

	private static int trueX(int x) {
		return x << 1;
	}

	private static int falseX(int x) {
		return (x << 1) | 1;
	}

	private static int notX(int x) {
		return x ^ 1;
	}
}

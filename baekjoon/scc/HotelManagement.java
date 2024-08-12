// https://www.acmicpc.net/problem/16915

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class HotelManagement {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static int[] visited;
	private static int[] sccId;
	private static boolean[] finished;
	private static List<List<Integer>> graph;
	private static int M, id, sz;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] state = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			state[i] = Integer.parseInt(st.nextToken());
		}

		int[][] switches = new int[N + 1][2];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			for (int j = 0; j < K; ++j) {
				int x = Integer.parseInt(st.nextToken());
				if (switches[x][0] == 0)
					switches[x][0] = i;
				else
					switches[x][1] = i;
			}
		}

		int countOfNodes = (M << 1) + 1;
		graph = new ArrayList<>(countOfNodes);
		for (int i = 0; i <= countOfNodes; ++i)
			graph.add(new ArrayList<>());

		for (int i = 1; i <= N; ++i) {
			int a = switches[i][0];
			int b = switches[i][1];
			if (state[i] == 1) {
				graph.get(a).add(b);
				graph.get(b).add(a);
				graph.get(notX(a)).add(notX(b));
				graph.get(notX(b)).add(notX(a));
			} else {
				graph.get(a).add(notX(b));
				graph.get(b).add(notX(a));
				graph.get(notX(a)).add(b);
				graph.get(notX(b)).add(a);
			}
		}

		visited = new int[countOfNodes];
		sccId = new int[countOfNodes];
		finished = new boolean[countOfNodes];

		for (int i = 1; i < countOfNodes; ++i) {
			if (!finished[i])
				dfs(i);
		}

		boolean valid = true;
		for (int i = 1; i <= M; ++i) {
			if (sccId[i] == sccId[notX(i)]) {
				valid = false;
				break;
			}
		}
		System.out.println(valid ? 1 : 0);
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
				sccId[node] = sz;
				finished[node] = true;
				if (node == x)
					break;
			}
			++sz;
		}
		return parent;
	}

	private static int notX(int x) {
		if (x > M)
			return x - M;
		else
			return x + M;
	}
}

// https://www.acmicpc.net/problem/1108

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class SearchEngine {

	private static int[] labels;
	private static boolean[] finished;
	private static int id, countOfNodes;
	private static List<List<Integer>> graph;
	private static List<List<Integer>> scc;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		Map<String, Integer> nameToIndexMapper = new HashMap<>();
		final int cap = 50 * 25;

		scc = new ArrayList<>();
		graph = new ArrayList<>(cap);
		for (int i = 0; i <= cap; ++i)
			graph.add(new ArrayList<>());

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			String website = st.nextToken();
			if (!nameToIndexMapper.containsKey(website))
				nameToIndexMapper.put(website, nameToIndexMapper.size() + 1);
			int dest = nameToIndexMapper.get(website);

			int c = Integer.parseInt(st.nextToken());
			for (int j = 0; j < c; ++j) {
				String other = st.nextToken();
				if (!nameToIndexMapper.containsKey(other))
					nameToIndexMapper.put(other, nameToIndexMapper.size() + 1);
				int src = nameToIndexMapper.get(other);
				graph.get(src).add(dest);
			}
		}
		int site = nameToIndexMapper.get(br.readLine());

		countOfNodes = nameToIndexMapper.size();
		tarjan();

		List<List<Integer>> graph2 = new ArrayList<>(countOfNodes + 1);
		for (int i = 0; i <= countOfNodes; ++i)
			graph2.add(new ArrayList<>());

		for (int i = 1; i <= countOfNodes; ++i) {
			for (int j: graph.get(i)) {
				if (labels[i] != labels[j])
					graph2.get(i).add(j);
			}
		}

		long[] result = topologySort(graph2, countOfNodes);
		System.out.println(result[site]);
	    br.close();
	}

	private static long[] topologySort(List<List<Integer>> graph, int n) {
		int[] inDegree = new int[n + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j: graph.get(i))
				++inDegree[j];
		}

		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= n; ++i) {
			if (inDegree[i] == 0)
				queue.add(i);
		}

		long[] result = new long[n + 1];
		Arrays.fill(result, 1);

		for (int i = 0; i < n; ++i) {
			assert !queue.isEmpty();
			int now = queue.poll();

			for (int next: graph.get(now)) {
				result[next] += result[now];
				if (--inDegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		return result;
	}

	private static void tarjan() {
		Deque<Integer> stack = new ArrayDeque<>();
		labels = new int[countOfNodes + 1];
		finished = new boolean[countOfNodes + 1];
		Arrays.fill(labels, -1);

		for (int i = 1; i <= countOfNodes; ++i) {
			if (!finished[i])
				dfs(i, stack);
		}
	}

	private static int dfs(int x, Deque<Integer> stack) {
		int parent = labels[x] = ++id;
		stack.push(x);

		for (int next: graph.get(x)) {
			if (labels[next] == -1)
				parent = Math.min(parent, dfs(next, stack));
			else if (!finished[next])
				parent = Math.min(parent, labels[next]);
		}

		if (parent == labels[x]) {
			List<Integer> component = new ArrayList<>();
			int sz = scc.size();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				component.add(node);
				labels[node] = sz;
				finished[node] = true;
				if (node == x)
					break;
			}
			scc.add(component);
		}
		return parent;
	}
}

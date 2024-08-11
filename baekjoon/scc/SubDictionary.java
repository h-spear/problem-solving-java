// https://www.acmicpc.net/problem/6264

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class SubDictionary {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static List<Set<Integer>> graph;
	private static int[] visited;
	private static int[] sccId;
	private static boolean[] finished;
	private static int id, countOfNodes;
	private static List<List<Integer>> scc;
	private static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String[] converter = inputAndPreprocessing(br);
			if (converter == null)
				break;

			countOfNodes = converter.length - 1;
			scc = new ArrayList<>();
			sccId = new int[countOfNodes + 1];
			finished = new boolean[countOfNodes + 1];
			visited = new int[countOfNodes + 1];

			// tarjan
			for (int i = 1; i <= countOfNodes; ++i) {
				if (!finished[i])
					dfs(i);
			}

			List<String> subDictionary = solve(converter);
			sb.append(subDictionary.size()).append("\n");
			for (String word: subDictionary) {
				sb.append(word).append(" ");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static List<String> solve(String[] converter) {
		int sz = scc.size();
		int[] sccOutDegree = new int[sz];
		int[] sccSize = new int[sz];

		List<Set<Integer>> dag = new ArrayList<>(sz);
		for (int i = 0; i < sz; ++i) {
			dag.add(new HashSet<>());
			sccSize[i] = scc.get(i).size();
		}

		for (int i = 1; i <= countOfNodes; ++i) {
			for (int j: graph.get(i)) {
				if (sccId[i] != sccId[j]) {
					dag.get(sccId[j]).add(sccId[i]);
					++sccOutDegree[sccId[j]];
				}
			}
		}

		check = new boolean[sz];
		for (int i = 0; i < sz; ++i) {
			if (sccOutDegree[i] == 0)
				check[i] = true;
			if (sccSize[i] > 1)
				checkDfs(dag, i);
		}

		List<String> subDictionary = new ArrayList<>();
		for (int i = 0; i < sz; ++i) {
			if (check[i]) {
				for (int node: scc.get(i))
					subDictionary.add(converter[node]);
			}
		}
		Collections.sort(subDictionary);
		return subDictionary;
	}

	private static void checkDfs(List<Set<Integer>> dag, int x) {
		check[x] = true;
		for (int next: dag.get(x)) {
			if (!check[next]) {
				checkDfs(dag, next);
			}
		}
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

	private static String[] inputAndPreprocessing(BufferedReader br) throws IOException {
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		if (n == 0)
			return null;

		graph = new ArrayList<>(3030);
		for (int i = 0; i < 3030; ++i)
			graph.add(new HashSet<>());

		Map<String, Integer> nameToIndexMapper = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			String first = st.nextToken();
			if (!nameToIndexMapper.containsKey(first)) {
				nameToIndexMapper.put(first, nameToIndexMapper.size() + 1);
			}
			int dest = nameToIndexMapper.get(first);

			while (st.hasMoreTokens()) {
				String pre = st.nextToken();
				if (!nameToIndexMapper.containsKey(pre)) {
					nameToIndexMapper.put(pre, nameToIndexMapper.size() + 1);
				}
				int src = nameToIndexMapper.get(pre);
				graph.get(src).add(dest);
			}
		}

		String[] converter = new String[nameToIndexMapper.size() + 1];
		for (Map.Entry<String, Integer> entry: nameToIndexMapper.entrySet()) {
			converter[entry.getValue()] = entry.getKey();
		}

		return converter;
	}
}

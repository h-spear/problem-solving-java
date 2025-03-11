// https://www.acmicpc.net/problem/11670

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ElementaryMath {

	private static int n;
	private static List<List<Long>> graph;
	private static Map<Long, Integer> match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		graph = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) {
			graph.add(new ArrayList<>());
		}
		match = new HashMap<>();
		visited = new int[n];

		Pair[] pairs = new Pair[n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			pairs[i] = new Pair();
			pairs[i].a = Integer.parseInt(st.nextToken());
			pairs[i].b = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; ++i) {
			graph.get(i).add((long) pairs[i].a + pairs[i].b);
			graph.get(i).add((long) pairs[i].a - pairs[i].b);
			graph.get(i).add((long) pairs[i].a * pairs[i].b);
		}

		int count = 0;
		for (int i = 0; i < n; ++i) {
			++call;
			if (dfs(i))
				++count;
		}

		if (count < n) {
			System.out.println("impossible");
		} else {
			StringBuilder sb = new StringBuilder();

			List<Map.Entry<Long, Integer>> sorted = match.entrySet().stream()
				.sorted(Comparator.comparingInt(Map.Entry::getValue))
				.collect(Collectors.toList());

			for (Map.Entry<Long, Integer> entry: sorted) {
				int index = entry.getValue();
				long result = entry.getKey();
				Pair p = pairs[index];

				sb.append(p.a);
				if (p.a + p.b == result) {
					sb.append(" + ");
				} else if (p.a - p.b == result) {
					sb.append(" - ");
				} else {
					sb.append(" * ");
				}
				sb.append(p.b).append(" = ")
					.append(result).append("\n");
			}
			System.out.println(sb.toString());
		}
	    br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;

		for (long y: graph.get(x)) {
			if (!match.containsKey(y) || dfs(match.get(y))) {
				match.put(y, x);
				return true;
			}
		}
		return false;
	}

	private static class Pair {
		int a, b;
	}
}

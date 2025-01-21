// https://www.acmicpc.net/problem/8452

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class GraphAndQuery {

	private static final int INF = 123456789;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[m];
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge();
			edges[i].u = Integer.parseInt(st.nextToken());
			edges[i].v = Integer.parseInt(st.nextToken());
		}

		Query[] queries = new Query[q];
		int ee = 0;
		for (int i = 0; i < q; ++i) {
			st = new StringTokenizer(br.readLine());
			queries[i] = new Query();
			queries[i].t = st.nextToken().charAt(0);
			queries[i].p = Integer.parseInt(st.nextToken());
			if (queries[i].t == 'U')
				--queries[i].p;
			else
				++ee;
		}

		int[] result = new int[ee];

		List<List<Integer>> graph = constructGraph(n, edges, queries);
		int[] visited = bfs(n, graph);

		for (int i = q - 1; i >= 0; --i) {
			Query query = queries[i];
			if (query.t == 'U') {
				Edge edge = edges[query.p];
				graph.get(edge.u).add(edge.v);
				if (visited[edge.u] + 1 >= visited[edge.v]) {
					continue;
				}
				visited = bfs(n, graph);
			} else {
				result[--ee] = visited[query.p] == INF ? -1 : visited[query.p];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int x: result)
			sb.append(x).append("\n");
		System.out.println(sb.toString());
	    br.close();
	}

	private static int[] bfs(int n, List<List<Integer>> graph) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		int[] visited = new int[n + 1];
		Arrays.fill(visited, INF);
		visited[1] = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int next: graph.get(now)) {
				if (visited[next] != INF)
					continue;
				queue.add(next);
				visited[next] = visited[now] + 1;
			}
		}
		return visited;
	}

	private static List<List<Integer>> constructGraph(int n, Edge[] edges, Query[] queries) {
		List<List<Integer>> graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}
		int m = edges.length;

		boolean[] removed = new boolean[m];
		for (Query query: queries) {
			if (query.t == 'U') {
				removed[query.p] = true;
			}
		}

		for (int i = 0; i < m; ++i) {
			if (removed[i])
				continue;
			Edge edge = edges[i];
			graph.get(edge.u).add(edge.v);
		}
		return graph;
	}

	private static class Edge {
		int u, v;
	}

	private static class Query {
		char t;
		int p;
	}
}

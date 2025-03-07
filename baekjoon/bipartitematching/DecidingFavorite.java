// https://www.acmicpc.net/problem/17481

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class DecidingFavorite {

	private static int N, M;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N);
		for (int i = 0; i < N; ++i)
			graph.add(new ArrayList<>());
		visited = new int[N];
		match = new int[M];
		Arrays.fill(match, -1);

		Map<String, Integer> idolIndexer = new HashMap<>();
		for (int i = 0; i < M; ++i) {
			idolIndexer.put(br.readLine(), i);
		}

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			while (K-- > 0) {
				String name = st.nextToken();
				graph.get(i).add(idolIndexer.get(name));
			}
		}

		int count = 0;
		for (int i = 0; i < N; ++i) {
			++call;
			if (dfs(i))
				++count;
		}
		System.out.println(count == N ? "YES" : "NO\n" + count);
	    br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == -1 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}
}

// https://www.acmicpc.net/problem/4376

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class Gopher_II {

	private static int n, m;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (input(br)) sb.append(solve()).append("\n");
		System.out.println(sb.toString());
		br.close();
	}

	private static int solve() {
		int count = 0;
		for (int i = 0; i < n; ++i) {
			++call;
			if (dfs(i))
				++count;
		}
		return n - count;
	}

	private static boolean dfs(int x) {
		if (visited[x] == call) {
			return false;
		}
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == -1 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static boolean input(BufferedReader br) throws IOException {
		StringTokenizer st;

		String line = br.readLine();
		if (line == null)
			return false;
		st = new StringTokenizer(line);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		visited = new int[n];
		match = new int[m];
		Arrays.fill(match, -1);

		graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}

		Pair[] gopher = new Pair[n];
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			gopher[i] = new Pair();
			gopher[i].x = Double.parseDouble(st.nextToken());
			gopher[i].y = Double.parseDouble(st.nextToken());
		}

		Pair[] hole = new Pair[m];
		for (int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			hole[i] = new Pair();
			hole[i].x = Double.parseDouble(st.nextToken());
			hole[i].y = Double.parseDouble(st.nextToken());
		}

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				double distance = getDistance(gopher[i], hole[j]);
				if (distance <= s * v) {
					graph.get(i).add(j);
				}
			}
		}
		return true;
	}

	private static double getDistance(Pair p1, Pair p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	private static class Pair {
		double x, y;
	}
}

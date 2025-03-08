// https://www.acmicpc.net/problem/2191

package baekjoon.bipartitematching;
import java.io.*;
import java.util.*;

public class EscapeOfFieldMouse {

	private static int N, M;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(solve());
	}

	private static int solve() {
		int count = 0;
		for (int i = 0; i < N; ++i) {
			++call;
			if (dfs(i))
				++count;
		}
		return N - count;
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

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		visited = new int[N];
		match = new int[M];
		Arrays.fill(match, -1);

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		Pair[] mouse = new Pair[N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			mouse[i] = new Pair();
			mouse[i].x = Double.parseDouble(st.nextToken());
			mouse[i].y = Double.parseDouble(st.nextToken());
		}

		Pair[] hole = new Pair[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			hole[i] = new Pair();
			hole[i].x = Double.parseDouble(st.nextToken());
			hole[i].y = Double.parseDouble(st.nextToken());
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				double distance = getDistance(mouse[i], hole[j]);
				if (distance <= S * V) {
					graph.get(i).add(j);
				}
			}
		}
		br.close();
	}

	private static double getDistance(Pair p1, Pair p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	private static class Pair {
		double x, y;
	}
}

// https://www.acmicpc.net/problem/20295

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class CandyDelivery {

	private static int N, P;
	private static int[] depth;
	private static int[][] parent;
	private static boolean[][][] isPossible;
	private static List<List<Integer>> graph;
	private static int[] candy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		P = 1;
		while ((1 << P) < N)
			++P;

		graph = new ArrayList<>(N + 1);
		candy = new int[N + 1];
		depth = new int[N + 1];
		parent = new int[P + 1][N + 1];
		isPossible = new boolean[P + 1][N + 1][5];

		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			candy[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int i = 0, u, v; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		bfs();
		fillParent();

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		int x, c, prev = 1;

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 1; i <= N; ++i) {
			if (candy[i] == c) {
				prev = i;
				break;
			}
		}

		sb.append(possible(prev, x, c) ? "PLAY\n" : "CRY\n");
		prev = x;

		for (int i = 1; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken()) - 1;
			sb.append(possible(prev, x, c) ? "PLAY\n" : "CRY\n");
			prev = x;
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static boolean possible(int a, int b, int c) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

		for (int i = P; i >= 0; --i) {
			if (depth[a] - (1 << i) >= depth[b]) {
				if (isPossible[i][a][c])
					return true;
				a = parent[i][a];
			}
		}

		if (a != b) {
			for (int i = P; i >= 0; --i) {
				if (parent[i][a] != parent[i][b]) {
					if (isPossible[i][a][c] || isPossible[i][b][c])
						return true;
					a = parent[i][a];
					b = parent[i][b];
				}
			}
			return isPossible[0][a][c] || isPossible[0][b][c];
		}
		return candy[a] == c;
	}

	private static void fillParent() {
		for (int i = 1; i <= P; ++i) {
			for (int j = 1; j <= N; ++j) {
				parent[i][j] = parent[i - 1][parent[i - 1][j]];
				for (int k = 0; k < 5; ++k) {
					isPossible[i][j][k] = isPossible[i - 1][j][k] || isPossible[i - 1][parent[i - 1][j]][k];
				}
			}
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		depth[1] = 1;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int next: graph.get(curr)) {
				if (depth[next] > 0)
					continue;
				depth[next] = depth[curr] + 1;
				parent[0][next] = curr;
				isPossible[0][next][candy[next]] = true;
				isPossible[0][next][candy[curr]] = true;
				queue.add(next);
			}
		}
	}
}
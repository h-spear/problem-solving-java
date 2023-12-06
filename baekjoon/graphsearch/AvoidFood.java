// https://www.acmicpc.net/problem/1743

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class AvoidFood {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	private static int N, M;
	private static int[][] graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		graph = new int[N][M];

		for (int i = 0, r, c; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			graph[r][c] = 1;
		}

		int answer = 0;
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (!visited[i][j] && graph[i][j] == 1) {
					answer = Math.max(answer, bfs(i, j, visited));
				}
			}
		}
		System.out.println(answer);
	    br.close();
	}

	private static int bfs(int x, int y, boolean[][] visited) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(x, y));
		visited[x][y] = true;
		int count = 0;
		while (!queue.isEmpty()) {
			++count;
			Pair p = queue.poll();
			int nx, ny;
			for (int i = 0; i < 4; ++i) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (visited[nx][ny])
					continue;
				if (graph[nx][ny] == 0)
					continue;
				queue.add(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return count;
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

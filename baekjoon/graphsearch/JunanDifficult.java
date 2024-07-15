// https://www.acmicpc.net/problem/14497

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class JunanDifficult {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken()) - 1;
		int y1 = Integer.parseInt(st.nextToken()) - 1;
		int x2 = Integer.parseInt(st.nextToken()) - 1;
		int y2 = Integer.parseInt(st.nextToken()) - 1;

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				graph[i][j] = line.charAt(j) == '1' ? 1 : 0;
			}
		}

		Deque<Pair> deque = new ArrayDeque<>();
		deque.add(new Pair(x1, y1, 0));
		boolean[][] visited = new boolean[N][M];
		visited[x1][y1] = true;

		int answer = -1;
		while (!deque.isEmpty()) {
			Pair p = deque.poll();

			if (p.x == x2 && p.y == y2) {
				answer = p.count + 1;
				break;
			}

			for (int i = 0; i < 4; ++i) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (visited[nx][ny])
					continue;
				visited[nx][ny] = true;
				if (graph[nx][ny] == 1) {
					deque.addLast(new Pair(nx, ny, p.count + 1));
				} else {
					deque.addFirst(new Pair(nx, ny, p.count));
				}
			}
		}
		System.out.println(answer);
	    br.close();
	}

	private static class Pair {
		int x, y, count;

		Pair(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}

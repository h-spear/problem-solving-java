// https://www.acmicpc.net/problem/1584

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Game_1584 {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	private static final int SAFE = 0;
	private static final int AREA_WARNING = 1;
	private static final int AREA_DEATH = 2;

	public static void main(String[] args) throws Exception {
		int[][] graph = inputAndPreprocessing();

		Deque<Pair> deque = new ArrayDeque<>();
		boolean[][] visited = new boolean[501][501];
		deque.add(new Pair(0, 0, 0));
		visited[0][0] = true;

		int answer = -1;
		while (!deque.isEmpty()) {
			Pair p = deque.poll();

			if (p.x == 500 && p.y == 500) {
				answer = p.cost;
				break;
			}

			for (int i = 0; i < 4; ++i) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx > 500 || ny > 500)
					continue;
				if (visited[nx][ny])
					continue;
				if (graph[nx][ny] == AREA_DEATH)
					continue;
				visited[nx][ny] = true;
				if (graph[nx][ny] == AREA_WARNING) {
					deque.addLast(new Pair(nx, ny, p.cost + 1));
				} else {
					deque.addFirst(new Pair(nx, ny, p.cost));
				}
			}
		}
		System.out.println(answer);
	}

	private static int[][] inputAndPreprocessing() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] warning = new int[502][502];
		int[][] death = new int[502][502];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int X1 = Integer.parseInt(st.nextToken());
			int Y1 = Integer.parseInt(st.nextToken());
			int X2 = Integer.parseInt(st.nextToken());
			int Y2 = Integer.parseInt(st.nextToken());
			int top = Math.min(X1, X2);
			int bottom = Math.max(X1, X2);
			int left = Math.min(Y1, Y2);
			int right = Math.max(Y1, Y2);
			warning[top][left] += 1;
			warning[top][right + 1] -= 1;
			warning[bottom + 1][left] -= 1;
			warning[bottom + 1][right + 1] += 1;
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int X1 = Integer.parseInt(st.nextToken());
			int Y1 = Integer.parseInt(st.nextToken());
			int X2 = Integer.parseInt(st.nextToken());
			int Y2 = Integer.parseInt(st.nextToken());
			int top = Math.min(X1, X2);
			int bottom = Math.max(X1, X2);
			int left = Math.min(Y1, Y2);
			int right = Math.max(Y1, Y2);
			death[top][left] += 1;
			death[top][right + 1] -= 1;
			death[bottom + 1][left] -= 1;
			death[bottom + 1][right + 1] += 1;
		}

		for (int i = 0; i < 502; ++i) {
			for (int j = 1; j < 502; ++j) {
				warning[i][j] += warning[i][j - 1];
				death[i][j] += death[i][j - 1];
			}
		}
		for (int j = 0; j < 502; ++j) {
			for (int i = 1; i < 502; ++i) {
				warning[i][j] += warning[i - 1][j];
				death[i][j] += death[i - 1][j];
			}
		}

		int[][] graph = new int[501][501];
		for (int i = 0; i < 501; ++i) {
			for (int j = 0; j < 501; ++j) {
				if (death[i][j] > 0)
					graph[i][j] = AREA_DEATH;
				else if (warning[i][j] > 0)
					graph[i][j] = AREA_WARNING;
			}
		}
		br.close();
		return graph;
	}

	private static class Pair {
		int x, y, cost;

		Pair(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}

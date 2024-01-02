// https://www.acmicpc.net/problem/17244

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class OhIRememberUmbrella {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] graph = new int[M][N];

		int sx = -1, sy = -1;
		int ex = -1, ey = -1;
		int itemCnt = 0;
		for (int i = 0; i < M; ++i) {
			String line = br.readLine();
			for (int j = 0; j < N; ++j) {
				switch (line.charAt(j)) {
					case 'S':
						sx = i; sy = j;
						break;
					case 'E':
						ex = i; ey = j;
						break;
					case '#':
						graph[i][j] = -1;
						break;
					case 'X':
						graph[i][j] = ++itemCnt;
						break;
				}
			}
		}
		int eItem = (1 << itemCnt) - 1;

		boolean[][][] visited = new boolean[M][N][1 << 6];
		Queue<Node> queue = new ArrayDeque<>();

		visited[sx][sy][0] = true;
		queue.add(new Node(sx, sy, 0, 0));
		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (now.x == ex && now.y == ey && now.items == eItem) {
				System.out.println(now.cnt);
				break;
			}

			for (int i = 0; i < 4; ++i) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int items = now.items;

				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;
				if (graph[nx][ny] < 0)
					continue;
				if (graph[nx][ny] > 0)
					items |= (1 << (graph[nx][ny] - 1));
				if (visited[nx][ny][items])
					continue;
				visited[nx][ny][items] = true;
				queue.add(new Node(nx, ny, items, now.cnt + 1));
			}
		}
	    br.close();
	}

	private static class Node {
		int x, y, items, cnt;

		public Node(int x, int y, int items, int cnt) {
			this.x = x;
			this.y = y;
			this.items = items;
			this.cnt = cnt;
		}
	}
}

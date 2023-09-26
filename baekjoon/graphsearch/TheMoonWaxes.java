// https://www.acmicpc.net/problem/1194

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class TheMoonWaxes {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	private static class Pair {
		int x, y, key, dist;

		public Pair(int x, int y, int key, int dist) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int sx = -1, sy = -1;

		char[][] graph = new char[N][M];
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < M; ++j) {
				graph[i][j] = line.charAt(j);
				if(graph[i][j] == '0') {
					graph[i][j] = '.';
					sx = i;
					sy = j;
				}
			}
		}


		boolean[][][] visited = new boolean[N][M][1 << 6];
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(sx, sy, 0, 0));
		visited[sx][sy][0] = true;
		int nx, ny, key, answer = -1;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			if (graph[p.x][p.y] == '1') {
				answer = p.dist;
				break;
			}

			for (int i = 0; i < 4; ++i) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				key = p.key;
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if ('A' <= graph[nx][ny] && graph[nx][ny] <= 'F'
					&& (p.key & (1 << (graph[nx][ny] - 'A'))) == 0)
					continue;
				if (graph[nx][ny] == '#')
					continue;
				if (visited[nx][ny][p.key])
					continue;
				if ('a' <= graph[nx][ny] && graph[nx][ny] <= 'f')
					key |= (1 << (graph[nx][ny] - 'a'));

				visited[nx][ny][key] = true;
				queue.add(new Pair(nx, ny, key, p.dist + 1));
			}
		}
		System.out.println(answer);
		br.close();
	}
}

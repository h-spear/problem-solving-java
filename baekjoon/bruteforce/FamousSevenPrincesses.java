package baekjoon.bruteforce;

import java.io.*;
import java.util.*;

public class FamousSevenPrincesses {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	private static int[][] graph;
	private static int comb;
	private static int answer;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new int[5][5];
		for (int i = 0; i < 5; ++i) {
			String line = br.readLine();
			for (int j = 0; j < 5; ++j) {
				graph[i][j] = line.charAt(j) == 'S' ? 1 : 0;
			}
		}
		dfs(0, 0);
		System.out.println(answer);
	    br.close();
	}

	private static void dfs(int idx, int depth) {
		if (depth == 7) {
			int i = 0;
			for (; i < 25; ++i) {
				if ((comb & (1 << i)) != 0)
					break;
			}
			if (bfs(i / 5, i % 5)) {
				++answer;
			}
			return;
		}
		for (int i = idx; i < 25; ++i) {
			comb |= (1 << i);
			dfs(i + 1, depth + 1);
			comb &= ~(1 << i);
		}
	}

	private static boolean bfs(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>(10);
		queue.add(new Pair(x, y));
		boolean[][] visited = new boolean[5][5];
		visited[x][y] = true;
		int nx, ny;
		int length = 0, count = 0;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			++length;
			count += graph[p.x][p.y];
			for (int i = 0; i < 4; ++i) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
					continue;
				if (visited[nx][ny])
					continue;
				if ((comb & (1 << (nx * 5 + ny))) == 0)
					continue;
				queue.add(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
		return length == 7 && count >= 4;
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

// 1949

package swea.swtest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class HikingTrailConstruction {

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static final int[] dx = { 1, -1, 0, 0 };
	private static final int[] dy = { 0, 0, 1, -1 };
	private static int[][] graph = new int[8][8];
	private static boolean[][] visited = new boolean[8][8];
	private static int N, K;
	private static int answer = 0;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());

			answer = 0;
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int high = 0;
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					high = Math.max(high, graph[i][j]);
				}
			}

			List<Pair> highest = new ArrayList<>();
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (graph[i][j] == high)
						highest.add(new Pair(i, j));
				}
			}

			for (Pair p: highest) {
				visitedClear(N);
				visited[p.x][p.y] = true;
				dfs(p.x, p.y, true, 0);
			}
			sb.append("#").append(tc).append(" ")
				.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void dfs(int x, int y, boolean item, int depth) {
		answer = Math.max(answer, depth + 1);
		int nx, ny;
		for (int i = 0; i < 4; ++i) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (visited[nx][ny])
				continue;
			if (graph[nx][ny] >= graph[x][y]) {
				if (item && graph[nx][ny] - K < graph[x][y]) {
					int temp = graph[nx][ny];
					graph[nx][ny] = graph[x][y] - 1;
					visited[nx][ny] = true;
					dfs(nx, ny, false, depth + 1);
					graph[nx][ny] = temp;
					visited[nx][ny] = false;
				}
			} else {
				visited[nx][ny] = true;
				dfs(nx, ny, item, depth + 1);
				visited[nx][ny] = false;
			}
		}
	}

	private static void visitedClear(int N) {
		for (int i = 0; i < N; ++i) {
			Arrays.fill(visited[i], 0, N, false);
		}
	}
}
// https://www.acmicpc.net/problem/2234

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Castle {

	//								서 / 북 / 동 / 남
	private static final int[] wall = {1, 2, 4, 8};
	private static final int[] dx = {0, -1, 0, 1};
	private static final int[] dy = {-1, 0, 1, 0};

	private static int N, M;
	private static int[][] graph;
	private static int[][] partition;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		partition = new int[N][M];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int roomCount = 0;
		int maxSize = 0;
		List<Integer> roomSizes = new ArrayList<>();
		roomSizes.add(-1);

		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < M; ++y) {
				if (partition[x][y] == 0) {
					int size = bfs(x, y, ++roomCount);
					maxSize = Math.max(maxSize, size);
					roomSizes.add(size);
				}
			}
		}

		int maxSizeAfterRemoveOneWall = 0;
		for (int x = 0; x < N; ++x) {
			for (int y = 0; y < M; ++y) {
				for (int i = 0; i < 4; ++i) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (partition[x][y] == partition[nx][ny])
						continue;
					maxSizeAfterRemoveOneWall = Math.max(maxSizeAfterRemoveOneWall,
						roomSizes.get(partition[x][y]) + roomSizes.get(partition[nx][ny]));
				}
			}
		}

		System.out.println(roomCount);
		System.out.println(maxSize);
		System.out.println(maxSizeAfterRemoveOneWall);
	    br.close();
	}

	private static int bfs(int x, int y, int num) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(x, y));
		partition[x][y] = num;
		int area = 1;

		while (!queue.isEmpty()) {
			Pair p = queue.poll();

			for (int i = 0; i < 4; ++i) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (partition[nx][ny] > 0)
					continue;
				if (hasWall(graph[p.x][p.y], i) || hasWall(graph[nx][ny], i ^ 2))
					continue;
				partition[nx][ny] = num;
				queue.add(new Pair(nx, ny));
				++area;
			}
		}
		return area;
	}

	private static boolean hasWall(int info, int dir) {
		return (info & wall[dir]) > 0;
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
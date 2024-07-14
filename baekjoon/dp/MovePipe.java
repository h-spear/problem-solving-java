// https://www.acmicpc.net/problem/17069

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class MovePipe {

	private static final int HORIZONTAL = 0;
	private static final int VERTICAL = 1;
	private static final int DIAGONAL = 2;
	private static final long[][][] cache = new long[33][33][3];
	private static int N;
	private static int[][] graph;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(0, 1, HORIZONTAL));
	    br.close();
	}

	private static long dfs(int x, int y, int direction) {
		if (x == N - 1 && y == N - 1)
			return 1;
		if (cache[x][y][direction] != 0)
			return cache[x][y][direction];

		long temp = 0;
		if (direction != HORIZONTAL && x < N - 1 && graph[x + 1][y] == 0)
			temp += dfs(x + 1, y, VERTICAL);
		if (direction != VERTICAL && y < N - 1 && graph[x][y + 1] == 0)
			temp += dfs(x, y + 1, HORIZONTAL);
		if (x < N - 1 && y < N - 1 && graph[x + 1][y] == 0 &&
			graph[x][y + 1] == 0 && graph[x + 1][y + 1] == 0)
			temp += dfs(x + 1, y + 1, DIAGONAL);
		return cache[x][y][direction] = temp;
	}
}

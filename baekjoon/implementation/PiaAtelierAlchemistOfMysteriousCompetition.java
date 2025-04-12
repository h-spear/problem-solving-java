// https://www.acmicpc.net/problem/15898

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class PiaAtelierAlchemistOfMysteriousCompetition {

	private static final int[][] s = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	private static final int[][] temp = new int[11][11];

	private static final int WHITE = 0;
	private static final int RED = 7;
	private static final int BLUE = 5;
	private static final int GREEN = 3;
	private static final int YELLOW = 2;
	private static final int UNKNOWN = Integer.MIN_VALUE >> 2;

	private static int N;
	private static int[][][] x;
	private static int[][][] c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		x = new int[N][4][4];
		c = new int[N][4][4];

		int[][][] graph = new int[2][5][5];

		for (int index = 0; index < N; ++index) {
			for (int i = 0; i < 4; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; ++j) {
					x[index][i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < 4; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; ++j) {
					c[index][i][j] = convertColor(st.nextToken().charAt(0));
				}
			}
		}
		System.out.println(dfs(graph, 0, 0));
		br.close();
	}

	private static int dfs(int[][][] graph, int visit, int depth) {
		if (depth == 3) {
			return calculateQuality(graph);
		}

		int ret = Integer.MIN_VALUE;
		for (int i = 0; i < N; ++i) {
			if ((visit & (1 << i)) > 0)
				continue;
			for (int j = 0; j < 4; ++j, rotateDegree90(4, c[i]), rotateDegree90(4, x[i])) {
				for (int[] coordinate: s) {
					int[][][] copied = new int[][][] {
						arraycopy2D(graph[0], 0, 0, 5),
						arraycopy2D(graph[1], 0, 0, 5)
					};
					addIngredient(x[i], c[i], copied, coordinate[0], coordinate[1]);
					ret = Math.max(ret, dfs(copied, visit | (1 << i), depth + 1));
				}
			}
		}
		return ret;
	}

	private static int calculateQuality(int[][][] graph) {
		int quality = 0;
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				quality += graph[0][i][j] * graph[1][i][j];
			}
		}
		return quality;
	}

	private static void addIngredient(int[][] x, int[][] c, int[][][] trg, int trgX, int trgY) {
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				trg[0][i + trgX][j + trgY] = clamp(trg[0][i + trgX][j + trgY] + x[i][j], 0, 9);
				if (c[i][j] != 0)
					trg[1][i + trgX][j + trgY] = c[i][j] = c[i][j];
			}
		}
	}

	private static int clamp(int x, int min, int max) {
		x = Math.min(x, max);
		x = Math.max(x, min);
		return x;
	}

	private static int[][] arraycopy2D(int[][] src, int x, int y, int length) {
		int[][] copied = new int[length][length];
		for (int i = 0; i < length; ++i) {
			System.arraycopy(src[i + x], y, copied[i], 0, length);
		}
		return copied;
	}

	private static void rotateDegree90(int n, int[][] matrix) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				temp[i][j] = matrix[n - j - 1][i];
			}
		}
		for (int i = 0; i < n; ++i)
			System.arraycopy(temp[i], 0, matrix[i], 0, n);
	}

	private static int convertColor(char color) {
		switch (color) {
			case 'W' : return WHITE;
			case 'R' : return RED;
			case 'B' : return BLUE;
			case 'G' : return GREEN;
			case 'Y' : return YELLOW;
		}
		return UNKNOWN;
	}
}
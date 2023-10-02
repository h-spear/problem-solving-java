// https://www.acmicpc.net/problem/2239

package baekjoon.backtracking;

import java.io.*;

public class Sudoku2239 {

	private static boolean dfs(int[][] sudoku, int idx, StringBuilder sb) {
		if (idx >= 81) {
			for (int i = 0; i < 9; ++i) {
				for (int j = 0; j < 9; ++j)
					sb.append(sudoku[i][j]);
				sb.append("\n");
			}
			return true;
		}

		int r = idx / 9;
		int c = idx % 9;

		boolean find = false;
		if (sudoku[r][c] != 0) {
			find = dfs(sudoku, idx + 1, sb);
		} else {
			for (int num = 1; num <= 9 && !find; ++num) {
				if (validate(sudoku, r, c, num)) {
					sudoku[r][c] = num;
					find = dfs(sudoku, idx + 1, sb);
					sudoku[r][c] = 0;
				}
			}
		}
		return find;
	}

	private static boolean validate(int[][] sudoku, int x, int y, int num) {
		for (int i = 0; i < 9; ++i) {
			if (sudoku[i][y] == num)
				return false;
		}

		for (int j = 0; j < 9; ++j) {
			if (sudoku[x][j] == num)
				return false;
		}

		int _i = x - x % 3;
		int _j = y - y % 3;
		for (int i = _i; i < _i + 3; ++i) {
			for (int j = _j; j < _j + 3; ++j) {
				if (sudoku[i][j] == num)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] sudoku = new int[9][9];
		for (int i = 0; i < 9; ++i) {
			String line = br.readLine();
			for (int j = 0; j < 9; ++j) {
				sudoku[i][j] = line.charAt(j) - '0';
			}
		}

		StringBuilder sb = new StringBuilder();
		dfs(sudoku, 0, sb);
		System.out.println(sb.toString());
		br.close();
	}
}

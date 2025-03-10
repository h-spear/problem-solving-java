// https://www.acmicpc.net/problem/1574

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class RookAttack {

	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] board = new int[R][C];
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			board[r][c] = 1;
		}

		graph = new ArrayList<>(R + 1);
		for (int i = 0; i <= R; ++i) {
			graph.add(new ArrayList<>());
		}
		visited = new int[R + 1];
		match = new int[C + 1];

		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (board[i][j] == 0) {
					graph.get(i + 1).add(j + 1);
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= R; ++i) {
			++call;
			if (dfs(i))
				++answer;
		}
		System.out.println(answer);
	    br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == 0 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}
}

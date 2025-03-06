// https://www.acmicpc.net/problem/2188

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class BarnAssignment {

	private static int N, M;
	private static int[][] cow;
	private static int[] house;
	private static int[] visited;
	private static int call;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cow = new int[N + 1][];
		house = new int[M + 1];
		visited = new int[N + 1];

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			cow[i] = new int[S];
			for (int j = 0; j < S; ++j) {
				cow[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			++call;
			if (dfs(i)) ++answer;
		}
		System.out.println(answer);
	    br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;
		for (int c: cow[x]) {
			if (house[c] == 0 || dfs(house[c])) {
				house[c] = x;
				return true;
			}
		}
		return false;
	}
}

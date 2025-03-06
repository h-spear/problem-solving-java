// https://www.acmicpc.net/problem/11375

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class YeolhyeolGangho {

	private static int N, M;
	private static int[][] graph;
	private static int[] task;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][];
		task = new int[M + 1];
		visited = new int[N + 1];

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			graph[i] = new int[count];
			for (int j = 0; j < count; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		for (int i = 1; i <= N; ++i) {
			++call;
			if (dfs(i))
				++answer;
		}
		System.out.println(answer);
	    br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call) {
			return false;
		}

		visited[x] = call;
		for (int next: graph[x]) {
			if (task[next] == 0 || dfs(task[next])) {
				task[next] = x;
				return true;
			}
		}
		return false;
	}
}

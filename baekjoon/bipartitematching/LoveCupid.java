// https://www.acmicpc.net/problem/30976

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class LoveCupid {

	private static int N, M;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(solve());
	}

	private static int solve() {
		int count = 0;
		for (int i = 0; i < N; ++i) {
			++call;
			if (dfs(i))
				++count;
		}
		return count;
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == -1 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N);
		for (int i = 0; i < N; ++i) {
			graph.add(new ArrayList<>());
		}

		visited = new int[N];
		match = new int[M];
		Arrays.fill(match, -1);

		int[] G = new int[N];
		int[] B = new int[M];
		int[] L = new int[N];
		int[] U = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			G[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			L[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			U[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (B[j] < L[i] && G[i] > U[j]) {
					graph.get(i).add(j);
				}
			}
		}
		br.close();
	}
}

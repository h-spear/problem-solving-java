// https://www.acmicpc.net/problem/18138

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class RiyunaLikesSailorSuits {

	private static int N, M;
	private static List<List<Integer>> graph;
	private static int[] match;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new int[N];
		match = new int[M];
		Arrays.fill(match, -1);

		int[] first = new int[N];
		int[] second = new int[M];
		for (int i = 0; i < N; ++i) {
			first[i] = Integer.parseInt(br.readLine()) * 4;
		}
		for (int i = 0; i < M; ++i) {
			second[i] = Integer.parseInt(br.readLine()) * 4;
		}

		graph = new ArrayList<>(N);
		for (int i = 0; i < N; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (between(second[j], first[i] / 2, (first[i] * 3) / 4) ||
					between(second[j], first[i], (first[i] * 5) / 4))
					graph.get(i).add(j);
			}
		}

		int answer = 0;
		for (int i = 0; i < N; ++i) {
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

		for (int y: graph.get(x)) {
			if (match[y] == -1 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static boolean between(int value, int lower, int upper) {
		return lower <= value && value <= upper;
	}
}

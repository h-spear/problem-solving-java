// https://www.acmicpc.net/problem/11180

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class Paintball {

	private static int N;
	private static List<List<Integer>> graph;
	private static int[] visited;
	private static int[] match;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}
		visited = new int[N + 1];
		match = new int[N + 1];

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			graph.get(B).add(A);
		}

		int count = 0;
		for (int i = 1; i <= N; ++i) {
			++call;
			if (dfs(i)) ++count;
		}

		if (count < N) {
			System.out.println("Impossible");
		} else {
			print(match, 1, N + 1, '\n');
		}
	    br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call) {
			return false;
		}
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == 0 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static void print(int[] arr, int fromIndex, int toIndex, char separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = fromIndex; i < toIndex; ++i) {
			sb.append(arr[i]).append(separator);
		}
		System.out.println(sb.toString());
	}
}

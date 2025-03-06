// https://www.acmicpc.net/problem/1671

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class SharksDinner {

	private static int N;
	private static List<List<Integer>> graph;
	private static int[] eaten;
	private static int[] visited;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		eaten = new int[N];
		Arrays.fill(eaten, -1);

		visited = new int[N];
		graph = new ArrayList<>(N);
		for (int i = 0; i < N; ++i)
			graph.add(new ArrayList<>());

		Triplet[] sharks = new Triplet[N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			Triplet shark = sharks[i] = new Triplet();
			shark.v1 = Integer.parseInt(st.nextToken());
			shark.v2 = Integer.parseInt(st.nextToken());
			shark.v3 = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; ++i) {
			for (int j = i + 1; j < N; ++j) {
				int compare = compare(sharks[i], sharks[j]);
				if (compare == 1) {
					graph.get(j).add(i);
				} else if (compare == -1) {
					graph.get(i).add(j);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < 2; ++j) {
				++call;
				if (dfs(i))
					++count;
			}
		}
		System.out.println(N - count);
		br.close();
	}

	private static boolean dfs(int x) {
		if (visited[x] == call)
			return false;
		visited[x] = call;
		for (int y: graph.get(x)) {
			if (eaten[y] == -1 || dfs(eaten[y])) {
				eaten[y] = x;
				return true;
			}
		}
		return false;
	}

	private static int compare(Triplet t1, Triplet t2) {
		if (t1.v1 >= t2.v1 && t1.v2 >= t2.v2 && t1.v3 >= t2.v3) {
			return -1;
		} else if (t1.v1 <= t2.v1 && t1.v2 <= t2.v2 && t1.v3 <= t2.v3) {
			return 1;
		} else {
			return 0;
		}
	}

	private static class Triplet {
		int v1, v2, v3;
	}
}

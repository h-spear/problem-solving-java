// https://www.acmicpc.net/problem/20119

package baekjoon.topologysort;

import java.io.*;
import java.util.*;

public class ClareAndPotion {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i) {
			graph.add(new ArrayList<>());
		}

		int[] r = new int[M];
		int[] inDegree = new int[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int[] xs = new int[k];
			for (int j = 0; j < k; ++j) {
				xs[j] = Integer.parseInt(st.nextToken());
			}
			r[i] = Integer.parseInt(st.nextToken());

			inDegree[i] = k;
			for (int x: xs) {
				graph.get(x).add(i);
			}
		}

		int L = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		boolean[] hasPotion = new boolean[N + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < L; ++i) {
			int y = Integer.parseInt(st.nextToken());
			queue.add(y);
			hasPotion[y] = true;
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next: graph.get(now)) {
				if (hasPotion[r[next]])
					continue;
				if (--inDegree[next] == 0 && !hasPotion[r[next]]) {
					queue.add(r[next]);
					hasPotion[r[next]] = true;
				}
			}
		}

		int potionCnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; ++i) {
			if (hasPotion[i]) {
				++potionCnt;
				sb.append(i).append(" ");
			}
		}
		System.out.println(potionCnt);
		System.out.println(sb.toString());
		br.close();
	}
}

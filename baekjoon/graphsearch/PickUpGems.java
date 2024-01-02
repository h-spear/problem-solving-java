// https://www.acmicpc.net/problem/2001

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class PickUpGems {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i) {
			graph.add(new ArrayList<>());
		}

		int[] jewel = new int[n + 1];
		Arrays.fill(jewel, -1);
		for (int i = 0, num = 0; i < k; ++i, ++num) {
			jewel[Integer.parseInt(br.readLine())] = num;
		}

		for (int i = 0, a, b, c; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		Queue<Status> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n + 1][1 << k];
		queue.add(new Status(1, 0, 0));
		visited[0][0] = true;

		int answer = 0;
		while (!queue.isEmpty()) {
			Status now = queue.poll();

			if (now.idx == 1) {
				answer = Math.max(answer, now.count);
			}

			for (Node next: graph.get(now.idx)) {
				if (next.weight >= now.count && !visited[next.idx][now.jewels]) {
					visited[next.idx][now.jewels] = true;
					queue.add(new Status(next.idx, now.jewels, now.count));
				}

				if (jewel[next.idx] >= 0 && (now.jewels & (1 << jewel[next.idx])) == 0
						&& next.weight >= now.count && !visited[next.idx][now.jewels | (1 << jewel[next.idx])]) {
					visited[next.idx][now.jewels | (1 << jewel[next.idx])] = true;
					queue.add(new Status(next.idx, now.jewels | (1 << jewel[next.idx]),now.count + 1));
				}
			}
		}
		System.out.println(answer);
	    br.close();
	}

	private static class Status {
		int idx, jewels, count;

		public Status(int idx, int jewels, int count) {
			this.idx = idx;
			this.jewels = jewels;
			this.count = count;
		}
	}

	private static class Node {
		int idx, weight;

		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}
}

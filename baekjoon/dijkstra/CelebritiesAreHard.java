// https://www.acmicpc.net/problem/17270

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class CelebritiesAreHard {

	private static final int INF = Integer.MAX_VALUE;
	private static int V;
	private static List<List<Node>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>(V + 1);
		for (int i = 0; i <= V; ++i) {
			graph.add(new ArrayList<>());
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		int J = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] distanceJ = dijkstra(J);
		int[] distanceS = dijkstra(S);

		// find min distance sum
		int minDistanceSum = Integer.MAX_VALUE;
		for (int i = 1; i <= V; ++i) {
			if (i == J || i == S)
				continue;
			minDistanceSum = Math.min(minDistanceSum, distanceJ[i] + distanceS[i]);
		}

		// find
		int bestI = -1;
		int bestDistJ = Integer.MAX_VALUE;
		for (int i = 1; i <= V; ++i) {
			if (i == J || i == S)   // 1
				continue;
			if (distanceJ[i] + distanceS[i] > minDistanceSum)   // 2
				continue;
			if (distanceJ[i] > distanceS[i])    // 3
				continue;
			if (distanceJ[i] >= bestDistJ)   // 4
				continue;
			bestI = i;
			bestDistJ = distanceJ[i];
		}
		System.out.println(bestI);
		br.close();
	}

	private static int[] dijkstra(int start) {
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		heap.add(new Node(start, 0));
		int[] distance = new int[V + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance[currNode.idx] < currNode.cost)
				continue;

			for (Node nextNode: graph.get(currNode.idx)) {
				int cost = currNode.cost + nextNode.cost;

				if (distance[nextNode.idx] > cost) {
					distance[nextNode.idx] = cost;
					heap.add(new Node(nextNode.idx, cost));
				}
			}
		}
		return distance;
	}

	private static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
}

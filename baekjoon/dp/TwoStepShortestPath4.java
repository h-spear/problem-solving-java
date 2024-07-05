// https://www.acmicpc.net/problem/23840

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class TwoStepShortestPath4 {

	private static final long INF = Long.MAX_VALUE >> 4;
	private static final long[][] D = new long[22][22];
	private static final long[][] dp = new long[22][1 << 21];
	private static int countOfCities;
	private static int fullVisit;

	public static void main(String[] args) throws Exception {
		inputAndPreprocessing();
		long result = dfs(0, 1);
		System.out.println(result == INF ? -1 : result);
	}

	private static long dfs(int x, int visit) {
		if (visit == fullVisit) {
			if (D[x][countOfCities - 1] > 0)
				return D[x][countOfCities - 1];
			return INF;
		}
		if (dp[x][visit] != 0)
			return dp[x][visit];
		long temp = INF;
		for (int i = 0; i < countOfCities - 1; ++i) {
			if ((visit & (1 << i)) > 0)
				continue;
			temp = Math.min(temp, dfs(i, visit | (1 << i)) + D[x][i]);
		}
		return dp[x][visit] = temp;
	}

	private static void inputAndPreprocessing() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Node>> graph = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}

		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Z = Integer.parseInt(st.nextToken());

		int P = Integer.parseInt(br.readLine());
		int[] Y = new int[P];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < P; ++i) {
			Y[i] = Integer.parseInt(st.nextToken());
		}

		countOfCities = P + 2;
		fullVisit = (1 << (countOfCities - 1)) - 1;
		long[] distanceX = dijkstra(graph, N, X);
		long[] distanceZ = dijkstra(graph, N, Z);
		for (int i = 1; i <= P; ++i) {
			D[0][i] = D[i][0] = distanceX[Y[i - 1]];
			D[P + 1][i] = D[i][P + 1] = distanceZ[Y[i - 1]];
			long[] distance = dijkstra(graph, N, Y[i - 1]);
			for (int j = 1; j <= P; ++j) {
				D[i][j] = D[j][i] = distance[Y[j - 1]];
			}
		}
		br.close();
	}

	private static long[] dijkstra(List<List<Node>> graph, int n, int x) {
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Long.compare(o1.weight, o2.weight));
		heap.add(new Node(x, 0));
		long[] distance = new long[n + 1];
		Arrays.fill(distance, INF);
		distance[x] = 0;
		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance[currNode.index] < currNode.weight)
				continue;

			for (Node nextNode: graph.get(currNode.index)) {
				long cost = currNode.weight + nextNode.weight;
				if (distance[nextNode.index] > cost) {
					distance[nextNode.index] = cost;
					heap.add(new Node(nextNode.index, cost));
				}
			}
		}
		return distance;
	}

	private static class Node {
		int index;
		long weight;

		Node(int index, long weight) {
			this.index = index;
			this.weight = weight;
		}
	}
}

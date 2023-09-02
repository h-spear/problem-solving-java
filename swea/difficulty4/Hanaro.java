// 1251

package swea.difficulty4;

import java.io.*;
import java.util.*;

public class Hanaro {

	private static class Pair {
		int x, y;
	}

	private static class Edge {
		int a, b;
		double cost;

		public Edge(int a, int b, double cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
	}

	private static int find(int[] parent, int x) {
		if (parent[x] != x)
			parent[x] = find(parent, parent[x]);
		return parent[x];
	}

	private static void union(int[] parent, int a, int b) {
		a = find(parent, a);
		b = find(parent, b);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] parent = new int[1000];
		Pair[] island = new Pair[1000];
		Edge[] edges = new Edge[1000 * 1000];
		int N;
		double E;
		for (int i = 0; i < 1000; ++i) {
			island[i] = new Pair();
		}

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				island[i].x = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				island[i].y = Integer.parseInt(st.nextToken());
			}

			E = Double.parseDouble(br.readLine());

			int eIdx = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = i + 1; j < N; ++j) {
					edges[eIdx++] = new Edge(i, j,
						E * (Math.pow(island[i].x - island[j].x, 2) + Math.pow(island[i].y - island[j].y, 2)));
				}
			}

			Arrays.sort(edges, 0, eIdx, (o1, o2) -> Double.compare(o1.cost, o2.cost));

			for (int i = 0; i < N; ++i)
				parent[i] = i;

			double answer = 0;
			Edge edge;
			for (int i = 0; i < eIdx; ++i) {
				edge = edges[i];
				if (find(parent, edge.a) == find(parent, edge.b))
					continue;
				union(parent, edge.a, edge.b);
				answer += edge.cost;
			}

			sb.append("#").append(tc).append(" ")
				.append((long)Math.round(answer)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
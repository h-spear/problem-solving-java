// https://www.acmicpc.net/problem/1774

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class CosmicGodConsensus {

    class Edge {
        int x;
        int y;
        double cost;

        public Edge(int x, int y, double cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Pair> coords = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            coords.add(new Pair(x, y));
        }
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                double cost = Math.sqrt(
                        Math.pow((coords.get(i).x - coords.get(j).x), 2) +
                        Math.pow((coords.get(i).y - coords.get(j).y), 2));
                edges.add(new Edge(i + 1, j + 1, cost));
            }
        }

        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            parent[i] = i;
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (find(parent, x) != find(parent, y))
                union(parent, x, y);
        }

        edges.sort(Comparator.comparingDouble(a -> a.cost));

        double answer = 0;
        for (Edge edge: edges) {
            if (find(parent, edge.x) == find(parent, edge.y))
                continue;

            union(parent, edge.x, edge.y);
            answer += edge.cost;
        }

        System.out.printf("%.2f\n", answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new CosmicGodConsensus().solution();
    }
}

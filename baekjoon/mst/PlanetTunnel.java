// https://www.acmicpc.net/problem/2887

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class PlanetTunnel {

    class Pair {
        int v, idx;

        public Pair(int idx, int v) {
            this.idx = idx;
            this.v = v;
        }
    }

    class Edge {
        int x;
        int y;
        int cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
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

        int n = Integer.parseInt(br.readLine());
        List<Pair> xs = new ArrayList<>();
        List<Pair> ys = new ArrayList<>();
        List<Pair> zs = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            xs.add(new Pair(i, x));
            ys.add(new Pair(i, y));
            zs.add(new Pair(i, z));
        }

        xs.sort((a, b) -> a.v - b.v);
        ys.sort((a, b) -> a.v - b.v);
        zs.sort((a, b) -> a.v - b.v);

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            edges.add(new Edge(xs.get(i).idx, xs.get(i + 1).idx, Math.abs(xs.get(i).v - xs.get(i + 1).v)));
            edges.add(new Edge(ys.get(i).idx, ys.get(i + 1).idx, Math.abs(ys.get(i).v - ys.get(i + 1).v)));
            edges.add(new Edge(zs.get(i).idx, zs.get(i + 1).idx, Math.abs(zs.get(i).v - zs.get(i + 1).v)));
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }

        edges.sort((a, b) -> a.cost - b.cost);
        int answer = 0;
        for (Edge edge: edges) {
            if (find(parent, edge.x) == find(parent, edge.y))
                continue;

            union(parent, edge.x, edge.y);
            answer += edge.cost;
        }
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception      {
        new PlanetTunnel().solution();
    }
}

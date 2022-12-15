// https://www.acmicpc.net/problem/1922

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class NetworkConnection {

    class Edge {
        int a;
        int b;
        int c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
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

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            parent[i] = i;

        edges.sort((a, b) -> a.c - b.c);

        int answer = 0;
        for (Edge edge: edges) {
            if (find(parent, edge.a) == find(parent, edge.b))
                continue;
            answer += edge.c;
            union(parent, edge.a, edge.b);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new NetworkConnection().solution();
    }
}

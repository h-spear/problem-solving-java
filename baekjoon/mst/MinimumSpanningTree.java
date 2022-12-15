// https://www.acmicpc.net/problem/1197

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class MinimumSpanningTree {

    class Edge {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
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

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        int[] parent = new int[v + 1];
        for (int i = 0; i <= v; ++i)
            parent[i] = i;

        edges.sort((a, b) -> a.w - b.w);
        int answer = 0;
        for (Edge edge: edges) {
            if (find(parent, edge.s) == find(parent, edge.e)) {
                continue;
            }
            union(parent, edge.s, edge.e);
            answer += edge.w;
        }
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new MinimumSpanningTree().solution();
    }
}

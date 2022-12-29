// https://www.acmicpc.net/problem/4386

package baekjoon.mst;

import java.io.*;
import java.util.*;


public class Constellation {

    class Edge {
        int a, b;
        double cost;

        public Edge(int a, int b, double cost) {
            this.a = a;
            this.b = b;
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
        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            x[i] = a;
            y[i] = b;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                double cost = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                edges.add(new Edge(i, j, cost));
            }
        }
        edges.sort((a, b) -> (int)(a.cost - b.cost));

        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }

        double answer = 0;
        for (Edge edge: edges) {
            if (find(parent, edge.a) == find(parent, edge.b))
                continue;
            union(parent, edge.a, edge.b);
            answer += edge.cost;
        }
        System.out.printf("%.2f\n", answer);
        br.close();
    }

    public static void main(String[] args) throws Exception      {
        new Constellation().solution();
    }
}

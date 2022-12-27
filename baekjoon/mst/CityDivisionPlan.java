// https://www.acmicpc.net/problem/1647

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class CityDivisionPlan {

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

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        int[] parent = new int[n + 1];

        for (int i = 0; i <= n; ++i) {
            parent[i] = i;
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
        edges.sort((a, b) -> a.cost - b.cost);

        int answer = 0;
        int maxCost = 0;
        for (int i = 0; i < m; ++i) {
            Edge edge = edges.get(i);
            if (find(parent, edge.x) == find(parent, edge.y)) {
                continue;
            }
            union(parent, edge.x, edge.y);
            answer += edge.cost;
            maxCost = Math.max(maxCost, edge.cost);
        }
        System.out.println(answer - maxCost);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new CityDivisionPlan().solution();
    }
}

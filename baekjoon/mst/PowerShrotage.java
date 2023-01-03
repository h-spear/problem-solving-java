// https://www.acmicpc.net/problem/6497

package baekjoon.mst;

import java.io.*;
import java.util.*;

public class PowerShrotage {

    class Edge {
        int x, y, cost;

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

        while (true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0)
                break;

            List<Edge> edges = new ArrayList<>();

            int answer = 0;
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                edges.add(new Edge(x, y, z));
                answer += z;
            }

            int[] parent = new int[n + 1];
            for (int i = 0; i <= n; ++i)
                parent[i] = i;

            // kruskal
            edges.sort(Comparator.comparingInt(a -> a.cost));

            for (Edge edge: edges) {
                if (find(parent, edge.x) == find(parent, edge.y))
                    continue;
                union(parent, edge.x, edge.y);
                answer -= edge.cost;
            }

            System.out.println(answer);
        }

        br.close();
    }

    public static void main(String[] args) throws Exception {
        new PowerShrotage().solution();
    }
}

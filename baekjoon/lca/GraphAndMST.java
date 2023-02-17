package baekjoon.lca;// https://www.acmicpc.net/problem/15481

import java.io.*;
import java.util.*;

public class GraphAndMST {

    private static final int K = 18;
    private static int N, M;
    private static List<List<Node>> graph = new ArrayList<>();
    private static Edge[] edges;
    private static int[][] parent;
    private static int[][] distance;
    private static int[] depth;

    class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    class Edge implements Comparable<Edge> {
        int u, v, cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o2) {
            return this.cost - o2.cost;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new Edge[M];
        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];
        distance = new int[K + 1][N + 1];

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        int u, v, w;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(u, v, w);
        }

        // MST 길이 구함, tree graph 생성
        long mstLength = kruskal(edges, graph);

        // LCA 알고리즘을 위한 DFS
        dfs(1, 1);
        fillParent();

        for (Edge edge: edges) {
            bw.write(mstLength + edge.cost - getMaximumCost(edge.u, edge.v) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // LCA : a, b의 최단거리에 존재하는 가장 비용이 비싼 간선을 구함
    private long getMaximumCost(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        long res = 0;
        for (int i = K; i >= 0; --i) {
            if (depth[a] - (int) Math.pow(2, i) >= depth[b]) {
                res = Math.max(res, distance[i][a]);
                a = parent[i][a];
            }
        }

        if (a != b) {
            for (int i = K; i >= 0; --i) {
                if (parent[i][a] != parent[i][b]) {
                    res = Math.max(res, distance[i][a]);
                    res = Math.max(res, distance[i][b]);
                    a = parent[i][a];
                    b = parent[i][b];
                }
            }
            res = Math.max(res, distance[0][a]);
            res = Math.max(res, distance[0][b]);
        }
        return res;
    }

    private long kruskal(Edge[] edges, List<List<Node>> graph) {
        Edge[] cloned = edges.clone();
        Arrays.sort(cloned);

        long res = 0;

        int[] parent = new int[N + 1];
        for (int i = 0; i <= N; ++i) {
            parent[i] = i;
        }

        for (Edge edge: cloned) {
            if (find(parent, edge.u) == find(parent, edge.v)) {
                continue;
            }
            union(parent, edge.u, edge.v);
            res += edge.cost;
            graph.get(edge.u).add(new Node(edge.v, edge.cost));
            graph.get(edge.v).add(new Node(edge.u, edge.cost));
        }
        return res;
    }

    private void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void fillParent() {
        for (int i = 1; i <= K; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
                distance[i][j] = Math.max(distance[i-1][j], distance[i-1][parent[i-1][j]]);
            }
        }
    }

    private void dfs(int curr, int dep) {
        depth[curr] = dep;
        for (Node next: graph.get(curr)) {
            if (depth[next.idx] == 0) {
                parent[0][next.idx] = curr;
                distance[0][next.idx] = next.cost;
                dfs(next.idx, dep + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new GraphAndMST().solution();
    }
}

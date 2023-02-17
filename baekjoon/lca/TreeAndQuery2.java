// https://www.acmicpc.net/problem/13511

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class TreeAndQuery2 {

    private static final int K = 17;
    private static int N;
    private static List<List<Node>> tree = new ArrayList<>();
    private static int[][] parent;
    private static long[][] distance;
    private static int[] depth;

    class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int M, a, u, v, w, k;

        N = Integer.parseInt(br.readLine());

        parent = new int[K + 1][N + 1];
        distance = new long[K + 1][N + 1];
        depth = new int[N + 1];
        for (int i = 0; i <= N; ++i) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            tree.get(u).add(new Node(v, w));
            tree.get(v).add(new Node(u, w));
        }

        dfs(1, 1);
        fillParent();

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            if (a == 1) {
                bw.write(getDistance(u, v) + "\n");
            } else {
                k = Integer.parseInt(st.nextToken());
                bw.write(getKthNode(u, v, k) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int getKthNode(int u, int v,int k) {
        int lca = findLCA(u, v);
        int distUtoLCA = depth[u] - depth[lca];
        int distVtoLCA = depth[v] - depth[lca];
        int passNodeCount = distUtoLCA + distVtoLCA + 1;

        if (distUtoLCA >= k) {
            --k;
            for (int i = K; i >= 0; --i) {
                if ((1 << i) <= k) {
                    u = parent[i][u];
                    k -= (1 << i);
                }
            }
            return u;
        } else {
            k = passNodeCount - k;
            for (int i = K; i >= 0; --i) {
                if ((1 << i) <= k) {
                    v = parent[i][v];
                    k -= (1 << i);
                }
            }
            return v;
        }
    }

    private int findLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = K; i >= 0; --i) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = parent[i][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = K; i >= 0; --i) {
            if (parent[i][u] != parent[i][v]) {
                u = parent[i][u];
                v = parent[i][v];
            }
        }
        return parent[0][u];
    }

    private long getDistance(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        long dist = 0;
        for (int i = K; i >= 0; --i) {
            if (depth[u] - (1 << i) >= depth[v]) {
                dist += distance[i][u];
                u = parent[i][u];
            }
        }

        if (u != v) {
            for (int i = K; i >= 0; --i) {
                if (parent[i][u] != parent[i][v]) {
                    dist += distance[i][u];
                    dist += distance[i][v];
                    u = parent[i][u];
                    v = parent[i][v];
                }
            }
            dist += distance[0][u];
            dist += distance[0][v];
        }
        return dist;
    }

    private void fillParent() {
        for (int i = 1; i <= K; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
                distance[i][j] = distance[i-1][j] + distance[i-1][parent[i-1][j]];
            }
        }
    }

    private void dfs(int now, int dep) {
        depth[now] = dep;
        for (Node next: tree.get(now)) {
            if (depth[next.idx] == 0) {
                distance[0][next.idx] = next.cost;
                parent[0][next.idx] = now;
                dfs(next.idx, dep + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new TreeAndQuery2().solution();
    }
}

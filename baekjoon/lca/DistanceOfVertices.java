// https://www.acmicpc.net/problem/1761

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class DistanceOfVertices {

    private static int N, K;
    private static int[] depth;
    private static int[][] parent;
    private static int[][] distance;
    private static List<List<Node>> graph = new ArrayList<>();

    class Node {
        int idx, dist;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int M, A, B, C;

        N = Integer.parseInt(br.readLine());

        int[] counter = new int[N + 1];

        K = 0;
        for (int i = 1; i < N; i <<= 1) {
            ++K;
        }

        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];
        distance = new int[K + 1][N + 1];

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
            counter[B]++;
        }

        int root = -1;
        for (int i = 1; i <= N; ++i) {
            if (counter[i] == 0) {
                root = i;
            }
        }
        dfs(root, 1);

        for (int i = 1; i <= K; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
                distance[i][j] = distance[i-1][j] + distance[i-1][parent[i-1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            bw.write(lca(A, B) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int res = 0;

        // 높이 같도록
        for (int i = K; i >= 0; --i) {
            if (depth[a] - (int) Math.pow(2, i) >= depth[b]) {
                res += distance[i][a];
                a = parent[i][a];
            }
        }

        if (a != b) {
            for (int i = K; i >= 0; --i) {
                if (parent[i][a] != parent[i][b]) {
                    res += distance[i][a];
                    res += distance[i][b];
                    a = parent[i][a];
                    b = parent[i][b];
                }
            }
            res += distance[0][a];
            res += distance[0][b];
        }

        return res;
    }

    private void dfs(int curr, int dep) {
        depth[curr] = dep;

        for (Node nextNode: graph.get(curr)) {
            if (depth[nextNode.idx] == 0) {
                parent[0][nextNode.idx] = curr;
                distance[0][nextNode.idx] = nextNode.dist;
                dfs(nextNode.idx, dep + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new DistanceOfVertices().solution();
    }
}

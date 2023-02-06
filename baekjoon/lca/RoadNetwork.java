// https://www.acmicpc.net/problem/3176

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class RoadNetwork {

    private static final int P = 17;
    private static int N, K;
    private static List<List<Node>> graph = new ArrayList<>();
    private static int[] depth;
    private static int[][] parent;
    private static int[][] minDist;
    private static int[][] maxDist;

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

        int A, B, C, D, E;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        depth = new int[N + 1];
        parent = new int[P + 1][N + 1];
        minDist = new int[P + 1][N + 1];
        maxDist = new int[P + 1][N + 1];

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        dfs(1, 1);
        init();

        // query
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            int[] res = query(D, E);
            bw.write(res[0] + " " + res[1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int[] query(int a, int b) {

        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int shortest = Integer.MAX_VALUE;
        int longest = Integer.MIN_VALUE;

        // depth를 같게
        for (int i = P; i >= 0; --i) {
            if (depth[a] - (int) Math.pow(2, i) >= depth[b]) {
                shortest = Math.min(shortest, minDist[i][a]);
                longest = Math.max(longest, maxDist[i][a]);
                a = parent[i][a];
            }
        }

        // 부모가 다르다면
        if (a != b) {
            for (int i = P; i >= 0; --i) {
                if (parent[i][a] != parent[i][b]) {
                    shortest = Math.min(shortest, minDist[i][a]);
                    shortest = Math.min(shortest, minDist[i][b]);
                    longest = Math.max(longest, maxDist[i][a]);
                    longest = Math.max(longest, maxDist[i][b]);

                    a = parent[i][a];
                    b = parent[i][b];
                }
            }
            shortest = Math.min(shortest, minDist[0][a]);
            shortest = Math.min(shortest, minDist[0][b]);
            longest = Math.max(longest, maxDist[0][a]);
            longest = Math.max(longest, maxDist[0][b]);
        }

        return new int[] {shortest, longest};
    }

    private void init() {
        for (int i = 1; i <= P; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
                minDist[i][j] = Math.min(minDist[i-1][j], minDist[i-1][parent[i-1][j]]);
                maxDist[i][j] = Math.max(maxDist[i-1][j], maxDist[i-1][parent[i-1][j]]);
            }
        }
    }

    private void dfs(int curr, int dep) {
        depth[curr] = dep;

        for (Node nextNode: graph.get(curr)) {
            if (depth[nextNode.idx] == 0) {
                dfs(nextNode.idx, dep + 1);
                parent[0][nextNode.idx] = curr;
                minDist[0][nextNode.idx] = nextNode.cost;
                maxDist[0][nextNode.idx] = nextNode.cost;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new RoadNetwork().solution();
    }
}

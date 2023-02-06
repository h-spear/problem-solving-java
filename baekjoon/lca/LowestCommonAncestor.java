// https://www.acmicpc.net/problem/3584

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class LowestCommonAncestor {

    private static int N, K, T;
    private static int[] depth;
    private static int[][] parent;
    private static List<List<Integer>> graph;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; ++tc) {
            int A, B;

            N = Integer.parseInt(br.readLine());

            K = 0;
            for (int i = 1; i < N; i <<= 1) {
                ++K;
            }

            parent = new int[K + 1][N + 1];
            depth = new int[N + 1];

            graph = new ArrayList<>();
            for (int i = 0; i <= N; ++i) {
                graph.add(new ArrayList<>());
            }

            int[] counter = new int[N + 1];
            for (int i = 0; i < N - 1; ++i) {
                st = new StringTokenizer(br.readLine());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());

                counter[B]++;
                graph.get(A).add(B);
                graph.get(B).add(A);
            }

            int root = 0;
            for (int i = 1; i <= N; ++i) {
                if (counter[i] == 0) {
                    root = i;
                }
            }
            dfs(root, 1);

            // fill parent
            for (int i = 1; i <= K; ++i) {
                for (int j = 1; j <= N; ++j) {
                    parent[i][j] = parent[i-1][parent[i-1][j]];
                }
            }

            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            // lca
            if (depth[A] < depth[B]) {
                int temp = A;
                A = B;
                B = temp;
            }

            // 높이가 같도록
            for (int i = K; i >= 0; --i) {
                if (depth[A] - (int) Math.pow(2, i) >= depth[B]) {
                    A = parent[i][A];
                }
            }

            int result = A;
            if (A != B) {
                for (int i = K; i >= 0; --i) {
                    if (parent[i][A] != parent[i][B]) {
                        A = parent[i][A];
                        B = parent[i][B];
                    }
                    result = parent[i][A];
                }
            }
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private void dfs(int curr, int dep) {
        depth[curr] = dep;

        for (int next: graph.get(curr)) {
            if (depth[next] == 0) {
                parent[0][next] = curr;
                dfs(next, dep + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new LowestCommonAncestor().solution();
    }
}

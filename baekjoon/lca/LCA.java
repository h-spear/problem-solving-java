// https://www.acmicpc.net/problem/11437

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class LCA {

    private static int N, M;
    private static int[] depth;
    private static int[] parent;
    private static List<List<Integer>> graph = new ArrayList<>();

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        dfs(1, 1);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (depth[A] < depth[B]) {
                int temp = A;
                A = B;
                B = temp;
            }

            while (depth[A] > depth[B]) {
                A = parent[A];
            }

            while (A != B) {
                A = parent[A];
                B = parent[B];
            }
            bw.write(A + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void dfs(int curr, int dep) {
        depth[curr] = dep;

        for (int next: graph.get(curr)) {
            if (depth[next] == 0) {
                parent[next] = curr;
                dfs(next, dep + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new LCA().solution();
    }
}

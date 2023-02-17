// https://www.acmicpc.net/problem/8012

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class HandongIsSalesperson {

    private static final int K = 17;
    private static int N;
    private static int[] depth;
    private static int[][] parent;
    private static List<List<Integer>> graph = new ArrayList<>();

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        int a, b;
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];

        dfs(1, 1);

        // fill parent
        for (int i = 1; i <= K; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        int M = Integer.parseInt(br.readLine());
        int m, now = 1;
        long answer = 0;

        for (int i = 0; i < M; ++i) {
            m = Integer.parseInt(br.readLine());
            answer += lca(now, m);
            now = m;
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private long lca(int a, int b) {
        long res = 0;
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 높이를 같게
        for (int i = K; i >= 0; --i) {
            if (depth[a] - (int) Math.pow(2, i) >= depth[b]) {
                a = parent[i][a];
                res += (int) Math.pow(2, i);
            }
        }

        if (a != b) {
            for (int i = K; i >= 0; --i) {
                if (parent[i][a] != parent[i][b]) {
                    a = parent[i][a];
                    b = parent[i][b];
                    res += 2 * (int) Math.pow(2, i);
                }
            }
            res += 2;
        }
        return res;
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
        new HandongIsSalesperson().solution();
    }
}

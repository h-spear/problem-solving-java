// https://www.acmicpc.net/problem/15480

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class LcaAndQuery {

    private static int N, M;
    private static int[] depth = new int[100001];
    private static int[][] parent = new int[18][100001];
    private static List<List<Integer>> graph = new ArrayList<>();

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        getDepth(1, 1);

        for (int i = 1; i <= 17; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        // query
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int ru = lca(r, u);
            int rv = lca(r, v);
            int uv = lca(u, v);
            int res = -1;
            if (depth[ru] > depth[rv]) {
                if (depth[uv] > depth[ru]) {
                    res = uv;
                } else {
                    res = ru;
                }
            } else {
                if (depth[uv] > depth[rv]) {
                    res = uv;
                } else{
                    res = rv;
                }
            }
            bw.write(res + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private int lca(int x, int y) {
        if (depth[x] < depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        // 높이를 같게
        int pow;
        for (int i = 17; i >= 0; --i) {
            pow = (int) Math.pow(2, i);
            if (depth[x] - pow >= depth[y]) {
                x = parent[i][x];
            }
        }

        int ca = x;
        if (x != y) {
            for (int i = 17; i >= 0; --i) {
                if (parent[i][x] != parent[i][y]) {
                    x = parent[i][x];
                    y = parent[i][y];
                }
                ca = parent[i][x];
            }
        }
        return ca;
    }

    private void getDepth(int curr, int dep) {
        depth[curr] = dep;

        for (int next: graph.get(curr)) {
            if (depth[next] == 0) {
                parent[0][next] = curr;
                getDepth(next, dep + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new LcaAndQuery().solution();
    }
}

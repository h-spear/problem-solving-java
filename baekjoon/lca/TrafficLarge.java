// https://www.acmicpc.net/problem/12746

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class TrafficLarge {

    private static final int K = 18;
    private static int N;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[][] parent;
    private static int[] depth;
    private static int[] counter;
    private static boolean[] visited;
    private static int first, second, people = -1;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int Q, a, b, c, d;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        depth = new int[N + 1];
        parent = new int[K + 1][N + 1];
        counter = new int[N + 1];

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1, 1);
        fillParent();

        for (int i = 0; i < Q; ++i) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            int lca = findLCA(c, d);
            counter[c] += 1;
            counter[d] += 1;
            counter[lca] -= 2;
        }

        visited = new boolean[N + 1];
        dfs2(1);

        bw.write(first + " " + second + " " + people);
        bw.flush();
        bw.close();
        br.close();
    }

    private void dfs2(int node) {
        visited[node] = true;

        for (int next: graph.get(node)) {
            if (visited[next]) {
                continue;
            }
            dfs2(next);

            int a, b, c;

            a = Math.min(node, next);
            b = Math.max(node, next);
            c = counter[next];

            if (people < c) {
                people = c;
                first = a;
                second = b;
            } else if (people == c) {
                if ((first > a) || (first == a && second > b)) {
                    first = a;
                    second = b;
                }
            }

            counter[node] += c;
        }
    }

    private int findLCA(int c, int d) {
        if (depth[c] < depth[d]) {
            int temp = c;
            c = d;
            d = temp;
        }

        for (int i = K; i >= 0; --i) {
            if (depth[c] - (int) Math.pow(2, i) >= depth[d]) {
                c = parent[i][c];
            }
        }

        int lca = c;
        if (c != d) {
            for (int i = K; i >= 0; --i) {
                if (parent[i][c] != parent[i][d]) {
                    c = parent[i][c];
                    d = parent[i][d];
                }
            }
            lca = parent[0][c];
        }
        return lca;
    }

    private void fillParent() {
        for (int i = 1; i <= K; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
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
        new TrafficLarge().solution();
    }
}

// https://www.acmicpc.net/problem/11438

package baekjoon.lca;

import java.io.*;
import java.util.*;

public class LCA2 {

    private static int N, M;
    private static int[] depth;
    private static int[][] parent;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        depth = new int[N + 1];
        parent = new int[18][N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1, 1);
        fillParent();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(query(a, b) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int query(int a, int b) {
        int depthA = depth[a];
        int depthB = depth[b];

        // depth가 깊은 것이 A
        if (depthA < depthB) {
            int temp = depthA;
            depthA = depthB;
            depthB = temp;

            temp = a;
            a = b;
            b = temp;
        }

        // 서로의 높이가 같도록
        for (int j = 17; j >= 0; --j) {
            int pow = (int) Math.pow(2, j);
            if (depthA - pow >= depthB) {
                a = parent[j][a];
                depthA -= pow;
            }

            if (depthA == depthB) {
                break;
            }
        }

        int answer = a;
        if (a != b) {
            for (int j = 17; j >= 0; --j) {
                if (parent[j][a] != parent[j][b]) {
                    a = parent[j][a];
                    b = parent[j][b];
                }
                answer = parent[j][a];
            }
        }
        return answer;
    }

    // depth 배열을 생성하는 dfs
    private static void dfs(int curr, int dep) {
        depth[curr] = dep;

        for (int next: graph.get(curr)) {
            if (depth[next] == 0) { // 아직 depth가 구해지지 않았음.
                parent[0][next] = curr; // 직계부모는 curr
                dfs(next, dep + 1);
            }
        }
    }

    private static void fillParent() {
        for (int i = 1; i <= 17; ++i) {
            for (int j = 1; j <= N; ++j) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }
}

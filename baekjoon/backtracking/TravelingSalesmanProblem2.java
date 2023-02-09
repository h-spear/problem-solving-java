// https://www.acmicpc.net/problem/10971

package baekjoon.backtracking;

import java.io.*;
import java.util.*;

public class TravelingSalesmanProblem2 {

    private int N;
    private int[][] W;
    private boolean[] visited;
    private int answer = Integer.MAX_VALUE;

    private void dfs(int curr, int cost, int depth) {
        visited[curr] = true;

        if (depth == N - 1) {
            if (W[curr][0] != 0) {
                answer = Math.min(answer, cost + W[curr][0]);
            }
        } else {
            for (int next = 1; next < N; ++next) {
                if (visited[next])
                    continue;
                if (W[curr][next] == 0)
                    continue;
                dfs(next, cost + W[curr][next], depth + 1);
            }
        }

        visited[curr] = false;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        bw.write(answer + " ");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new TravelingSalesmanProblem2().solution();
    }
}

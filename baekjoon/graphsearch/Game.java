// https://www.acmicpc.net/problem/1103

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Game {

    private static int N, M;
    private static char[][] graph;
    private static boolean[][] visited;
    private static int[][] dp;
    private static boolean isCycle = false;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                graph[i][j] = line.charAt(j);
            }
        }

        System.out.println(dfs(0, 0));
        br.close();
    }

    private int dfs(int x, int y) {
        if (dp[x][y] != 0 || isCycle) {
            return dp[x][y] + 1;
        }

        // 1.
        visited[x][y] = true;

        // 2.

        // 3. 연결된 곳
        int nx, ny;
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i] * (graph[x][y] - '0');
            ny = y + dy[i] * (graph[x][y] - '0');

            // 4.
            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            if (graph[nx][ny] == 'H')
                continue;
            if (visited[nx][ny]) {
                isCycle = true;
                break;
            }
            // 5.
            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny));
        }

        // 6.
        visited[x][y] = false;

        if (isCycle) {
            return -1;
        } else {
            return dp[x][y] + 1;
        }
    }

    public static void main(String[] args) throws Exception {
        new Game().solution();
    }
}

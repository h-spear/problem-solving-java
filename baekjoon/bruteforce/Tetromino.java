// https://www.acmicpc.net/problem/14500

package baekjoon.bruteforce;

import java.io.*;
import java.util.*;

public class Tetromino {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N, M, answer;
    private static int[][] graph;
    private static boolean[][] visited;

    private static void dfs(int x, int y, int sum, int depth) {
        if (depth == 3) {
            answer = Math.max(answer, sum);
            return;
        }
        visited[x][y] = true;

        int nx, ny;
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            if (visited[nx][ny])
                continue;
            dfs(nx, ny, sum + graph[nx][ny], depth + 1);
        }
        visited[x][y] = false;
    }

    private static void checkPink(int x, int y) {
        int[] nums = {-1, -1, -1, -1};
        int nx, ny, cnt = 0;
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            nums[i] = graph[nx][ny];
            ++cnt;
        }

        if (cnt < 3)
            return;

        for (int i = 0; i < 4; ++i) {
            if (nums[i] < 0)
                continue;
            for (int j = i + 1; j < 4; ++j) {
                if (nums[j] < 0)
                    continue;
                for (int k = j + 1; k < 4; ++k) {
                    if (nums[k] < 0)
                        continue;
                    answer = Math.max(answer, graph[x][y] + nums[i] + nums[j] + nums[k]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                dfs(i, j, graph[i][j], 0);
                checkPink(i, j);
            }
        }
        System.out.println(answer);
        br.close();
    }
}

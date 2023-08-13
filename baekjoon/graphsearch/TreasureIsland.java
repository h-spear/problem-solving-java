// https://www.acmicpc.net/problem/2589

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class TreasureIsland {

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N, M;
    private static char[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j)
                graph[i][j] = line.charAt(j);
        }

        int answer = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (graph[i][j] == 'L')
                    answer = Math.max(answer, bfs(i, j));
            }
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<Pair> queue = new ArrayDeque<>(13);
        boolean[][] visited = new boolean[N][M];
        queue.offer(new Pair(x, y));
        visited[x][y] = true;

        int nx, ny, cnt = -1;
        while (!queue.isEmpty()) {
            ++cnt;
            int queueSize = queue.size();
            for (int q = 0; q < queueSize; ++q) {
                Pair p = queue.poll();

                for (int i = 0; i < 4; ++i) {
                    nx = p.x + dx[i];
                    ny = p.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;
                    if (visited[nx][ny])
                        continue;
                    if (graph[nx][ny] == 'W')
                        continue;
                    queue.offer(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        return cnt;
    }
}

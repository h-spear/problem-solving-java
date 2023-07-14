// https://www.acmicpc.net/problem/16946

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class BreakTheWallAndMove4 {

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N, M;
    private static int[][] graph;
    private static int[][] visited;
    private static int[] size;
    private static int groupNum;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        // flood fill
        groupNum = 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (graph[i][j] == 0 && visited[i][j] == 0) {
                    bfs(i, j, groupNum++);
                }
            }
        }
        fillSizeArray();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (graph[i][j] == 1) {
                    graph[i][j] = (getAdjArea(i, j) + 1) % 10;
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                bw.write(String.valueOf(graph[i][j]));
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private void fillSizeArray() {
        size = new int[groupNum];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (visited[i][j] != 0) {
                    ++size[visited[i][j]];
                }
            }
        }
    }

    private int getAdjArea(int x, int y) {
        int nx, ny, res = 0;
        boolean[] checker = new boolean[groupNum];
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            if (graph[nx][ny] != 0)
                continue;
            if (checker[visited[nx][ny]])
                continue;
            res += size[visited[nx][ny]];
            checker[visited[nx][ny]] = true;
        }
        return res;
    }

    private void bfs(int x, int y, int num) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = num;
        int nx, ny;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; ++i) {
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                if (graph[nx][ny] != 0)
                    continue;
                if (visited[nx][ny] != 0)
                    continue;
                queue.add(new Pair(nx, ny));
                visited[nx][ny] = num;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new BreakTheWallAndMove4().solution();
    }
}

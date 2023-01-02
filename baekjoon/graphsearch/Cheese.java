// https://www.acmicpc.net/problem/2636

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Cheese {
    private static int n;
    private static int m;
    private static int[][] graph;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void bfs(int x, int y, boolean[][] visited) {
        if (graph[x][y] != 0 || visited[x][y])
            return;

        int nx, ny;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;

            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (graph[nx][ny] == 1)
                    continue;

                visited[nx][ny] = true;
                queue.add(new Pair(nx, ny));
            }
        }
    }

    private boolean[][] getAirContactPosition() {
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; ++i) {
            bfs(i, 0, visited);
            bfs(i, m - 1, visited);
        }
        for (int j = 0; j < m; ++j) {
            bfs(0, j, visited);
            bfs(n - 1, j, visited);
        }
        return visited;
    }

    private int simul() {
        int nx, ny;
        boolean[][] air = getAirContactPosition();
        boolean[][] removed = new boolean[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (graph[i][j] == 0)
                    continue;
                for (int k = 0; k < 4; ++k) {
                    nx = i + dx[k];
                    ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                        continue;
                    if (air[nx][ny])
                        removed[i][j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (removed[i][j])
                    graph[i][j] = 0;

                if (graph[i][j] == 1)
                    count++;
            }
        }
        return count;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        int curr = 0;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1)
                    curr++;
            }
        }

        int prev = curr, t = 0;
        while (curr != 0) {
            prev = curr;
            curr = simul();
            ++t;
        }
        System.out.println(t);
        System.out.println(prev);
        br.close();
    }

    public static void main(String[] args) throws Exception      {
        new Cheese().solution();
    }
}

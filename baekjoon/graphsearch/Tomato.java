// https://www.acmicpc.net/problem/7576

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Tomato {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};


    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m, n, answer = 0;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m];
        int[][] visited = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {
                    queue.add(new Pair(i, j));
                    visited[i][j] = 1;
                }
            }
        }

        int x, y, nx, ny;

        while (queue.size() != 0) {
            int queueSize = queue.size();
            for (int k = 0; k < queueSize; ++k) {
                Pair pair = queue.poll();
                x = pair.x;
                y = pair.y;

                for (int i = 0; i < 4; ++i) {
                    nx = x + dx[i];
                    ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                        continue;
                    if (visited[nx][ny] != 0)
                        continue;
                    if (graph[nx][ny] == 1 || graph[nx][ny] == -1)
                        continue;

                    visited[nx][ny] = visited[x][y] + 1;
                    queue.add(new Pair(nx, ny));
                    graph[nx][ny] = 1;
                }
            }
        }

        boolean flag = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (graph[i][j] == -1)
                    continue;
                if (graph[i][j] == 0)
                    flag = false;
                answer = answer > visited[i][j] - 1 ? answer : visited[i][j] - 1;
            }
        }

        if (!flag)
            answer = -1;

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Tomato().solution();
    }
}

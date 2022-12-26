// https://www.acmicpc.net/problem/10026

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class RedGreenColourBlindness {
    private static final int RED = 1;
    private static final int GREEN = 2;
    private static final int BLUE = 3;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int bfs(int x, int y, int n, int[][] graph, boolean[][] visited, boolean blindness) {
        int nx, ny, color = graph[x][y];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;

            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (blindness) {
                    if (color == BLUE && graph[nx][ny] != BLUE)
                        continue;
                    if (color != BLUE && graph[nx][ny] == BLUE)
                        continue;
                } else if (graph[nx][ny] != color) {
                    continue;
                }

                queue.add(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return 1;
    }

    private void handler(int n, int[][] graph, int[] answer, boolean blindness) {
        int idx = blindness ? 1 : 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (visited[i][j])
                    continue;
                answer[idx] += bfs(i, j, n, graph, visited, blindness);
            }
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 1: red, 2: green, 3: blue
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < n; ++j) {
                switch (line.charAt(j)) {
                    case 'B':
                        graph[i][j] = BLUE;
                        break;
                    case 'G':
                        graph[i][j] = GREEN;
                        break;
                    case 'R':
                        graph[i][j] = RED;
                        break;
                }
            }
        }
        int[] answer = {0, 0};
        handler(n, graph, answer, true);
        handler(n, graph, answer, false);
        System.out.println(answer[0] + " " + answer[1]);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new RedGreenColourBlindness().solution();
    }
}

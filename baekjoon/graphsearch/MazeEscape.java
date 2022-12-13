package baekjoon.graphsearch;// https://www.acmicpc.net/problem/2178

import java.io.*;
import java.util.*;

public class MazeEscape {
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

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                if (line.charAt(j) == '1')
                    graph[i][j] = 1;
            }
        }

        Pair pair;
        int x, y, nx, ny;
        int[][] visited = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0));
        visited[0][0] = 1;

        while (queue.size() != 0) {
            pair = queue.poll();
            x = pair.x;
            y = pair.y;

            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                if (visited[nx][ny] != 0)
                    continue;
                if (graph[nx][ny] == 0)
                    continue;
                visited[nx][ny] = visited[x][y] + 1;
                queue.add(new Pair(nx, ny));
            }
        }
        System.out.println(visited[n-1][m-1]);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new MazeEscape().solution();
    }
}
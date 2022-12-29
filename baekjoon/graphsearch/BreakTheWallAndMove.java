// https://www.acmicpc.net/problem/2206

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class BreakTheWallAndMove {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    class Pair {
        int x, y, crush;

        public Pair(int x, int y, int crush) {
            this.x = x;
            this.y = y;
            this.crush = crush;
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
                graph[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        int visited[][][] = new int[n][m][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                visited[i][j][0] = INF;
                visited[i][j][1] = INF;
            }
        }
        visited[0][0][0] = 1;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0, 0));
        int x, y, crush, nx, ny;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;
            crush = pair.crush;

            for (int i = 0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                if (graph[nx][ny] == 1) {
                    if (crush == 0 && visited[nx][ny][1] == INF) {
                        visited[nx][ny][1] = visited[x][y][crush] + 1;
                        queue.add(new Pair(nx, ny, 1));
                    }
                    continue;
                }
                if (visited[nx][ny][crush] != INF)
                    continue;
                queue.add(new Pair(nx, ny, crush));
                visited[nx][ny][crush] = visited[x][y][crush] + 1;
            }
        }

        int answer = Math.min(visited[n-1][m-1][0], visited[n-1][m-1][1]);
        if (answer == INF)
            answer = -1;

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new BreakTheWallAndMove().solution();
    }
}

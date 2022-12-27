// https://www.acmicpc.net/problem/7569

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Tomato2 {

    private static final int[] dx = {-1, 1, 0, 0, 0, 0};
    private static final int[] dy = {0, 0, 1, -1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, 1, -1};

    class Pair {
        int x, y, z;

        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Queue<Pair> queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][][] graph = new int[h][n][m];
        int[][][] visited = new int[h][n][m];
        for (int i = 0 ; i < h; ++i) {
            for (int j = 0; j < n; ++j) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; ++k) {
                    graph[i][j][k] = Integer.parseInt(st.nextToken());
                    if (graph[i][j][k] == 1) {
                        queue.add(new Pair(i, j, k));
                        visited[i][j][k] = 1;
                    }
                }
            }
        }

        // bfs
        int x, y, z, nx, ny, nz;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;
            z = pair.z;

            for (int i = 0; i < 6; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];
                nz = z + dz[i];

                if (nx < 0 || ny < 0 || nz < 0 || nx >= h || ny >= n || nz >= m)
                    continue;
                if (visited[nx][ny][nz] != 0)
                    continue;
                if (graph[nx][ny][nz] != 0)
                    continue;

                queue.add(new Pair(nx, ny, nz));
                graph[nx][ny][nz] = 1;
                visited[nx][ny][nz] = visited[x][y][z] + 1;
            }
        }

        boolean flag = true;
        int answer = 0;
        for (int i = 0 ; i < h; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < m; ++k) {
                    if (graph[i][j][k] == 0) {
                        flag = false;
                    }
                    answer = Math.max(answer, visited[i][j][k] - 1);
                }
            }
        }

        if (flag)
            System.out.println(answer);
        else
            System.out.println(-1);
    }

    public static void main(String[] args) throws Exception {
        new Tomato2().solution();
    }
}

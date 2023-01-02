// https://www.acmicpc.net/problem/1520
// dfs + dp

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Downhill {

    private static int n;
    private static int m;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int[][] graph;
    private static int[][] visited;

    private int dfs(int x, int y) {
        int nx, ny;
        if (x == m - 1 && y == n - 1)
            return 1;
        if (visited[x][y] != -1)
            return visited[x][y];

        visited[x][y] = 0;
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                continue;
            if (graph[x][y] <= graph[nx][ny])
                continue;
            visited[x][y] += dfs(nx, ny);
        }
        return visited[x][y];
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[m][n];
        visited = new int[m][n];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
        br.close();
    }

    public static void main(String[] args) throws Exception      {
        new Downhill().solution();
    }
}

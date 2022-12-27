// https://www.acmicpc.net/problem/1012

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class OrganicCabbage {

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

    private int bfs(int x, int y, int n, int m, boolean[][] graph, boolean[][] visited) {
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
                if (!graph[nx][ny])
                    continue;
                if (visited[nx][ny])
                    continue;

                queue.add(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return 1;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = 0;
        int t = Integer.parseInt(br.readLine());
        while (tc < t) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            boolean[][] graph = new boolean[n][m];
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < k; ++i) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[y][x] = true;
            }

            int answer = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (visited[i][j])
                        continue;
                    if (!graph[i][j])
                        continue;
                    answer += bfs(i, j, n, m, graph, visited);
                }
            }
            System.out.println(answer);
            ++tc;
        }
    }

    public static void main(String[] args) throws Exception {
        new OrganicCabbage().solution();
    }
}

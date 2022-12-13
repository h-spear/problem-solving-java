// https://www.acmicpc.net/problem/4963

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class IslandCount {
    private static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    private static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
    private static int w;
    private static int h;
    private static int[][] graph;
    private static boolean[][] visited;

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int bfs(int x, int y) {
        int nx, ny;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;
        while (queue.size() != 0) {
            Pair pair = queue.poll();
            x = pair.x;
            y = pair.y;

            for (int i = 0; i < 8; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h || ny >= w)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (graph[nx][ny] == 0)
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

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0)
                break;

            graph = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; ++j) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < w; ++j) {
                    if (visited[i][j])
                        continue;
                    if (graph[i][j] == 0)
                        continue;
                    answer += bfs(i, j);
                }
            }
            System.out.println(answer);
        }
    }

    public static void main(String[] args) throws Exception {
        new IslandCount().solution();
    }
}

// https://www.acmicpc.net/problem/14502

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Laboratory {

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int n;
    private static int m;
    private static int answer = 0;
    private static List<Pair> candidate = new LinkedList<>();

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int bfs(int[][] graph) {
        int[][] visited = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j)
                if (graph[i][j] == 2) {
                    visited[i][j] = 1;
                    queue.add(new Pair(i, j));
                }
        }

        int x, y, nx, ny;
        while (queue.size() != 0) {
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
                if (graph[nx][ny] >= 1)
                    continue;

                queue.add(new Pair(nx, ny));
                graph[nx][ny] = 2;
                visited[nx][ny] = 1;
            }
        }

        int count = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j)
                if (graph[i][j] == 0)
                    count++;
        }
        return count;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private void dfs(int graph[][], int depth, int idx) {
        int[][] copied;
        if (depth == 3) {
            answer = max(answer, bfs(graph));
            return;
        }

        for (int i = idx; i < candidate.size(); ++i) {
            copied = copyGraph(graph);
            Pair p = candidate.get(i);
            copied[p.x][p.y] = 1;
            dfs(copied, depth + 1, i + 1);
        }
    }

    private int[][] copyGraph(int[][] graph) {
        int[][] copied = new int[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                copied[i][j] = graph[i][j];
        return copied;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 0)
                    candidate.add(new Pair(i, j));
            }
        }

        dfs(graph, 0, 0);
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Laboratory().solution();
    }
}

// https://www.acmicpc.net/problem/20058

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class WizardSharkAndFirestorm {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int[][] rotateDegree90(int[][] graph, int x, int y, int size) {
        int[][] rotated = new int[size][size];
        for (int i = x; i < x + size; ++i) {
            for (int j = y; j < y + size; ++j) {
                rotated[j - y][size - (i - x) - 1] = graph[i][j];
            }
        }
        return rotated;
    }

    private void firestorm(int[][] graph, int n, int l) {
        int size = (int) Math.pow(2, l);
        int[][] rotated;

        // move
        for (int i = 0; i < n; i += size) {
            for (int j = 0; j < n; j += size) {
                rotated = rotateDegree90(graph, i, j, size);
                for (int _i = i; _i < i + size; ++_i) {
                    for (int _j = j; _j < j + size; ++_j) {
                        graph[_i][_j] = rotated[_i - i][_j - j];
                    }
                }
            }
        }

        // ice
        int nx, ny;
        boolean[][] flag = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (graph[i][j] == 0)
                    continue;

                int count = 0;
                for (int k = 0; k < 4; ++k) {
                    nx = i + dx[k];
                    ny = j + dy[k];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || graph[nx][ny] == 0)
                        ++count;
                }
                if (count >= 2) {
                    flag[i][j] = true;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (flag[i][j])
                    --graph[i][j];
            }
        }
    }

    private int getTotalIce(int[][] graph, int n) {
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                res += graph[i][j];
            }
        }
        return res;
    }

    private int bfs(int x, int y, int[][] graph, int n, boolean[][] visited) {
        int nx, ny, size = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            ++size;
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
                if (graph[nx][ny] == 0)
                    continue;

                queue.add(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return size;
    }

    private int getLargestIceBlockSize(int[][] graph, int n) {
        boolean[][] visited = new boolean[n][n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (graph[i][j] == 0)
                    continue;
                if (visited[i][j])
                    continue;
                res = Math.max(res, bfs(i, j, graph, n, visited));
            }
        }
        return res;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        int q = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; ++i) {
            int l = Integer.parseInt(st.nextToken());
            firestorm(graph, n, l);
        }

        // print
        System.out.println(getTotalIce(graph, n));
        System.out.println(getLargestIceBlockSize(graph, n));
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new WizardSharkAndFirestorm().solution();
    }
}

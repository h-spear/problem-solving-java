// https://www.acmicpc.net/problem/21610

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class WizardSharkAndRainDance {

    private static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void move(Queue<Pair> cloud, int d, int s, int n) {
        for (int i = 0; i < cloud.size(); ++i) {
            Pair pair = cloud.poll();
            int nx = (pair.x + s * dx[d] + s * n) % n;
            int ny = (pair.y + s * dy[d] + s * n) % n;
            cloud.add(new Pair(nx, ny));
        }
    }

    private boolean[][] rainfallAndWaterCopy(Queue<Pair> cloud, int[][] graph, int n) {
        // rainfall
        boolean[][] waterCopy = new boolean[n][n];
        while (!cloud.isEmpty()) {
            Pair pair = cloud.poll();
            ++graph[pair.x][pair.y];
            waterCopy[pair.x][pair.y] = true;
        }

        int nx, ny;
        int[][] temp = new int[n][n];

        // water copy
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!waterCopy[i][j])
                    continue;

                int count = 0;
                for (int k = 1; k < 8; k += 2) {
                    nx = i + dx[k];
                    ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                        continue;
                    if (graph[nx][ny] != 0)
                        ++count;
                }
                temp[i][j] += count;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                graph[i][j] += temp[i][j];
            }
        }
        return waterCopy;
    }

    private void generateCloud(Queue<Pair> cloud, int[][] graph, int n, boolean[][] removed) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (removed[i][j])
                    continue;
                if (graph[i][j] >= 2) {
                    graph[i][j] -= 2;
                    cloud.add(new Pair(i, j));
                }
            }
        }
    }

    private int getTotalWater(int[][] graph, int n) {
        int water = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                water += graph[i][j];
            }
        }
        return water;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Pair> cloud = new LinkedList<>();
        cloud.add(new Pair(n-2, 0));
        cloud.add(new Pair(n-2, 1));
        cloud.add(new Pair(n-1, 0));
        cloud.add(new Pair(n-1, 1));
        boolean[][] removed;

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // rain dance
            move(cloud, d-1, s, n);    // 1
            removed = rainfallAndWaterCopy(cloud, graph, n);// 2, 3, 4
            generateCloud(cloud, graph, n, removed); // 5
        }

        System.out.println(getTotalWater(graph, n));
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new WizardSharkAndRainDance().solution();
    }
}

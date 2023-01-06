// https://www.acmicpc.net/problem/4485

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class GreenClothesZelda {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    class Pair {
        int x, y, dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = 0;
        while (true) {
            ++tc;
            int n = Integer.parseInt(br.readLine());

            if (n == 0)
                break;

            int[][] graph = new int[n][n];
            int[][] distance = new int[n][n];
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; ++j) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = INF;
                }
            }
            distance[0][0] = graph[0][0];
            PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) -> a.dist - b.dist);
            heap.add(new Pair(0, 0, graph[0][0]));
            int x, y, dist, nx, ny;
            while (!heap.isEmpty()) {
                Pair pair = heap.poll();
                x = pair.x;
                y = pair.y;
                dist = pair.dist;

                if (dist > distance[x][y])
                    continue;

                for (int i = 0; i < 4; ++i) {
                    nx = x + dx[i];
                    ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                        continue;
                    int cost = distance[x][y] + graph[nx][ny];
                    if (distance[nx][ny] > cost) {
                        distance[nx][ny] = cost;
                        heap.add(new Pair(nx, ny, cost));
                    }
                }
            }
            bw.write("Problem " + tc + ": " + distance[n-1][n-1]);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new GreenClothesZelda().solution();
    }
}

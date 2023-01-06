// https://www.acmicpc.net/problem/1261

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class Algospot {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    class Pair {
        int x;
        int y;
        int dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                graph[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        int[][] distance = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                distance[i][j] = INF;
            }
        }
        distance[0][0] = 0;
        PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        heap.add(new Pair(0, 0, 0));
        int nx, ny, cost;
        while (!heap.isEmpty()) {
            Pair curr = heap.poll();

            if (curr.dist > distance[curr.x][curr.y])
                continue;

            for (int i = 0; i < 4; ++i) {
                nx = curr.x + dx[i];
                ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;
                cost = distance[curr.x][curr.y] + graph[nx][ny];
                if (distance[nx][ny] > cost) {
                    distance[nx][ny] = cost;
                    heap.add(new Pair(nx, ny, cost));
                }
            }
        }
        System.out.println(distance[n-1][m-1]);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Algospot().solution();
    }
}

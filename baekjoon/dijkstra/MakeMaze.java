// https://www.acmicpc.net/problem/2665

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class MakeMaze {

    private static final int INF = (int) 1e9;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int[][] graph;
    private static int N;
    private static int[][] distance;

    class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dist=" + dist +
                    '}';
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        distance = new int[N][N];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < N; ++j) {
                graph[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        for (int i = 0; i < N; ++i) {
            Arrays.fill(distance[i], INF);
        }

        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        heap.add(new Node(0, 0, 0));
        distance[0][0] = 0;

        int nx, ny, cost;
        while (heap.size() > 0) {
            Node currNode = heap.poll();

            if (distance[currNode.x][currNode.y] < currNode.dist)
                continue;

            for (int i = 0; i < 4; ++i) {
                nx = currNode.x + dx[i];
                ny = currNode.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                cost = currNode.dist + (1 - graph[nx][ny]);
                if (distance[nx][ny] > cost) {
                    distance[nx][ny] = cost;
                    heap.add(new Node(nx, ny, cost));
                }
            }
        }

        bw.write(distance[N-1][N-1] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new MakeMaze().solution();
    }
}

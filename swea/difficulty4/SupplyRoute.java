// 1249

package swea.difficulty4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class SupplyRoute {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N;
    private static int[][] graph;

    static class Node {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(br.readLine());
            graph = new int[N][N];
            for (int i = 0; i < N; ++i) {
                String line = br.readLine();
                for (int j = 0; j < N; ++j) {
                    graph[i][j] = line.charAt(j) - '0';
                }
            }
            bw.write("#" + tc + " " + dijkstra() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int dijkstra() {
        int[][] distance = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> heap = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost));
        distance[0][0] = 0;
        heap.add(new Node(0, 0, 0));
        int nx, ny, cost;
        while (heap.size() > 0) {
            Node currNode = heap.poll();

            for (int i = 0; i < 4; ++i) {
                nx = currNode.x + dx[i];
                ny = currNode.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                cost = currNode.cost + graph[nx][ny];
                if (distance[nx][ny] > cost) {
                    distance[nx][ny] = cost;
                    heap.add(new Node(nx, ny, cost));
                }
            }
        }
        return distance[N-1][N-1];
    }
}
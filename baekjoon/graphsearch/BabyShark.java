// https://www.acmicpc.net/problem/16236

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class BabyShark {

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int n;
    private static Pair sharkPos;
    private static int sharkSize = 2;
    private static int eatCount = 0;
    private static int answer = 0;

    private boolean bfs(int[][] graph) {
        int[][] visited = new int[n][n];
        Pair fish = new Pair(99, 99);
        visited[sharkPos.x][sharkPos.y] = 1;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(sharkPos);

        int x, y, nx, ny;
        while (queue.size() != 0) {
            boolean flag = false;

            int queueSize = queue.size();
            for (int k = 0; k < queueSize; ++k) {
                Pair pair = queue.poll();
                x = pair.x;
                y = pair.y;

                for (int i = 0; i < 4; ++i) {
                    nx = x + dx[i];
                    ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                        continue;
                    if (visited[nx][ny] != 0)
                        continue;
                    if (graph[nx][ny] > sharkSize)
                        continue;

                    visited[nx][ny] = visited[x][y] + 1;
                    queue.add(new Pair(nx, ny));

                    if (graph[nx][ny] == 0 || graph[nx][ny] >= sharkSize)
                        continue;

                    if (fish.x > nx || (fish.x == nx && fish.y > ny)) {
                        fish = new Pair(nx, ny);
                        flag = true;
                    }
                }
            }
            if (flag)
                break;
        }

        if (fish.x == 99 && fish.y == 99)
            return false;


        graph[sharkPos.x][sharkPos.y] = 0;
        graph[fish.x][fish.y] = 9;
        sharkPos = fish;
        answer += visited[fish.x][fish.y] - 1;
        eatCount++;
        if (eatCount == sharkSize) {
            eatCount = 0;
            sharkSize++;
        }
        return true;
    }


    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9)
                    sharkPos = new Pair(i, j);
            }
        }

        while (bfs(graph));
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new BabyShark().solution();
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
